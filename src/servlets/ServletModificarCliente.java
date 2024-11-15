package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import negocio.ClienteNegocio;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;

/**
 * Servlet implementation class ServletModificarCliente
 */
@WebServlet("/ServletModificarCliente")
public class ServletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 String idClienteStr = request.getParameter("idcliente");
    	    
    	    int idCliente = Integer.parseInt(idClienteStr);

        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        Cliente cliente = clienteNegocio.obtenerPorId(idCliente);
        LocalidadNegocio localidadNegocio = new LocalidadNegocioImp();
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();

        if (cliente != null) {
        	
        	 Localidad localidad = localidadNegocio.obtenerLocalidadPorId(cliente.getLocalidadCliente().getId());
             Provincia provincia = provinciaNegocio.obtenerProvinciaPorId(cliente.getProvinciaCliente().getId());
             cliente.setLocalidadCliente(localidad);
             cliente.setProvinciaCliente(provincia);
             
             //obtegno todas las provinicas y localidades
             List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
             List<Localidad> listaLocalidades = localidadNegocio.listarLocalidades();
             
             request.setAttribute("cliente", cliente);
             request.setAttribute("listaProvincias", listaProvincias);
             request.setAttribute("listaLocalidades", listaLocalidades);
             
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ModificarCliente.jsp");
            dispatcher.forward(request, response);
        } else {
           //manejar error
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
    

    }

