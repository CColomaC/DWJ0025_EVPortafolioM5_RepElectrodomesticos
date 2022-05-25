<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>Orden de Servicio</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body class="bg-light">

		<div class="my-3 p-3 bg-body rounded shadow-sm">
			<h1>Orden de Servicio Nº ${ods.id}</h1>
			<div class="container">	
				<div class="col mb-6">
					  <h3>Detalles del artefacto</h3>
					  <div class="mb-3">
					    <label class="form-label">Tipo de artefacto: </label>
					    <input type="text" class="form-control" id="tipoArtefacto" name="tipoArtefacto" disabled value="${ods.electrodomestico_id.tipo}">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Problema del artefacto</label>
					    <input type="text" class="form-control" id="problemaArtefacto" name="problemaArtefacto" disabled value="${ods.electrodomestico_id.problema}">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Estado de la orden</label>
					    <input type="text" class="form-control" id="estadoOrden" name="estadoOrden" disabled value="${ods.estado}">
					  </div>
					  
					  <h3>Detalles del cliente</h3>
					  <div class="mb-3">
					    <label class="form-label">Nombre del cliente</label>
					    <input type="text" class="form-control" id="nombreCliente" name="nombreCliente" disabled value="${ods.electrodomestico_id.cliente_id.nombre}">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Dirección del cliente</label>
					    <input type="text" class="form-control" id="direccionCliente" name="direccionCliente" disabled value="${ods.electrodomestico_id.cliente-id.direccion}">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Teléfono del cliente</label>
					    <input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" disabled value="${ods.electrodomestico_id.cliente_id.telefono}">
					  </div>
					  <a href="${pageContext.request.contextPath}/Index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
				</div>
			</div>
		</div>
	</body>
</html>
