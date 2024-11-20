package negocio;

import entidad.Prestamo;

public interface CuotaNegocio {

	boolean generarCuotas(Prestamo prestamo);
	boolean pagarCuota(int idPrestamo);

}
