<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagar Pr�stamo</title>

    <!-- Estilos Externos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">

</head>

<body>

    <h2 style="text-align: center;">Pagar Pr�stamo</h2>

    <!-- Tabla de Detalles del Pr�stamo -->
    <table>
        <thead>
            <tr>
                <th>ID Pr�stamo</th>
                <th>Cuotas Pagas</th>
                <th>Valor de la Cuota</th>
                <th>Monto Total</th>
                <th>Fecha Pr�stamo</th>
                <th>Cuenta</th>
                <th>Acci�n</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>001</td>
                <td>3/12</td>
                <td>$1,500.00</td>
                <td>$18,000.00</td>
                <td>01/01/2024</td>
                <td>
                    <select name="cuenta" required>
                        <option value="2245567880">Caja de Ahorro - 2245567880</option>
                        <option value="2245567881">Cuenta Sueldo - 2245567881</option>
                    </select>
                </td>
                <td>
                    <form method="post" action="ProcesarPago.jsp">
                        <input type="hidden" name="idPrestamo" value="001">
                        <!-- Bot�n de pagar cuota con clase 'button' y 'button-green' -->
                        <input type="submit" value="Pagar Cuota" class="button button-green">
                    </form>
                </td>
            </tr>
        </tbody>
    </table>

    <!-- Bot�n para Regresar -->
    <div class="action-buttons">
        <form method="post" action="servletPrestamos">
            <input type="submit" name="btnRegresar" value="Regresar" class="button button-blue">
        </form>
    </div>

</body>

</html>
