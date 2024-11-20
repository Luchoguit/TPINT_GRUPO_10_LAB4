<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
    <style>        
    </style>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    
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
            <label for="password">Contraseña</label>
            <input id="password" type="password" name="password" required class="contra">
		    <a href="#" id="mostrar-contra" onclick="mostrarContra()">
		        Mostrar contraseña
		    </a>
        </div>

		<div class="form-group" style="display: flex; justify-content: space-between; align-items: center;">
		    <input type="submit" value="Aceptar" class="button button-green">
		</div>
    </form>
</div>

<script>

function mostrarContra() {
    const passwordField = document.getElementById("password");
    const toggleLink = document.getElementById("mostrar-contra");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleLink.textContent = "Ocultar contraseña";
    } else {
        passwordField.type = "password";
        toggleLink.textContent = "Mostrar contraseña";
    }
}
</script>

</body>
</html>
