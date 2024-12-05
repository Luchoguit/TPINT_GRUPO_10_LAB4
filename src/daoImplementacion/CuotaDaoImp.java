package daoImplementacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dao.CuotaDao;
import entidad.Localidad;
import entidad.Prestamo;

public class CuotaDaoImp implements CuotaDao {


	private static final String qryInsertCuota = 
		    "INSERT INTO cuotas (id_prestamo, numero_cuota, monto, fecha_pago) VALUES (?, ?, ?, ?);";
	
	@Override
	public boolean generarCuotas(Prestamo prestamo) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    try {
	        statement = conexion.prepareStatement(qryInsertCuota);

	        int numeroCuotas = prestamo.getCantidadCuotas();


	        for (int i = 1; i <= numeroCuotas; i++) {
	            statement.setInt(1, prestamo.getIdPrestamo());
	            statement.setInt(2, i); 
	            statement.setBigDecimal(3, prestamo.getImporteMensual());
	            statement.setTimestamp(4, Timestamp.valueOf(prestamo.getFechaAlta()));
	            statement.addBatch();
	        }

	        int[] resultados = statement.executeBatch(); 

	        // Verificar que todas las cuotas se insertaron correctamente
	        for (int resultado : resultados) {
	            if (resultado == PreparedStatement.EXECUTE_FAILED) {
	                conexion.rollback();
	                return false;
	            }
	        }

	        conexion.commit();
	        return true; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (conexion != null) {
	                conexion.rollback();
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return false;
	}

	private static final String qryPagarCuotas = 
		    "UPDATE cuotas " +
		    "SET pagada = true, fecha_pago = ?, id_cuenta_pago = ? " +
		    "WHERE id_prestamo = ? AND pagada = false " +
		    "ORDER BY numero_cuota ASC " +
		    "LIMIT ?";

		@Override
		public boolean pagarCuotas(int idPrestamo, int cantidadCuotas, int idCuenta) {
		    boolean resultado = false;
		    Connection conexion = null;
		    PreparedStatement statement = null;

		    try {
		        // Obtener conexión
		        conexion = Conexion.getConexion().getSQLConexion();

		        // Preparar sentencia
		        statement = conexion.prepareStatement(qryPagarCuotas);
		        statement.setDate(1, new java.sql.Date(System.currentTimeMillis())); // Fecha actual
		        statement.setInt(2, idCuenta);
		        statement.setInt(3, idPrestamo);
		        statement.setInt(4, cantidadCuotas);

		        // Ejecutar actualización
		        int filasAfectadas = statement.executeUpdate();
		        resultado = (filasAfectadas == cantidadCuotas); // Verificar si se actualizaron todas las cuotas esperadas

		        if (resultado) {
		            conexion.commit(); // Confirmar transacción
		        } else {
		            conexion.rollback(); // Revertir cambios si hubo un problema
		        }
		        

		    } catch (SQLException e) {
		        e.printStackTrace();
		        try {
		            if (conexion != null) {
		                conexion.rollback(); // Revertir cambios en caso de error
		            }
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		    } finally {
		        try {
		            if (statement != null) statement.close();
		            if (conexion != null) Conexion.getConexion().cerrarConexion();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    return resultado;
		}

	private static final String qryCuotasPagas = 
		    "SELECT COUNT(*) AS CantidadCuotas FROM cuotas "
		    + "WHERE pagada = 1 AND id_prestamo = ?";
	
	
	@Override
	public int cantidadCuotasPagas(int idPrestamo) {

	    int cantidadCuotas = 0;

	    try {
	        
	        Connection con = Conexion.getConexion().getSQLConexion();
	        
	        
	        PreparedStatement statement = con.prepareStatement(qryCuotasPagas);
	        statement.setInt(1, idPrestamo); 

	    
	        ResultSet resultSet = statement.executeQuery();

	      
	        if (resultSet.next()) {
	        	cantidadCuotas = resultSet.getInt("CantidadCuotas");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        Conexion.getConexion().cerrarConexion();
	    }
		
		return cantidadCuotas;
	}


	private static final String countTotalCuotasSql = "SELECT cantidad_cuotas FROM Prestamos WHERE id_prestamo = ?";
	private static final String countCuotasPagadasSql = "SELECT COUNT(*) FROM Cuotas WHERE id_prestamo = ? AND pagada = TRUE";
	
	@Override
	public boolean esUltimaCuotaPagada(int idPrestamo) 
	{
	    int totalCuotas = 0;
	    int cuotasPagadas = 0;

	    // Establecer la conexión y preparar la consulta
	    try (Connection con = Conexion.getConexion().getSQLConexion();
	         PreparedStatement stmtTotal = con.prepareStatement(countTotalCuotasSql);
	         PreparedStatement stmtPagadas = con.prepareStatement(countCuotasPagadasSql)) {

	        // Contar el total de cuotas del préstamo
	        stmtTotal.setInt(1, idPrestamo);
	        try (ResultSet rsTotal = stmtTotal.executeQuery()) {
	            if (rsTotal.next()) {
	                totalCuotas = rsTotal.getInt("cantidad_cuotas");
	            }
	        }

	        // Contar cuántas cuotas han sido pagadas
	        stmtPagadas.setInt(1, idPrestamo);
	        try (ResultSet rsPagadas = stmtPagadas.executeQuery()) {
	            if (rsPagadas.next()) {
	                cuotasPagadas = rsPagadas.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Si el número de cuotas pagadas es igual al total de cuotas, significa que es la última cuota pagada
	    return cuotasPagadas == totalCuotas;
	}
	
	
	
}
