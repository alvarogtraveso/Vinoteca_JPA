package ca.vinote.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.vinote.exception.CustomException;
import ca.vinote.model.Usuario;
import ca.vinote.service.IPedidoService;
import ca.vinote.service.IUsuarioService;
import ca.vinote.util.Estado;
import ca.vinote.util.NivelUsuario;

/**
 * Encargado de manejar las acciones del usuario.
 * 
 * @author Alvaro Gomez
 * 
 */
@Controller
@RequestMapping("/usuario/*")
public class UsuarioController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Realiza el login
	 * 
	 * @param email
	 * @param password
	 * @param referer
	 * @param sesion
	 * @param model
	 * @return
	 */
	@RequestMapping("login.do")
	public String login(@RequestParam String email,
			@RequestParam String password, @RequestHeader String referer,
			HttpSession sesion, Model model) {
		logger.info("Logeando");

		Usuario u = new Usuario(email, password);

		IUsuarioService uService = context.getBean("usuarioService",
				IUsuarioService.class);
		try {
			u = uService.validar(u);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		sesion.setAttribute("user", u);

		return "redirect:" + referer;
	}

	/**
	 * Realiza el logout quitando la variable del usuario de la sesi�n.
	 * 
	 * @param sesion
	 * @return
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession sesion) {
		logger.info("Logout");
		sesion.removeAttribute("user");
		return "redirect:/home/home.do";
	}

	/**
	 * Redirige hacia la vista de perfil del usuario. Añade como atributo el
	 * historico de pedidos según el parámetro estado.
	 * 
	 * @param estado
	 *            String es un filtro para la obtención de pedidos según su
	 *            estado
	 * @param sesion
	 * @return
	 */
	@RequestMapping("perfil.do")
	public String irPerfil(String estado, HttpSession sesion) {
		Usuario u = (Usuario) sesion.getAttribute("user");
		IPedidoService pedidoService = context.getBean("pedidoService",
				IPedidoService.class);

		try {
			if (estado == null || estado.equals(""))
				sesion.setAttribute("pedidos", pedidoService.obtener(u));
			else {
				sesion.setAttribute("pedidos",
						pedidoService.obtener(u, Estado.valueOf(estado)));
			}
		} catch (CustomException e) {
			e.printStackTrace();
		}

		return "/jsp/perfil.jsp";
	}

	/**
	 * Redirige a la vista de registro
	 * 
	 * @return
	 */
	@RequestMapping("registro.do")
	public String irRegistro() {
		return "/jsp/registro.jsp";
	}

	/**
	 * Encargado de validar y registrar un nuevo usuario.
	 * 
	 * @param password_confirm
	 * @param u
	 * @param sesion
	 * @return
	 */
	@RequestMapping("registrar_usuario.do")
	public String registrarUsuario(@RequestParam String password_confirm,
			Usuario u, HttpSession sesion) {
		logger.info("Registrando usuario...");

		if (!u.getPassword().equals("")
				&& u.getPassword().equals(password_confirm)) { // Comprobando si
																// las
																// contraseñas
																// son validas
			IUsuarioService uService = context.getBean("usuarioService",
					IUsuarioService.class);
			try {
				if (uService.obtener(u) == null) { // Comprueba que el usuario
													// no exista
					u.setNivel(NivelUsuario.FREE);
					uService.agregar(u);
					sesion.setAttribute("user", u);
					return "/jsp/perfil.jsp";
				}
			} catch (CustomException e) {
				e.printStackTrace();
			}

		}

		return "redirect:/usuario/registro.do";
	}

	/**
	 * Redirige a la vista de modificar perfil
	 * 
	 * @return
	 */
	@RequestMapping("vista_modificar_perfil.do")
	public String irModificarPerfil() {
		logger.info("Redirigiendo a la vista de modificar perfil...");

		return "/jsp/perfil_modificar.jsp";
	}

	/**
	 * Encargado de validar y modificar los datos de un usuario.
	 * 
	 * @param password_confirm
	 * @param u
	 * @param sesion
	 * @return
	 */
	@RequestMapping("modificar_usuario.do")
	public String modificarUsuario(@RequestParam String password_confirm,
			Usuario u, HttpSession sesion) {
		logger.info("Modificando usuario...");

		if (u.getPassword().equals(password_confirm)) {
			IUsuarioService uService = context.getBean("usuarioService",
					IUsuarioService.class);

			u.setNivel(NivelUsuario.FREE);
			try {
				uService.modificar(u);
			} catch (CustomException e) {
				e.printStackTrace();
			}
			sesion.setAttribute("user", u);
		}

		return "/jsp/perfil.jsp";
	}

	/**
	 * Recibe un email, en el caso de exista en la base de datos devuelve un
	 * html imagen de warning, en el caso de que no exista la imagen indicará el
	 * que es valido.
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("validar_registro.do")
	public @ResponseBody
	String validarRegistro(@RequestBody String email) {
		logger.info("Validando email de registro...[" + email + "]");

		IUsuarioService uService = context.getBean("usuarioService",
				IUsuarioService.class);
		Usuario u = new Usuario();
		u.setEmail(email);
		try {
			u = uService.obtener(u);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		if (u == null) {
			return "<img src=\"../img/valid.png\"/>";
		} else {
			return "<img src=\"../img/invalid.png\"/>";
		}
	}

}
