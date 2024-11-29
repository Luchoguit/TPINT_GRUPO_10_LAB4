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
import javax.servlet.http.HttpSession;

import entidad.Cuenta;
import entidad.Movimiento;
import negocio.MovimientoNegocio;
import negocioimplementacion.MovimientoNegocioImp;
import utilidades.ValidarSesion;


@WebServlet("/ServletInformesAdmin")
public class ServletInformesAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }

        
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/InformesAdmin.jsp");
        dispatcher.forward(request, response);
        
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("[DEBUG] entra al doPost InformesAdmin");
		

	    String fechaInicio = request.getParameter("fechaInicio");
	    String fechaFin = request.getParameter("fechaFin");

	    // Convertir fechaFin para incluir todo el día
	    if (fechaFin != null && !fechaFin.isEmpty()) {
	        fechaFin = fechaFin + " 23:59:59";  // Establece la hora a las 23:59:59
	    }
	    
	    MovimientoNegocio movimientoNegocio = new MovimientoNegocioImp();
	    List<Movimiento> listaMovimientos = movimientoNegocio.todosLosMovimientosPorFechas(fechaInicio, fechaFin);

	    BigDecimal totalIngresos = BigDecimal.ZERO;
	    BigDecimal totalEgresos = BigDecimal.ZERO;

	    /* APLICAR LOGICA PARA SEPARAR INGRESOS/EGRESOS DEL BANCO
	     * 
	     * INGRESO: PAGO DE CUOTA DE PRESTAMO
	     * EGRESO: DEPOSITO DE PRESTAMO (Y ALTA DE CUENTA??)
	     */
	     
	    int totalMovimientos = listaMovimientos.size();
	    int cantAltaCuentas = 0, cantAltaPrestamos = 0, cantPagoPrestamos = 0, cantTransferencias = 0;
	    for (Movimiento movimiento : listaMovimientos) {
	        int tipoMovimientoId = movimiento.getTipoMovimiento().getId();
	        
	        	switch (tipoMovimientoId) {
	            case 1: // Alta de cuenta - ??
	            	cantAltaCuentas ++;
	            	break;
	            case 2: // Alta de prestamo - Egreso
	                totalEgresos = totalEgresos.add(movimiento.getImporte());
	                cantAltaPrestamos ++;
	                break;
	            case 3: // Pago de prestamo - Ingreso
	                totalIngresos = totalIngresos.add(movimiento.getImporte());
	                cantPagoPrestamos ++;
	                break;
	            case 4: // Transferencia -
	            	cantTransferencias ++;
	                break;
	            default:
	                System.out.println("[DEBUG] Tipo de movimiento desconocido: " + tipoMovimientoId);
	        }
	    }

	    BigDecimal balance = totalIngresos.subtract(totalEgresos);
	   


	    System.out.println("[DEBUG] totalIngresos: " + totalIngresos);
	    System.out.println("[DEBUG] totalEgresos: " + totalEgresos);
	    System.out.println("[DEBUG] balance: " + balance);


	    request.setAttribute("totalMovimientos", totalMovimientos);
	    request.setAttribute("totalIngresos", totalIngresos);
	    request.setAttribute("totalEgresos", totalEgresos);
	    request.setAttribute("balance", balance);
	    request.setAttribute("cantAltaCuentas", cantAltaCuentas);
	    request.setAttribute("cantAltaPrestamos", cantAltaPrestamos);
	    request.setAttribute("cantPagoPrestamos", cantPagoPrestamos);
	    request.setAttribute("cantTransferencias", cantTransferencias);


	    RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/InformesAdmin.jsp");
	    dispatcher.forward(request, response);
	}
	
}

