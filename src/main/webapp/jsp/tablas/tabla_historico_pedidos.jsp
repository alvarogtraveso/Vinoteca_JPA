<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="../js/pedidos_ajax.js"></script>
<style type="text/css">		
	/* Historial de pedidos */
	#table_history{	width:60%; float:left; }
	#pedido{ margin-top: 1em; clear:left;}
	.div_detalle_pedido {float:left;}
	#tabla_detalle_pedido{ text-align: right; float:left; }
	#tabla_detalle_pedido td{ border: 1px solid #e5e5e5; background-color: white; }
	#estado_pedido{	background-color: white; float:left; }
	
</style>

<script type="text/javascript">
function cambiarVistaPedidos(){
	var estado = document.getElementsByName("select_estados_pedido")[0].value;
	console.log(estado);
	if(estado == 'Todos'){
		window.location.href = "../usuario/perfil.do";
	}
	if(estado == 'Pendiente de pago'){
		window.location.href = "../usuario/perfil.do?estado=PENDIENTE_PAGO";
	} 
	if(estado == 'Pendiente de confirmación'){
		window.location.href = "../usuario/perfil.do?estado=PENDIENTE_CONFIRMACION";
	}
	if(estado == 'Confirmados'){
		window.location.href = "../usuario/perfil.do?estado=CONFIRMADO";
	}
	if(estado == 'Rechazados'){
		window.location.href = "../usuario/perfil.do?estado=RECHAZADO";
	}
}
</script>


<div id="table_title">
	Historial de Pedidos 
	<select name="select_estados_pedido" onChange="cambiarVistaPedidos()">
		<option>Elige...</option>
		<option>Todos</option>
		<option>Pendiente de pago</option>
		<option>Pendiente de confirmación</option>
		<option>Confirmados</option>
		<option>Rechazados</option>
	</select>
</div>

<c:forEach var="pedidos" items="${pedidos}">

	<div id="pedido">
		
		<table id="estado_pedido" class="table">
			<tr>
				<th>Fecha:</th>
				<td><fmt:formatDate pattern="dd-MM-yyyy" value="${pedidos.fecha}" /></td>
			</tr>
			<tr>
				<th>Estado:</th>
				<c:choose>
					<c:when test="${pedidos.estado.estado == 'PENDIENTE_PAGO'}">
						<td>Pendiente de pago</td>
			</tr>
			<tr>
				<td colspan="2">
					<form action="../pedido/cambiar_estado.do" method="POST">
						<input name="pedidoId" type="hidden" value="${pedidos.id}" />
						<input name="pedidoEstado" type="hidden" value="${pedidos.estado}" />
						<input name="codigoBancareo" type="text" placeholder="Código bancareo" />
						<input type="submit" value="Confirmar"/>
					</form>
				</td>
			</tr>
			</c:when>
			<c:when test="${pedidos.estado.estado == 'PENDIENTE_CONFIRMACION'}">
				<td>Pendiente de confirmación</td>
				</tr>
			</c:when>
			<c:when test="${pedidos.estado.estado == 'CONFIRMADO'}">
				<td>CONFIRMADO</td>
				</tr>
			</c:when>
			<c:when test="${pedidos.estado.estado == 'RECHAZADO'}">
				<td>RECHAZADO</td>
				</tr>
				<tr>
					<th>Motivo:</th>
					<td><input type="text" disabled="true" placeholder="Sin motivo" value="${pedidos.motivoRechazo}"/></td>
				</tr>
			</c:when>
			</c:choose>
			<tr>
				<td><input id="mostrar_detalles${pedidos.id}" onclick="pushDetalles(${pedidos.id})" type="button" value="Detalles"/></td>
			</tr>
		</table>
		
		
		
		<div id="response${pedidos.id}" name="div_detalle_pedido"></div>		
	</div>

	<div class="divisor" name="${pedidos.estado.estado}" ></div>
</c:forEach>



