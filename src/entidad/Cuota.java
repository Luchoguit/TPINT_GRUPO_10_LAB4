package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cuota {

	private int idCuota;
	private Prestamo prestamo;
	private int numeroCuota;
	private BigDecimal monto;
	private LocalDateTime fechaPago;
	
	
	public Cuota() {
	}


	public Cuota(Prestamo prestamo, int numeroCuota, BigDecimal monto, LocalDateTime fechaPago) {
		this.prestamo = prestamo;
		this.numeroCuota = numeroCuota;
		this.monto = monto;
		this.fechaPago = fechaPago;
	}


	public Cuota(int idCuota, Prestamo prestamo, int numeroCuota, BigDecimal monto, LocalDateTime fechaPago) {
		this.idCuota = idCuota;
		this.prestamo = prestamo;
		this.numeroCuota = numeroCuota;
		this.monto = monto;
		this.fechaPago = fechaPago;
	}


	public int getIdCuota() {
		return idCuota;
	}


	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}


	public Prestamo getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}


	public int getNumeroCuota() {
		return numeroCuota;
	}


	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}


	public BigDecimal getMonto() {
		return monto;
	}


	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}


	public LocalDateTime getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(LocalDateTime fechaPago) {
		this.fechaPago = fechaPago;
	}
	
	
	
}
