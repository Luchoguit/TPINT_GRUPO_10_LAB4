package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cuenta {
    private int id;
    private String numeroCuenta;
    private LocalDateTime fechaCreacion;
    private String cbu;
    private BigDecimal saldo;
    private Cliente cliente; 
    private TipoCuenta tipoCuenta; 

    public Cuenta(int id, String numeroCuenta, LocalDateTime fechaCreacion, String cbu, 
                  BigDecimal saldo, Cliente cliente, TipoCuenta tipoCuenta) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.fechaCreacion = fechaCreacion;
        this.cbu = cbu;
        this.saldo = saldo;
        this.cliente = cliente;
        this.tipoCuenta = tipoCuenta;
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

