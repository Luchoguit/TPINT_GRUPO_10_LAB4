package daoImplementacion;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.PrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Prestamo;
import entidad.Provincia;
import entidad.TipoCuenta;
import entidad.Usuario;

public class PrestamoDaoImp implements PrestamoDao {

	
	private static final String qryInsertPrestamo = "INSERT INTO prestamos (id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, importe_mensual, importe_final, cantidad_cuotas) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	
	@Override
	public boolean altaPrestamo(Prestamo prestamo) {

		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsertPrestamo);

            // Seteando los parámetros de la consulta
            statement.setInt(1, prestamo.getCliente().getId());
            statement.setInt(2, prestamo.getCuenta().getId());
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setBigDecimal(4, prestamo.getImportePedido());
            statement.setInt(5, prestamo.getPlazoMeses());
            statement.setBigDecimal(6, prestamo.getImporteMensual());
            statement.setBigDecimal(7, prestamo.getImporteFinal());
            statement.setInt(8, prestamo.getCantidadCuotas());

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

	private static final String qrylistarPrestamos = "SELECT P.*, Cl.*, Cu.* "
			+ "FROM prestamos P "
			+ "JOIN Solicitudes_Prestamos Sp ON Sp.id_prestamo = P.id_prestamo "
			+ "JOIN clientes Cl ON Cl.id = P.id_cliente "
			+ "JOIN cuentas Cu ON Cu.id = P.id_cuenta "
			+ "WHERE Sp.estado = 1 AND Cu.estado = 1 AND Cl.estado = 1";


	@Override
	public List<Prestamo> listarPrestamos() {

		List<Prestamo> listaPrestamos = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrylistarPrestamos);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
            
	            int idPrestamo = resultSet.getInt("P.id_prestamo");
	            
	            // CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSet.getInt("Cl.id"));
	        	cliente.setDni(resultSet.getString("Cl.dni"));
	        	cliente.setCuil(resultSet.getString("Cl.cuil"));
	        	cliente.setNombre(resultSet.getString("Cl.nombre"));
	        	cliente.setApellido(resultSet.getString("Cl.apellido"));
	        	cliente.setSexo(resultSet.getString("Cl.sexo"));
	        	cliente.setNacionalidad(resultSet.getString("Cl.nacionalidad"));
	        	cliente.setEstado(resultSet.getBoolean("Cl.estado"));
	        	
	        	java.sql.Date sqlDate = resultSet.getDate("Cl.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSet.getInt("Cl.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("Cl.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSet.getString("Cl.correo"));
	        	cliente.setTelefono(resultSet.getString("Cl.telefono"));
	        	cliente.setDireccion(resultSet.getString("Cl.direccion"));
	        	cliente.setEstado(resultSet.getBoolean("Cl.estado"));
	        	
	        	// CUENTA
	        	Cuenta cuenta = new Cuenta();
	        	cuenta.setId(resultSet.getInt("Cu.id"));
	        	cuenta.setNumeroCuenta(resultSet.getString("Cu.numero_cuenta"));
	        	cuenta.setFechaCreacion(resultSet.getTimestamp("Cu.fecha_creacion").toLocalDateTime());
	        	cuenta.setCbu(resultSet.getString("Cu.cbu"));
	        	cuenta.setSaldo(resultSet.getBigDecimal("Cu.saldo"));
	        	 
	        	Usuario usuario = new Usuario();
	        	usuario.setCliente(cliente);
	        	cuenta.setUsuario(usuario);
	        	
	        	TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("Cu.id_tipoCuenta"));
	        	cuenta.setTipoCuenta(tipoCuenta);
	        	
	            cuenta.setEstado(resultSet.getBoolean("Cu.estado"));

	        	
	        	// FECHAALTA
	            java.sql.Timestamp sqlTimestampAlta = resultSet.getTimestamp("P.fecha_alta");
	            LocalDateTime fechaAlta = sqlTimestampAlta != null ? sqlTimestampAlta.toLocalDateTime() : null;
        	
	        	
	        	BigDecimal importePedido = resultSet.getBigDecimal("P.importe_pedido");
	        	int plazoMeses = resultSet.getInt("P.plazo_meses");
	        	BigDecimal importeMensual = resultSet.getBigDecimal("P.importe_mensual");
	        	BigDecimal importeFinal = resultSet.getBigDecimal("P.importe_final");
	        	int cantidadCuotas = resultSet.getInt("P.cantidad_cuotas");
	        	boolean estado = resultSet.getBoolean("P.estado");

	            Prestamo prestamo = new Prestamo(idPrestamo, cliente, cuenta, fechaAlta, importePedido, plazoMeses, importeMensual, importeFinal, cantidadCuotas, estado);
	            listaPrestamos.add(prestamo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaPrestamos;
	}

	private static final String qryBuscarPrestamo = "SELECT P.*, Cl.*, Cu.* FROM prestamos P "
			+ "JOIN clientes Cl ON Cl.id = P.id_cliente "
			+ "JOIN cuentas Cu ON Cu.id = P.id_cuenta "
			+ "WHERE P.id_prestamo = ?";

	@Override
	public Prestamo obtenerPrestamoPorId(int id) {
		Prestamo prestamo = new Prestamo();

	    try {
	        
	        Connection con = Conexion.getConexion().getSQLConexion();
	        
	        PreparedStatement statement = con.prepareStatement(qryBuscarPrestamo);
	        statement.setInt(1, id); 
	    
	        ResultSet resultSet = statement.executeQuery();
	      
	        if (resultSet.next()) {
	        	
	        	prestamo.setIdPrestamo(resultSet.getInt("P.id_prestamo"));
	            
	            // CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSet.getInt("Cl.id"));
	        	cliente.setDni(resultSet.getString("Cl.dni"));
	        	cliente.setCuil(resultSet.getString("Cl.cuil"));
	        	cliente.setNombre(resultSet.getString("Cl.nombre"));
	        	cliente.setApellido(resultSet.getString("Cl.apellido"));
	        	cliente.setSexo(resultSet.getString("Cl.sexo"));
	        	cliente.setNacionalidad(resultSet.getString("Cl.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSet.getDate("Cl.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSet.getInt("Cl.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("Cl.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSet.getString("Cl.correo"));
	        	cliente.setTelefono(resultSet.getString("Cl.telefono"));
	        	cliente.setDireccion(resultSet.getString("Cl.direccion"));
	        	cliente.setEstado(resultSet.getBoolean("Cl.estado"));
	        	
	        	prestamo.setCliente(cliente);
	        	
	        	// CUENTA
	        	Cuenta cuenta = new Cuenta();
	        	cuenta.setId(resultSet.getInt("Cu.id"));
	        	cuenta.setNumeroCuenta(resultSet.getString("Cu.numero_cuenta"));
	        	cuenta.setFechaCreacion(resultSet.getTimestamp("Cu.fecha_creacion").toLocalDateTime());
	        	cuenta.setCbu(resultSet.getString("Cu.cbu"));
	        	cuenta.setSaldo(resultSet.getBigDecimal("Cu.saldo"));
	        	
	        	Usuario usuario = new Usuario();
	        	usuario.setCliente(cliente);
	        	cuenta.setUsuario(usuario);
	        	
	        	TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("Cu.id_tipoCuenta"));
	        	cuenta.setTipoCuenta(tipoCuenta);
	        	
	            cuenta.setEstado(resultSet.getBoolean("Cu.estado"));

	            prestamo.setCuenta(cuenta);
	        	
	        	// FECHAALTA
	            java.sql.Timestamp sqlTimestampAlta = resultSet.getTimestamp("P.fecha_alta");
	            LocalDateTime fechaAlta = sqlTimestampAlta != null ? sqlTimestampAlta.toLocalDateTime() : null;
        	
	        	prestamo.setFechaAlta(fechaAlta);
	        	
	        	prestamo.setImportePedido(resultSet.getBigDecimal("P.importe_pedido"));
	        	prestamo.setPlazoMeses(resultSet.getInt("P.plazo_meses"));
	        	prestamo.setImporteMensual(resultSet.getBigDecimal("P.importe_mensual"));
	        	prestamo.setImporteFinal(resultSet.getBigDecimal("P.importe_final"));
	        	prestamo.setCantidadCuotas(resultSet.getInt("P.cantidad_cuotas"));
	        	prestamo.setEstado(resultSet.getBoolean("P.estado"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        Conexion.getConexion().cerrarConexion();
	    }

	    return prestamo;
	}

	private static final String qrylistarPrestamosCuenta = "SELECT P.*, Cl.*, Cu.* "
			+ "FROM prestamos P "
			+ "JOIN clientes Cl ON Cl.id = P.id_cliente "
			+ "JOIN cuentas Cu ON Cu.id = P.id_cuenta "
			+ "WHERE P.estado = 1 AND Cu.id = ?";
	
	@Override
	public List<Prestamo> listarPrestamosCuenta(int idCuenta) {
		List<Prestamo> listaPrestamos = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrylistarPrestamosCuenta);
	        statement.setInt(1, idCuenta); 
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
            
	            int idPrestamo = resultSet.getInt("P.id_prestamo");
	            
	            // CLIENTE
	        	Cliente cliente = new Cliente();
	        	cliente.setId(resultSet.getInt("Cl.id"));
	        	cliente.setDni(resultSet.getString("Cl.dni"));
	        	cliente.setCuil(resultSet.getString("Cl.cuil"));
	        	cliente.setNombre(resultSet.getString("Cl.nombre"));
	        	cliente.setApellido(resultSet.getString("Cl.apellido"));
	        	cliente.setSexo(resultSet.getString("Cl.sexo"));
	        	cliente.setNacionalidad(resultSet.getString("Cl.nacionalidad"));
	        	
	        	java.sql.Date sqlDate = resultSet.getDate("Cl.fecha_nacimiento");
	            LocalDate fechaNacimiento = sqlDate != null ? sqlDate.toLocalDate() : null;
	            cliente.setFechaNacimiento(fechaNacimiento);
	            
	            Localidad localidadCliente = new Localidad();
	            localidadCliente.setId(resultSet.getInt("Cl.id_localidad"));
	        	cliente.setLocalidadCliente(localidadCliente);
	        	
	        	Provincia provinciaCliente = new Provincia();
	            provinciaCliente.setId(resultSet.getInt("Cl.id_provincia"));
	        	cliente.setProvinciaCliente(provinciaCliente);
	        	
	        	cliente.setCorreo(resultSet.getString("Cl.correo"));
	        	cliente.setTelefono(resultSet.getString("Cl.telefono"));
	        	cliente.setDireccion(resultSet.getString("Cl.direccion"));
	        	cliente.setEstado(resultSet.getBoolean("Cl.estado"));
	        	
	        	// CUENTA
	        	Cuenta cuenta = new Cuenta();
	        	cuenta.setId(resultSet.getInt("Cu.id"));
	        	cuenta.setNumeroCuenta(resultSet.getString("Cu.numero_cuenta"));
	        	cuenta.setFechaCreacion(resultSet.getTimestamp("Cu.fecha_creacion").toLocalDateTime());
	        	cuenta.setCbu(resultSet.getString("Cu.cbu"));
	        	cuenta.setSaldo(resultSet.getBigDecimal("Cu.saldo"));
	        	
	        	Usuario usuario = new Usuario();
	        	usuario.setCliente(cliente);
	        	cuenta.setUsuario(usuario);
	        	
	        	TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("Cu.id_tipoCuenta"));
	        	cuenta.setTipoCuenta(tipoCuenta);
	        	
	            cuenta.setEstado(resultSet.getBoolean("Cu.estado"));

	        	
	        	// FECHAALTA
	            java.sql.Timestamp sqlTimestampAlta = resultSet.getTimestamp("P.fecha_alta");
	            LocalDateTime fechaAlta = sqlTimestampAlta != null ? sqlTimestampAlta.toLocalDateTime() : null;
        	
	        	
	        	BigDecimal importePedido = resultSet.getBigDecimal("P.importe_pedido");
	        	int plazoMeses = resultSet.getInt("P.plazo_meses");
	        	BigDecimal importeMensual = resultSet.getBigDecimal("P.importe_mensual");
	        	BigDecimal importeFinal = resultSet.getBigDecimal("P.importe_final");
	        	int cantidadCuotas = resultSet.getInt("P.cantidad_cuotas");
	        	boolean estado = resultSet.getBoolean("P.estado");

	            Prestamo prestamo = new Prestamo(idPrestamo, cliente, cuenta, fechaAlta, importePedido, plazoMeses, importeMensual, importeFinal, cantidadCuotas, estado);
	            listaPrestamos.add(prestamo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaPrestamos;
	}

	
	private static final String qryCancelarPrestamo = 
		    "UPDATE Prestamos " +
		    "SET estado = false " +  
		    "WHERE id_prestamo = ?";
	
	@Override
	public boolean cancelarPrestamo(int idPrestamo) 
	{
		
		   boolean resultado = false;

		    
		    try (Connection conexion = Conexion.getConexion().getSQLConexion();
		         PreparedStatement statement = conexion.prepareStatement(qryCancelarPrestamo)) {
		        
		        
		        conexion.setAutoCommit(false);

		        
		        statement.setInt(1, idPrestamo);

		        
		        int filasAfectadas = statement.executeUpdate();

		        if (filasAfectadas > 0) {
		            conexion.commit();  
		            resultado = true;
		        } else {
		            conexion.rollback();  
		        }

		    } catch (SQLException e) {
		        
		        e.printStackTrace();
		        
		        try (Connection conexion = Conexion.getConexion().getSQLConexion()) {
		            if (conexion != null) {
		                conexion.rollback();  
		            }
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		    }

		    return resultado;
	}

}
