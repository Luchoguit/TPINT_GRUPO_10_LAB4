package negocio;

import java.util.List;

import entidad.Cliente;

public interface ClienteNegocio {
	
	
    List<Cliente> listarClientes();
    boolean altaCliente(Cliente cliente);
    List<Cliente> listarClientesSinUsuario();
    Cliente obtenerPorDNI(String dni);
    boolean eliminarCliente(int id);
    boolean activarCliente (int id);
    boolean verificarDniIngresado(String dni);
    boolean verificarCuilIngresado(String cuil);
    boolean verificarEmailIngresado(String email);
    boolean verificarTelefonoIngresado(String telefono);
	boolean actualizarCliente(Cliente cliente);
   

}
