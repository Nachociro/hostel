package usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

public class Limpieza {

    int idLimpieza;
    int numeroHabitacion;
    LocalDate fecha;
    String hora;

    public Limpieza(int idLimpieza, int numeroHabitacion, LocalDate fecha, String hora) {
        this.idLimpieza = idLimpieza;
        this.numeroHabitacion = numeroHabitacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public boolean validarFechaYHora() {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();
        return fecha.isAfter(fechaActual) || (fecha.isEqual(fechaActual) && LocalTime.parse(hora).isAfter(horaActual));
    }

    public String tipoLimpieza() {
        if (habitacionTieneReservaActiva()) {
            return "Limpieza de Rutina";
        } else {
            return "Limpieza Definitiva";
        }
    }

    public void duracionLimpieza() {
        if (tipoLimpieza().equals("Limpieza de Rutina")) {
            JOptionPane.showInputDialog("La limpieza durará 5 minutos", fecha);
        } else {
            JOptionPane.showInputDialog("La limpieza durará 15 minutos", fecha);
        }
    }

    private boolean habitacionTieneReservaActiva() {
        return false; // Lógica para verificar si hay una reserva activa en la habitación
    }

    public int getIdLimpieza() {
        return idLimpieza;
    }

    public void setIdLimpieza(int idLimpieza) {
        this.idLimpieza = idLimpieza;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
