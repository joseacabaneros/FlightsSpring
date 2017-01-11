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
		function imprimirCalendario(){
			w=window.open();
			w.document.write(document.getElementById('calendario').innerHTML);
			w.print();
			w.close();
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="page-header">
	 		<h1>FLIGHTS</h1>
	 		<h2><spring:message code="tag_selecciona_vuelos"/></h2>
	 	</div>
	 	<ol class="breadcrumb">
	 		<li><strong><spring:message code="listado_proceso_compra_uno"/></strong></li>
		  	<li><spring:message code="listado_proceso_compra_dos"/></li>
		  	<li><spring:message code="listado_proceso_compra_tres"/></li>
		</ol>
		<div class="row">
	  		<div class="col-xs-12 col-md-6">
	  			<h3><spring:message code="tag_busqueda"/></h3>
		  		<dl class="dl-horizontal">
				  	<dt><spring:message code="label_aeropuerto_origen"/></dt>
				  	<dd><c:out value="${vueloSearch.origen}"/></dd>
				  	<dt><spring:message code="label_aeropuerto_destino"/></dt>
				  	<dd><c:out value="${vueloSearch.destino}"/></dd>
				  	<dt><spring:message code="label_fecha_salida"/></dt>
				  	<dd><c:out value="${vueloSearch.salida}"/></dd>
				  	<c:if test="${vueloSearch.getRegresoDate() != null}">
					  	<dt><spring:message code="label_fecha_regreso"/></dt>
					  	<dd><c:out value="${vueloSearch.regreso}"/></dd>
					</c:if>
				  	<dt><spring:message code="label_numero_plazas"/></dt>
				  	<dd><c:out value="${vueloSearch.plazas}"/></dd>
				</dl>
	  		</div>
	  		<div class="col-xs-12 col-md-4 col-md-offset-2">
	  			<c:if test="${vuelosSalida.size() != 0}">
					<h3><c:out value="${vueloSearch.destino}"/></h3>
					<img src="<c:url value="/resources/${vuelosSalida[0].imagenUrl}"/>" 
						alt="<spring:message code="listado_imagen"/>" class="img-responsive img-thumbnail"/>
				</c:if>
	  		</div>
  		</div>
		<hr>
		<form:form commandName="reserva" class="form-horizontal">
			<div id="calendario">
				<h3><spring:message code="listado_vuelos_salida"/></h3>
				<c:if test="${vuelosSalida.size() == 0}">
					<h4><spring:message code="listado_no_existen_vuelos"/></h4>
					<p>
						<c:set var="vueloSalidaIdErrors"><form:errors path="vueloSalidaId"/></c:set>
					    <c:if test="${not empty vueloSalidaIdErrors}">
					    	<span class="error text-danger">${vueloSalidaIdErrors}</span>
					    </c:if>
					</p>
				</c:if>
				<c:if test="${vuelosSalida.size() != 0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th><spring:message code="listado_table_origen"/></th>
								<th><spring:message code="listado_table_destino"/></th>
								<th><spring:message code="listado_table_salida"/></th>
								<th><spring:message code="listado_table_duracion"/></th>
								<th><spring:message code="listado_table_plazas"/></th>
								<th>
									<spring:message code="listado_table_precio"/><br/>
									<c:set var="vueloSalidaIdErrors"><form:errors path="vueloSalidaId"/></c:set>
								    <c:if test="${not empty vueloSalidaIdErrors}">
								    	<span class="error text-danger">${vueloSalidaIdErrors}</span>
								    </c:if>
								</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${vuelosSalida}" var="vuelo">
							<tr>
								<td><c:out value="${vuelo.origen}"></c:out></td>
								<td><c:out value="${vuelo.destino}"></c:out></td>
								<td><c:out value="${vuelo.getFechaSalidaDate()}"></c:out></td>
								<td><c:out value="${vuelo.duracion}"></c:out></td>
								<td><c:out value="${vuelo.plazas}"></c:out></td>
							 	<th><form:radiobutton path="vueloSalidaId" name="salida" 
							 		value="${vuelo.id}"/>
							 		<c:out value="${vuelo.precio}"></c:out> EUR
						 		</th>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${vueloSearch.getRegresoDate() != null}">
					<h3><spring:message code="listado_vuelos_regreso"/></h3>
					<c:if test="${vuelosRegreso.size() == 0}">
						<h4><spring:message code="listado_no_existen_vuelos"/></h4>
					</c:if>
					<c:if test="${vuelosRegreso.size() != 0}">
						<table class="table table-striped">
							<thead>
								<tr>
									<th><spring:message code="listado_table_origen"/></th>
									<th><spring:message code="listado_table_destino"/></th>
									<th><spring:message code="listado_table_salida"/></th>
									<th><spring:message code="listado_table_duracion"/></th>
									<th><spring:message code="listado_table_plazas"/></th>
									<th><spring:message code="listado_table_precio"/></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${vuelosRegreso}" var="vuelo">
								<tr>
									<td><c:out value="${vuelo.origen}"></c:out></td>
									<td><c:out value="${vuelo.destino}"></c:out></td>
									<td><c:out value="${vuelo.getFechaSalidaDate()}"></c:out></td>
									<td><c:out value="${vuelo.duracion}"></c:out></td>
									<td><c:out value="${vuelo.plazas}"></c:out></td>
									<th><form:radiobutton path="vueloRegresoId" name="regreso" 
								 		value="${vuelo.id}"/>
								 		<c:out value="${vuelo.precio}"></c:out> EUR<br>
							 		</th>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</c:if>
				</c:if>
			</div>
			<a href="javascript:imprimirCalendario()"><spring:message code="tag_imprimir_calendario"/></a>
			<hr>
			<div class="row">
	  			<div class="col-xs-12 col-md-6 border-right">
		  			<h2><spring:message code="listado_equipaje"/></h2>
	  				<div class="form-group">
					  	<form:label path="equipajeNormal" for="equipajeNormal" class="col-sm-6 control-label">
					  		<spring:message code="label_maleta_normal"/>
					  	</form:label>
			  			<div class="col-sm-6">
			  				<form:input path="equipajeNormal" type="number" class="form-control" id="equipajeNormal"/>
			  				<c:set var="equipajeNormalErrors"><form:errors path="equipajeNormal"/></c:set>
						    <c:if test="${not empty equipajeNormalErrors}">
						    	<span class="error text-danger">${equipajeNormalErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="equipajeGrande" for="equipajeGrande" class="col-sm-6 control-label">
					  		<spring:message code="label_maleta_grande"/>
					  	</form:label>
			  			<div class="col-sm-6">
			  				<form:input path="equipajeGrande" type="number" class="form-control" id="equipajeGrande"/>
			  				<c:set var="equipajeGrandeErrors"><form:errors path="equipajeGrande"/></c:set>
						    <c:if test="${not empty equipajeGrandeErrors}">
						    	<span class="error text-danger">${equipajeGrandeErrors}</span>
						    </c:if>
					  	</div>
					</div>
				</div>
				<div class="col-xs-12 col-md-6">
		  			<h2><spring:message code="listado_vehiculo"/></h2>
	  				<div class="form-group">
					  	<form:label path="cocheUtilitario" for="cocheUtilitario" class="col-sm-6 control-label">
					  		<spring:message code="label_utilitario"/>
					  	</form:label>
			  			<div class="col-sm-6">
			  				<form:input path="cocheUtilitario" type="number" class="form-control" id="cocheUtilitario"/>
			  				<c:set var="cocheUtilitarioErrors"><form:errors path="cocheUtilitario"/></c:set>
						    <c:if test="${not empty cocheUtilitarioErrors}">
						    	<span class="error text-danger">${cocheUtilitarioErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="cocheFurgoneta" for="cocheFurgoneta" class="col-sm-6 control-label">
					  		<spring:message code="label_furgoneta"/>
					  	</form:label>
			  			<div class="col-sm-6">
			  				<form:input path="cocheFurgoneta" type="number" class="form-control" id="cocheFurgoneta"/>
			  				<c:set var="cocheFurgonetaErrors"><form:errors path="cocheFurgoneta"/></c:set>
						    <c:if test="${not empty cocheFurgonetaErrors}">
						    	<span class="error text-danger">${cocheFurgonetaErrors}</span>
						    </c:if>
					  	</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-md-6">
			    	<a href="<c:url value="/searchVuelos"/>" class="btn btn-primary btn-lg">
			    		&lsaquo; <spring:message code="enlace_atras"/></a>
				</div>
				<div class="col-xs-12 col-md-6">
					<div class="form-group pull-right">
				    	<input type="submit"value="<spring:message code="boton_siguiente"/> &rsaquo;" class="btn btn-primary btn-lg"/>
					</div>
				</div>
			</div>
		</form:form>
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

