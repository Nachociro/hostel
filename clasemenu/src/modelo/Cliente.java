package modelo;


public class Cliente {
	String nombre;
	String apellido;
	int dni;
	int numerodeTelefono;
	String reseña = "";
	public Cliente(String nombre, String apellido, int dni, int numerodeTelefono, String reseña) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.numerodeTelefono = numerodeTelefono;
		this.reseña = reseña;
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
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public int getNumerodeTelefono() {
		return numerodeTelefono;
	}
	public void setNumerodeTelefono(int numerodeTelefono) {
		this.numerodeTelefono = numerodeTelefono;
	}
	public String getReseña() {
		return reseña;
	}
	public void setReseña(String reseña) {
		this.reseña = reseña;
	}
	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", numerodeTelefono="
				+ numerodeTelefono + ", reseña=" + reseña + "]";
	}
	
}
