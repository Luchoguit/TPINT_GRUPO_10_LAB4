package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        List<Cliente> clientesSinUsuario = clienteNegocio.listarClientesSinUsuario();
        
        request.setAttribute("clientesSinUsuario", clientesSinUsuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        String usuarioNombre = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        String contrasena2 = request.getParameter("contrasena2");
        String idClienteStr = request.getParameter("Cliente");

        if (contrasena.equals(contrasena2)) {
            try {
                int idCliente = Integer.parseInt(idClienteStr);
                Usuario usuario = new Usuario(idCliente , usuarioNombre, contrasena, "cliente", true, java.time.LocalDateTime.now());

                UsuarioNegocio usuarioNegocio = new UsuarioNegocioImp();
                boolean usuarioCreado = usuarioNegocio.altaUsuario(usuario);

                if (usuarioCreado) {                   
                    request.setAttribute("mensaje", "Usuario creado exitosamente.");
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
}
