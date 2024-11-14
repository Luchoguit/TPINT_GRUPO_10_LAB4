package dao;

import java.util.List;

import entidad.TipoCuenta;

public interface TipoCuentaDao {
	
	List<TipoCuenta> ObtenerTiposCuenta();
	TipoCuenta ObtenerPorId(int id);

}
