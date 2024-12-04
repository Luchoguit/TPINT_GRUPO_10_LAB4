<%@page import="entidad.SolicitudAltaCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Administrar solicitudes cuentas</title>

    <!-- Estilos comunes -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">

    <style>
    </style>

</head>
<body>

<h2>Administrar solicitud alta cuentas</h2>

   <!-- Contenedor de mensajes -->
    <% 
    String mensaje = (String) request.getAttribute("mensaje");
    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
    if (mensaje != null && tipoMensaje != null) {
    %>
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>

<% 
    // Datos para paginación
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>
    <div class="table-container">

<table>
    <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>DNI</th>
            <th>Tipo de cuenta</th>
            <th>Aceptar</th>
            <th>Rechazar</th>
        </tr>
    </thead>
    <tbody>
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
                    <button type="submit" name="accion" value="aceptar" class="button button-green">Aceptar</button>
                </form>
            </td>
            <td>
                <form action="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas" method="post">
                    <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
                    <button type="submit" name="accion" value="rechazar" class="button button-red">Rechazar</button>
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
</div>

<!-- Enlace para volver al menú -->
<div class="volver-menu">
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="button button-blue">
    </a>
</div>

</body>
</html>
