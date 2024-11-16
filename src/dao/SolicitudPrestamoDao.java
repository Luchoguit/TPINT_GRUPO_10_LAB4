package dao;

import java.util.List;

import entidad.Prestamo;
import entidad.SolicitudPrestamo;

public interface SolicitudPrestamoDao {
	
	boolean registrarSolicitud(Prestamo prestamo);
	List<SolicitudPrestamo> listarSolicitudes();
	boolean aceptarPrestamo(int id);
	boolean rechazarPrestamo(int id);
	

}
