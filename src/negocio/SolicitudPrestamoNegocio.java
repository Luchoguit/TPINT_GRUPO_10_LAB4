package negocio;

import java.util.List;

import entidad.Prestamo;
import entidad.SolicitudPrestamo;

public interface SolicitudPrestamoNegocio {

	boolean registrarSolicitud(Prestamo prestamo);
	List<SolicitudPrestamo> listarSolicitudes();
	boolean aceptarPrestamo(int id);
	boolean rechazarPrestamo(int id);

}
