package usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import controlador.LimpiezaControlador;
import vista.tablaHabitaciones;

public class Limpieza {

    private int idLimpieza;
    private int numeroHabitacion;
    private LocalDate fecha;
    private String hora;

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
                throw new IllegalArgumentException("Tipo de habitación desconocido");
        }

        if (tipoLimpieza().equals("Limpieza de Rutina")) {
            System.out.println("La limpieza durará " + duracionRutina + " minutos");
        } else {
            System.out.println("La limpieza durará " + duracionDefinitiva + " minutos");
        }
    }

    public void realizarLimpieza() {
        if (validarFechaYHora()) {
            LimpiezaControlador controlador = new LimpiezaControlador();
            controlador.addLimpieza(this);
            System.out.println("Limpieza realizada y registrada en la base de datos");
        } else {
            System.out.println("No se puede realizar la limpieza en la fecha y hora especificadas");
        }
    }

    private String tipoHabitacion() {
        tablaHabitaciones tablaHab = new tablaHabitaciones(null);
        String tipo = tablaHab.obtenerTipoHabitacionSeleccionada();

        if (tipo == null) {
            throw new IllegalArgumentException("Ninguna habitación seleccionada");
        }

        return tipo;
    }

    private boolean habitacionTieneLimpiezaActiva() {
        LimpiezaControlador controlador = new LimpiezaControlador();
        return controlador.tieneLimpiezaActivaEnHabitacion(numeroHabitacion);
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
