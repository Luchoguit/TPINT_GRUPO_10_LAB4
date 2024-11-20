package negocioimplementacion;

import dao.CuotaDao;
import daoImplementacion.CuotaDaoImp;
import negocio.CuotaNegocio;

public class CuotaNegocioImp implements CuotaNegocio {

	private CuotaDao cuotaDao = new CuotaDaoImp();
	
	@Override
	public boolean pagarCuota(int idPrestamo) {
		return cuotaDao.pagarCuota(idPrestamo);
	}

}
