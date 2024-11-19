package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.Movimiento;
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
		
		
		Cuenta cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
		
		CuentaNegocioImp cuentaNegocio = new CuentaNegocioImp();
		List<Movimiento> movimientos = cuentaNegocio.listarMovimientosCuenta(cuentaSeleccionada);
		
		request.setAttribute("movimientos", movimientos);	
		
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

}
