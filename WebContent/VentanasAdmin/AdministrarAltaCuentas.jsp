<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar solicitudes cuentas</title>

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
        
        #rechazar {
        background-color: #e74c3c;
        color: #fff;
        padding: 5px 10px;
        font-size: 9px; 
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    #rechazar:hover {
        background-color: #c0392b;
    }
    
    #aceptar {
        background-color: #2ecc71;
        color: #fff;
        padding: 5px 10px;
        font-size: 9px; 
        border-radius: 5px; 
        cursor: pointer; 
        transition: background-color 0.3s ease;
    }

    #aceptar:hover {
        background-color: #27ae60;
    }
</style>

</head>
<body>

	<h2 style="text-align: center;">Administrar solicitud alta cuentas</h2>


	<table>
	    <thead>
	        <tr>
	            <th>Nombre</th>
	            <th>Apellido</th>
	            <th>DNI</th>
	            <th>Tipo de cuenta</th>
	            <th></th>
	            <th></th>
	        </tr>
	    </thead>
	    <tbody>
	        <tr>
	            <td>Juan</td>
	            <td>Perez</td>
	            <td>123456789</td>
	            <td>Caja de ahorro</td>
	            <td>
	                <button type="button" id="aceptar" >Aceptar</button>
	            </td>
	            <td>
	                <button type="button" id="rechazar">Rechazar</button>
	            </td>
	        </tr>
	    </tbody>
	</table>

</body>
</html>