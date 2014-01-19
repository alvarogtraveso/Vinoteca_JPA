<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vinote.Ca</title>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<style type="text/css"> 
	#tabla_p{
		float:left;
	}
</style>
<script type="text/javascript" src="../js/carritoJs.js"></script>
</head>
<body>
	<jsp:include page="cabeceras/header_admin.jsp"></jsp:include>
	
	<div id="tabla_p">
		<c:import url="/admin/listar_pedidos.do"/>
	</div>
</body>
</html>