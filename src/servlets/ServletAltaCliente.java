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
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;


@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	// Carga de provincias
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
        List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
        request.setAttribute("provincias", listaProvincias);
        
        // Carga de localidades
        LocalidadNegocio localidadesNegocio = new LocalidadNegocioImp();
        List<Localidad> listaLocalidades = localidadesNegocio.listarLocalidades();
        request.setAttribute("localidades", listaLocalidades);

        // Reenvío al JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
        dispatcher.forward(request, response);
        

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String dni = request.getParameter("dni");
            String cuil = request.getParameter("cuil");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String sexo = request.getParameter("sexo");
            String nacionalidad = request.getParameter("nacionalidad");
            String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
            String direccion = request.getParameter("direccion");
            

            // Conversión/obtención de IDs de localidad y provincia
            int idLocalidad = request.getParameter("localidad") != null ? Integer.parseInt(request.getParameter("localidad")) : -1;
            int idProvincia = request.getParameter("provincia") != null ? Integer.parseInt(request.getParameter("provincia")) : -1;

            
            String correo = request.getParameter("correo");
            String telefono = request.getParameter("telefono");

            
            // Conversión de fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);


            // Creación de objetos Localidad y Provincia
            Localidad localidadCliente = new Localidad();
            localidadCliente.setId(idLocalidad);

            Provincia provinciaCliente = new Provincia();
            provinciaCliente.setId(idProvincia);

            // Configuración del cliente
            Cliente cliente = new Cliente();
            cliente.setDni(dni);
            cliente.setCuil(cuil);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setSexo(sexo);
            cliente.setNacionalidad(nacionalidad);
            cliente.setFechaNacimiento(fechaNacimiento);
            cliente.setDireccion(direccion);
            cliente.setLocalidadCliente(localidadCliente);
            cliente.setProvinciaCliente(provinciaCliente);
            cliente.setCorreo(correo);
            cliente.setTelefono(telefono);
            


            // Guardar cliente
            ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
            boolean resultado = clienteNegocio.altaCliente(cliente);

            if (resultado) {
                System.out.println("Alta de cliente exitosa.");
            } else {
                System.out.println("Error en el alta de cliente.");

                // Carga de provincias
                ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
                List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
                request.setAttribute("provincias", listaProvincias);
                
                // Carga de localidades
                LocalidadNegocio localidadesNegocio = new LocalidadNegocioImp();
                List<Localidad> listaLocalidades = localidadesNegocio.listarLocalidades();
                request.setAttribute("localidades", listaLocalidades);

                // Reenvío al JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
                        
            // Carga de provincias
            ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
            List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
            request.setAttribute("provincias", listaProvincias);
            
            // Carga de localidades
            LocalidadNegocio localidadesNegocio = new LocalidadNegocioImp();
            List<Localidad> listaLocalidades = localidadesNegocio.listarLocalidades();
            request.setAttribute("localidades", listaLocalidades);

            // Reenvío al JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
            dispatcher.forward(request, response);
        }
    }



}
