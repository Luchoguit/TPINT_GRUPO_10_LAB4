package dao;

import java.util.List;

import entidad.Cliente;;

public interface ClienteDao {
	
	boolean altaCliente(Cliente cliente);
    List<Cliente> listarClientes();
    List<Cliente> listarClientesSinUsuario();
    Cliente obtenerPorId(int id);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int id);
    
    boolean verificarDniIngresado(String dni);
    boolean verificarCuilIngresado(String cuil);
    boolean verificarEmailIngresado(String email);
    boolean verificarTelefonoIngresado(String telefono);
	
    
}