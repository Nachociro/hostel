package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controlador.ReservaControlador;
import modelo.Reserva;

public class TablaReservas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ReservaControlador controlador;
    private JTextField dniTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaReservas frame = new TablaReservas();
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
    public TablaReservas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        controlador = new ReservaControlador();

        model = new DefaultTableModel(new String[]{"ID Reserva", "Fecha Entrada", "Fecha Salida", "ID Huésped", "Número Habitación", "Nombre Huésped"}, 0);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton btnAllReservas = new JButton("Ver Todas las Reservas");
        btnAllReservas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTabla(controlador.getAllReservas());
            }
        });
        panel.add(btnAllReservas);

        dniTextField = new JTextField();
        dniTextField.setColumns(10);
        panel.add(dniTextField);

        JButton btnBuscarPorDni = new JButton("Buscar por DNI");
        btnBuscarPorDni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dniText = dniTextField.getText();
                if (!dniText.isEmpty()) {
                    try {
                        int dni = Integer.parseInt(dniText);
                        Reserva reserva = controlador.buscarReservaPorDNI(dni);
                        if (reserva != null) {
                            actualizarTabla(List.of(reserva));
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva para el DNI proporcionado.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "DNI inválido. Por favor, ingrese un número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un DNI.");
                }
            }
        });
        panel.add(btnBuscarPorDni);
    }

    private void actualizarTabla(List<Reserva> reservas) {
        model.setRowCount(0);
        for (Reserva reserva : reservas) {
            model.addRow(new Object[]{
                reserva.getId_reserva(),
                reserva.getFecha_entrada(),
                reserva.getFecha_salida(),
                reserva.getId_huesped(),
                reserva.getNumero_habitacion(),
                reserva.getId_huesped()
            });
        }
    }
}
