package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImp implements CuentaDao {
	
	
	 private static final String qryInsert = "INSERT INTO cuentas (numero_cuenta, fecha_creacion, cbu, saldo, id_usuario, id_tipoCuenta) "
	            + "VALUES (?, ?, ?, ?, ?, ?)";
	 
	@Override
	public boolean altaCuenta(Cuenta cuenta) {
		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsert);

            // Seteando los parámetros de la consulta
            statement.setString(1, cuenta.getNumeroCuenta());
            statement.setTimestamp(2, Timestamp.valueOf(cuenta.getFechaCreacion()));
            statement.setString(3, cuenta.getCbu());
            statement.setBigDecimal(4, cuenta.getSaldo());
            statement.setInt(5, cuenta.getUsuario().getIdCliente());
            statement.setInt(6, cuenta.getTipoCuenta().getId());
                

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

	@Override
	public List<Cuenta> listarCuentas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}

	private static final String qryEliminarCuenta = "UPDATE cuentas SET estado = false WHERE id = ?";

	@Override
	public boolean eliminarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}



}
