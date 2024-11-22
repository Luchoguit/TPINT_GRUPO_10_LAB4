package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import entidad.Cuenta;
import entidad.Prestamo;
import entidad.Usuario;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocio.SolicitudPrestamoNegocio;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.PrestamoNegocioImp;
import negocioimplementacion.SolicitudPrestamoNegocioImp;

@WebServlet("/ServletPedirPrestamo")
public class ServletPedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!verificarSesionActiva(request, response)) {
            return; 
        }
		
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
		List<Cuenta> listaCuentas = cuentaNegocio.listarCuentas(user);
		
		request.setAttribute("listaCuentas", listaCuentas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/PedirPrestamo.jsp");
        dispatcher.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");

	    int idCuenta = Integer.parseInt(request.getParameter("cuenta"));
	    Cuenta cuenta = new Cuenta();
	    cuenta.setId(idCuenta);

	    BigDecimal importePedido = new BigDecimal(request.getParameter("monto").replaceAll("[^0-9]", ""));
	    System.out.println("[DEBUG] importePedido: " + importePedido);
	    int plazoMeses = Integer.parseInt(request.getParameter("plazo"));
	    int cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));

	    // Calcular el importe mensual
	    BigDecimal importeMensual = importePedido.divide(BigDecimal.valueOf(cantidadCuotas), 2, RoundingMode.HALF_UP);
	    Prestamo prestamo = new Prestamo(cliente, cuenta, importePedido, plazoMeses, importeMensual, cantidadCuotas);

	    PrestamoNegocio prestamoNegocio = new PrestamoNegocioImp();
	    boolean resultadoAltaPrestamo = prestamoNegocio.altaPrestamo(prestamo);
	    
	    if (resultadoAltaPrestamo) {
        	request.setAttribute("mensaje", "Solicitud enviada con exito.");
            request.setAttribute("tipoMensaje", "success");
            
        } else {
        	request.setAttribute("mensaje", "No se pudo realizar la solicitud.");
            request.setAttribute("tipoMensaje", "error");
        }

	    doGet(request, response);
	}
	
	private boolean verificarSesionActiva(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // false evita crear nueva sesión
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("LOGIN/Login.jsp"); // Redirige al login si no hay usuario en sesión
            return false;
        }
              
        return true; 
    }


}
