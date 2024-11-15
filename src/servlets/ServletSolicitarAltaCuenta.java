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
import entidad.Localidad;
import entidad.Provincia;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import entidad.Usuario;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocio.SolicitudAltaCuentaNegocio;
import negocio.TipoCuentaNegocio;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;
import negocioimplementacion.SolicitudAltaCuentaNegocioImp;
import negocioimplementacion.TipoCuentaNegocioImp;


@WebServlet("/ServletSolicitarAltaCuenta")
public class ServletSolicitarAltaCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L; 


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TipoCuentaNegocio tcNegocio = new TipoCuentaNegocioImp();
		List<TipoCuenta> listaTC = tcNegocio.ObtenerTiposCuenta();
		
		
		request.setAttribute("listaTC", listaTC);

        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasUser/SolicitarAltaCuenta.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			TipoCuentaNegocio tcNegocio = new TipoCuentaNegocioImp();
			
			int id_tipoCuenta = request.getParameter("tipoCuenta") != null ? Integer.parseInt(request.getParameter("tipoCuenta")) : -1;
			
			TipoCuenta tipoCuenta = tcNegocio.ObtenerPorId(id_tipoCuenta);
			Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
			
			
			SolicitudAltaCuenta solicitudAC = new SolicitudAltaCuenta(cliente,tipoCuenta,false);
			
			SolicitudAltaCuentaNegocio solicitudNegocio = new SolicitudAltaCuentaNegocioImp();

			boolean registroExitoso = solicitudNegocio.registrarSolicitud(solicitudAC);
			if (registroExitoso) {
			    request.setAttribute("mensaje", "La solicitud de alta de cuenta se ha registrado exitosamente.");
			    request.setAttribute("tipoMensaje", "success");
			    doGet(request, response);
                return;
			
			} else {
			    request.setAttribute("mensaje", "Hubo un problema al registrar la solicitud. Intente nuevamente.");
			    request.setAttribute("tipoMensaje", "error");
			    doGet(request, response);
                return;
			}
		
	} catch (Exception e) {
        e.printStackTrace();   

        doGet(request, response);
    }
	}
	}
