<%@page import="entidad.Cuenta" %>
<%@page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Pr�stamo</title>
    <style>
        .form-container {
            width: 80%;
            margin: 20px auto;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"], input[type="number"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .action-buttons {
            text-align: center;
            margin-top: 20px;
        }
        .action-buttons input[type="submit"] {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .action-buttons input[type="submit"]:hover {
            background-color: #45a049;
        }
        
         /* Estilos para el contenedor de mensajes */
        .message-container {
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
            font-weight: bold;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
    <script>
        function formatCurrency(input) {
            // Elimina caracteres que no sean d�gitos
            let value = input.value.replace(/\D/g, '');
            // Agregar punto cada 3 cifras
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
            input.value = '$ ' + value;
        }
    </script>
</head>
<body>

    <h2 style="text-align: center;">Solicitar Pr�stamo</h2>


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
		
    <div class="form-container">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo">
            <div class="form-group">
                <label for="monto">Monto del Pr�stamo:</label>
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
    <label for="cuenta">Cuenta de Dep�sito:</label>
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

            <div class="action-buttons">
                <input type="submit" value="Solicitar Pr�stamo">
            </div>
        </form>
    </div>

    <div class="action-buttons">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
            <input type="submit" name="btnRegresar" value="Regresar">
        </form>
    </div>

</body>
</html>