<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/table.css">

<div class="container">
	<div class="table_header">
		<h1>${titulo}</h1>
	</div>
	<div class="table_offer" style="display: table">
		<c:set var="count" value="${1}" />
		<c:set var="flag" value="true" />
		<c:forEach var="v" items="${vinos}">			
			<c:if test="${ flag eq true }">
				<div class="row" style="display: table-row">
					<c:set var="flag" value="false" />
			</c:if>

			<div class="cell" style="display: table-cell">
				<div class="imagen">
					<a href="#openModal${titulo}${v.id}"><img src="${v.imagen}"/></a>
				</div>
				<!-- Esta es la información completa del vino, oculta hasta que el usuario haga click en la imagen de un producto -->
				<div id="openModal${titulo}${v.id}" class="modalDialog">
					
					<%@include file="tabla_detalle_vino.jsp" %>				
				</div>
				<!--  -->
				<div class="wine_info">
					<div id="wine_price">
						${v.precio}€
					</div>
					<div id="wine_name">${v.nombre}</div>
					<div id="wine_categ">${v.categoria.categoria}</div>
					
					<div id="wine_buying">
						<form id="form" onsubmit="addCookieCarrito(this,${v.id})" action="#">
							<input id="cantidad" type="number" min="0" max="99" /> 
							<input	class="add_cart" type="submit" value="+"/>
						</form>
					</div>
				</div>
			</div>

			<c:if test="${count%5==0}">
	</div>
	<c:set var="flag" value="true" />
	</c:if>
	<c:set var="count" value="${count + 1}" />

	</c:forEach>

</div>
</div>