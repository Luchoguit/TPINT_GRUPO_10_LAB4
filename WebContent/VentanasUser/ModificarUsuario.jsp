<%@page import="entidad.Usuario" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Alta de Usuario</title>
    
    <!-- Importar los estilos de botones -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css"> 
</head>
<body>

<div class="form-container">
    <h1>Modificar Usuario</h1>

    <!-- Contenedor de mensajes -->
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            String tipoMensaje = "error";
            if (mensaje.contains("exitosamente")) {
                tipoMensaje = "success";
            }
    %>
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>


<%
	Usuario user = (Usuario) request.getAttribute("user");
    if (user != null) {
%>
     <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletModificarUsuario">
        <input id="id" name="id" type="hidden" value="<%= user.getCliente() != null ? user.getCliente().getId() : ""  %>">
                
        <!-- Contrase�a -->
        <div class="form-group">
            <label for="contrasena">Contrase�a</label>
            <input id="contrasena" type="password"  class="contra" name="contrasena" maxlength="50" required value="<%= user.getContrase�a() %>">
        </div>

        <!-- Repetir Contrase�a -->
        <div class="form-group">
            <label for="contrasena2">Repetir Contrase�a</label>
            <input id="contrasena2" type="password"  class="contra" name="contrasena2" maxlength="50" required value="<%= user.getContrase�a() %>">
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
<%
    } else {
        out.println("<p>No hay un usuario en sesi�n. Por favor, inicie sesi�n nuevamente.</p>");
    }
%>

</div>


<script>

function mostrarContra() {
    const passwordField = document.getElementById("contrasena");
    const passwordField2 = document.getElementById("contrasena2");
    const toggleLink = document.getElementById("mostrar-contra");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        passwordField2.type = "text";
        toggleLink.textContent = "Ocultar contrase�a";
    } else {
        passwordField.type = "password";
        passwordField2.type = "password";
        toggleLink.textContent = "Mostrar contrase�a";
    }
}
</script>


</body>
</html>
