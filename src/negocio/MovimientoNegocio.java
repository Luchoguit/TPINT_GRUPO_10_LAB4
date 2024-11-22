package negocio;

import java.util.List;

import entidad.Movimiento;

public interface MovimientoNegocio {

	List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin);

}
