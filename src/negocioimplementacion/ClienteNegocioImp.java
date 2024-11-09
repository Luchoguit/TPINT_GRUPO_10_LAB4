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
        return clienteDao.obtenerTodos();
    }

    @Override
    public boolean altaCliente(Cliente cliente) {
        return clienteDao.altaCliente(cliente);
    }
   
}
