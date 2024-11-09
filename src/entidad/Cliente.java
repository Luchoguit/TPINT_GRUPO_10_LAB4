package entidad;
import java.time.LocalDate;

public class Cliente {
    private int id;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private Localidad localidadCliente;
    private Provincia ProvinciaCliente;
    private String correo;
    private String telefono;
    private String direccion;

    public Cliente(int id, String dni, String cuil, String nombre, String apellido, 
                   String sexo, String nacionalidad, LocalDate fechaNacimiento, String direccion, 
                   Localidad localidadCliente, Provincia provinciaCliente, String correo, String telefono) {
        this.id = id;
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.localidadCliente = localidadCliente;
        this.correo = correo;
        this.telefono = telefono;
        this.setDireccion(direccion);
    }

    public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Localidad getLocalidadCliente() {
        return localidadCliente;
    }

    public void setLocalidadCliente(Localidad localidadCliente) {
        this.localidadCliente = localidadCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public Provincia getProvinciaCliente() {
		return ProvinciaCliente;
	}

	public void setProvinciaCliente(Provincia provinciaCliente) {
		ProvinciaCliente = provinciaCliente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}

