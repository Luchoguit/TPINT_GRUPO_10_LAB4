package negocioimplementacion;

import java.util.List;

import dao.TipoCuentaDao;
import daoImplementacion.TipoCuentaDaoImp;
import entidad.TipoCuenta;
import negocio.TipoCuentaNegocio;

public class TipoCuentaNegocioImp implements TipoCuentaNegocio {

	private TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImp();
	
	@Override
	public List<TipoCuenta> ObtenerTiposCuenta() {
		return tipoCuentaDao.ObtenerTiposCuenta();
	}

	@Override
	public TipoCuenta ObtenerPorId(int id) {
		return tipoCuentaDao.ObtenerPorId(id);
	}
}
