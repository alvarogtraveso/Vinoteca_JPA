<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="../js/jquery.validate.js"></script>
<script type="text/javascript" src="../js/carritoJs.js"></script>
<style type="text/css">
	.container{	width: 90%; margin-left: auto; margin-right: auto; }
	#table_login{ margin-top: 6em; margin-rigth: 1em; float:left; }
	#table_register{ margin-top: 6em; margin-rigth: 1em; margin-left: 4em; float:left; }
	#table_title{ margin-left: 4em; }
</style>
<script type="text/javascript">
	$(document).ready(function() {
		var usuario = {
			nombre: "Alvaro",
			email : "el@email"
		};
		//hacemos focus
		//$("#email").focus();
		//comprobamos si se pulsa una tecla
		$("#email").keyup(function(e) {
			//obtenemos el texto introducido en el campo
			var email = $("#email").val();

			//hace la búsqueda
			$("#resultado").delay(0).queue(function(n) {

				$("#resultado").html('<img src="../img/ajax-loader.gif" />');

				$.ajax({
					type : "POST",
					url : "../usuario/validar_registro.do",
					contentType: "text/html; charset=utf-8",
					dataType : "html",
					data : email,
					error : function() {
						alert("error petición ajax");
					},
					success : function(data) {
						$("#resultado").html(data);
						n();
					}
				});

			});

		});

	});
</script>
</head>
<body>
	<c:import url="/home/cabecera.do" />

	<div class="container">

		<div id="table_login">
			<div id="table_title">Identifícate</div>
			<form id="form_login" action="../usuario/login.do" method="POST">
				<jsp:include page="../jsp/tablas/table_login.jsp"></jsp:include>
			</form>
		</div>

		<div id="table_register">
			<div id="table_title">¿Eres nuevo?</div>
			<form id="form_registro" action="../usuario/registrar_usuario.do"
				method="POST">
				<jsp:include page="../jsp/tablas/table_register.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>