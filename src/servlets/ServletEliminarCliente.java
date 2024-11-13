package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocioimplementacion.ClienteNegocioImp;

/**
 * Servlet implementation class ServletEliminarCliente
 */
@WebServlet("/ServletEliminarCliente")
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEliminarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("dniCliente") != null)
		{
			String dniCliente = request.getParameter("dniCliente");
			
			ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
			
			Cliente aux = clienteNegocio.obtenerPorDNI(dniCliente);
			
			
			Boolean resultado = clienteNegocio.eliminarCliente(aux.getId());
			
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoClientes.jsp");
            dispatcher.forward(request, response);
		}
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		doGet(request,response);
	}

}
