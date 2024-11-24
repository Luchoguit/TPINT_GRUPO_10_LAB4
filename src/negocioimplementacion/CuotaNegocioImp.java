package negocioimplementacion;

import dao.CuotaDao;
import daoImplementacion.CuotaDaoImp;
import entidad.Prestamo;
import negocio.CuotaNegocio;

public class CuotaNegocioImp implements CuotaNegocio {

	private CuotaDao cuotaDao = new CuotaDaoImp();
	
	@Override
	public boolean pagarCuotas(int idPrestamo, int cantidadCuotas, int idCuenta) {
		return cuotaDao.pagarCuotas(idPrestamo, cantidadCuotas, idCuenta);
	}

	@Override
	public boolean generarCuotas(Prestamo prestamo) {
		return cuotaDao.generarCuotas(prestamo);
	}

	@Override
	public int cantidadCuotasPagas(int idPrestamo) {
		return cuotaDao.cantidadCuotasPagas(idPrestamo)
				;
	}

}
