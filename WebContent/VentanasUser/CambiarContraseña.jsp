<%@page import="entidad.Usuario" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cambiar contrase�a</title>
    
    <!-- Importar los estilos de botones -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css"> 
</head>
<body>

<div class="form-container">
    <h1>Cambiar contrase�a</h1>

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

     <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletCambiarContrase�a">
      
        <!-- Contrase�a actual -->
        <div class="form-group">
            <label for="contrasenaActual">Ingrese su contrase�a actual</label>
            <input id="contrasenaActual" type="password"  class="contra" name="contrasenaActual" maxlength="50">
        </div>     
                
        <!-- Contrase�a nueva -->
        <div class="form-group">
            <label for="contrasenaNueva">Ingrese su nueva contrase�a</label>
            <input id="contrasenaNueva" type="password"  class="contra" name="contrasenaNueva" maxlength="50">
        </div>

        <!-- Repetir Contrase�a -->
        <div class="form-group">
            <label for="contrasenaNueva2">Repetir nueva contrase�a</label>
            <input id="contrasenaNueva2" type="password"  class="contra" name="contrasenaNueva2" maxlength="50">
        </div>
		<a href="#" id="mostrar-contra" onclick="mostrarContra()">
		        Mostrar contrase�a
		    </a>
        <!-- Bot�n de Guardar -->
        <div class="form-group">
            <input type="submit" value="Modificar" class="button button-green"> 
        </div>
    </form>

    <!-- Enlace para volver al men� -->
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
        toggleLink.textContent = "Ocultar contrase�a";
    } else {
        passwordField1.type = "password";
        passwordField2.type = "password";
        passwordField3.type = "password";
        toggleLink.textContent = "Mostrar contrase�a";
    }
}
</script>


</body>
</html>
