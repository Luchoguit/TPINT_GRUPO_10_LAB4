package dao;

import java.util.List;

import entidad.Cuenta;

public interface CuentaDao {
	
	boolean altaCuenta (Cuenta cuenta);
	List<Cuenta> listarCuentas();
	boolean modificarCuenta(Cuenta cuenta);
	boolean eliminarCuenta (Cuenta cuenta);

}
