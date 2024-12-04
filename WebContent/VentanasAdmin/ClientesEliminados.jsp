<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Clientes Eliminados</title>

    <!-- Enlaces a los estilos CSS comunes -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    
    <!-- Estilo específico para esta página -->
    <style>
        form input[type="submit"], .volver-menu input[type="button"] {
            width: auto; 
            margin: 10px;
            padding: 10px 20px;
        }
        .filter-container form {
            display: flex; 
            justify-content: center; 
            align-items: center; 
        }

        .filter-container input[type="text"] {
            width: 200px; 
            padding: 8px; 
            margin-right: 10px; 
        }

        .filter-container input[type="submit"] {
            padding: 8px 15px; 
            font-size: 14px; 
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">Lista de Clientes Eliminados</h2>

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

<!-- Filtro de búsqueda -->
<div class="filter-container">
    <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientesEliminados">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI, nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar" class="button button-blue">
    </form>
</div>

<% 
    // Datos para paginación
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>
    <div class="table-container">
<table>
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>CUIL</th>
        <th>Correo</th>
        <th>Teléfono</th>       
        <th>Activar</th>
    </tr>
    
    <% 
    List<Cliente> listaClientes = (List<Cliente>)request.getAttribute("listaClientes");
    if (listaClientes != null) {
        for (Cliente cliente : listaClientes) {
    %>
    
    <tr>
        <td><%= cliente.getDni() %></td>
        <td><%= cliente.getNombre() %></td>
        <td><%= cliente.getApellido() %></td>
        <td><%= cliente.getCuil() %></td>
        <td><%= cliente.getCorreo() %></td>
        <td><%= cliente.getTelefono() %></td>
        <td>    
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientesEliminados">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="btnActivar" value="Activar" class="button button-green">
            </form>
       </td>
    </tr>
    <% } } else { %>
    <tr>
        <td colspan="9">No se encontraron clientes. Presione filtrar para recargar</td>
    </tr>
    <% } %>
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

    <a href="/TPINT_GRUPO_10_LAB4/ServletListadoClientes" class="volver-menu">
        <input type="button" value="Ir al listado de Clientes" class="button button-grey"> <!-- Aplicamos el estilo de botón -->
    </a>

<!-- Enlace para volver al menu -->
<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
    <input type="button" value="Volver al Menu" class="button button-blue">
</a>

</body>
</html>
