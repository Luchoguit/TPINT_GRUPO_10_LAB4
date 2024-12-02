package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import negocioimplementacion.UsuarioNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/ServletCambiarContrase�a")
public class ServletCambiarContrase�a extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }
		    	                 
       RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
       dispatcher.forward(request, response);

    } 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	    
		
		String contrasenaActual = request.getParameter("contrasenaActual");
	    String contrasenaNueva = request.getParameter("contrasenaNueva");
	    String contrasenaNueva2 = request.getParameter("contrasenaNueva2");
	    
	    
	    //Se valida que se hayan ingresado las contrase�as
	    if (contrasenaNueva == null || contrasenaNueva.trim().isEmpty() ||
	    		contrasenaNueva2 == null || contrasenaNueva2.trim().isEmpty() || 
	    				contrasenaActual == null || contrasenaActual.trim().isEmpty()){
		            Mensaje.error(request, "Todos los campos son obligatorios.");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
		            dispatcher.forward(request, response);
		            return;
		        }
		    
		    // Valido que las contrase�as coincidan
		    if (!contrasenaNueva.equals(contrasenaNueva2)) {
	            Mensaje.error(request, "Las contrase�as no coinciden.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
		        dispatcher.forward(request, response);
		        return;
		        }
		      
		    if (contrasenaActual.equals(user.getContrase�a())) {
			    UsuarioNegocio usuarioNegocio = new UsuarioNegocioImp();

			    // Actualizo  pw del usuario
			    user.setContrase�a(contrasenaNueva);
			    boolean actualizado = usuarioNegocio.actualizarUser(user);

			    if (actualizado) {
		            Mensaje.exito(request, "Contrase�a modificada exitosamente.");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
			        dispatcher.forward(request, response);
			    } else {
		            Mensaje.error(request, "Ocurri� un error al modificar la contrase�a. Por favor, intente nuevamente.");
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
			        dispatcher.forward(request, response);
			    }   	
		    	
		    } else {
	            Mensaje.error(request, "La contrase�a actual ingresada no es correcta.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/CambiarContrase�a.jsp");
		        dispatcher.forward(request, response);
		        return;
		    }
		    	    
    }
    
}

