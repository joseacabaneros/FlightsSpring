<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Fights</title>
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Yellowtail">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
	<script>
		function cancelReserva(identUsuario, codReserva){
			bootbox.confirm({
				title: "<spring:message code='title_cancelar'/> '" + codReserva + "'?",
			    message: "<spring:message code='mensage_cancelar'/>",
			    buttons: {
			        cancel: {
			            label: "<i class='glyphicon glyphicon-remove'></i> <spring:message code='enlace_atras'/>"
			        },
			        confirm: {
			            label: "<i class='glyphicon glyphicon-ok'></i> <spring:message code='tag_confirmar'/>"
			        }
			    },
			    callback: function (result) {
			        if(result === true){
			        	window.location.href = "<c:url value='/listadoReservas/cancelar/" + identUsuario + "/" + codReserva + "'/>";
			        }
			    }
			});	
        };
  	</script>
</head>
<body>
	<div class="container">
		<div class="page-header">
	 		<h1>FLIGHTS</h1>
	 		<h2><spring:message code="listado_reservas_tag_reservas"/> '${usuario.identificacion}'</h2>
	 	</div>
	 	<h4><spring:message code="resumen_tag_usuario"/></h4>
		<dl class="dl-horizontal">
			<dt><spring:message code="label_documento_identidad"/></dt>
		  	<dd><c:out value="${usuario.identificacion}"/></dd>
		  	<dt><spring:message code="reserva_tag_nombre"/> </dt>
		  	<dd><c:out value="${usuario.nombre}"/></dd>
		  	<dt><spring:message code="reserva_tag_apellidos"/> </dt>
		  	<dd><c:out value="${usuario.apellidos}"/></dd>
		  	<dt><spring:message code="reserva_tag_correo"/></dt>
		  	<dd><c:out value="${usuario.email}"/></dd>
	  	</dl>
	  	<hr>
	  	<!-- Alerta de nofiticaciones -->
		<c:if test="${message != null}">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
				<strong><spring:message code="tag_exito"/></strong>
	  			<span><c:out value="${message}"/></span>
			</div>
		</c:if>
		<c:if test="${vuelos != null}">
			<div class="panel panel-info">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><spring:message code="listado_reservas_tag_detalles"/> 
		    			<strong>'<c:out value="${codigoReserva}"/>'</strong></h3>
		  		</div>
		  		<div class="panel-body">
		    		<table class="table table-striped">
		    			<thead>
							<tr>
								<th><spring:message code="listado_table_origen"/></th>
								<th><spring:message code="listado_table_destino"/></th>
								<th><spring:message code="listado_table_salida"/></th>
								<th><spring:message code="listado_table_duracion"/></th>
								<th><spring:message code="listado_table_precio"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${vuelos}" var="vuelo">
								<tr>
									<td><c:out value="${vuelo.origen}"></c:out></td>
									<td><c:out value="${vuelo.destino}"></c:out></td>
									<td><c:out value="${vuelo.getFechaSalidaDate()}"></c:out></td>
									<td><c:out value="${vuelo.duracion}"></c:out></td>
								 	<th><c:out value="${vuelo.precio}"></c:out> <spring:message code="listado_reservas_tag_eur"/></th>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h3><spring:message code="listado_imagen"/></h3>
					<img src="<c:url value="/resources/${vuelos[0].imagenUrl}"/>" 
						alt="<spring:message code="listado_imagen"/>" class="img-responsive img-thumbnail"/>
		 		 </div>
			</div>
		</c:if>
		<table class="table table-striped" id="tableListado">
			<thead>
				<tr>
					<th><spring:message code="listado_reservas_tag_codigo"/></th>
					<th><spring:message code="listado_reservas_tag_salida"/></th>
					<th><spring:message code="listado_reservas_tag_regreso"/></th>
					<th><spring:message code="listado_reservas_tag_normal"/></th>
					<th><spring:message code="listado_reservas_tag_grande"/></th>
					<th><spring:message code="listado_reservas_tag_utilitario"/></th>
					<th><spring:message code="listado_reservas_tag_furgoneta"/></th>
					<th><spring:message code="listado_reservas_tag_plazas"/></th>
					<th><spring:message code="listado_reservas_tag_precio"/></th>
					<th><spring:message code="listado_reservas_tag_cancelar"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reservas}" var="reserva">
					<tr>
						<td>
							<a href="<c:url value="/listadoReservas/${usuario.identificacion}/${reserva.codigoReserva}"/>">
								<c:out value="${reserva.codigoReserva}"></c:out>
							</a>
						</td>
						<td><c:out value="${reserva.vueloSalidaId}"></c:out></td>
						<c:if test="${reserva.vueloRegresoId != 0}">
							<td><c:out value="${reserva.vueloRegresoId}"></c:out></td>
						</c:if>
						<c:if test="${reserva.vueloRegresoId == 0}">
							<td><spring:message code="listado_reservas_sin_regreso"/></td>
						</c:if>
						<td><c:out value="${reserva.equipajeNormal}"></c:out></td>
						<td><c:out value="${reserva.equipajeGrande}"></c:out></td>
						<td><c:out value="${reserva.cocheUtilitario}"></c:out></td>
						<td><c:out value="${reserva.cocheFurgoneta}"></c:out></td>
						<td><c:out value="${reserva.plazas}"></c:out></td>
						<th><c:out value="${reserva.precioTotal}"></c:out> EUR</th>
						<td class="centro">
							<a href="#" onclick="cancelReserva('${usuario.identificacion}','${reserva.codigoReserva}');">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="<c:url value="/searchVuelos"/>" class="btn btn-primary">
   			&lsaquo; <spring:message code="enlace_atras"/></a>
 	</div>
	<footer class="footer">
		<div class="container">
			<p class="text-muted lead"><spring:message code="tag_numero_visitas"/> <c:out value="${visitas}"></c:out></p>
			<p class="text-muted">Jose Antonio Cabañeros Blanco - 71474017E - UO234549<p>
			<p class="text-muted">Master en Ingeniería Web - Arquitectura y Diseño de Sitios Web</p>
		</div>
	</footer>
</body>
</html>

