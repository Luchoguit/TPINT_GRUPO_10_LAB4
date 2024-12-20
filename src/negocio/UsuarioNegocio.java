package negocio;

import java.util.List;

import entidad.Usuario;

public interface UsuarioNegocio {
	
	boolean altaUsuario(Usuario usuario);
	Usuario getUsuario(String nombre, String contrasenia);
	Usuario obtenerUsuarioPorId(int id);

	boolean actualizarUser(Usuario usuario);
}