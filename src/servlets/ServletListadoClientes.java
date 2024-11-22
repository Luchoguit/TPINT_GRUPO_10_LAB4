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
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocioimplementacion.ClienteNegocioImp;

@WebServlet("/ServletListadoClientes")
public class ServletListadoClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 if (!verificarSesionActiva(request, response)) {
             return; 
         }
    	 
    	cargarClientes(request, response, null);
       
       
        
    }

    @Override
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
        
       // Solo dejamos en la lista aquellos clientes activos
       Iterator<Cliente> iterator = listaClientes.iterator();
        
       while (iterator.hasNext()) {
           Cliente cliente = iterator.next();
           
          
           if (!cliente.isEstado()) {
               iterator.remove();
           }
       }
        
       // Manejo de paginaci�n
       int registrosPorPagina = 5;
       int paginaActual = 1;

       // Obtener el n�mero de pagina actual desde la request
       String pageParam = request.getParameter("page");
       if (pageParam != null && !pageParam.isEmpty()) {
    	   paginaActual = Integer.parseInt(pageParam);
       }

       // Calcular los indices para la sublista de Clientes
       int totalRecords = listaClientes.size();
       int totalPaginas = (int) Math.ceil((double) totalRecords / registrosPorPagina);
       int startIndex = (paginaActual - 1) * registrosPorPagina;
       int endIndex = Math.min(startIndex + registrosPorPagina, totalRecords);

       // Sublista de clientes para la pagina actual
       List<Cliente> clientesPagina = listaClientes.subList(startIndex, endIndex);

       // Pasar atributos a la vista
       request.setAttribute("listaClientes", clientesPagina);
       request.setAttribute("totalPaginas", totalPaginas);
       request.setAttribute("paginaActual", paginaActual);
       
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoClientes.jsp");
        dispatcher.forward(request, response);
    }
    
    private boolean verificarSesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // false evita crear nueva sesi�n
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("LOGIN/Login.jsp"); // Redirige al login si no hay usuario en sesi�n
            return false;
        }
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (!usuario.esAdministrador()) { // Verificar si el usuario es administrador
            response.sendRedirect("LOGIN/Login.jsp"); // Redirige al login si no es administrador
            return false;
        }
        
        return true; 
    }
    
}
