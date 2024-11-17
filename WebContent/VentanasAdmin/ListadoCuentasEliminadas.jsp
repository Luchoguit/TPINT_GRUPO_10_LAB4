<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cuentas del cliente</title>

<style>
    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: collapse;
    }
    th, td {
        padding: 10px;
        text-align: left;
        border: 1px solid #ddd;
        white-space: nowrap;
        overflow-x: auto;
        max-width: 200px;
    }
    th {
        background-color: #f4f4f4;
    }
    .filter-container {
        text-align: center;
        margin: 20px;
    }
    .filter-container input[type="text"] {
        padding: 8px;
        width: 300px;
    }
    .filter-container input[type="submit"] {
        padding: 8px 12px;
    }
    .estado-verde {
        width: 20px;
        height: 20px;
        background-color: green;
        border-radius: 50%;
        display: inline-block;
    }
    .estado-rojo {
        width: 20px;
        height: 20px;
        background-color: red;
        border-radius: 50%;
        display: inline-block;
    }
    .leyenda {
        position: absolute;
        top: 20px;
        right: 20px;
        border: 1px solid #ddd;
        padding: 10px;
        background-color: #f9f9f9;
        border-radius: 5px;
    }
    .leyenda span {
        margin-right: 10px;
        vertical-align: middle;
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
</head>
<body>

<h2 style="text-align: center;">Cuentas de clientes deshabilitadas</h2>

<!-- Leyenda de colores -->
<div class="leyenda">
    <span class="estado-rojo"></span> Deshabilitado
    <br>
</div>

<div class="filter-container">
    <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">
        <input type="text" name="filtroCliente" placeholder="Ingrese DNI del titular , nombre o apellido">
        <input type="submit" name="btnFiltrar" value="Filtrar">
    </form>
</div>

<table>
    <tr>
        <th>Tipo de cuenta</th>
        <th>Numero de cuenta</th>
        <th>DNI Titular</th>
        <th>Nombre</th> <!-- Nueva celda para nombre -->
        <th>Apellido</th> <!-- Nueva celda para apellido -->
        <th>Movimientos</th>
        <th>Estado</th>
        <th>Deshabilitar</th>
    </tr>


  <%
        List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("cuentasFiltradas");
        List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("clientesFiltrados");  

        if (listaCuentas != null && listaClientes != null) {
           System.out.println("entramos al for");
            for (Cuenta cuenta : listaCuentas) {
                Cliente cliente = null;

                
                for (Cliente c : listaClientes) {
                    if (c.getId() == cuenta.getUsuario().getIdCliente()) {
                        cliente = c;
                        
                        break;
                    }
                }
    %>
    <tr>
        <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
        <td><%= cuenta.getNumeroCuenta() %></td>
        <td><%= cliente != null ? cliente.getDni() : "No encontrado" %></td>
        <td><%= cliente != null ? cliente.getNombre() : "No encontrado" %></td> <!-- Mostrar nombre -->
        <td><%= cliente != null ? cliente.getApellido() : "No encontrado" %></td> <!-- Mostrar apellido -->
        <td>
            <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletMovimientos">
                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                <input type="submit" name="btnMovimientos" value="+">
            </form>
        </td>
        <td>
            <% if (cuenta.isEstado()) { %>
                <span class="estado-verde"></span>
            <% } else { %>
                <span class="estado-rojo"></span>
            <% } %>
        </td>
        <td>
            <form onsubmit="return confirmarEliminacion()" method="post" action="/TPINT_GRUPO_10_LAB4/ServletListadoCuentasEliminadas">
                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                <input type="submit" name="btnHabilitar" value="Habilitar">
            </form>
        </td>
    </tr>
    <%
            }
        }
    %>


</table>

	<div class="volver-menu">
     <!-- Enlace para volver al menu -->
		<a href="/TPINT_GRUPO_10_LAB4/MENUS/IndexAdmin.jsp" class="volver-menu">
			<input type="button" value="Volver al Menu" class="btn-volver">
		</a>
     </div>

</body>
<script type="text/javascript">
        function confirmarEliminacion() {
            
            var respuesta = confirm("Estas seguro de que deseas habilitar esta cuenta?");
            return respuesta;  
        }
    </script>


</html>