<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Alta de Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f4f4f4;
        }
        .form-container {
            width: 400px;
            padding: 20px;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-container h1 {
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
        input[type="text"], input[type="password"], select {
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
    <h1>Alta Usuario</h1>

    <!-- Contenedor de mensajes -->
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            String tipoMensaje = "error";
            if (mensaje.contains("exitoso")) {
                tipoMensaje = "success";
            }
    %>
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>

    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletLAltaUsuario">
        
        <div class="form-group">
            <label for="Cliente">Cliente</label>
            <select id="Cliente" name="Cliente" required>
                <option value="">Seleccione el cliente</option>
                <% 
                    List<Cliente> clientesSinUsuario = (List<Cliente>) request.getAttribute("clientesSinUsuario");
                    if (clientesSinUsuario != null) {
                        for (Cliente cliente : clientesSinUsuario) {
                %>
                    <option value="<%= cliente.getId() %>">
                        <%= cliente.getNombre() + " " + cliente.getApellido() %>
                    </option>
                <% 
                        } 
                    } else {
                        out.println("No hay clientes sin usuario asociado");
                    }
                %>
            </select>
        </div>

        <!-- Usuario -->
        <div class="form-group">
            <label for="usuario">Usuario</label>
            <input id="usuario" type="text" name="usuario" maxlength="50" required>
        </div>

        <!-- Contraseña -->
        <div class="form-group">
            <label for="contrasena">Contraseña</label>
            <input id="contrasena" type="password" name="contrasena" maxlength="50" required>
        </div>

        <!-- Repetir Contraseña -->
        <div class="form-group">
            <label for="contrasena2">Repetir Contraseña</label>
            <input id="contrasena2" type="password" name="contrasena2" maxlength="50" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Guardar Usuario">
        </div>
    </form>
    
    <!-- Enlace para volver al menu -->
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>
    
</div>

</body>
</html>

