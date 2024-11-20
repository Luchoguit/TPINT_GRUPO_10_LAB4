package entidad;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Cuenta {

    private int id;
    private String numeroCuenta;
    private LocalDateTime fechaCreacion;
    private String cbu;
    private BigDecimal saldo;
    private Usuario usuario; 
    private TipoCuenta tipoCuenta; 
    private boolean estado; 
    
    
    public Cuenta() {}

	public Cuenta(int id, String numeroCuenta, LocalDateTime fechaCreacion, String cbu, BigDecimal saldo,
			Usuario usuario, TipoCuenta tipoCuenta, boolean estado) {
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.fechaCreacion = fechaCreacion;
		this.cbu = cbu;
		this.saldo = saldo;
		this.usuario = usuario;
		this.tipoCuenta = tipoCuenta;
		this.estado = estado;
	}


    public Cuenta(Usuario usuario, TipoCuenta tipoCuenta) {
        this.numeroCuenta = "1000000000";
        this.fechaCreacion = LocalDateTime.now(); 
        this.cbu = null;
        this.saldo = BigDecimal.valueOf(10000);  
        this.usuario = usuario;
        this.tipoCuenta = tipoCuenta;
        this.estado = true;  
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
