package testNacho;

import modelo.SingletonHabitaciones;
import javax.swing.JOptionPane;

public class HacerReservaTest {
    public static void main(String[] args) {
        
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();

       
        boolean reservaExitosa = singleton.reservarHabitacion();
        
        if (reservaExitosa) {
            JOptionPane.showMessageDialog(null, "Reserva exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "La reserva no se pudo realizar");
        }
    }
}
