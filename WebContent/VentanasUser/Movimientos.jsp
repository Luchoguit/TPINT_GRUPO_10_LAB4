<%@page import="entidad.Movimiento" %>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="utilidades.Formato" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Movimientos de Cuenta Bancaria</title>

    <!-- Hojas de estilo -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    
    <style>
.filter-container {
    display: flex;  /* Usa flexbox para alinear los elementos */
    justify-content: space-between;  /* Se distribuyen uniformemente a lo largo del espacio */
    align-items: center;  /* Alinea los elementos verticalmente */
    flex-wrap: wrap;  /* Permite que los elementos se ajusten si no hay suficiente espacio */
    margin: 20px 0;
}
    </style>
    
</head>
<body>

<% 
    int paginaActual = (int) request.getAttribute("paginaActual");
    int totalPaginas = (int) request.getAttribute("totalPaginas");
%>

<div class="table-container">
    <h1 class="table-title">Extracto de Cuenta</h1>

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
        Cuenta cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteSeleccionado") != null ?
                          (Cliente) request.getSession().getAttribute("clienteSeleccionado") :
                          (Cliente) request.getSession().getAttribute("cliente");
        java.time.LocalDateTime fechaHoy = java.time.LocalDateTime.now();
    %>

    <div class="table-header">
        <div><strong>Fecha:</strong> <%= Formato.formatoFecha(fechaHoy) %></div>
        <div><strong>Titular:</strong> <%= cliente.getNombre() + " " + cliente.getApellido() %></div>
        <div><strong>Número de Cuenta:</strong> <%= cuentaSeleccionada.getNumeroCuenta() %></div>
    </div>

    <!-- Formulario de filtros -->
    <form method="get" action="ServletMovimientosCuenta" class="filter-container">
	
        <label for="fechaDesde">Fecha Desde:</label>
        <input type="date" name="fechaDesde" id="fechaDesde">

        <label for="fechaHasta">Hasta:</label>
        <input type="date" name="fechaHasta" id="fechaHasta">

        <label for="montoMin">Monto Mínimo:</label>
        <input type="number" name="montoMin" onkeypress="soloNumeros(event)" step="0.01" id="montoMin">

        <label for="montoMax">Máximo:</label>
        <input type="number" name="montoMax" onkeypress="soloNumeros(event)" step="0.01" id="montoMax">
	
        <label for="tipoMovimiento">Tipo de Movimiento:</label>
        <select name="tipoMovimiento" id="tipoMovimiento">
            <option value="">Todos</option>
            <option value="1">Alta de Cuenta</option>
            <option value="2">Alta de Préstamo</option>
            <option value="3">Pago de Préstamo</option>
            <option value="4">Transferencia</option>
        </select>
        
        <button type="submit" class="button-blue">Filtrar</button>
    </form>

    <% 
    List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
    Boolean esFiltro = (Boolean) request.getAttribute("esFiltro");
    if (movimientos != null && !movimientos.isEmpty()) { 
    %>
        <table>
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Concepto</th>
                    <th>Importe</th>
                    <% if (!esFiltro){ %>
                    <th>Saldo</th>
                    <%} %>
                </tr>
            </thead>
            <tbody>
                <% 
                List<BigDecimal> saldosParciales = (List<BigDecimal>) request.getAttribute("listaSaldosParciales");
                int iteracion = 0;
                for (Movimiento movimiento : movimientos) { 
                %>
                <tr>
                    <td><%= Formato.formatoFechaHora(movimiento.getFechaHora()) %></td>
                    <td><%= movimiento.getDetalle().replace(System.lineSeparator(), "<br>") %></td>
                    <td><%= Formato.formatoMonetario(movimiento.getImporte()) %></td>
                    <% if (!esFiltro){ %>
                    <td><%= Formato.formatoMonetario(saldosParciales.get(iteracion)) %></td>
                    <%} %>                   
                </tr>
                <% iteracion++; } %>
            </tbody>
        </table>
    <% 
    } else { 
    %>
        <h3 class="message-container warning">No hay movimientos para mostrar</h3>
    <% } %>

	<div class="pagination">
	    <% 
	        String tipoMovimiento = request.getParameter("tipoMovimiento");
	        String fechaDesde = request.getParameter("fechaDesde");
	        String fechaHasta = request.getParameter("fechaHasta");
	        String montoMin = request.getParameter("montoMin");
	        String montoMax = request.getParameter("montoMax");
	
	        String queryString = "&tipoMovimiento=" + (tipoMovimiento != null ? tipoMovimiento : "") +
	                             "&fechaDesde=" + (fechaDesde != null ? fechaDesde : "") +
	                             "&fechaHasta=" + (fechaHasta != null ? fechaHasta : "") +
	                             "&montoMin=" + (montoMin != null ? montoMin : "") +
	                             "&montoMax=" + (montoMax != null ? montoMax : "");
	    %>
	
	    <% if (paginaActual > 1) { %>
	        <a href="?page=<%= paginaActual - 1 %><%= queryString %>" class="pagination-link">&laquo; Anterior</a>
	    <% }
	     for (int i = 1; i <= totalPaginas; i++) { %>
	        <% if (i == paginaActual) { %>
	            <span class="pagination-current"><%= i %></span>
	        <% } else { %>
	            <a href="?page=<%= i %><%= queryString %>" class="pagination-link"><%= i %></a>
	        <% } 
	     } %>
	    <% if (paginaActual < totalPaginas) { %>
	        <a href="?page=<%= paginaActual + 1 %><%= queryString %>" class="pagination-link">Siguiente &raquo;</a>
	    <% } %>
	</div>

    <div class="volver-menu">
        <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp" class="btn-volver">Volver a cuenta</a>
    </div>
</div>


<script>

    
    
    function soloNumeros(event) {
        var key = event.keyCode || event.which;
        var tecla = String.fromCharCode(key);
        var regex = /^[0-9]$/;
        if (!regex.test(tecla)) {
            event.preventDefault();
        }
    }
    
</script>



</body>
</html>
