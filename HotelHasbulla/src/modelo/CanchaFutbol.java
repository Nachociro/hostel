package modelo;

import javax.swing.JOptionPane;

public class CanchaFutbol {
    private int id_futbol;
    private int tamano;
    private double precio;
    private boolean disponible;

    public CanchaFutbol(int id_futbol, int tamano, double precio, boolean disponible) {
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

    public void reservarCancha(int numPersonas) {
        if (disponible) {
            if (numPersonas >= 8) {
                precio = precio * 0.75; // Aplicar 25% de descuento
            }
            disponible = false;
            System.out.println("Cancha " + id_futbol + " reservada con éxito. Precio final: " + precio);
        } else {
            System.out.println("Cancha " + id_futbol + " no está disponible para reservar.");
        }
    }

    public void cancelarReserva() {
        if (!disponible) {
            disponible = true;
            System.out.println("Reserva de la cancha " + id_futbol + " cancelada con éxito.");
        } else {
            System.out.println("La cancha " + id_futbol + " no está reservada.");
        }
    }

    public static void reservarCancha(CanchaFutbol[] canchas) {
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
        int num = Integer.parseInt(canchaNum);
        String numPersonasStr = JOptionPane.showInputDialog("Ingrese el número de personas que jugarán:");
        int numPersonas = Integer.parseInt(numPersonasStr);
        boolean canchaEncontrada = false;

        for (CanchaFutbol cancha : canchas) {
            if (cancha.getId_futbol() == num) {
                cancha.reservarCancha(numPersonas);
                canchaEncontrada = true;
                break;
            }
        }
        if (!canchaEncontrada) {
            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
        }
    }

    public static void cancelarReserva(CanchaFutbol[] canchas) {
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a cancelar la reserva:");
        int num = Integer.parseInt(canchaNum);
        boolean canchaEncontrada = false;

        for (CanchaFutbol cancha : canchas) {
            if (cancha.getId_futbol() == num) {
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
