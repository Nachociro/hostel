package modelo;

import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import vista.calendario;
import vista.tablaHabitaciones;
import controlador.HabitacionControlador;
import controlador.ReservaControlador;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    private HabitacionControlador habitacionControlador;
    private ReservaControlador reservaControlador;
    private Habitacion seleccionada;

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
            calendario cal = new calendario();
            cal.setVisible(true);

            // Esperar hasta que se seleccionen las fechas en el calendario
            synchronized (cal) {
                while (!cal.isFechasSeleccionadas()) {
                    try {
                        cal.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Obtener las fechas después de que el usuario las ha seleccionado
            Date fechaEntrada = (Date) cal.getFecha1();
            Date fechaSalida = (Date) cal.getFecha2();

            // Verificar que las fechas no sean nulas
            if (fechaEntrada == null || fechaSalida == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar ambas fechas.");
                return false;
            }

            if (fechaEntrada.after(fechaSalida)) {
                JOptionPane.showMessageDialog(null, "La fecha de entrada debe ser anterior a la fecha de salida.");
                return false;
            }

            tablaHabitaciones frame = new tablaHabitaciones(fechaEntrada, fechaSalida, this);
            frame.setVisible(true);

            // Verificar disponibilidad y limpieza de la habitación seleccionada
            if (!seleccionada.isDisponibilidad() || seleccionada.isLimpieza()) {
                mostrarMensajeHabitacionNoDisponible(seleccionada.getNumero_habitacion());
                return false;
            }

            // Pedir datos del cliente
            Cliente cliente = Cliente.pedirDatosCliente();

            // Marcar la habitación como no disponible
            seleccionada.setDisponibilidad(false);
            habitacionControlador.updateHabitacion(seleccionada);

            // Crear nueva reserva y agregarla al controlador de reservas
            Reserva nuevaReserva = new Reserva(Reserva.generarIdReserva(), fechaEntrada, fechaSalida, cliente.getId_huesped(), seleccionada.getNumero_habitacion(), cliente.getNombre_huesped());
            reservaControlador.addReserva(nuevaReserva);

            // Mostrar confirmación de reserva
            mostrarConfirmacionReserva(nuevaReserva.getId_reserva(), seleccionada.getNumero_habitacion(), cliente.getNombre_huesped(), fechaEntrada, fechaSalida);
            return true;
        } catch (Exception e) {
            mostrarError(e.getMessage());
            return false;
        }
    }

    public void setSeleccionada(Habitacion habitacion) {
        this.seleccionada = habitacion;
    }

    private void mostrarMensajeHabitacionNoDisponible(int numeroHabitacion) {
        JOptionPane.showMessageDialog(null, "La habitación con número " + numeroHabitacion + " no está disponible para reservar.");
    }

    private void mostrarConfirmacionReserva(int id_reserva, int numeroHabitacion, String nombreCliente, Date fechaEntrada, Date fechaSalida) {
        JOptionPane.showMessageDialog(null, "Se hizo la reserva número: " + id_reserva + "\nPara la habitación " + numeroHabitacion + "\nReservada a nombre de " + nombreCliente + "\nPara la fecha de " + fechaEntrada + "\nHasta la fecha de " + fechaSalida);
    }

    private void mostrarError(String mensajeError) {
        JOptionPane.showMessageDialog(null, "Error al reservar la habitación: " + mensajeError);
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
                                JOptionPane.showMessageDialog(null, "Que disfrute los días que le quedan");
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
        String resena = JOptionPane.showInputDialog("Por favor, deja una reseña sobre tu estancia:");
        Cliente cliente = Cliente.pedirDatosCliente();
        cliente.setResena(resena);

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
