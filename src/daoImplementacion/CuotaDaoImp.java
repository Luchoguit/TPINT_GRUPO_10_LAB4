package daoImplementacion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dao.CuotaDao;
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
            BigDecimal montoCuota = prestamo.getImportePedido().divide(
                    BigDecimal.valueOf(numeroCuotas), RoundingMode.HALF_UP
                );

            for (int i = 1; i <= numeroCuotas; i++) {
            	statement.setInt(1, prestamo.getIdPrestamo());
            	statement.setInt(2, i); 
            	statement.setBigDecimal(3, montoCuota);
            	statement.setTimestamp(4, Timestamp.valueOf(prestamo.getFechaAlta()));
            	statement.addBatch();
            }

            int[] resultados = statement.executeBatch(); 

            // Verificar que todas las cuotas se insertaron correctamente
            for (int resultado : resultados) {
                if (resultado == PreparedStatement.EXECUTE_FAILED) {
                    return false;
                }
            }

            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
        
        } finally {
            Conexion.getConexion().cerrarConexion();
        }

        return false;
	}

	@Override
	public boolean pagarCuota(int idPrestamo) {
		// TODO Auto-generated method stub
		return false;
	}
}
