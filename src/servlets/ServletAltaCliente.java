/*package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import negocioimplementacion.ClienteNegocioImp;


@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        }
}


}*/
