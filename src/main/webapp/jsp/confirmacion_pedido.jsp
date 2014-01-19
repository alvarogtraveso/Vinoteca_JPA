<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link rel="stylesheet" type="text/css" href="../css/datos_compra.css">
<link rel="stylesheet" type="text/css" href="../css/carrito.css">
<script type="text/javascript" src="../js/carritoJs.js"></script>
<style type="text/css">
	/* General */
	.container { width: 100%; margin-top: 5em; float: left; }
	.boton_seguir {	padding-left: 4em; padding-top: 1em; float: left; }	
	#table_confirm { margin-top:1em; margin-bottom:2em; padding-left: 4em; float: left; }	
</style>
<script type="text/javascript">
	function disableInputs(){
		var inputs=document.getElementsByTagName('input');
		for(i=0;i<inputs.length;i++){
		    inputs[i].disabled=true;
		} 
		document.getElementById("boton_logout").disabled=false;
		document.getElementById("boton_confirmar").disabled=false;
		document.getElementById("boton_busqueda").disabled=false;
		document.getElementById("barra_busqueda").disabled=false;
		document.getElementById("barra_busqueda").disabled=false;
		
		var bModificar =document.getElementsByName('boton_modificar');
		var bEliminar =document.getElementsByName('boton_eliminar');
		for(i=0;i<bModificar.length;i++){
			bModificar[i].style.display = 'none';
			bEliminar[i].style.display = 'none';
		} 
	}
</script>
</head>
<body onLoad="disableInputs()">
	<c:import url="/home/cabecera.do"/>
	
<div class="container">
	<jsp:include page="cabecera_pedido.jsp"></jsp:include>
	<div class="carrito_info">	
		<div id="table_title">Carrito</div>	
		<jsp:include page="../jsp/tablas/tabla_estado_carrito.jsp"/>
	</div>
	
	<div id="table_title">Datos de entrega </div>	
	<jsp:include page="../jsp/tablas/tabla_datos_usuario.jsp"/>
	
	<div id="table_confirm">
		<form action="../pedido/confirmar_pedido.do" method="POST">
			<input id="boton_confirmar" type="submit" value="Confirmar compra"/>
		</form>	
	</div>
		
</div>
</body>
</html>