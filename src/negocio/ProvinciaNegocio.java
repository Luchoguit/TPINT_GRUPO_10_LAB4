package negocio;

import java.util.List;

import entidad.Provincia;

public interface ProvinciaNegocio {
	
	List<Provincia> listarProvincias();
	Provincia obtenerProvinciaPorId(int id);

}
