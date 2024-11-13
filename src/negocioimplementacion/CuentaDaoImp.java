package negocioimplementacion;

import java.util.List;

import dao.CuentaDao;
import entidad.Cuenta;

public class CuentaDaoImp implements CuentaDao {
	
	CuentaDao cuentaDao = new CuentaDaoImp();

	@Override
	public boolean altaCuenta(Cuenta cuenta) {
		return cuentaDao.altaCuenta(cuenta);
	}
	
	@Override
	public List<Cuenta> listarCuentas() {
		return cuentaDao.listarCuentas();
	}

	@Override
	public boolean modificarCuenta(Cuenta cuenta) {
		return cuentaDao.modificarCuenta(cuenta);
	}

	@Override
	public boolean eliminarCuenta(Cuenta cuenta) {
		return cuentaDao.eliminarCuenta(cuenta);
	}


}
