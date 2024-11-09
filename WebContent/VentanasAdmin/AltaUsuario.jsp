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
        input[type="password"],
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
    <h1>Alta Usuario</h1>
    <form method="post" action="ServletAgregarCliente">
        
        <div class="form-group">
            <label for="localidad">Cliente</label>
            <select id="localidad" name="localidad" required>
                <option value="">Seleccione el cliente</option>
                <!-- Las opciones se llenarán dinámicamente desde la base de datos -->
            </select>
        </div>
        <!-- Usuario -->
        <div class="form-group">
            <label for="usuario">Usuario</label>
            <input id="usuario" type="text" name="usuario" maxlength="50" required>
        </div>

        <!-- Contraseña -->
        <div class="form-group">
            <label for="contrasena">Contraseña</label>
            <input id="contrasena" type="password" name="contrasena" maxlength="50" required>
        </div>
        
        <!-- Repetir Contraseña (armar logica verificando que coinciden las pw)-->
        <div class="form-group">
            <label for="contrasena2">Repetir Contraseña</label>
            <input id="contrasena2" type="password" name="contrasena" maxlength="50" required>
        </div>
        <!-- ID Usuario -->
        <input type="hidden" name="id_usuario" value="1">

        <div class="form-group">
            <input type="submit" value="Guardar Cliente">
        </div>
    </form>
</div>

</body>
</html>
