package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
		
		String tipoMovimientoParam = request.getParameter("tipoMovimiento");
		String fechaDesdeParam = request.getParameter("fechaDesde");
		String fechaHastaParam = request.getParameter("fechaHasta");
		String montoMinParam = request.getParameter("montoMin");
		String montoMaxParam = request.getParameter("montoMax");
		
		Boolean esFiltro = false;
		
		if (tipoMovimientoParam != null || fechaDesdeParam != null || fechaHastaParam != null || montoMinParam != null || montoMaxParam != null)
		{
			movimientos = filtrarMovimientos(movimientos, tipoMovimientoParam, fechaDesdeParam, fechaHastaParam, montoMinParam, montoMaxParam);
			 esFiltro = true;
		}
		
		request.setAttribute("esFiltro", esFiltro);
		request.setAttribute("movimientos", movimientos);
		request.setAttribute("saldos", saldosParciales);
		
		System.out.println("Cantidad de movimientos en servlet: " + movimientos.size());
		
		
	     // Manejo de paginación
        int registrosPorPagina = 5;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<BigDecimal> actualizarSaldosInvertidos(List<Movimiento> movimientos, Cuenta cuentaSeleccionada)
	{
		BigDecimal acumulador = BigDecimal.ZERO;
		int tipo_movimiento_alta_prestamo = 2;
		int tipo_movimiento_alta_cuenta = 1;
		int tipo_movimiento_pago_prestamo = 3;


		System.out.println("Id cuenta seleccionada: " + cuentaSeleccionada.getId());
		
		List<BigDecimal> saldosParciales = new ArrayList<>();
		
		for (Movimiento m : movimientos) {
		    System.out.println("Id cuenta origen: " + m.getCuentaOrigen().getId());
		    System.out.println("Id cuenta destino: " + m.getCuentaDestino().getId());
		    System.out.println("importe: " + m.getImporte());

		    boolean movimientoProcesado = false;
		    String saltoLinea = System.lineSeparator();


		    if (m.getTipoMovimiento().getId() == tipo_movimiento_alta_cuenta) {
		        acumulador = acumulador.add(m.getImporte());
		        System.out.println("alta cuenta (suma)");
		        movimientoProcesado = true;
		    }

		    if (m.getTipoMovimiento().getId() == tipo_movimiento_alta_prestamo) {
		        acumulador = acumulador.add(m.getImporte());
		        System.out.println("alta préstamo (suma)");
		        movimientoProcesado = true;
		    }

		    if (m.getCuentaDestino().getId() == cuentaSeleccionada.getId()) {
		        acumulador = acumulador.add(m.getImporte());
		        System.out.println("recibe transferencia (suma)");
		        if (m.getDetalle().isEmpty()) {
		            m.setDetalle("Transferencia recibida de: " + 
		                         m.getCuentaOrigen().getUsuario().getCliente().getNombre() + " " + 
		                         m.getCuentaOrigen().getUsuario().getCliente().getApellido() + saltoLinea + 
		                         "Cuenta: " + m.getCuentaOrigen().getNumeroCuenta());
		        } else {
		            m.setDetalle("Transferencia recibida de: " + 
		                         m.getCuentaOrigen().getUsuario().getCliente().getNombre() + " " + 
		                         m.getCuentaOrigen().getUsuario().getCliente().getApellido() + saltoLinea + 
		                         "Cuenta: " + m.getCuentaOrigen().getNumeroCuenta() + saltoLinea + 
		                         "Detalle: " + m.getDetalle());
		        }
		        
		        movimientoProcesado = true;
		    }

		    if (m.getTipoMovimiento().getId() == tipo_movimiento_pago_prestamo) {
		        acumulador = acumulador.subtract(m.getImporte());
		        System.out.println("paga préstamo (resta)");
		        movimientoProcesado = true;
		    }

		    if (!movimientoProcesado) {
		        acumulador = acumulador.subtract(m.getImporte());
		        System.out.println("realiza transferencia (resta)");
		        if(m.getDetalle().isEmpty())
		        {
		        	m.setDetalle("Transferencia realizada a: " + 
		                    m.getCuentaDestino().getUsuario().getCliente().getNombre() + " " + 
		                    m.getCuentaDestino().getUsuario().getCliente().getApellido() + saltoLinea + 
		                    "Cuenta: " + m.getCuentaDestino().getNumeroCuenta());  			        	
		        } else {
		        	m.setDetalle("Transferencia realizada a: " + 
		        			m.getCuentaDestino().getUsuario().getCliente().getNombre() + " " + 
		                    m.getCuentaDestino().getUsuario().getCliente().getApellido() + saltoLinea + 
		                    "Cuenta: " + m.getCuentaDestino().getNumeroCuenta() + saltoLinea + 
		                    "Detalle: " + m.getDetalle());
  	
		        }
		        
		    }

		    System.out.println("------------------");
		    saldosParciales.add(acumulador);
		}

		
		Collections.reverse(saldosParciales);
		return saldosParciales;
	}
	
	private List<Movimiento> filtrarMovimientos(List<Movimiento> movimientos, String tipoMovimientoParam, String fechaDesdeParam, String fechaHastaParam, String montoMinParam, String montoMaxParam)
	{
		List<Movimiento> movimientosFiltrados = new ArrayList<>();

		for (Movimiento movimiento : movimientos) {
		    boolean agregar = true;

		    // Filtro por tipo de movimiento
		    if (tipoMovimientoParam != null && !tipoMovimientoParam.isEmpty()) {
		        int tipoMovimientoFiltro = Integer.parseInt(tipoMovimientoParam);
		        if (movimiento.getTipoMovimiento().getId() != tipoMovimientoFiltro) {
		            agregar = false;
		        }
		    }

		    // Filtro por rango de fechas
		    
		  
		    
		    if (fechaDesdeParam != null && !fechaDesdeParam.isEmpty()) {
		    	
			        LocalDateTime fechaDesde = LocalDate.parse(fechaDesdeParam).atStartOfDay();
			        if (movimiento.getFechaHora().isBefore(fechaDesde)) {
			            agregar = false;
			        }
		    	}
	
		    

		    if (fechaHastaParam != null && !fechaHastaParam.isEmpty()) {
		    	
			        LocalDateTime fechaHasta = LocalDate.parse(fechaHastaParam).atTime(23, 59, 59);
			        if (movimiento.getFechaHora().isAfter(fechaHasta)) {
			            agregar = false;
			        }
		    }

		    // Filtro por rango de montos
		    if (montoMinParam != null && !montoMinParam.isEmpty()) {
		        BigDecimal montoMin = new BigDecimal(montoMinParam);
		        if (movimiento.getImporte().compareTo(montoMin) < 0) {
		            agregar = false;
		        }
		    }

		    if (montoMaxParam != null && !montoMaxParam.isEmpty()) {
		        BigDecimal montoMax = new BigDecimal(montoMaxParam);
		        if (movimiento.getImporte().compareTo(montoMax) > 0) {
		            agregar = false;
		        }
		    }

		    if (agregar) {
		        movimientosFiltrados.add(movimiento);
		    }
		}

		return movimientosFiltrados;		
	}

}
