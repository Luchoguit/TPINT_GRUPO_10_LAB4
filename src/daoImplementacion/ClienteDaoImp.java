package daoImplementacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;

public class ClienteDaoImp implements ClienteDao {

	private static final String qryInsert = "INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, localidad, correo, telefono) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public boolean altaCliente(Cliente cliente) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    boolean isInsertExitoso = false;

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

	private static final String qrylistarclientes = "SELECT id, dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono FROM clientes";
	
	@Override
	public List<Cliente> listarClientes() {
	
	    List<Cliente> clientes = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();	
	        System.out.println("[DEBUG] Conexión a la base de datos establecida");

	        System.out.println("[DEBUG] Conexión establecida: " + con);

	        PreparedStatement statement = con.prepareStatement(qrylistarclientes);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	            String dni = resultSet.getString("dni");
	            String cuil = resultSet.getString("cuil");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            String sexo = resultSet.getString("sexo");
	            String nacionalidad = resultSet.getString("nacionalidad");
	            java.sql.Date sqlDate = resultSet.getDate("fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;

	            
	            Localidad localidadCliente = new Localidad();
	            Provincia provinciaCliente = new Provincia();
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));

	            
	            String correo = resultSet.getString("correo");
	            String telefono = resultSet.getString("telefono");
	            String direccion = resultSet.getString("direccion");
	        
	            Cliente cli = new Cliente(id, dni, cuil, nombre, apellido, sexo, nacionalidad, 
	            		fechaNacimiento, direccion, localidadCliente, provinciaCliente, correo, telefono);
	            clientes.add(cli);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return clientes;
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