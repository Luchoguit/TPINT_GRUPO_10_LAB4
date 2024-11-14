package negocio;

import java.util.List;

import entidad.SolicitudAltaCuenta;
import entidad.TipoCuenta;

public interface SolicitudAltaCuentaNegocio {
	
	boolean registrarSolicitud(SolicitudAltaCuenta solicitud);
	List<SolicitudAltaCuenta> listarSolicitudesSinResponder();
	SolicitudAltaCuenta obtenerSolicitudPorId(int id);
	boolean responderSolicitud (int id);


}
