package modelo;

import javax.swing.JOptionPane;
import vista.calendario;
import vista.tablaHabitaciones;
import vista.DatosCliente;
import vista.TablaReservas;
import vista.TablaRecepcionista;
import controlador.ClienteControlador;
import controlador.HabitacionControlador;
import controlador.ReservaControlador;
import java.sql.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;

public class SingletonHabitaciones {
    private static SingletonHabitaciones instance;
    private HabitacionControlador habitacionControlador;
    private ReservaControlador reservaControlador;
    private Habitacion habitacionSeleccionada;
    private java.util.Date fechaEntrada;
    private java.util.Date fechaSalida;
    private boolean fechasSeleccionadas;
    private DatosCliente datosCliente;
    private Reserva reservaSeleccionada;
    private ClienteControlador clienteControlador; // Agregamos el controlador de clientes

    private SingletonHabitaciones() {
        habitacionControlador = new HabitacionControlador();
        reservaControlador = new ReservaControlador();
        clienteControlador = new ClienteControlador(); // Inicializamos el controlador de clientes
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

            // Agregar la reserva
            reservaControlador.addReserva(reserva);

            // Actualizar disponibilidad de la habitación en la base de datos
            habitacionSeleccionada.setDisponibilidad(false);
            habitacionControlador.updateHabitacion(habitacionSeleccionada); // Actualizar en la base de datos

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
        TablaReservas tablaReservas = new TablaReservas();
        tablaReservas.setVisible(true);
        
        tablaReservas.setSeleccionarReservaListener(new TablaReservas.SeleccionarReservaListener() {
            @Override
            public void reservaSeleccionada(Reserva reserva) {
                int numeroHabitacionReserva = reserva.getNumero_habitacion();
                
                Habitacion habitacion = habitacionControlador.getHabitacionByNumero(numeroHabitacionReserva);
                
                if (habitacion != null) {
                    LocalDate fechaActual = LocalDate.now();
                    LocalDate fechaEntradaReserva = ((Date) reserva.getFecha_entrada()).toLocalDate();
                    
                    // Verificar si la fecha de entrada de la reserva coincide con la fecha actual
                    if (fechaActual.isEqual(fechaEntradaReserva)) {
                        habitacion.setDisponibilidad(false);
                        habitacionControlador.updateHabitacion(habitacion);
                                              
                        JOptionPane.showMessageDialog(null,
                                "Check-In realizado correctamente para habitación número: " + habitacion.getNumero_habitacion());
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se puede hacer el Check-In en esta fecha. La fecha de entrada no coincide con la fecha actual.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se encontró la habitación correspondiente al número indicado en la reserva.");
                }
                
                tablaReservas.dispose();
                
                // Aquí abres la pantalla de inicio después de cerrar TablaReservas
                abrirPantallaInicio();
            }
        });
    }

    private void abrirPantallaInicio() {
        // Código para abrir la pantalla de inicio, por ejemplo:
        TablaRecepcionista TablaRecepcionista = new TablaRecepcionista();
        TablaRecepcionista.setVisible(true);
    }

    public void hacerCheckOut() {
        TablaReservas tablaReservas = new TablaReservas();
        tablaReservas.setVisible(true);
        
        tablaReservas.setSeleccionarReservaListener(new TablaReservas.SeleccionarReservaListener() {
            @Override
            public void reservaSeleccionada(Reserva reserva) {
                int numeroHabitacionReserva = reserva.getNumero_habitacion();
                
                Habitacion habitacion = habitacionControlador.getHabitacionByNumero(numeroHabitacionReserva);
                
                if (habitacion != null) {
                    LocalDate fechaActual = LocalDate.now();
                    LocalDate fechaSalidaReserva = ((Date) reserva.getFecha_salida()).toLocalDate();
                    
                    // Verificar si la fecha de salida de la reserva es posterior a la fecha actual
                    if (fechaActual.isAfter(fechaSalidaReserva)) {
                        JOptionPane.showMessageDialog(null,
                                "La fecha de salida ya ha pasado. Se procederá con el Check-Out, pero se le cobrará un extra debido a la demora.");
                    } else if (fechaActual.isBefore(fechaSalidaReserva)) {
                        int opcion = JOptionPane.showOptionDialog(null,
                                "La fecha de salida aún no ha llegado. ¿Desea quedarse o irse igualmente?",
                                "Confirmación de Check-Out",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new String[]{"Quedarse", "Irse Igualmente"},
                                "Quedarse");
                        
                        if (opcion == JOptionPane.YES_OPTION) {
                            
                            return;
                        }
                    }

                   
                    realizarCheckOut(reserva, habitacion);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No se encontró la habitación correspondiente al número indicado en la reserva.");
                }
                
                tablaReservas.dispose();             
            }
        });
    }

    private void realizarCheckOut(Reserva reserva, Habitacion habitacion) {
        Cliente cliente = clienteControlador.buscarClientePorDni(reserva.getDniHuesped());

        if (cliente != null) {
            String resena = JOptionPane.showInputDialog("Por favor, deja una reseña sobre tu estancia:");
            cliente.setResena(resena);
         
            habitacion.setDisponibilidad(true);
            habitacionControlador.updateHabitacion(habitacion);
         
            reservaControlador.deleteReserva(reserva.getId_reserva());

            JOptionPane.showMessageDialog(null,
                    "Check-Out realizado con éxito para habitación número: " + habitacion.getNumero_habitacion());
            
            abrirPantallaInicio();
        } else {
            JOptionPane.showMessageDialog(null, "Error al buscar al cliente por DNI.");
        }
    }
 

    public HabitacionControlador getHabitacionControlador() {
        return habitacionControlador;
    }

    public ReservaControlador getReservaControlador() {
        return reservaControlador;
    }
}
