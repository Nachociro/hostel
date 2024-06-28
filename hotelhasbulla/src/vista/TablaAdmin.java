package vista;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.ReservaControlador;
import controlador.HabitacionControlador;
import modelo.Reserva;
import modelo.SingletonHabitaciones;
import modelo.Habitacion;

public class TablaAdmin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel model;
    private ReservaControlador reservaControlador;
    private HabitacionControlador habitacionControlador;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaAdmin frame = new TablaAdmin();
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
    public TablaAdmin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        reservaControlador = new ReservaControlador();
        habitacionControlador = new HabitacionControlador();

        String[] columnNames = {"ID", "Cliente", "Fecha Inicio", "Fecha Fin", "Número Habitación"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 660, 300);
        contentPane.add(scrollPane);

        JButton btnVerReservas = new JButton("Ver Reservas");
        btnVerReservas.setBounds(10, 320, 150, 30);
        btnVerReservas.addActionListener(e -> {
            mostrarReservas();
        });
        contentPane.add(btnVerReservas);

        JButton btnVerHabitaciones = new JButton("Ver Habitaciones");
        btnVerHabitaciones.setBounds(170, 320, 150, 30);
        btnVerHabitaciones.addActionListener(e -> {
            mostrarHabitaciones();
        });
        contentPane.add(btnVerHabitaciones);

        JButton btnVerLimpieza = new JButton("Ver Limpieza");
        btnVerLimpieza.setBounds(330, 320, 150, 30);
        btnVerLimpieza.addActionListener(e -> {
            mostrarLimpieza();
        });
        contentPane.add(btnVerLimpieza);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(490, 320, 150, 30);
        btnCerrar.addActionListener(e -> {
            dispose();
        });
        contentPane.add(btnCerrar);
    }

    private void mostrarReservas() {
        TablaReservas frame = new TablaReservas();
        frame.setVisible(true);
        dispose(); // Cierra la ventana actual
    }

    private void mostrarHabitaciones() {
    	SingletonHabitaciones singleton = new SingletonHabitaciones();
        tablaHabitaciones frame = new tablaHabitaciones(singleton);
        frame.setVisible(true);
        dispose(); // Cierra la ventana actual
    }

    private void mostrarLimpieza() {
        tablaLimpieza frame = new tablaLimpieza();
        frame.setVisible(true);
        dispose(); // Cierra la ventana actual
    }
}
