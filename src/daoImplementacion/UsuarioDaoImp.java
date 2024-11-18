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
       
            statement.setInt(1, usuario.getCliente().getId()); 
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

    private static final String qryGetUsuario = "SELECT U.*, C.* "
    		+ "FROM usuarios U "
    		+ "JOIN clientes C ON C.id = U.id "
    		+ "WHERE U.nombre_usuario = ? AND U.contrasenia = ?";
    
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

	        	// CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSet.getInt("C.id"));
	        	cliente.setDni(resultSet.getString("C.dni"));
	        	cliente.setCuil(resultSet.getString("C.cuil"));
	        	cliente.setNombre(resultSet.getString("C.nombre"));
	        	cliente.setApellido(resultSet.getString("C.apellido"));
	        	cliente.setSexo(resultSet.getString("C.sexo"));
	        	cliente.setNacionalidad(resultSet.getString("C.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSet.getDate("C.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSet.getInt("C.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("C.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSet.getString("C.correo"));
	        	cliente.setTelefono(resultSet.getString("C.telefono"));
	        	cliente.setDireccion(resultSet.getString("C.direccion"));
	        	cliente.setEstado(resultSet.getBoolean("C.estado"));

	            String nombreUsuario = resultSet.getString("nombre_usuario");
	            String contraseña = resultSet.getString("contrasenia");
	            String tipoUsuario = resultSet.getString("tipo");
	            boolean estado = resultSet.getBoolean("estado");
	            Timestamp sqlTimestamp = resultSet.getTimestamp("fecha_creacion");
	            LocalDateTime fechaCreacion = sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;

	            user = new Usuario(cliente, nombreUsuario, contraseña, tipoUsuario, estado, fechaCreacion);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return user;
	}

	
    private static final String qryObtenerUsuarioPorId = "SELECT U.*, C.* "
    		+ "FROM usuarios U "
    		+ "JOIN clientes C ON C.id = U.id "
    		+ "WHERE U.id = ?";

    
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
	        	    	
	        	// CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSet.getInt("C.id"));
	        	cliente.setDni(resultSet.getString("C.dni"));
	        	cliente.setCuil(resultSet.getString("C.cuil"));
	        	cliente.setNombre(resultSet.getString("C.nombre"));
	        	cliente.setApellido(resultSet.getString("C.apellido"));
	        	cliente.setSexo(resultSet.getString("C.sexo"));
	        	cliente.setNacionalidad(resultSet.getString("C.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSet.getDate("C.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSet.getInt("C.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("C.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSet.getString("C.correo"));
	        	cliente.setTelefono(resultSet.getString("C.telefono"));
	        	cliente.setDireccion(resultSet.getString("C.direccion"));
	        	cliente.setEstado(resultSet.getBoolean("C.estado"));
	        	
	        	
	            String nombreUsuario = resultSet.getString("nombre_usuario");
	            String contraseña = resultSet.getString("contrasenia");
	            String tipo = resultSet.getString("tipo");
	            boolean estado = resultSet.getBoolean("estado");
	            Timestamp sqlTimestamp = resultSet.getTimestamp("fecha_creacion");
	            LocalDateTime fechaCreacion = sqlTimestamp != null ? sqlTimestamp.toLocalDateTime() : null;


	            user = new Usuario(cliente, nombreUsuario, contraseña, tipo, estado, fechaCreacion);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return user;
	}	
	
}