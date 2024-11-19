<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@page import="entidad.Provincia" %>
<%@page import="entidad.Localidad" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Modificar Cliente</title>
    <style>          
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloFormulario.css">
    
</head>
<body>

  <!-- Suponiendo que despes vamos a tener un tipo de dato cliente ... simulo como seria el value -->

<div class="form-container">
    <h2>Modificar Cliente</h2>
    
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
    
<form method="post" action="/TPINT_GRUPO_10_LAB4/ServletModificarCliente">
	
	<input id="id" name="id" type="hidden" value="<%= ((Cliente) request.getAttribute("cliente")).getId() %>">

	<input id="dniOriginal" name="dniOriginal" type="hidden" value="<%= ((Cliente) request.getAttribute("cliente")).getDni() %>">
	<input id="cuilOriginal" name="cuilOriginal" type="hidden" value="<%= ((Cliente) request.getAttribute("cliente")).getCuil() %>">

    <div class="form-group">
        <label for="dni">DNI</label>
        <input id="dni" name="dni" type="number"  value="<%= ((Cliente) request.getAttribute("cliente")).getDni() %>">
    </div>

    <div class="form-group">
        <label for="cuil">CUIL</label>
        <input id="cuil" name="cuil" type="number"  value="<%= ((Cliente) request.getAttribute("cliente")).getCuil() %>">
    </div>

    <div class="form-group">
        <label for="nombre">Nombre</label>
        <input id="nombre" name="nombre" type="text" value="<%= ((Cliente) request.getAttribute("cliente")).getNombre() %>">
    </div>

    <div class="form-group">
        <label for="apellido">Apellido</label>
        <input id="apellido" name="apellido" type="text" value="<%= ((Cliente) request.getAttribute("cliente")).getApellido() %>">
    </div>

    <div class="form-group">
        <label for="sexo">Sexo</label>
        <select id="sexo" name="sexo">
            <option value="M" <%= ((Cliente) request.getAttribute("cliente")).getSexo().equals("M") ? "selected" : "" %>>Masculino</option>
            <option value="F" <%= ((Cliente) request.getAttribute("cliente")).getSexo().equals("F") ? "selected" : "" %>>Femenino</option>
        </select>
    </div>

    <div class="form-group">
        <label for="nacionalidad">Nacionalidad</label>
        <input id="nacionalidad" name="nacionalidad" type="text" value="<%= ((Cliente) request.getAttribute("cliente")).getNacionalidad() %>">
    </div>

    <div class="form-group">
        <label for="fecha_nacimiento">Fecha de Nacimiento</label>
        <input id="fecha_nacimiento" name="fecha_nacimiento" type="date" value="<%= ((Cliente) request.getAttribute("cliente")).getFechaNacimiento() %>">
    </div>

    <div class="form-group">
        <label for="direccion">Direccion</label>
        <input id="direccion" name="direccion" type="text" value="<%= ((Cliente) request.getAttribute("cliente")).getDireccion() %>">
    </div>

    <div class="form-group">
        <label for="provincia">Provincia</label>
        <select id="provincia" name="provincia">
            <% 
                List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("listaProvincias");
                Provincia provinciaSeleccionada = ((Cliente) request.getAttribute("cliente")).getProvinciaCliente();
                if (listaProvincias != null) {
                    for (Provincia provincia : listaProvincias) {
            %>
                <option value="<%= provincia.getId() %>" <%= (provinciaSeleccionada != null && provincia.getId() == provinciaSeleccionada.getId()) ? "selected" : "" %>>
                    <%= provincia.getNombre() %>
                </option>
            <%      }
                }
            %>
        </select>
    </div>

    <div class="form-group">
        <label for="localidad">Localidad</label>
        <select id="localidad" name="localidad">
            <% 
                List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("listaLocalidades");
                Localidad localidadSeleccionada = ((Cliente) request.getAttribute("cliente")).getLocalidadCliente();
                if (listaLocalidades != null) {
                    for (Localidad localidad : listaLocalidades) {
            %>
                <option value="<%= localidad.getId() %>" <%= (localidadSeleccionada != null && localidad.getId() == localidadSeleccionada.getId()) ? "selected" : "" %>>
                    <%= localidad.getNombre() %>
                </option>
            <%      }
                }
            %>
        </select>
    </div>

    <div class="form-group">
        <label for="correo">Correo Electronico</label>
        <input id="correo" name="correo" type="email" value="<%= ((Cliente) request.getAttribute("cliente")).getCorreo() %>">
    </div>

    <div class="form-group">
        <label for="telefono">Telofono</label>
        <input id="telefono" name="telefono" type="text" value="<%= ((Cliente) request.getAttribute("cliente")).getTelefono() %>">
    </div>

	<div class="volver-menu">
	    <button type="submit" class="btn-volver">Guardar cambios</button>
	</div>
</form>
	
	    <!-- Enlace para volver al menu -->
    <a href="/TPINT_GRUPO_10_LAB4/ServletListadoClientes" style="margin-top: 10px;" class="volver-menu">
        <input type="button" value="Volver al Listado de Clientes" class="btn-volver">
    </a>
	
</div>

</body>
</html>