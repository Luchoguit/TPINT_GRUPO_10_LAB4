package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exceptions.MailInvalidoException;
import Exceptions.TelefonoInvalidoException;
import Exceptions.ValidadorMail;
import Exceptions.ValidadorTelefono;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.Usuario;
import negocio.LocalidadNegocio;
import negocio.ProvinciaNegocio;
import negocioimplementacion.ClienteNegocioImp;
import negocioimplementacion.LocalidadNegocioImp;
import negocioimplementacion.ProvinciaNegocioImp;
import utilidades.Mensaje;
import utilidades.ValidarSesion;


@WebServlet("/ServletAltaCliente")
public class ServletAltaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ValidarSesion.validarAdministrador(request, response)) {
            return; 
        }
				
	    // Preguntamos si no hay provincia seleccionada (primera carga de la pagina)
		// De ser asi, cargamos solo las provincias en el desplegable
	    if (request.getParameter("provinciaId") == null) {
	    	
	        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
	        List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
	        request.setAttribute("provincias", listaProvincias);
	        
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
	        dispatcher.forward(request, response);
	        
	    } else {
	    	
	        //Si entramos aqui es porque hay una provincia seleccionada, entonces
	    	// procedemos a cargar las localidades
	    	
	        LocalidadNegocio localidadesNegocio = new LocalidadNegocioImp();

	        int provinciaId = Integer.parseInt(request.getParameter("provinciaId"));
	        
	        //Establecemos que se devolvera como respuesta un json
	        response.setContentType("application/json");
	        
	        // El objeto se necesita para escribir la respuesta del servidor al cliente
	        PrintWriter out = response.getWriter();

	        try {
	            
	            List<Localidad> localidades = localidadesNegocio.listarPorProvincia(provinciaId);

	            // Creamos una respuesta JSON para devolver y luego convertir
	            // en un objeto javascript
	            // Este objeto sera un array con cada una de las localidades
	            
	            //Esta es una representacion de como se veria el resultado final
	            /*
	             * [
					    {"id": "1", "nombre": "Localidad 1"},
					    {"id": "2", "nombre": "Localidad 2"},
					    {"id": "3", "nombre": "Localidad 3"}
					]			
	             * 
	             * */
	            
	            
	            StringBuilder jsonResponse = new StringBuilder();
	            
	            // Iniciamos el json 
	            jsonResponse.append("[");

	            for (int i = 0; i < localidades.size(); i++) {
	                Localidad localidad = localidades.get(i);
	                jsonResponse.append("{")
	                        .append("\"id\": \"").append(localidad.getId()).append("\",")
	                        .append("\"nombre\": \"").append(localidad.getNombre()).append("\"")
	                        .append("}");

	                if (i < localidades.size() - 1) {
	                    jsonResponse.append(",");
	                }
	            }
	            
	            // Finalizamos el json
	            jsonResponse.append("]");

	            // Escribimos la respuesta json
	            out.print(jsonResponse.toString());

	        } catch (Exception e) {
	            e.printStackTrace();
	            
	            // En caso de errores con el json, devolvemos un array vacio
	            out.print("[]"); 
	        }
	    }
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
     
                
            
                
                
            //Validaciones
            ClienteNegocioImp clienteNegocio = new ClienteNegocioImp();
            
	            //Validar DNI no repetido
	            boolean dniRepetido = false;
	            
	            dniRepetido = clienteNegocio.verificarDniIngresado(dni);
	            	System.out.println("dni repetido: " + dniRepetido);
	            
	            //Validar CUIL no repetido
	            boolean cuilRepetido = false;
	            
	            cuilRepetido = clienteNegocio.verificarCuilIngresado(cuil);
	            System.out.println("cuil repetido: " + cuilRepetido);
	            
	            
	            //Validar correo no utilizado
	           
	            boolean emailRepetido = false;
	            
	            emailRepetido = clienteNegocio.verificarEmailIngresado(correo);
	            System.out.println("email repetido: " + emailRepetido);
	            
	            
	            //Validar telefono no utilizado
	            boolean telefonoRepetido = false;
	            
	            telefonoRepetido = clienteNegocio.verificarTelefonoIngresado(telefono);
	            System.out.println("telefono repetido: " + telefonoRepetido);
	            



            // Creación de objetos Localidad y Provincia
            Localidad localidadCliente = new Localidad();
            localidadCliente.setId(idLocalidad);

            Provincia provinciaCliente = new Provincia();
            provinciaCliente.setId(idProvincia);

            LocalDate fechaNacimiento = null;
            if (fechaNacimientoStr != null && !fechaNacimientoStr.isEmpty()) {
                fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
            }
            
            
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
            
            

// Validación del telefono
            
            ValidadorTelefono validadorTelefono = new ValidadorTelefono();
            
                try {
                    validadorTelefono.validar(telefono);
                } catch (TelefonoInvalidoException e) {
                   
                    Mensaje.error(request, e.getMessage());
                    cargarProvincias(request);  
                    pregarcarDatos(request, cliente);
                    doGet(request, response);  
                    return;
                }
                
                
             // Validación del correo
                
                ValidadorMail validadorMail = new ValidadorMail();
                try {
                    validadorMail.validarMail1(correo);
                } catch (MailInvalidoException e) {
                    Mensaje.error(request, e.getMessage());  
                    cargarProvincias(request); 
                    pregarcarDatos(request, cliente);
                    doGet(request, response);  
                    return;
                }
                
            
            
            

            // Guardar cliente
            boolean resultado = false;
            
            if(emailRepetido == false && dniRepetido == false && cuilRepetido == false && telefonoRepetido == false)
            {
            	resultado = clienteNegocio.altaCliente(cliente);            	
            }
            else
            {
            	
            	pregarcarDatos(request, cliente);
                
            }

            if (resultado) {
                Mensaje.exito(request,"Cliente creado exitosamente");
                System.out.println("Alta de cliente exitosa.");
                
                // Reenvío al JSP
                //RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
                //dispatcher.forward(request, response);
                doGet(request, response);
                return;
                
            } else {
            	
            	if(dniRepetido == true)
            	{
            		Mensaje.error(request, "Ya existe un cliente con ese DNI.");
                	System.out.println("Error: dni repetido");
            	}
            	else if(cuilRepetido == true)
            	{
            		Mensaje.error(request, "Ya existe un cliente con ese CUIL.");
                	System.out.println("Error: cuil repetido");
            	}
            	else if(emailRepetido == true)
            	{
            		Mensaje.error(request, "El correo ingresado ya esta utilizado.");
                	System.out.println("Error: correo repetido");
            	}
            	else if(telefonoRepetido == true)
            	{
            		Mensaje.error(request, "El telefono ingresado ya esta utilizado.");
                	System.out.println("Error: telefono repetido");
            	}
            	else
            	{
            		Mensaje.error(request, "Error en el alta de cliente.");
                    System.out.println("Error en el alta de cliente.");
            	}

                // Carga de provincias
                ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
                List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
                request.setAttribute("provincias", listaProvincias);
                
                // Carga de localidades
                LocalidadNegocio localidadesNegocio = new LocalidadNegocioImp();
                List<Localidad> listaLocalidades = localidadesNegocio.listarLocalidades();
                request.setAttribute("localidades", listaLocalidades);
                
                

                
                // Reenvío al JSP
                //RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
                //dispatcher.forward(request, response);
                doGet(request, response);
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
            //RequestDispatcher dispatcher = request.getRequestDispatcher("VentanasAdmin/AltaCliente.jsp");
            //dispatcher.forward(request, response);
            doGet(request, response);
        }
        
    }
    
    private void cargarProvincias(HttpServletRequest request) {
        // Cargar provincias
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();
        List<Provincia> listaProvincias = provinciaNegocio.listarProvincias();
        request.setAttribute("provincias", listaProvincias);
    }
    
    private void pregarcarDatos(HttpServletRequest request, Cliente cliente)
    {
    	//Guarda el ingreso del usuario para precargar los campos
    	
        request.setAttribute("precargaDni", cliente.getDni());
        request.setAttribute("precargaCuil", cliente.getCuil());
        request.setAttribute("precargaNombre", cliente.getNombre());
        request.setAttribute("precargaApellido", cliente.getApellido());
        request.setAttribute("precargaNacionalidad", cliente.getNacionalidad());
        request.setAttribute("precargaDireccion", cliente.getDireccion());
        request.setAttribute("precargaFechaNac", cliente.getFechaNacimiento());
        request.setAttribute("precargaSexo", cliente.getSexo());
        
        
        //request.setAttribute("precargaCorreo", cliente.getCorreo());
        //request.setAttribute("precargaTelefono", cliente.getTelefono());
        
        
        //request.setAttribute("precargaIdProvincia", idProvincia);
        
        //String sexo = request.getParameter("sexo");
        //String fechaNacimientoStr = request.getParameter("fecha_nacimiento");
    }

}
