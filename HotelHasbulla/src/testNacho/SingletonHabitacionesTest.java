package testNacho;

import modelo.SingletonHabitaciones;
import modelo.Habitacion;
import modelo.Cliente;
import java.sql.Date;
import javax.swing.JOptionPane;

public class SingletonHabitacionesTest {
    public static void main(String[] args) {
        // Crear una instancia del Singleton
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();

        // Crear un cliente
        Cliente cliente1 = new Cliente(1, "Juan", "Perez", "juan.perez@example.com", "123456789", "Ninguna");

        // Definir fechas de reserva
        Date fechaEntrada = Date.valueOf("2024-06-01");
        Date fechaSalida = Date.valueOf("2024-06-05");

        // Probar reservar una habitación disponible
        boolean reservaExitosa = singleton.reservarHabitacion(101, cliente1, fechaEntrada, fechaSalida);
        if (reservaExitosa) {
            JOptionPane.showMessageDialog(null, "Reserva exitosa para la habitación 101");
        } else {
            JOptionPane.showMessageDialog(null, "Fallo al reservar la habitación 101");
        }

        // Intentar reservar la misma habitación de nuevo
        boolean reservaFallida = singleton.reservarHabitacion(101, cliente1, fechaEntrada, fechaSalida);
        if (!reservaFallida) {
            JOptionPane.showMessageDialog(null, "La habitación 101 ya está reservada, no se puede reservar de nuevo");
        } else {
            JOptionPane.showMessageDialog(null, "Error: se pudo reservar nuevamente la habitación 101, lo cual no es correcto");
        }
    }
}
