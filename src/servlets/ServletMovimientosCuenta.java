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
import entidad.Usuario;
import negocioimplementacion.CuentaNegocioImp;

/**
 * Servlet implementation class ServletMovimientosCuenta
 */
@WebServlet("/ServletMovimientosCuenta")
public class ServletMovimientosCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ServletMovimientosCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!verificarSesionActiva(request, response)) {
            return; 
        }
		
		
		Cuenta cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
		
		CuentaNegocioImp cuentaNegocio = new CuentaNegocioImp();
		List<Movimiento> movimientos = cuentaNegocio.listarMovimientosCuenta(cuentaSeleccionada);
		
		List<BigDecimal> saldosParciales = actualizarSaldosInvertidos(movimientos, cuentaSeleccionada);
		
		Collections.reverse(movimientos);
		
		
		request.setAttribute("movimientos", movimientos);	
		request.setAttribute("saldos", saldosParciales);
		
		System.out.println("Cantidad de movimientos en servlet: " + movimientos.size());
		
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/Movimientos.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<BigDecimal> actualizarSaldosInvertidos(List<Movimiento> movimientos, Cuenta cuentaSeleccionada)
	{
		BigDecimal acumulador = new BigDecimal("10000");
		int tipo_movimiento_alta_prestamo = 2;

		
		List<BigDecimal> saldosParciales = new ArrayList<>();
		
		for(Movimiento m : movimientos)
		{
			if(m.getCuentaDestino().getId()== cuentaSeleccionada.getId() || m.getTipoMovimiento().getId() == tipo_movimiento_alta_prestamo)
			{
				acumulador = acumulador.add(m.getImporte());
			}
			else
			{
				acumulador = acumulador.subtract(m.getImporte());
			}
			
			saldosParciales.add(acumulador);
		}
		
		Collections.reverse(saldosParciales);
		return saldosParciales;
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
