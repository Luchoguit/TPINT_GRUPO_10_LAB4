<%@page import="entidad.SolicitudAltaCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar solicitudes cuentas</title>

<style>    
</style>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloPaginacion.css">

</head>
<body>

<h2 style="text-align: center;">Administrar solicitud alta cuentas</h2>

<%
    String mensaje = (String) request.getAttribute("mensaje");
    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
    if (mensaje != null && tipoMensaje != null) {
%>
    <div class="message-container <%= tipoMensaje %>">
        <%= mensaje %>
    </div>
<% } %>

		<% // Datos para paginacion
	    int paginaActual = (int) request.getAttribute("paginaActual");
	    int totalPaginas = (int) request.getAttribute("totalPaginas");
		%>

<table>
	    <thead>
	        <tr>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>DNI</th>
	            <th>Tipo de cuenta</th>
	            <th></th>
	            <th></th>
	        </tr>
	    </thead>
	    <tbody>
	 
	 <%-- Verifica si hay un mensaje de error en el request --%>
<% String error = (String) request.getAttribute("error"); %>



	 
	    <% 
    List<SolicitudAltaCuenta> listaSolicitudes = (List<SolicitudAltaCuenta>)request.getAttribute("listaSolicitudes");
    if (listaSolicitudes != null) {
        for (SolicitudAltaCuenta solicitud : listaSolicitudes) {
    %>
    
	        <tr>
	            <td><%= solicitud.getCliente().getNombre() %></td>
	            <td><%= solicitud.getCliente().getApellido() %></td>
	            <td><%= solicitud.getCliente().getDni() %></td>
	            <td><%= solicitud.getTipoCuenta().getDescripcion() %></td>
	            <td>
            <form action="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas" method="post">
                <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
                <button type="submit" name="accion" value="aceptar" id="aceptar">Aceptar</button>
            </form>
        </td>
        <td>
            <form action="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas" method="post">
                <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
                <button type="submit" name="accion" value="rechazar" id="rechazar">Rechazar</button>
            </form>
        </td>
                </tr>
	        <% } } %>
	    </tbody>
	</table>
	
	<!-- Controles de paginación -->
<div class="pagination">
    <% if (paginaActual > 1) { %>
        <a href="?page=<%= paginaActual - 1 %>" class="pagination-link">&laquo; Anterior</a>
    <% }
     for (int i = 1; i <= totalPaginas; i++) { %>
        <% if (i == paginaActual) { %>
            <span class="pagination-current"><%= i %></span>
        <% } else { %>
            <a href="?page=<%= i %>" class="pagination-link"><%= i %></a>
        <% } 
     } %>
    <% if (paginaActual < totalPaginas) { %>
        <a href="?page=<%= paginaActual + 1 %>" class="pagination-link">Siguiente &raquo;</a>
    <% } %>
</div>


	<div class="volver-menu">
     <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
			<input type="button" value="Volver al Menu" class="btn-volver">
		</a>
     </div>

</body>

</html>