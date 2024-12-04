package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import entidad.TipoMovimiento;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ValidarSesion.validarCliente(request, response)) {
            return; 
        }

        Cliente clienteSesion = (Cliente) request.getSession().getAttribute("cliente");
        Usuario usuarioSesion = (Usuario) request.getSession().getAttribute("usuario");
        Cuenta cuentaSesion = (Cuenta) request.getSession().getAttribute("cuenta");
        
        CuentaNegocio cuentaNegocio = new CuentaNegocioImp();

        // Lista de cuentas propias
        List<Cuenta> cuentasPropias = cuentaNegocio.listarCuentas(usuarioSesion);
        cuentasPropias.removeIf(c -> c.getCbu().equals(cuentaSesion.getCbu())); // Elimina la cuenta actual

        // Lista de destinatarios frecuentes
        List<Cuenta> destinatariosFrecuentes = cuentaNegocio.obtenerDestinatariosFrecuentes(cuentaSesion.getId());
		
        
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        Map<Cuenta, Cliente> cuentasConClientes = new HashMap<>();
        
        for (Cuenta cuenta : destinatariosFrecuentes) {
            Cliente cliente = clienteNegocio.obtenerPorId(cuenta.getUsuario().getCliente().getId());
            if (cliente != null) {
                cuentasConClientes.put(cuenta, cliente);
            } else {
                System.out.println("[DEBUG] Cliente nulo para la cuenta con CBU: " + cuenta.getCbu());
            }
        }

        request.setAttribute("clienteSesion", clienteSesion);
        request.setAttribute("cuentasConClientes", cuentasConClientes);
        request.setAttribute("cuentasPropias", cuentasPropias);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/Transferencias.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("btnCBU") != null) {
			
			String cbu = request.getParameter("inputCBU");
			
			
			System.out.println("CBU Ingresado: " + cbu);
			
			if(cbu.trim().isEmpty())
			{
				System.out.println("[DEBUG] CBU Invalido");
	            Mensaje.error(request, "Debe seleccionar un cbu Valido");
	            doGet(request, response);
				return;
			}
			
			CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
			boolean existeCuentaConCbu = cuentaNegocio.existeCuentaConCbu(cbu); 
						
			Cuenta cuentaCliente = (Cuenta) request.getSession().getAttribute("cuenta");
			
			
			if(existeCuentaConCbu == false)
			{
				System.out.println("[DEBUG] CBU Invalido");
	            Mensaje.error(request, "Debe seleccionar un cbu Valido");
	            doGet(request, response);
				return;
	            
			}
			else {
				System.out.println("[DEBUG] CBU valido");
			}
			
			if(cuentaCliente.getCbu().equals(cbu))
			{
				System.out.println("[DEBUG] Son el mismo CBU");

	            Mensaje.error(request, "No puede transferir a la misma cuenta en uso");	            
				request.setAttribute("mismaCuenta", "error");
				
			}
			else {
				System.out.println("[DEBUG] Son distintos CBU");
				
			}
			
			request.setAttribute("cbuDestino", cbu);

			Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorCBU(cbu);
			System.out.println("Numero de cuenta destino: " + cuentaDestino.getNumeroCuenta());			
			if (cuentaDestino != null) {
				request.setAttribute("cuentaDestino", cuentaDestino);
				ClienteNegocio clienteNegocio = new ClienteNegocioImp();
				Cliente clienteDestino = clienteNegocio.obtenerPorId(cuentaDestino.getUsuario().getCliente().getId());
				
				if(cuentaDestino!= null) {
					request.setAttribute("clienteDestino", clienteDestino);
				}
			}
			
		}
		
	    doGet(request, response);

	}
	

}
