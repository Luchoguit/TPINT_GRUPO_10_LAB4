package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.MovimientoDao;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.TipoMovimiento;

public class MovimientoDaoImp implements MovimientoDao {

	private static final String qrybuscarMovimientos = "SELECT * FROM movimientos "
			+ "WHERE id_cuenta = ? OR id_cuentaDestino = ? "
			+ "AND fechaHora BETWEEN ? AND ?";

	@Override
	public List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin) {
	    List<Movimiento> lista = new ArrayList<>();
	    
	    try {
	    	
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrybuscarMovimientos);

	        statement.setInt(1, idCuenta);
	        statement.setInt(2, idCuenta);
	        statement.setString(3, fechaInicio);
	        statement.setString(4, fechaFin);

	        ResultSet resultSet = statement.executeQuery();


	        while (resultSet.next()) {
	            Movimiento m = new Movimiento();
	            m.setId(resultSet.getInt("id"));
	            Cuenta cuentaOrigen = new Cuenta();
	            cuentaOrigen.setId(resultSet.getInt("id_cuenta"));
	            m.setCuentaOrigen(cuentaOrigen);
	            Cuenta cuentaDestino = new Cuenta();
	            cuentaDestino.setId(resultSet.getInt("id_cuentaDestino"));
	            m.setCuentaDestino(cuentaDestino);
	            TipoMovimiento tipoMovimiento = new TipoMovimiento();
	            tipoMovimiento.setId(resultSet.getInt("id_tipoMovimiento"));
	            m.setTipoMovimiento(tipoMovimiento);
	            m.setDetalle(resultSet.getString("detalle"));
	            m.setFechaHora(resultSet.getTimestamp("fechaHora").toLocalDateTime());
	            m.setImporte(resultSet.getBigDecimal("importe"));
	            lista.add(m);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lista;
	}


}
