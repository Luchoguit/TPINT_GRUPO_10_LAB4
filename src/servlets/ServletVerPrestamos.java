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
import negocio.PrestamoNegocio;
import negocioimplementacion.PrestamoNegocioImp;

@WebServlet("/ServletVerPrestamos")
public class ServletVerPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		Cuenta cuenta = (Cuenta) request.getSession().getAttribute("cuenta");
		PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
		List<Prestamo> listaPrestamos = prestamoNegocio.listarPrestamosCuenta(cuenta.getId());
		
		request.setAttribute("listaPrestamos", listaPrestamos);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/VerPrestamos.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("[DEBUG] entra al doPost de ServletVerPrestamos para redireccionar");

		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo")); 
		System.out.println("[DEBUG] idPrestamo: " + idPrestamo);
		PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
		Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
		
		request.getSession().setAttribute("prestamo", prestamo);
		
		response.sendRedirect("ServletPagarPrestamo");

	}

}
