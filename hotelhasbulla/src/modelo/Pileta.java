package modelo;

import javax.swing.JOptionPane;

public class Pileta {
    private int capacidadMaxima;
    private int cantidadPersonas;

    public Pileta(int capacidadMaxima, int cantidadPersonas) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadPersonas = cantidadPersonas;
    }

    public void ingresarPersonas() {
        int personas;
        try {
            personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a ingresar a la pileta:"));
            if (personas <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad de personas a ingresar debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad de personas.");
            return;
        }

        if (cantidadPersonas + personas <= capacidadMaxima) {
            cantidadPersonas += personas;
            JOptionPane.showMessageDialog(null, "Se han ingresado " + personas + " personas a la pileta.\nTotal: " + cantidadPersonas);
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden ingresar más personas, la pileta está llena.");
        }
    }

    public void retirarPersonas() {
        if (cantidadPersonas > 0) {
            int personas;
            try {
                personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a retirar de la pileta:"));
                if (personas <= 0 || personas > cantidadPersonas) {
                    JOptionPane.showMessageDialog(null, "La cantidad de personas a retirar debe ser mayor que 0 y menor o igual al número actual de personas en la pileta.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad de personas.");
                return;
            }

            cantidadPersonas -= personas;
            JOptionPane.showMessageDialog(null, "Se han retirado " + personas + " personas de la pileta.\nTotal: " + cantidadPersonas);
        } else {
            JOptionPane.showMessageDialog(null, "No hay personas en la pileta para retirar.");
        }
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public static void gestionarPileta(Pileta pileta) {
        int opcion;
        do {
            opcion = mostrarMenuPileta();
            switch (opcion) {
                case 1:
                    pileta.ingresarPersonas();
                    break;
                case 2:
                    pileta.retirarPersonas();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa");
                    break;
            }
        } while (opcion != 3);
    }

    private static int mostrarMenuPileta() {
        String[] opciones = {"Ingresar personas a la pileta", "Retirar personas de la pileta", "Salir"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Gestión de Pileta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return seleccion + 1;
    }
}
