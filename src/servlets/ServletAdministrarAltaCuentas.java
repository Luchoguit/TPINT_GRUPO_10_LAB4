package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuenta;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.SolicitudAltaCuentaNegocio;
import negocio.TipoCuentaNegocio;
import negocio.UsuarioNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.CuentaNegocioImp;
import negocioimplementacion.SolicitudAltaCuentaNegocioImp;
import negocioimplementacion.TipoCuentaNegocioImp;
import negocioimplementacion.UsuarioNegocioImp;


@WebServlet("/ServletAdministrarAltaCuentas")
public class ServletAdministrarAltaCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	SolicitudAltaCuentaNegocio solicitudNegocio = new SolicitudAltaCuentaNegocioImp();
	List<SolicitudAltaCuenta> listaSolicitudes = solicitudNegocio.listarSolicitudesSinResponder();	
	
	ClienteNegocio clienteNegocio = new ClienteNegocioImp();
	List<Cliente> listaClientes = clienteNegocio.listarClientes();
	
	TipoCuentaNegocio tipoCuentaNegocio = new TipoCuentaNegocioImp();
    List<TipoCuenta> listaTiposCuenta = tipoCuentaNegocio.ObtenerTiposCuenta();
	
    for (SolicitudAltaCuenta solicitud : listaSolicitudes) {
        int clienteId = solicitud.getCliente().getId(); 
        int tipoCuentaId = solicitud.getTipoCuenta().getId(); 

        // Buscamos el cliente correspondiente en la lista de clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == clienteId) { 
                solicitud.setCliente(cliente); 
                break; 
            }
        }
        // Buscamos el tipo de cuenta correspondiente en la lista de tipos de cuenta
        for (TipoCuenta tipoCuenta : listaTiposCuenta) {
            if (tipoCuenta.getId() == tipoCuentaId) { 
                solicitud.setTipoCuenta(tipoCuenta); 
                break; 
            }
        }
    }
	
	request.setAttribute("listaSolicitudes", listaSolicitudes);
	
    RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AdministrarAltaCuentas.jsp");
    dispatcher.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");
        int solicitudId = Integer.parseInt(request.getParameter("solicitudId"));    
        
        System.out.println("[DEBUG] Entra al doPost");
        System.out.println("[DEBUG] accion: " + accion);
        System.out.println("[DEBUG] id de solicitud: " + solicitudId);


        SolicitudAltaCuentaNegocio solicitudNegocio = new SolicitudAltaCuentaNegocioImp();
        SolicitudAltaCuenta solicitud = solicitudNegocio.obtenerSolicitudPorId(solicitudId);
        
        System.out.println("[DEBUG] id de objeto solicitud: " + solicitud.getId());


        try {
	    if ("aceptar".equals(accion)) {
	    	
	    	UsuarioNegocio userNegocio = new UsuarioNegocioImp();
	    	Usuario user = userNegocio.obtenerUsuarioPorId(solicitud.getCliente().getId());
	    	TipoCuenta tipoCuenta = solicitud.getTipoCuenta();
	    	
	        System.out.println("[DEBUG] tipo de cuenta: " + solicitud.getTipoCuenta().getDescripcion());

	    	
	    	CuentaNegocio cuentaNegocio = new CuentaNegocioImp();
	    	Cuenta cuenta = new Cuenta(user,tipoCuenta);
	    	
	        System.out.println("[DEBUG] id de cuenta: " + cuenta.getId());

	    	boolean resultadoAlta = cuentaNegocio.altaCuenta(cuenta);
	    	
	        System.out.println("[DEBUG] resultado alta cuenta: " + resultadoAlta);

	    } 

	    boolean resultadoRespuesta = solicitudNegocio.responderSolicitud(solicitudId);
        System.out.println("[DEBUG] resultado respuesta de solicitud: " + resultadoRespuesta);


        } catch (Exception e) {
            e.printStackTrace();
        }
        
	    doGet(request, response);
	
	}

}
