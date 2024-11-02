package entidad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movimiento {
    private int id;
    private Cuenta cuentaOrigen; 
    private TipoMovimiento tipoMovimiento; 
    private String detalle;
    private LocalDateTime fechaHora;
    private BigDecimal importe;
    private Cuenta cuentaDestino; 

    public Movimiento(int id, Cuenta cuentaOrigen, TipoMovimiento tipoMovimiento, String detalle, 
                      LocalDateTime fechaHora, BigDecimal importe, Cuenta cuentaDestino) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.tipoMovimiento = tipoMovimiento;
        this.detalle = detalle;
        this.fechaHora = fechaHora;
        this.importe = importe;
        this.cuentaDestino = cuentaDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
