package negocioimplementacion;

import dao.PrestamoDao;
import daoImplementacion.PrestamoDaoImp;
import entidad.Prestamo;
import negocio.PrestamoNegocio;

public class PrestamoNegocioImp implements PrestamoNegocio{
	
	PrestamoDao prestamoDao = new PrestamoDaoImp();
	
	@Override
	public boolean altaPrestamo(Prestamo prestamo) {
		return prestamoDao.altaPrestamo(prestamo);
	}

}
