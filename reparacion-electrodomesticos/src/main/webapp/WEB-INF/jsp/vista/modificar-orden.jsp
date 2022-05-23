<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
<title>Formulario modificar orden</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">

	<div class="my-3 p-3 bg-body rounded shadow-sm">
		<h1>Modificar Orden de Servicio</h1>
		<div class="container">
		
			<div class="col mb-6">
				<form method="POST" action="Controller?accion=finalizarEdit">
				  <input type=hidden class="form-control" id="idOds" name="idOds" value="${ods.id}">
				  <input type=hidden class="form-control" id="idCliente" name="idCliente" value="${ods.electrodomestico_id.cliente_id.id}">
				  <input type=hidden class="form-control" id="idElectrodomestico" name="idElectrodomestico" value="${ods.electrodomestico_id.id}">
				  
				  <h3>Detalles del artefacto</h3>
				  <div class="mb-3">
				    <label class="form-label">Tipo de artefacto</label>
				    <input type="text" class="form-control" id="tipoArtefacto" name="tipoArtefacto" value="${ods.electrodomestico_id.tipo}">
				  </div>
				  <div class="mb-3">
				    <label class="form-label">Problema del artefacto</label>
				    <input type="text" class="form-control" id="problemaArtefacto" name="problemaArtefacto" value="${ods.electrodomestico_id.problema}">
				  </div>
				  <div class="mb-3">
				    <label class="form-label">Estado de la orden</label>
				    <select class="form-select" name="estadoOrden" id="estadoOrden">
			    		<c:choose>
							<c:when test="${ods.estado eq 'Pendiente'}">
								<option selected="selected" value="Pendiente">Pendiente</option>
								<option value="En reparacion">En reparacion</option>
								<option value="Reparado">Reparado</option>
								<option value="Orden cerrada">Orden cerrada</option>
								<option value="Irreparable">Irreparable</option>
							</c:when>
							<c:when test="${ods.estado eq 'En reparacion'}">
								<option value="Pendiente">Pendiente</option>
								<option selected="selected" value="En reparacion">En reparación</option>
								<option value="Reparado">Reparado</option>
								<option value="Orden cerrada">Orden cerrada</option>
								<option value="Irreparable">Irreparable</option>
							</c:when>
							<c:when test="${ods.estado eq 'Reparado'}">
								<option value="Pendiente">Pendiente</option>
								<option value="En reparacion">En reparación</option>
								<option selected="selected" value="Reparado">Reparado</option>
								<option value="Orden cerrada">Orden cerrada</option>
								<option value="Irreparable">Irreparable</option>
							</c:when>
							<c:when test="${ods.estado eq 'Orden cerrada'}">
								<option value="Pendiente">Pendiente</option>
								<option value="En reparacion">En reparación</option>
								<option value="Reparado">Reparado</option>
								<option selected="selected" value="Orden cerrada">Orden cerrada</option>
								<option value="Irreparable">Irreparable</option>
							</c:when>
							<c:otherwise>
								<option value="Pendiente">Pendiente</option>
								<option value="En reparacion">En reparación</option>
								<option value="Reparado">Reparado</option>
								<option value="Orden cerrada">Orden cerrada</option>
								<option selected="selected" value="Irreparable">Irreparable</option>						
							</c:otherwise>				
						</c:choose>
				    </select>
				  </div>
				  
				  <h3>Datos del cliente</h3>
				  <div class="form-text">Los cambios en los datos del cliente aplicarán a todas las ordenes de trabajo asociadas.</div>
				  <div class="mb-3">
				    <label class="form-label">Nombre del cliente</label>
				    <input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="${ods.electrodomestico_id.cliente_id.nombre}">
				  </div>
				  <div class="mb-3">
				    <label class="form-label">Dirección del cliente</label>
				    <input type="text" class="form-control" id="direccionCliente" name="direccionCliente" value="${ods.electrodomestico_id.cliente_id.direccion}">
				  </div>
				  <div class="mb-3">
				    <label class="form-label">Teléfono del cliente</label>
				    <input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" value="${ods.electrodomestico_id.cliente_id.telefono}">
				  </div>
	
				  
				  <button type="submit" class="btn btn-success">Finalizar modificación</button> 
				  <a href="${pageContext.request.contextPath}/Index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
				</form>
	
			</div>
		</div>
	</div>
</body>
</html>
