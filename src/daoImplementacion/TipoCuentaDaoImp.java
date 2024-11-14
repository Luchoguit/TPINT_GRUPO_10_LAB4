package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoCuentaDao;
import entidad.Provincia;
import entidad.TipoCuenta;

public class TipoCuentaDaoImp implements TipoCuentaDao {

	
	private static final String qryTipoCuenta = "SELECT * FROM tipos_de_cuentas";

	@Override
	public List<TipoCuenta> ObtenerTiposCuenta() {
		
        System.out.println("[DEBUG] metodo ObtenerTipoCuenta");
        
			List<TipoCuenta> listaTC = new ArrayList<>();
	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qryTipoCuenta);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {

	        	 int id = resultSet.getInt("id");
	        	 String descripcion = resultSet.getString("descripcion");	
	        	 
;
	        	 TipoCuenta tc = new TipoCuenta(id,descripcion);
	        	 listaTC.add(tc);
	        	 }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return listaTC;
	}
	
	
	private static final String qryIdTipoCuenta = "SELECT * FROM tipos_de_cuentas WHERE id = ?";

	@Override
	public TipoCuenta ObtenerPorId(int id) {
		TipoCuenta tipoCuenta = null;

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qryIdTipoCuenta);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("id"));
	            tipoCuenta.setDescripcion(resultSet.getString("descripcion"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return tipoCuenta;
	}

}
