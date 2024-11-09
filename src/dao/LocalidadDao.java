package dao;

import java.util.List;

import entidad.Localidad;

public interface LocalidadDao {

	List<Localidad> listarLocalidades();
	List<Localidad> listarPorProvincia(int idProvincia);
}
