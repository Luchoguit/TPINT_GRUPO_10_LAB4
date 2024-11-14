package entidad;

public class SolicitudAltaCuenta {
	
	public SolicitudAltaCuenta(int id, Cliente cliente, TipoCuenta tipoCuenta, boolean respondida) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.tipoCuenta = tipoCuenta;
		this.respondida = respondida;
	}


	public SolicitudAltaCuenta() {}


	private int id;
	private Cliente cliente;
	private TipoCuenta tipoCuenta;
	private boolean respondida;	
	
	public boolean isRespondida() {
		return respondida;
	}


	public void setRespondida(boolean respondida) {
		this.respondida = respondida;
	}


	public SolicitudAltaCuenta( Cliente cliente, TipoCuenta tipoCuenta, boolean respondida) {
		this.cliente = cliente;
		this.tipoCuenta = tipoCuenta;
		this.respondida = respondida;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}


	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	
	

}
