package dao;

import java.util.List;

import entidad.Cliente;;

public interface ClienteDao {
	
	
	Cliente obtenerPorDNI(String id);
	boolean altaCliente(Cliente cliente);
    List<Cliente> listarClientes();
    List<Cliente> listarClientesSinUsuario();
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int id);
    boolean activarCliente (int id);
    
    boolean verificarDniIngresado(String dni);
    boolean verificarCuilIngresado(String cuil);
    boolean verificarEmailIngresado(String email);
    boolean verificarTelefonoIngresado(String telefono);
    
    
	
    
}