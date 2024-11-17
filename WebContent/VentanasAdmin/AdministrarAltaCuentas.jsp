<%@page import="entidad.SolicitudAltaCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar solicitudes cuentas</title>

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
		.message-container {
    padding: 15px;
    margin: 20px 0;
    border-radius: 4px;
    font-size: 16px;
    text-align: center;
    width: 100%;
    max-width: 500px;
    margin-left: auto;
    margin-right: auto;
}


.message-container.success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
}


.message-container.error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}


.message-container.warning {
    background-color: #fff3cd;
    color: #856404;
    border: 1px solid #ffeeba;
}
		
		
    
</style>

</head>
<body>

<h2 style="text-align: center;">Administrar solicitud alta cuentas</h2>

<%
    String mensaje = (String) request.getAttribute("mensaje");
    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
    if (mensaje != null && tipoMensaje != null) {
%>
    <div class="message-container <%= tipoMensaje %>">
        <%= mensaje %>
    </div>
<% } %>

<table>
	    <thead>
	        <tr>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>DNI</th>
	            <th>Tipo de cuenta</th>
	            <th></th>
	            <th></th>
	        </tr>
	    </thead>
	    <tbody>
	 
	 <%-- Verifica si hay un mensaje de error en el request --%>
<% String error = (String) request.getAttribute("error"); %>



	 
	    <% 
    List<SolicitudAltaCuenta> listaSolicitudes = (List<SolicitudAltaCuenta>)request.getAttribute("listaSolicitudes");
    if (listaSolicitudes != null) {
        for (SolicitudAltaCuenta solicitud : listaSolicitudes) {
    %>
    
	        <tr>
	            <td><%= solicitud.getCliente().getNombre() %></td>
	            <td><%= solicitud.getCliente().getApellido() %></td>
	            <td><%= solicitud.getCliente().getDni() %></td>
	            <td><%= solicitud.getTipoCuenta().getDescripcion() %></td>
	            <td>
            <form action="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas" method="post">
                <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
                <button type="submit" name="accion" value="aceptar" id="aceptar">Aceptar</button>
            </form>
        </td>
        <td>
            <form action="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas" method="post">
                <input type="hidden" name="solicitudId" value="<%= solicitud.getId() %>">
                <button type="submit" name="accion" value="rechazar" id="rechazar">Rechazar</button>
            </form>
        </td>
                </tr>
	        <% } } %>
	    </tbody>
	</table>
	
	


	<div class="volver-menu">
     <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
			<input type="button" value="Volver al Menu" class="btn-volver">
		</a>
     </div>

</body>

</html>