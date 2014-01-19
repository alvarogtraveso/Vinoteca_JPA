package ca.vinote.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.vinote.exception.CustomException;
import ca.vinote.service.IBodegaService;

@Controller
@RequestMapping("/home/*")
public class HomeController extends BaseController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping("/home.do")
	public String doPost() {
		return "/jsp/home.jsp";
	}

	/**
	 * Carga las bodegas para la cabecera de la vista
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/cabecera.do")
	public String cabecera(Model model) {
		IBodegaService bService = context.getBean("bodegaService",
				IBodegaService.class);

		try {
			model.addAttribute("bodegas", bService.obtenerTodos());
		} catch (CustomException e) {
			System.err.println("Error cargando las bodegas para la cabecera");
			e.printStackTrace();
		}

		return "../jsp/header.jsp";
	}
}
