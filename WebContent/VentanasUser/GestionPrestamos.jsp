<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestionar Préstamos</title>
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
            display: flex;
            justify-content: center;
            gap: 10px;
            margin: 20px;
        }
        .action-buttons a, .action-buttons input[type="submit"] {
            padding: 8px 12px;
            text-decoration: none;
            color: #000;
            border: 1px solid #ddd;
            background-color: #f4f4f4;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <h2 style="text-align: center;">Préstamos Activos</h2>

    <table>
        <tr>
            <th>Número de Préstamo</th>
            <th>Cuotas Pagas</th>
            <th>Valor de la Cuota</th>
            <th>Monto Total</th>
            <th>Fecha del Préstamo</th>
            <th>Saldo Restante</th>
            <th>Acciones</th>
        </tr>
        
        <tr>
            <td>002</td>
            <td>3 / 10</td>
            <td>$7,500</td>
            <td>$75,000</td>
            <td>10/03/2024</td>
            <td>$52,500</td>
            <td><a href="PagarPrestamo.jsp">Pagar cuota</a></td>
        </tr>
        <tr>
            <td>003</td>
            <td>9 / 24</td>
            <td>$3,000</td>
            <td>$72,000</td>
            <td>25/08/2023</td>
            <td>$45,000</td>
            <td><a href="PagarPrestamo.jsp">Pagar cuota</a></td>
        </tr>
    </table>

    <div class="action-buttons">
        <a href="PedirPrestamo.jsp">Solicitar nuevo Préstamo</a>
    </div>

</body>
</html>