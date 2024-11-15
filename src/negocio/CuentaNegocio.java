package negocio;

import java.util.List;

import entidad.Cuenta;
import entidad.Usuario;

public interface CuentaNegocio {
	
	boolean altaCuenta (Cuenta cuenta);
	List<Cuenta> listarCuentas(Usuario usuario);
	boolean modificarCuenta(Cuenta cuenta);
	boolean eliminarCuenta (Cuenta cuenta);
	public List<Cuenta> listarTodasLasCuentas();
}
