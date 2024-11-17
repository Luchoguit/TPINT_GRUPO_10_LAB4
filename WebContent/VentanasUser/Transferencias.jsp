<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>
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

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
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
</head>
<body>

<%Cuenta cuenta = null;
	Cuenta cuentaDestino = null;
	Usuario user = null;
	if (request.getAttribute("Cuenta") != null){
		cuenta = (Cuenta) request.getSession().getAttribute("cuenta");
		cuentaDestino = (Cuenta) request.getSession().getAttribute("cuentaDestino");
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
<div class="container" style="margin-left: 100px">

	


    <form  method="post" action="/TPINT_GRUPO_10_LAB4/servletTransferencia" style="display: flex; align-items: center; gap: 10px;">
        <h2 style="text-align: center; margin: 10;">CBU </h2>
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
			System.out.println("CBU de cuenta actual: " + cuenta.getCbu());
			
			if(cbuIngresado.equals(cuenta.getCbu()))
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
                    <th>Nombre Usuario</th>
                    <th>ID Usuario</th>
                    <th>ID Cuenta</th>
                    <th>CBU</th>
                    <th>Transferir</th>
                </tr>
            </thead>
            <tbody>
					
	

					
                	<tr>
					    <td><%= cuenta != null && cuentaDestino.getUsuario() != null && cuentaDestino.getUsuario().getNombreUsuario() != null && mismaCuenta == false
					             ? cuentaDestino.getUsuario().getNombreUsuario() 
					             : "--" %></td>
					    <td><%= cuenta != null && cuentaDestino.getUsuario() != null && mismaCuenta == false
					             ? cuentaDestino.getUsuario().getIdCliente() 
					             : "--" %></td>
					    <td><%= cuenta != null && mismaCuenta == false
					             ? cuentaDestino.getId() 
					             : "--" %></td>
					    <td><%= cuentaDestino != null && cuentaDestino.getCbu() != null && mismaCuenta == false
					             ? cuentaDestino.getCbu() 
					             : "--" %></td>
					      	<td><form onsubmit="mostrarFormulario(event)">
			                    <input type="hidden" name="CBU" value="<%= cuentaDestino != null  ? cuentaDestino.getCbu() : 0%>">
			                    <% if (cuentaDestino != null && cuentaDestino.getCbu() != null && mismaCuenta == false) { %>
        							<button type="button" onclick="mostrarFormularioTransferencia()">Transferencia</button>
    							<% } %>
		                		</form>
		                	</td>
           				 
					</tr>
            </tbody>
        </table>
 
  <form  method="post" action="/TPINT_GRUPO_10_LAB4/servletTransferencia" style="display: flex; align-items: center; gap: 10px;">
	   <div id="formularioMonto" style="display: none; margin-top: 15px; margin-left: 100px">
		    <label for="monto">Monto $</label>
		    <input type="number" id="monto" name="monto" placeholder="Ingrese el monto">
		    <label>Concepto </label>
		    <input type="text" id="concepto" name="concepto" placeholder="Opcional">
		    <input type="hidden" name="CBUDestino" value="<%= cuenta != null  ? cuentaDestino.getCbu() : 0%>">
		    <br>
		    <br>
		    <input type="submit" name="btnTransferir" value="Aceptar">
		</div>
	</form>
	
	 <div class="volver-menu">
    	 <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp" class="volver-menu">
			<input type="button" value="Volver a cuenta" class="btn-volver">
		</a>
     </div>
	
	
</div>
<script>
    function mostrarFormularioTransferencia() {
        event.preventDefault(); // Evita el envío del formulario
        document.getElementById("formularioMonto").style.display = "block"; // Muestra el formulario de monto
    }

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
    
</script>

</body>
</html>