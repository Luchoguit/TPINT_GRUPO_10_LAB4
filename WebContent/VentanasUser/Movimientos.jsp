<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Movimientos de Cuenta Bancaria</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .table-container {
        width: 80%;
        max-width: 800px;
        background-color: #fff;
        padding: 20px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
    }
    .table-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 20px;
    }
    .table-header div {
        margin: 5px 0;
    }
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #f4f4f4;
        font-weight: bold;
    }
    .table-title {
        text-align: center;
        font-size: 24px;
        margin-bottom: 20px;
    }  
   .back-button {
        padding: 15px 40px; 
        background-color: #28a745;
        color: #fff;
        border: none;
        border-radius: 5px;
        font-size: 24px; 
        cursor: pointer;
    }
    .back-button:hover {
        background-color: #218838;
    }
</style>
</head>
<body>

<div class="table-container">
    <h1 class="table-title">Extracto de Cuenta</h1>
    
    <div class="table-header">
        <div><strong>Fecha: 02-11-2024 </strong> </div>
        <div><strong>Titular:</strong> Malvina Ibarra </div>
        <div><strong>Número de Cuenta:</strong> 0000-0000-00-0000000000</div>
    </div>

    <table>
        <thead>
            <tr>
                <th>Fecha</th>
                <th>Concepto</th>
                <th>Importe</th>
                <th>Saldo</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>15/08/2020</td>
                <td>Saldo Anterior</td>
                <td></td>
                <td>8,074.56 </td>
            </tr>
            <tr>
                <td>20/08/2020</td>
                <td>Cargo Traspaso Saldo</td>
                <td>-657.32 </td>
                <td>7,417.24 </td>
            </tr>
            <tr>
                <td>21/08/2020</td>
                <td>Pago Cheque Compensado</td>
                <td>-5,171.81 </td>
                <td>2,245.43 </td>
            </tr>
            <tr>
                <td>21/08/2020</td>
                <td>Cargo Compra</td>
                <td>-195.01 </td>
                <td>2,050.42 </td>
            </tr>
            <tr>
                <td>26/08/2020</td>
                <td>Intereses</td>
                <td>0.50 </td>
                <td>1,993.93 </td>
            </tr>
            <tr>
                <td>27/08/2020</td>
                <td>Pago Gimnasio</td>
                <td>-37.46 </td>
                <td>1,956.47 </td>
            </tr>
            <tr>
                <td>27/08/2020</td>
                <td>Pago Recibo Teléfono</td>
                <td>-48.19 </td>
                <td>1,908.74 </td>
            </tr>
            <tr>
                <td>28/08/2020</td>
                <td>Cargo Compra</td>
                <td>-13.15 </td>
                <td>1,895.74 </td>
            </tr>
            <tr>
                <td>28/08/2020</td>
                <td>Pago Recibo Luz</td>
                <td>-48.19 </td>
                <td>1,847.55 </td>
            </tr>
            <tr>
                <td>29/08/2020</td>
                <td>Nómina</td>
                <td>4,000.00 </td>
                <td>5,732.57 </td>
            </tr>
        </tbody>
    </table>
    <div class="button-container">
        <input type="button" value="Volver" class="back-button" onclick="window.history.back()">
    </div>
</div>

</body>
</html>