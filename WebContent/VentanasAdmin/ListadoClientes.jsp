<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>Lista de Clientes</title>
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

<h2 style="text-align: center;">Lista de Clientes</h2>

<div class="filter-container">
    <form method="post" action="servletCliente">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI, nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar">
    </form>
</div>

<table>
    <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>CUIL</th>
        <th>Correo</th>
        <th>Teléfono</th>
        <th>Ver Datos</th>
        <th>Modificar</th>
        <th>Eliminar</th>
    </tr>
    <tr>
        <td>12345678</td>
        <td>Juan</td>
        <td>Pérez</td>
        <td>20123456789</td>
        <td>juan.perez@example.com</td>
        <td>123-456-7890</td>
        <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="12345678">             
                <input type="submit" name="btnModificar" value=" + ">
            </form>
        </td>
        <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="12345678">             
                <input type="submit" name="btnModificar" value="Modificar">
            </form>
        </td>
        <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="12345678">
                <input type="submit" name="btnEliminar" value="Eliminar">
            </form>
        </td>
    </tr>
    <tr>
        <td>87654321</td>
        <td>Ana</td>
        <td>Gómez</td>
        <td>20876543210</td>
        <td>ana.gomez@example.com</td>
        <td>098-765-4321</td>
         <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="12345678">             
                <input type="submit" name="btnModificar" value=" + ">
            </form>
        </td>
        <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="87654321">             
                <input type="submit" name="btnModificar" value="Modificar">
            </form>
        </td>
        <td>
            <form method="post" action="servletCliente">
                <input type="hidden" name="idCliente" value="87654321">
                <input type="submit" name="btnEliminar" value="Eliminar">             
            </form>
        </td>
    </tr>
</table>

</body>
</html>
