<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos de la cuenta</title>
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
            white-space: nowrap; 
            overflow-x: auto; 
            max-width: 200px; 
        }
        th {
            background-color: #f4f4f4;
        }
        .filter-container {
            text-align: center;
            margin: 20px;
        }
        .filter-container input[type="text"] {
            padding: 8px;
            width: 300px;
        }
        .filter-container input[type="submit"] {
            padding: 8px 12px;
        }
    </style>

</head>
<body>

	<h2 style="text-align: center;">Cuentas disponibles</h2>

	<table>
	    <tr>
	        <th>Tipo de cuenta</th>
	        <th>Numero de cuenta</th>
	        <th>CBU</th>
	        <th>DNI Titular</th>
	        <th>Nombre titular</th>
	        <th>Apellido titular</th>
	        <th>Fecha de creacion</th>
	        <th>Saldo</th>
	        <th></th>
	    </tr>
	    <tr>
	        <td>Caja de ahorro</td>
	        <td>2245567880</td>
	        <td>1000000009234567891234</td>
	        <td>11234333</td>
	        <td>Juan</td>
	        <td>Perez</td>
	        <td>01/01/2024</td>
	        <td>10.000,00</td>
	        <th><a href="../MENUS/IndexCuenta.jsp">Ver Cuenta</a></th>
	    </tr>
	    	    <tr>
	        <td>Cuenta sueldo</td>
	        <td>2245567881</td>
	        <td>1000000009234567891235</td>
	        <td>11234333</td>
	        <td>Juan</td>
	        <td>Perez</td>
	        <td>01/01/2023</td>
	        <td>200.000,00</td>
	        <th><a href="../MENUS/IndexCuenta.jsp">Ver Cuenta</a>
	    </tr>
	</table>

	<div style="text-align: center;">
    	<form method="post" action="servletCuentas">
        	<input type="submit" name="btnRegresar" value="Regresar">
    	</form>
	</div>

</body>
</html>