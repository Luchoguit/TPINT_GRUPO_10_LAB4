package dao;

import entidad.Prestamo;

public interface CuotaDao {
	
	boolean generarCuotas(Prestamo prestamo);
	boolean pagarCuotas(int idPrestamo, int cantidadCuotas, int idCuenta);
	int cantidadCuotasPagas(int idPrestamo );
	boolean esUltimaCuotaPagada(int idPrestamo);

}
