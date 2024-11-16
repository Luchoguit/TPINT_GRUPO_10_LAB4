package negocio;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoNegocio {

	boolean altaPrestamo(Prestamo prestamo);
	List<Prestamo> listarPrestamos();
	Prestamo obtenerPrestamoPorId(int id);


}
