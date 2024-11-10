package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.UsuarioDao;
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
            statement.setString(4, usuario.getTipo()); //
            statement.setBoolean(5, usuario.isEstado());
            statement.setTimestamp(6, java.sql.Timestamp.valueOf(usuario.getFechaCreacion()));

           
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
}