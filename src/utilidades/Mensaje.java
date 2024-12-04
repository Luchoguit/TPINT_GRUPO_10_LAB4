package utilidades;


import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    
    // M�todo para establecer un mensaje de �xito en el request
    public static void exito(HttpServletResponse response, String url, String mensaje) throws IOException {
        redirigirConMensaje(response, url, mensaje, "success");
    }

    // M�todo para establecer un mensaje de error en el request
    public static void error(HttpServletResponse response, String url, String mensaje) throws IOException {
        redirigirConMensaje(response, url, mensaje, "error");
    }

    // M�todo privado que redirige con el mensaje codificado
    private static void redirigirConMensaje(HttpServletResponse response, String url, String mensaje, String tipoMensaje) throws IOException {
        String mensajeCodificado = URLEncoder.encode(mensaje, "UTF-8");
        String tipoMensajeCodificado = URLEncoder.encode(tipoMensaje, "UTF-8");
        response.sendRedirect(url + "?mensaje=" + mensajeCodificado + "&tipoMensaje=" + tipoMensajeCodificado);
    }
}