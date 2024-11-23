package servlets;

import java.io.IOException;
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
import utilidades.Mensaje;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("usuario");
        String contrasenia = request.getParameter("password");

        UsuarioNegocio usuarioNegocio = new UsuarioNegocioImp();
        Usuario usuario = usuarioNegocio.getUsuario(nombreUsuario, contrasenia);

        System.out.println("[DEBUG] Entra al doPost");

        // Verificar si el usuario existe y está activo
        if (usuario != null && usuario.isEstado()) {
            // Almacenar el usuario en la sesión
            request.getSession().setAttribute("usuario", usuario);
            
            ClienteNegocio clienteNegocio = new ClienteNegocioImp();
            Cliente cliente = clienteNegocio.obtenerPorId(usuario.getCliente().getId());
            
            request.getSession().setAttribute("cliente", cliente);

            System.out.println("[DEBUG] Encuentra Usuario");

            // Redirigir según el tipo de usuario
            if (usuario.esAdministrador()) {
                System.out.println("[DEBUG] ES ADMIN");
                response.sendRedirect("MENUS/IndexAdmin.jsp");
            } else {
                System.out.println("[DEBUG] ES USER");
                response.sendRedirect("MENUS/IndexUser.jsp");
            }
        } else {
            Mensaje.error(request, "Datos Incorrectos, intente nuevamente.");

            request.getRequestDispatcher("LOGIN/Login.jsp").forward(request, response);

            System.out.println("[DEBUG] No encuentra Usuario");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    	request.getRequestDispatcher("LOGIN/login.jsp").forward(request, response);
    
    }
}
