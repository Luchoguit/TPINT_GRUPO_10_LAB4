<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Solicitar Préstamo</title>
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
    </style>
    <script>
        function formatCurrency(input) {
            // Elimina caracteres que no sean dígitos
            let value = input.value.replace(/\D/g, '');
            // Agregar punto cada 3 cifras
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
            input.value = '$ ' + value;
        }
    </script>
</head>
<body>

    <h2 style="text-align: center;">Solicitar Préstamo</h2>

    <div class="form-container">
        <form method="post" action="">
            <div class="form-group">
                <label for="monto">Monto del Préstamo:</label>
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
                <label for="cuenta">Cuenta de Depósito:</label>
                <select id="cuenta" name="cuenta" required>
                    <option value="2245567880">Caja de Ahorro - 2245567880</option>
                    <option value="2245567881">Cuenta Sueldo - 2245567881</option>
                    <option value="2245567882">Cuenta de Ahorro - 2245567882</option>
                    <option value="2245567883">Cuenta Corriente - 2245567883</option>
                </select>
            </div>

            <div class="action-buttons">
                <input type="submit" value="Solicitar Préstamo">
            </div>
        </form>
    </div>

    <div class="action-buttons">
        <form method="post" action="servletPrestamos">
            <input type="submit" name="btnRegresar" value="Regresar">
        </form>
    </div>

</body>
</html>