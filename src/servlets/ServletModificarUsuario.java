package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import daoImplementacion.UsuarioDaoImp;
import entidad.Cliente;
import entidad.Cuenta;
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
import utilidades.ValidarSesion;


@WebServlet("/ServletModificarUsuario")
public class ServletModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }
		    	
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        
        if (user != null) {
            request.setAttribute("user", user);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/ModificarUsuario.jsp");
            dispatcher.forward(request, response);
        } else {
           //manejar error
        }
    } 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	    
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
	    String nuevaContrasena = request.getParameter("contrasena");
	    String contrasenaConfirmacion = request.getParameter("contrasena2");
	    String idStr = request.getParameter("id");
	    
	    if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty() ||
		        contrasenaConfirmacion == null || contrasenaConfirmacion.trim().isEmpty()) {
	    			request.setAttribute("user", user);
		            request.setAttribute("mensaje", "Todos los campos son obligatorios.");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/ModificarUsuario.jsp");
		            dispatcher.forward(request, response);
		            return;
		        }
		    
		    // Valido que las contraseñas coincidan
		    if (!nuevaContrasena.equals(contrasenaConfirmacion)) {
		    	request.setAttribute("user", user);
		        request.setAttribute("mensaje", "Las contraseñas no coinciden.");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/ModificarUsuario.jsp");
		        dispatcher.forward(request, response);
		        return;
		        }
	    
	    
	    int id = Integer.parseInt(idStr);
        Cliente cliente = new Cliente();
        cliente.setId(id);
        user.setCliente(cliente);
        
        //
	    UsuarioNegocio usuarioNegocio = new UsuarioNegocioImp();
	    
	    // Actualizo  pw del usuario
	    user.setContraseña(nuevaContrasena);
	    boolean actualizado = usuarioNegocio.actualizarUser(user);

	    if (actualizado) {
	    	request.setAttribute("user", user);
	        request.setAttribute("mensaje", "Usuario modificado exitosamente.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/MENUS/IndexUser.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        request.setAttribute("mensaje", "Ocurrió un error al modificar el usuario. Por favor, intente nuevamente.");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/ModificarUsuario.jsp");
	        dispatcher.forward(request, response);
	    }
	    
	    
	    
    }
    
}

