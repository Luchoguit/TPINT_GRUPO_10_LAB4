package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.SolicitudPrestamoDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Prestamo;
import entidad.Provincia;
import entidad.SolicitudPrestamo;
import entidad.TipoCuenta;
import entidad.Usuario;

public class SolicitudPrestamoDaoImp implements SolicitudPrestamoDao {

	private static final String qrylistarSolicitudes = "SELECT Sp.*, P.*, Cl.*, Cu.* "
			+ "FROM Solicitudes_Prestamos Sp "
			+ "JOIN prestamos P ON P.id_prestamo = P.id_prestamo "
			+ "JOIN clientes Cl ON Cl.id = P.id_cliente "
			+ "JOIN cuentas Cu ON Cu.id = id_cuenta"
			+ "WHERE Sp.estado = 1";
	@Override
	public List<SolicitudPrestamo> listarSolicitudes() {
		List<SolicitudPrestamo> listaSolicitudes = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrylistarSolicitudes);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
            
	            int idSolicitud = resultSet.getInt("Sp.id_solicitud");
	            
			     // PRESTAMO
	
		            Prestamo prestamo = new Prestamo();
		            prestamo.setIdPrestamo(resultSet.getInt("Sp.id_prestamo"));
		            
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
		            
		            java.sql.Timestamp sqlTimestampAlta = resultSet.getTimestamp("P.fecha_alta");
		            LocalDateTime fechaAlta = sqlTimestampAlta != null ? sqlTimestampAlta.toLocalDateTime() : null;
	        	    prestamo.setFechaAlta(fechaAlta);
	        	    prestamo.setImportePedido(resultSet.getBigDecimal("P.importe_pedido"));
	        	    prestamo.setPlazoMeses(resultSet.getInt("P.plazo_meses"));
	        	    prestamo.setImporteMensual(resultSet.getBigDecimal("P.importe_mensual"));
	        	    prestamo.setCantidadCuotas(resultSet.getInt("P.cantidad_cuotas"));
	        	    prestamo.setEstado(resultSet.getBoolean("P.estado"));
	            
	            
	        	boolean aprobado = resultSet.getBoolean("Sp.aprobado");
	        	boolean estado = resultSet.getBoolean("Sp.estado");

	            SolicitudPrestamo solicitud = new SolicitudPrestamo(idSolicitud, prestamo, aprobado, estado);
	            listaSolicitudes.add(solicitud);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaSolicitudes;
	}
	
	
	private static final String qryInsertSolicitud = "INSERT INTO Solicitudes_Prestamos (id_prestamo) "
			+ "VALUES (?)";
	
	@Override
	public boolean registrarSolicitud(Prestamo prestamo) {
		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsertSolicitud);

            // Seteando los parámetros de la consulta
            statement.setInt(1, prestamo.getIdPrestamo());
            
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
	
	private static final String qryActualizarPrestamo = "UPDATE prestamos SET estado = 1 WHERE id_prestamo = ?;";
	private static final String qryActualizarSolicitud = "UPDATE Solicitudes_Prestamos SET aprobado = 1, estado = 0 WHERE id_prestamo = ?;";


@Override
public boolean aceptarPrestamo(int id) {
    Connection con = null;
    try {
        con = Conexion.getConexion().getSQLConexion();

        con.setAutoCommit(false);

        try (PreparedStatement statementPrestamo = con.prepareStatement(qryActualizarPrestamo);
             PreparedStatement statementSolicitud = con.prepareStatement(qryActualizarSolicitud)) {

            statementPrestamo.setInt(1, id);
            statementSolicitud.setInt(1, id);

            int rowsAffectedPrestamo = statementPrestamo.executeUpdate();
            int rowsAffectedSolicitud = statementSolicitud.executeUpdate();

            if (rowsAffectedPrestamo > 0 && rowsAffectedSolicitud > 0) {
                con.commit();
                return true;
            } else {
                con.rollback();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
            return false;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        if (con != null) {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Conexion.getConexion().cerrarConexion();
            }
        }
    }
}
	


	private static final String qryRechazarPrestamo = 
	        "UPDATE prestamos SET estado = 0 WHERE id_prestamo = ?; ";

	private static final String qryRechazarSolicitud = 
	        "UPDATE Solicitudes_Prestamos SET aprobado = 0, estado = 0 WHERE id_prestamo = ?; ";

	@Override
	public boolean rechazarPrestamo(int id) {
	    Connection con = null;
	    try {
	        con = Conexion.getConexion().getSQLConexion();

	        con.setAutoCommit(false);

	        try (PreparedStatement statementPrestamo = con.prepareStatement(qryRechazarPrestamo);
	             PreparedStatement statementSolicitud = con.prepareStatement(qryRechazarSolicitud)) {

	            statementPrestamo.setInt(1, id);
	            statementSolicitud.setInt(1, id);

	            int rowsAffectedPrestamo = statementPrestamo.executeUpdate();
	            int rowsAffectedSolicitud = statementSolicitud.executeUpdate();

	            if (rowsAffectedPrestamo > 0 && rowsAffectedSolicitud > 0) {
	                con.commit();
	                return true;
	            } else {
	                con.rollback();
	                return false;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            con.rollback();
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (con != null) {
	            try {
	                con.setAutoCommit(true);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } finally {
	                Conexion.getConexion().cerrarConexion();
	            }
	        }
	    }
	}





}
