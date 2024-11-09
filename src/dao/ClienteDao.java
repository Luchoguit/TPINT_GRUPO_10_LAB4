package dao;

import java.util.List;

import entidad.Cliente;;

public interface ClienteDao {
	
	boolean altaCliente(Cliente cliente);
    List<Cliente> obtenerTodos();
    Cliente obtenerPorId(int id);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int id);
    
}