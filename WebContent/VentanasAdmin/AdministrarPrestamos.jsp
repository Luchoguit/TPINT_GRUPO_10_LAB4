<%@page import="entidad.Prestamo" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
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
		
		
          		/* Estilos para los controles para la paginacion */
        
        .pagination {
		    text-align: center;
		    margin: 20px 0;
		}
		
		.pagination-link {
		    display: inline-block;
		    margin: 0 5px;
		    padding: 10px 15px;
		    text-decoration: none;
		    color: #007bff;
		    border: 1px solid #ddd;
		    border-radius: 5px;
		    transition: background-color 0.3s, color 0.3s;
		}
		
		.pagination-link:hover {
		    background-color: #007bff;
		    color: #fff;
		}
		
		.pagination-current {
		    display: inline-block;
		    margin: 0 5px;
		    padding: 10px 15px;
		    font-weight: bold;
		    color: #fff;
		    background-color: #007bff;
		    border: 1px solid #007bff;
		    border-radius: 5px;
		}
		
    
</style>
</head>
<body>




<h2 style="text-align: center;">Administrar Préstamos</h2>

		<% // Datos para paginacion
	    int paginaActual = (int) request.getAttribute("paginaActual");
	    int totalPaginas = (int) request.getAttribute("totalPaginas");
		%>

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
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>

</body>
</html>