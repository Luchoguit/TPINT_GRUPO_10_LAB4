<%@page import="entidad.Prestamo" %>
<%@page import="utilidades.Formato" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>Administrar Prestamos</title>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    
    <style>
    .container {
    width: 90%;
    padding: 20px;
    margin: 20px auto;
    background: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    overflow-x: auto;
}
    </style>
    
</head>
<body>

<h2 style="text-align: center;">Administrar Préstamos</h2>

<%
    // Datos para paginación
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>


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

    <div class="container">

<table>
    <thead>
        <tr>
            <th>Cliente</th>
            <th>DNI</th>
            <th>Numero de Cuenta</th>
            <th>Monto del prestamo</th>
            <th>Cuotas</th>
            <th>Importe mensual</th>
            <th>Importe final</th>
            <th>A pagar en</th>
            <th>Fecha de Solicitud</th>
            <th></th>
            <th></th>
        </tr>
    </thead>
    <tbody>
    
    <% 
    List<Prestamo> listaPrestamos = (List<Prestamo>)request.getAttribute("listaPrestamos");
    if (listaPrestamos != null) {
        for (Prestamo prestamo : listaPrestamos) {
    %>
        <tr>
            <td><%= prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido() %></td>
            <td><%= prestamo.getCliente().getDni() %></td>
            <td><%= prestamo.getCuenta().getNumeroCuenta() %></td>
            <td><%= Formato.formatoMonetario(prestamo.getImportePedido()) %></td>
            <td><%= prestamo.getCantidadCuotas() %></td>
            <td><%= Formato.formatoMonetario(prestamo.getImporteMensual()) %></td>
            <td><%= Formato.formatoMonetario(prestamo.getImporteFinal()) %></td>
            <td><%= prestamo.getPlazoMeses() + " meses" %></td>
            <td><%= Formato.formatoFecha(prestamo.getFechaAlta()) %></td>
            <td>
                <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletSolicitudesPrestamos">
                    <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                    <button type="submit" name="accion" value="aceptar" class="button button-green">Aceptar</button>
                </form>
            </td>
            <td>
                <form action="/TPINT_GRUPO_10_LAB4/ServletSolicitudesPrestamos" method="post">
                    <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
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

<!-- Enlace para volver al menu -->
<div class="volver-menu">
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="button button-blue">
    </a>
</div>

</body>
</html>
