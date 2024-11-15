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
        .filter-container input[type="text"] {
            padding: 8px;
            width: 300px;
        }
        .filter-container input[type="submit"] {
            padding: 8px 12px;
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

<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoClientes">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI, nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar">
    </form>
</div>

	


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