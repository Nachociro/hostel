package modelo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import controlador.HabitacionControlador;
import controlador.ReservaControlador;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    public HabitacionControlador habitacionControlador;
    public ReservaControlador reservaControlador;

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

    public boolean reservarHabitacion() {
        try {
           
            Date fechaEntrada = pedirFecha("Ingrese la fecha de entrada (yyyy-MM-dd):");
            Date fechaSalida = pedirFecha("Ingrese la fecha de salida (yyyy-MM-dd):");

            
            if (fechaEntrada.after(fechaSalida)) {
                JOptionPane.showMessageDialog(null, "La fecha de entrada debe ser anterior a la fecha de salida.");
                return false;
            }

           
            List<Habitacion> habitacionesDisponibles = habitacionControlador.getHabitacionesDisponibles();
            if (habitacionesDisponibles.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay habitaciones disponibles para las fechas seleccionadas.");
                return false;
            }


            mostrarHabitacionesDisponibles(habitacionesDisponibles);

            
            Cliente cliente = Cliente.pedirDatosCliente();

            
            int numeroHabitacion = pedirNumeroHabitacion();

            
            Habitacion habitacion = habitacionControlador.getHabitacionByNumero(numeroHabitacion);
            if (habitacion == null || !habitacion.isDisponibilidad() || habitacion.isLimpieza()) {
                mostrarMensajeHabitacionNoDisponible(numeroHabitacion);
                return false;
            }

          
            habitacion.setDisponibilidad(false);
            habitacionControlador.updateHabitacion(habitacion);

           
            Reserva nuevaReserva = new Reserva(Reserva.generarIdReserva(), fechaEntrada, fechaSalida, cliente.getId_huesped(), numeroHabitacion, cliente.getNombre_huesped());
            reservaControlador.addReserva(nuevaReserva);

            mostrarConfirmacionReserva(numeroHabitacion, cliente.getNombre_huesped(), fechaEntrada, fechaSalida);
            return true;
        } catch (Exception e) {
            mostrarError(e.getMessage());
            return false;
        }
    }

    private void mostrarHabitacionesDisponibles(List<Habitacion> habitacionesDisponibles) {
        StringBuilder sb = new StringBuilder("Habitaciones disponibles para las fechas seleccionadas:\n");
        for (Habitacion habitacion : habitacionesDisponibles) {
            sb.append("Número: ").append(habitacion.getNumero_habitacion()).append(", Tipo: ").append(habitacion.getTipo()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private int pedirNumeroHabitacion() {
        return Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de la habitación a reservar:"));
    }

    private void mostrarMensajeHabitacionNoDisponible(int numeroHabitacion) {
        JOptionPane.showMessageDialog(null, "La habitación con número " + numeroHabitacion + " no está disponible para reservar.");
    }

    private void mostrarConfirmacionReserva(int id_reserva, int numeroHabitacion, String nombreCliente, Date fechaEntrada, Date fechaSalida) {
        JOptionPane.showMessageDialog(null, "Habitación " + numeroHabitacion + " reservada a nombre de " + nombreCliente + " para la fecha de " + fechaEntrada + " hasta la fecha de " + fechaSalida);
    }

    private void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(null, "Error al reservar la habitación: " + mensajeError);
    }


    private Date pedirFecha(String mensaje) {
        String fechaString = JOptionPane.showInputDialog(mensaje);
        LocalDate localDate = LocalDate.parse(fechaString);
        return Date.valueOf(localDate);
    }
    public void hacerCheckIn() {
        String opcion;
        do {
            String[] opciones = {"Ingresar DNI", "Salir"};
            opcion = (String) JOptionPane.showInputDialog(null, "Menú de Check-In", "Opciones", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case "Ingresar DNI":
                    String dniReserva = JOptionPane.showInputDialog("Ingrese el DNI del huésped:");
                    Reserva reserva = buscarReservaPorDNI(Integer.parseInt(dniReserva));
                    if (reserva != null) {
                        if (((Date) reserva.getFecha_entrada()).toLocalDate().isEqual(LocalDate.now())) {
                            realizarCheckIn(reserva);
                            JOptionPane.showMessageDialog(null, "Check-In realizado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha de ingreso no coincide con la fecha actual.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada al DNI ingresado.");
                    }
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo del Menú de Check-In.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (!opcion.equals("Salir"));
    }

    private Reserva buscarReservaPorDNI(int dni) {
        return reservaControlador.buscarReservaPorDNI(dni);
    }

    private void realizarCheckIn(Reserva reserva) {
        Habitacion habitacion = habitacionControlador.getHabitacionByNumero(reserva.getNumero_habitacion());
        habitacion.setLimpieza(false);
        reservaControlador.updateReserva(reserva);
        habitacionControlador.updateHabitacion(habitacion);
    }
    public void hacerCheckOut() {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog("Menú de Check-Out\n1. Ingresar DNI\n2. Salir\nIngrese la opción:");
            switch (opcion) {
                case "1":
                    String dniReserva = JOptionPane.showInputDialog("Ingrese el DNI del huésped:");
                    Reserva reserva = buscarReservaPorDNI(Integer.parseInt(dniReserva));
                    if (reserva != null) {
                        LocalDate fechaSalida = ((Date) reserva.getFecha_salida()).toLocalDate();
                        LocalDate fechaActual = LocalDate.now();
                        if (fechaSalida.isBefore(fechaActual)) {
                            JOptionPane.showMessageDialog(null, "La fecha de salida es anterior a la fecha actual. Debe pagar el costo total.");
                            String[] opciones = {"Salir igualmente", "Quedarse"};
                            int seleccion = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Confirmación",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                            if (seleccion == 0) {
                                realizarCheckOut(reserva);
                                JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                            } else if (seleccion == 1) {
                                // El huésped decide quedarse, no se realiza el check-out
                            }
                        } else if (fechaSalida.isAfter(fechaActual)) {
                            JOptionPane.showMessageDialog(null, "La fecha de salida es posterior a la fecha actual. Se cobrará un extra.");
                            realizarCheckOut(reserva);
                            JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                        } else {
                            realizarCheckOut(reserva);
                            JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva asociada al DNI ingresado.");
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Saliendo del Menú de Check-Out.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (!opcion.equals("2"));
    }
    private void realizarCheckOut(Reserva reserva) {   
        Habitacion habitacion = habitacionControlador.getHabitacionByNumero(reserva.getNumero_habitacion());        
        habitacion.setDisponibilidad(true);        
        habitacionControlador.updateHabitacion(habitacion);
        reservaControlador.deleteReserva(reserva.getId_reserva());
    }

	public void tipoLimpieza() {
		// TODO Auto-generated method stub
		
	}


	public void LimpiezaHabitacion1() {
		// TODO Auto-generated method stub
		
	}

	public void LimpiezaHabitacion() {
		// TODO Auto-generated method stub
		
	}
}
