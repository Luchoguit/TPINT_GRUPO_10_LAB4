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

@WebServlet("/ServletLAltaUsuario")
public class ServletLAltaUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	if (!verificarSesionActiva(request, response)) {
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
                    request.setAttribute("mensaje", "Usuario creado exitosamente.");
                    request.setAttribute("tipoMensaje", "success");
                } else {
                    request.setAttribute("mensaje", "Error al crear el usuario.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "El ID del cliente no es válido.");
            }
        } else {
            request.setAttribute("mensaje", "Las contraseñas no coinciden.");
        }
        
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        List<Cliente> clientesSinUsuario = clienteNegocio.listarClientesSinUsuario();
        
        request.setAttribute("clientesSinUsuario", clientesSinUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaUsuario.jsp");
        dispatcher.forward(request, response);
    }
    
    private boolean verificarSesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // false evita crear nueva sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("LOGIN/Login.jsp"); // Redirige al login si no hay usuario en sesión
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
