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
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;
import negocio.CuentaNegocio;
import negocioimplementacion.CuentaNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
        dispatcher.forward(request, response);
	}

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
	            Mensaje.error(request, "Debe seleccionar un cbu Valido");
			}
			else {
				System.out.println("[DEBUG] CBU valido");
			}
			
			if(cuentaCliente.getCbu().equals(cbu))
			{
				System.out.println("[DEBUG] Son el mismo CBU");

	            Mensaje.error(request, "No puede transferir a la misma cuenta en uso");	            
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
		
		RequestDispatcher rd = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
		rd.forward(request, response);
	}
	

}
