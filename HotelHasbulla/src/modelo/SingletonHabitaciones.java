package modelo;

import java.util.LinkedList;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    private LinkedList<Habitacion> habitaciones;

    private SingletonHabitaciones() {
        habitaciones = new LinkedList<>();
    }

    public static SingletonHabitaciones getInstance() {
        if (instance == null) {
            instance = new SingletonHabitaciones();
        }
        return instance;
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }

    public LinkedList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Habitacion buscarHabitacion(int numero_habitacion) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero_habitacion() == numero_habitacion) {
                return habitacion;
            }
        }
        return null; // Retorna null si no encuentra la habitación
    }
}
