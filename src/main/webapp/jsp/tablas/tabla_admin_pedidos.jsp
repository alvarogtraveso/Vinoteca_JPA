<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#table_title {
	color: #666;
	margin-left: 4em;
	margin-top: 6em;
	float: none;
}

#table_history {
	width: 90%;
	float: left;
}

#tabla_usuario {
	float: left;
}

#tabla_detalle_pedido {
	text-align: right;
	float: left;
}

#estado_pedido {
	text-align: right;
	background-color: white;
	float: left;
}

.cell {
	padding-right: 1em;
	padding-left: 1em;
	padding-bottom: 1em;
}

#boton_rechazar {
	border: 1px solid #b0281a;
	background-color: #d14836;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#dd4b39),
		to(#d14836) );
}

#boton_rechazar:hover {
	background-image: -webkit-linear-gradient(top, #dd4b39, #c53727);
	background-color: #c53727;
}

#containerPedidos{
	 width:100%;
	float:left;
}
#pedido {
	width: 50%;
	margin-top: 1em;
	float: left;
}
</style>

<div id="table_title">Historial de Pedidos</div>

<div id="containerPedidos">
<c:forEach var="pedidos" items="${pedidos}">

	<div id="pedido">

		<table id="tabla_usuario" class="table">
			<tr>
				<th>Usuario:</th>
				<td>${pedidos.usuario.email}</td>
			</tr>
			<tr>
				<th>Fecha:</th>
				<td><fmt:formatDate pattern="dd-MM-yyyy"
						value="${pedidos.fecha}" /></td>
			</tr>

		</table>


		<table id="estado_pedido" class="table">
			<tr>
				
				<c:choose>
					<c:when test="${pedidos.estado.estado == 'PENDIENTE_PAGO'}">
						<th>Pendiente de pago</th>
					</c:when>
					<c:when test="${pedidos.estado.estado == 'PENDIENTE_CONFIRMACION'}">
						<th>Pendiente de confirmación </th>
							<form action="../pedido/cambiar_estado.do">
								<input name="pedidoId" type="hidden" value="${pedidos.id}" />
								<input name="pedidoEstado" type="hidden" value="${pedidos.estado}" />
						<td>	<input type="submit" value="Confirmar" /></td>
							</form>
										
					</c:when>
					<c:when test="${pedidos.estado.estado == 'CONFIRMADO'}">
						<th>CONFIRMADO</th>
					</c:when>
					<c:when test="${pedidos.estado.estado == 'RECHAZADO'}">
						<th>RECHAZADO</th>
					</c:when>
				</c:choose>
			</tr>			
			<c:choose>
				<c:when test="${pedidos.estado.estado == 'CONFIRMADO'}">
				</c:when>
				<c:when test="${pedidos.estado.estado == 'RECHAZADO'}">
					<tr>
						<td colspan="3"><input name="motivoRechazo" disabled="true" type="text" placeholder="Sin motivo" value="${pedidos.motivoRechazo}" /></td>
					<tr>
				</c:when>
				<c:otherwise>
				<tr>	
					<td colspan="3">
						<form action="../pedido/cambiar_estado.do" method="POST">
							<input name="pedidoId" type="hidden" value="${pedidos.id}" /> 						
							<input name="motivoRechazo" type="text" placeholder="Motivos del rechazo" />
							<input id="boton_rechazar" type="submit" value="Rechazar" />
						</form>
					</td>				
				</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</div>



	
</c:forEach>
</div>


