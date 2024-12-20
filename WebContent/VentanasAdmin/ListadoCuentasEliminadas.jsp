<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cuentas del cliente</title>

    <!-- Estilos comunes -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">

    <style>
        /* Estado de cuenta (verde o rojo) */
        .estado-verde {
            width: 20px;
            height: 20px;
            background-color: green;
            border-radius: 50%;
            display: inline-block;
        }

        .estado-rojo {
            width: 20px;
            height: 20px;
            background-color: red;
            border-radius: 50%;
            display: inline-block;
        }

        .estado-verde, .estado-rojo {
            margin: 0 auto;
        }

        /* Leyenda de estado de cuentas */
        .leyenda {
            position: absolute;
            top: 20px;
            right: 20px;
            border: 1px solid #ddd;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }

        .leyenda span {
            margin-right: 10px;
            vertical-align: middle;
        }

        /* Filtro y bot�n */
        .filter-container input[type="text"] {
            width: 200px; 
            padding: 8px; 
            margin-right: 10px; 
        }

        .filter-container input[type="submit"] {
            padding: 8px 15px; 
            font-size: 14px; 
        }

        /* Ajustes generales de los formularios */
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
    </style>

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

<!-- Filtro de b�squeda -->
<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI del titular , nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar" class="button button-blue">
    </form>
</div>

<% 
    // Datos para paginaci�n
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>
    <div class="table-container">

<table>
    <tr>
        <th>Tipo de cuenta</th>
        <th>Numero de cuenta</th>
        <th>DNI Titular</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Movimientos</th>
        <th>Estado</th>
        <th>Habilitar</th>
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
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletMovimientosCuenta">
                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                <input type="submit" name="btnMovimientos" value="+" class="button button-green btn-movimiento">
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
                <input type="submit" name="btnHabilitar" value="Habilitar" class="button button-blue">
            </form>
        </td>
    </tr>
<% 
        }
    }
%>
</table>

<%
    String filtroCliente = request.getParameter("filtroCliente");
    String filtroParam = (filtroCliente != null && !filtroCliente.isEmpty()) ? "&filtroCliente=" + filtroCliente : "";
%>
<div class="pagination">
    <% if (paginaActual > 1) { %>
        <a href="?page=<%= paginaActual - 1 %><%= filtroParam %>" class="pagination-link">&laquo; Anterior</a>
    <% }
    for (int i = 1; i <= totalPaginas; i++) { %>
        <% if (i == paginaActual) { %>
            <span class="pagination-current"><%= i %></span>
        <% } else { %>
            <a href="?page=<%= i %><%= filtroParam %>" class="pagination-link"><%= i %></a>
        <% } 
    } 
    if (paginaActual < totalPaginas) { %>
        <a href="?page=<%= paginaActual + 1 %><%= filtroParam %>" class="pagination-link">Siguiente &raquo;</a>
    <% } %>
</div>

</div>

    <a href="/TPINT_GRUPO_10_LAB4/ServletListadoCuentas" class="volver-menu">
        <input type="button" value="Ir al listado de Cuentas" class="button button-grey"> <!-- Aplicamos el estilo de bot�n -->
    </a>

<!-- Enlace para volver al men� -->
<div class="volver-menu">
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="button button-blue">
    </a>
</div>

<script type="text/javascript">
    function confirmarEliminacion() {
        var respuesta = confirm("�Est�s seguro de que deseas habilitar esta cuenta?");
        return respuesta;  
    }
</script>

</body>
</html>
