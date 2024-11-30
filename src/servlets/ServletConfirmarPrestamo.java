package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioimplementacion.PrestamoNegocioImp;
import utilidades.Mensaje;


@WebServlet("/ServletConfirmarPrestamo")
public class ServletConfirmarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Prestamo prestamo = (Prestamo) request.getSession().getAttribute("prestamo");

		if (prestamo != null) {
			BigDecimal importeTotal = prestamo.getImporteMensual().multiply(BigDecimal.valueOf(prestamo.getCantidadCuotas()));
			request.setAttribute("prestamo", prestamo);
		    request.setAttribute("importeTotal", importeTotal); 
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/ConfirmarPrestamo.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Prestamo prestamo = (Prestamo) request.getSession().getAttribute("prestamo");

	    PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
	    boolean resultadoAltaPrestamo = prestamoNegocio.altaPrestamo(prestamo);
	    
	    if (resultadoAltaPrestamo) {
            Mensaje.exito(request, "Solicitud enviada con exito.");

            
        } else {
            Mensaje.error(request, "No se pudo realizar la solicitud.");

        }
	    
	   request.getSession().removeAttribute("prestamo");

		doGet(request, response);
	}

}
