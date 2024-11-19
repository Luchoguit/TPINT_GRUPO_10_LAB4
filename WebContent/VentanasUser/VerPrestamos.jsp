<%@page import="entidad.Prestamo" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestionar Préstamos</title>
    <style>
    </style>
    
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
    
</head>
<body>

    <h2 style="text-align: center;">Préstamos Activos</h2>

    <table>
        <tr>
            <th>Número de Préstamo</th>
            <th>Cuotas Pagas</th>
            <th>Valor de la Cuota</th>
            <th>Monto Total</th>
            <th>Fecha del Préstamo</th>
            <th>Saldo Restante</th>
            <th>Acciones</th>
        </tr>
        	     	
        	     	
        	   	<% 
			    	List<Prestamo> listaPrestamos = (List<Prestamo>)request.getAttribute("listaPrestamos");
            		if (listaPrestamos != null) {
			        for (Prestamo prestamo : listaPrestamos) {
			    %>
        <tr>
            <td><%= prestamo.getIdPrestamo() %></td>
            <td>0 / <%= prestamo.getCantidadCuotas() %></td>
            <td>$<%= prestamo.getImporteMensual() %></td>
            <td>$<%= prestamo.getImportePedido() %></td>
            <td><%= prestamo.getFechaAlta() %></td>
            <td>$<%= prestamo.getImportePedido() %></td>
            <td><a href="/TPINT_GRUPO_10_LAB4/ServletPagarPrestamo">Pagar cuota</a></td>
        </tr>
        
         		<% } ;} %>
    </table>

    <div class="action-buttons">
        <a href="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo">Solicitar nuevo Préstamo</a>
    </div>
    
   	<div class="volver-menu">
    	 <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp" class="volver-menu">
			<input type="button" value="Volver a cuenta" class="btn-volver">
		</a>
     </div>

</body>
</html>