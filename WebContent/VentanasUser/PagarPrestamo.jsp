<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagar Pr�stamo</title>
    <style>
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
    
</head>
<body>

    <h2 style="text-align: center;">Pagar Pr�stamo</h2>

    <table>
        <tr>
            <th>ID Pr�stamo</th>
            <th>Cuotas Pagas</th>
            <th>Valor de la Cuota</th>
            <th>Monto Total</th>
            <th>Fecha Pr�stamo</th>
            <th>Cuenta</th>
            <th>Acci�n</th>
        </tr>
        <tr>
            <td>001</td>
            <td>3/12</td>
            <td>$1.500,00</td>
            <td>$18.000,00</td>
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
                    <input type="submit" value="Pagar Cuota">
                </form>
            </td>
        </tr>
    </table>

    <div class="action-buttons">
        <form method="post" action="servletPrestamos">
            <input type="submit" name="btnRegresar" value="Regresar">
        </form>
    </div>

</body>
</html>