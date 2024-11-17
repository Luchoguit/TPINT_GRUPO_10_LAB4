<%@page import="entidad.Cliente" %>
<%@page import="entidad.TipoCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de cuenta</title>
<style>
        /* Estilos básicos para la estructura del formulario si quieren copien todo */
        
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }
        .form-container {
            width: 400px;
            padding: 20px;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="email"],
        input[type="number"],
        input[type="date"],
        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        
        .readonly-input {
            pointer-events: none; 
            background-color: #f0f0f0; 
            border: 1px solid #ccc; 
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
	
	<div class="form-container">
		<h1>Alta Cuenta</h1>	 
		
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
	 	
	 
	 	
	 	<form method="post" action="/TPINT_GRUPO_10_LAB4/ServletSolicitarAltaCuenta">
	 	
	 	<% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>

	 	<div class="form-group">
	 		<label for="dniUsuario">DNI Usuario</label>
            <input type="number" min="1" step="1" name="dniUsuario" class="readonly-input" readonly 
            value="<%= cliente != null ? cliente.getDni() : "" %>" >
            <label for="apellidoUsuario">Apellido: </label>
            <input type="text" name="apellidoUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getApellido() : "" %>" >
            <label for="nombreUsuario">Nombre: </label>
            <input type="text" name="nombreUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getNombre() : "" %>" >
	 	</div>
	 
	 <% List<TipoCuenta> listaTC = (List<TipoCuenta>)request.getAttribute("listaTC"); %>
	     <div class="form-group">
			<select id="tipoCuenta" name="tipoCuenta" required>
                <option value="">Seleccione...</option>
                <% if (listaTC != null) {
                    for (TipoCuenta tipoCuenta : listaTC) { %>
                <option value="<%= tipoCuenta.getId() %>"><%= tipoCuenta.getDescripcion() %></option>
         <% } } %>
            </select>
        </div>
        
         <div class="form-group">
            <input type="submit" value="Enviar solicitud">
        </div>
        
        <!-- Para posible manejo de error -->
        <!-- El usuario solo podra tener 3 cuentas activas -->
        <label> </label>
        
         </form>
         
         <div class="volver-menu">
         	 <!-- Enlace para volver al menu -->
		    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp" class="volver-menu">
		        <input type="button" value="Volver al Menu" class="btn-volver">
		    </a>
         </div>

         
	 </div>
	 

	
</body>


</html>