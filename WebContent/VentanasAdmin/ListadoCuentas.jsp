<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas del cliente</title>

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

	<h2 style="text-align: center;">Cuentas de clientes</h2>
	
	
	<div class="filter-container">
	    <form method="post" action="servletCuentas">
	        <input type="text" name="filtroCliente" placeholder="Ingrese DNI del titular o numero de cuenta">
	        <input type="submit" name="btnFiltrar" value="Filtrar">
	    </form>
	</div>
	
	<table>
	    <tr>
	        <th>Tipo de cuenta</th>
	        <th>Numero de cuenta</th>
	        <th>DNI Titular</th>
	        <th>Movimientos</th>
	        <th>Datos cuenta</th>
	        <th>Eliminar</th>       
	    </tr>
	    <tr>
	        <td>Caja de ahorro</td>
	        <td>2245567880</td>
	        <td>11234333</td>
	        <td>
	        	<form method="post" action="servletCuentas">
	                <input type="hidden" name="" value="">
	                <input type="submit" name="btnMovimientos" value="+">
	            </form>
	        </td>
	        <td>
	        	<form method="post" action="servletCuentas">
	                <input type="hidden" name="" value="">
	                <input type="submit" name="btnDatosCuenta" value="+">
	            </form>
	        </td>
	        <td>
	        	<form method="post" action="servletCuentas">
	                <input type="hidden" name="" value="">
	                <input type="submit" name="btnEliminar" value="Eliminar">
	            </form>
			</td>
	    </tr>
	</table>

</body>
</html>