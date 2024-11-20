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


@WebServlet("/ServletModificarCliente")
public class ServletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idClienteStr = request.getParameter("idcliente");
    	    
    	int idCliente = Integer.parseInt(idClienteStr);

        ClienteNegocio clienteNegocio = new ClienteNegocioImp();
        Cliente cliente = clienteNegocio.obtenerPorId(idCliente);
        LocalidadNegocio localidadNegocio = new LocalidadNegocioImp();
        ProvinciaNegocio provinciaNegocio = new ProvinciaNegocioImp();

        if (cliente != null) {
               
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parametros del formulario
		String idClienteStr = request.getParameter("id");
	    
	    int idCliente = Integer.parseInt(idClienteStr);
		
	    //variables para verificar si se modifica cuil, dni, emial, telefono.
	    String dniOriginal = request.getParameter("dniOriginal");
	    String cuilOriginal = request.getParameter("cuilOriginal");
	    String correoOriginal = request.getParameter("emailOriginal");
	    String telOriginal = request.getParameter("telOriginal");
	    
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
        Cliente cliente = clienteNegocio.obtenerPorId(idCliente); // Obtener el cliente existente

        if (cliente != null) {
            // verificamos datos irrepetibles            
            boolean actualizado = false;
            boolean dni_repetido = false;
            boolean cuil_repetido = false;
            boolean tel_repetido = false;
            boolean emailRepetido = false;
            
            if (!dni.equals(dniOriginal)) {
            	//si son distintas, se quiere modificar dni, entonces
            	//verifico que el nuevo dni no exista en BD            
                if(clienteNegocio.verificarDniIngresado(dni)){
                	//el dni ingresado ya existe en BD.
                	dni_repetido = true;
                }
            }
            
            if (!cuil.equals(cuilOriginal)) {
            	//si son distintas, se quiere modificar cuil, entonces
            	//verifico que el nuevo cuil no exista en BD    
            	if(clienteNegocio.verificarCuilIngresado(cuil)) {
                	//el cuil ingresado ya existe en BD.
                	cuil_repetido = true;
                }
            }
            
            if(!telefono.equals(telOriginal)) {
            	if (clienteNegocio.verificarTelefonoIngresado(telefono)) {
	                tel_repetido = true;
	            }
            }
	            
            if(!correo.equals(correoOriginal)) {
            	if (clienteNegocio.verificarEmailIngresado(correo)) {
            		emailRepetido = true;
	            }
            }
            
            
            if(dni_repetido == false && cuil_repetido == false && tel_repetido == false && emailRepetido == false) {
            	//no hay datos repetidos, actualizamos el cliente.
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
            	cliente.setDni(dni);
                cliente.setCuil(cuil);
            	
            	actualizado = clienteNegocio.actualizarCliente(cliente);
            }
            	
            if (actualizado) {
                // Redirigir o mostrar un mensaje de exito
                request.setAttribute("mensaje", "Cliente actualizado correctamente.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletListadoClientes");
                request.setAttribute("tipoMensaje", "success");
                dispatcher.forward(request, response);
            } 
            
            else {
            	//armo mensaje de error dependiendo de los campos repetidos.
            	String mensajeError = "Error. Dato repetido en sistema: ";
            	if (dni_repetido) {
            	    mensajeError += "DNI";
            	}
            	if (cuil_repetido) {
            	    if (dni_repetido) {
            	        mensajeError += " , ";
            	    }
            	    mensajeError += "CUIL";
            	}
            	if (tel_repetido) {
            	    if (dni_repetido || cuil_repetido) {
            	        mensajeError += " , ";
            	    }
            	    mensajeError += "Telefono";
            	}
            	
            	if (emailRepetido) {
            	    if (dni_repetido || cuil_repetido || tel_repetido) {
            	        mensajeError += " , ";
            	    }
            	    mensajeError += "Email";
            	}
            	
            	
            	//envio mensaje de error a la vista
            	request.setAttribute("mensaje", mensajeError);
            	request.setAttribute("tipoMensaje", "error");
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
            request.setAttribute("error", "No se encontro el cliente con el DNI proporcionado.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletListadoClientes");
            dispatcher.forward(request, response);
        }
    }
    
}

