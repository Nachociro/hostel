package vista;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.PiletaControlador;
import modelo.Pileta;
import vista.pantallaInicio;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TablaPileta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private PiletaControlador controlador;
    private JLabel elemento;
    private Pileta seleccionada;
    private JButton ingresarButton;
    private JButton retirarButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaPileta frame = new TablaPileta();
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
    public TablaPileta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 64, 64));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Inicializar controlador y pileta seleccionada
        controlador = new PiletaControlador();
        seleccionada = controlador.getPileta(); // Obtener la única pileta

        // Crear la tabla y el modelo
        String[] columnNames = { "Capacidad Máxima", "Capacidad Actual", "Llena" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 10, 900, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(10, 210, 880, 14);
        contentPane.add(elemento);

        ingresarButton = new JButton("Ingresar Personas");
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionada.ingresarPersonas();
                actualizarTabla();
            }
        });
        ingresarButton.setBounds(10, 303, 165, 35);
        contentPane.add(ingresarButton);

        retirarButton = new JButton("Retirar Personas");
        retirarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionada.retirarPersonas();
                actualizarTabla();
            }
        });
        retirarButton.setBounds(10, 348, 165, 35);
        contentPane.add(retirarButton);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual
            }
        });
        btnSalir.setBounds(683, 348, 165, 35);
        contentPane.add(btnSalir);

        JButton btnVolverInicio = new JButton("Volver al inicio");
        btnVolverInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pantallaInicio inicio = new pantallaInicio();
                inicio.setVisible(true); // Abre la pantalla de inicio
                dispose(); // Cierra la ventana actual
            }
        });
        btnVolverInicio.setBounds(683, 303, 165, 35);
        contentPane.add(btnVolverInicio);

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
                        int capacidadMaxima = 50; // Capacidad máxima siempre es 50
                        int capacidadActual = (int) table.getValueAt(selectedRow, 1);
                        boolean llena = (boolean) table.getValueAt(selectedRow, 2);
                        elemento.setText("Seleccionado: Capacidad Máxima=" + capacidadMaxima + ", Capacidad Actual="
                                + capacidadActual);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Agregar los datos al modelo
        model.addRow(new Object[] { 50, // Capacidad máxima siempre es 50
                seleccionada.getCantidadPersonas(), seleccionada.getCantidadPersonas() >= 50 });
    }
}
