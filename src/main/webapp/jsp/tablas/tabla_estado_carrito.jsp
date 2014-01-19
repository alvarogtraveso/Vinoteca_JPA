<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style type="text/css">
	#precio_row{ text-align: right; }
</style>

<table class="table">
	<tr>
		<th>Vino</th>
		<th>Bodega</th>
		<th>Precio</th>
		<th>Cantidad</th>
		<th>Precio Total</th>
	</tr>
	<c:set var="precio_total" value="0" />
	<c:forEach var="carrito" items="${carrito}">
		<tr>
			<td>${carrito.key.nombre}</td>
			<td>${carrito.key.bodega.nombre}</td>
			<td>${carrito.key.precio} €</td>
			<td><input id="cantidad${carrito.key.id}" type="number" min="0"
				max="99" value="${carrito.value}" /></td>
			<td id="precio_row"><fmt:formatNumber type="number"
					pattern="##.##" value="${carrito.value * carrito.key.precio}" /> €
			</td>
			<td><input type="button" name="boton_modificar"
				value="Modificar" onclick="modifyCookieCarrito(${carrito.key.id})" /></td>
			<td><input type="button" name="boton_eliminar" value="Eliminar"
				onclick="deleteCookieCarrito(${carrito.key.id})" /></td>
		</tr>
		<c:set var="precio_total"
			value="${precio_total + (carrito.value * carrito.key.precio)}" />
	</c:forEach>
	<tr id="precio_total">
		<td colspan="4">Total:</td>
		<td><fmt:formatNumber type="number" pattern="##.##"
				value="${precio_total}" /> €</td>

	</tr>
</table>

