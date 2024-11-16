package negocioimplementacion;

import java.util.List;

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

	@Override
	public List<Prestamo> listarPrestamos() {
		return prestamoDao.listarPrestamos();
	}

	@Override
	public Prestamo obtenerPrestamoPorId(int id) {
		return prestamoDao.obtenerPrestamoPorId(id);
	}

	@Override
	public List<Prestamo> listarPrestamosCuenta(int idCuenta) {
		return prestamoDao.listarPrestamosCuenta(idCuenta);
	}


}
