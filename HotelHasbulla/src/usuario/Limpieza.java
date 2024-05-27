package usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.JOptionPane;

class LimpiezaHabitacion {
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
            	JOptionPane.showInputDialog("La limpieza durara 5 minutos", fecha);
            } else {
            	JOptionPane.showInputDialog("La limpieza durara 15 minutos", fecha);
            }
        }

        private boolean habitacionTieneReservaActiva() {
            return false;
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
