package modelo;

import javax.swing.JOptionPane;

public class Cancha_Futbol {
		private int id_futbol;
		private int tamano;
		private double precio;
		private boolean disponible;
		
		public Cancha_Futbol(int id_futbol, int tamano, double precio, boolean disponible) {
			this.id_futbol = id_futbol;
			this.tamano = tamano;
			this.precio = precio;
			this.disponible = disponible;
		}
		
		public int getId_futbol() {
			return id_futbol;
		}
		public void setId_futbol(int id_futbol) {
			this.id_futbol = id_futbol;
		}
		public int getTamano() {
			return tamano;
		}
		public void setTamano(int tamano) {
			this.tamano = tamano;
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
		@Override
		public String toString() {
			return "CanchaFutbol [id_futbol=" + id_futbol + ", tamano=" + tamano + ", precio=" + precio + ", disponible=" + disponible + "]";
		}
		
		public void reservarCancha() {
			if (disponible) {
				disponible = false;
				System.out.println("Cancha " + this.id_futbol + "reservada con éxito.");
			} else {
				System.out.println("Cancha " + this.id_futbol + " no está disponible para reservar.");
			}
		}
	    public static void reservarCancha(Cancha_Futbol[] canchas) {
	        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
	        int num = Integer.parseInt(canchaNum);
	        boolean canchaEncontrada = false;

	        for (Cancha_Futbol cancha : canchas) {
	            if (cancha.getId_futbol() == num) {
	                cancha.reservarCancha();
	                canchaEncontrada = true;
	                break;
	            }
	        }
	        if (!canchaEncontrada) {
	            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
	            }
	    }
}