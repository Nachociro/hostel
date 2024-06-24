package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controlador.HabitacionControlador;
import modelo.Habitacion;
import modelo.SingletonHabitaciones;

public class tablaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private HabitacionControlador controlador;
    private SingletonHabitaciones singleton;
    private Date fechaEntrada;
    private Date fechaSalida;

    public tablaHabitaciones(Date fechaEntrada, Date fechaSalida, SingletonHabitaciones singleton) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.singleton = singleton;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        controlador = new HabitacionControlador();

        model = new DefaultTableModel(new String[]{"Número", "Tipo", "Descripción", "Precio", "Disponibilidad", "Limpieza", "Acción"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton btnAllHabitaciones = new JButton("Ver Todas las Habitaciones");
        btnAllHabitaciones.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTabla(controlador.getAllHabitaciones());
            }
        });
        panel.add(btnAllHabitaciones);

        JButton btnDisponibles = new JButton("Ver Habitaciones Disponibles");
        btnDisponibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTabla(controlador.getHabitacionesDisponibles());
            }
        });
        panel.add(btnDisponibles);

        // Actualizar tabla con habitaciones disponibles para las fechas seleccionadas
        actualizarTabla(controlador.getHabitacionesDisponibles());
    }

    private void actualizarTabla(List<Habitacion> habitaciones) {
        model.setRowCount(0);
        for (Habitacion habitacion : habitaciones) {
            JButton btnSeleccionar = new JButton("Seleccionar");
            btnSeleccionar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    singleton.setseleccionada(habitacion);
                    dispose(); // Cerrar la ventana después de seleccionar la habitación
                }
            });
            model.addRow(new Object[]{
                habitacion.getNumero_habitacion(),
                habitacion.getTipo(),
                habitacion.getDescripcion(),
                habitacion.getPrecio(),
                habitacion.isDisponibilidad(),
                habitacion.isLimpieza(),
                btnSeleccionar
            });
        }
    }
}
