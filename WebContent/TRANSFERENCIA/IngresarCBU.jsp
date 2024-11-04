<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar CBU</title>
<style>
        .container {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .column {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        .form-group input {
            width: 100%;
            box-sizing: border-box;
        }

        .form-group button {
            margin-left: 10px;
        }

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

        .form-group .button-container {
            margin-top: 20px;
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">Transferir</h2>

<div>
<div class="container" style="margin-left: 100px">
    <form method="post" action="" style="display: flex; align-items: center; gap: 10px;">
        <h2 style="text-align: center; margin: 10;">CBU </h2>
        <input type="text" name="inputCBU" placeholder="Ingrese CBU a transferir" style="margin-left: 5px; width: 250px;">
        <input type="submit" name="btnCBU" value="Buscar" style="margin-left: 5px">
    </form>
</div>


        
        <!-- Tabla de datos del CBU -->
        <table>
            <thead>
                <tr>
                    <th>Nombre Usuario</th>
                    <th>ID Usuario</th>
                    <th>ID Cuenta</th>
                    <th>CBU</th>
                    <th>Transferir</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Juan Perez</td>
                    <td>123</td>
                    <td>456</td>
                    <td>000123456789</td>
                    <td>
                <form onsubmit="mostrarFormulario(event)">
                    <input type="hidden" name="CBU" value="">
                    <input type="submit" value="Transferencia">
                </form>
            </td>
                </tr>
            </tbody>
        </table>
 
   <div id="formularioMonto" style="display: none; margin-top: 15px; margin-left: 100px">
	    <label for="monto">Monto $</label>
	    <input type="number" id="monto" name="monto" placeholder="Ingrese el monto">
	    <button type="button" onclick="aceptarTransferencia()">Aceptar</button>
	</div>
</div>
<script>
    function mostrarFormulario(event) {
        event.preventDefault(); // Evita el envío del formulario
        document.getElementById("formularioMonto").style.display = "block"; // Muestra el formulario de monto
    }

    function aceptarTransferencia() {
        const monto = document.getElementById("monto").value;
        alert("Transferencia aceptada por $" + monto);
        
    }
</script>

</body>
</html>