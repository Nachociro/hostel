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

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private ReservaControlador controlador;
    private JTextField dniTextField;
    private JButton btnConfirmarReserva; // Botón para confirmar la reserva
    private SeleccionarReservaListener seleccionarReservaListener;
    private int filtroDNI = -1; // Variable para almacenar el DNI usado como filtro

    public interface SeleccionarReservaListener {
        void reservaSeleccionada(Reserva reserva);
    }

    public void setSeleccionarReservaListener(SeleccionarReservaListener listener) {
        this.seleccionarReservaListener = listener;
    }

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

        // Crear modelo de tabla con las columnas deseadas
        model = new DefaultTableModel(new Object[][] {}, new String[] { "ID Reserva", "Fecha Entrada", "Fecha Salida",
                "ID Hu\u00E9sped", "N\u00FAmero Habitaci\u00F3n" });
        table = new JTable(model);

        // Agregar tabla a un JScrollPane y añadirlo al panel principal
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Panel para los botones y el campo de texto para buscar por DNI
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        // Botón para ver todas las reservas
        JButton btnAllReservas = new JButton("Ver Todas las Reservas");
        btnAllReservas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filtroDNI = -1; // Reiniciar el filtro de DNI
                dniTextField.setText(""); // Limpiar el campo de texto
                actualizarTabla(controlador.getAllReservas(), false);
            }
        });
        panel.add(btnAllReservas);

        // Campo de texto para ingresar el DNI a buscar
        dniTextField = new JTextField();
        dniTextField.setColumns(10);
        panel.add(dniTextField);

        // Botón para buscar por DNI
        JButton btnBuscarPorDni = new JButton("Buscar por DNI");
        btnBuscarPorDni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dniText = dniTextField.getText();
                if (!dniText.isEmpty()) {
                    try {
                        int dni = Integer.parseInt(dniText);
                        filtroDNI = dni; // Establecer el filtro de DNI
                        actualizarTabla(controlador.buscarReservaPorDNI(dni), true);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "DNI inv\u00E1lido. Por favor, ingrese un n\u00FAmero v\u00E1lido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un DNI.");
                }
            }
        });
        panel.add(btnBuscarPorDni);

        // Botón para confirmar la reserva
        btnConfirmarReserva = new JButton("Confirmar Reserva");
        btnConfirmarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnConfirmarReservaActionPerformed(e);
            }
        });
        panel.add(btnConfirmarReserva);

        // Mostrar todas las reservas al inicio
        actualizarTabla(controlador.getAllReservas(), false);
    }

    // Método para actualizar la tabla con una lista de reservas
    private void actualizarTabla(List<Reserva> reservas, boolean filtrarPorDNI) {
        model.setRowCount(0); // Limpiar modelo actual
        for (Reserva reserva : reservas) {
            // Filtrar por DNI si es necesario
            if (!filtrarPorDNI || reserva.getDniHuesped() == filtroDNI) {
                model.addRow(new Object[] { reserva.getId_reserva(), reserva.getFecha_entrada(),
                        reserva.getFecha_salida(), reserva.getDniHuesped(), reserva.getNumero_habitacion() });
            }
        }
    }

    // Método para manejar la acción del botón Confirmar Reserva
    private void btnConfirmarReservaActionPerformed(ActionEvent e) {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idReserva = (int) model.getValueAt(filaSeleccionada, 0); // ID de la reserva
            Reserva reservaSeleccionada = controlador.getReservaById(idReserva);
            if (reservaSeleccionada != null && seleccionarReservaListener != null) {
                seleccionarReservaListener.reservaSeleccionada(reservaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva.");
        }
    }
}
