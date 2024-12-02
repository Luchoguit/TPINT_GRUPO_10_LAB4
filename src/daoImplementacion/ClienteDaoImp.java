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

	private static final String qrylistarclientes = "SELECT C.*, P.nombre AS nombreProv, L.nombre AS nombreLoc "
			+ "FROM clientes C "
			+ "JOIN provincias P ON P.id = id_provincia "
			+ "JOIN localidades L ON L.id = id_localidad";

	
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
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            localidadCliente.setNombre(resultSet.getString("nombreLoc"));
	            
	            Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));
	            provinciaCliente.setNombre(resultSet.getString("nombreProv"));

	            
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
			"SELECT C.*, P.nombre AS nombreProv, L.nombre AS nombreLoc " 
		    + "FROM clientes c "
			+ "JOIN provincias P ON P.id = id_provincia "
			+ "JOIN localidades L ON L.id = id_localidad "
		    + "LEFT JOIN usuarios u ON c.id = u.id "
		    + "WHERE u.id IS NULL";
 
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
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            localidadCliente.setNombre(resultSet.getString("nombreLoc"));
	            
	            Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));
	            provinciaCliente.setNombre(resultSet.getString("nombreProv"));

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
	    String sql = "UPDATE clientes SET dni = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, fecha_nacimiento = ?, direccion = ?, id_provincia = ?, id_localidad = ?, correo = ?, telefono = ? WHERE id = ?";
	    
	    try {
	        // 1. Obtener la conexion
	        conn = Conexion.getConexion().getSQLConexion();

	        // 2. Preparar el Statement
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, cliente.getDni()); // El DNI va primero
	        stmt.setString(2, cliente.getNombre());
	        stmt.setString(3, cliente.getApellido());
	        stmt.setString(4, cliente.getSexo());
	        stmt.setString(5, cliente.getNacionalidad());
	        stmt.setDate(6, java.sql.Date.valueOf(cliente.getFechaNacimiento()));
	        stmt.setString(7, cliente.getDireccion());
	        
	        // Manejar posibles valores nulos en Provincia y Localidad
	        if (cliente.getProvinciaCliente() != null) {
	            stmt.setInt(8, cliente.getProvinciaCliente().getId());
	        } else {
	            stmt.setNull(8, java.sql.Types.INTEGER);
	        }
	        
	        if (cliente.getLocalidadCliente() != null) {
	            stmt.setInt(9, cliente.getLocalidadCliente().getId());
	        } else {
	            stmt.setNull(9, java.sql.Types.INTEGER);
	        }
	        
	        stmt.setString(10, cliente.getCorreo());
	        stmt.setString(11, cliente.getTelefono());

	        // Usar el `id` del cliente como condicion
	        stmt.setInt(12, cliente.getId());

	        // 3. Ejecutar la actualizacion
	        int filasActualizadas = stmt.executeUpdate();

	        // 4. Confirmar la transaccin si las filas fueron afectadas
	        if (filasActualizadas > 0) {
	            conn.commit();
	        }

	        // Retornar si se realiz la actualizacion
	        return filasActualizadas > 0;
	    } catch (SQLException e) {
	        try {
	            if (conn != null) {
	                conn.rollback(); // Deshacer cambios en caso de error
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        // 5. Cerrar recursos
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) Conexion.getConexion().cerrarConexion();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            Conexion.getConexion().cerrarConexion(); // Cerrar la conexión
	        }
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
	
	private static final String qryActivarCliente = "UPDATE clientes SET estado = true WHERE id = ?";
	
	public boolean activarCliente (int id)
	{
		Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.getConexion().getSQLConexion();
            stmt = conn.prepareStatement(qryActivarCliente);
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
	
	private static final String qryObtenerClientePorDni =	
	    "SELECT C.*, P.nombre AS nombreProv, L.nombre AS nombreLoc " 
	    + "FROM clientes c "
		+ "JOIN provincias P ON P.id = id_provincia "
		+ "JOIN localidades L ON L.id = id_localidad "
	    + "WHERE c.dni = ?";
	
	
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
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            localidadCliente.setNombre(resultSet.getString("nombreLoc"));
	            
	            Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));
	            provinciaCliente.setNombre(resultSet.getString("nombreProv"));

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


	private static final String qryObtenerClientePorId = 	    
			"SELECT C.*, P.nombre AS nombreProv, L.nombre AS nombreLoc " 
		    + "FROM clientes c "
			+ "JOIN provincias P ON P.id = id_provincia "
			+ "JOIN localidades L ON L.id = id_localidad "
		    + "WHERE c.id = ?";

	@Override
	public Cliente obtenerPorId(int id) {
		Cliente cliente = null;

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        System.out.println("[DEBUG] Conexion a la base de datos establecida para obtener cliente por ID");
	        System.out.println("[DEBUG] ID Cliente: " + id);

	        PreparedStatement statement = con.prepareStatement(qryObtenerClientePorId);
	        statement.setInt(1, id); // Asignar el DNI al parametro de la consulta

	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String dni = resultSet.getString("dni");
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
	            localidadCliente.setId(resultSet.getInt("id_localidad"));
	            localidadCliente.setNombre(resultSet.getString("nombreLoc"));
	            
	            Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("id_provincia"));
	            provinciaCliente.setNombre(resultSet.getString("nombreProv"));

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
	
	@Override
	public boolean verificarCuentasyPrestamosActivos(int idCliente) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    String sql = "SELECT VerificarCuentasyPrestamosActivos(?) AS tieneActivos"; 
	    
	    try {
	        conn = Conexion.getConexion().getSQLConexion(); 
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idCliente);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("tieneActivos") == 1;
	        }
	        return false;  // Si no hay resultado, retornamos false
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            Conexion.getConexion().cerrarConexion();  
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
}