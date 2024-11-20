package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Prestamo;
import negocio.PrestamoNegocio;
import negocioimplementacion.PrestamoNegocioImp;


@WebServlet("/ServletPagarPrestamo")
public class ServletPagarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo")); 
		PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
		Prestamo prestamo = prestamoNegocio.obtenerPrestamoPorId(idPrestamo);
		
		request.setAttribute("prestamo", prestamo);
		

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/PagarPrestamo.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
