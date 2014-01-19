<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/carritoJs.js"></script>
<style type="text/css">
	/* General */
	.container{	width: 100%; float:left; }
	#table_title{ margin-left: 4em; margin-top: 4em; padding-top: 2em;	clear: left; }
	
	/* Informacion de la cuenta del usuario*/	
	#table_profile{	float:left; }	
	#botones_perfil{ padding-left: 4em;	padding-top: 1em; clear:left; }	
</style>
</head>
<body>
	<c:import url="/home/cabecera.do"/>
	
<div class="container">
	<div id="table_title">Información de cuenta</div> 
		<form action="../usuario/modificar_usuario.do">	
		<div id="table_profile">
			<table class="table">
				<tr>
					<th>Nombre</th>
					<td><input name="nombre" type="text" value="${user.nombre}"/></td>
				</tr>
					<th>Email</th>
					<td><input name="email" type="text" value="${user.email}"/></td>
				</tr>
				<tr>
					<th>Contraseña antigua</th>
					<td><input name="password_antigua" type="password" /></td>
				</tr>
				<tr>
					<th>Contraseña</th>
					<td><input name="password" type="password" /></td>
				</tr>
				</tr>
					<th>Repita la nueva contraseña</th>
					<td><input name="password_confirm" type="password"/></td>
				</tr>
			</table>
			<div id="botones_perfil">
				<input name="id" type="hidden" value="${user.id}"/>				
				<input type="submit" value="Confirmar"/>				
			</div>				
		</div>
		</form>
	
</div>
</body>
</html>