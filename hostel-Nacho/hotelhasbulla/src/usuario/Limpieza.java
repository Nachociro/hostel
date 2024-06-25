package usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import controlador.LimpiezaControlador;
import vista.tablaHabitaciones;

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
        if (habitacionTieneLimpiezaActiva()) {
            return "Limpieza de Rutina";
        } else {
            return "Limpieza Definitiva";
        }
    }

    public void duracionLimpieza() {
        int duracionRutina = 0;
        int duracionDefinitiva = 0;

        switch (tipoHabitacion()) {
            case "Individual":
                duracionRutina = 5;
                duracionDefinitiva = 15;
                break;
            case "Doble":
                duracionRutina = 10;
                duracionDefinitiva = 20;
                break;
            case "Triple":
                duracionRutina = 15;
                duracionDefinitiva = 25;
                break;
            case "Suite":
                duracionRutina = 20;
                duracionDefinitiva = 30;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de habitación desconocido");
                return;
        }

        if (tipoLimpieza().equals("Limpieza de Rutina")) {
            JOptionPane.showMessageDialog(null, "La limpieza durará " + duracionRutina + " minutos");
        } else {
            JOptionPane.showMessageDialog(null, "La limpieza durará " + duracionDefinitiva + " minutos");
        }
    }
    
    private String tipoHabitacion() {
       
        tablaHabitaciones tablaHab = new tablaHabitaciones();
        String tipo = tablaHab.obtenerTipoHabitacionSeleccionada();
        
        if (tipo == null) {
        
            JOptionPane.showMessageDialog(null, "Ninguna habitación seleccionada");
        }
        
        return tipo;
    }

    private boolean habitacionTieneLimpiezaActiva() {
        boolean tieneLimpiezaActiva = false;
        LimpiezaControlador controlador = new LimpiezaControlador();

        tieneLimpiezaActiva = controlador.tieneLimpiezaActivaEnHabitacion(numeroHabitacion);

        return tieneLimpiezaActiva;
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