package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.CuotaNegocioImp;
import negocioimplementacion.PrestamoNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/ServletPagarPrestamo")
public class ServletPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }
				
		Prestamo prestamo = (Prestamo)request.getSession().getAttribute("prestamo");
		
		request.setAttribute("prestamo", prestamo);
		
		CuotaNegocio cuotaNegocio = new CuotaNegocioImp();
		int cantidadCuotasPagas = cuotaNegocio.cantidadCuotasPagas(prestamo.getIdPrestamo());
		
		request.setAttribute("cantidadCuotasPagas", cantidadCuotasPagas);
		
		int cantidadImpagas = prestamo.getCantidadCuotas() - cantidadCuotasPagas;
		
		request.setAttribute("cantidadImpagas", cantidadImpagas);
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
		List<Cuenta> listaCuentas = cuentaNegocio.listarCuentas(user);
		
		request.setAttribute("listaCuentas", listaCuentas);
		
		
		

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/PagarPrestamo.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[DEBUG] entra al doPost para pagar cuota");
		
		
		Prestamo prestamo = (Prestamo)request.getSession().getAttribute("prestamo");	

		/// OBTENER LA CANTIDAD DE CUOTAS QUE EL USUARIO DESEA PAGAR	
		
		int cantCuotas = Integer.parseInt(request.getParameter("cantidadCuotas")); 
		System.out.println("[DEBUG] cantidad de cuotas: " + cantCuotas);
		
		/// OBTENER EL ID CUENTA DESDE LA QUE SE VA A ABONAR
		int idCuenta = Integer.parseInt(request.getParameter("cuenta")); 
		System.out.println("[DEBUG] idCuenta: " + idCuenta);
		

		//--------------//
		//Antes de le excepcion
		
		// SE VALIDA SI ALCANZA EL SALDO EN LA CUENTA PARA PAGAR LO SELECCIONADO:
		CuentaNegocioImp cuentaNegocio = new CuentaNegocioImp();
		Cuenta cuenta = cuentaNegocio.obtenerCuentaPorId(idCuenta);
		
		BigDecimal saldo = cuenta.getSaldo();
		
		BigDecimal cantidadCuotas = new BigDecimal(cantCuotas);
		
		BigDecimal totalSeleccionado = prestamo.getImporteMensual();
		totalSeleccionado = totalSeleccionado.multiply(cantidadCuotas);
		
		saldo = saldo.subtract(totalSeleccionado);
		
		if(saldo.compareTo(BigDecimal.ZERO) < 0) 
		{
			Mensaje.error(request, "Saldos insuficientes en la cuenta");
			doGet(request, response);
			return;
		}
		
		
		
		/// REGISTRAR EL PAGO DE LA CUOTA
		CuotaNegocio cuotaNegocio = new CuotaNegocioImp();
		boolean resultado = cuotaNegocio.pagarCuotas(prestamo.getIdPrestamo(), cantCuotas, idCuenta);	

		/// EL UPDATE AL SALDO DE LA CUENTA Y 
		//  EL INSERT DEL MOVIMIENTO SE REALIZAN MEDIANTE TRIGGER
		
		///Despues de la excepcion
		int cantCuotasAbonadas = cuotaNegocio.cantidadCuotasPagas(prestamo.getIdPrestamo());
		
		System.out.println("[DEBUG] cantidad de cuotas abonadas: " + cantCuotasAbonadas);
		System.out.println("[DEBUG] cantidad de cuotas totales prestamo: " + prestamo.getCantidadCuotas());
		
		//ACTUALIZAMOS A FALSE EL ESTADO DEL PRESTAMO SI ES LA ULTIMA CUOTA
		if(cantCuotasAbonadas == prestamo.getCantidadCuotas())
		{
			PrestamoNegocioImp prestamoNegocio = new PrestamoNegocioImp();
			prestamoNegocio.cancelarPrestamo(prestamo.getIdPrestamo());
			System.out.println("[DEBUG] Prestamo cancelado ");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("MENUS/IndexCuenta.jsp");
			dispatcher.forward(request, response);
		}
        Mensaje.exito(request, "Cuota/s pagada/s exitosamente.");

		doGet(request, response);
	}


}
