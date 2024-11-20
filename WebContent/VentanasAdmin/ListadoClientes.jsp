<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lista de Clientes</title>
    
    <!-- Estilos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
   
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

<h2 style="text-align: center;">Lista de Clientes</h2>

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
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientes">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI, nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar" class="button button-blue">
    </form>
</div>

<% 
    // Datos para paginación
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>

<table>
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>CUIL</th>
        <th>Correo</th>
        <th>Telefono</th>
        <th>Ver Datos</th>
        <th>Modificar</th>
        <th>Eliminar</th>
    </tr>
    
    <% 
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
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
        
        <!-- Botón para ver más detalles -->
        <td>
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletsInfoCompletaCliente">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="verMas" value=" + " class="button button-green">
            </form>
        </td>

        <!-- Botón para modificar -->
        <td>
            <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletModificarCliente">
                <input type="hidden" name="idcliente" value="<%= cliente.getId() %>">
                <input type="submit" name="btnModificar" value="Modificar" class="button button-yellow"> 
            </form>
        </td>

        <!-- Botón para eliminar -->
        <td>
            <form id="formEliminar_<%= cliente.getDni() %>" onsubmit="return confirmarEliminacion()" method="post" action="/TPINT_GRUPO_10_LAB4/ServletEliminarCliente">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="btnEliminar" value="Eliminar" class="button button-red">
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

<!-- Enlace para volver al menu -->
<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
    <input type="button" value="Volver al Menu" class="button button-blue">
</a>

<!-- JavaScript para la confirmación de eliminación -->
<script type="text/javascript">
    function confirmarEliminacion() {
        var respuesta = confirm("¿Estás seguro de que deseas eliminar este cliente?");
        return respuesta;  
    }
</script>

</body>
</html>
