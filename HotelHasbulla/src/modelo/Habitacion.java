package modelo;

public class Habitacion {
<<<<<<< Updated upstream

=======
	private int num;
	private int cantHuespedes;
	private double precio;    
	private boolean disponible;
	private boolean limpieza;
	
	public Habitacion(int num, int cantHuespedes, double precio, boolean disponible, boolean limpieza) {
		super();
		this.num = num;
		this.cantHuespedes = cantHuespedes;
		this.precio = precio;
		this.disponible = disponible;
		this.limpieza = limpieza;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCantHuespedes() {
		return cantHuespedes;
	}
	public void setCantHuespedes(int cantHuespedes) {
		this.cantHuespedes = cantHuespedes;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public boolean isLimpieza() {
		return limpieza;
	}
	public void setLimpieza(boolean limpieza) {
		this.limpieza = limpieza;
	}
	@Override
	public String toString() {
		return "Habitacion [num=" + num + ", cantHuespedes=" + cantHuespedes + ", precio=" + precio + ", disponible="
				+ disponible + ", limpieza=" + limpieza + "]";
	}
>>>>>>> Stashed changes
}
