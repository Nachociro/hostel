package vista;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Pileta;
import controlador.PiletaControlador;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

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
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador y pileta seleccionada
        controlador = new PiletaControlador();
        seleccionada = controlador.getPileta(); // Obtener la única pileta

        // Crear la tabla y el modelo
        String[] columnNames = {"Capacidad Máxima", "Capacidad Actual", "Llena"};
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

        ingresarButton = new JButton("Ingresar Personas");
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionada.ingresarPersonas();
                actualizarTabla();
            }
        });
        ingresarButton.setBounds(253, 280, 187, 58);
        contentPane.add(ingresarButton);

        retirarButton = new JButton("Retirar Personas");
        retirarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                seleccionada.retirarPersonas();
                actualizarTabla();
            }
        });
        retirarButton.setBounds(453, 280, 187, 58);
        contentPane.add(retirarButton);

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
                        int capacidadMaxima = 50; // Capacidad máxima siempre es 50
                        int capacidadActual = (int) table.getValueAt(selectedRow, 1);
                        boolean llena = (boolean) table.getValueAt(selectedRow, 2);
                        elemento.setText("Seleccionado: Capacidad Máxima=" + capacidadMaxima + ", Capacidad Actual=" + capacidadActual);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Agregar los datos al modelo
        model.addRow(
            new Object[] {
                50, // Capacidad máxima siempre es 50
                seleccionada.getCantidadPersonas(),
                seleccionada.getCantidadPersonas() >= 50
            }
        );
    }
}
