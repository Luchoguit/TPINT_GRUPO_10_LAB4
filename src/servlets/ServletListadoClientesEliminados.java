package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

/**
 * Servlet implementation class ServletListadoClientesEliminados
 */
@WebServlet("/ServletListadoClientesEliminados")
public class ServletListadoClientesEliminados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletListadoClientesEliminados() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cargarClientes(request, response, null);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtroCliente = request.getParameter("filtroCliente");
        cargarClientes(request, response, filtroCliente);
	}
	
	private void cargarClientes(HttpServletRequest request, HttpServletResponse response, String filtroCliente) throws ServletException, IOException {
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        List<Cliente> listaClientes = clienteNegocio.listarClientes();

        // Aplicar filtro si es necesario
        if (filtroCliente != null && !filtroCliente.trim().isEmpty()) {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            for (Cliente cliente : listaClientes) {
                if (cliente.getDni().contains(filtroCliente) ||
                    cliente.getNombre().toLowerCase().contains(filtroCliente.toLowerCase()) ||
                    cliente.getApellido().toLowerCase().contains(filtroCliente.toLowerCase())) {
                    
                    clientesFiltrados.add(cliente);
                }
            }
            listaClientes = clientesFiltrados;
        }
        
       // Solo dejamos en la lista aquellos clientes eliminados
       Iterator<Cliente> iterator = listaClientes.iterator();
        
       while (iterator.hasNext()) {
           Cliente cliente = iterator.next();
           
          
           if (cliente.isEstado()) {
               iterator.remove();
           }
       }
        
        request.setAttribute("listaClientes", listaClientes);

       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ClientesEliminados.jsp");
        dispatcher.forward(request, response);
    }

}
