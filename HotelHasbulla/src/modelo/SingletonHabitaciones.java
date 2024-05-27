package modelo;

import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

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
        return null; // 
    }
  
    public boolean reservarHabitacion(int numero_habitacion, Cliente cliente, Date fechaEntrada, Date fechaSalida) {
    	
    	Habitacion habitacion = buscarHabitacion(numero_habitacion);
        if (habitacion != null && habitacion.isDisponibilidad() && !habitacion.isLimpieza()) {
            habitacion.setDisponibilidad(false);
            Reserva nuevaReserva = new Reserva(
                generarIdReserva(),
                fechaEntrada,
                fechaSalida,
                cliente.getId_huesped(),
                numero_habitacion
            );
           
            JOptionPane.showMessageDialog(null, "Habitación " + numero_habitacion + " reservada con éxito para el cliente " + cliente.getNombre_huesped() + ".");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "La habitación " + numero_habitacion + " no está disponible para reservar.");
            return false;
        }
    }

    private int generarIdReserva() {
        
		
        return ++idReserva;
    }
}
