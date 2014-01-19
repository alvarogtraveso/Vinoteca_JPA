package ca.vinote.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.annotation.Secured;

import ca.vinote.exception.CustomException;
import ca.vinote.model.Bodega;
import ca.vinote.model.Vino;
import ca.vinote.service.IBodegaService;
import ca.vinote.service.IPedidoService;
import ca.vinote.service.IVinoService;

/**
 * Encargado de controlar las acciones del administrador.
 * 
 * @author Alvaro Gomez
 * 
 */
@Controller
@Secured(value = { "ROLE_ADMIN" })
@RequestMapping("/admin/*")
public class AdminController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Redirige hacia la vista de pedidos del aministrador
	 * 
	 * @return
	 */
	@RequestMapping("/pedidos.do")
	public String irAPedidos() {
		logger.info("Redirigiendo a historial de pedidos (admin)...");

		return "/jsp/admin_pedidos.jsp";
	}

	/**
	 * Redirige hacia la vista de administración de vinos
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/vinos.do")
	public String irAVinos(Model model) {
		logger.info("Redirigiendo a la vista de vinos (admin)...");

		IBodegaService bService = context.getBean("bodegaService",
				IBodegaService.class);
		IVinoService vService = context.getBean("vinoService",
				IVinoService.class);
		try {
			model.addAttribute("vinos", vService.obtenerVinos());
			model.addAttribute("bodegas", bService.obtenerTodos());
		} catch (CustomException e) {
			System.err.println("Error cargando las bodegas para el admin");
			e.printStackTrace();
		}

		return "/jsp/admin_vinos.jsp";
	}

	/**
	 * Encargado de obtener los pedidos del service y redirigirlos a la vista
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/listar_pedidos.do")
	public String listarPedidos(Model model) {
		logger.info("Listando pedidos...");

		IPedidoService pedidoService = context.getBean("pedidoService",
				IPedidoService.class);
		try {
			model.addAttribute("pedidos", pedidoService.obtenerPedidos());
		} catch (CustomException e) {
			e.printStackTrace();

		}

		return "/jsp/tablas/tabla_admin_pedidos.jsp";
	}

	/**
	 * Encargado de recoger la información del vino y enviarla al service para
	 * agregarlo a la db
	 * 
	 * @param vino
	 * @param bodega_id
	 * @return
	 */
	@RequestMapping("/agregar_vino.do")
	public String agregarVino(Vino vino, String bodega_id) {
		logger.info("Agregando vino");

		Bodega b = new Bodega();
		b.setId(Integer.parseInt(bodega_id));
		vino.setBodega(b);
		IVinoService vService = context.getBean("vinoService",
				IVinoService.class);
		try {
			vService.agregar(vino);
		} catch (CustomException e) {
			System.err.println("Error agregando el vino.");
			e.printStackTrace();

		}

		return "redirect:/admin/vinos.do";
	}
	
	/**
	 * Encargado de recoger la información del vino y enviarla al service para su modificación.
	 * 
	 * @param vino
	 * @param bodega_id
	 * @return
	 */
	@RequestMapping("/modificar_vino.do")
	public String modificarVino(Vino vino, String bodega_id) {
		logger.info("Modificando vino");
		
		Bodega b = new Bodega();
		b.setId(Integer.parseInt(bodega_id));
		vino.setBodega(b);
		
		IVinoService vService = context.getBean("vinoService",
				IVinoService.class);
		try {			
			Vino v = vService.obtener(vino);
			v.setBodega(b);
			v.setCategoria(vino.getCategoria());
			v.setDescripcion(vino.getDescripcion());
			v.setImagen(vino.getImagen());
			v.setNombre(vino.getNombre());
			v.setPrecio(vino.getPrecio());
			v.setAnio(vino.getAnio());
			vService.modificar(v);
		} catch (CustomException e) {
			System.err.println("Error agregando el vino.");
			e.printStackTrace();

		}
		
		return "redirect:/admin/vinos.do";
	}

}
