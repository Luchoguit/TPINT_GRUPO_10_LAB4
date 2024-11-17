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

import entidad.Cliente;
import entidad.Cuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;

@WebServlet("/ServletListadoCuentasEliminadas")
public class ServletListadoCuentasEliminadas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	
    	CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        String filtroCliente = request.getParameter("filtroCliente");
    
        List<Cuenta> cuentas = cuentaNegocio.listarTodasLasCuentasEliminadas(); 
        List<Cliente> clientes = clienteNegocio.listarClientes(); 
        
        
        List<Cliente> clientesFiltrados = new ArrayList<>();
        List<Cuenta> cuentasFiltradas = new ArrayList<>();
        
        if (filtroCliente != null && !filtroCliente.trim().isEmpty()) {

            for (Cliente cliente : clientes) {
                if (cliente.getDni().contains(filtroCliente) ||
                    cliente.getNombre().toLowerCase().contains(filtroCliente.toLowerCase()) ||
                    cliente.getApellido().toLowerCase().contains(filtroCliente.toLowerCase())) {
                    
                   
                    clientesFiltrados.add(cliente);

                    
                    for (Cuenta cuenta : cuentas) {
                        if (cuenta.getUsuario().getIdCliente() == cliente.getId()) {
                           
                            cuentasFiltradas.add(cuenta);
                        }
                    }
                }
            }
        } else {
          
            clientesFiltrados = clientes;
            cuentasFiltradas = cuentas;
        }

        
        request.setAttribute("clientesFiltrados", clientesFiltrados);
        request.setAttribute("cuentasFiltradas", cuentasFiltradas);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoCuentasEliminadas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	
    		CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
    		String idclientestring = request.getParameter("idCliente");
    		int idCliente = 0; 
    	
    	    if (idclientestring != null && !idclientestring.isEmpty()) {
    	        idCliente = Integer.parseInt(idclientestring);}
    	
    	
    	int cuentasActivas = cuentaNegocio.contarCuentasActivasPorUsuario(idCliente);
    	
    	System.out.println("[DEBUG] id cliente: " + idCliente);
    	
        if (cuentasActivas >= 3) {
        
        	request.setAttribute("mensaje", "El cliente ya tiene 3 cuentas activas.");
            request.setAttribute("tipoMensaje", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/AdministrarAltaCuentas.jsp");
            dispatcher.forward(request, response);
            return;
        }
    	
    	
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

        
        boolean resultado = cuentaNegocio.ActivarCuenta(idCuenta);

        if(resultado) {
        	request.setAttribute("mensaje", "Cuenta Habilitada exitosamente");
            request.setAttribute("tipoMensaje", "success");
        }
        System.out.println("[DEBUG] resultado: " + resultado);

        doGet(request, response);
    }
    
    
}