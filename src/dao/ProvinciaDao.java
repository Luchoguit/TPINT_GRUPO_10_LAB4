package dao;

import java.util.List;

import entidad.Provincia;

public interface ProvinciaDao {
	
	List<Provincia> listarProvincias();
	Provincia obtenerProvinciaPorId(int id);


}
