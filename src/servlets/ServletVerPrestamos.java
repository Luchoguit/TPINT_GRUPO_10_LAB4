package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Prestamo;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocioimplementacion.CuotaNegocioImp;
import negocioimplementacion.PrestamoNegocioImp;

@WebServlet("/ServletVerPrestamos")
public class ServletVerPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cuenta cuenta = (Cuenta) request.getSession().getAttribute("cuenta");
		PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
		CuotaNegocio cuotaNegocio = new CuotaNegocioImp();

		List<Prestamo> listaPrestamos = prestamoNegocio.listarPrestamosCuenta(cuenta.getId());

		// Mapa para asociar préstamos con sus cuotas pagadas
		Map<Prestamo, Integer> prestamosCuotas = new HashMap<>();

		for (Prestamo prestamo : listaPrestamos) {
			if (prestamo.isEstado()) {
				int cantCuotasAbonadas = cuotaNegocio.cantidadCuotasPagas(prestamo.getIdPrestamo());
				prestamosCuotas.put(prestamo, cantCuotasAbonadas);
			}
		}

		request.setAttribute("prestamosCuotas", prestamosCuotas);

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
