<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Formulario electrodoméstico</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body class="bg-light">

		<div class="my-3 p-3 bg-body rounded shadow-sm">
			<h1>Nueva orden</h1>
			<div class="container">
			
				<h2>Datos de electrodoméstico</h2>
				<div class="col mb-6">
					<form method="POST" action="Controller?accion=addElectrodomestico">
					
					  <input type=hidden class="form-control" id="id" name="id" value="${cliente.id}">
					  		
					  <div class="mb-3">
					    <label class="form-label">Nombre Cliente</label>
					    <input type="text" class="form-control" id="nombre" name="nombre" disabled value="${cliente.nombre}">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Tipo de artefacto</label>
					    <input type="text" class="form-control" id="tipoArtefacto" name="tipoArtefacto">
					  </div>
					  <div class="mb-3">
					    <label class="form-label">Problema del artefacto</label>
					    <input type="text" class="form-control" id="problemaArtefacto" name="problemaArtefacto">
					  </div>
					  <button type="submit" class="btn btn-primary">Agregar artefacto</button><br>
					 </form>
					  <form method="POST" action="Controller?accion=finalizar">
					  <input type="hidden" class="form-control" id="id" name="id" value="${cliente.id}">
					  <button type="submit" class="btn btn-danger">Finalizar</button>
					  <div class="form-text">Antes de finalizar debe agregar un artefacto.</div>
					  <div class="form-text">No agregar al menos un artefacto cancelará la orden.</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>