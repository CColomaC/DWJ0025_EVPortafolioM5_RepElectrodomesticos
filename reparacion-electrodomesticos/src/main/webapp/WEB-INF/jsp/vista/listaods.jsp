<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>

		<meta charset="UTF-8">
		<title>Lista</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	</head>
	<body class="bg-light">

	<div class="my-3 p-3 bg-body rounded shadow-sm">
		<h1>Ordenes de servicio</h1>	
		<table id="sortTable" class="table table-striped">
			<thead>
				<tr>
					<th class="col">ID</th>
					<th class="col">Objeto</th>
					<th class="col">Estado</th>
					<th class="col">Cliente asociado</th>
					<th class="col">Fecha Solicitud</th>
					<th class="col">Fecha Ultima Actualización</th>
					<th class="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="odt" items="${ods}">
					<tr>
						<td><c:out value="${ods.id}"></c:out></td>
						<td><c:out value="${ods.electrodomestico_id.nombre}"></c:out></td>
						<td><c:out value="${ods.estado}"></c:out></td>
						<td><c:out value="${ods.electrodomestico_id.cliente_id.nombre}"></c:out></td>
						<td><c:out value="${ods.fechaSolicitud}"></c:out></td>
						<td><c:out value="${ods.fechaActualizacionOrden}"></c:out></td>
						<td>
							<a href="Controller?accion=editar&amp;id=${ods.id}">Modificar</a>
							<a href="Controller?accion=verODS&amp;id=${ods.id}">Ver ODS</a>
						</td>
					</tr>			
				</c:forEach>
			</tbody>
		</table>
		<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
	</div>		

	</body>
</html>