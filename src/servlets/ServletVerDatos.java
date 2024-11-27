package servlets;

import java.io.IOException;

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
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;

@WebServlet("/ServletVerDatos")
public class ServletVerDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }
		
		
        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		 
            if (cliente != null) {        
                request.setAttribute("cliente", cliente);
                request.setAttribute("usuario", user);
            } else {

                Mensaje.error(request, "Debe loguearse para ver sus datos.");

            }
       

        // Redirigir a la página JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/VerDatos.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
