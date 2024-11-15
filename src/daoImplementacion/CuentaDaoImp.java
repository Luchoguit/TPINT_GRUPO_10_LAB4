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

import dao.CuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Provincia;
import entidad.TipoCuenta;
import entidad.Usuario;

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
	
	private static final String qryListarCuentas = "SELECT * FROM cuentas WHERE id_usuario = ?";

	@Override
	public List<Cuenta> listarCuentas(Usuario usuario) {
		
		List<Cuenta> listaCuentas = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();	
	        System.out.println("[DEBUG] Conexión a la base de datos establecida");

	        System.out.println("[DEBUG] Conexión establecida: " + con);

	        PreparedStatement statement = con.prepareStatement(qryListarCuentas);
            statement.setInt(1, usuario.getIdCliente());
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("id");
	            String numeroCuenta = resultSet.getString("numero_cuenta");
	            LocalDateTime fechaCreacion = resultSet.getTimestamp("fecha_creacion").toLocalDateTime();
	            String cbu = resultSet.getString("cbu");
	            BigDecimal saldo = resultSet.getBigDecimal("saldo");
	            
	            Usuario usuarioCuenta = new Usuario();
	            usuarioCuenta.setIdCliente(resultSet.getInt("id_usuario")); ;
	            
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSet.getInt("id_tipoCuenta"));
	            	            			            
	            boolean estado = resultSet.getBoolean("estado");

	        
	            Cuenta cuenta = new Cuenta(id, numeroCuenta, fechaCreacion, cbu, saldo, usuarioCuenta, tipoCuenta, estado);
	            listaCuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return listaCuentas;
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
	
	@Override
	public List<Cuenta> listarTodasLasCuentas() {
	    List<Cuenta> listaCuentas = new ArrayList<>();

	    try (Connection con = Conexion.getConexion().getSQLConexion()) {
	        System.out.println("[DEBUG] Conexion a la base de datos establecida");

	    
	        String qryListarCuentas = 
	            "SELECT c.id, c.numero_cuenta, t.id AS tipo_cuenta_id, t.descripcion AS tipo_cuenta_desc, c.id_usuario, c.estado " +
	            "FROM cuentas c " +
	            "JOIN tipos_de_cuentas t ON c.id_tipoCuenta = t.id ";
	         
	        PreparedStatement statementCuentas = con.prepareStatement(qryListarCuentas);
	        ResultSet resultSetCuentas = statementCuentas.executeQuery();

	        
	        while (resultSetCuentas.next()) {
	            Cuenta cuenta = new Cuenta(0, null, null, null, null, null, null, false);

	            cuenta.setId(resultSetCuentas.getInt("id"));
	            cuenta.setNumeroCuenta(resultSetCuentas.getString("numero_cuenta"));
	            
	       
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(resultSetCuentas.getInt("tipo_cuenta_id"));
	            tipoCuenta.setDescripcion(resultSetCuentas.getString("tipo_cuenta_desc"));

	           
	            cuenta.setTipoCuenta(tipoCuenta);
	            
	            Usuario usuarioCuenta = new Usuario();
	            usuarioCuenta.setIdCliente(resultSetCuentas.getInt("id_usuario")); ;
	            
	            cuenta.setUsuario(usuarioCuenta);
	            cuenta.setEstado(resultSetCuentas.getBoolean("estado"));

	          
	            listaCuentas.add(cuenta);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    // Devolvemos solo la lista de cuentas
	    return listaCuentas;
	}



}
