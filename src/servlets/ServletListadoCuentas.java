package servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;

@WebServlet("/ServletListadoCuentas")
public class ServletListadoCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }
		    	
        CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
        String filtroCliente = request.getParameter("filtroCliente");

        List<Cuenta> cuentas = cuentaNegocio.listarTodasLasCuentas(); 

        List<Cuenta> cuentasFiltradas = new ArrayList<>();

        if (filtroCliente != null && !filtroCliente.trim().isEmpty()) {
            for (Cuenta cuenta : cuentas) {
                Cliente cliente = cuenta.getUsuario().getCliente(); 
                if (cliente.getDni().contains(filtroCliente) ||
                    cliente.getNombre().toLowerCase().contains(filtroCliente.toLowerCase()) ||
                    cliente.getApellido().toLowerCase().contains(filtroCliente.toLowerCase())) {
                    cuentasFiltradas.add(cuenta);
                }
            }
        } else {
            cuentasFiltradas = cuentas;
        }
        
     // Manejo de paginación
        int registrosPorPagina = 5;
        int paginaActual = 1;

        // Obtener el número de pagina actual desde la request
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
     	   paginaActual = Integer.parseInt(pageParam);
        }

        // Calcular los indices para la sublista de Clientes
        int totalRecords = cuentasFiltradas.size();
        int totalPaginas = (int) Math.ceil((double) totalRecords / registrosPorPagina);
        int startIndex = (paginaActual - 1) * registrosPorPagina;
        int endIndex = Math.min(startIndex + registrosPorPagina, totalRecords);

        // Sublista de clientes para la pagina actual
        List<Cuenta> cuentasPagina = cuentasFiltradas.subList(startIndex, endIndex);

        // Pasar atributos a la vista
        request.setAttribute("listaCuentas", cuentasPagina);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("paginaActual", paginaActual);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoCuentas.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
 
        int idCuenta = 0;
        String idCuentaParam = request.getParameter("idCuenta");

 
        if (idCuentaParam != null && !idCuentaParam.trim().isEmpty()) {
            try {
                idCuenta = Integer.parseInt(idCuentaParam);
            } catch (NumberFormatException e) {
             
                return;
            }
        } else {
            doGet(request, response);      
        }

  
        System.out.println("[DEBUG] id cuenta: " + idCuenta);

        CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
        boolean resultado = cuentaNegocio.eliminarCuenta(idCuenta);
        if (resultado) {
        Mensaje.exito(request, "Cuenta deshabilitada exitosamente.");

        } else 
        {
            Mensaje.error(request, "No se pudo deshabilitar la cuenta.");
        }
        System.out.println("[DEBUG] resultado: " + resultado);

        doGet(request, response);
    }
      
    
}