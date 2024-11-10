package negocioimplementacion;

import dao.UsuarioDao;
import daoImplementacion.UsuarioDaoImp;
import entidad.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImp implements UsuarioNegocio {

  
    private UsuarioDao usuarioDao = new UsuarioDaoImp();

    @Override
    public boolean altaUsuario(Usuario usuario) {
        return usuarioDao.altaUsuario(usuario);
    }
}
