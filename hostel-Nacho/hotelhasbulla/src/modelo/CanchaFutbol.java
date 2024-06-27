package modelo;

import javax.swing.JOptionPane;
import controlador.CanchaFutbolControlador;

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
                precio *= 0.75;
            }
            disponible = false;
            System.out.println("Cancha " + id_futbol + " reservada con éxito. Precio final: " + precio);
        } else {
            System.out.println("Cancha " + id_futbol + " no está disponible para reservar.");
        }
    }

    public static void reservarCancha(CanchaFutbol[] canchas) {
        CanchaFutbolControlador controlador = new CanchaFutbolControlador();
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a reservar:");
        int num = Integer.parseInt(canchaNum);
        String numPersonasStr = JOptionPane.showInputDialog("Ingrese el número de personas que jugarán:");
        int numPersonas = Integer.parseInt(numPersonasStr);
        boolean canchaEncontrada = false;

        for (CanchaFutbol cancha : canchas) {
            if (cancha.getId_futbol() == num) {
                if (controlador.estaCanchaDisponible(cancha.getId_futbol())) {
                    cancha.reservarCancha(numPersonas);
                    controlador.reservarCancha(cancha.getId_futbol(), numPersonas);
                } else {
                    JOptionPane.showMessageDialog(null, "La cancha ya está reservada. No se puede realizar la reserva.");
                }
                canchaEncontrada = true;
                break;
            }
        }
        if (!canchaEncontrada) {
            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
        }
    }

    public void cancelarReserva() {
        if (disponible) {
            System.out.println("La cancha " + id_futbol + " no está reservada.");
        } else {
            disponible = true;
            System.out.println("Reserva de la cancha " + id_futbol + " cancelada con éxito.");
        }
    }
   
    public static void cancelarReserva(CanchaFutbol[] canchas) {
        CanchaFutbolControlador controlador = new CanchaFutbolControlador();
        String canchaNum = JOptionPane.showInputDialog("Ingrese el número de la cancha a cancelar la reserva:");
        int num = Integer.parseInt(canchaNum);
        boolean canchaEncontrada = false;

        for (CanchaFutbol cancha : canchas) {
            if (cancha.getId_futbol() == num) {
                if (!controlador.estaCanchaDisponible(cancha.getId_futbol())) {
                    cancha.cancelarReserva();
                    controlador.cancelarReserva(cancha.getId_futbol());
                } else {
                    JOptionPane.showMessageDialog(null, "La cancha no está reservada. No se puede cancelar la reserva.");
                }
                canchaEncontrada = true;
                break;
            }
        }
        if (!canchaEncontrada) {
            JOptionPane.showMessageDialog(null, "Cancha no encontrada.");
        }
    }

    public static void gestionCanchaFutbol(CanchaFutbol[] canchas) {
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
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción para la cancha:",
                "Gestión de Cancha de Fútbol", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        return seleccion + 1;
    }
}