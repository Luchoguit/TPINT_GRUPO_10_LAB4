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

@WebServlet("/ServletListadoCuentas")
public class ServletListadoCuentas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        String filtroCliente = request.getParameter("filtroCliente");
    
        List<Cuenta> cuentas = cuentaNegocio.listarTodasLasCuentas(); 
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

        RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoCuentas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        doGet(request, response);
    }
}