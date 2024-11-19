<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas del cliente</title>

<style>    
</style>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloPaginacion.css">

</head>
<body>

<h2 style="text-align: center;">Cuentas de clientes deshabilitadas</h2>

<% 
		    String mensaje = (String) request.getAttribute("mensaje");
		    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
		    if (mensaje != null && tipoMensaje != null) {
		%>
		    <div class="message-container <%= tipoMensaje %>">
		        <%= mensaje %>
		    </div>
		<% } %>

<!-- Leyenda de colores -->
<div class="leyenda">
    <span class="estado-rojo"></span> Deshabilitado
    <br>
</div>

<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI del titular , nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar">
    </form>
</div>


		<% // Datos para paginacion
	    int paginaActual = (int) request.getAttribute("paginaActual");
	    int totalPaginas = (int) request.getAttribute("totalPaginas");
		%>

<table>
    <tr>
        <th>Tipo de cuenta</th>
        <th>Numero de cuenta</th>
        <th>DNI Titular</th>
        <th>Nombre</th> <!-- Nueva celda para nombre -->
        <th>Apellido</th> <!-- Nueva celda para apellido -->
        <th>Movimientos</th>
        <th>Estado</th>
        <th>Deshabilitar</th>
    </tr>


  <%
        List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas"); 

  if (listaCuentas != null) {
      for (Cuenta cuenta : listaCuentas) {
    %>
    <tr>
        <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
        <td><%= cuenta.getNumeroCuenta() %></td>
        <td><%= cuenta.getUsuario().getCliente().getDni() %></td>
        <td><%= cuenta.getUsuario().getCliente().getNombre() %></td>
        <td><%= cuenta.getUsuario().getCliente().getApellido() %></td>
        <td>
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletMovimientos">
                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">               
                <input type="submit" name="btnMovimientos" value="+">
            </form>
        </td>
        <td>
            <% if (cuenta.isEstado()) { %>
                <span class="estado-verde"></span>
            <% } else { %>
                <span class="estado-rojo"></span>
            <% } %>
        </td>
        <td>
            <form onsubmit="return confirmarEliminacion()" method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">
                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                <input type="hidden" name="idCliente" value="<%= cuenta.getUsuario().getCliente().getId() %>">
                <input type="submit" name="btnHabilitar" value="Habilitar">
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>


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
<script type="text/javascript">
        function confirmarEliminacion() {
            
            var respuesta = confirm("Estas seguro de que deseas habilitar esta cuenta?");
            return respuesta;  
        }
    </script>


</html>