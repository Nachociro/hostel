package modelo;

import javax.swing.JOptionPane;

public class Pileta {
	private int cantidadPersonas;
	
	public Pileta() {
        this.cantidadPersonas = 0;
    }
	 public void ingresarPersonas() {
	        if (cantidadPersonas < 50) {
	            int personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a ingresar a la pileta:"));
	            if (personas > 0) {
	                if (cantidadPersonas + personas <= 50) {
	                    cantidadPersonas += personas;
	                    JOptionPane.showMessageDialog(null, "Se han ingresado " + personas + " personas a la pileta.\n Total: " + cantidadPersonas);
	                } else {
	                    JOptionPane.showMessageDialog(null, "No se pueden ingresar más personas, la pileta está llena.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "La cantidad de personas a ingresar debe ser mayor que 0.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "La pileta ya está llena, no se pueden ingresar más personas.");
	        }
	 }
	        
	public void retirarPersonas() {
	            if (cantidadPersonas > 0) {
	                int personas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de personas a retirar de la pileta:"));
	                if (personas > 0 && personas <= cantidadPersonas) {
	                    cantidadPersonas -= personas;
	                    JOptionPane.showMessageDialog(null, "Se han retirado " + personas + " personas de la pileta. Total: " + cantidadPersonas);
	                } else {
	                    JOptionPane.showMessageDialog(null, "La cantidad de personas a retirar debe ser mayor que 0 y menor o igual al número actual de personas en la pileta.");
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "No hay personas en la pileta para retirar.");
	            }
	        }	        

	public static void gestionarPileta() {
	    Pileta pileta = new Pileta();
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


	public static int mostrarMenuPileta() {
	    String[] opciones = {"Ingresar personas a la pileta", "Retirar personas de la pileta", "Salir"};
	    int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Gestión de Pileta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
	    return seleccion + 1;
	}  
}



	

