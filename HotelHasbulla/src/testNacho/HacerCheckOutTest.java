package testNacho;

import modelo.SingletonHabitaciones;
import javax.swing.JOptionPane;

public class HacerCheckOutTest {
    public static void main(String[] args) {
        
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();

        singleton.hacerCheckOut(); 

        JOptionPane.showMessageDialog(null, "Check-Out completado.");
    }
}