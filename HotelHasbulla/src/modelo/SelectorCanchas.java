package modelo;

import javax.swing.JOptionPane;
import vista.tablaCanchaFutbol;
import vista.tablaCanchaTenis;

public class SelectorCanchas {

    public static void elegirDeporte() {
        String[] opciones = {"Fútbol", "Tenis", "Salir"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el deporte:",
                "Gestión de Canchas", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);

        switch (seleccion) {
            case 0: // Fútbol
                new tablaCanchaFutbol().setVisible(true);
                break;
            case 1: // Tenis
            	new tablaCanchaTenis().setVisible(true);
                break;
            case 2: // Salir
                JOptionPane.showMessageDialog(null, "Saliendo de la gestión de canchas.");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }
}
