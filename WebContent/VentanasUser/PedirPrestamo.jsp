<%@page import="entidad.Cuenta" %>
<%@page import="java.util.List" %>

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
<body onload="actualizarCuotas()">

<div class="form-container">
    <h2>Solicitar Pr�stamo</h2>

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
            <label for="monto">Monto del Pr�stamo:</label>
            <input type="text" id="monto" name="monto" required oninput="formatCurrency(this)">
        </div>

		<div class="form-group">
		    <label for="plazo">Plazo de pago:</label>
		    <select id="plazo" name="plazo" required onchange="actualizarCuotas()">
		        <option value="6" selected>6 meses</option>
		        <option value="12">12 meses</option>
		        <option value="18">18 meses</option>
		        <option value="24">24 meses</option>
		    </select>
		</div>
		
		<div class="form-group">
		    <label for="tasa-interes" style="display: inline;">Tasa de inter�s aplicada:</label>
		    <span id="tasa-interes" style="font-weight: bold; color: #333;">5%</span>
		</div>
		
		<div class="form-group">
		    <label for="cuotas">Cantidad de Cuotas:</label>
		    <select id="cuotas" name="cuotas" required>
                <option value="">Debe seleccionar el plazo de pago primero</option>
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

        <!-- Bot�n principal: Solicitar Pr�stamo -->
        <div class="form-buttons">
           <button type="submit" class="button button-green">Calcular Pr�stamo</button>       
        </div>
    </form>

    <!-- Bot�n secundario: Regresar -->
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
           <button type="submit" class="button button-blue">Volver al Men�</button>
        </form>
</div>

<script>
    function formatCurrency(input) {
        let value = input.value.replace(/\D/g, '');
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
        input.value = '$ ' + value;
    }

    function actualizarCuotas() {
        const cuotasSelect = document.getElementById("cuotas");
        const plazo = parseInt(document.getElementById("plazo").value);
        const tasaInteresDiv = document.getElementById("tasa-interes");

        // Mostrar la tasa de inter�s seg�n el plazo seleccionado
        const tasasInteres = {
            6: "5%",
            12: "10%",
            18: "15%",
            24: "20%"
        };

        if (tasasInteres[plazo]) {
            tasaInteresDiv.textContent = tasasInteres[plazo];
        } else {
            tasaInteresDiv.textContent = "N/A";
        }

        // Verificar que el plazo sea v�lido antes de modificar cuotas
        if (!plazo || isNaN(plazo)) {
            cuotasSelect.innerHTML = '<option value="">Debe seleccionar el plazo de pago primero</option>';
            return;
        }

        // Opciones de cuotas seg�n el plazo
        const opcionesCuotas = {
            6: [6],
            12: [6, 12],
            18: [6, 12, 18],
            24: [6, 12, 18, 24]
        };

        // Limpiar las opciones actuales
        cuotasSelect.innerHTML = "";

        // Agregar las nuevas opciones de cuotas
        opcionesCuotas[plazo].forEach(cuota => {
            const option = document.createElement("option");
            option.value = cuota;
            option.text = cuota + " Cuotas";
            cuotasSelect.add(option);
        });

        // Seleccionar la primera opci�n por defecto (si existen opciones)
        cuotasSelect.selectedIndex = 0;
    }
    
    
</script>
</body>
</html>