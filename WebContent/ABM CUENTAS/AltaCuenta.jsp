<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de cuenta</title>
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
        
        .readonly-input {
            pointer-events: none; 
            background-color: #f0f0f0; 
            border: 1px solid #ccc; 
        }
        
    </style>
</head>
<body>
	
	<div class="form-container">
		<h1>Alta Cuenta</h1>
	 	
	 	</br>
	 	
	 	<div class="form-group">
	 		<label for="dniUsuario">DNI Usuario</label>
            <input type="number" min="1" step="1" name="dniUsuario" required>
            <label for="apellidoUsuario">Apellido: </label>
            <input type="text" name="apellidoUsuario" class="readonly-input" readonly>
            <label for="nombreUsuario">Nombre: </label>
            <input type="text" name="nombreUsuario" class="readonly-input" readonly>
	 	</div>
	 
	     <div class="form-group">
			<select id="tipoCuenta" name="tipoCuenta" required>
                <option value="">Seleccione...</option>
                <option value="Caja de ahorro">Caja de ahorro</option>
                <option value="Cuenta corriente">Cuenta corriente</option>
            </select>
        </div>
        
        
         <div class="form-group">
            <input type="submit" value="Crear cuenta">
        </div>
        
        <!-- Para posible manejo de error -->
        <!-- El usuario solo podra tener 3 cuentas activas -->
        <label> </label>
        
	 </div>
	
</body>
</html>