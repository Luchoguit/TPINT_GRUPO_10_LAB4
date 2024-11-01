<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Alta de Cliente</title>
    <style>
        /* Estilos básicos para la estructura del formulario si quieren copien todo */
        
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
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h1>Alta Cliente</h1>
    <form method="post" action="ServletAgregarCliente">
        <!-- DNI -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" name="dni" maxlength="11" required>
        </div>

        <!-- CUIL -->
        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" name="cuil" maxlength="11" required>
        </div>

        <!-- Nombre -->
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre" maxlength="50" required>
        </div>

        <!-- Apellido -->
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" name="apellido" maxlength="50" required>
        </div>

        <!-- Sexo -->
        <div class="form-group">
            <label for="sexo">Sexo</label>
            <select id="sexo" name="sexo" required>
                <option value="">Seleccione...</option>
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
            </select>
        </div>

        <!-- Nacionalidad -->
        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" name="nacionalidad" required maxlength="50">
        </div>

        <!-- Fecha de Nacimiento -->
        <div class="form-group">
            <label for="fecha_nacimiento">Fecha de Nacimiento</label>
            <input id="fecha_nacimiento" type="date" required name="fecha_nacimiento" >
        </div>

        <!-- Dirección -->
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input id="direccion" type="text" name="direccion" required maxlength="100">
        </div>

       <!-- Localidad -->
	<div class="form-group">
   	 <label for="localidad">Localidad</label>
    	<select id="localidad" name="localidad" required>
        <option value="">Seleccione una localidad</option>
        <!-- Las opciones se llenarán dinámicamente desde la base de datos -->
    	</select>
		</div>

	<!-- Provincia -->
	<div class="form-group">
    	<label for="provincia">Provincia</label>
    	<select id="provincia" name="provincia" required>
        <option value="">Seleccione una provincia</option>
        <!-- Las opciones se llenarán dinámicamente desde la base de datos -->
    	</select>
	</div>

        <!-- Correo Electrónico -->
        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input id="correo" type="email" name="correo" maxlength="100" required>
        </div>

        <!-- Teléfono -->
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input id="telefono" type="text" name="telefono" required maxlength="20">
        </div>

        <!-- ID Usuario, supongo que tendra alguna logica que aplicarle despues -->
        <input type="hidden" name="id_usuario" value="1"> 

        <div class="form-group">
            <input type="submit" value="Guardar Cliente">
        </div>
    </form>
</div>

</body>
</html>
