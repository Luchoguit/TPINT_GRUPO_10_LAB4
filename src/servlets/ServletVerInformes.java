package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
import negocio.MovimientoNegocio;
import negocioimplementacion.MovimientoNegocioImp;


@WebServlet("/ServletVerInformes")
public class ServletVerInformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasUser/VerInformes.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("[DEBUG] entra al doPost VerInformes");
		
		
	    Cuenta cuenta = (Cuenta) request.getSession().getAttribute("cuenta");
	    int idCuenta = cuenta.getId();

	    String fechaInicio = request.getParameter("fechaInicio");
	    String fechaFin = request.getParameter("fechaFin");

	    // Convertir fechaFin para incluir todo el día
	    if (fechaFin != null && !fechaFin.isEmpty()) {
	        fechaFin = fechaFin + " 23:59:59";  // Establece la hora a las 23:59:59
	    }
	    
	    MovimientoNegocio movimientoNegocio = new MovimientoNegocioImp();
	    List<Movimiento> listaMovimientos = movimientoNegocio.obtenerMovimientosPorFechas(idCuenta, fechaInicio, fechaFin);

	    BigDecimal totalIngresos = BigDecimal.ZERO;
	    BigDecimal totalEgresos = BigDecimal.ZERO;

	    for (Movimiento movimiento : listaMovimientos) {
	        int tipoMovimientoId = movimiento.getTipoMovimiento().getId();

	        	switch (tipoMovimientoId) {
	            case 1: // Ingreso
	            case 2: // Ingreso
	                totalIngresos = totalIngresos.add(movimiento.getImporte());
	                break;

	            case 3: // Egreso
	                totalEgresos = totalEgresos.add(movimiento.getImporte());
	                break;

	            case 4: 
	                // Si el idCuentaDestino es igual al idCuenta, es un ingreso; de lo contrario, es un egreso
	                if (movimiento.getCuentaDestino().getId() == idCuenta) {
	                    totalIngresos = totalIngresos.add(movimiento.getImporte());
	                } else {
	                    totalEgresos = totalEgresos.add(movimiento.getImporte());
	                }
	                break;

	            default:
	                System.out.println("[DEBUG] Tipo de movimiento desconocido: " + tipoMovimientoId);
	        }
	    }

	    BigDecimal balance = totalIngresos.subtract(totalEgresos);


	    System.out.println("[DEBUG] totalIngresos: " + totalIngresos);
	    System.out.println("[DEBUG] totalEgresos: " + totalEgresos);
	    System.out.println("[DEBUG] balance: " + balance);


	    request.setAttribute("totalIngresos", totalIngresos);
	    request.setAttribute("totalEgresos", totalEgresos);
	    request.setAttribute("balance", balance);


	    RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/VerInformes.jsp");
	    dispatcher.forward(request, response);
	}
}
