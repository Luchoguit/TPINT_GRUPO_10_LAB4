package servlets;

import java.io.IOException;
import java.math.BigDecimal;
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
		
		
	    // Obtener la cuenta de la sesión
	    Cuenta cuenta = (Cuenta) request.getSession().getAttribute("cuenta");
	    int idCuenta = cuenta.getId(); // Obtener el ID de la cuenta asociada

	    String fechaInicio = request.getParameter("fechaInicio");
	    String fechaFin = request.getParameter("fechaFin");

	    // Convertir fechaFin para incluir todo el día
	    if (fechaFin != null && !fechaFin.isEmpty()) {
	        fechaFin = fechaFin + " 23:59:59";  // Establece la hora a las 23:59:59
	    }
	    
	    MovimientoNegocio movimientoNegocio = new MovimientoNegocioImp();
	    List<Movimiento> listaIngresos = movimientoNegocio.obtenerIngresosPorFechas(idCuenta, fechaInicio, fechaFin);
	    List<Movimiento> listaEgresos = movimientoNegocio.obtenerEgresosPorFechas(idCuenta, fechaInicio, fechaFin);

	 // Calcular el total de ingresos
	    BigDecimal totalIngresos = listaIngresos.stream()
	            .map(Movimiento::getImporte) // Obtiene el BigDecimal de cada ingreso
	            .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma los valores

	    // Calcular el total de egresos
	    BigDecimal totalEgresos = listaEgresos.stream()
	            .map(Movimiento::getImporte) // Obtiene el BigDecimal de cada egreso
	            .reduce(BigDecimal.ZERO, BigDecimal::add); // Suma los valores

	    if (totalIngresos == null) {
	        totalIngresos = BigDecimal.ZERO;
	    }

	    if (totalEgresos == null) {
	        totalEgresos = BigDecimal.ZERO;
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
