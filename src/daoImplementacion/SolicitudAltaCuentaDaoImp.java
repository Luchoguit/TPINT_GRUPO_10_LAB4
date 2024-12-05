package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.SolicitudAltaCuentaDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Provincia;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import entidad.Usuario;

public class SolicitudAltaCuentaDaoImp implements SolicitudAltaCuentaDao {

	
	private static final String qryInsertSolicitud = "INSERT INTO solicitudes_alta_cuenta (id_cliente, id_tipocuenta) VALUES (?, ?)";

	 
	@Override
	public boolean registrarSolicitud(SolicitudAltaCuenta solicitud) {
		PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean isInsertExitoso = false;

        try {
            statement = conexion.prepareStatement(qryInsertSolicitud);

            // Seteando los parámetros de la consulta
            statement.setInt(1, solicitud.getCliente().getId());
            statement.setInt(2, solicitud.getTipoCuenta().getId());


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
	
	 private static final String qryListarSolicitudes = "SELECT S.* FROM solicitudes_alta_cuenta S "
	 		+ "JOIN Clientes C ON C.id = S.id_cliente "
	 		+ "WHERE respondida = 0 AND C.estado = 1";

	@Override
	public List<SolicitudAltaCuenta> listarSolicitudesSinResponder() {
		List<SolicitudAltaCuenta> listaSolicitudes = new ArrayList<>();
	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    try {
	        PreparedStatement statement = conexion.prepareStatement(qryListarSolicitudes);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	        	
	        	int idSolicitud = resultSet.getInt("id");
	        	int idCliente = resultSet.getInt("id_cliente");
	        	Cliente cliente = new Cliente();
	        	cliente.setId(idCliente);
	        	
	            int idTipoCuenta = resultSet.getInt("id_tipoCuenta");	    
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(idTipoCuenta);
	            boolean respondida = resultSet.getBoolean("respondida");

	            SolicitudAltaCuenta solicitud = new SolicitudAltaCuenta(idSolicitud, cliente,tipoCuenta,respondida);
	            
	            listaSolicitudes.add(solicitud);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }
	    return listaSolicitudes;
	}

	 private static final String qryObtenerSolicitud = "SELECT * FROM solicitudes_alta_cuenta WHERE id = ?";
	 
	@Override
	public SolicitudAltaCuenta obtenerSolicitudPorId(int id) {
		SolicitudAltaCuenta solicitud = new SolicitudAltaCuenta();

	    try {
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qryObtenerSolicitud);
	        statement.setInt(1, id);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	        	
	        	solicitud.setId(resultSet.getInt("id"));
	        	
	        	int idCliente = resultSet.getInt("id_cliente");
	        	Cliente cliente = new Cliente();
	        	cliente.setId(idCliente);
	            solicitud.setCliente(cliente);
	        	            
	            int idTipoCuenta = resultSet.getInt("id_tipoCuenta");	    
	            TipoCuenta tipoCuenta = new TipoCuenta();
	            tipoCuenta.setId(idTipoCuenta);
	            solicitud.setTipoCuenta(tipoCuenta);
	            
	            solicitud.setRespondida(resultSet.getBoolean("respondida"));

	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return solicitud;
	}

	
	 private static final String qryResponder= "UPDATE solicitudes_alta_cuenta SET respondida = 1 WHERE id = ?";

	 @Override
	 public boolean responderSolicitud(int id) {
	     try (Connection con = Conexion.getConexion().getSQLConexion();
	          PreparedStatement statement = con.prepareStatement(qryResponder)) {

	         System.out.println("[DEBUG] Metodo responderSolicitud");
	         System.out.println("[DEBUG] Conexión establecida: " + con);
	         System.out.println("[DEBUG] id solicitud: " + id);

	         System.out.println("[DEBUG] Autocommit: " + con.getAutoCommit());

	
	         if (!con.getAutoCommit()) {
	             con.setAutoCommit(true); 
	             System.out.println("[DEBUG] Autocommit habilitado");
	         }

	         statement.setInt(1, id);

	         int rowsAffected = statement.executeUpdate();
	         System.out.println("[DEBUG] filas afectadas: " + rowsAffected);

	         if (rowsAffected > 0 && !con.getAutoCommit()) {
	             con.commit();  
	             System.out.println("[DEBUG] Commit realizado");
	         }

	         return rowsAffected > 0;

	     } catch (SQLException e) {
	         e.printStackTrace();
		    } finally {
		        Conexion.getConexion().cerrarConexion();
		    }
	     
	     return false;
	 }

	


}
