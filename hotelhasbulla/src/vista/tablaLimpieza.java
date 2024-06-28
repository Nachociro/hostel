package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controlador.HabitacionControlador;
import controlador.LimpiezaControlador;
import modelo.Habitacion;
import usuario.Limpieza;

public class tablaLimpieza extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private LimpiezaControlador limpiezaControlador;
    private HabitacionControlador habitacionControlador;
    private JLabel elemento;
    private Limpieza limpiezaSeleccionada;
    private JButton btnEditar;
    private JButton btnVerHistorial;
    private JButton btnVerHabitacionesSucias;
    private JButton btnRealizarLimpieza;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaLimpieza frame = new tablaLimpieza();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public tablaLimpieza() {
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        limpiezaControlador = new LimpiezaControlador();
        habitacionControlador = new HabitacionControlador();
        limpiezaSeleccionada = new Limpieza(0, 0, LocalDate.now(), "00:00");

        String[] columnNames = {"ID Limpieza", "Número Habitación", "Fecha y Hora"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarLimpieza();
            }
        });
        btnEliminar.setBounds(53, 280, 187, 58);
        contentPane.add(btnEliminar);

        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirEditar();
            }
        });
        btnEditar.setBounds(367, 280, 166, 58);
        contentPane.add(btnEditar);

        btnVerHistorial = new JButton("Ver Historial de Limpiezas");
        btnVerHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        btnVerHistorial.setBounds(53, 350, 187, 58);
        contentPane.add(btnVerHistorial);

        btnVerHabitacionesSucias = new JButton("Ver Habitaciones Sucias");
        btnVerHabitacionesSucias.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verHabitacionesSucias();
            }
        });
        btnVerHabitacionesSucias.setBounds(367, 350, 166, 58);
        contentPane.add(btnVerHabitacionesSucias);

        btnRealizarLimpieza = new JButton("Realizar Limpieza");
        btnRealizarLimpieza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (limpiezaSeleccionada != null) {
                    LocalDate inicio = LocalDate.now();
                    LocalTime horaInicio = LocalTime.now();
                    limpiezaSeleccionada.realizarLimpieza();
                    LocalDate fin = LocalDate.now();
                    LocalTime horaFin = LocalTime.now();
                    int minutosDuracion = java.time.Duration.between(horaInicio, horaFin).toMinutesPart();

                    JOptionPane.showMessageDialog(null, "Limpieza realizada en " + minutosDuracion + " minutos");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una limpieza para realizar");
                }
            }
        });
        btnRealizarLimpieza.setBounds(53, 420, 187, 58);
        contentPane.add(btnRealizarLimpieza);

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarSeleccion();
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        List<Limpieza> limpiezas = limpiezaControlador.getAllLimpiezas();

        for (Limpieza limpieza : limpiezas) {
            model.addRow(new Object[]{
                limpieza.getIdLimpieza(),
                limpieza.getNumeroHabitacion(),
                limpieza.getFecha() + " " + limpieza.getHora() // Combinar fecha y hora en una sola columna
            });
        }

        System.out.println("Datos cargados: " + limpiezas.size());
    }

    private void verHabitacionesSucias() {
        model.setRowCount(0);

        String[] columnNames = {"Número Habitación", "Tipo", "Descripción", "Precio", "Disponibilidad", "Limpieza", "Tiempo de Limpieza"};
        model.setColumnIdentifiers(columnNames);

        List<Habitacion> habitacionesSucias = habitacionControlador.getHabitacionesNoLimpias();

        for (Habitacion habitacion : habitacionesSucias) {
            Limpieza limpieza = new Limpieza(0, habitacion.getNumero_habitacion(), LocalDate.now(), "00:00");
            int tiempoLimpieza = limpieza.duracionLimpieza();
            model.addRow(new Object[]{
                habitacion.getNumero_habitacion(),
                habitacion.getTipo(),
                habitacion.getDescripcion(),
                habitacion.getPrecio(),
                habitacion.isDisponibilidad() ? "Disponible" : "Ocupado",
                habitacion.isLimpieza() ? "Limpia" : "Sucia",
                tiempoLimpieza + " mins"
            });
        }

        System.out.println("Habitaciones sucias cargadas: " + habitacionesSucias.size());
    }

    private void actualizarSeleccion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int idLimpieza = (int) table.getValueAt(selectedRow, 0);
            int numeroHabitacion = (int) table.getValueAt(selectedRow, 1);
            String fechaHora = (String) table.getValueAt(selectedRow, 2);
            String[] partes = fechaHora.split(" ");
            LocalDate fecha = LocalDate.parse(partes[0]);
            String hora = partes[1];
            elemento.setText("Seleccionado: ID Limpieza=" + idLimpieza + ", Número Habitación=" + numeroHabitacion + ", Fecha y Hora=" + fechaHora);
            limpiezaSeleccionada.setIdLimpieza(idLimpieza);
            limpiezaSeleccionada.setNumeroHabitacion(numeroHabitacion);
            limpiezaSeleccionada.setFecha(fecha);
            limpiezaSeleccionada.setHora(hora);
        }
    }

    private void eliminarLimpieza() {
        if (limpiezaSeleccionada.getIdLimpieza() != 0) {
            limpiezaControlador.deleteLimpieza(limpiezaSeleccionada.getIdLimpieza());
            JOptionPane.showMessageDialog(null, "Limpieza eliminada correctamente");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una limpieza para eliminar");
        }
    }

    private void abrirEditar() {
        if (limpiezaSeleccionada.getIdLimpieza() != 0) {
            Editar editar = new Editar(limpiezaSeleccionada, limpiezaControlador);
            editar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una limpieza para editar");
        }
    }
}