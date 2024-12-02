<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="utilidades.Formato" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Transferencia</title>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
</head>
<body>

<% 
    String mensaje = (String) request.getAttribute("mensaje");
    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
    if (mensaje != null && tipoMensaje != null) {
%>
<div class="mensaje-contenedor">
    <div class="message-container <%= tipoMensaje %>">
        <%= mensaje %>
    </div>
    <div class="volver-menu">
        <a href="/TPINT_GRUPO_10_LAB4/servletTransferencia">
            <button type="button" class="button btn-volver">Volver</button>
        </a>
    </div> 
</div>
<% } %>


            <% 
                if (request.getAttribute("cuentaDestino") != null && request.getAttribute("clienteDestino") != null) { 
                    Cuenta cuentaDestino = (Cuenta) request.getAttribute("cuentaDestino");
                    Cliente clienteDestino = (Cliente) request.getAttribute("clienteDestino");
                    Cuenta cuentaActual = (Cuenta) request.getSession().getAttribute("cuenta");
            %>
    <div class="form-container">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletConfirmarTransferencia">
            
            <h2>Transferencia</h2>
            <div class="form-group">
                <label for="cliente">Cliente Destino</label>
                <input type="text" id="cliente" value="<%= clienteDestino.getNombre() + " " + clienteDestino.getApellido() %>" disabled>
            </div>
            <div class="form-group">
                <label for="cuenta">Número de Cuenta</label>
                <input type="text" id="cuenta" value="<%= cuentaDestino.getNumeroCuenta() %>" disabled>
            </div>
            <div class="form-group">
                <label for="cbu">CBU</label>
                <input type="text" id="cbu" value="<%= cuentaDestino.getCbu() %>" disabled>
            </div>
            
            <div class="form-group">
                <label for="montoDisponible">Monto disponible en cuenta</label>
                <input type="text" id="montoDisponible" value="<%= Formato.formatoMonetario(cuentaActual.getSaldo()) %>" disabled>
            </div>

            <div class="form-group">
                <label for="monto">Monto a transferir</label>
                <input type="text" id="monto" name="monto" placeholder="Ingrese el monto" required oninput="formatCurrency(this)">
            </div>

            <div class="form-group">
                <label for="concepto">Concepto</label>
                <input type="text" id="concepto" name="concepto" placeholder="Opcional">
            </div>

            <input type="hidden" name="CBUDestino" value="<%= cuentaDestino.getCbu() %>">
            <input type="submit" name="btnTransferir" class="button button-green" value="Transferir" onclick="return confirmarTransferencia()">
        </form>
            
	 <div class="volver-menu">
            <a href="/TPINT_GRUPO_10_LAB4/servletTransferencia">
                <button type="button" class="button btn-volver">Volver</button>
            </a>
        </div>
    </div>
            <% } %>
    <script>
        function formatCurrency(input) {
            let value = input.value.replace(/\D/g, '');
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
            input.value = '$ ' + value;
        }

        function confirmarTransferencia() {
            const monto = document.getElementById('monto').value;
            if (!monto || parseFloat(monto.replace(/[^\d]/g, '')) <= 0) {
                alert('Por favor, ingrese un monto válido.');
                return false;
            }
            return confirm('¿Está seguro de que desea realizar esta transferencia?');
        }
    </script>

</body>
</html>
