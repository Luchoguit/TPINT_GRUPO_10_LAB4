<%@page import="entidad.Prestamo" %>
<%@page import="entidad.Cuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Pagar Préstamo</title>
    <style>
        td {
            text-align: center;
            vertical-align: middle;
        }

        .quantity-selector {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 5px;
        }

        .quantity-selector button {
            width: 30px;
            height: 30px;
            font-size: 18px;
            text-align: center;
            background-color: #f0f0f0;
            border: 1px solid #ccc;
            border-radius: 4px;
            cursor: pointer;
        }

        .quantity-selector button:hover {
            background-color: #e0e0e0;
        }

        .quantity-selector input {
            width: 50px;
            text-align: center;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
</head>

<body>

    <h2 style="text-align: center;">Pagar Préstamo</h2>
    
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
    <table>
        <thead>
            <tr>
                <th>Cuotas Pagas</th>
                <th>Valor de la Cuota</th>
                <th>Monto Total</th>
                <th>Fecha Préstamo</th>
                <th>Cuenta</th>
                <th>Saldo Disponible</th> 
                <th>Cantidad de cuotas a pagar:</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
            <% 
                int cantidadCuotasPagas = (int)request.getAttribute("cantidadCuotasPagas");
                int cantidadImpagas = (int)request.getAttribute("cantidadImpagas");
                Prestamo prestamo = (Prestamo)request.getAttribute("prestamo");
                if (prestamo != null) {
            %>
            <tr>
                <td><%= cantidadCuotasPagas %> / <%= prestamo.getCantidadCuotas() %></td>
                <td>$<%= prestamo.getImporteMensual() %></td>
                <td>$<%= prestamo.getImportePedido() %></td>
                <td><%= prestamo.getFechaAlta() %></td>
                <td>
                    <select name="cuenta" required onchange="updateSaldoDisponible(this)">
                        <%
                            List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
                            if (listaCuentas != null) {
                            	boolean primeraCuenta = true;
                                for (Cuenta cuenta : listaCuentas) {
                                    String descripcion = cuenta.getTipoCuenta().getDescripcion();
                                    String numeroCuenta = cuenta.getNumeroCuenta();
                        %>
                        <option value="<%= cuenta.getId() %>" 
                data-saldo="<%= cuenta.getSaldo() %>"
                <%= primeraCuenta ? "selected" : "" %> >
                            <%= descripcion %> - <%= numeroCuenta %>
                        </option>
                        <% 
                        primeraCuenta = false;
                                }
                            }
                        %>
                    </select>
                </td>
                 <td id="saldoDisponible">$0.00</td> 
                <td>
                    <div class="quantity-selector">
                        <button type="button" onclick="adjustQuantity(-1)">-</button>
                        <input type="number" name="cantidadCuotas" id="cantidadCuotas" value="1" min="1" max="<%= cantidadImpagas %>">
                        <button type="button" onclick="adjustQuantity(1)">+</button>
                    </div>
                </td>
                <td>
                    <input type="submit" value="Pagar Cuota" class="button button-green">
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</form>

    <!-- Botón para Regresar -->
    <div class="action-buttons">
        <form method="get" action="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">
            <input type="submit" name="btnRegresar" value="Regresar" class="button button-blue">
        </form>
    </div>

    <script>
	    function adjustQuantity(change) {
	        const input = document.getElementById('cantidadCuotas');
	        const newValue = parseInt(input.value) + change;
	        if (newValue >= parseInt(input.min) && newValue <= parseInt(input.max)) {
	            input.value = newValue;  // Actualiza directamente el input
	        }
	    }
		
	    
	    function updateSaldoDisponible(selectElement) {

			//obtenemos el saldo de la cuenta seleccionada
	        var saldo = selectElement.options[selectElement.selectedIndex].getAttribute('data-saldo');
	        
	        // Actualizamos la tabla con el valor obtenido
	        document.getElementById('saldoDisponible').textContent = "$" + saldo;
	    }
	    
	    window.onload = function() {

			// Al cargar la pagina, obtiene el valor de la primera cuenta seleccionada
	        var selectElement = document.querySelector('select[name="cuenta"]');
	        
	        // Llamamos a la función para establecer el saldo por defecto
	        updateSaldoDisponible(selectElement);
	    }
    </script>

</body>

</html>
