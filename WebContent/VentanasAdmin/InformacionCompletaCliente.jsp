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
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f4f4f4;
            overflow: auto;
        }
        
        .form-container {
            width: 400px;
            padding: 20px;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
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
            background-color: #f1f1f1;
            border: 1px solid #ccc;
        }
        input[readonly], select[readonly] {
            pointer-events: none;
        }
        .button-container {
            margin-top: 20px;
            text-align: center;
        }
        .button-container input[type="button"] {
            margin: 5px;
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .error-message {
            color: red;
            padding: 10px;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Datos del Cliente</h2>

    <% 
        // Obtener el mensaje del atributo
        String mensaje = (String) request.getAttribute("error");
        if (mensaje != null) {
            // Determinar el tipo de mensaje (error o success)
            String tipoMensaje = "error";
            if (mensaje.contains("exitoso")) {
                tipoMensaje = "success";
            }
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
            <select id="sexo" readonly>
                <option value="M" <c:if test="${cliente.sexo == 'M'}">selected</c:if>>Masculino</option>
                <option value="F" <c:if test="${cliente.sexo == 'F'}">selected</c:if>>Femenino</option>
            </select>
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
            <label for="localidad">Localidad</label>
            <input id="localidad" type="text" readonly value="<%= request.getAttribute("cliente") != null && ((Cliente) request.getAttribute("cliente")).getLocalidadCliente() != null ? ((Cliente) request.getAttribute("cliente")).getLocalidadCliente().getNombre() : "" %>">
        </div>

        <div class="form-group">
            <label for="provincia">Provincia</label>
            <input id="provincia" type="text" readonly value="<%= request.getAttribute("cliente") != null && ((Cliente) request.getAttribute("cliente")).getProvinciaCliente() != null ? ((Cliente) request.getAttribute("cliente")).getProvinciaCliente().getNombre() : "" %>">
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
    <div class="button-container">
    <!-- Enlace para ir a la Pagina Principal -->
    <a href="http://localhost:14591/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp">
    <input type="button" value="Ir a la Pagina Principal">
    </a>

    <!-- Enlace para volver al Listado de Clientes -->
    <a href="/TPINT_GRUPO_10_LAB4/ServletListadoClientes">
        <input type="button" value="Volver al Listado de Clientes">
    </a>
</div>
</div>

</body>
</html>
