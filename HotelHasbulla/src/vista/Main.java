package vista;
import javax.swing.JOptionPane;
import controlador.HabitacionControlador;
import controlador.UsuarioControlador;
import modelo.SingletonHabitaciones;
import modelo.Habitacion;
import modelo.SingletonHabitaciones;
public class Main {

    public static void main(String[] args) {
    	
    	 UsuarioControlador controlador = new UsuarioControlador();
         SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
       //  crearHabitacionesPrueba();
    	
    	
        int opcion = 0;
        String[] roles = {"Recepcionista", "Personal de Limpieza", "Administrador"};

        int seleccionRol = JOptionPane.showOptionDialog(null, "Seleccione su rol:", "Inicio", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);

            if (seleccionRol == 0) {
            	do {
                opcion = mostrarMenuRecepcionista();
                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Reservando habitación");
                        singleton.reservarHabitacion();
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Hacer check in"); 
                        singleton.hacerCheckIn();
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Hacer check out");
                        singleton.hacerCheckOut();
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Gestionar pileta"); // ingresar y egresar de la pileta
                        
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Gestionar reservas de extras"); // reservar o liberar canchas de fulbo, tenis y pileta
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Saliendo del programa");
                        break;                     
             }
            	} while (opcion != 6);
            } 


              if (seleccionRol == 1) {
            	  do {
                opcion = mostrarMenuLimpieza();
                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Empezando la limpieza rapida");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Empezando la limpieza para nuevo huesped");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Saliendo del programa");
                        break;
                }
              } while (opcion != 3);
              }


             if (seleccionRol == 2) {
            	 do {
                opcion = mostrarMenuAdmin();
                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Gestionando personal de limpieza");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Gestionando habitaciones");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Saliendo del programa");
                        break;
                }
            	 } while (opcion != 3);
             }

              
}

    public static int mostrarMenuRecepcionista() {
        String[] opciones = {"Reservar una habitación","Hacer Chek-in","Hacer Chek-out","Gestionar pileta","Gestionar reservas de canchas","Salir"};

        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una acción:", "Menú Recepcionista", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }

    public static int mostrarMenuLimpieza() {
        String[] opciones = {"Limpieza rapida","Limpieza para nuevo huesped","Salir"};

        int seleccion = JOptionPane.showOptionDialog(null,"Seleccione una accion:","Menu Personal de Limpieza", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }

    public static int mostrarMenuAdmin() {
        String[] opciones = {"Gestionar personal de limpieza","Gestionar habitaciones","Salir"};

        int seleccion = JOptionPane.showOptionDialog(null,"Seleccione una accion:","Menu Administrador", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        return seleccion + 1;
    }
    public static void crearHabitacionesPrueba() {
        HabitacionControlador controlador = new HabitacionControlador();

        
        Habitacion habitacion1 = new Habitacion(101, "Individual", "Habitación individual estándar", 50.0, true, true);
        Habitacion habitacion2 = new Habitacion(102, "Doble", "Habitación doble estándar", 75.0, true, false);
        Habitacion habitacion3 = new Habitacion(103, "Suite", "Suite de lujo con vista al mar", 150.0, true, true);

      
        controlador.addHabitacion(habitacion1);
        controlador.addHabitacion(habitacion2);
        controlador.addHabitacion(habitacion3);

        System.out.println("Habitaciones de prueba creadas exitosamente.");
    }

}
