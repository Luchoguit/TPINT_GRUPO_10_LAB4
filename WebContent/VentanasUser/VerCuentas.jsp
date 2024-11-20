<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Datos de la Cuenta</title>

    <!-- Estilos -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloBotones.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloMensajes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/EstiloTabla.css">
</head>
<body>

    <div class="form-container">
        <h2 style="text-align: center;">Cuentas Disponibles</h2>

        <!-- Tabla de cuentas -->
        <table class="table">
            <thead>
                <tr>
                    <th>Acción</th>
                    <th>Saldo</th>
                    <th>Tipo de Cuenta</th>
                    <th>Número de Cuenta</th>
                    <th>CBU</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Cliente cliente = (Cliente) request.getAttribute("cliente");
                    List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("listaCuentas");

                    if (listaCuentas != null && !listaCuentas.isEmpty()) {
                        for (Cuenta cuenta : listaCuentas) {
                %>
                <tr>
                    <td>
                        <form method="post" action="/TPINT_GRUPO_10_LAB4/ServletVerCuentas">
                            <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                            <button type="submit" >Ver Cuenta</button>
                        </form>
                    </td>
                    <td><%= cuenta.getSaldo() %></td>
                    <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
                    <td><%= cuenta.getNumeroCuenta() %></td>
                    <td><%= cuenta.getCbu() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">No se encontraron cuentas asociadas.</td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <!-- Botón para regresar al menú -->
        <div class="volver-menu">
            <form method="post" action="/TPINT_GRUPO_10_LAB4/MENUS/IndexUser.jsp">
                <button type="submit" class="button button-blue">Regresar al Menú</button>
            </form>
        </div>
    </div>

</body>
</html>
