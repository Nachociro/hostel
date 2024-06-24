package vista;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        pantallaInicio.main(args);
    }

    public static void mostrarMenuLimpieza() {
        String[] opciones = {"Limpieza rápida", "Limpieza para nuevo huésped", "Salir"};

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Personal de Limpieza",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Realizando limpieza rápida...");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Realizando limpieza para nuevo huésped...");
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        } while (seleccion != 2);
    }

    public static void mostrarMenuRecepcionista() {
        String[] opciones = {"Reservar una habitación", "Hacer Check-in", "Hacer Check-out", "Gestionar pileta", "Gestionar reservas de canchas", "Salir"};

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Recepcionista",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Reservando una habitación...");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Haciendo Check-in...");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Haciendo Check-out...");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Gestionando pileta...");
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Gestionando reservas de canchas...");
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (seleccion != 5);
    }

    public static void mostrarMenuAdmin() {
        String[] opciones = {"Gestionar personal de limpieza", "Gestionar habitaciones", "Salir"};

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Administrador",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Gestionando personal de limpieza...");
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Gestionando habitaciones...");
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        } while (seleccion != 2);
    }
}
