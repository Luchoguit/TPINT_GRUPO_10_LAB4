<%@page import="entidad.Movimiento" %>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.time.LocalDate" %>


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

<div class="table-container">
    <h1 class="table-title">Extracto de Cuenta</h1>
    <%
    	Cuenta cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
    	Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
    	 LocalDate fechaHoy = LocalDate.now(); 
    %>
    
    
    <div class="table-header">
        <div><strong>Fecha: <%=fechaHoy %> </strong> </div>
        <div><strong>Titular:</strong> <%=cliente.getNombre() + " " + cliente.getApellido() %> </div>
        <div><strong>Número de Cuenta:</strong> <%=cuentaSeleccionada.getNumeroCuenta() %></div>
    </div>
	
	<%if(request.getAttribute("movimientos") != null)
		{
			List<Movimiento> movimientos = (List<Movimiento>)request.getAttribute("movimientos");
			
			
			LocalDate fechaCreacion = cuentaSeleccionada.getFechaCreacion().toLocalDate();
		
			
			if(movimientos.size() > 0)
			{
				%>
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
							<tr>
				<% 
				
				
				List<BigDecimal> saldosParciales = (List<BigDecimal>) request.getAttribute("saldos");
				
				
				int iteracion = 0;
				for(Movimiento movimiento : movimientos)
				{
					boolean salida = false;
					if(! (movimiento.getCuentaDestino().getId()== cuentaSeleccionada.getId()))
					{
						salida = true;
					}
					
					
					BigDecimal saldoIteracion = saldosParciales.get(iteracion);
		

					
				%>
		
				
				            <tr>
				                <td>
				                <%
				                	LocalDate fecha = movimiento.getFechaHora().toLocalDate();
				                
				                %>
				                
				                <%=fecha %>
				                </td>
				                <td><%=movimiento.getDetalle() %></td>
				                <td> 
				                	<%if(salida == true)
				                	{ %>
				                		<%=movimiento.getImporte().negate() %>
				                	<%
				                	}
				                	else
				                	{%>
				                		<%=movimiento.getImporte()%>
				                	<%}
				                	%>
				                	 
				                </td>
				                <td><%=saldoIteracion %></td>
				            </tr>
			<%
			
				iteracion++;
				} %>
							<tr>
				                <td> <%= fechaCreacion %></td>
				                <td>Saldo Inicial</td>
				                <td></td>
				                <td>10000.00 </td>
				            </tr>
			
			
        				</tbody>
    			</table>
    
    <%
			}
	
		}
		else
		{
		%>
			<h3>Aquí aparecerán sus movimientos cuando utilice la cuenta</h3>
		<%} %>
		
    <div class="button-container">
        <input type="button" value="Volver" class="back-button" onclick="window.history.back()">
    </div>
</div>

</body>
</html>