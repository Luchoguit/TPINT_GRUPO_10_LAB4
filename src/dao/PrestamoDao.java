package dao;

import java.util.List;

import entidad.Prestamo;

public interface PrestamoDao {
	
	boolean altaPrestamo(Prestamo prestamo);
	List<Prestamo> listarPrestamos();
	Prestamo obtenerPrestamoPorId(int id);
	List<Prestamo> listarPrestamosCuenta(int idCuenta);
	boolean cancelarPrestamo(int idPrestamo);
}
