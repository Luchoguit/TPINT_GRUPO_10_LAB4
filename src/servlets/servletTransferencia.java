package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import negocio.CuentaNegocio;
import negocioimplementacion.CuentaNegocioImp;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnCBU") != null) {
			String cbu = request.getParameter("inputCBU");
			CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
			Cuenta cuenta = new Cuenta();
			
			cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbu);
			
			if (cuenta != null) {
				request.setAttribute("Cuenta", cuenta);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
			rd.forward(request, response);

		}
		
		else {
			request.setAttribute("mensaje", "Debe seleccionar un cbu Valido");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/servletTransferencia");
            request.setAttribute("tipoMensaje", "error");
            dispatcher.forward(request, response);
			
			
			
		}
		
	}

}
