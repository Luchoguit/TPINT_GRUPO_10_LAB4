<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Clientes</title>
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
        .filter-container {
            text-align: center;
            margin: 20px;
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
		
		/* Estilos para el contenedor de mensajes */
        .message-container {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            font-weight: bold;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
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

<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientesEliminados">
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
        	<form method="post" action="/TPINT_GRUPO_10_LAB4/ServletActivarCliente">
                <input type="hidden" name="dniCliente" value="<%= cliente.getDni() %>">
                <input type="submit" name="btnActivar" value="Activar">
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
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>


</body>
</html>
