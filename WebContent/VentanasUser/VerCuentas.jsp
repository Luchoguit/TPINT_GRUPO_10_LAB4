<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Datos de la cuenta</title>
    <style>
    </style>
    
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">

</head>
<body>

	<h2 style="text-align: center;">Cuentas disponibles</h2>

	
	<table>
	    <tr>
	        <th></th>
	        <th>Saldo</th>
	        <th>Tipo de cuenta</th>
	        <th>Numero de cuenta</th>
	        <th>CBU</th>
	    </tr>
	     		<% 
	     			Cliente cliente = (Cliente)request.getAttribute("cliente");
			    	List<Cuenta> listaCuentas = (List<Cuenta>)request.getAttribute("listaCuentas");
            		if (listaCuentas != null) {
			        for (Cuenta cuenta : listaCuentas) {
			    %>
	    <tr>
	        <td>
       		 <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletVerCuentas">
           		 <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
          		  <input type="submit" value="Ver Cuenta">
       		 </form>
  			</td>
	        <td><%= cuenta.getSaldo() %></td>
	        <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
	        <td><%= cuenta.getNumeroCuenta() %></td>
	        <td><%= cuenta.getCbu() %></td>
	    </tr>
 				<% } 
			        } else {System.out.println("Las cuentas no llegaron correctamente.");}
			    %>
	</table>

	<div style="text-align: center;">
    	<form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp">
        	<input type="submit" name="btnRegresar" value="Regresar">
    	</form>
	</div>

</body>
</html>