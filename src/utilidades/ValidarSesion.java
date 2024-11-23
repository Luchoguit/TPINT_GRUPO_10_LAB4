package utilidades;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Usuario;

public class ValidarSesion {

    // Verifica que haya un usuario en sesión
    public static boolean validarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // false evita crear nueva sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("LOGIN/Login.jsp");
            return false;
        }
        return true;
    }

    // Verifica que el usuario en sesión sea administrador
    public static boolean validarAdministrador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!validarSesion(request, response)) {
            return false;
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (!usuario.esAdministrador()) {
            response.sendRedirect("LOGIN/Login.jsp");
            return false;
        }
        return true;
    }

    // Verifica que el usuario en sesión sea cliente
    public static boolean validarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!validarSesion(request, response)) {
            return false;
        }

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (!usuario.esCliente()) {
            response.sendRedirect("LOGIN/Login.jsp");
            return false;
        }
        return true;
    }
}
