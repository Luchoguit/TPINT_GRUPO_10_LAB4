package negocio;

import entidad.Prestamo;

public interface CuotaNegocio {

	boolean generarCuotas(Prestamo prestamo);
	boolean pagarCuotas(int idPrestamo, int cantidadCuotas, int idCuenta);
	int cantidadCuotasPagas(int idPrestamo );


}
