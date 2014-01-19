/**
 * Obtiene los detalles de un pedido mediante una peticion POST con AJAX y los
 * pinta en el html.
 * 
 * @param pedidoId
 */
function pushDetalles(pedidoId) {
	console.log(pedidoId);
	var div = "#response" + pedidoId;

	$(div).html('<img src="../img/ajax-loader.gif" />');
	$.ajax({
		type : "POST",
		url : "../pedido/obtener_detalle_pedido.do",
		contentType : "application/json; charset=UTF-8",
		dataType : "json",
		data : '' + pedidoId + '',
		error : function() {
			alert("error petición ajax");
		},
		success : function(data) {
			pintarTablaDetalles(data, div);
			$("#mostrar_detalles" + pedidoId).attr("onclick",
					"popDetalles(" + pedidoId + ")");
		}
	});

}

/**
 * Borra la tabla de detalles del pedido y actualiza el boton 'Detalles'
 * 
 * @param id
 */
function popDetalles(id) {
	$("#response" + id).hide("slow");
	// $("#response" + id).html("");
	$("#mostrar_detalles" + id).attr("onclick", "pushDetalles(" + id + ")");
}

/**
 * Crea un html de la tabla de detalle de pedido
 * 
 * @param detalles
 * @param div
 */
function pintarTablaDetalles(detalles, div) {
	$(div).hide();
	var total = 0;
	$(div).html("");
	var tabla = "<table id=\"tabla_detalle_pedido\" class=\"table\">";
	tabla += "<tr><th>Vino</th><th>Categoría</th><th>Año</th><th>Bodega</th><th>Cantidad</th><th>Precio</th></tr>";
	$.each(detalles, function(i, item) {
		total += eval(item.vinoPrecio);
		tabla += "<tr>";
		tabla += "<td>" + item.vinoNombre + "</td>";
		tabla += "<td>" + item.vinoCategoria + "</td>";
		tabla += "<td>" + item.vinoAnio + "</td>";
		tabla += "<td>" + item.vinoBodega + "</td>";
		tabla += "<td>" + item.cantidad + "</td>";
		tabla += "<td>" + eval(item.vinoPrecio).toFixed(2) + " €</td>";
		tabla += "</tr>";

	});
	tabla += "<tr><td colspan='5'>Total:</td><td>" + eval(total).toFixed(2)
			+ " €</td></tr>";
	tabla += "</table>";
	console.log(tabla);
	$(div).html(tabla);

	$(div).show("slow");
}
