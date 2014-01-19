package ca.vinote.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.vinote.exception.CustomException;
import ca.vinote.model.DatosUsuario;
import ca.vinote.model.DetallePedido;
import ca.vinote.model.Pedido;
import ca.vinote.model.Usuario;
import ca.vinote.model.Vino;
import ca.vinote.service.IPedidoService;
import ca.vinote.service.IUsuarioService;
import ca.vinote.service.IVinoService;
import ca.vinote.util.CookieManager;
import ca.vinote.util.Estado;
import ca.vinote.util.JsonManager;

/**
 * Controller encargado de operaciones sobre el carrito y de compra de pedidos
 * 
 * @author Alvaro Gomez
 * 
 */
@Controller
@RequestMapping("/pedido/*")
public class PedidoController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Recoge el carrito de las cookies y lo mete en sesión. Redirige hacia la
	 * vista de modificaci�n del carrito
	 * 
	 * @param request
	 * @param sesion
	 * @return
	 */
	@RequestMapping("carrito.do")
	public String irCarrito(HttpServletRequest request, HttpSession sesion) {
		logger.info("Redirigiendo al carrito");

		IVinoService vinoService = context.getBean("vinoService",
				IVinoService.class);
		Map<Vino, Integer> carrito = new HashMap<Vino, Integer>();

		Map<Integer, Integer> vinos = CookieManager.obtenerVinos(request);
		for (Entry<Integer, Integer> v : vinos.entrySet()) {
			Vino vino = null;
			try {
				vino = vinoService.obtener(v.getKey());
			} catch (CustomException e) {
				e.printStackTrace();
			}
			Integer cantidad = v.getValue();
			carrito.put(vino, cantidad);
		}
		sesion.setAttribute("carrito", carrito);

		return "/jsp/carrito.jsp";
	}

	/**
	 * Redirige hacia la segunda vista de la compra (Datos de entrega)
	 * 
	 * @return
	 */
	@RequestMapping("iniciar_pedido.do")
	public String irIniciarPedido() {
		logger.info("Redirigiendo al inicio del pedido...");

		return "/jsp/detalle_pedido.jsp";
	}

	/**
	 * Recoge los datos de usuario, los actualiza en sesi�n y en base datos.
	 * Redirige hacia la tercera vista de la compra (Confirmaci�n del pedido)
	 * 
	 * @param fecha_nacimiento
	 * @param datosUsuario
	 * @param sesion
	 * @return
	 */
	@RequestMapping("vista_confirmar_pedido.do")
	public String irConfirmarPedido(@RequestParam String fecha_nacimiento,
			DatosUsuario datosUsuario, HttpSession sesion) {
		logger.info("Redirigiendo a confirmar pedido...");

		// TODO buscar una manera simple de parsear la fecha
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
		Date date = null;
		try {
			date = df.parse(fecha_nacimiento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		datosUsuario.setFechaNacimiento(date);

		IUsuarioService uService = context.getBean("usuarioService",
				IUsuarioService.class);
		try {
			Usuario u = uService.obtener((Usuario) sesion.getAttribute("user"));
			datosUsuario.setUsuario(u);
			// TODO Se utiliza la lista como si solo tuviese un elemento
			List<DatosUsuario> du = uService.obtenerDatosUsuario(u);
			if (du == null || du.size() == 0)
				uService.modificarDatosUsuario(datosUsuario);
			else {
				DatosUsuario duPersist = du.get(0);
				datosUsuario.setId(duPersist.getId());
				uService.modificarDatosUsuario(datosUsuario);
			}

			// refresca el usuario en sesion con los nuevos datos
			u = uService.obtener(u);
			sesion.setAttribute("user", u);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		return "/jsp/confirmacion_pedido.jsp";
	}

	/**
	 * Crea el pedido con los datos introducidos en los pasos anteriores de la
	 * compra (Carrito y Datos de Usuario).
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("confirmar_pedido.do")
	public String confirmarPedido(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("Pedido confirmado");

		IVinoService vinoService = context.getBean("vinoService",
				IVinoService.class);

		Pedido pedido = new Pedido();
		List<DetallePedido> detallesPedido = new LinkedList<DetallePedido>();

		// Recoge los datos del carrito y los almacena en el modelo en el caso
		// de que el carrito contenga algo
		Map<Integer, Integer> vinos = CookieManager.obtenerVinos(request);
		if (!vinos.isEmpty()) {
			for (Entry<Integer, Integer> v : vinos.entrySet()) {
				DetallePedido detallePedido = new DetallePedido();
				Vino vino = null;
				try {
					vino = vinoService.obtener(v.getKey());
				} catch (CustomException e) {
					e.printStackTrace();
				}
				detallePedido.setVino(vino);
				detallePedido.setCantidad(v.getValue());
				detallePedido.setPrecio(vino.getPrecio());
				detallePedido.setPedido(pedido);
				detallesPedido.add(detallePedido);
			}
			pedido.setDetallesPedido(detallesPedido);
			pedido.setUsuario((Usuario) request.getSession().getAttribute(
					"user"));
			pedido.setEstado(Estado.PENDIENTE_PAGO);
			pedido.setFecha(new Date());

			// Agrega el pedido utilizando el service
			IPedidoService pedidoService = context.getBean("pedidoService",
					IPedidoService.class);
			try {
				pedidoService.agregar(pedido);
			} catch (CustomException e) {
				e.printStackTrace();
			}

			// borrado de las cookies
			CookieManager.borrarVinos(request, response);
		}

		return "/usuario/perfil.do";
	}

	/**
	 * Cambia el estado del pedido en orden de PENDIENTE_PAGAR ->
	 * PENDIENTE_CONFIRMAR -> CONFIRMADO. En el caso de que el parámetro
	 * pedidoEstado sea nulo el pedido pasará a tener estado RECHAZADO.
	 * 
	 * @param referer
	 * @param pedidoId
	 *            String id del pedido
	 * @param pedidoEstado
	 *            String actual Estado del pedido
	 * @param codigoBancareo
	 *            String en el caso de que el estado sea PENDIENTE_PAGAR es
	 *            obligatorio
	 * @param motivoRechazo
	 *            String motivos del rechazo
	 * @return
	 */
	@RequestMapping("cambiar_estado.do")
	public String cambiarEstadoPedido(@RequestHeader String referer,
			@RequestParam String pedidoId, Estado pedidoEstado,
			String codigoBancareo, String motivoRechazo) {
		logger.info("Cambiando el estado del pedido...");
		Pedido p = new Pedido();
		p.setId(Integer.parseInt(pedidoId));
		IPedidoService pService = context.getBean("pedidoService",
				IPedidoService.class);
		try {
			p = pService.obtener(p);
			if (pedidoEstado == null) {
				p.setMotivoRechazo(motivoRechazo);
				p.setEstado(Estado.RECHAZADO);
			} else if (pedidoEstado.equals(Estado.PENDIENTE_PAGO)) {
				p.setCodigoConfirmacion(codigoBancareo);
				p.setEstado(Estado.PENDIENTE_CONFIRMACION);
			} else if (pedidoEstado.equals(Estado.PENDIENTE_CONFIRMACION)) {
				p.setEstado(Estado.CONFIRMADO);
			}

			pService.modificar(p);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		return "redirect:" + referer;
	}

	/**
	 * Obtiene los detalles de un pedido y los devuelve como un JSON
	 * 
	 * @param pedidoId
	 * @return
	 */
	@RequestMapping(value = "obtener_detalle_pedido.do", produces = "application/json; charset=ISO-8859-1")
	public @ResponseBody
	String obtenerDetallePedido(@RequestBody String pedidoId) {
		logger.info("Obtener detalle pedido: " + pedidoId);
		IPedidoService pService = context.getBean("pedidoService",
				IPedidoService.class);

		Pedido pedido = new Pedido();
		pedido.setId(Integer.parseInt(pedidoId));
		List<DetallePedido> dp = null;
		try {
			dp = pService.obtenerDetallePedido(pedido);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		return JsonManager.toJSONArray(dp).toString();

	}
}
