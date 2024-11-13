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

	 private static final String qryInsert = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono) "
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    @Override
	    public boolean altaCliente(Cliente cliente) {
	        PreparedStatement statement;
	        Connection conexion = Conexion.getConexion().getSQLConexion();
	        boolean isInsertExitoso = false;

	        try {
	            statement = conexion.prepareStatement(qryInsert);

	            // Seteando los parámetros de la consulta
	            statement.setString(1, cliente.getDni());
	            statement.setString(2, cliente.getCuil());
	            statement.setString(3, cliente.getNombre());
	            statement.setString(4, cliente.getApellido());
	            statement.setString(5, String.valueOf(cliente.getSexo())); // 'M' o 'F'
	            statement.setString(6, cliente.getNacionalidad());
	            statement.setDate(7, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
	            statement.setString(8, cliente.getDireccion());
	            statement.setInt(9, cliente.getLocalidadCliente().getId()); // id_localidad
	            statement.setInt(10, cliente.getProvinciaCliente().getId()); // id_provincia
	            statement.setString(11, cliente.getCorreo());
	            statement.setString(12, cliente.getTelefono());

	            // Ejecución de la consulta
	            if (statement.executeUpdate() > 0) {
	                conexion.commit(); // Confirmar la transacción si todo va bien
	                isInsertExitoso = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            try {
	                conexion.rollback(); // Revertir la transacción en caso de error
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	        } finally {
	            Conexion.getConexion().cerrarConexion(); // Cerrar la conexión
	        }

	        return isInsertExitoso;
	    }

	private static final String qrylistarclientes = "SELECT id, dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono, estado FROM clientes";
	
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
	            boolean estado = resultSet.getBoolean("estado");

	        
	            Cliente cli = new Cliente(id, dni, cuil, nombre, apellido, sexo, nacionalidad, 
	            		fechaNacimiento, direccion, localidadCliente, provinciaCliente, correo, telefono, estado);
	            clientes.add(cli);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return clientes;
	}
	
	private static final String qryClientesSinUsuario = 
		    "SELECT c.id, c.dni, c.cuil, c.nombre, c.apellido, c.sexo, c.nacionalidad, " +
		    "c.fecha_nacimiento, c.direccion, c.id_localidad, c.id_provincia, c.correo, c.telefono, c.estado " +
		    "FROM clientes c " +
		    "LEFT JOIN usuarios u ON c.id = u.id " +
		    "WHERE u.id IS NULL";
 
	@Override
	public List<Cliente> listarClientesSinUsuario() {
	    List<Cliente> clientesSinUsuario = new ArrayList<>();
	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    try {
	        PreparedStatement statement = conexion.prepareStatement(qryClientesSinUsuario);
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
	            boolean estado = resultSet.getBoolean("estado");

	         
	            Cliente cliente = new Cliente(id, dni, cuil, nombre, apellido, sexo, nacionalidad, 
	                    fechaNacimiento, direccion, localidadCliente, provinciaCliente, correo, telefono, estado);
	            clientesSinUsuario.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
	    return clientesSinUsuario;
	}




	@Override
	public boolean actualizarCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, id_provincia = ?, id_localidad = ?, correo = ?, telefono = ? WHERE dni = ?";
        try {
            conn = Conexion.getConexion().getSQLConexion();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getNacionalidad());
            stmt.setDate(5, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
            stmt.setString(6, cliente.getDireccion());
            stmt.setInt(7, cliente.getProvinciaCliente().getId());
            stmt.setInt(8, cliente.getLocalidadCliente().getId());
            stmt.setString(9, cliente.getCorreo());
            stmt.setString(10, cliente.getTelefono());
            stmt.setString(11, cliente.getDni());

            int filasActualizadas = stmt.executeUpdate();
            conn.commit();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
	        Conexion.getConexion().cerrarConexion();
	    }
    }

	private static final String qryEliminarCliente = "UPDATE clientes SET estado = false WHERE id = ?";

	@Override
	public boolean eliminarCliente(int id) {
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            stmt = conn.prepareStatement(qryEliminarCliente);
            stmt.setInt(1, id);

            int filasActualizadas = stmt.executeUpdate();
            conn.commit();
            return filasActualizadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
	        Conexion.getConexion().cerrarConexion();
	    }
	}
	
	private static final String qryValidarEmailNoRepetido = "select * from clientes where correo like ?"; 
	
	@Override
	public boolean verificarEmailIngresado(String email)
	{
		boolean resultado = false;
		Cliente aux = null;
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
	        PreparedStatement statement = conexion.prepareStatement(qryValidarEmailNoRepetido);
	        statement.setString(1, email);
	        ResultSet resultSet = statement.executeQuery();
	        
	        while(resultSet.next())
	        {
	        	aux = new Cliente();
	        }
			
	        if(aux != null)
	        {
	        	resultado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
		return resultado;
		
	}
	
	private static final String qryValidarDniRepetido = "select * from clientes where dni like ?"; 

	@Override
	public boolean verificarDniIngresado(String dni)
	{
		boolean resultado = false;
		Cliente aux = null;
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
	        PreparedStatement statement = conexion.prepareStatement(qryValidarDniRepetido);
	        statement.setString(1, dni);
	        ResultSet resultSet = statement.executeQuery();
	        
	        while(resultSet.next())
	        {
	        	aux = new Cliente();
	        }
			
	        if(aux != null)
	        {
	        	resultado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
		return resultado;
		
	}
	
	private static final String qryValidarCuilRepetido = "select * from clientes where cuil like ?"; 
	
	@Override
	public boolean verificarCuilIngresado(String cuil)
	{
		boolean resultado = false;
		Cliente aux = null;
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
	        PreparedStatement statement = conexion.prepareStatement(qryValidarCuilRepetido);
	        statement.setString(1, cuil);
	        ResultSet resultSet = statement.executeQuery();
	        
	        while(resultSet.next())
	        {
	        	aux = new Cliente();
	        }
			
	        if(aux != null)
	        {
	        	resultado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
		return resultado;
		
	}
	
	private static final String qryValidarTelefonoRepetido = "select * from clientes where telefono like ?"; 
	
	@Override
	public boolean verificarTelefonoIngresado(String telefono)
	{
		boolean resultado = false;
		Cliente aux = null;
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		try {
	        PreparedStatement statement = conexion.prepareStatement(qryValidarTelefonoRepetido);
	        statement.setString(1, telefono);
	        ResultSet resultSet = statement.executeQuery();
	        
	        while(resultSet.next())
	        {
	        	aux = new Cliente();
	        }
			
	        if(aux != null)
	        {
	        	resultado = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
		return resultado;
		
	}
	
	private static final String qryObtenerClientePorDni = "SELECT id, dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, id_provincia, correo, telefono, estado FROM clientes WHERE dni = ?";	

	@Override
	public Cliente obtenerPorDNI(String dni) {
	    Cliente cliente = null;

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        System.out.println("[DEBUG] Conexion a la base de datos establecida para obtener cliente por DNI");

	        PreparedStatement statement = con.prepareStatement(qryObtenerClientePorDni);
	        statement.setString(1, dni); // Asignar el DNI al parametro de la consulta

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String cuil = resultSet.getString("cuil");
	            String nombre = resultSet.getString("nombre");
	            String apellido = resultSet.getString("apellido");
	            String sexo = resultSet.getString("sexo");
	            String nacionalidad = resultSet.getString("nacionalidad");
	            java.sql.Date sqlDate = resultSet.getDate("fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            String direccion = resultSet.getString("direccion");

	            // Crear los objetos Localidad y Provincia
	            Localidad localidadCliente = new Localidad();
	            Provincia provinciaCliente = new Provincia();
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));

	            String correo = resultSet.getString("correo");
	            String telefono = resultSet.getString("telefono");
	            boolean estado = resultSet.getBoolean("estado");


	            // Crear el objeto Cliente con los datos obtenidos
	            cliente = new Cliente(id, dni, cuil, nombre, apellido, sexo, nacionalidad, 
	                                  fechaNacimiento, direccion, localidadCliente, provinciaCliente, correo, telefono, estado);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return cliente;
	}	
	
	
}