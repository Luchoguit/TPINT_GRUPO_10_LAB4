package negocio;

import java.util.List;

import entidad.Movimiento;

public interface MovimientoNegocio {

	List<Movimiento> obtenerIngresosPorFechas(int idCuenta, String fechaInicio, String fechaFin);
	List<Movimiento> obtenerEgresosPorFechas(int idCuenta, String fechaInicio, String fechaFin);

}
