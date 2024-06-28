package usuario;

import java.time.LocalDate;
import java.time.LocalTime;

import controlador.LimpiezaControlador;
import vista.tablaHabitaciones;

public class Limpieza {

    private int id_limpieza;
    private int numero_habitacion;
    private LocalDate fecha;
    private String hora;

    public Limpieza(int id_limpieza, int numero_habitacion, LocalDate fecha, String hora) {
        this.id_limpieza = id_limpieza;
        this.numero_habitacion = numero_habitacion;
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

    public int duracionLimpieza() {
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
            return duracionRutina;
        } else {
            return duracionDefinitiva;
        }
    }

    public void realizarLimpieza() {
        if (validarFechaYHora()) {
            LimpiezaControlador controlador = new LimpiezaControlador();
            controlador.addLimpieza(this);

            // Registro del tiempo de inicio
            LocalTime horaInicio = LocalTime.now();

            try {
                // Simulación del tiempo de limpieza
                Thread.sleep(duracionLimpieza() * 60000); // convertir minutos a milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Registro del tiempo de fin después de la simulación
            LocalTime horaFin = LocalTime.now();

            // Puedes registrar estos tiempos donde lo necesites, como en una base de datos o sistema de registros
            System.out.println("Limpieza realizada y registrada en la base de datos");
            System.out.println("Hora de inicio: " + horaInicio + ", Hora de fin: " + horaFin);
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
        return controlador.tieneLimpiezaActivaEnHabitacion(numero_habitacion);
    }

    public int getid_limpieza() {
        return id_limpieza;
    }

    public void setid_limpieza(int id_limpieza) {
        this.id_limpieza = id_limpieza;
    }

    public int getnumero_habitacion() {
        return numero_habitacion;
    }

    public void setnumero_habitacion(int numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
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