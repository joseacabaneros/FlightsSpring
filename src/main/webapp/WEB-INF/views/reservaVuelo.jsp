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
</head>
<body>
	<div class="container">
		<div class="page-header">
	 		<h1>FLIGHTS</h1>
	 		<h2><spring:message code="tag_reserva"/></h2>
	 	</div>
	 	<ol class="breadcrumb">
	 		<li><spring:message code="listado_proceso_compra_uno"/></li>
		  	<li><strong><spring:message code="listado_proceso_compra_dos"/></strong></li>
		  	<li><spring:message code="listado_proceso_compra_tres"/></li>
		</ol>
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
		<!-- Alerta de errores -->
		<c:if test="${message != null}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<strong><spring:message code="tag_error"/></strong>
	  			<span class="bg-danger"><c:out value="${message}"/></span>
			</div>
		</c:if>
		<div class="row">
	  		<div class="col-xs-12 col-md-6">
	  			<h3><spring:message code="reserva_usuario_antiguo"/></h3>
				<form:form commandName="usuarioSearch" class="form-horizontal">
					<!-- Alerta de errores -->
	  				<c:set var="errores"><form:errors path=""/></c:set>
				    <c:if test="${not empty errores}">
					    <div class="alert alert-danger" role="alert">
					    	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  						<strong><spring:message code="tag_error"/></strong>
	  						<span class="bg-danger">${errores}</span>
	  					</div>
				    </c:if>
				    <div class="form-group">
					  	<form:label path="identificacion" for="identificacion" class="col-sm-5 control-label">
					  		<spring:message code="label_documento_identidad"/>
					  	</form:label>
			  			<div class="col-sm-3">
			  				<form:select path="tipoIdentificacion" class="form-control" id="tipoIdentificacion">
							   <form:option value="dni" label="DNI"/>
							   <form:option value="pasaporte" label="Pasaporte"/>
							   <form:option value="nie" label="NIE"/>
							</form:select>
					  	</div>
					  	<div class="col-sm-4">
			  				<form:input path="identificacion" type="text" class="form-control" id="identificacion" placeholder="11645678L"/>
			  				<c:set var="identificacionErrors"><form:errors path="identificacion"/></c:set>
						    <c:if test="${not empty identificacionErrors}">
						    	<span class="error text-danger">${identificacionErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
				  		<div class="col-sm-offset-8 col-sm-4">
					    	<input type="submit" name="autocompletar" value="<spring:message code="reserva_autocompletar"/>" class="btn btn-info"/>
					  	</div>
					</div>
				</form:form>
	  		</div>
	  		<div class="col-xs-12 col-md-6 border-left">
	  			<h3><spring:message code="reserva_datos_personales"/></h3>
				<form:form commandName="usuario" class="form-horizontal">
					<!-- Alerta de errores -->
	  				<c:set var="errores"><form:errors path=""/></c:set>
				    <c:if test="${not empty errores}">
					    <div class="alert alert-danger" role="alert">
					    	<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
	  						<strong><spring:message code="tag_error"/></strong>
	  						<span class="bg-danger">${errores}</span>
	  					</div>
				    </c:if>
				    <div class="form-group">
					  	<form:label path="identificacion" for="identificacion" class="col-sm-5 control-label">
					  		<spring:message code="label_documento_identidad"/>
					  	</form:label>
			  			<div class="col-sm-3">
			  				<form:select path="tipoIdentificacion" class="form-control" id="tipoIdentificacion">
							   <form:option value="dni" label="DNI"/>
							   <form:option value="pasaporte" label="Pasaporte"/>
							   <form:option value="nie" label="NIE"/>
							</form:select>
					  	</div>
					  	<div class="col-sm-4">
			  				<form:input path="identificacion" type="text" class="form-control" id="identificacion" placeholder="11645678L"/>
			  				<c:set var="identificacionErrors"><form:errors path="identificacion"/></c:set>
						    <c:if test="${not empty identificacionErrors}">
						    	<span class="error text-danger">${identificacionErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="nombre" for="nombre" class="col-sm-5 control-label">
					  		<spring:message code="reserva_tag_nombre"/>
					  	</form:label>
			  			<div class="col-sm-7">
			  				<form:input path="nombre" type="text" class="form-control" id="nombre" placeholder="Juan"/>
			  				<c:set var="nombreErrors"><form:errors path="nombre"/></c:set>
						    <c:if test="${not empty nombreErrors}">
						    	<span class="error text-danger">${nombreErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="apellidos" for="apellidos" class="col-sm-5 control-label">
					  		<spring:message code="reserva_tag_apellidos"/>
					  	</form:label>
			  			<div class="col-sm-7">
			  				<form:input path="apellidos" type="text" class="form-control" id="apellidos" placeholder="Pérez Martínez"/>
			  				<c:set var="apellidosErrors"><form:errors path="apellidos"/></c:set>
						    <c:if test="${not empty apellidosErrors}">
						    	<span class="error text-danger">${apellidosErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="email" for="email" class="col-sm-5 control-label">
					  		<spring:message code="reserva_tag_correo"/>
					  	</form:label>
			  			<div class="col-sm-7">
			  				<form:input path="email" type="email" class="form-control" id="email" placeholder="ejemplo@ejemplo.com"/>
			  				<c:set var="emailErrors"><form:errors path="email"/></c:set>
						    <c:if test="${not empty emailErrors}">
						    	<span class="error text-danger">${emailErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="row">
						<div class="col-xs-12 col-md-6">
					    	<a href="<c:url value="/searchVuelos"/>" class="btn btn-primary btn-lg">
					    		&lsaquo; <spring:message code="enlace_volver_inicio"/></a>
						</div>
						<div class="col-xs-12 col-md-6">
							<div class="form-group pull-right">
						    	<input type="submit" name="pagar" value="<spring:message code="boton_pagar"/>" class="btn btn-primary btn-lg"/>
							</div>
						</div>
					</div>
				</form:form>
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

