package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import utilidades.Mensaje;


@WebServlet("/ServletConfirmarTransferencia")
public class ServletConfirmarTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	if(request.getParameter("CBU") != null) {
	String cbu = (String) request.getParameter("CBU");
	
	CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
	Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorCBU(cbu);
	
	ClienteNegocio clienteNegocio = new ClienteNegocioImp();
	Cliente clienteDestino = clienteNegocio.obtenerPorId(cuentaDestino.getUsuario().getCliente().getId());
	
	request.setAttribute("cuentaDestino", cuentaDestino);
	request.setAttribute("clienteDestino", clienteDestino);
	}
    RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/ConfirmarTransferencia.jsp");
    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    if (request.getParameter("btnTransferir") != null) {
	        boolean error = false;
	        CuentaNegocioImp negocioImp = new CuentaNegocioImp();
	        Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuenta");
	        TipoMovimiento tipoMovimiento = new TipoMovimiento(4, "Transferencia");
	        String detalle = request.getParameter("concepto");
	        LocalDateTime fechaHora = LocalDateTime.now();
	        BigDecimal importe = BigDecimal.ZERO;

	        try {
	            // Leer el monto ya limpio (formato enviado desde el JSP: "50.99")
	            String montoStr = request.getParameter("monto");
	            importe = new BigDecimal(montoStr);

	            if (importe.compareTo(BigDecimal.ZERO) <= 0) {
	                error = true;
	                Mensaje.error(request, "Valor de transferencia inválido.");
	            }
	        } catch (NumberFormatException e) {
	            error = true;
	            Mensaje.error(request, "Formato de monto inválido.");
	        }

	        if (!error) {
	            String cbuDestino = request.getParameter("CBUDestino");
	            Cuenta cuentaDestino = negocioImp.obtenerCuentaPorCBU(cbuDestino);
	            Movimiento movimiento = new Movimiento(cuentaOrigen, tipoMovimiento, detalle, fechaHora, importe, cuentaDestino);
	            BigDecimal saldoActualizado = cuentaOrigen.getSaldo().subtract(importe);

	            if (saldoActualizado.compareTo(BigDecimal.ZERO) >= 0) {
	                if (negocioImp.realizarTransferencia(movimiento)) {
	                    Mensaje.exito(request, "Transferencia realizada exitosamente");
	                } else {
	                    Mensaje.error(request, "Ha ocurrido un error al transferir");
	                }
	            } else {
	                Mensaje.error(request, "Saldo insuficiente");
	            }
	        }

	        request.setAttribute("ValorInvalido", error ? "error" : "ok");
	    }

	    doGet(request, response);
	}



}
