<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="detalles_vino">
	<a href="#close" title="Close" class="close">X</a>
	<div id="imagen_detalle">
		<img src="${v.imagen}" />
	</div>
	<div id="tabla_detalle">
		<table class="table">
			<tr>
				<th>id</th>
				<td>${v.id}</td>
			</tr>
			<tr>
				<th>Nombre:</th>
				<td>${v.nombre}</td>
			</tr>
			<tr>
				<th>Año:</th>
				<td>${v.anio}</td>
			</tr>
			<tr>
				<th>Categoria:</th>
				<td>${v.categoria.categoria}</td>
			</tr>
			<tr>
				<th>Bodega:</th>
				<td>${v.bodega.nombre}</td>
			</tr>
			<tr>
				<th>Descripción:</th>
				<td>${v.descripcion}</td>
			</tr>
			<tr>
				<th>Precio:</th>
				<td>${v.precio} €</td>
			</tr>
		</table>
	</div>
</div>