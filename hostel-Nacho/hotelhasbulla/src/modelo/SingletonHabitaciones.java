package modelo;

import javax.swing.JOptionPane;
import vista.calendario;
import vista.tablaHabitaciones;
import vista.DatosCliente;
import controlador.HabitacionControlador;
import controlador.ReservaControlador;
import java.sql.Date;
import java.util.List;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    private HabitacionControlador habitacionControlador;
    private ReservaControlador reservaControlador;
    private Habitacion habitacionSeleccionada;
    private java.util.Date fechaEntrada;
    private java.util.Date fechaSalida;
    private boolean fechasSeleccionadas;
    private DatosCliente datosCliente;

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

    public void setFechas(java.util.Date fechaEntrada, java.util.Date fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.fechasSeleccionadas = true;
    }

    public void setSeleccionada(Habitacion habitacion) {
        this.habitacionSeleccionada = habitacion;
    }

    public void reservarHabitacion() {
        calendario cal = new calendario();
        cal.setVisible(true);

        cal.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (fechasSeleccionadas) {
                    abrirTablaHabitaciones();
                }
            }
        });
    }

    private void abrirTablaHabitaciones() {
        tablaHabitaciones tab = new tablaHabitaciones(this);
        tab.setVisible(true);
        tab.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (habitacionSeleccionada != null) {
                    abrirDatosCliente();
                }
            }
        });
    }

    private void abrirDatosCliente() {
        pedirDatosCliente();
    }

    public void pedirDatosCliente() {
        DatosCliente datos = new DatosCliente();
        datos.setVisible(true);

        datos.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                int dniHuesped = datos.getDniHuesped();
                finalizarReserva(dniHuesped);
            }
        });
    }

    public boolean finalizarReserva(int dniHuesped) {
        if (habitacionSeleccionada != null && fechaEntrada != null && fechaSalida != null) {

            int idReserva = Reserva.generarIdReserva();

            Reserva reserva = new Reserva(idReserva, new Date(fechaEntrada.getTime()), new Date(fechaSalida.getTime()), dniHuesped,
                    habitacionSeleccionada.getNumero_habitacion());

            reservaControlador.addReserva(reserva);

            habitacionSeleccionada.setDisponibilidad(false);

            JOptionPane.showMessageDialog(null,
                    "Reserva realizada correctamente para habitación número: " + habitacionSeleccionada.getNumero_habitacion()
                            + "\nDNI del huésped: " + dniHuesped);

            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error al finalizar la reserva. Seleccione una habitación y fechas válidas.");
            return false;
        }
    }

    public List<Habitacion> getHabitacionesDisponibles() {
        return habitacionControlador.getHabitacionesDisponibles();
    }

    public void hacerCheckIn() {
        String opcion;
        do {
            String[] opciones = { "Ingresar DNI", "Salir" };
            opcion = (String) JOptionPane.showInputDialog(null, "Menú de Check-In", "Opciones",
                    JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (opcion) {
                case "Ingresar DNI":
                    String dniReserva = JOptionPane.showInputDialog("Ingrese el DNI del huésped:");
                    Reserva reserva = buscarReservaPorDNI(Integer.parseInt(dniReserva));
                    if (reserva != null) {
                        java.util.Date fechaReserva = reserva.getFecha_entrada();
                        java.util.Date fechaActual = new java.util.Date();
                        if (fechaReserva.equals(fechaActual)) {
                            realizarCheckIn(reserva);
                            JOptionPane.showMessageDialog(null, "Check-In realizado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "La fecha de ingreso no coincide con la fecha actual.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se encontró ninguna reserva asociada al DNI ingresado.");
                    }
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo del Menú de Check-In.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción no válida. Por favor, seleccione una opción válida.");
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
                        java.util.Date fechaSalida = reserva.getFecha_salida();
                        java.util.Date fechaActual = new java.util.Date();
                        if (fechaSalida.before(fechaActual)) {
                            JOptionPane.showMessageDialog(null,
                                    "La fecha de salida es anterior a la fecha actual. Debe pagar el costo total.");
                            String[] opciones = { "Salir igualmente", "Quedarse" };
                            int seleccion = JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Confirmación",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones,
                                    opciones[0]);
                            if (seleccion == 0) {
                                realizarCheckOut(reserva);
                                JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                            } else if (seleccion == 1) {
                                JOptionPane.showMessageDialog(null, "Que disfrute los días que le quedan");
                            }
                        } else if (fechaSalida.after(fechaActual)) {
                            JOptionPane.showMessageDialog(null,
                                    "La fecha de salida es posterior a la fecha actual. Se cobrará un extra.");
                            realizarCheckOut(reserva);
                            JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                        } else {
                            realizarCheckOut(reserva);
                            JOptionPane.showMessageDialog(null, "Check-Out realizado con éxito.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se encontró ninguna reserva asociada al DNI ingresado.");
                    }
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Saliendo del Menú de Check-Out.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción no válida. Por favor, seleccione una opción válida.");
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
        // Implementar lógica de tipos de limpieza
    }

    public void limpiezaHabitacion1() {
        // Implementar lógica de limpieza de habitación 1
    }

    public void limpiezaHabitacion() {
        // Implement
}
}