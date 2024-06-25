package testEma;

import java.time.LocalDate;

public class LimpiezaHabitacion {

    int idLimpieza;
    int numeroHabitacion;
    LocalDate fecha;
    String hora;

    public LimpiezaHabitacion(int idLimpieza, int numeroHabitacion, LocalDate fecha, String hora) {
        this.idLimpieza = idLimpieza;
        this.numeroHabitacion = numeroHabitacion;
        this.fecha = fecha;
        this.hora = hora;
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
