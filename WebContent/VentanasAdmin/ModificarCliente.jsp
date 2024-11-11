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
        
        .volver-menu {
		    display: flex;               
		    justify-content: center;     
		    align-items: center;                     
		}
		
		
		.btn-volver {
		    background-color: #007bff;   
		    color: white;                
		    padding: 10px 20px;          
		    border: none;                
		    border-radius: 5px;          
		    font-size: 16px;             
		    cursor: pointer;            
		    transition: background-color 0.3s ease;  
		}
		
		.btn-volver:hover {
		    background-color: #0056b3;   
		}
        
        
    </style>
</head>
<body>

  <!-- Suponiendo que despes vamos a tener un tipo de dato cliente ... simulo como seria el value -->

<div class="form-container">
    <h2>Modificar Cliente</h2>
    
    <form method="post" action="ServletModificarCliente">

        <!-- DNI -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" name="dni" maxlength="11" readonly required value="${cliente.dni}">
        </div>

        <!-- CUIL -->
        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" name="cuil" maxlength="11" readonly required value="${cliente.cuil}">
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
    		<input id="fecha_nacimiento" type="date" name="fecha_nacimiento" required value="${cliente.fechaNacimiento}">
		</div>

        <!-- Dirección -->
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input id="direccion" type="text" name="direccion" required maxlength="100" value="${cliente.direccion}">
        </div>

        <!-- Localidad -->
        <div class="form-group">
            <label for="localidad">Localidad</label>
            <select id="localidad" name="localidad">
                <option value="${cliente.localidadCliente.nombre}"></option>

            </select>
        </div>

        <!-- Provincia -->
        <div class="form-group">
            <label for="provincia">Provincia</label>
            <select id="provincia" name="provincia">
                <option value="${cliente.provinciaCliente.nombre}"></option>
               
            </select>
        </div>

        <!-- Correo Electrónico -->
        <div class="form-group">
            <label for="correo">Correo Electrónico</label>
            <input id="correo" type="email" name="correo" maxlength="100" required value="${cliente.correo}">
        </div>

        <!-- Teléfono -->
        <div class="form-group">
            <label for="telefono">Teléfono</label>
            <input id="telefono" type="text" name="telefono" required maxlength="20" value="${cliente.telefono}">
        </div>

        <div class="form-group">
            <input type="submit" value="Modificar Cliente">
        </div>
        
	    <!-- Enlace para volver al menu -->
	    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
	        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>
    </form>
</div>

</body>
</html>