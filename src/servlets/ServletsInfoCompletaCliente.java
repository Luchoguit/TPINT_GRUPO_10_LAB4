package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;



@WebServlet("/ServletsInfoCompletaCliente")
public class ServletsInfoCompletaCliente extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Obtener el DNI del cliente desde el parámetro de la URL
        String dni = request.getParameter("dniCliente");

        if (dni != null && !dni.isEmpty()) {
           
        	ClienteNegocio clienteNegocio = new ClienteNegocioImp();
            LocalidadNegocio localidadNegocio = new LocalidadNegocioImp();
            ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();

            // Buscar cliente por DNI
            Cliente cliente = clienteNegocio.obtenerPorDNI(dni);

            if (cliente != null) {        
                request.setAttribute("cliente", cliente);
            } else {
                // Enviar un mensaje de error si no se encuentra el cliente
                request.setAttribute("error", "No se encontró un cliente con el DNI proporcionado.");
            }
        } else {
            // Enviar un mensaje de error si el DNI es nulo o vacío
            request.setAttribute("error", "DNI no válido.");
        }

        // Redirigir a la página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/InformacionCompletaCliente.jsp");
        dispatcher.forward(request, response);
    }
    
}