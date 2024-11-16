package entidad;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class Cuenta {
    private static BigInteger numeroCuentaInicial = new BigInteger("1000000000");  

    private int id;
    private String numeroCuenta;
    private LocalDateTime fechaCreacion;
    private String cbu;
    private BigDecimal saldo;
    private Usuario usuario; 
    private TipoCuenta tipoCuenta; 
    
    
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

	private boolean estado; 

    public Cuenta(Usuario usuario, TipoCuenta tipoCuenta) {
        this.numeroCuenta = generarNumeroCuenta();
        this.fechaCreacion = LocalDateTime.now(); 
        this.cbu = null;
        this.saldo = BigDecimal.valueOf(10000);  
        this.usuario = usuario;
        this.tipoCuenta = tipoCuenta;
        this.estado = true;  
    }

    private static synchronized String generarNumeroCuenta() {
        numeroCuentaInicial = numeroCuentaInicial.add(BigInteger.ONE);  // Incrementa el número de cuenta
        return String.format("%010d", numeroCuentaInicial);  // Formatea con 10 dígitos, rellena con ceros a la izquierda
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
