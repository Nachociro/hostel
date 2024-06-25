
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
                this.precio *= 0.75;
            }
            disponible = false;
            System.out.println("Cancha " + this.id_Tenis + " reservada con éxito. Precio final: " + this.precio);
        } else {
            System.out.println("Cancha " + this.id_Tenis + " no está disponible para reservar.");
        }
    }

    public static void reservarCancha(CanchaTenis[] canchas) {
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
        int num = Integer.parseInt(canchaNum);
        String numPersonasStr = JOptionPane.showInputDialog("Ingrese el número de personas que jugarán:");
        int numPersonas = Integer.parseInt(numPersonasStr);
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

    public static void gestionCanchaTenis(CanchaTenis[] canchas) {
        int opcion;
        do {
            opcion = mostrarMenuCancha();
            switch (opcion) {
                case 1:
                    reservarCancha(canchas);
                    break;
                case 2:
                    cancelarReserva(canchas);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);
    }

    public static int mostrarMenuCancha() {
        String[] opciones = {"Hacer reserva de cancha", "Cancelar reserva de cancha", "Salir"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción para la cancha de tenis:",
                "Gestión de Cancha de Tenis", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        return seleccion + 1;
    }
}
