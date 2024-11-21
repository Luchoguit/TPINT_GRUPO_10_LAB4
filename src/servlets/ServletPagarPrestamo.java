package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


@WebServlet("/ServletPagarPrestamo")
public class ServletPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		/// REGISTRAR EL PAGO DE LA CUOTA
		CuotaNegocio cuotaNegocio = new CuotaNegocioImp();
		boolean resultado = cuotaNegocio.pagarCuotas(prestamo.getIdPrestamo(), cantCuotas);	

		/// ACTUALIZAR EL SALDO DE LA CUENTA
		
		/// EN EL CASO DE QUE SEA LA ULTIMA CUOTA, CAMBIAR EL ESTADO DEL PRESTAMO
		
		doGet(request, response);
	}

}
