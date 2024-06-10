package vista;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        pantallaInicio.main(args);
    }

    
    public static int mostrarMenuLimpieza() {
        String[] opciones = {"Limpieza rápida", "Limpieza para nuevo huésped", "Salir"};

        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Personal de Limpieza", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }

    public static int mostrarMenuRecepcionista() {
        String[] opciones = {"Reservar una habitación", "Hacer Check-in", "Hacer Check-out", "Gestionar pileta", "Gestionar reservas de canchas", "Salir"};

        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Recepcionista", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }

    public static int mostrarMenuAdmin() {
        String[] opciones = {"Gestionar personal de limpieza", "Gestionar habitaciones", "Salir"};

        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Administrador", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }

  
}
