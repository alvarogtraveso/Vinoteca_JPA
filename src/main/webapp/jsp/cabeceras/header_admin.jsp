<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style type="text/css">
	/* General */
	.header{ position: fixed; width:100%; }
	#cssdropdown, #cssdropdown ul { list-style: none; }
	#cssdropdown, #cssdropdown * { padding: 0; margin: 0; }
	
	/* Search bar */
	div#search { padding-top:9px; padding-right:10px; position:relative; float: right; }
	input[type="text"].barra_busqueda { width: 500px; }
	
	/* Tabla Identificate */
	#tabla_identificate .table{ border: none; padding: 1em;}
	#tabla_identificate td{ padding: 3px; }
	#tabla_identificate input[type='submit'] { padding: 4px; }
	
	/* Head links */
	#cssdropdown li.headlink { float: left; margin-left: -1px; border: 1px solid #e5e5e5; background-color: #f1f1f1; text-align: center; }
	#cssdropdown li.headlink a { color: #535353;;  display: block; padding: 15px; }

	/* Child lists and links */
	#cssdropdown li.headlink ul { display: none; border-top: 1px #8B8B8B solid; text-align: left; }
	#cssdropdown li.headlink:hover ul { display: block; }
	#cssdropdown li.headlink ul li a:hover { background-color: #8B8B8B; }
	
	/* Pretty styling */
	#logo { width:19px ; height: 19px; }
	#cssdropdown li.headlink { background-color: #f1f1f1;}
	#cssdropdown li.headlink ul { background-position: bottom; }
</style>
<script language="JavaScript">	
	$(document).ready(function(){
		$('#cssdropdown li.headlink').hover(
			function() { $('ul', this).css('display', 'block'); },
			function() { $('ul', this).css('display', 'none'); });
	});
</script> 

<div class="header">
	<div id="navigation">
		<ul id="cssdropdown">
			<li class="headlink"><a href="../home/home.do"><img id="logo" src="../img/menu.png"></a></li>
			<c:if test="${not empty user}">
				<li class="headlink"><a href="../usuario/perfil.do">Hola ${user.nombre}</a>
					<ul>
						<form action="../usuario/logout.do" method="POST">
						<li id="tabla_identificate"><input id="boton_logout" type="submit" value="Cerrar sesión" /></li>
						</form>										
					</ul>		
				</li>
			</c:if> 
			<c:if test="${empty user}">
				<li class="headlink"><a href="../usuario/registro.do">Identifícate</a>
					<ul>
						<li id="tabla_identificate">
						<form id="form_login" action="../usuario/login.do" method="POST">
							<jsp:include page="../tablas/table_login.jsp"></jsp:include>
						</form>
						</li>
						<li id="regist"><a href="../usuario/registro.do">Regístrate</a></li>				
					</ul>				
				</li>
			</c:if>
			<li class="headlink"><a href="../admin/pedidos.do">Pedidos</a></li>
			<li class="headlink"><a href="../admin/vinos.do">Vinos</a></li>
		</ul>
	</div>
	
	
	<div id="search">
		<form action="/Vinoteca/busqueda/busqueda_vinos.do" method="POST">
			<input id="barra_busqueda" class="barra_busqueda" name="filtro_busqueda" type="text" value="" /> 
			<input id="boton_busqueda" class="boton_busqueda" type="submit" value="Buscar" />
		</form>
	</div>
</div>
				

