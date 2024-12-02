package dao;

import java.util.List;

import entidad.Cuenta;
import entidad.Movimiento;

public interface MovimientoDao {
	
	List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin);
	List<Movimiento> todosLosMovimientosPorFechas(String fechaInicio, String fechaFin);

}
