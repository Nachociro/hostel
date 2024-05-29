package testNacho;

import modelo.SingletonHabitaciones;
import javax.swing.JOptionPane;

public class HacerCheckInTest {
    public static void main(String[] args) {
        
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();

        singleton.hacerCheckIn(); 

        JOptionPane.showMessageDialog(null, "Check-In completado.");
    }
}