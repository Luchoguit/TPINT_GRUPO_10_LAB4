<%@page import="java.util.Map" %>
<%@page import="entidad.Prestamo" %>
<%@page import="java.math.BigDecimal"%>
<%@page import="utilidades.Formato" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Gestionar Pr�stamos</title>

    <!-- Estilos -->
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="/TPINT_GRUPO_10_LAB4/CSS/EstiloTabla.css">
</head>
<body>

    <!-- Contenedor de la Tabla -->
    <div class="table-container">
    
    <!-- T�tulo de la P�gina -->
    <h2>Pr�stamos Activos</h2>
    
        <!-- Tabla de Pr�stamos -->
        <table>
            <thead>
                <tr>
                    <th>Codigo de Pr�stamo</th>
                    <th>Importe solicitado</th>
                    <th>Cuotas Pagas</th>
                    <th>Valor de la Cuota</th>
                    <th>Total a pagar</th>
                    <th>Fecha del Pr�stamo</th>
                    <th>Importe restante a pagar</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>		
                <% 
                    Map<Prestamo, Integer> prestamosCuotas = (Map<Prestamo, Integer>) request.getAttribute("prestamosCuotas");
                    if (prestamosCuotas != null) {
                        for (Map.Entry<Prestamo, Integer> entry : prestamosCuotas.entrySet()) {
                            Prestamo prestamo = entry.getKey();
                            int cuotasPagas = entry.getValue();
                            BigDecimal totalAbonado = prestamo.getImporteMensual().multiply(new BigDecimal(cuotasPagas));
                            BigDecimal saldoRestante = prestamo.getImporteFinal().subtract(totalAbonado);
                %>
                <tr>
                    <td><%= prestamo.getIdPrestamo() %></td>
                    <td><%= Formato.formatoMonetario(prestamo.getImportePedido()) %></td>
                    <td><%= cuotasPagas %> / <%= prestamo.getCantidadCuotas() %></td>
                    <td><%= Formato.formatoMonetario(prestamo.getImporteMensual()) %></td>
                    <td><%= Formato.formatoMonetario(prestamo.getImporteFinal()) %></td>
                    <td><%= Formato.formatoFecha(prestamo.getFechaAlta()) %></td>
                    <td><%= Formato.formatoMonetario(saldoRestante) %></td>
                    <td>
                        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletVerPrestamos">
                            <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                            <button type="submit">PAGAR CUOTA</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    }
                %>
            </tbody>
        </table>
    </div>

    <!-- Bot�n para Solicitar un Nuevo Pr�stamo -->
    <div class="action-buttons">
        <form action="/TPINT_GRUPO_10_LAB4/ServletPedirPrestamo" method="get">
            <input type="submit" value="Solicitar nuevo Pr�stamo" class="button button-green">
        </form>
    </div>

    <!-- Bot�n para Volver al Men� -->
    <div class="action-buttons">
        <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexCuenta.jsp">
            <button type="submit" class="button button-blue">Volver a cuenta</button>
        </form>
    </div>

</body>
</html>
