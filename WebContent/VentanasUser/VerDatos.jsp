<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@page import="entidad.Provincia" %>
<%@page import="entidad.Localidad" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Ver Cliente</title>
    <style>
    </style>
     	   <!-- Estilos -->
    
            <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
            <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    
    
</head>
<body>

<div class="form-container">
    <h2>Datos del Cliente</h2>

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

    <form>
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getDni() : "" %>">
        </div>

        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getCuil() : "" %>">
        </div>

        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getNombre() : "" %>">
        </div>

        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getApellido() : "" %>">
        </div>

		<div class="form-group">
		    <label for="sexo">Sexo</label>
		    <input type="text" id="sexo" name="sexo" value="${cliente.sexo == 'M' ? 'Masculino' : 'Femenino'}" readonly>
		</div>

        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getNacionalidad() : "" %>">
        </div>

        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de Nacimiento</label>
            <input id="fecha_nacimiento" type="date" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getFechaNacimiento() : "" %>">
        </div>

        <div class="form-group">
            <label for="direccion">Direccion</label>
            <input id="direccion" type="text" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getDireccion() : "" %>">
        </div>

		<div class="form-group">
            <label for="provincia">Provincia</label>
            <input id="provincia" type="text" readonly value="<%= request.getAttribute("cliente") != null && ((Cliente) request.getAttribute("cliente")).getProvinciaCliente() != null ? ((Cliente) request.getAttribute("cliente")).getProvinciaCliente().getNombre() : "" %>">
        </div>


        <div class="form-group">
            <label for="localidad">Localidad</label>
            <input id="localidad" type="text" readonly value="<%= request.getAttribute("cliente") != null && ((Cliente) request.getAttribute("cliente")).getLocalidadCliente() != null ? ((Cliente) request.getAttribute("cliente")).getLocalidadCliente().getNombre() : "" %>">
        </div>

        <div class="form-group">
            <label for="correo">Correo Electronico</label>
            <input id="correo" type="email" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getCorreo() : "" %>">
        </div>

        <div class="form-group">
            <label for="telefono">Telefono</label>
            <input id="telefono" type="text" readonly value="<%= request.getAttribute("cliente") != null ? ((Cliente) request.getAttribute("cliente")).getTelefono() : "" %>">
        </div>
    </form>
    
    <!-- Botón para regresar al menú -->
    <div class="volver-menu">
        <form method="get" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp">
            <button type="submit" class="button button-blue">Ir a la Página Principal</button>
        </form>
    </div>
</div>

</body>
</html>
