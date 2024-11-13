package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNegocio {
	
	boolean altaCuenta (Cuenta cuenta);
	List<Cuenta> listarCuentas();
	boolean modificarCuenta(Cuenta cuenta);
	boolean eliminarCuenta (Cuenta cuenta);

}
