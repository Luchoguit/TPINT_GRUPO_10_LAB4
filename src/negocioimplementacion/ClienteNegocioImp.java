package negocioimplementacion;

import java.util.List;
import dao.ClienteDao;
import daoImplementacion.ClienteDaoImp;
import entidad.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImp implements ClienteNegocio {

    ClienteDao clienteDao = new ClienteDaoImp();

    @Override
    public List<Cliente> listarClientes() {
        return clienteDao.listarClientes();
    }

    @Override
    public boolean altaCliente(Cliente cliente) {
        return clienteDao.altaCliente(cliente);
    }

   
    @Override
    public List<Cliente> listarClientesSinUsuario() {
        return clienteDao.listarClientesSinUsuario();
    }
    
    public boolean verificarDniIngresado(String dni)
    {
    	return clienteDao.verificarDniIngresado(dni);
    }
    
    public boolean verificarCuilIngresado(String cuil)
    {
    	return clienteDao.verificarCuilIngresado(cuil);
    }
    
    public boolean verificarEmailIngresado(String email) {
    	return clienteDao.verificarEmailIngresado(email);
    }
    
    public boolean verificarTelefonoIngresado(String telefono)
    {
    	return clienteDao.verificarTelefonoIngresado(telefono);
    }
    
    @Override
	public Cliente obtenerPorDNI(String dni) {
		return clienteDao.obtenerPorDNI(dni);
	}

	@Override
	public boolean actualizarCliente(Cliente cliente) {
		return clienteDao.actualizarCliente(cliente);
	}

	@Override
	public boolean eliminarCliente(int id) {
		return clienteDao.eliminarCliente(id);
	}
}
