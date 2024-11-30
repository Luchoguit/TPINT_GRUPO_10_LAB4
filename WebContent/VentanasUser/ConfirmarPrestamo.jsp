<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Prestamo" %>
<%@page import="java.math.BigDecimal" %>
<%@page import="utilidades.Formato" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Pr�stamo</title>
    
<link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
<link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
<link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloFormulario.css">
    
</head>
<body>

<div class="form-container">
    <h2>Confirmar Pr�stamo</h2>

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

    <% 
        Prestamo prestamo = (Prestamo) request.getAttribute("prestamo");
        if (prestamo != null) {
    %>

    <!-- Formulario -->
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletConfirmarPrestamo">
        <div class="form-group">
            <label for="monto">Monto del Pr�stamo:</label>
            <span><%= Formato.formatoMonetario(prestamo.getImportePedido()) %></span>
        </div>
    
        <div class="form-group">
            <label for="plazo">Plazo de pago:</label>
            <span><%= prestamo.getPlazoMeses() %></span>
        </div>
    
        <div class="form-group">
            <label for="cuotas">Cantidad de Cuotas:</label>
            <span><%= prestamo.getCantidadCuotas() %></span>
        </div>
    
        <div class="form-group">
            <label for="cuenta">Numero de cuenta de Dep�sito:</label>
            <span><%= prestamo.getCuenta().getNumeroCuenta() %></span>
        </div>
    
        <div class="form-group">
            <label for="monto">Total a pagar:</label>
            <span><%= Formato.formatoMonetario(prestamo.getImporteFinal()) %></span>
        </div>
    
        <div class="form-group">
            <label for="monto">Monto por cuota:</label>
            <span><%= Formato.formatoMonetario(prestamo.getImporteMensual()) %></span>
        </div>

        <!-- Bot�n Solicitar Pr�stamo -->
        <div class="form-buttons">
           <button type="submit" class="button button-green">Solicitar Pr�stamo</button>       
        </div>
    </form>

    <% } %>

    <!-- Bot�n Regresar  -->
    <div class="volver-menu">
        <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo">
           <button type="submit" class="button button-blue">Volver</button>
        </form>
    </div>
</div>

</body>
</html>
