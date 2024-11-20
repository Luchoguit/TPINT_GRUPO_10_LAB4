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

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.CuentaNegocio;
import negocioimplementacion.CuentaNegocioImp;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnCBU") != null) {
			
			String cbu = request.getParameter("inputCBU");
			CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
			Cuenta cuenta = new Cuenta();
			
			boolean existeCuentaConCbu = cuentaNegocio.existeCuentaConCbu(cbu); 
			
			if(request.getSession().getAttribute("cuenta") != null)
			{
				System.out.println("[DEBUG] Llego una cuenta");
				Cuenta cuentaCliente = (Cuenta) request.getSession().getAttribute("cuenta");
			}
			
			Cuenta cuentaCliente = (Cuenta) request.getSession().getAttribute("cuenta");
			
			
			if(existeCuentaConCbu == false)
			{
				System.out.println("[DEBUG] CBU Invalido");
				request.setAttribute("mensaje", "Debe seleccionar un cbu Valido");
	            request.setAttribute("tipoMensaje", "error");
			}
			else {
				System.out.println("[DEBUG] CBU valido");
			}
			
			if(cuentaCliente.getCbu().equals(cbu))
			{
				System.out.println("[DEBUG] Son el mismo CBU");
				request.setAttribute("mensaje", "No puede transferir a la misma cuenta en uso");
	            request.setAttribute("tipoMensaje", "error");
				request.setAttribute("mismaCuenta", "error");
				
			}
			else {
				System.out.println("[DEBUG] Son distintos CBU");
				
			}

			cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbu);
			Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorCBU(cbu);
			request.getSession().setAttribute("cuentaDestino", cuentaDestino);
			
			request.getSession().setAttribute("cbuDestino", cbu);
			
			if (cuenta != null) {
				request.setAttribute("Cuenta", cuenta);
			}

		}
		
		
		if(request.getParameter("btnTransferir") != null)
		{
			boolean error = false;
			
			CuentaNegocioImp negocioImp = new CuentaNegocioImp();
			
			Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuenta");
			
			TipoMovimiento tipoMovimiento = new TipoMovimiento(4, "Transferencia");
			
			String detalle = request.getParameter("concepto");
			LocalDateTime fechaHora = LocalDateTime.now();
			BigDecimal importe = new BigDecimal(request.getParameter("monto"));
			
			//validacion transferencia = 0
			if (importe.compareTo(BigDecimal.ZERO) == 0 || importe.compareTo(BigDecimal.ZERO) < 0) {
				System.out.println("[DEBUG] transferencia invalida");
				request.setAttribute("mensaje", "Valor de transferencia invalido.");
	            request.setAttribute("tipoMensaje", "error");
				request.setAttribute("ValorInvalido", "error");
				error = true;
			}
			
			//validacion transferencia numero invalido
			String importeStr = request.getParameter("monto").toString();
			if (importeStr.startsWith("0")  || importeStr.startsWith("0.") ) {
				System.out.println("[DEBUG] transferencia invalida");
				request.setAttribute("mensaje", "Valor de transferencia invalido.");
	            request.setAttribute("tipoMensaje", "error");
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
						request.setAttribute("mensaje", "Transferencia realizada exitosamente");
			            request.setAttribute("tipoMensaje", "success");
					}
					else
					{
						request.setAttribute("mensaje", "Ha ocurrido un error al transferir");
			            request.setAttribute("tipoMensaje", "error");
					}
				}
				else 
				{
					request.setAttribute("mensaje", "Saldo insuficiente");
		            request.setAttribute("tipoMensaje", "error");
				}
			}
			
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
		rd.forward(request, response);
	}

}
