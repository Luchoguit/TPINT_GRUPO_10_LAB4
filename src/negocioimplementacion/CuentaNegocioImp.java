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


}
