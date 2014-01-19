<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../js/carritoJs.js"></script>
<style type="text/css">

	/* General */
	.container{	width: 100%; float:left; }
	.info{	width: 90%;	float:none; }
	.history{ padding-top:2em; with: 90%; float: none; }
	#table_title{ margin-left: 4em; margin-top: 4em; padding-top: 2em;	clear: left; }
	
	/* Informacion de la cuenta del usuario*/	
	#table_profile{	float:left; }	
	#botones_perfil{ padding-left: 4em;	padding-top: 1em; clear:left; }	
	
</style>
</head>
<body>
	<c:import url="/home/cabecera.do"/>
	
<div class="container">
	<div class="info">
		<div id="table_title">Información de cuenta</div>
			
		<div id="table_profile">
			<table class="table">
				<tr>
					<th>Nombre</th>
					<td>${user.nombre}</td>
				</tr>
					<th>Email</th>
					<td>${user.email}</td>
				</tr>
			</table>
			<div id="botones_perfil">
				<form action="../usuario/vista_modificar_perfil.do">
					<input type="submit" value="Modificar"/>
				</form>
			</div>				
		</div>
	</div>
	<div class="history">	
		<jsp:include page="../jsp/tablas/tabla_historico_pedidos.jsp"/>
	</div>
</div>
</body>
</html>