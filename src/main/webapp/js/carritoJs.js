/**
 * Suma la cantidad al vino almacenado en cookies. En el caso de que no exista
 * se crea la cookie.
 * 
 * @param id
 */
function addCookieCarrito(form, id) {
	var cantidad = eval(form.cantidad.value);
	if (cantidad > 0) {
		var cantidadAnterior = eval(getCookieCarrito(id));
		if (cantidadAnterior != null)
			cantidad += cantidadAnterior;

		setCookieCarrito(id, cantidad);

		actualizarVista(id);
	}

}

/**
 * Modifica la cantidad al vino almacenado en cookies.
 * 
 * @param id
 */
function modifyCookieCarrito(id) {
	var cantidad = eval(document.getElementById("cantidad" + id).value);
	if (cantidad > 0) {
		setCookieCarrito(id, cantidad);

		document.location.reload();		
	}

	if (eval(document.getElementById("cantidad" + id).value == 0)) {
		deleteCookieCarrito(id);		
	}

}

/**
 * Crea o modifica(si existe) la cantidad de un vino almacenado en cookies.
 * 
 * @param id
 * @param value
 */
function setCookieCarrito(id, value) {
	var exdate = new Date();
	exdate.setDate(exdate.getDate() + 1); // se a�ade un d�a al d�a actual
	document.cookie = "vino_" + id + "=" + value + "; expires="
			+ exdate.toUTCString() + "; path=/";
}

/**
 * Obtiene una cookie del carrito. Devuelve la cantidad del vino.
 * 
 * @param id
 * @returns
 */
function getCookieCarrito(id) {
	var parts = document.cookie.split("vino_" + id + "=");
	if (parts.length == 2)
		return parts.pop().split(";").shift();
}

/**
 * Borra una cookie del carrito.
 * 
 * @param id
 */
function deleteCookieCarrito(id) {
	document.cookie = "vino_" + id
			+ '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;' + ";path=/";
	document.location.reload();
}

/**
 * Devuelve la cantidad total de vinos en el carrito.
 * 
 * @returns {Number}
 */
function numVinosEnCarrito() {
	var total = 0;
	var pairs = document.cookie.split(";");
	for ( var i = 0; i < pairs.length; i++) {
		var pair = pairs[i].split("=");

		if (pair[0].match("vino") != null) {
			total += eval(pair[1]);
		}

	}

	return total;
}

function actualizarVista(id){
	document.getElementById("carrito_icon").innerHTML = 'Carrito [ '+ numVinosEnCarrito() +' ]';
	document.getElementById("cantidad"+id).value = '';
	
	//document.location.reload();
}

function getCookies() {
	var pairs = document.cookie.split(";");
	var cookies = {};
	for ( var i = 0; i < pairs.length; i++) {
		var pair = pairs[i].split("=");
		cookies[pair[0]] = unescape(pair[1]);
	}
	return cookies;
}
