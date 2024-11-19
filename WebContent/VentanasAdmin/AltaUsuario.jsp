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
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloFormulario.css">
    
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
        
        <!-- Tipo de Usuario -->
        
        <div class="form-group">
            <label for="tipoUsuario">Seleccione el tipo de Usuario</label>
			<select id="tipoUsuario" name="tipoUsuario">
			    <option value="cliente">Cliente</option>
			    <option value="admin">Administrador</option>
			</select>
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

