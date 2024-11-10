package negocioimplementacion;

import java.util.List;

import dao.LocalidadDao;
import daoImplementacion.LocalidadDaoImp;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImp implements LocalidadNegocio {
	
	LocalidadDao localidadDao = new LocalidadDaoImp();

	@Override
	public List<Localidad> listarLocalidades() {
		return localidadDao.listarLocalidades();
	}

	@Override
	public List<Localidad> listarPorProvincia(int idProvincia) {
		return localidadDao.listarPorProvincia(idProvincia);
	}

	@Override
	public Localidad obtenerLocalidadPorId(int id) {
		return localidadDao.obtenerLocalidadPorId(id);
	}
	
	
	

}
