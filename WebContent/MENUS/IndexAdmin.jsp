<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>P�gina Principal</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
	body {
	    font-family: 'Arial', sans-serif;
	    margin: 0;
	    padding: 0;
	    background-color: #e9ecef;
	    text-align: center;
	    display: flex;
	    flex-direction: column;
	    align-items: center;  
	    min-height: 100vh; 
	}
	
	.main-container {
	    width: 500px;
	    padding: 20px;
	    background: #fff;
	    box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.1);
	    border-radius: 10px;
	    text-align: center;
	    display: flex;
	    flex-direction: column;
	    align-items: center;
	    margin-top: 20px;
	}


        h1 {
            margin-bottom: 30px;
            color: black; 
            font-size: 28px;
            font-weight: 600;
        }

        .logged-user {
            font-weight: bold;
            margin-bottom: 20px;
            color: #495057;
        }

        .navigation {
            list-style: none;
            padding: 0;
            margin: 0;
            width: 100%;
        }

        .navigation li {
            margin: 12px 0;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .navigation li a {
            text-decoration: none;
            padding: 10px 20px;
            border: 2px solid black; 
            color: black; 
            background-color: transparent; 
            border-radius: 8px;
            width: 100%;
            font-size: 16px;
            font-weight: 500;
            display: flex;
            align-items: center;
            justify-content: center; 
        }

        .navigation li a:hover {
            background-color: #f1f1f1; 
        }

        .navigation li a .fa {
            margin-left: 15px;
            font-size: 18px;
            opacity: 0.7;
        }

        .navigation li:hover {
            background-color: #f1f1f1;
        }

        .navigation li:hover a {
            background-color: #f1f1f1; 
        }

        .navigation li:last-child a {
            background-color: #dc3545; 
            border-color: #dc3545; 
            color: white; 
        }

        .navigation li:last-child a:hover {
            background-color: #c82333; 
            border-color: #c82333; 
        }

        .message {
            margin-top: 20px;
            padding: 10px;
            text-align: center;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
        }

        .message-success {
            background-color: #d4edda;
            color: #155724;
        }

        .message-error {
            background-color: #f8d7da;
            color: #721c24;
        }
    </style>
    
        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    
</head>
<body>

<div class="main-container">
    <h1>BANCO G10</h1>

    <div class="logged-user">
		ADMIN: ${usuario.nombreUsuario.toUpperCase()}
    </div>

    <ul class="navigation">
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletAltaCliente">Agregar nuevo cliente <i class="fa fa-user-plus"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletLAltaUsuario">Crear nuevo usuario <i class="fa fa-plus-circle"></i></a></li>      
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletListadoClientes">Ver listado de Clientes <i class="fa fa-users"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletListadoClientesEliminados">Ver clientes Deshabilitados <i class="fa fa-user-times"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletListadoCuentas">Ver listado de Cuentas <i class="fa fa-list-alt"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">Ver listado de Cuentas Deshabilitadas <i class="fa fa-ban"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletAdministrarAltaCuentas">Gestionar solicitudes de cuentas <i class="fa fa-university"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletSolicitudesPrestamos">Gestionar solicitudes de prestamos <i class="fa fa-hand-holding-usd"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletInformesAdmin">Ver informes <i class="fa fa-chart-bar"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletCerrarSesion">Cerrar sesi�n <i class="fa fa-sign-out-alt"></i></a></li>
    </ul>

</div>
    <div class="message">
        <p class="message-success">
            <% if (request.getAttribute("mensajeExito") != null) { %>
                <%= request.getAttribute("mensajeExito") %>
            <% } %>
        </p>
        <p class="message-error">
            <% if (request.getAttribute("mensajeError") != null) { %>
                <%= request.getAttribute("mensajeError") %>
            <% } %>
        </p>
    </div>

</body>
</html>
