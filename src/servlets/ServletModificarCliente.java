package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import negocioimplementacion.ClienteNegocioImp;

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
        String dniCliente = request.getParameter("dniCliente");

        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        Cliente cliente = clienteNegocio.obtenerPorDNI(dniCliente);

        if (cliente != null) {
        	request.setAttribute("cliente", cliente);
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ModificarCliente.jsp");
            dispatcher.forward(request, response);
        } else {
           //manejar error
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String sexo = request.getParameter("sexo");
        String nacionalidad = request.getParameter("nacionalidad");
        String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
        String direccion = request.getParameter("direccion");
        String localidad = request.getParameter("localidad");
        String provincia = request.getParameter("provincia");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setCuil(cuil);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setSexo(sexo);
        cliente.setNacionalidad(nacionalidad);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
        
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setDireccion(direccion);
        
        int idLocalidad = request.getParameter("localidad") != null ? Integer.parseInt(request.getParameter("localidad")) : -1;
        int idProvincia = request.getParameter("provincia") != null ? Integer.parseInt(request.getParameter("provincia")) : -1;
        Localidad localidadCliente = new Localidad();
        localidadCliente.setId(idLocalidad);

        Provincia provinciaCliente = new Provincia();
        provinciaCliente.setId(idProvincia);
        
        cliente.setLocalidadCliente(localidadCliente);
        cliente.setProvinciaCliente(provinciaCliente);
        
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        boolean actualizado = clienteNegocio.actualizarCliente(cliente);

        if (actualizado) {
            response.sendRedirect("ListarClientes.jsp");
        } else {
            //manejamos error
        }
    }

}
