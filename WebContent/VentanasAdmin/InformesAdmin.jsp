<%@page import="entidad.Movimiento" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="utilidades.Formato" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Movimientos</title>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<style>
.chart-container {
    width: 100%;  
    height: 300px;
}
</style>
<body>
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
        <div class="form-group">
            <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp">
                <button type="submit" class="button button-blue">Regresar al Menú</button>
            </form>
        </div>
    </div>

    <div class="filter-container">
        <% 
            // Obtener los valores de los totales y el balance calculados en el Servlet
            BigDecimal totalIngresos = (BigDecimal) request.getAttribute("totalIngresos");
            BigDecimal totalEgresos = (BigDecimal) request.getAttribute("totalEgresos");
            BigDecimal balance = (BigDecimal) request.getAttribute("balance");

            // Asegurarse de que no haya nulls
            if (totalIngresos == null) totalIngresos = BigDecimal.ZERO;
            if (totalEgresos == null) totalEgresos = BigDecimal.ZERO;
            if (balance == null) balance = BigDecimal.ZERO;
        %>

 		<div class="message-container success">
            <p><strong>Total Ingresos:</strong> <%= Formato.formatoMonetario(totalIngresos) %></p>
            <p><strong>Total Egresos:</strong> <%= Formato.formatoMonetario(totalEgresos) %></p>
            <p><strong>Balance:</strong> <%= Formato.formatoMonetario(balance) %></p>
        </div>

        <canvas id="chartMovimientos"></canvas>
<canvas id="chartMovimientos" class="chart-container"></canvas>
<script>
    const ctx = document.getElementById('chartMovimientos');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Ingresos', 'Egresos'],  // Etiquetas de las barras
            datasets: [{
                label: 'Ingresos',  // Etiqueta para la barra de ingresos
                data: [
                    <%= totalIngresos %>, 
                    0  // Egresos en esta barra, será 0 en la barra de ingresos
                ],
                backgroundColor: '#28a745',  // Color verde para los ingresos
                borderColor: '#218838',  // Color verde para los bordes de los ingresos
                borderWidth: 1
            },
            {
                label: 'Egresos',  // Etiqueta para la barra de egresos
                data: [
                    0,  // Ingresos en esta barra, será 0 en la barra de egresos
                    <%= totalEgresos %>
                ],
                backgroundColor: '#dc3545',  // Color rojo para los egresos
                borderColor: '#c82333',  // Color rojo para los bordes de los egresos
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,  // Asegura que el eje Y comience desde cero
                },
                x: {
                    // Ajustar el comportamiento de las barras dentro de cada categoría
                    categoryPercentage: 0.8,  // Define cuánto espacio ocupan las barras dentro de su categoría
                    barPercentage: 0.9,      // Ajusta el porcentaje de la barra con respecto al espacio disponible
                }
            },
            // Ajuste de distribución para las barras en el eje X
            plugins: {
                legend: {
                    position: 'top',  // Coloca la leyenda en la parte superior
                }
            }
        }
    });
</script>

    </div>
</body>
</html>
