package ca.vinote.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.vinote.model.DetallePedido;

/**
 * Encargado de realizar operaciones entre Objetos Java y JSON
 * 
 * @author Alvaro Gomez
 * 
 */
public class JsonManager {

	/**
	 * Convierte una List<DetallePedido> en un Array JSON.
	 * 
	 * @param detallesPedido
	 * @return
	 */
	public static JSONArray toJSONArray(List<DetallePedido> detallesPedido) {
		JSONArray jArray = new JSONArray();
		try {
			for (DetallePedido d : detallesPedido) {
				JSONObject dJSON = new JSONObject();
				dJSON.put("id", d.getId());
				dJSON.put("vinoId", d.getVino().getId());
				dJSON.put("vinoNombre", d.getVino().getNombre());
				dJSON.put("vinoAnio", d.getVino().getAnio());
				Float precio = d.getVino().getPrecio() * d.getCantidad();
				dJSON.put("vinoPrecio", precio);
				dJSON.put("vinoBodega", d.getVino().getBodega().getNombre());
				dJSON.put("vinoCategoria", d.getVino().getCategoria()
						.getCategoria());
				dJSON.put("cantidad", d.getCantidad());
				jArray.put(dJSON);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jArray;
	}
}
