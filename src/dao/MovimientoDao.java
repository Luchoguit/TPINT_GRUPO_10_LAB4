package dao;

import java.util.List;

import entidad.Movimiento;

public interface MovimientoDao {
	
	List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin);

}