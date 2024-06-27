package modelo;

import javax.swing.JOptionPane;
import vista.TablaPileta;

public class Pileta {
    private int capacidadMaxima;
    private int cantidadPersonas;

    public Pileta(int capacidadMaxima, int cantidadPersonas) {
        this.capacidadMaxima = capacidadMaxima;
        this.cantidadPersonas = cantidadPersonas;
    }

    public void ingresarPersonas() {
        if (cantidadPersonas < capacidadMaxima) {
	        int personas;
	        boolean ingresoValido = false;
	        do {
	            try {
	                personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a ingresar a la pileta:"));
	                if (personas <= 0) {
	                    JOptionPane.showMessageDialog(null, "La cantidad de personas a ingresar debe ser mayor que 0.");
	                } else if (cantidadPersonas + personas > capacidadMaxima) {
	                    JOptionPane.showMessageDialog(null, "Esa cantidad de personas sobrepasan el límite de la pileta (50).");
	                } else {
	                    ingresoValido = true;
	                    cantidadPersonas += personas;
	                    JOptionPane.showMessageDialog(null, "Se han ingresado " + personas + " personas a la pileta.\nTotal: " + cantidadPersonas);
	                }
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad de personas.");
	            }
	        } while (!ingresoValido);
	    } else {
	        JOptionPane.showMessageDialog(null, "Capacidad máxima alcanzada, no se pueden ingresar personas.");
	    }
    }
    
    public void retirarPersonas() {
        if (cantidadPersonas > 0) {
            int personas;
            boolean ingresoValido = false;
            do {
                try {
                    personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a retirar de la pileta:"));
                    if (personas <= 0 || personas > cantidadPersonas) {
                        JOptionPane.showMessageDialog(null, "La cantidad de personas a retirar debe ser mayor que 0 y menor o igual al número actual de personas en la pileta.");
                    } else {
                        ingresoValido = true;
                        cantidadPersonas -= personas;
                        JOptionPane.showMessageDialog(null, "Se han retirado " + personas + " personas de la pileta.\nTotal: " + cantidadPersonas);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido para la cantidad de personas.");
                }
            } while (!ingresoValido);
        } else {
            JOptionPane.showMessageDialog(null, "No hay personas en la pileta para retirar.");
        }
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public static void gestionarPileta() {
        TablaPileta frame = new TablaPileta();
        frame.setVisible(true);
    }
}
