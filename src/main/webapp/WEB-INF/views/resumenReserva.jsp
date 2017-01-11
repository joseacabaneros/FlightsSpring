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
	<script type="text/javascript">
	function imprimirResumen(){
		w=window.open();
		w.document.write(document.getElementById('resumen').innerHTML);
		w.print();
		w.close();
	}
	</script>
</head>
<body>
	<div class="container">
		<div class="page-header">
	 		<h1>FLIGHTS</h1>
	 		<h2><spring:message code="tag_resumen"/></h2>
	 	</div>
	 	<ol class="breadcrumb">
	 		<li><spring:message code="listado_proceso_compra_uno"/></li>
		  	<li><spring:message code="listado_proceso_compra_dos"/></li>
		  	<li><strong><spring:message code="listado_proceso_compra_tres"/></strong></li>
		</ol>
		<div id="resumen">
			<h3 class="bg-info"><spring:message code="resumen_codigo"/> 
				<strong><c:out value="${reserva.codigoReserva}"></c:out></strong></h3>
			<hr>
			<table class="table table-striped">
				<thead>
					<tr>
						<th><spring:message code="reserva_table_tipo"/></th>
						<th><spring:message code="listado_table_origen"/></th>
						<th><spring:message code="listado_table_destino"/></th>
						<th><spring:message code="listado_table_salida"/></th>
						<th><spring:message code="listado_table_duracion"/></th>
						<th><spring:message code="listado_table_plazas"/></th>
						<th><spring:message code="listado_table_precio"/></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><spring:message code="reserva_table_ida"/></th>
						<td><c:out value="${vueloSalida.origen}"></c:out></td>
						<td><c:out value="${vueloSalida.destino}"></c:out></td>
						<td><c:out value="${vueloSalida.getFechaSalidaDate()}"></c:out></td>
						<td><c:out value="${vueloSalida.duracion}"></c:out></td>
						<td><c:out value="${reserva.plazas}"></c:out></td>
					 	<th><c:out value="${vueloSalida.precio}"></c:out> EUR</th>
					</tr>
					<c:if test="${vueloRegreso != null}">
					<tr>
						<th><spring:message code="reserva_table_vuelta"/></th>
						<td><c:out value="${vueloRegreso.origen}"></c:out></td>
						<td><c:out value="${vueloRegreso.destino}"></c:out></td>
						<td><c:out value="${vueloRegreso.getFechaSalidaDate()}"></c:out></td>
						<td><c:out value="${vueloRegreso.duracion}"></c:out></td>
						<td><c:out value="${reserva.plazas}"></c:out></td>
					 	<th><c:out value="${vueloRegreso.precio}"></c:out> EUR</th>
					</tr>
					</c:if>
				</tbody>
			</table>
			<div class="row">	
				<div class="col-xs-12 col-md-6 border-right">
					<dl>
						<dt><spring:message code="listado_equipaje"/></dt>
					  	<dd>
					  		<ul>
								<li><spring:message code="label_maleta_normal"/>: 
									<strong><c:out value="${reserva.equipajeNormal}"/></strong> 
								</li>
								<li><spring:message code="label_maleta_grande"/>: 
									<strong><c:out value="${reserva.equipajeGrande}"/></strong> 
								</li>
							</ul>
					  	</dd>
				  	</dl>
				</div>
				<div class="col-xs-12 col-md-6">
					<dl>
						<dt><spring:message code="listado_vehiculo"/></dt>
					  	<dd>
					  		<ul>
								<li><spring:message code="label_utilitario"/>: 
									<strong><c:out value="${reserva.cocheUtilitario}"/></strong> 
								</li>
								<li><spring:message code="label_furgoneta"/>: 
									<strong><c:out value="${reserva.cocheFurgoneta}"/></strong> 
								</li>
							</ul>
					  	</dd>
				  	</dl>
				</div>
			</div>
			<h3><spring:message code="reserva_total_pagar"/> 
				<strong><c:out value="${reserva.precioTotal}"/> EUR</strong></h3>
			<hr>
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
		</div>
		<div class="row">	
			<div class="col-xs-12 col-md-6">
				<a href="javascript:imprimirResumen()"><spring:message code="resumen_imprimir_reserva"/></a><br/>
				<a href="mailto:${usuario.email}?subject=${reserva.codigoReserva}&body=
					<spring:message code="resumen_text_correo_uno"/> ${usuario.nombre} <spring:message 
					code="resumen_text_correo_dos"/>${reserva.codigoReserva}<spring:message 
					code="resumen_text_correo_tres"/>"><spring:message code="resumen_correo"/></a><br/>
			</div>
			<div class="col-xs-12 col-md-6">
				<div class="pull-right">
					<a href="<c:url value="/searchVuelos"/>" class="btn btn-primary"> 
						<spring:message code="enlace_volver_inicio"/></a>
				</div>
			</div>
		</div>
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

