<%@page import="entidad.Cuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Préstamo</title>
    
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloBotones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloFormulario.css">
    
<script>
    function formatCurrency(input) {
        // Elimina caracteres que no sean dígitos
        let value = input.value.replace(/\D/g, '');
        // Agregar punto cada 3 cifras
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
        input.value = '$ ' + value;
    }
</script>
</head>
<body>

<div class="form-container">
    <h2>Solicitar Préstamo</h2>

    <!-- Contenedor de mensajes -->
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        String tipoMensaje = (String) request.getAttribute("tipoMensaje");
        if (mensaje != null && tipoMensaje != null) {
    %>
        <div class="message-container <%= tipoMensaje %>">
            <%= mensaje %>
        </div>
    <% } %>

    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo">
        <div class="form-group">
            <label for="monto">Monto del Préstamo:</label>
            <input type="text" id="monto" name="monto" required oninput="formatCurrency(this)">
        </div>

        <div class="form-group">
            <label for="cuotas">Cantidad de Cuotas:</label>
            <select id="cuotas" name="cuotas" required>
                <option value="6">6 Cuotas</option>
                <option value="12">12 Cuotas</option>
                <option value="18">18 Cuotas</option>
                <option value="24">24 Cuotas</option>
            </select>
        </div>

        <div class="form-group">
            <label for="plazo">Plazo de pago:</label>
            <select id="plazo" name="plazo" required>
                <option value="6">6 meses</option>
                <option value="12">12 meses</option>
                <option value="18">18 meses</option>
                <option value="24">24 meses</option>
            </select>
        </div>

        <div class="form-group">
            <label for="cuenta">Cuenta de Depósito:</label>
            <select id="cuenta" name="cuenta" required>
                <%
                    List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
                    if (listaCuentas != null) {
                        for (Cuenta cuenta : listaCuentas) {
                            String descripcion = cuenta.getTipoCuenta().getDescripcion();
                            String numeroCuenta = cuenta.getNumeroCuenta();
                %>
                            <option value="<%= cuenta.getId() %>">
                                <%= descripcion %> - <%= numeroCuenta %>
                            </option>
                <%
                        }
                    } else {
                %>
                    <option value="">No hay cuentas disponibles</option>
                <%
                    }
                %>
            </select>
        </div>

        <!-- Botón principal: Solicitar Préstamo -->
        <div class="form-buttons">
           <button type="submit" class="button button-green">Solicitar Préstamo</button>       
        </div>
    </form>

    <!-- Botón secundario: Regresar -->
    <div class="volver-menu">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
           <button type="submit" class="button button-blue">Volver al Menú</button>
        </form>
    </div>
</div>

</body>
</html>
