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
import negocio.ClienteNegocio;
import negocioimplementacion.ClienteNegocioImp;


@WebServlet("/ServletListadoClientes")
public class ServletListadoClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Carga de Clientes
		ClienteNegocio clienteNegocio = new ClienteNegocioImp();
		List<Cliente> listaClientes = clienteNegocio.listarClientes();
		request.setAttribute("listaClientes", listaClientes);
		
        System.out.println(listaClientes.size());

		
        // Reenvío al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/ListadoClientes.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
