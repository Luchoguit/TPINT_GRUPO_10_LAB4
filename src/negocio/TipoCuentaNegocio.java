package negocio;

import java.util.List;

import entidad.TipoCuenta;

public interface TipoCuentaNegocio {

	List<TipoCuenta> ObtenerTiposCuenta();
	TipoCuenta ObtenerPorId(int id);
}
