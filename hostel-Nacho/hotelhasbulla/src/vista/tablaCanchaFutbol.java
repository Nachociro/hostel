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

import modelo.CanchaFutbol;
import controlador.CanchaFutbolControlador;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class tablaCanchaFutbol extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private CanchaFutbolControlador controlador;
    private JLabel elemento;
    private JButton reservarButton;
    private JButton cancelarButton;
    private JButton salirButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaCanchaFutbol frame = new tablaCanchaFutbol();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public tablaCanchaFutbol() {
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        controlador = new CanchaFutbolControlador();

        String[] columnNames = {"ID", "Tamaño", "Precio", "Disponible"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 900, 190);
        contentPane.add(scrollPane);

        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 900, 14);
        contentPane.add(elemento);

        reservarButton = new JButton("Reservar Cancha");
        reservarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    String numPersonasStr = JOptionPane.showInputDialog("Ingrese el número de personas que jugarán:");
                    int numPersonas = Integer.parseInt(numPersonasStr);
                    controlador.reservarCancha(id, numPersonas);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una cancha.");
                }
            }
        });
        reservarButton.setBounds(216, 248, 187, 58);
        contentPane.add(reservarButton);

        cancelarButton = new JButton("Cancelar Reserva");
        cancelarButton.addActionListener(new ActionListener() {
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
        cancelarButton.setBounds(482, 248, 187, 58);
        contentPane.add(cancelarButton);

        salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        salirButton.setBounds(350, 344, 187, 58);
        contentPane.add(salirButton);


        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int id = (int) table.getValueAt(selectedRow, 0);
                        int tamaño = (int) table.getValueAt(selectedRow, 1);
                        double precio = (double) table.getValueAt(selectedRow, 2);
                        boolean disponible = (boolean) table.getValueAt(selectedRow, 3);
                        elemento.setText("Seleccionado: ID=" + id + ", Tamaño=" + tamaño);
                    }
                }
            }
        });
    }

    private void actualizarTabla() {
        model.setRowCount(0);

        List<CanchaFutbol> canchas = controlador.getAllCanchas();

        for (CanchaFutbol cancha : canchas) {
            model.addRow(
                new Object[] {
                    cancha.getId_futbol(),
                    cancha.getTamano(),
                    cancha.getPrecio(),
                    cancha.isDisponible()
                }
            );
        }
    }
}