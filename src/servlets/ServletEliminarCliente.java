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
import negocioimplementacion.ClienteNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/ServletEliminarCliente")
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }
		
		
		if(request.getParameter("dniCliente") != null)
		{
			String dniCliente = request.getParameter("dniCliente");
			
			ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
			
			Cliente aux = clienteNegocio.obtenerPorDNI(dniCliente);
			
			
			Boolean resultado = clienteNegocio.eliminarCliente(aux.getId());
			
			Mensaje.exito(request, "Ciente eliminado exitosamente.");
			
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletListadoClientes");
            dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request,response);
	}

}
