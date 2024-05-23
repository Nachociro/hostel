package modelo;

<<<<<<< Updated upstream

public class Cliente {
	String nombre;
	String apellido;
	int dni;
	int numerodeTelefono;
	String reseña = "";
=======
public class Cliente {
	private String nombre;
	private String apellido;
	private int dni;
	private int numerodeTelefono;
	private String reseña = "";
	
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
	}
=======
	} 
>>>>>>> Stashed changes
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
