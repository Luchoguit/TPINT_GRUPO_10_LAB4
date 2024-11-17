<%@page import="entidad.Prestamo" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdministrarPrestamos</title>
<style>
		table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
            white-space: nowrap; 
            overflow-x: auto; 
            max-width: 200px;
        }
        th {
            background-color: #f4f4f4;
        }
        
        .action-buttons {
            text-align: center;
            margin-top: 20px;
        }
        .action-buttons input[type="submit"] {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .action-buttons input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        #rechazar {
        background-color: #e74c3c;
        color: #fff;
        padding: 5px 10px;
        font-size: 9px; 
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    #rechazar:hover {
        background-color: #c0392b;
    }
    
    #aceptar {
        background-color: #2ecc71;
        color: #fff;
        padding: 5px 10px;
        font-size: 9px; 
        border-radius: 5px; 
        cursor: pointer; 
        transition: background-color 0.3s ease;
    }

    #aceptar:hover {
        background-color: #27ae60;
    }
    
    		.volver-menu {
		    display: flex;               
		    justify-content: center;     
		    align-items: center;                     
		}
		
		
		.btn-volver {
		    background-color: #007bff;   
		    color: white;                
		    padding: 10px 20px;          
		    border: none;                
		    border-radius: 5px;          
		    font-size: 16px;             
		    cursor: pointer;            
		    transition: background-color 0.3s ease;  
		}
		
		.btn-volver:hover {
		    background-color: #0056b3;   
		}
    
</style>
</head>
<body>




<h2 style="text-align: center;">Administrar Préstamos</h2>


<table>
    <thead>
        <tr>
            <th>Cliente</th>
            <th>DNI</th>
            <th>Numero de Cuenta</th>
            <th>Monto del prestamo</th>
            <th>Cuotas</th>
            <th>Importe mensual</th>
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
            <td><%= prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido()%></td>
            <td><%= prestamo.getCliente().getDni() %></td>
            <td><%= prestamo.getCuenta().getNumeroCuenta() %></td>
            <td><%= prestamo.getImportePedido() %>$</td>
            <td><%= prestamo.getCantidadCuotas() %></td>
            <td><%= prestamo.getImporteMensual() %></td>
            <td><%= prestamo.getPlazoMeses() + " meses" %></td>
            <td><%= prestamo.getFechaAlta()%></td>
            <td>
    	 	<form method="post" action="/TPINT_GRUPO_10_LAB4/ServletSolicitudesPrestamos">
    	 	    <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                <button type="submit" name="accion" value="aceptar" id="aceptar">Aceptar</button>
            </form>
            </td>
            <td>
            <form action="/TPINT_GRUPO_10_LAB4/ServletSolicitudesPrestamos" method="post">
                <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                <button type="submit" name="accion" value="rechazar" id="rechazar">Rechazar</button>
            </form>
        </td>
        </tr>
     <% } } %>
    </tbody>
</table>

        <!-- Enlace para volver al menu -->
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>

</body>
</html>