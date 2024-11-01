<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
            background-color: #f1f1f1;
            border: 1px solid #ccc;
        }
        input[readonly], select[readonly] {
            pointer-events: none;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Datos del Cliente</h2>
    <form>
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" readonly value="999999">
        </div>

        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" readonly value="205555944">
        </div>

        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" readonly value="Diego">
        </div>

        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" readonly value="Maradona">
        </div>

        <div class="form-group">
            <label for="sexo">Sexo</label>
            <select id="sexo" readonly>
                <option value="M" ${cliente.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                <option value="F" ${cliente.sexo == 'F' ? 'selected' : ''}>Femenino</option>
            </select>
        </div>

        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" readonly value="Argentino">
        </div>

        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de Nacimiento</label>
            <input id="fecha_nacimiento" type="date" readonly value="2000 ac">
        </div>

        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input id="direccion" type="text" readonly value="No se">
        </div>

        <div class="form-group">
            <label for="localidad">Localidad</label>
            <select id="localidad" readonly>
                <!-- Opciones dinámicas desde la base de datos -->
            </select>
        </div>

        <div class="form-group">
            <label for="provincia">Provincia</label>
            <select id="provincia" readonly>
                <!-- Opciones dinámicas desde la base de datos -->
            </select>
        </div>

        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input id="correo" type="email" readonly value="Crack@gmail.com">
        </div>

        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input id="telefono" type="text" readonly value="10-1010-1010">
        </div>
    </form>
    <div class="button-container">
        <input type="button" value="Ir a la Página Principal" onclick="window.location.href='paginaPrincipal.jsp'">
        <input type="button" value="Volver al Listado de Clientes" onclick="window.location.href='ListadoClientes.jsp'">
    </div>
    
</div>

</body>
</html>
>