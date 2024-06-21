package usuario;

import java.util.LinkedList;

public class Recepcionista {
    private int habitacionesDisponibles;
    private int habitacionesLimpias;
    private LinkedList<Reserva> reservas;

    public Recepcionista(int totalHabitaciones) {
        this.habitacionesDisponibles = totalHabitaciones;
        this.habitacionesLimpias = totalHabitaciones;
        this.reservas = new LinkedList<>();
    }

    public void checkIn(int habitacionesNecesarias) {
        if (habitacionesDisponibles >= habitacionesNecesarias) {
            habitacionesDisponibles -= habitacionesNecesarias;
            habitacionesLimpias -= habitacionesNecesarias;
            System.out.println("Check-in exitoso en " + habitacionesNecesarias + " habitaciones.");
        } else {
            System.out.println("No hay suficientes habitaciones disponibles para el check-in.");
        }
    }

    public void checkOut(int habitacionesLibres) {
        habitacionesDisponibles += habitacionesLibres;
        System.out.println("Check-out exitoso. Se liberaron " + habitacionesLibres + " habitaciones.");
    }

    public void gestionarLimpieza(int habitacionesSucias) {
        if (habitacionesLimpias >= habitacionesSucias) {
            habitacionesLimpias -= habitacionesSucias;
            System.out.println("Se limpiaron " + habitacionesSucias + " habitaciones.");
        } else {
            System.out.println("No hay suficientes habitaciones sucias para limpiar.");
        }
    }

    public void hacerReserva(int cantidadHabitaciones) {
        if (habitacionesDisponibles >= cantidadHabitaciones) {
            reservas.add(new Reserva(cantidadHabitaciones));
            System.out.println("Reserva realizada de " + cantidadHabitaciones + " habitaciones.");
        } else {
            System.out.println("No hay suficientes habitaciones disponibles para la reserva.");
        }
    }

    public void cancelarReserva() {
        if (!reservas.isEmpty()) {
            Reserva ultimaReserva = reservas.removeLast();
            habitacionesDisponibles += ultimaReserva.getCantidadHabitaciones();
            System.out.println("Se canceló la reserva de " + ultimaReserva.getCantidadHabitaciones() + " habitaciones.");
        } else {
            System.out.println("No hay reservas registradas para cancelar.");
        }
    }

    // Getters y setters

    public int getHabitacionesDisponibles() {
        return habitacionesDisponibles;
    }

    public void setHabitacionesDisponibles(int habitacionesDisponibles) {
        this.habitacionesDisponibles = habitacionesDisponibles;
    }

    public int getHabitacionesLimpias() {
        return habitacionesLimpias;
    }

    public void setHabitacionesLimpias(int habitacionesLimpias) {
        this.habitacionesLimpias = habitacionesLimpias;
    }

    // Método principal para probar la clase

    public static void main(String[] args) {
        // Crear un recepcionista con 50 habitaciones
        Recepcionista recepcionista = new Recepcionista(50);

        // Check-in de clientes
        recepcionista.checkIn(2);
        recepcionista.checkIn(3);

        // Hacer una reserva
        recepcionista.hacerReserva(2);

        // Check-out
        recepcionista.checkOut(1);

        // Cancelar reserva
        recepcionista.cancelarReserva();
    }
}

class Reserva {
    private int cantidadHabitaciones;

    public Reserva(int cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public int getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(int cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }
}
