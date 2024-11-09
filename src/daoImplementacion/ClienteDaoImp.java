package daoImplementacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import entidad.Cliente;

public class ClienteDaoImp implements ClienteDao {

	private static final String qryInsert = "INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, localidad, correo, telefono) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public boolean altaCliente(Cliente cliente) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    boolean isInsertExitoso = false;

	    String qryInsert = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	
	        statement = conexion.prepareStatement(qryInsert);


	        statement.setString(1, cliente.getDni());
	        statement.setString(2, cliente.getCuil());
	        statement.setString(3, cliente.getNombre());
	        statement.setString(4, cliente.getApellido());
	        statement.setString(5, String.valueOf(cliente.getSexo()));
	        statement.setString(6, cliente.getNacionalidad());
	        statement.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento())); 
	        statement.setString(8, cliente.getDireccion()); 
	        statement.setInt(9, cliente.getLocalidadCliente().getId()); 
	        statement.setInt(10, cliente.getProvinciaCliente().getId()); 
	        statement.setString(11, cliente.getCorreo());
	        statement.setString(12, cliente.getTelefono());


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
	        Conexion.getConexion().cerrarConexion(); 
	    }

	    return isInsertExitoso;
	}

	@Override
	public List<Cliente> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente obtenerPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarCliente(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}