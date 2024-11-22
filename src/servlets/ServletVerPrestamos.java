package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioimplementacion.PrestamoNegocioImp;

@WebServlet("/ServletVerPrestamos")
public class ServletVerPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		if (!verificarSesionActiva(request, response)) {
	        return; 
	    }
		
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
	
	private boolean verificarSesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // false evita crear nueva sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("LOGIN/Login.jsp"); // Redirige al login si no hay usuario en sesión
            return false;
        }
              
        return true; 
    }

	
}
