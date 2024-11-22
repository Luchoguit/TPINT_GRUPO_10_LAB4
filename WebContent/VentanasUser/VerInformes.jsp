<%@page import="entidad.Movimiento" %>
<%@page import="java.util.List" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="java.text.DecimalFormat" %>

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
<body>
    <div class="form-container">
        <h2>Generar Reporte de Movimientos</h2>
        <form method="post" action="ServletVerInformes">
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
            <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
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

            // Formatear los valores para mostrar en formato de moneda
            DecimalFormat df = new DecimalFormat("$###,###.##");

            // Asegurarse de que no haya nulls
            if (totalIngresos == null) totalIngresos = BigDecimal.ZERO;
            if (totalEgresos == null) totalEgresos = BigDecimal.ZERO;
            if (balance == null) balance = BigDecimal.ZERO;
        %>

        <div class="message-container success">
            <p><strong>Total Ingresos:</strong> <%= df.format(totalIngresos) %></p>
            <p><strong>Total Egresos:</strong> <%= df.format(totalEgresos) %></p>
            <p><strong>Balance:</strong> <%= df.format(balance) %></p>
        </div>

        <canvas id="chartMovimientos"></canvas>
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
                        }
                    }
                }
            });
        </script>
    </div>
</body>
</html>
