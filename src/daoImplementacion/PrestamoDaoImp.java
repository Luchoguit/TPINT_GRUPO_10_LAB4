package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dao.PrestamoDao;
import entidad.Prestamo;

public class PrestamoDaoImp implements PrestamoDao {

	
	private static final String qryInsertPrestamo = "INSERT INTO prestamos (id_prestamo, id_cliente, id_cuenta, fecha_alta, importe_pedido, plazo_meses, importe_mensual, cantidad_cuotas) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	
	@Override
	public boolean altaPrestamo(Prestamo prestamo) {

		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsertPrestamo);

            // Seteando los parámetros de la consulta
            statement.setInt(1, prestamo.getIdPrestamo());
            statement.setInt(2, prestamo.getCliente().getId());
            statement.setInt(3, prestamo.getCuenta().getId());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            statement.setBigDecimal(5, prestamo.getImportePedido());
            statement.setInt(6, prestamo.getPlazoMeses());
            statement.setBigDecimal(7, prestamo.getImporteMensual());
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

}
