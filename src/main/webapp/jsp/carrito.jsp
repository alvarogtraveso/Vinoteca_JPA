<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/carritoJs.js"></script>
<style type="text/css">
	/* General */
	.container { width: 100%; margin-top: 5em; float: left; }
	.boton_seguir {	padding-left: 4em; padding-top: 1em; float: left; }		
</style>
</head>
<body>
	<c:import url="/home/cabecera.do"/>

	<div class="container">
		<jsp:include page="cabecera_pedido.jsp"></jsp:include>

		<form action="../pedido/iniciar_pedido.do" method="POST">
			<div class="carrito_info">	
				<div id="table_title">Carrito</div>	
				<c:import url="../jsp/tablas/tabla_estado_carrito.jsp"/>
			</div>
			<div class="boton_seguir"><input type="submit" value="Continuar"/></div>
		</form>

	</div>
</body>
</html>