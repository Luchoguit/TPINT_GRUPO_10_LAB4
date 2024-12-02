package negocioimplementacion;

import java.util.List;

import dao.CuentaDao;
import daoImplementacion.CuentaDaoImp;
import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;
import negocio.CuentaNegocio;

public class CuentaNegocioImp implements CuentaNegocio{
	
	private CuentaDao cuentaDao = new CuentaDaoImp();

	@Override
	public boolean altaCuenta(Cuenta cuenta) {
		return cuentaDao.altaCuenta(cuenta);
	}
	
	@Override
	public List<Cuenta> listarCuentas(Usuario usuario) {
		return cuentaDao.listarCuentas(usuario);
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		return cuentaDao.modificarCuenta(cuenta);
	}

	@Override
	public boolean eliminarCuenta(int id) {
		return cuentaDao.eliminarCuenta(id);
	}
	
	@Override
    public List<Cuenta> listarTodasLasCuentas() {
        return cuentaDao.listarTodasLasCuentas();
    }

	@Override
	public Cuenta obtenerCuentaPorId(int id) {
		return cuentaDao.obtenerCuentaPorId(id);
	}

	@Override
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		return cuentaDao.obtenerCuentaPorCBU(cbu);
	}
	
	@Override
	public int contarCuentasActivasPorUsuario(int id){
		return cuentaDao.contarCuentasActivasPorUsuario(id);
	}
	
	@Override
	public boolean ActivarCuenta(int id) {
		
		return cuentaDao.ActivarCuenta(id);
	};
	
	@Override
	public List<Cuenta> listarTodasLasCuentasEliminadas(){
		return cuentaDao.listarTodasLasCuentasEliminadas();
	}

	@Override
	public boolean existeCuentaConCbu(String cbu) {
		return cuentaDao.existeCuentaConCbu(cbu);
	}

	@Override
	public boolean realizarTransferencia(Movimiento movimiento) {
		return cuentaDao.realizarTransferencia(movimiento);
	}

	@Override
	public boolean actualizarSaldo(Movimiento movimiento, boolean salida) {
		return cuentaDao.actualizarSaldo(movimiento, salida);
	}

	@Override
	public List<Movimiento> listarMovimientosCuenta(Cuenta cuenta) {
		return cuentaDao.listarMovimientosCuenta(cuenta);
	};
	
	@Override
	public boolean verificarCuentaAsociadaAPrestamo(int idCuenta){
		return cuentaDao.verificarCuentaAsociadaAPrestamo(idCuenta);
	};

	@Override
	public List<Cuenta> obtenerDestinatariosFrecuentes(int idCuenta) {
		return cuentaDao.obtenerDestinatariosFrecuentes(idCuenta);
	}
}
