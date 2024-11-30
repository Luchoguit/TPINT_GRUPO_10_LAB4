package servlets;

import java.io.IOException;
import java.math.BigDecimal;

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
		BigDecimal importeTotal = (BigDecimal) request.getAttribute("importeTotal");
		
		System.out.println("[DEBUG] doGet ConfirmarPrestamo");
        System.out.println("[DEBUG] importe pedido: " + prestamo.getImportePedido());
        System.out.println("[DEBUG] importe total: " + importeTotal);

		if (prestamo != null) {
			request.setAttribute("importeSolicitado", prestamo.getImportePedido());
			request.setAttribute("plazoMeses", prestamo.getPlazoMeses());
			request.setAttribute("cantidadCuotas", prestamo.getCantidadCuotas());
			request.setAttribute("numeroCuenta", prestamo.getCuenta().getNumeroCuenta() );
		    request.setAttribute("importeTotal", importeTotal); 
		    request.setAttribute("importeMensual", prestamo.getImporteMensual());
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

	   response.sendRedirect("ServletPedirPrestamo");		
	}

}
