package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;

@WebServlet("/ServletVerDatos")
public class ServletVerDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		 
            if (cliente != null) {        
                request.setAttribute("cliente", cliente);
            } else {
                // Enviar un mensaje de error si no se encuentra el cliente
                request.setAttribute("error", "Debe loguearse para ver sus datos.");
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
