package negocio;

import java.util.List;

import entidad.Localidad;

public interface LocalidadNegocio {
	
	List<Localidad> listarLocalidades();
	List<Localidad> listarPorProvincia(int idProvincia);

}
