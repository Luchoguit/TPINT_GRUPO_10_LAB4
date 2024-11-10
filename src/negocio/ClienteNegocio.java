package negocio;

import java.util.List;

import entidad.Cliente;

public interface ClienteNegocio {
    List<Cliente> listarClientes();
    boolean altaCliente(Cliente cliente);
    List<Cliente> listarClientesSinUsuario();
}
