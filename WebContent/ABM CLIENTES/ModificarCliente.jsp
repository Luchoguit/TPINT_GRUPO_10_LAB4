<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Modificar Cliente</title>
    <style>  
        body {
		    font-family: Arial, sans-serif;
		    margin: 0;
		    padding: 0;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    min-height: 100vh; /* Cambiado a min-height */
		    background-color: #f4f4f4;
		    overflow: auto; /* Permitir desplazamiento si es necesario */
		}
		
		.form-container {
		    width: 400px;
		    padding: 20px;
		    background: #fff;
		    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
		    border-radius: 8px;
		    overflow: hidden; /* Para evitar que se desborde el contenido */
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
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

  <!-- Suponiendo que despes vamos a tener un tipo de dato cliente ... simulo como seria el value -->

<div class="form-container">
    <h2>Modificar Cliente</h2>
    
    <form method="post" action="ServletModificarCliente">
        <!-- ID Cliente (oculto para identificar cliente a modificar) -->
        <input type="hidden" name="id_cliente" value="${cliente.id_cliente}">

        <!-- DNI -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" name="dni" maxlength="11" required value="${cliente.dni}">
        </div>

        <!-- CUIL -->
        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" name="cuil" maxlength="11" required value="${cliente.cuil}">
        </div>

        <!-- Nombre -->
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre" maxlength="50" required value="${cliente.nombre}">
        </div>

        <!-- Apellido -->
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" name="apellido" maxlength="50" required value="${cliente.apellido}">
        </div>

        <!-- Sexo -->
        <div class="form-group">
            <label for="sexo">Sexo</label>
            <select id="sexo" name="sexo" required>
                <option value="">Seleccione...</option>
                <option value="M" ${cliente.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                <option value="F" ${cliente.sexo == 'F' ? 'selected' : ''}>Femenino</option>
            </select>
        </div>

        <!-- Nacionalidad -->
        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" name="nacionalidad" required maxlength="50" value="${cliente.nacionalidad}">
        </div>

        <!-- Fecha de Nacimiento -->
        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de Nacimiento</label>
            <input id="fecha_nacimiento" type="date" required name="fecha_nacimiento" value="${cliente.fecha_nacimiento}">
        </div>

        <!-- Direcci�n -->
        <div class="form-group">
            <label for="direccion">Direcci�n</label>
            <input id="direccion" type="text" name="direccion" required maxlength="100" value="${cliente.direccion}">
        </div>

        <!-- Localidad -->
        <div class="form-group">
            <label for="localidad">Localidad</label>
            <select id="localidad" name="localidad" required>
                <option value="">Seleccione una localidad</option>
                <!-- Las opciones se llenar�n din�micamente desde la base de datos -->
            </select>
        </div>

        <!-- Provincia -->
        <div class="form-group">
            <label for="provincia">Provincia</label>
            <select id="provincia" name="provincia" required>
                <option value="">Seleccione una provincia</option>
                <!-- Las opciones se llenar�n din�micamente desde la base de datos -->
            </select>
        </div>

        <!-- Correo Electr�nico -->
        <div class="form-group">
            <label for="correo">Correo Electr�nico</label>
            <input id="correo" type="email" name="correo" maxlength="100" required value="${cliente.correo}">
        </div>

        <!-- Tel�fono -->
        <div class="form-group">
            <label for="telefono">Tel�fono</label>
            <input id="telefono" type="text" name="telefono" required maxlength="20" value="${cliente.telefono}">
        </div>

        <div class="form-group">
            <input type="submit" value="Modificar Cliente">
        </div>
         <div class="form-group">
            <input type="submit" value="Cancelar">
        </div>
    </form>
</div>

</body>
</html>