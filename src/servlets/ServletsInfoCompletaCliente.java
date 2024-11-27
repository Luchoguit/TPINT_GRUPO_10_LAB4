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
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocio.UsuarioNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;
import negocioimplementacion.UsuarioNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;



@WebServlet("/ServletsInfoCompletaCliente")
public class ServletsInfoCompletaCliente extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
       	
        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }
		
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
           
        	ClienteNegocio clienteNegocio = new ClienteNegocioImp();

            Cliente cliente = clienteNegocio.obtenerPorId(idCliente);

            if (cliente != null) {        
                request.setAttribute("cliente", cliente);
                
                UsuarioNegocio userNegocio = new UsuarioNegocioImp();
                Usuario user = userNegocio.obtenerUsuarioPorId(idCliente);
                
                request.setAttribute("usuario", user);
                
            } else {
                Mensaje.error(request, "No se encontró un cliente con el DNI proporcionado.");

            }

        

        // Redirigir a la página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/InformacionCompletaCliente.jsp");
        dispatcher.forward(request, response);
    }
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
       
}