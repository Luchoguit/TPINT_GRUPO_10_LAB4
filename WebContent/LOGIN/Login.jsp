<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
    <style>
        /* Estilos b�sicos para la estructura del formulario si quieren copien todo */
        
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
        
        .contra {
    	width: 100%;
	    max-width: 100%;
	    padding: 8px;
	    box-sizing: border-box;
		}
		
		#mostrar-contra{
		font-size: 0.9em;
		color: blue;
		margin-left: 10px;
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

<div class="form-container">
    <h2>Login</h2>
    
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
    
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletLogin">
 
        <div class="form-group">
            <label for="usuario">Usuario</label>
            <input id="usuario" type="text" name="usuario" required>
        </div>

        <div class="form-group">
            <label for="password">Contrase�a</label>
            <input id="password" type="password" name="password" required class="contra">
        </div>

		<div class="form-group" style="display: flex; justify-content: space-between; align-items: center;">
		    <input type="submit" value="Aceptar">
		    <a href="#" id="mostrar-contra" onclick="mostrarContra()">
		        Mostrar contrase�a
		    </a>
		</div>
    </form>
</div>

<script>

function mostrarContra() {
    const passwordField = document.getElementById("password");
    const toggleLink = document.getElementById("mostrar-contra");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleLink.textContent = "Ocultar contrase�a";
    } else {
        passwordField.type = "password";
        toggleLink.textContent = "Mostrar contrase�a";
    }
}
</script>

</body>
</html>
