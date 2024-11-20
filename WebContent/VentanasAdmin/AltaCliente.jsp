<%@page import="entidad.Provincia" %>
<%@page import="entidad.Localidad" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Alta de Cliente</title>

    <!-- Importar Estilos Externos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">

</head>

<body>

<div class="form-container">
    <h1>Alta Cliente</h1>

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

    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletAltaCliente">
        <!-- DNI -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" onkeypress="soloNumeros(event)" name="dni" value="<%= request.getAttribute("precargaDni") != null ? request.getAttribute("precargaDni") : "" %>" maxlength="11">
        </div>

        <!-- CUIL -->
        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" onkeypress="soloNumeros(event)" name="cuil" value="<%= request.getAttribute("precargaCuil") != null ? request.getAttribute("precargaCuil") : "" %>" maxlength="11">
        </div>

        <!-- Nombre -->
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre" value="<%= request.getAttribute("precargaNombre") != null ? request.getAttribute("precargaNombre") : "" %>" maxlength="50">
        </div>

        <!-- Apellido -->
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" name="apellido" value="<%= request.getAttribute("precargaApellido") != null ? request.getAttribute("precargaApellido") : "" %>" maxlength="50">
        </div>

        <!-- Sexo -->
        <div class="form-group">
            <label for="sexo">Sexo</label>
            <select id="sexo" name="sexo">
                <option value="">Seleccione...</option>
                <option value="M" <%= (request.getAttribute("precargaSexo") != null && "M".equals(request.getAttribute("precargaSexo"))) ? "selected" : "" %>>Masculino</option>
                <option value="F" <%= (request.getAttribute("precargaSexo") != null && "F".equals(request.getAttribute("precargaSexo"))) ? "selected" : "" %>>Femenino</option>
            </select>
        </div>

        <!-- Nacionalidad -->
        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" name="nacionalidad" value="<%= request.getAttribute("precargaNacionalidad") != null ? request.getAttribute("precargaNacionalidad") : "" %>" maxlength="50">
        </div>

        <!-- Fecha de Nacimiento -->
        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de Nacimiento</label>
            <input type="text" id="fecha_nacimiento" name="fecha_nacimiento" maxlength="10" placeholder="dd/mm/yyyy" required>
        </div>

        <!-- Dirección -->
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input id="direccion" type="text" name="direccion" value="<%= request.getAttribute("precargaDireccion") != null ? request.getAttribute("precargaDireccion") : "" %>" maxlength="100">
        </div>

        <!-- Provincia -->
        <div class="form-group">
            <label for="provincia">Provincia</label>
            <select id="provincia" name="provincia">
                <option value="">Seleccione una provincia</option>
                <% 
                    List<Provincia> listaProvincias = (List<Provincia>)request.getAttribute("provincias");
                    if (listaProvincias != null) {
                        for (Provincia provincia : listaProvincias) {
                %>
                    <option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                <% } } else {System.out.println("Las provincias no llegaron correctamente.");}
                %>
            </select>
        </div>

        <!-- Localidad -->
        <div class="form-group">
            <label for="localidad">Localidad</label>
            <select id="localidad" name="localidad">
                <option value="">Seleccione una localidad</option>
            </select>
        </div>

        <!-- Correo Electrónico -->
        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input id="correo" type="email" name="correo" value="<%= request.getAttribute("precargaCorreo") != null ? request.getAttribute("precargaCorreo") : "" %>" maxlength="100" required>
        </div>

        <!-- Teléfono -->
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input id="telefono" type="number" onkeypress="soloNumeros(event)" name="telefono" value="<%= request.getAttribute("precargaTelefono") != null ? request.getAttribute("precargaTelefono") : "" %>" required maxlength="20">
        </div>
        
        <!-- Botón de Enviar -->
        <div class="form-group">
            <input type="submit" value="Guardar Cliente" class="button button-green"> <!-- Aplicamos el estilo de botón -->
        </div>
    </form>

    <!-- Enlace para volver al menu -->
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="button button-blue"> <!-- Aplicamos el estilo de botón -->
    </a>

</div>

<!-- Scripts -->
<script>
    document.getElementById("fecha_nacimiento").addEventListener("input", function (e) {
        let input = e.target.value;
        input = input.replace(/\D/g, "");
        if (input.length >= 3) {
            input = input.substring(0, 2) + "/" + input.substring(2);
        }
        if (input.length >= 6) {
            input = input.substring(0, 5) + "/" + input.substring(5, 9);
        }
        e.target.value = input;
    });
</script>

<script>
    document.getElementById('provincia').addEventListener('change', function() {
        var provinciaId = this.value;
        if (!provinciaId || isNaN(provinciaId)) {
            console.error('ID de provincia no válido:', provinciaId);
            return;
        }
        if (provinciaId) {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', '/TPINT_GRUPO_10_LAB4/ServletAltaCliente?provinciaId=' + provinciaId, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var localidadSelect = document.getElementById('localidad');
                    localidadSelect.innerHTML = '<option value="">Seleccione una localidad</option>';
                    var localidades = JSON.parse(xhr.responseText);
                    localidades.forEach(function(localidad) {
                        var option = document.createElement('option');
                        option.value = localidad.id;
                        option.text = localidad.nombre;
                        localidadSelect.appendChild(option);
                    });
                }
            };
            xhr.send();
        } else {
            document.getElementById('localidad').innerHTML = '<option value="">Seleccione una localidad</option>';
        }
    });
</script>

<script>
    function soloNumeros(event) {
        var key = event.keyCode || event.which;
        var tecla = String.fromCharCode(key);
        var regex = /^[0-9]$/;
        if (!regex.test(tecla)) {
            event.preventDefault();
        }
    }
</script>

</body>
</html>
