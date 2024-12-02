<%@page import="entidad.Usuario" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cambiar contraseña</title>
    
    <!-- Importar los estilos de botones -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css"> 
</head>
<body>

<div class="form-container">
    <h1>Cambiar contraseña</h1>

    <!-- Contenedor de mensajes -->
    <% 
    String mensaje = (String) request.getAttribute("mensaje");
    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
    if (mensaje != null && tipoMensaje != null) {
    %>
        <!-- Mostrar el mensaje con el estilo adecuado -->
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>

     <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletCambiarContraseña">
      
        <!-- Contraseña actual -->
        <div class="form-group">
            <label for="contrasenaActual">Ingrese su contraseña actual</label>
            <input id="contrasenaActual" type="password"  class="contra" name="contrasenaActual" maxlength="50">
        </div>     
                
        <!-- Contraseña nueva -->
        <div class="form-group">
            <label for="contrasenaNueva">Ingrese su nueva contraseña</label>
            <input id="contrasenaNueva" type="password"  class="contra" name="contrasenaNueva" maxlength="50">
        </div>

        <!-- Repetir Contraseña -->
        <div class="form-group">
            <label for="contrasenaNueva2">Repetir nueva contraseña</label>
            <input id="contrasenaNueva2" type="password"  class="contra" name="contrasenaNueva2" maxlength="50">
        </div>
		<a href="#" id="mostrar-contra" onclick="mostrarContra()">
		        Mostrar contraseña
		    </a>
        <!-- Botón de Guardar -->
        <div class="form-group">
            <input type="submit" value="Modificar" class="button button-green"> 
        </div>
    </form>

    <!-- Enlace para volver al menú -->
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="button button-blue"> 
    </a>

</div>


<script>

function mostrarContra() {
    const passwordField1 = document.getElementById("contrasenaActual");
    const passwordField2 = document.getElementById("contrasenaNueva");
    const passwordField3 = document.getElementById("contrasenaNueva2");
    const toggleLink = document.getElementById("mostrar-contra");
    
    if (passwordField1.type === "password") {
        passwordField1.type = "text";
        passwordField2.type = "text";
        passwordField3.type = "text";
        toggleLink.textContent = "Ocultar contraseña";
    } else {
        passwordField1.type = "password";
        passwordField2.type = "password";
        passwordField3.type = "password";
        toggleLink.textContent = "Mostrar contraseña";
    }
}
</script>


</body>
</html>
