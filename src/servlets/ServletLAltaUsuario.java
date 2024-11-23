package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
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
import negocio.UsuarioNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.UsuarioNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;

@WebServlet("/ServletLAltaUsuario")
public class ServletLAltaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }		
    	
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        List<Cliente> clientesSinUsuario = clienteNegocio.listarClientesSinUsuario();
        
        request.setAttribute("clientesSinUsuario", clientesSinUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        String usuarioNombre = request.getParameter("usuario");
        String tipoUsuario = request.getParameter("tipoUsuario");
        String contrasena = request.getParameter("contrasena");
        String contrasena2 = request.getParameter("contrasena2");
        String idClienteStr = request.getParameter("Cliente");


        if (contrasena.equals(contrasena2)) {
            try {
                int idCliente = Integer.parseInt(idClienteStr);
                Cliente cliente = new Cliente();
                cliente.setId(idCliente);
                Usuario usuario = new Usuario(cliente , usuarioNombre, contrasena, tipoUsuario, true, LocalDateTime.now());

                UsuarioNegocio usuarioNegocio = new UsuarioNegocioImp();
                boolean usuarioCreado = usuarioNegocio.altaUsuario(usuario);

                if (usuarioCreado) {                   
                    Mensaje.exito(request, "Usuario creado exitosamente.");

                } else {
                    Mensaje.error(request, "Error al crear el usuario.");
                }
            } catch (NumberFormatException e) {
                Mensaje.error(request, "El ID del cliente no es v�lido.");

            }
        } else {
            Mensaje.error(request, "Las contrase�as no coinciden.");
            
        }
        
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        List<Cliente> clientesSinUsuario = clienteNegocio.listarClientesSinUsuario();
        
        request.setAttribute("clientesSinUsuario", clientesSinUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaUsuario.jsp");
        dispatcher.forward(request, response);
    }
    
}
