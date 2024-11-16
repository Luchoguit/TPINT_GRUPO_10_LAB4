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


@WebServlet("/ServletActivarCliente")
public class ServletActivarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("dniCliente") != null)
		{
			String dniCliente = request.getParameter("dniCliente");
			
			ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
			
			Cliente aux = clienteNegocio.obtenerPorDNI(dniCliente);
			
			
			Boolean resultado = clienteNegocio.activarCliente(aux.getId());
			
        	request.setAttribute("mensaje", "Cliente activado exitosamente.");
            request.setAttribute("tipoMensaje", "success");
			
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ClientesEliminados.jsp");
            dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
