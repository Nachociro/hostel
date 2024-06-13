package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.HabitacionControlador;
import modelo.Habitacion;

import java.sql.Connection;
import controlador.DatabaseConnection;
import controlador.UsuarioControlador;

public class tablaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private HabitacionControlador controlador;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaHabitaciones frame = new tablaHabitaciones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public tablaHabitaciones() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        controlador = new HabitacionControlador();

        model = new DefaultTableModel(new String[]{"Número", "Tipo", "Descripción", "Precio", "Disponibilidad", "Limpieza"}, 0);
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
        
                JButton btnVolverInicio = new JButton("Volver al inicio");
                panel.add(btnVolverInicio);
                btnVolverInicio.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        pantallaInicio inicio = new pantallaInicio();
                        inicio.setVisible(true); // Abre la pantalla de inicio
                        dispose(); // Cierra la ventana actual
                    }
                });
                btnVolverInicio.setBounds(683, 303, 165, 35);
        JButton btnSalir = new JButton("Salir");
        panel.add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        btnSalir.setBounds(683, 348, 165, 35);
        
    }
    

    private void actualizarTabla(List<Habitacion> habitaciones) {
        model.setRowCount(0); 
        for (Habitacion habitacion : habitaciones) {
            model.addRow(new Object[]{
                habitacion.getNumero_habitacion(),
                habitacion.getTipo(),
                habitacion.getDescripcion(),
                habitacion.getPrecio(),
                habitacion.isDisponibilidad(),
                habitacion.isLimpieza()
            });
        }
    }
}
