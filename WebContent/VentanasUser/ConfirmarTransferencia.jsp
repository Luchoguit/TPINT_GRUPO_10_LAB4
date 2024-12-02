<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transferir</title>
</head>
<body>

<div>
<h3>Cuentas Destino</h3>
<table border="1">
    <tr>
        <th>Cliente</th>
        <th>Número de Cuenta</th>
        <th>CBU</th>
    </tr>
    <% 
    Cuenta cuentaDestino = (Cuenta) request.getAttribute("cuentaDestino");
    Cliente clienteDestino = (Cliente) request.getAttribute("clienteDestino");
    %>
        <tr>
            <td><%= clienteDestino.getNombre() + " " + clienteDestino.getApellido() %></td>
            <td><%= cuentaDestino.getNumeroCuenta() %></td>
            <td><%= cuentaDestino.getCbu() %></td>
        </tr>
</table>


  <form  method="post" action="/TPINT_GRUPO_10_LAB4/servletConfirmarTransferencia" style="display: flex; align-items: center; gap: 10px; ">
	   <div id="formularioMonto" style="display: none; margin-top: 15px; margin-left: 100px">
        <label for="monto">Monto $</label>
        <input type="text" id="monto" name="monto" placeholder="Ingrese el monto" required oninput="formatCurrency(this)">
        <label>Concepto </label>
        <input type="text" id="concepto" name="concepto" placeholder="Opcional">
        <input type="hidden" name="CBUDestino" value="<%= cuentaDestino != null ? cuentaDestino.getCbu() : 0 %>">
        <br><br>
        <input type="submit" name="btnTransferir" value="Aceptar" onclick="confirmarTransferencia()">
    </div>
	</form>
</div>


<script>

    function aceptarTransferencia() {
        const monto = document.getElementById("monto").value;
        alert("Transferencia aceptada por $" + monto);
        
    }
    
    
	function soloNumeros(event) {
	    var key = event.keyCode || event.which;
	    var tecla = String.fromCharCode(key);
	    var regex = /^[0-9]$/;  
	    if (!regex.test(tecla)) {
	        event.preventDefault(); 
	    }
	}
    
	 function confirmarTransferencia() {
	        const monto = document.getElementById('monto').value;
	        if (!monto || parseFloat(monto) <= 0) {
	            alert('Por favor, ingrese un monto válido.');
	            return false; // Evita el envío si el monto no es válido
	        }
	        return confirm('¿Está seguro de que desea realizar esta transferencia?');
	    }
	
	
</script>
</body>
</html>