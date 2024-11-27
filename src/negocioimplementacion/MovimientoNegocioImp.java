package negocioimplementacion;

import java.util.List;

import dao.MovimientoDao;
import daoImplementacion.MovimientoDaoImp;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImp implements MovimientoNegocio {

	private MovimientoDao movimientoDao = new MovimientoDaoImp();	
	
	@Override
	public List<Movimiento> obtenerMovimientosPorFechas(int idCuenta, String fechaInicio, String fechaFin) {
		return movimientoDao.obtenerMovimientosPorFechas(idCuenta, fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> todosLosMovimientosPorFechas(String fechaInicio, String fechaFin) {
		return movimientoDao.todosLosMovimientosPorFechas(fechaInicio, fechaFin);
	}

}
