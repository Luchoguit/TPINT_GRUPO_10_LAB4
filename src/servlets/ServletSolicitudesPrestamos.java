package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocio.SolicitudAltaCuentaNegocio;
import negocio.SolicitudPrestamoNegocio;
import negocio.UsuarioNegocio;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.CuotaNegocioImp;
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
		
		
	       // Manejo de paginación
	       int registrosPorPagina = 5;
	       int paginaActual = 1;

	       // Obtener el número de pagina actual desde la request
	       String pageParam = request.getParameter("page");
	       if (pageParam != null && !pageParam.isEmpty()) {
	    	   paginaActual = Integer.parseInt(pageParam);
	       }

	       // Calcular los indices para la sublista de Clientes
	       int totalRecords = listaPrestamos.size();
	       int totalPaginas = (int) Math.ceil((double) totalRecords / registrosPorPagina);
	       int startIndex = (paginaActual - 1) * registrosPorPagina;
	       int endIndex = Math.min(startIndex + registrosPorPagina, totalRecords);

	       // Sublista de clientes para la pagina actual
	       List<Prestamo> prestamosPagina = listaPrestamos.subList(startIndex, endIndex);

	       // Pasar atributos a la vista
	       request.setAttribute("listaPrestamos", prestamosPagina);
	       request.setAttribute("totalPaginas", totalPaginas);
	       request.setAttribute("paginaActual", paginaActual);
		
		
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
	    	
	    	PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
	    	Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
	    	// ACEPTAR PRESTAMO
	    	SolicitudPrestamoNegocio spNegocio = new SolicitudPrestamoNegocioImp();
	    	boolean resultadoAceptar = spNegocio.aceptarPrestamo(idPrestamo);
	    	
	        System.out.println("[DEBUG] resultado aceptar: " + resultadoAceptar);
	        if(resultadoAceptar) {
	        	CuotaNegocio cuotaNegocio = new CuotaNegocioImp();
	        	boolean resultadoCuotas = cuotaNegocio.generarCuotas(prestamo);
	        	System.out.println("[DEBUG] resultado crear cuotas: " + resultadoCuotas);
	        }

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
