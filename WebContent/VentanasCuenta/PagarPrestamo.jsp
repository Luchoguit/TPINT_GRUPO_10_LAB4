<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagar Préstamo</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
        .action-buttons {
            text-align: center;
            margin-top: 20px;
        }
        .action-buttons input[type="submit"] {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .action-buttons input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h2 style="text-align: center;">Pagar Préstamo</h2>

    <table>
        <tr>
            <th>ID Préstamo</th>
            <th>Cuotas Pagas</th>
            <th>Valor de la Cuota</th>
            <th>Monto Total</th>
            <th>Fecha Préstamo</th>
            <th>Cuenta</th>
            <th>Acción</th>
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