package negocioimplementacion;

import java.util.List;

import dao.CuentaDao;
import daoImplementacion.CuentaDaoImp;
import entidad.Cuenta;
import entidad.Usuario;
import negocio.CuentaNegocio;

public class CuentaNegocioImp implements CuentaNegocio{
	
	CuentaDao cuentaDao = new CuentaDaoImp();

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
	public boolean eliminarCuenta(Cuenta cuenta) {
		return cuentaDao.eliminarCuenta(cuenta);
	}
	
	@Override
    public List<Cuenta> listarTodasLasCuentas() {
        return cuentaDao.listarTodasLasCuentas();
    }


}
