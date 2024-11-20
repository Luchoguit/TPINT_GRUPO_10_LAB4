<%@page import="entidad.Cliente" %>
<%@page import="entidad.TipoCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de Cuenta</title>

<!-- Estilos -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloBotones.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloFormulario.css">

</head>
<body>

<div class="form-container">
    <h1>Alta de Cuenta</h1> 

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

    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletSolicitarAltaCuenta">
        <% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>

        <!-- Información del cliente -->
        <div class="form-group">
            <label for="dniUsuario">DNI Usuario</label>
            <input type="number" min="1" step="1" name="dniUsuario" class="readonly-input" readonly 
            value="<%= cliente != null ? cliente.getDni() : "" %>" >
            <label for="apellidoUsuario">Apellido:</label>
            <input type="text" name="apellidoUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getApellido() : "" %>" >
            <label for="nombreUsuario">Nombre:</label>
            <input type="text" name="nombreUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getNombre() : "" %>" >
        </div>

        <!-- Selección de tipo de cuenta -->
        <% List<TipoCuenta> listaTC = (List<TipoCuenta>) request.getAttribute("listaTC"); %>
        <div class="form-group">
            <label for="tipoCuenta">Tipo de cuenta:</label>
            <select id="tipoCuenta" name="tipoCuenta" required>
                <option value="">Seleccione...</option>
                <% if (listaTC != null) {
                    for (TipoCuenta tipoCuenta : listaTC) { %>
                        <option value="<%= tipoCuenta.getId() %>"><%= tipoCuenta.getDescripcion() %></option>
                <% } } %>
            </select>
        </div>

        <!-- Botón principal: Enviar Solicitud -->
        <div class="form-buttons">
            <button type="submit" class="button button-green">Enviar Solicitud</button>
        </div>
    </form>

    <!-- Botón secundario: Volver al Menú -->
    <div class="volver-menu">
        <form method="get" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp">
            <button type="submit" class="button button-blue">Volver al Menú</button>
        </form>
    </div>
</div>

</body>
</html>
