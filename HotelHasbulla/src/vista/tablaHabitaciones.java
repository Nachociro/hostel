package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.HabitacionControlador;
import modelo.Habitacion;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class tablaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private HabitacionControlador controlador;
    private JLabel elemento;
    private Habitacion seleccionada;
    private JButton eliminarButton;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public tablaHabitaciones() {
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador y habitación seleccionada
        controlador = new HabitacionControlador();
        seleccionada = new Habitacion(0, "", "", 0.0, false, false);

        // Crear la tabla y el modelo
        String[] columnNames = {"Número Habitación", "Tipo", "Descripción", "Precio", "Disponibilidad", "Limpieza"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 900, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 900, 14);
        contentPane.add(elemento);

        eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seleccionada.getNumero_habitacion() != 0) {
                    controlador.deleteHabitacion(seleccionada.getNumero_habitacion());
                    JOptionPane.showMessageDialog(null, "Habitación eliminada");
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una habitación");
                }
            }
        });
        eliminarButton.setBounds(53, 280, 187, 58);
        contentPane.add(eliminarButton);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(15, 220, 101, 22);
        contentPane.add(menuBar);

        // Configurar el modelo de selección
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un escuchador de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int numero_habitacion = (int) table.getValueAt(selectedRow, 0);
                        String tipo = (String) table.getValueAt(selectedRow, 1);
                        String descripcion = (String) table.getValueAt(selectedRow, 2);
                        double precio = (double) table.getValueAt(selectedRow, 3);
                        boolean disponibilidad = (boolean) table.getValueAt(selectedRow, 4);
                        boolean limpieza = (boolean) table.getValueAt(selectedRow, 5);
                        elemento.setText("Seleccionado: Número Habitación=" + numero_habitacion + ", Tipo=" + tipo);
                        seleccionada.setNumero_habitacion(numero_habitacion);
                        seleccionada.setTipo(tipo);
                        seleccionada.setDescripcion(descripcion);
                        seleccionada.setPrecio(precio);
                        seleccionada.setDisponibilidad(disponibilidad);
                        seleccionada.setLimpieza(limpieza);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de habitaciones
        List<Habitacion> habitaciones = controlador.getAllHabitaciones();

        // Agregar los datos al modelo
        for (Habitacion habitacion : habitaciones) {
            model.addRow(
                new Object[] {
                    habitacion.getNumero_habitacion(),
                    habitacion.getTipo(),
                    habitacion.getDescripcion(),
                    habitacion.getPrecio(),
                    habitacion.isDisponibilidad(),
                    habitacion.isLimpieza()
                }
            );
        }
    }
}
