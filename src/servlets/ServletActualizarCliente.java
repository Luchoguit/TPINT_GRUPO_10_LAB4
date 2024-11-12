package servlets;

import java.io.IOException;
import java.time.LocalDate;
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
 * Servlet implementation class ServletActualizarCliente
 */
@WebServlet("/ServletActualizarCliente")
public class ServletActualizarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletActualizarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String sexo = request.getParameter("sexo");
        String nacionalidad = request.getParameter("nacionalidad");
        String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
        String direccion = request.getParameter("direccion");
        String provinciaIdStr = request.getParameter("provincia");
        String localidadIdStr = request.getParameter("localidad");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");

        // Validar y convertir los datos necesarios
        int provinciaId = Integer.parseInt(provinciaIdStr);
        int localidadId = Integer.parseInt(localidadIdStr);

        LocalDate fechaNacimiento = null;
        if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
        }

        // Obtener las instancias de Provincia y Localidad
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
        LocalidadNegocio localidadNegocio = new LocalidadNegocioImp();
        Provincia provincia = provinciaNegocio.obtenerProvinciaPorId(provinciaId);
        Localidad localidad = localidadNegocio.obtenerLocalidadPorId(localidadId);

        // Crear y configurar el objeto Cliente
        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        Cliente cliente = clienteNegocio.obtenerPorDNI(dni); // Obtener el cliente existente

        if (cliente != null) {
            // Actualizar los datos del cliente
            cliente.setCuil(cuil);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setSexo(sexo);
            cliente.setNacionalidad(nacionalidad);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setDireccion(direccion);
            cliente.setProvinciaCliente(provincia);
            cliente.setLocalidadCliente(localidad);
            cliente.setCorreo(correo);
            cliente.setTelefono(telefono);

            // Actualizar el cliente en la base de datos
            boolean actualizado = clienteNegocio.actualizarCliente(cliente);

            if (actualizado) {
                // Redirigir o mostrar un mensaje de éxito
                request.setAttribute("mensaje", "Cliente actualizado correctamente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListadoClientes.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Ocurrió un error al actualizar el cliente.");


                request.setAttribute("cliente", cliente);

                ProvinciaNegocio provinciaNegocios = new ProvinciaNegocioImp();
                LocalidadNegocio localidadNegocios = new LocalidadNegocioImp();
                List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
                List<Localidad> listaLocalidades = localidadNegocio.listarLocalidades();
                request.setAttribute("listaProvincias", listaProvincias);
                request.setAttribute("listaLocalidades", listaLocalidades);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ModificarCliente.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Manejar el caso en que no se encuentra el cliente
            request.setAttribute("error", "No se encontró el cliente con el DNI proporcionado.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/ListaClientes.jsp");
            dispatcher.forward(request, response);
        }
    }

}
