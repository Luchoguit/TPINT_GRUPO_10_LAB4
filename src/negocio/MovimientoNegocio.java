package negocio;

import java.util.List;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoNegocio {

	List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin);
	List<Movimiento> todosLosMovimientosPorFechas(String fechaInicio, String fechaFin);

}
