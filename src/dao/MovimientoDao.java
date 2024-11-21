package dao;

import java.util.List;

import entidad.Movimiento;

public interface MovimientoDao {
	
	List<Movimiento> obtenerIngresosPorFechas(int idCuenta, String fechaInicio, String fechaFin);
	List<Movimiento> obtenerEgresosPorFechas(int idCuenta, String fechaInicio, String fechaFin);

}
