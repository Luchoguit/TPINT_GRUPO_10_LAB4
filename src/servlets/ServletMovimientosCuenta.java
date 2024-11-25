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

import entidad.Cliente;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import utilidades.ValidarSesion;


@WebServlet("/ServletMovimientosCuenta")
public class ServletMovimientosCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       // if (!ValidarSesion.validarCliente(request, response)) {
         //   return; 
        //}
				
        CuentaNegocioImp cuentaNegocio = new CuentaNegocioImp();
        Cuenta cuentaSeleccionada = null;
        
        //Aqui entra desde ADMIN
        if(request.getParameter("idCuenta") != null)
        {
        	String idCuenta = request.getParameter("idCuenta");
        	cuentaSeleccionada = cuentaNegocio.obtenerCuentaPorId(Integer.parseInt(idCuenta));
        	request.getSession().setAttribute("cuenta", cuentaSeleccionada);
        	
        	ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
        	Cliente propietarioCuentaSeleccionada = clienteNegocio.obtenerPorId(cuentaSeleccionada.getUsuario().getCliente().getId());
        	request.getSession().setAttribute("clienteSeleccionado", propietarioCuentaSeleccionada);
        }
        //Aqui entra desde USUARIO
        else 
        {
        	cuentaSeleccionada = (Cuenta) request.getSession().getAttribute("cuenta");
        }
        
		
		
		List<Movimiento> movimientos = cuentaNegocio.listarMovimientosCuenta(cuentaSeleccionada);
		
		List<BigDecimal> saldosParciales = actualizarSaldosInvertidos(movimientos, cuentaSeleccionada);
		
		Collections.reverse(movimientos);
		
		
		request.setAttribute("movimientos", movimientos);	
		request.setAttribute("saldos", saldosParciales);
		
		System.out.println("Cantidad de movimientos en servlet: " + movimientos.size());
		
		
	     // Manejo de paginación
        int registrosPorPagina = 10;
        int paginaActual = 1;

        // Obtener el número de pagina actual desde la request
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
     	   paginaActual = Integer.parseInt(pageParam);
        }

        // Calcular los indices para la sublista de Clientes
        int totalRecords = movimientos.size();
        int totalPaginas = (int) Math.ceil((double) totalRecords / registrosPorPagina);
        int startIndex = (paginaActual - 1) * registrosPorPagina;
        int endIndex = Math.min(startIndex + registrosPorPagina, totalRecords);

        // Sublista de Movimientos y saldos parciales para la pagina actual
        List<Movimiento> movimientosPagina = movimientos.subList(startIndex, endIndex);
        List<BigDecimal> saldosParcialesPagina = saldosParciales.subList(startIndex, endIndex);

        // Pasar atributos a la vista
        request.setAttribute("listaMovimientos", movimientosPagina);
        request.setAttribute("listaSaldosParciales", saldosParcialesPagina);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("paginaActual", paginaActual);
		
		
		
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
	
	

}
