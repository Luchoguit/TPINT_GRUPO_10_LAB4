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
import utilidades.Mensaje;
import utilidades.ValidarSesion;

@WebServlet("/ServletPedirPrestamo")
public class ServletPedirPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ValidarSesion.validarCliente(request, response)) {
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
	    CuentaNegocio cuentaNegocio = new CuentaNegocioImp();    
	    Cuenta cuenta = cuentaNegocio.obtenerCuentaPorId(idCuenta);

	    BigDecimal importePedido = new BigDecimal(request.getParameter("monto").replaceAll("[^0-9]", ""));
	    System.out.println("[DEBUG] importePedido: " + importePedido);
	    int plazoMeses = Integer.parseInt(request.getParameter("plazo"));    
	    
	    int cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));

	    
	    BigDecimal tasaInteres = BigDecimal.ZERO;

		switch (plazoMeses) {
		     case 6:
		         tasaInteres = new BigDecimal("0.05");
		         break;
		     case 12:
		         tasaInteres = new BigDecimal("0.10");
		         break;
		     case 18:
		         tasaInteres = new BigDecimal("0.15");
		         break;
		     case 24:
		         tasaInteres = new BigDecimal("0.20");
		         break;
		}
	
		BigDecimal importeTotal = importePedido.add(importePedido.multiply(tasaInteres));
		BigDecimal importeMensual = importeTotal.divide(BigDecimal.valueOf(cantidadCuotas), 2, RoundingMode.HALF_UP);
	
		Prestamo prestamo = new Prestamo(cliente, cuenta, importePedido, plazoMeses, importeMensual, importeTotal, cantidadCuotas);
        System.out.println("[DEBUG] Prestamo, monto mensual: " + prestamo.getImporteMensual());
		
		request.setAttribute("importeTotal", importeTotal);
		request.getSession().setAttribute("prestamo", prestamo);
	    response.sendRedirect("ServletConfirmarPrestamo");		
	    		 
	}
	

}
