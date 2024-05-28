package modelo;

import javax.swing.JOptionPane;

public class Cancha_Tenis {
		private int id_Tenis;
		private String piso;
		private double precio;
		private boolean disponible;
		
		public Cancha_Tenis(int id_Tenis, String piso, double precio, boolean disponible) {
			this.id_Tenis = id_Tenis;
			this.piso = piso;
			this.precio = precio;
			this.disponible = disponible;
		}
		
		public int getId_Tenis() {
			return id_Tenis;
		}
		public void setId_Tenis(int id_Tenis) {
			this.id_Tenis = id_Tenis;
		}
		public String getpiso() {
			return piso;
		}
		public void setpiso(String piso) {
			this.piso = piso;
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
			return "CanchaTenis [id_Tenis=" + id_Tenis + ", piso=" + piso + ", precio=" + precio + ", disponible=" + disponible + "]";
		}
		
		public void reservarCancha() {
			if (disponible) {
				disponible = false;
				System.out.println("Cancha " + this.id_Tenis + "reservada con éxito.");
			} else {
				System.out.println("Cancha " + this.id_Tenis + " no está disponible para reservar.");
			}
		}
	    public static void reservarCancha(Cancha_Tenis[] canchas) {
	        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
	        int num = Integer.parseInt(canchaNum);
	        boolean canchaEncontrada = false;

	        for (Cancha_Tenis cancha : canchas) {
	            if (cancha.getId_Tenis() == num) {
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