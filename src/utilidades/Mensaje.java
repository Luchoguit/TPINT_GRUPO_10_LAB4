package utilidades;


import javax.servlet.http.HttpServletRequest;

public class Mensaje {

    // Método para establecer un mensaje de éxito
    public static void exito(HttpServletRequest request, String texto) {
        request.setAttribute("mensaje", texto);
        request.setAttribute("tipoMensaje", "success");
    }

    // Método para establecer un mensaje de error
    public static void error(HttpServletRequest request, String texto) {
        request.setAttribute("mensaje", texto);
        request.setAttribute("tipoMensaje", "error");
    }
}