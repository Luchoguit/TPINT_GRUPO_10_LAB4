<%@page import="entidad.Prestamo" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagar Préstamo</title>

    <!-- Estilos Externos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">

</head>

<body>

    <h2 style="text-align: center;">Pagar Préstamo</h2>

    <!-- Tabla de Detalles del Préstamo -->
    <table>
        <thead>
            <tr>
                <th>ID Préstamo</th>
                <th>Cuotas Pagas</th>
                <th>Valor de la Cuota</th>
                <th>Monto Total</th>
                <th>Fecha Préstamo</th>
                <th>Cuenta</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
		   <% 
                Prestamo prestamo = (Prestamo)request.getAttribute("prestamo");
                if (prestamo != null) {
            %>
            <tr>
                <td><%= prestamo.getIdPrestamo() %></td>
                <td>0 / <%= prestamo.getCantidadCuotas() %></td>
                <td>$<%= prestamo.getImporteMensual() %></td>
                <td>$<%= prestamo.getImportePedido() %></td>
                <td><%= prestamo.getFechaAlta() %></td>
                <td>
                    <select name="cuenta" required>
                        <option value="2245567880">Caja de Ahorro - 2245567880</option>
                        <option value="2245567881">Cuenta Sueldo - 2245567881</option>
                    </select>
                </td>
                <td>
                    <form method="post" action="ProcesarPago.jsp">
                        <input type="hidden" name="idPrestamo" value="001">
                        <input type="submit" value="Pagar Cuota" class="button button-green">
                    </form>
                </td>
            </tr>
             <% } %>
        </tbody>
    </table>

    <!-- Botón para Regresar -->
    <div class="action-buttons">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">
            <input type="submit" name="btnRegresar" value="Regresar" class="button button-blue">
        </form>
    </div>

</body>

</html>
