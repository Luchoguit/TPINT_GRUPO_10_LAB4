package negocioimplementacion;

import java.util.List;

import dao.MovimientoDao;
import daoImplementacion.MovimientoDaoImp;
import entidad.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImp implements MovimientoNegocio {

	private MovimientoDao movimientoDao = new MovimientoDaoImp();	
	
	@Override
	public List<Movimiento> obtenerIngresosPorFechas(int idCuenta, String fechaInicio, String fechaFin) {
		return movimientoDao.obtenerIngresosPorFechas(idCuenta, fechaInicio, fechaFin);
	}

	@Override
	public List<Movimiento> obtenerEgresosPorFechas(int idCuenta, String fechaInicio, String fechaFin) {
		return movimientoDao.obtenerEgresosPorFechas(idCuenta, fechaInicio, fechaFin);
	}

}
