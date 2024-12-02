<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>
<%@page import="entidad.Cliente" %>
<%@page import="utilidades.Formato" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar CBU</title>
<style>        
        .container {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .column {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }

        label {
            font-weight: bold;
            margin-right: 10px;
        }

        .form-group input {
            width: 100%;
            box-sizing: border-box;
        }

        .form-group button {
            margin-left: 10px;
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

        .form-group .button-container {
            margin-top: 20px;
            display: flex;
            justify-content: center;
        }
        
      .volver-menu {
		    display: flex;               
		    justify-content: center;     
		    align-items: center;                     
		}
        
     .btn-volver {
		    background-color: #007bff;   
		    color: white;                
		    padding: 10px 20px;          
		    border: none;                
		    border-radius: 5px;          
		    font-size: 16px;             
		    cursor: pointer;            
		    transition: background-color 0.3s ease;  
		}
		
		.btn-volver:hover {
		    background-color: #0056b3;   
		 }
    </style>
    
    
        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
        <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">

<script>
  
	function soloNumeros(event) {
	    var key = event.keyCode || event.which;
	    var tecla = String.fromCharCode(key);
	    var regex = /^[0-9]$/;  
	    if (!regex.test(tecla)) {
	        event.preventDefault(); 
	    }
	}
</script>

</head>
<body>

<%
	Cuenta cuentaActual = (Cuenta) request.getSession().getAttribute("cuenta");
	Cuenta cuentaDestino = null;
	if (request.getAttribute("cuentaDestino") != null){
		cuentaDestino = (Cuenta) request.getAttribute("cuentaDestino");
	}
	Cliente clienteDestino = null;
	if (request.getAttribute("clienteDestino") != null){
		clienteDestino = (Cliente) request.getAttribute("clienteDestino");
	}
%>

<h2 style="text-align: center;">Transferir</h2>

		<% 
		    String mensaje = (String) request.getAttribute("mensaje");
		    String tipoMensaje = (String) request.getAttribute("tipoMensaje");
		    if (mensaje != null && tipoMensaje != null) {
		%>
		    <div class="message-container <%= tipoMensaje %>">
		        <%= mensaje %>
		    </div>
		<% } %>

<div>
<h3>Cuentas Propias</h3>
<table border="1">
    <tr>
        <th>Cliente</th>
        <th>Número de Cuenta</th>
        <th>CBU</th>
		<th>Transferir</th>
    </tr>
    <% 
    List<Cuenta> cuentasPropias = (List<Cuenta>) request.getAttribute("cuentasPropias");
    Cliente clienteActual = (Cliente) request.getAttribute("clienteSesion");
    for (Cuenta cuentaPropia : cuentasPropias) {
    %>
        <tr>
            <td><%= clienteActual.getNombre() + " " + clienteActual.getApellido() %></td>
            <td><%= cuentaPropia.getNumeroCuenta() %></td>
            <td><%= cuentaPropia.getCbu() %></td>
            <td>
					<form method="get" action="/TPINT_GRUPO_10_LAB4/ServletConfirmarTransferencia">
			        <input type="hidden" name="CBU" value="<%= cuentaPropia.getCbu()%>">
		       		<input type="submit" value="Transferir" class="btn-succes">
		          	</form>            
		    </td>
        </tr>
    <% } %>
</table>
		
<h3>Destinatarios recientes:</h3>
<table border="1">
    <tr>
        <th>Cliente</th>
        <th>Número de Cuenta</th>
        <th>CBU</th>
		<th>Transferir</th>
    </tr>
    <% 
    Map<Cuenta, Cliente> cuentasConClientes = (Map<Cuenta, Cliente>) request.getAttribute("cuentasConClientes");
    if (cuentasConClientes != null) {
        for (Map.Entry<Cuenta, Cliente> entry : cuentasConClientes.entrySet()) {
            Cuenta cuentaReciente = entry.getKey();
            Cliente clienteReciente = entry.getValue();
    %>
            <tr>
                <td><%= clienteReciente.getNombre() + " " + clienteReciente.getApellido() %></td>
                <td><%= cuentaReciente.getNumeroCuenta() %></td>
                <td><%= cuentaReciente.getCbu() %></td>
		      	<td>
					<form method="get" action="/TPINT_GRUPO_10_LAB4/ServletConfirmarTransferencia">
			        <input type="hidden" name="CBU" value="<%= cuentaReciente.getCbu()%>">
					<input type="submit" value="Transferir" class="btn-succes">
		          	</form> 
             	</td>
            </tr>
    <% 
        } 
    } 
    %>
    
    </table>       
    
        <div class="container">
    <form  method="post" action="/TPINT_GRUPO_10_LAB4/servletTransferencia" style="display: flex; align-items: center; gap: 10px;">
        <h3 style="text-align: center; margin: 10;">Buscar por CBU: </h3>
        <input type="text" name="inputCBU" onkeypress="soloNumeros(event)" placeholder="Ingrese CBU a transferir" style="margin-left: 5px; width: 250px;">
        <input type="submit" name="btnCBU" value="Buscar" style="margin-left: 5px">
    </form>
</div>
		
		<% 
		boolean mismaCuenta = false;
		if(request.getParameter("inputCBU") != null)
		{
			String cbuIngresado = request.getParameter("inputCBU");
			System.out.println("CBU Ingresado: " + cbuIngresado);
			System.out.println("CBU de cuenta actual: " + cuentaActual.getCbu());
			
			if(cbuIngresado.equals(cuentaActual.getCbu()))
			{
				System.out.println("Si vez este mensaje son la misma cuenta");
				mismaCuenta = true;
			}

			else{
				System.out.println("Si vez este mensaje no deberian ser la misma cuenta");
			}
		}

		%>
		
 
        <!-- Tabla de datos del CBU -->
        <table>
            <thead>
                <tr>
                    <th>Cliente</th>
                    <th>Numero de Cuenta</th>
                    <th>CBU</th>
                    <th>Transferir</th>
                </tr>
            </thead>
            <tbody>
                	<tr>
					    <td><%= clienteDestino != null && clienteDestino.getNombre() != null && clienteDestino.getApellido() != null && mismaCuenta == false
					             ? clienteDestino.getNombre() + " " + clienteDestino.getApellido() 
					             : "--" %></td>
					    <td><%= cuentaDestino != null && mismaCuenta == false
					             ? cuentaDestino.getNumeroCuenta() 
					             : "--" %></td>
					    <td><%= cuentaDestino != null && cuentaDestino.getCbu() != null && mismaCuenta == false
					             ? cuentaDestino.getCbu() 
					             : "--" %></td>
					   	<td>
						   	<form method="get" action="/TPINT_GRUPO_10_LAB4/ServletConfirmarTransferencia">
					            <input type="hidden" name="CBU" value="<%= cuentaDestino != null  ? cuentaDestino.getCbu() : 0%>">
					            <% if (cuentaDestino != null && cuentaDestino.getCbu() != null && mismaCuenta == false) { %>
		        				<input type="submit" value="Transferir" class="btn-green">
		    					<% } %>
			                </form>
		               	</td>
           				 
					</tr>
            </tbody>
        </table>    
	
	 <div class="volver-menu">
    	 <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp" class="volver-menu">
			<input type="button" value="Volver a cuenta" class="btn-volver">
		</a>
     </div>
	
	
</div>
</body>
</html>