package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Prestamo {
	
	private int idPrestamo;
	private Cliente cliente;
	private Cuenta cuenta;
	private LocalDateTime fechaAlta;
	private BigDecimal importePedido;
	private int plazoMeses;
	private BigDecimal importeMensual;
	private int cantidadCuotas;
	
	
	public Prestamo() {	}


	public Prestamo(Cliente cliente, Cuenta cuenta, BigDecimal importePedido, int plazoMeses,
			BigDecimal importeMensual, int cantidadCuotas) {
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.importePedido = importePedido;
		this.plazoMeses = plazoMeses;
		this.importeMensual = importeMensual;
		this.cantidadCuotas = cantidadCuotas;
	}


	public int getIdPrestamo() {
		return idPrestamo;
	}


	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}


	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}


	public BigDecimal getImportePedido() {
		return importePedido;
	}


	public void setImportePedido(BigDecimal importePedido) {
		this.importePedido = importePedido;
	}


	public int getPlazoMeses() {
		return plazoMeses;
	}


	public void setPlazoMeses(int plazoMeses) {
		this.plazoMeses = plazoMeses;
	}


	public BigDecimal getImporteMensual() {
		return importeMensual;
	}


	public void setImporteMensual(BigDecimal importeMensual) {
		this.importeMensual = importeMensual;
	}


	public int getCantidadCuotas() {
		return cantidadCuotas;
	}


	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	
	
	
	

}
