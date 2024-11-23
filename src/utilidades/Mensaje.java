package utilidades;


import javax.servlet.http.HttpServletRequest;

public class Mensaje {

    // M�todo para establecer un mensaje de �xito
    public static void exito(HttpServletRequest request, String texto) {
        request.setAttribute("mensaje", texto);
        request.setAttribute("tipoMensaje", "success");
    }

    // M�todo para establecer un mensaje de error
    public static void error(HttpServletRequest request, String texto) {
        request.setAttribute("mensaje", texto);
        request.setAttribute("tipoMensaje", "error");
    }
}