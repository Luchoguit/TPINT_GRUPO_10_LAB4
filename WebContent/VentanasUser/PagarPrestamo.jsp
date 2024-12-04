<%@page import="entidad.Prestamo" %>
<%@page import="entidad.Cuenta" %>
<%@page import="java.util.List" %>
<%@page import="utilidades.Formato" %>
<%@page import="java.math.BigDecimal" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagar Préstamo</title>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
</head>
</head>
<body>

<div class="form-container">
    <h2>Pagar Préstamo</h2>

    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        String tipoMensaje = (String) request.getAttribute("tipoMensaje");
        if (mensaje != null && tipoMensaje != null) {
    %>
    <div class="message-container <%= tipoMensaje %>">
        <%= mensaje %>
    </div>
    <% } %>

    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletPagarPrestamo">
        <% 
            int cantidadCuotasPagas = (int) request.getAttribute("cantidadCuotasPagas");
            int cantidadImpagas = (int) request.getAttribute("cantidadImpagas");
            Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
            if (prestamo != null) {
                BigDecimal totalAbonado = prestamo.getImporteMensual().multiply(new BigDecimal(cantidadCuotasPagas));
                BigDecimal saldoRestante = prestamo.getImporteFinal().subtract(totalAbonado);
        %>

        <div class="form-group">
            <label>Importe solicitado</label>
            <input type="text" value="<%= Formato.formatoMonetario(prestamo.getImportePedido()) %>" readonly>
        </div>

        <div class="form-group">
            <label>Total a pagar</label>
            <input type="text" value="<%= Formato.formatoMonetario(prestamo.getImporteFinal()) %>" readonly>
        </div>

        <div class="form-group">
            <label>Cuotas pagas</label>
            <input type="text" value="<%= cantidadCuotasPagas %> / <%= prestamo.getCantidadCuotas() %>" readonly>
        </div>

        <div class="form-group">
            <label>Importe restante a pagar</label>
            <input type="text" value="<%= Formato.formatoMonetario(saldoRestante) %>" readonly>
        </div>

        <div class="form-group">
            <label>Valor de la cuota</label>
            <input type="text" value="<%= Formato.formatoMonetario(prestamo.getImporteMensual()) %>" readonly>
        </div>

        <div class="form-group">
            <label>Fecha del préstamo</label>
            <input type="text" value="<%= Formato.formatoFecha(prestamo.getFechaAlta()) %>" readonly>
        </div>

        <div class="form-group">
            <label for="cuenta">Cuenta</label>
            <select name="cuenta" id="cuenta" required onchange="updateSaldoDisponible(this)">
                <% 
                    List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
                    if (listaCuentas != null) {
                        boolean primeraCuenta = true;
                        for (Cuenta cuenta : listaCuentas) {
                            String descripcion = cuenta.getTipoCuenta().getDescripcion();
                            String numeroCuenta = cuenta.getNumeroCuenta();
                %>
                <option value="<%= cuenta.getId() %>" 
                        data-saldo="<%= Formato.formatoMonetario(cuenta.getSaldo()) %>"
                        <%= primeraCuenta ? "selected" : "" %>>
                    <%= descripcion %> - <%= numeroCuenta %>
                </option>
                <% 
                    primeraCuenta = false;
                        }
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label>Saldo disponible</label>
            <input type="text" id="saldoDisponible" readonly value="$0.00">
        </div>

        <div class="form-group">
            <label for="cantidadCuotas">Cantidad de cuotas a pagar</label>
            <input type="number" name="cantidadCuotas" id="cantidadCuotas" value="1" min="1" max="<%= cantidadImpagas %>" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Pagar Cuota" class="button button-green">
        </div>

        <% } %>
    </form>

            <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">
                <button type="submit" class="button button-blue">Regresar</button>
            </form>


<script>
    function updateSaldoDisponible(selectElement) {
        var saldo = selectElement.options[selectElement.selectedIndex].getAttribute('data-saldo');
        document.getElementById('saldoDisponible').value = saldo;
    }

    window.onload = function() {
        var selectElement = document.querySelector('select[name="cuenta"]');
        updateSaldoDisponible(selectElement);
    }
</script>

</body>
</html>
