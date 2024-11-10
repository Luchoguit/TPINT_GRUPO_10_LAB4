package daoImplementacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import entidad.Localidad;
import entidad.Provincia;

public class LocalidadDaoImp implements LocalidadDao {

	

public LocalidadDaoImp() {};
	
	private static final String qrylistarlocalidades = "SELECT id, nombre, provincia_id FROM localidades";
	
	public List<Localidad> listarLocalidades() {
	    List<Localidad> localidades = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrylistarlocalidades);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            int provincia_id = resultSet.getInt("provincia_id");
	            
	            Provincia prov = new Provincia();
	            prov.setId(provincia_id);
	           

	            Localidad loc = new Localidad(id, nombre,prov);
	            localidades.add(loc);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return localidades;
	}

	 
private static final String qrylistarlocalidadesPorProvincia = "SELECT id, nombre, provincia_id FROM localidades WHERE provincia_id = ?";
	
	public List<Localidad> listarPorProvincia(int idProvincia) {
	    List<Localidad> localidades = new ArrayList<>();

	    try {
	     
	        Connection con = Conexion.getConexion().getSQLConexion();
	        PreparedStatement statement = con.prepareStatement(qrylistarlocalidadesPorProvincia);
			statement.setInt(1, idProvincia);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String nombre = resultSet.getString("nombre");
	            int provincia_id = resultSet.getInt("provincia_id");
	            
	            Provincia prov = new Provincia();
	            prov.setId(provincia_id);
	           

	            Localidad loc = new Localidad(id, nombre,prov);
	            localidades.add(loc);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        Conexion.getConexion().cerrarConexion();
	    }

	    return localidades;
	}
	
private static final String qryBuscarLocalidad = "SELECT id, nombre FROM localidades WHERE id = ?";
	
	public Localidad obtenerLocalidadPorId(int id) {
	    Localidad localidad = null;

	    try {
	        
	        Connection con = Conexion.getConexion().getSQLConexion();
	        
	        
	        PreparedStatement statement = con.prepareStatement(qryBuscarLocalidad);
	        statement.setInt(1, id); 

	    
	        ResultSet resultSet = statement.executeQuery();

	      
	        if (resultSet.next()) {
	            localidad = new Localidad();
	            localidad.setId(resultSet.getInt("id"));
	            localidad.setNombre(resultSet.getString("nombre"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        
	        Conexion.getConexion().cerrarConexion();
	    }

	    return localidad;
	}

	
	}

