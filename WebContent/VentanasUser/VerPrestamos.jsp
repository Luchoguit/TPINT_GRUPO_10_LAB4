<%@page import="java.util.Map" %>
<%@page import="entidad.Prestamo" %>
<%@page import="utilidades.Formato" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestionar Préstamos</title>

    <!-- Estilos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/CSS/EstiloTabla.css">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
            margin: 0;
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
            background-color: #fff;
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f8f8f8;
        }

        td a {
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }

        td a:hover {
            text-decoration: underline;
        }

        .action-btn-submit {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .action-btn-submit:hover {
            background-color: #218838;
        }

        .action-buttons {
            text-align: center;
            margin-top: 20px;
        }

        .volver-menu {
            text-align: center;
            margin-top: 20px;
        }

        .volver-btn-back {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .volver-btn-back:hover {
            background-color: #0056b3;
        }

    </style>
</head>
<body>

    <!-- Título de la Página -->
    <h2>Préstamos Activos</h2>

    <!-- Tabla de Préstamos -->
    <table>
        <thead>
            <tr>
                <th>Codigo de Préstamo</th>
                <th>Cuotas Pagas</th>
                <th>Valor de la Cuota</th>
                <th>Monto Total</th>
                <th>Fecha del Préstamo</th>
                <th>Saldo Restante</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>		
		<% 
		    Map<Prestamo, Integer> prestamosCuotas = (Map<Prestamo, Integer>) request.getAttribute("prestamosCuotas");
		    if (prestamosCuotas != null) {
		        for (Map.Entry<Prestamo, Integer> entry : prestamosCuotas.entrySet()) {
		            Prestamo prestamo = entry.getKey();
		            int cuotasPagas = entry.getValue();
		%>
		<tr>
		    <td><%= prestamo.getIdPrestamo() %></td>
		    <td><%= cuotasPagas %> / <%= prestamo.getCantidadCuotas() %></td>
		    <td><%= Formato.formatoMonetario(prestamo.getImporteMensual()) %></td>
		    <td><%= Formato.formatoMonetario(prestamo.getImportePedido()) %></td>
		    <td><%= Formato.formatoFecha(prestamo.getFechaAlta()) %></td>
		    <td><%= Formato.formatoMonetario(prestamo.getImportePedido()) %></td>
		    <td>
		        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">
		            <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
		            <button type="submit">PAGAR CUOTA</button>
		        </form>
		    </td>
		</tr>
		<% 
		        }
		    }
		%>
        </tbody>
    </table>

    <!-- Botón para Solicitar un Nuevo Préstamo -->
    <div class="action-buttons">
        <form action="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo" method="get">
            <input type="submit" value="Solicitar nuevo Préstamo" class="action-btn-submit">
        </form>
    </div>

    <!-- Botón para Volver al Menú -->
    <div class="volver-menu">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
            <button type="submit" class="volver-btn-back">Volver a cuenta</button>
        </form>
    </div>

</body>
</html>
