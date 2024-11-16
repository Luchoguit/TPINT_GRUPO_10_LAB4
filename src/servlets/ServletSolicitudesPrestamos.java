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
import entidad.Prestamo;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocio.SolicitudAltaCuentaNegocio;
import negocio.SolicitudPrestamoNegocio;
import negocio.UsuarioNegocio;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.PrestamoNegocioImp;
import negocioimplementacion.SolicitudAltaCuentaNegocioImp;
import negocioimplementacion.SolicitudPrestamoNegocioImp;
import negocioimplementacion.UsuarioNegocioImp;


@WebServlet("/ServletSolicitudesPrestamos")
public class ServletSolicitudesPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
		List<Prestamo> listaPrestamos = prestamoNegocio.listarPrestamos();
		
		request.setAttribute("listaPrestamos", listaPrestamos);
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/AdministrarPrestamos.jsp");
        dispatcher.forward(request, response);
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
        int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));    
        
        System.out.println("[DEBUG] Entra al doPost");
        System.out.println("[DEBUG] accion: " + accion);
        System.out.println("[DEBUG] id de solicitud: " + idPrestamo);


        try {
	    if ("aceptar".equals(accion)) {
	    	

	    	// ACEPTAR PRESTAMO
	    	SolicitudPrestamoNegocio spNegocio = new SolicitudPrestamoNegocioImp();
	    	boolean resultadoAceptar = spNegocio.aceptarPrestamo(idPrestamo);
	    	
	        System.out.println("[DEBUG] resultado aceptar: " + resultadoAceptar);

	    } else {

	     // RECHAZAR PRESTAMO
	    	SolicitudPrestamoNegocio spNegocio = new SolicitudPrestamoNegocioImp();
	    	boolean resultadoRechazar = spNegocio.rechazarPrestamo(idPrestamo);
	    	
	        System.out.println("[DEBUG] resultado rechazar: " + resultadoRechazar);

	    }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	    doGet(request, response);
	}

}
