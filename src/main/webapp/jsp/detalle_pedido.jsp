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
	#table_title_red { color: red; margin-top:1em; margin-left: 4em; float: none; }
	
	/* Tablas del login o registro */
	#table_login { margin-left: 1em; float: left; }
	#table_register { margin-left: 4em; float: left; }
	
	/* Tabla de detalles de entrega */
	.boton_seguir {	padding-left: 4em;	padding-top: 1em; float: left; }
	
</style>
</head>
<body>
	<c:import url="/home/cabecera.do"/>

	<div class="container">
		<jsp:include page="../jsp/cabecera_pedido.jsp"></jsp:include>

		<c:choose>
			<c:when test="${empty user}">
				<div id="table_title_red">Para seguir has de estar logeado</div>
				<div id="table_login">
					<div id="table_title">Identifícate</div>
					<form action="../usuario/login.do" method="POST">
						<jsp:include page="../jsp/tablas/table_login.jsp"/>
					</form>
				</div>

				<div id="table_register">
				<div id="table_title">¿Eres Nuevo?</div>
					<form action="../usuario/registrar_usuario.do" method="POST">
						<jsp:include page="../jsp/tablas/table_register.jsp"></jsp:include>
					</form>
				</div>
			</c:when>

			<c:otherwise>
				<form action="../pedido/vista_confirmar_pedido.do" method="POST">
					<div class="datos_info">	
						<div id="table_title">Datos de entrega </div>	
						<jsp:include page="../jsp/tablas/tabla_datos_usuario.jsp"></jsp:include>
						<div class="boton_seguir">
							<input type="submit" value="Continuar" />
						</div>
					</div>
				</form>
			</c:otherwise>
		</c:choose>





	</div>
</body>
</html>