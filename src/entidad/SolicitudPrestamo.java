package entidad;

public class SolicitudPrestamo {
	private int idSolicitud;
	private Prestamo prestamo;
	private boolean aprobado;
	private boolean estado;
	
	public SolicitudPrestamo() {
	}

	public SolicitudPrestamo(Prestamo prestamo, boolean aprobado, boolean estado) {
		this.prestamo = prestamo;
		this.aprobado = aprobado;
		this.estado = estado;
	}

	public SolicitudPrestamo(int idSolicitud, Prestamo prestamo, boolean aprobado, boolean estado) {
		this.idSolicitud = idSolicitud;
		this.prestamo = prestamo;
		this.aprobado = aprobado;
		this.estado = estado;
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public boolean isAprobado() {
		return aprobado;
	}

	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
