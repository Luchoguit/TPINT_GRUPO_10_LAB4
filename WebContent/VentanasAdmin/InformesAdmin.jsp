<%@page import="entidad.Movimiento" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="utilidades.Formato" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>Reporte de Movimientos</title>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 20px;
            margin: 0;
            padding: 20px;
        }

        .form-container, .message-container, .chart-container {
            width: 80%;
            max-width: 800px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        .message-container {
            background-color: #e8f5e9;
        }

        .chart-container {
            position: relative;
            height: 400px;
        }
    </style>
</head>
<body>

    <!-- Formulario para generar el reporte -->
    <div class="form-container">
        <h2>Generar Reporte de Movimientos</h2>
        <form method="post" action="ServletInformesAdmin">
            <div class="form-group">
                <label for="fechaInicio">Fecha Inicio:</label>
                <input type="date" id="fechaInicio" name="fechaInicio" required>
            </div>
            <div class="form-group">
                <label for="fechaFin">Fecha Fin:</label>
                <input type="date" id="fechaFin" name="fechaFin" required>
            </div>
            <input type="submit" class="button button-green" value="Generar Reporte">
        </form>
    </div>

    <!-- Mostrar totales de ingresos y egresos -->
    <div class="message-container success">
        <%
            BigDecimal totalIngresos = (BigDecimal) request.getAttribute("totalIngresos");
            BigDecimal totalEgresos = (BigDecimal) request.getAttribute("totalEgresos");
            BigDecimal balance = (BigDecimal) request.getAttribute("balance");

            if (totalIngresos == null) totalIngresos = BigDecimal.ZERO;
            if (totalEgresos == null) totalEgresos = BigDecimal.ZERO;
            if (balance == null) balance = BigDecimal.ZERO;
        %>
        <p><strong>Total Ingresos:</strong> <%= Formato.formatoMonetario(totalIngresos) %></p>
        <p><strong>Total Egresos:</strong> <%= Formato.formatoMonetario(totalEgresos) %></p>
        <p><strong>Balance:</strong> <%= Formato.formatoMonetario(balance) %></p>
    </div>

    <!-- Gráfico de ingresos y egresos -->
    <div class="chart-container">
        <canvas id="chartIngresosEgresos"></canvas>
    </div>

    <!-- Mostrar totales de movimientos -->
    <div class="message-container success">
        <%
        Integer totalMovimientos = (Integer) request.getAttribute("totalMovimientos");
        if (totalMovimientos == null) totalMovimientos = 0;

        Integer cantAltaCuentas = (Integer) request.getAttribute("cantAltaCuentas");
        if (cantAltaCuentas == null) cantAltaCuentas = 0;

        Integer cantAltaPrestamos = (Integer) request.getAttribute("cantAltaPrestamos");
        if (cantAltaPrestamos == null) cantAltaPrestamos = 0;

        Integer cantPagoPrestamos = (Integer) request.getAttribute("cantPagoPrestamos");
        if (cantPagoPrestamos == null) cantPagoPrestamos = 0;

        Integer cantTransferencias = (Integer) request.getAttribute("cantTransferencias");
        if (cantTransferencias == null) cantTransferencias = 0;        

        %>
        <p><strong>Total de Movimientos:</strong> <%= totalMovimientos %></p>
        <p><strong>Alta Cuentas:</strong> <%= cantAltaCuentas %></p>
        <p><strong>Alta Préstamos:</strong> <%= cantAltaPrestamos %></p>
        <p><strong>Pago Préstamos:</strong> <%= cantPagoPrestamos %></p>
        <p><strong>Transferencias:</strong> <%= cantTransferencias %></p>
    </div>

    <!-- Gráfico de tipos de movimientos -->
    <div class="chart-container">
        <canvas id="chartTiposMovimientos"></canvas>
    </div>
    
    <div class="volver-menu">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp">
            <button type="submit" class="button button-blue">Regresar al Menú</button>
        </form>
   </div>

<script>
    // Gráfico de Ingresos y Egresos
    new Chart(document.getElementById('chartIngresosEgresos'), {
        type: 'bar',
        data: {
            labels: ['Ingresos', 'Egresos'],
            datasets: [
                {
                    label: 'Ingresos',
                    data: [<%= totalIngresos %>, 0],
                    backgroundColor: '#28a745',
                    borderColor: '#218838',
                    borderWidth: 1
                },
                {
                    label: 'Egresos',
                    data: [0, <%= totalEgresos %>],
                    backgroundColor: '#dc3545',
                    borderColor: '#c82333',
                    borderWidth: 1
                }
            ]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false },
                datalabels: {
                    anchor: 'end',
                    align: 'top',
                    color: 'black',
                    font: { weight: 'bold', size: 14 },
                    formatter: (value) => value.toLocaleString()
                },
                title: {
                    display: true,
                    text: 'Ingresos y Egresos',
                    font: { size: 18, weight: 'bold' }
                }
            },
            scales: { y: { beginAtZero: true } }
        }
    });

    // Gráfico de Tipos de Movimientos
    new Chart(document.getElementById('chartTiposMovimientos'), {
        type: 'bar',
        data: {
            labels: ['Alta Cuentas', 'Alta Préstamos', 'Pago Préstamos', 'Transferencias'],
            datasets: [{
                data: [<%= cantAltaCuentas %>, <%= cantAltaPrestamos %>, <%= cantPagoPrestamos %>, <%= cantTransferencias %>],
                backgroundColor: ['#007bff', '#ffc107', '#28a745', '#17a2b8'],
                borderColor: ['#0056b3', '#e0a800', '#1e7e34', '#117a8b'],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: false },
                datalabels: {
                    anchor: 'end',
                    align: 'top',
                    color: 'black',
                    font: { weight: 'bold', size: 14 },
                    formatter: (value) => value.toLocaleString()
                },
                title: {
                    display: true,
                    text: 'Cantidad de Movimientos',
                    font: { size: 18, weight: 'bold' }
                }
            },
            scales: { y: { beginAtZero: true } }
        }
    });
</script>


</body>
</html>
