<%@page import="entidad.Cliente" %>
<%@page import="entidad.TipoCuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de cuenta</title>
<style>        
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloFormulario.css">
    
</head>
<body>
	
	<div class="form-container">
		<h1>Alta Cuenta</h1>	 
		
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

	 	<div class="form-group">
	 		<label for="dniUsuario">DNI Usuario</label>
            <input type="number" min="1" step="1" name="dniUsuario" class="readonly-input" readonly 
            value="<%= cliente != null ? cliente.getDni() : "" %>" >
            <label for="apellidoUsuario">Apellido: </label>
            <input type="text" name="apellidoUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getApellido() : "" %>" >
            <label for="nombreUsuario">Nombre: </label>
            <input type="text" name="nombreUsuario" class="readonly-input" readonly
            value="<%= cliente != null ? cliente.getNombre() : "" %>" >
	 	</div>
	 
	 <% List<TipoCuenta> listaTC = (List<TipoCuenta>)request.getAttribute("listaTC"); %>
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
        
         <div class="form-group">
            <input type="submit" value="Enviar solicitud">
        </div>
        
        <!-- Para posible manejo de error -->
        <!-- El usuario solo podra tener 3 cuentas activas -->
        <label> </label>
        
         </form>
         
         <div class="volver-menu">
         	 <!-- Enlace para volver al menu -->
		    <a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp" class="volver-menu">
		        <input type="button" value="Volver al Menu" class="btn-volver">
		    </a>
         </div>

         
	 </div>
	 

	
</body>


</html>