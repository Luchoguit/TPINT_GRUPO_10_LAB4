<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Lista de Clientes</title>
    <style>	        
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloPaginacion.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
    
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
		


<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientes">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI, nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar">
    </form>
</div>

	
		<% // Datos para paginacion
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
        
        <!-- Boton para ver mas detalles -->
        <td>
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletsInfoCompletaCliente">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="verMas" value=" + ">
            </form>
        </td>

        <!-- Boton para modificar -->
        <td>
            <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletModificarCliente">
                <input type="hidden" name="idcliente" value="<%= cliente.getId() %>">
                <input type="submit" name="btnModificar" value="Modificar">
            </form>
        </td>

        <!-- Boton para eliminar -->
        <td>
            <form id="formEliminar_<%= cliente.getDni() %>" onsubmit="return confirmarEliminacion()" method="post" action="/TPINT_GRUPO_10_LAB4/ServletEliminarCliente">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="btnEliminar" value="Eliminar">
            </form>
        </td>
    </tr>
    <% } } else { %>
    <tr>
        <td colspan="9">No se encontraron clientes. Presione filtrar para recargar</td>
    </tr>
    <% } %>
    
</table>

<!-- Controles de paginaci�n -->
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
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>

    <!-- JavaScript para la confirmacion de eliminacion -->
    <script type="text/javascript">
        function confirmarEliminacion() {
            
            var respuesta = confirm("Estas seguro de que deseas eliminar este cliente?");
            return respuesta;  
        }
    </script>

</body>
</html>