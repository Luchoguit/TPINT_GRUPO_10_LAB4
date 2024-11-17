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
				System.out.println("Llego una cuenta");
				Cuenta cuentaCliente = (Cuenta) request.getSession().getAttribute("cuenta");
				System.out.println(cuentaCliente.getCbu());
			}
			
			Cuenta cuentaCliente = (Cuenta) request.getSession().getAttribute("cuenta");
			
			
			if(existeCuentaConCbu == false)
			{
				System.out.println("CBU Invalido");
				request.setAttribute("mensaje", "Debe seleccionar un cbu Valido");
	            request.setAttribute("tipoMensaje", "error");
			}
			else {
				System.out.println("CBU valido");
			}
			
			if(cuentaCliente.getCbu().equals(cbu))
			{
				System.out.println("Son el mismo CBU");
				request.setAttribute("mensaje", "No puede transferir a la misma cuenta en uso");
	            request.setAttribute("tipoMensaje", "error");
				System.out.println(cuentaCliente.getCbu());
				System.out.println(cbu);
				request.setAttribute("mismaCuenta", "error");
				
			}
			else {
				System.out.println("Son distintos CBU");
				System.out.println(cuentaCliente.getCbu());
				System.out.println(cbu);
				
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
			
			CuentaNegocioImp negocioImp = new CuentaNegocioImp();
			
			Cuenta cuentaOrigen = (Cuenta) request.getSession().getAttribute("cuenta");
			
			TipoMovimiento tipoMovimiento = new TipoMovimiento(4, "Transferencia");
			
			String detalle = request.getParameter("concepto");
			LocalDateTime fechaHora = LocalDateTime.now();
			BigDecimal importe = new BigDecimal(request.getParameter("monto"));
			String cbuDestino = (String) request.getSession().getAttribute("cbuDestino");
			Cuenta cuentaDestino = negocioImp.obtenerCuentaPorCBU(cbuDestino);
			
			System.out.println("CBU capturado: " + cbuDestino);
			System.out.println("Datos de la cuenta destino");
			System.out.println(cuentaDestino.getCbu());
			System.out.println(cuentaDestino.getNumeroCuenta());
			System.out.println(cuentaDestino.getSaldo());
			
			Movimiento movimiento = new Movimiento(1, cuentaOrigen, tipoMovimiento, detalle, fechaHora, importe, cuentaDestino);
			
			BigDecimal saldoActualizado = movimiento.getCuentaDestino().getSaldo().subtract(movimiento.getImporte());
			
			if(saldoActualizado.floatValue() >= 0.00)
			{
				if(negocioImp.realizarTransferencia(movimiento))
				{	
					//Actualizamos saldo cuenta origen
					negocioImp.actualizarSaldo(movimiento, true);
					
					//Actualzamos saldo cuenta destino
					Movimiento movimientoDestino = new Movimiento(1, cuentaDestino, tipoMovimiento, detalle, fechaHora, importe, cuentaOrigen);
					negocioImp.actualizarSaldo(movimientoDestino, false);
					
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
		
		RequestDispatcher rd = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
		rd.forward(request, response);
	}

}
