package negocioimplementacion;

import java.util.List;

import dao.ProvinciaDao;
import daoImplementacion.ProvinciaDaoImp;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImp implements ProvinciaNegocio {
	
	private ProvinciaDao provinciaDao = new ProvinciaDaoImp();

	@Override
	public List<Provincia> listarProvincias() {
		return provinciaDao.listarProvincias();
	}

	@Override
	public Provincia obtenerProvinciaPorId(int id) {
		return provinciaDao.obtenerProvinciaPorId(id);
	}

}
