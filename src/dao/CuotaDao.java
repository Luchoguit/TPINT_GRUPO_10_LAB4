package dao;

import entidad.Prestamo;

public interface CuotaDao {
	
	boolean generarCuotas(Prestamo prestamo);
	boolean pagarCuota(int idPrestamo);

}
