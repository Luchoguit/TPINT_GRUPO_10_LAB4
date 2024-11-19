package dao;

import java.math.BigDecimal;
import java.util.List;

import entidad.Cuenta;
import entidad.Movimiento;
import entidad.Usuario;

public interface CuentaDao {
	
	boolean altaCuenta (Cuenta cuenta);
	List<Cuenta> listarCuentas(Usuario usuario);
	boolean modificarCuenta(Cuenta cuenta);
	boolean eliminarCuenta (int id);
	public List<Cuenta> listarTodasLasCuentas();
	public List<Cuenta> listarTodasLasCuentasEliminadas();
	Cuenta obtenerCuentaPorId(int id);

	Cuenta obtenerCuentaPorCBU(String cbu);
	int contarCuentasActivasPorUsuario(int id);
	boolean ActivarCuenta(int id);
	boolean existeCuentaConCbu(String cbu);
	boolean realizarTransferencia(Movimiento movimiento);
	boolean actualizarSaldo(Movimiento movimiento, boolean salida);
	List<Movimiento> listarMovimientosCuenta(Cuenta cuenta);
	
}
