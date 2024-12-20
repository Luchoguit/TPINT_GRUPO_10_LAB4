package negocio;

import java.util.List;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;

public interface CuentaNegocio {
	
	boolean altaCuenta (Cuenta cuenta);
	List<Cuenta> listarCuentas(Usuario usuario);
	boolean modificarCuenta(Cuenta cuenta);
	boolean eliminarCuenta (int id);
	public List<Cuenta> listarTodasLasCuentas();
	public List<Cuenta> listarTodasLasCuentasEliminadas();
	Cuenta obtenerCuentaPorId(int id);
	
	
	
	Cuenta obtenerCuentaPorCBU(String cbu);
	int contarCuentasActivasPorUsuario(int id);
	public boolean ActivarCuenta(int id);
	boolean existeCuentaConCbu(String cbu);
	boolean realizarTransferencia(Movimiento movimiento);
	boolean actualizarSaldo(Movimiento movimiento, boolean salida);
	List<Movimiento> listarMovimientosCuenta(Cuenta cuenta);
	boolean verificarCuentaAsociadaAPrestamo(int idCuenta);
	
	List<Cuenta> obtenerDestinatariosFrecuentes(int idCuenta);

}
