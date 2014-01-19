<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ca.vinote.util.Categoria"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<style type="text/css"> 
#table_title{
	color: #666;
	margin-left: 4em;
	margin-top:6em;
	float:left;	
}
#tabla_v{
	padding-bottom: 6em;
	clear:left;
}
#precio, #anio{
	width: 4em;
}	
	
</style>
<script type="text/javascript" src="../js/carritoJs.js"></script>
</head>
<body>
	<jsp:include page="cabeceras/header_admin.jsp"></jsp:include>
	
	<div id="table_title">
	Vinos en el almacén
	</div>
	
	
	<div id="tabla_v">
		<table class="table">
			<tr>
				<th>Nombre</th>
				<th>Categoría</th>
				<th>Bodega</th>
				<th>Añada</th>
				<th>Descripción</th>
				<th>Imagen</th>
				<th>Precio</th>
			</tr>
			<tr>
				<form action="../admin/agregar_vino.do" method="POST">
				<td><input name="nombre" type="text" required/></td>
				<td>
					<select name="categoria">
						<option value="<%=Categoria.TINTO %>"><%=Categoria.TINTO %></option>
						<option value="<%=Categoria.BLANCO %>"><%=Categoria.BLANCO %></option>
						<option value="<%=Categoria.ROSADO %>"><%=Categoria.ROSADO %></option>
					</select>
				</td>
				<td>
					<select name="bodega_id">
					<c:forEach var="b" items="${bodegas}">
							<option value="${b.id}">${b.nombre}</option>
					</c:forEach>
					</select>
				</td>
				<td><input id="anio" name="anio" type="text" required/></td>
				<td><input name="descripcion" type="text" required/></td>
				<td><input name="imagen" type="text" required/></td>
				<td><input id="precio" name="precio" type="text" required/></td>
				<td><input type="submit" value="Agregar"/></td>
				</form>
			</tr>
		<c:forEach var="v" items="${vinos}">
			<tr>
				<form action="../admin/modificar_vino.do" method="POST">
					<input name="id" type="hidden" value="${v.id}"/>
				<td><input name="nombre" type="text" value="${v.nombre}"/></td>
				<td>
					<select name="categoria">
						<option value="<%=Categoria.TINTO %>" <c:if test="${v.categoria eq 'TINTO'}">selected</c:if>><%=Categoria.TINTO %></option>
						<option value="<%=Categoria.BLANCO %>"<c:if test="${v.categoria eq 'BLANCO'}">selected</c:if>><%=Categoria.BLANCO %></option>
						<option value="<%=Categoria.ROSADO %>"<c:if test="${v.categoria eq 'ROSADO'}">selected</c:if>><%=Categoria.ROSADO %></option>
					</select>
				</td>
				<td>
					<select name="bodega_id">
					<c:forEach var="b" items="${bodegas}">
							<option value="${b.id}" <c:if test="${v.bodega.id eq b.id}">selected</c:if> >${b.nombre}</option>
					</c:forEach>
					</select>
				</td>
				<td><input id="anio" name="anio" type="text" value="${v.anio}"/></td>
				<td><input name="descripcion" type="text" value="${v.descripcion}"/></td>
				<td><input name="imagen" type="text" value="${v.imagen}"/></td>
				<td><input id="precio" name="precio" type="text" value="${v.precio}"/></td>
				
				<td><input type="submit" value="Modificar"/></td>				
				</form>
			</tr>
		
		</c:forEach>
		</table>
	</div>
</body>
</html>