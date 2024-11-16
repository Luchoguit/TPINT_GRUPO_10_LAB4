package negocioimplementacion;

import java.util.List;

import dao.SolicitudPrestamoDao;
import daoImplementacion.SolicitudPrestamoDaoImp;
import entidad.Prestamo;
import entidad.SolicitudPrestamo;
import negocio.SolicitudPrestamoNegocio;

public class SolicitudPrestamoNegocioImp implements SolicitudPrestamoNegocio {

	SolicitudPrestamoDao solicitudDao = new SolicitudPrestamoDaoImp();
	
	@Override
	public List<SolicitudPrestamo> listarSolicitudes() {
		return solicitudDao.listarSolicitudes();
	}

	@Override
	public boolean registrarSolicitud(Prestamo prestamo) {
		return solicitudDao.registrarSolicitud(prestamo);
	}

	@Override
	public boolean aceptarPrestamo(int id) {
		return solicitudDao.aceptarPrestamo(id);
	}

	@Override
	public boolean rechazarPrestamo(int id) {
		return solicitudDao.rechazarPrestamo(id);
	}

}
