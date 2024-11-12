package entidad;

import java.time.LocalDate;

public class Usuario {
    private int idCliente;
    private String nombreUsuario;
    private String contrase�a;
    private String tipo; 
    private boolean estado; 
    private LocalDate fechaCreacion;

    public Usuario(int id, String nombreUsuario, String contrase�a, String tipo, boolean estado, LocalDate fechaCreacion) {
        this.setIdCliente(id);
        this.nombreUsuario = nombreUsuario;
        this.contrase�a = contrase�a;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // M�todos adicionales
    public boolean esAdministrador() {
        return "admin".equals(tipo);
    }

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
