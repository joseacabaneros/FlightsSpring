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
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.min.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css"/>">
	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
	<script>
		$(function() {
			$("#salida").datepicker({dateFormat: "dd/mm/yy"});
			$("#regreso").datepicker({dateFormat: "dd/mm/yy"});
		});
		$(document).on("click", ".confirmacion", function(e) {
			bootbox.confirm({
				title: "<spring:message code='title_cancelar'/> '" + $("#codigoReservaa").val() + "'?",
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
			        	var input = $("<input>")
			        				   .attr("class", "invisible")
						               .attr("type", "submit")
						               .attr("name", "cancelar");
						$('#formCancelar').append($(input));
						$(input).click();
			        }
			    }
			});
        });
  	</script>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h1><spring:message code="titulo"/></h1>
			<p><spring:message code="subtitulo_cabecera"/></p>
		</div>
	</div>
	<div class="container">	
		<div class="row">
	  		<div class="col-xs-12 col-md-8 border-right">
	  			<h2><spring:message code="search_titulo_busqueda"/></h2>
	  			<form:form commandName="vueloSearch" class="form-horizontal">
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
					  	<form:label path="origen" for="origen" class="col-sm-4 control-label">
					  		<spring:message code="label_aeropuerto_origen"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:select path="origen" items="${origenes}" class="form-control" id="origen"/>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="destino" for="destino" class="col-sm-4 control-label">
					  		<spring:message code="label_aeropuerto_destino"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:select path="destino" items="${destinos}" class="form-control" id="destino"/>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="salida" for="salida" class="col-sm-4 control-label">
					  		<spring:message code="label_fecha_salida"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:input path="salida" type="text" class="form-control" id="salida"/>
			  				<c:set var="salidaErrors"><form:errors path="salida"/></c:set>
						    <c:if test="${not empty salidaErrors}">
						    	<span class="error text-danger">${salidaErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="regreso" for="regreso" class="col-sm-4 control-label">
					  		<spring:message code="label_fecha_regreso"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:input path="regreso" type="text" class="form-control" id="regreso"/>
			  				<c:set var="regresoErrors"><form:errors path="regreso"/></c:set>
						    <c:if test="${not empty regresoErrors}">
						    	<span class="error text-danger">${regresoErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="plazas" for="plazas" class="col-sm-4 control-label">
					  		<spring:message code="label_numero_plazas"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:input path="plazas" type="number" class="form-control" id="plazas"/>
			  				<c:set var="plazasErrors"><form:errors path="plazas"/></c:set>
						    <c:if test="${not empty plazasErrors}">
						    	<span class="error text-danger">${plazasErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
				  		<div class="col-sm-offset-4 col-sm-8">
					    	<input type="submit" name="search" value="<spring:message code="boton_buscar_vuelos"/>" class="btn btn-primary btn-lg"/>
					  	</div>
					</div>
				</form:form>
	  		</div>
	  		<div class="col-xs-12 col-md-4">
	  			<h2><spring:message code="search_destinos_populares"/></h2>
				<ol>
					<c:forEach items="${populares}" var="destino">
						<li><c:out value="${destino}"></c:out></li>
					</c:forEach>
				</ol>
	  		</div>
		</div>
		<hr>
		<!-- Alerta de errores -->
		<c:if test="${message_error != null}">
			<div class="alert alert-danger" role="alert">
				<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				<strong><spring:message code="tag_error"/></strong>
	  			<span class="bg-danger"><c:out value="${message_error}"/></span>
			</div>
		</c:if>
		<!-- Alerta de notificaciones -->
		<c:if test="${message_exito != null}">
			<div class="alert alert-success" role="alert">
				<span class="glyphicon glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
				<strong><spring:message code="tag_exito"/></strong>
	  			<span><c:out value="${message_exito}"/></span>
			</div>
		</c:if>
		<div class="row">
	  		<div class="col-xs-12 col-md-6 border-right">
	  			<h2><spring:message code="search_cancelar_reserva"/></h2>
				<form:form commandName="reservaCancel" class="form-horizontal" id="formCancelar">
					<div class="form-group">
					  	<form:label path="codigoReserva" for="codigoReserva" class="col-sm-4 control-label">
					  		<spring:message code="label_codigo_reserva"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:input path="codigoReserva" type="text" class="form-control" id="codigoReservaa" placeholder="ex1EqLHXVk"/>
			  				<c:set var="codigoReservaErrors"><form:errors path="codigoReserva"/></c:set>
						    <c:if test="${not empty codigoReservaErrors}">
						    	<span class="error text-danger">${codigoReservaErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
					  	<form:label path="email" for="email" class="col-sm-4 control-label">
					  		<spring:message code="label_correo_electronico"/>
					  	</form:label>
			  			<div class="col-sm-8">
			  				<form:input path="email" type="email" class="form-control" id="email" placeholder="ejemplo@gmail.com"/>
			  				<c:set var="emailErrors"><form:errors path="email"/></c:set>
						    <c:if test="${not empty emailErrors}">
						    	<span class="error text-danger">${emailErrors}</span>
						    </c:if>
					  	</div>
					</div>
					<div class="form-group">
				  		<div class="col-sm-offset-4 col-sm-8">
				  			<button type="button" class="btn btn-danger confirmacion">
				  				<spring:message code="boton_cancelar_reserva"/>
				  			</button>
					  	</div>
					</div>
				</form:form>
	  		</div>
	  		<div class="col-xs-12 col-md-6">
	  			<h2><spring:message code="search_consultar_reservas"/></h2>
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
			  				<form:select path="tipoIdentificacion" class="form-control" id="origen">
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
					    	<input type="submit" name="reservas" value="<spring:message code="boton_buscar_reservas"/>" class="btn btn-info"/>
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

