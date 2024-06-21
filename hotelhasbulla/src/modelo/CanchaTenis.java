package modelo;

import javax.swing.JOptionPane;

public class CanchaTenis {
    private int id_Tenis;
    private String piso;
    private double precio;
    private boolean disponible;

    public CanchaTenis(int id_Tenis, String piso, double precio, boolean disponible) {
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

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
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

    public void reservarCancha(int numPersonas) {
        if (disponible) {
            if (numPersonas >= 8) {
                this.precio *= 0.75; // Aplicar 25% de descuento
            }
            disponible = false;
            System.out.println("Cancha " + this.id_Tenis + " reservada con éxito. Precio final: " + this.precio);
        } else {
            System.out.println("Cancha " + this.id_Tenis + " no está disponible para reservar.");
        }
    }

    public static void reservarCancha(CanchaTenis[] canchas, int numPersonas) {
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
        int num = Integer.parseInt(canchaNum);
        boolean canchaEncontrada = false;

        for (CanchaTenis cancha : canchas) {
            if (cancha.getId_Tenis() == num) {
                cancha.reservarCancha(numPersonas);
                canchaEncontrada = true;
                break;
            }
        }
        if (!canchaEncontrada) {
            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
        }
    }

    public void cancelarReserva() {
        if (!disponible) {
            disponible = true;
            System.out.println("Reserva de la cancha " + this.id_Tenis + " cancelada con éxito.");
        } else {
            System.out.println("La cancha " + this.id_Tenis + " no está reservada.");
        }
    }

    public static void cancelarReserva(CanchaTenis[] canchas) {
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a cancelar la reserva:");
        int num = Integer.parseInt(canchaNum);
        boolean canchaEncontrada = false;

        for (CanchaTenis cancha : canchas) {
            if (cancha.getId_Tenis() == num) {
                cancha.cancelarReserva();
                canchaEncontrada = true;
                break;
            }
        }
        if (!canchaEncontrada) {
            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
        }
    }
}
