package negocioimplementacion;

import java.util.List;

import dao.SolicitudAltaCuentaDao;
import daoImplementacion.SolicitudAltaCuentaDaoImp;
import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;
import negocio.SolicitudAltaCuentaNegocio;

public class SolicitudAltaCuentaNegocioImp implements SolicitudAltaCuentaNegocio {
	
	SolicitudAltaCuentaDao solicitudDao = new SolicitudAltaCuentaDaoImp();

	@Override
	public boolean registrarSolicitud(SolicitudAltaCuenta solicitud) {
		return solicitudDao.registrarSolicitud(solicitud);
	}

	@Override
	public List<SolicitudAltaCuenta> listarSolicitudesSinResponder() {
		return solicitudDao.listarSolicitudesSinResponder();
	}

	@Override
	public SolicitudAltaCuenta obtenerSolicitudPorId(int id) {
		return solicitudDao.obtenerSolicitudPorId(id);
	}

	@Override
	public boolean responderSolicitud(int id) {
		return solicitudDao.responderSolicitud(id);
	}



}
