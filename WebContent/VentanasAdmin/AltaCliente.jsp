<%@page import="entidad.Provincia" %>
<%@page import="entidad.Localidad" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Alta de Cliente</title>
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
        
         /* Estilos para el contenedor de mensajes */
        .message-container {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            font-weight: bold;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
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

<div class="form-container">
    <h1>Alta Cliente</h1>
    
    
        <!-- Contenedor de mensajes -->
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
            String tipoMensaje = "error";
            if (mensaje.contains("exitoso")) {
                tipoMensaje = "success";
            }
    %>
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>
    
    
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletAltaCliente">
        <!-- DNI -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input id="dni" type="number" name="dni" maxlength="11">
        </div>

        <!-- CUIL -->
        <div class="form-group">
            <label for="cuil">CUIL</label>
            <input id="cuil" type="number" name="cuil" maxlength="11">
        </div>

        <!-- Nombre -->
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input id="nombre" type="text" name="nombre" maxlength="50">
        </div>

        <!-- Apellido -->
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input id="apellido" type="text" name="apellido" maxlength="50">
        </div>

        <!-- Sexo -->
        <div class="form-group">
            <label for="sexo">Sexo</label>
            <select id="sexo" name="sexo">
                <option value="">Seleccione...</option>
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
            </select>
        </div>

        <!-- Nacionalidad -->
        <div class="form-group">
            <label for="nacionalidad">Nacionalidad</label>
            <input id="nacionalidad" type="text" name="nacionalidad" maxlength="50">
        </div>

        <!-- Fecha de Nacimiento -->
        <div class="form-group">
		<label for="fecha_nacimiento">Fecha de Nacimiento</label>
		<input type="text" id="fecha_nacimiento" name="fecha_nacimiento" maxlength="10" placeholder="dd/mm/yyyy" required>
        </div>

        <!-- Dirección -->
        <div class="form-group">
            <label for="direccion">Dirección</label>
            <input id="direccion" type="text" name="direccion" maxlength="100">
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
			    <option value="">Seleccione una provincia</option>
			    <% 
			        List<Localidad> listaLocalidades = (List<Localidad>)request.getAttribute("localidades");
            		if (listaLocalidades != null) {
			        for (Localidad localidad : listaLocalidades) {
			    %>
			        <option value="<%= localidad.getId() %>"><%= localidad.getNombre() %></option>
			    <% } } else {System.out.println("Las localidades no llegaron correctamente.");}
			    %>
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
       
        <div class="form-group">
            <input type="submit" value="Guardar Cliente">
        </div>
    </form>
    
        <!-- Enlace para volver al menu -->
    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
        <input type="button" value="Volver al Menu" class="btn-volver">
    </a>
    
</div>





        <!-- Script para formato de fecha -->
<script>
    document.getElementById("fecha_nacimiento").addEventListener("input", function (e) {
        let input = e.target.value;
        // Elimina todos los caracteres que no sean dígitos
        input = input.replace(/\D/g, "");
        
        // Agrega las barras
        if (input.length >= 3) {
            input = input.substring(0, 2) + "/" + input.substring(2);
        }
        if (input.length >= 6) {
            input = input.substring(0, 5) + "/" + input.substring(5, 9);
        }

        // Actualiza el valor del campo con el formato aplicado
        e.target.value = input;
    });
</script>


</body>
</html>
