package modelo;

import java.sql.Date;
import javax.swing.JOptionPane;
import controlador.HabitacionControlador;
import controlador.ReservaControlador;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    private HabitacionControlador habitacionControlador;
    private ReservaControlador reservaControlador;

    private SingletonHabitaciones() {
        habitacionControlador = new HabitacionControlador();
        reservaControlador = new ReservaControlador();
    }

    public static SingletonHabitaciones getInstance() {
        if (instance == null) {
            instance = new SingletonHabitaciones();
        }
        return instance;
    }

    public boolean reservarHabitacion(int numero_habitacion, Cliente cliente, Date fechaEntrada, Date fechaSalida) {
        Habitacion habitacion = habitacionControlador.getHabitacionByNumero(numero_habitacion);
        if (habitacion != null && habitacion.isDisponibilidad() && !habitacion.isLimpieza()) {
            habitacion.setDisponibilidad(false);
            habitacionControlador.updateHabitacion(habitacion);

            Reserva nuevaReserva = new Reserva(
                Reserva.generarIdReserva(),
                fechaEntrada,
                fechaSalida,
                cliente.getId_huesped(),
                numero_habitacion,
                cliente.getNombre_huesped()
            );
            reservaControlador.addReserva(nuevaReserva);

            JOptionPane.showMessageDialog(null, "Habitación " + numero_habitacion + " reservada a nombre de " + cliente.getNombre_huesped() + " \n para la fecha de " + fechaEntrada + "\n hasta la fecha de " + fechaSalida);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La habitación " + numero_habitacion + " no está disponible para reservar.");
            return false;
        }
    }
}
