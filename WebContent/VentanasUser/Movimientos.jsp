<%@page import="entidad.Movimiento" %>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="utilidades.Formato" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos de Cuenta Bancaria</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .table-container {
        width: 80%;
        max-width: 800px;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .table-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
    }
    .table-header div {
        margin: 5px 0;
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f4f4f4;
        font-weight: bold;
    }
    .table-title {
        text-align: center;
        font-size: 24px;
        margin-bottom: 20px;
    }  
   .back-button {
        padding: 15px 40px; 
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 24px; 
        cursor: pointer;
    }
    .back-button:hover {
        background-color: #218838;
    }
    
    
    
</style>

        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloPaginacion.css">

</head>
<body>



<% 
    // Datos para paginación
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
        <!-- Mostrar el mensaje con el estilo adecuado -->
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>
    
    <%
        Cuenta cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
        Cliente cliente = null;
        
        if(request.getSession().getAttribute("clienteSeleccionado") != null)
        {
        	cliente = (Cliente) request.getSession().getAttribute("clienteSeleccionado");
        }
        else
        {
        	cliente = (Cliente) request.getSession().getAttribute("cliente");
        }
        
        java.time.LocalDateTime fechaHoy = java.time.LocalDateTime.now(); 
    %>
    
    <div class="table-header">
        <div><strong>Fecha:</strong> <%= Formato.formatoFecha(fechaHoy) %></div>
        <div><strong>Titular:</strong> <%= cliente.getNombre() + " " + cliente.getApellido() %></div>
        <div><strong>Número de Cuenta:</strong> <%= cuentaSeleccionada.getNumeroCuenta() %></div>
    </div>
	
	<% if (request.getAttribute("movimientos") != null) {
		List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("listaMovimientos");
		java.time.LocalDateTime fechaCreacion = cuentaSeleccionada.getFechaCreacion();
		if (movimientos.size() > 0) { %>
		    <table>
		        <thead>
		            <tr>
		                <th>Fecha</th>
		                <th>Concepto</th>
		                <th>Importe</th>
		                <th>Saldo</th>
		            </tr>
		        </thead>
				<tbody>
					
		<%
			List<BigDecimal> saldosParciales = (List<BigDecimal>) request.getAttribute("listaSaldosParciales");
			int iteracion = 0;
			int tipo_movimiento_alta_prestamo = 2;
			
			int tipo_movimiento_alta_cuenta = 1;
			boolean depositoInicial = false;
			
			for (Movimiento movimiento : movimientos) {
				boolean salida = false;
				
				if(movimiento.getTipoMovimiento().getId() == tipo_movimiento_alta_cuenta)
				{
					depositoInicial = true;
				}
				else
				{
					if (!(movimiento.getCuentaDestino().getId() == cuentaSeleccionada.getId() || 
					      movimiento.getTipoMovimiento().getId() == tipo_movimiento_alta_prestamo)) {
						salida = true;
					}
				}
				BigDecimal saldoIteracion = saldosParciales.get(iteracion);
		%>
		            <tr>
		                <td><%= Formato.formatoFechaHora(movimiento.getFechaHora()) %></td>
		                <td><%= movimiento.getDetalle() %></td>
		                <td>
		                    <% if (depositoInicial) { %>
		                        <%= Formato.formatoMonetario(movimiento.getImporte()) %>
		                    <% depositoInicial = false;
		                    } else if(salida) { %>
		                        <%= Formato.formatoMonetario(movimiento.getImporte().negate()) %>
		                    <% }else{ %>
		                    	<%= Formato.formatoMonetario(movimiento.getImporte()) %>
		                    <%} %>
		                </td>
		                <td><%= Formato.formatoMonetario(saldoIteracion) %></td>
		            </tr>

		<%
			iteracion++;
			} 
			%>
				</tbody>
    		</table>
    <% 
		} else { %>
			<h3>Aquí aparecerán sus movimientos cuando utilice la cuenta</h3>
		<% }
	} %>
	
	
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
	
	
	
    <div class="button-container">
        <input type="button" value="Volver" class="back-button" onclick="window.history.back()">
    </div>
</div>

</body>
</html>
