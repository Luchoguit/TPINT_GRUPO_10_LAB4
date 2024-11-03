<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Página Principal</title>
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
        .main-container {
            width: 500px;
            padding: 20px;
            background: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
        }
        .navigation {
            list-style: none;
            padding: 0;
        }
        .navigation li {
            margin: 10px 0; /* Ajusta la separación entre los botones */
        }
        .navigation li a {
            text-decoration: none;
            padding: 6px 12px; /* Tamaño del botón */
            background-color: #007bff;
            color: white;
            border-radius: 5px;
            font-size: 14px; /* Tamaño de fuente */
            transition: background-color 0.3s ease;
        }
        .navigation li a:hover {
            background-color: #0056b3;
        }
        .logged-user {
            font-weight: bold;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

    <div class="main-container">
        <h1>BANCO G10</h1>

        <div class="logged-user">
            <!-- Mostrar el usuario logueado -->
            Usuario logueado: <%= request.getSession().getAttribute("usuarioLogueado") %>
        </div>

        <ul class="navigation">
            <li><a href="../ABM CUENTAS/VerCuentas.jsp">Seleccionar cuentas</a></li>
            <li><a href="../ABM CUENTAS/AltaCuenta.jsp">Crear nueva cuenta</a></li>
			<li><a href="../ABM CLIENTES/InformacionCompletaCliente.jsp">Ver datos personales</a></li>
            <li><a href="../LOGIN/Login.jsp">Cerrar sesion</a></li>
        </ul>

        <!-- Posibles mensajes aclaratorios -->
        <p style="color: green;">
            <% if (request.getAttribute("mensajeExito") != null) { %>
                <%= request.getAttribute("mensajeExito") %>
            <% } %>
        </p>
        <p style="color: red;">
            <% if (request.getAttribute("mensajeError") != null) { %>
                <%= request.getAttribute("mensajeError") %>
            <% } %>
        </p>
    </div>

</body>
</html>
