package dao;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	
	boolean altaUsuario(Usuario Usuario);
   
	Usuario getUsuario(String nombre, String contrasenia);
	
	Usuario obtenerUsuarioPorId(int id);
	
	boolean actualizarUser(Usuario usuario);
    
}