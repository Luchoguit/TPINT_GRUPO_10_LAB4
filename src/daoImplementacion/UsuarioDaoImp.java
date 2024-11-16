package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.UsuarioDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoCuenta;
import entidad.Usuario;

public class UsuarioDaoImp implements UsuarioDao {

  
    private static final String qryInsert = "INSERT INTO usuarios (id, nombre_usuario, contrasenia, tipo, estado, fecha_creacion) "
                                          + "VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean altaUsuario(Usuario usuario) {
        PreparedStatement statement = null;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsert);
       
            statement.setInt(1, usuario.getIdCliente()); 
            statement.setString(2, usuario.getNombreUsuario());
            statement.setString(3, usuario.getContraseña());
            statement.setString(4, usuario.getTipoUsuario()); //
            statement.setBoolean(5, usuario.isEstado());
            statement.setTimestamp(6, Timestamp.valueOf(usuario.getFechaCreacion()));

           
            if (statement.executeUpdate() > 0) {
                conexion.commit(); 
                isInsertExitoso = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback(); 
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
        
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            Conexion.getConexion().cerrarConexion();
        }

        return isInsertExitoso;
    }

    private static final String qryGetUsuario = "SELECT id, nombre_usuario, contrasenia, tipo, estado, fecha_creacion FROM Usuarios WHERE nombre_usuario = ? AND contrasenia = ?";
    
	@Override
	public Usuario getUsuario(String nombre, String contrasenia) {

		Usuario user = null;

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        System.out.println("[DEBUG] Conexion a la base de datos establecida para obtener Usuario");

	        PreparedStatement statement = con.prepareStatement(qryGetUsuario);
	        statement.setString(1, nombre);
	        statement.setString(2, contrasenia);
	        

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nombreUsuario = resultSet.getString("nombre_usuario");
	            String contraseña = resultSet.getString("contrasenia");
	            String tipoUsuario = resultSet.getString("tipo");
	            boolean estado = resultSet.getBoolean("estado");
	            Timestamp sqlTimestamp = resultSet.getTimestamp("fecha_creacion");
	            LocalDateTime fechaCreacion = sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;

	            user = new Usuario(id, nombreUsuario, contraseña, tipoUsuario, estado, fechaCreacion);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return user;
	}

	
    private static final String qryObtenerUsuarioPorId = "SELECT id, nombre_usuario, contrasenia, tipo, estado, fecha_creacion FROM Usuarios WHERE id = ?";

    
	@Override
	public Usuario obtenerUsuarioPorId(int id) {
		Usuario user = null;

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        System.out.println("[DEBUG] Conexion a la base de datos establecida para obtener Usuario por Id");

	        PreparedStatement statement = con.prepareStatement(qryObtenerUsuarioPorId);
	        statement.setInt(1, id);

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String nombreUsuario = resultSet.getString("nombre_usuario");
	            String contraseña = resultSet.getString("contrasenia");
	            String tipo = resultSet.getString("tipo");
	            boolean estado = resultSet.getBoolean("estado");
	            Timestamp sqlTimestamp = resultSet.getTimestamp("fecha_creacion");
	            LocalDateTime fechaCreacion = sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;


	            user = new Usuario(id, nombreUsuario, contraseña, tipo, estado, fechaCreacion);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return user;
	}	
	
}