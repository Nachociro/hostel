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

import modelo.CanchaTenis;
import controlador.CanchaTenisControlador;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class tablaCanchaTenis extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private CanchaTenisControlador controlador;
    private JLabel elemento;
    private JButton reservarButton;
    private JButton cancelarReservaButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaCanchaTenis frame = new tablaCanchaTenis();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public tablaCanchaTenis() {
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador
        controlador = new CanchaTenisControlador();

        // Crear la tabla y el modelo
        String[] columnNames = {"ID", "Piso", "Precio", "Disponible"};
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

        reservarButton = new JButton("Reservar Cancha");
        reservarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    String numPersonasStr = JOptionPane.showInputDialog("Ingrese el número de personas:");
                    int numPersonas = Integer.parseInt(numPersonasStr);
                    controlador.reservarCancha(id, numPersonas);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una cancha.");
                }
            }
        });
        reservarButton.setBounds(253, 280, 187, 58);
        contentPane.add(reservarButton);

        cancelarReservaButton = new JButton("Cancelar Reserva");
        cancelarReservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    controlador.cancelarReserva(id);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una cancha.");
                }
            }
        });
        cancelarReservaButton.setBounds(450, 280, 187, 58);
        contentPane.add(cancelarReservaButton);

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
                        int id = (int) table.getValueAt(selectedRow, 0);
                        String piso = (String) table.getValueAt(selectedRow, 1);
                        double precio = (double) table.getValueAt(selectedRow, 2);
                        boolean disponible = (boolean) table.getValueAt(selectedRow, 3);
                        elemento.setText("Seleccionado: ID=" + id + ", Piso=" + piso);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de canchas de tenis
        List<CanchaTenis> canchas = controlador.getAllCanchas();

        // Agregar los datos al modelo
        for (CanchaTenis cancha : canchas) {
            model.addRow(
                new Object[] {
                    cancha.getId_Tenis(),
                    cancha.getPiso(),
                    cancha.getPrecio(),
                    cancha.isDisponible()
                }
            );
        }
    }
}
