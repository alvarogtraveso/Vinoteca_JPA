package ca.vinote.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Clase encargada de las operacion de la aplicación con las cookies
 * 
 * @author Alvaro Gomez
 * 
 */
public class CookieManager {

	/**
	 * Elimina las cookies correspondientes al carrito
	 * 
	 * @param req
	 * @param resp
	 */
	public static void borrarVinos(HttpServletRequest req,
			HttpServletResponse resp) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (Cookie c : cookies) {
				String[] name = c.getName().split("_");
				if (name[0].equals("vino")) {
					c.setValue("");
					c.setMaxAge(0);
					c.setPath("/");
					resp.addCookie(c);
				}
			}
	}

	/**
	 * Recoge los vinos guardados en cookies. Devuelve un Map donde la clave es
	 * el id del vino y el value su cantidad (Map<id del vino, cantidad>)
	 * 
	 * @param req
	 * @return Map<Integer,Integer> la clave del map es el id del vino mientras
	 *         que el value es su cantidad
	 */
	public static Map<Integer, Integer> obtenerVinos(HttpServletRequest req) {
		Map<Integer, Integer> vinos = new HashMap<Integer, Integer>();

		Cookie[] cookies = req.getCookies();
		if (cookies != null)
			for (Cookie c : cookies) {
				String[] name = c.getName().split("_");
				if (name[0].equals("vino")) {
					vinos.put(Integer.parseInt(name[1]),
							Integer.parseInt(c.getValue()));
				}
			}

		return vinos;
	}

	/**
	 * Comprueba si la cookie es un vino del carrito
	 * 
	 * @param cookieName
	 *            el name de la cookie
	 * @return
	 */
	private boolean esVino(String cookieName) {
		boolean esVino = false;

		String[] s = cookieName.split("_");
		if (s[0].equals("vino")) {
			esVino = true;
		}

		return esVino;
	}
}
