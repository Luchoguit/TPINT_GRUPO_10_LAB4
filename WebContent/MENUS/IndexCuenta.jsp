<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cuenta Sueldo</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #e9ecef;
            text-align: center; 
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
        }

        h1 {
            margin-bottom: 10px;
            color: #495057; 
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

        /* Estilo especial para el botón "Cerrar sesión" */
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
    <h1>CUENTA: ${cuenta.numeroCuenta}</h1>
	<h3 style="color: #495057;">Tipo: ${cuenta.tipoCuenta.descripcion}</h3>

    <div class="logged-user">
        Usuario: ${usuario.nombreUsuario}

    </div>

    <ul class="navigation">
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletMovimientosCuenta">Consultar movimientos <i class="fa fa-list-alt"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/servletTransferencia">Realizar transferencia <i class="fa fa-exchange-alt"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">Gestionar préstamos <i class="fa fa-hand-holding-usd"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletVerInformes">Ver informes <i class="fa fa-hand-holding-usd"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletVerCuentas">Volver a cuentas <i class="fa fa-credit-card"></i></a></li>
        <li><a href="/TPINT_GRUPO_10_LAB4/ServletCerrarSesion">Cerrar sesión <i class="fa fa-sign-out-alt"></i></a></li>
    </ul>

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
</div>

</body>
</html>
