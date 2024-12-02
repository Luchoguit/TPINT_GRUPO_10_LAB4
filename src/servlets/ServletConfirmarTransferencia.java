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
		
		if(request.getParameter("btnTransferir") != null)
		{
			boolean error = false;
			
			CuentaNegocioImp negocioImp = new CuentaNegocioImp();
			
			Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuenta");
			
			TipoMovimiento tipoMovimiento = new TipoMovimiento(4, "Transferencia");
			
			String detalle = request.getParameter("concepto");
			LocalDateTime fechaHora = LocalDateTime.now();
			BigDecimal importe = new BigDecimal(request.getParameter("monto").replaceAll("[^0-9]", ""));
			
			//validacion transferencia = 0
			if (importe.compareTo(BigDecimal.ZERO) == 0 || importe.compareTo(BigDecimal.ZERO) < 0) {
				System.out.println("[DEBUG] transferencia invalida");
	            Mensaje.error(request, "Valor de transferencia invalido.");	            

				request.setAttribute("ValorInvalido", "error");
				error = true;
			}
			
			//validacion transferencia numero invalido
			String importeStr = request.getParameter("monto").toString();
			if (importeStr.startsWith("0")  || importeStr.startsWith("0.") ) {
				System.out.println("[DEBUG] transferencia invalida");
	            Mensaje.error(request, "Valor de transferencia invalido.");	            
				request.setAttribute("ValorInvalido", "error");
				error = true;
			}
			
			
			if (error == false) {
				String cbuDestino = (String) request.getSession().getAttribute("cbuDestino");
				Cuenta cuentaDestino = negocioImp.obtenerCuentaPorCBU(cbuDestino);
				
				Movimiento movimiento = new Movimiento(1, cuentaOrigen, tipoMovimiento, detalle, fechaHora, importe, cuentaDestino);
				
				BigDecimal saldoActualizado = movimiento.getCuentaOrigen().getSaldo().subtract(movimiento.getImporte());
				
				if(saldoActualizado.floatValue() >= 0.00)
				{
					if(negocioImp.realizarTransferencia(movimiento))
					{	
			            Mensaje.exito(request,  "Transferencia realizada exitosamente");	            

					}
					else
					{
			            Mensaje.error(request,  "Ha ocurrido un error al transferir");	            
					}
				}
				else 
				{
		            Mensaje.error(request, "Saldo insuficiente");	            

				}
			}
			
			
		}	
		
		doGet(request, response);
	}

}
