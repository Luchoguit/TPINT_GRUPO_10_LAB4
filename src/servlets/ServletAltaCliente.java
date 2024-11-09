package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.google.gson.Gson;


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
    	

        // Verificar si la solicitud es para cargar localidades
        String provinciaId = request.getParameter("provinciaId");
        if (provinciaId != null && !provinciaId.isEmpty()) {
            try {
                LocalidadNegocio localidadNegocio = new LocalidadNegocioImp();
                List<Localidad> localidades = localidadNegocio.listarPorProvincia(Integer.parseInt(provinciaId));

                // Convertir la lista de localidades a JSON
                Gson gson = new Gson();
                String json = gson.toJson(localidades);

                // Configurar el tipo de respuesta como JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (Exception e) {
                // Manejo de error si ocurre alguna excepción
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
                response.getWriter().write("{\"error\":\"Error al cargar las localidades\"}");
            }
            return;
        }
    	

     // Lógica para cargar provincias
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
        List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
        request.setAttribute("provincias", listaProvincias);

        // Reenvío al JSP para mostrar las provincias
        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
        dispatcher.forward(request, response);
        System.out.println("Cargando provincias en ServletAltaCliente");

    }
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro al doPost");

    	
    	String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String sexo = request.getParameter("sexo");
        String nacionalidad = request.getParameter("nacionalidad");
        String fechaNacimientoStr = request.getParameter("fecha_nacimiento"); 
        String direccion = request.getParameter("direccion");

 
        //int idLocalidad = Integer.parseInt(request.getParameter("id_localidad"));
        //int idProvincia = Integer.parseInt(request.getParameter("id_provincia"));

        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);


        Localidad localidadCliente = new Localidad(); 
        //localidadCliente.setId(idLocalidad);
        localidadCliente.setId(1);
        Provincia provinciaCliente = new Provincia(); 
        //provinciaCliente.setId(idProvincia);
         provinciaCliente.setId(1);
  
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

        ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
        boolean resultado = clienteNegocio.altaCliente(cliente);

        if (resultado) {
            System.out.println("Alta de cliente exitosa.");      
        } else {
        	
            System.out.println("Error en el alta de cliente."); 
            
            ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
            List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
            request.setAttribute("provincias", listaProvincias);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/VentanasAdmin/AltaCliente.jsp");
            dispatcher.forward(request, response);
        	
        }
}


}
