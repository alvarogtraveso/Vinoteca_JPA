package ca.vinote.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.vinote.exception.CustomException;
import ca.vinote.service.IVinoService;

/**
 * Controller para operaciones de busqueda
 * 
 * @author Alvaro Gomez
 *
 */
@Controller
@RequestMapping("/busqueda/*")
public class BusquedaController extends BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * Obtiene las novedades entre los vinos y los a�ade como atributo
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("novedades.do")
	public String obtenerNovedades(Model model) {
		logger.info("Listando Novedades");
		
		IVinoService vinoService = context.getBean("vinoService", IVinoService.class);
		model.addAttribute("titulo", "NOVEDADES");
		try {
			model.addAttribute("vinos",vinoService.obtenerNovedades());
		} catch (CustomException e) {
			e.printStackTrace();
		}	
		
		return "/jsp/tablas/tabla_home.jsp";
	}
	
	/**
	 * Obtiene los vinos más vendidos y los añade como atributo
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("top_ventas.do")
	public String obtenerTopVentas(Model model) {
		logger.info("Listando Top ventas");

		IVinoService vinoService = context.getBean("vinoService", IVinoService.class);
		model.addAttribute("titulo", "TOP VENTAS");
		try {
			model.addAttribute("vinos",vinoService.obtenerTopVentas());
		} catch (CustomException e) {
			e.printStackTrace();
		}	
		
		return "/jsp/tablas/tabla_home.jsp";
	}
	
	/**	
	 * Pide al servicio el resultado de la busqueda y lo  añade como atributo
	 * 
	 * @param filtro_busqueda
	 * @param model
	 * @return
	 */
	@RequestMapping("busqueda_vinos.do")
	public String busqueda(@RequestParam String filtro_busqueda, Model model) {
		logger.info("Redirigiendo a la pagina de busqueda... " + filtro_busqueda);

		IVinoService vinoService = context.getBean("vinoService", IVinoService.class);
		model.addAttribute("titulo", "Resultado de la búsqueda");
		try {
			model.addAttribute("vinos",vinoService.buscarVinos(filtro_busqueda));
		} catch (CustomException e) {
			e.printStackTrace();
		}
		return "/jsp/busqueda.jsp";
	}
}
