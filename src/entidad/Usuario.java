package entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    private int idCliente;
    private String nombreUsuario;
    private String contrase�a;
    private String tipoUsuario; 
    private boolean estado; 
    private LocalDateTime fechaCreacion;

    public Usuario(int id, String nombreUsuario, String contrase�a, String tipoUsuario, boolean estado, LocalDateTime fechaCreacion) {
        this.setIdCliente(id);
        this.nombreUsuario = nombreUsuario;
        this.contrase�a = contrase�a;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Usuario() {}

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrase�a() {
        return contrase�a;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // M�todos adicionales
    public boolean esAdministrador() {
        return "admin".equals(tipoUsuario);
    }

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
