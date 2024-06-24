package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controlador.LimpiezaControlador;
import usuario.Limpieza;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;

public class tablaLimpieza extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private LimpiezaControlador controlador; 
    private JLabel elemento;
    private Limpieza limpiezaSeleccionada;
    private JButton btnEditar;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaLimpieza frame = new tablaLimpieza();
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
    public tablaLimpieza() {
        this.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Inicializar controlador y limpieza seleccionada
        controlador = new LimpiezaControlador();
        limpiezaSeleccionada = new Limpieza(0, 0, LocalDate.now(), "00:00");

        // Crear la tabla y el modelo
        String[] columnNames = {"ID Limpieza", "Número Habitación", "Fecha", "Hora"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        contentPane.setLayout(null);

        // Crear el JScrollPane y agregar la tabla
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 19, 911, 190);
        contentPane.add(scrollPane);

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Seleccionado:");
        elemento.setBounds(5, 5, 911, 14);
        contentPane.add(elemento);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarLimpieza();
            }
        });
        btnEliminar.setBounds(53, 280, 187, 58);
        contentPane.add(btnEliminar);

        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirEditar();
            }
        });
        btnEditar.setBounds(367, 280, 166, 58);
        contentPane.add(btnEditar);

        // Configurar el modelo de selección
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Agregar un escuchador de selección
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    actualizarSeleccion();
                }
            }
        });
    }

    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Obtener la lista actualizada de limpiezas
        List<Limpieza> limpiezas = controlador.getAllLimpiezas();

        // Agregar los datos al modelo
        for (Limpieza limpieza : limpiezas) {
            model.addRow(new Object[]{
                limpieza.getIdLimpieza(),
                limpieza.getNumeroHabitacion(),
                limpieza.getFecha(),
                limpieza.getHora()
            });
        }
    }

    private void actualizarSeleccion() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int idLimpieza = (int) table.getValueAt(selectedRow, 0);
            int numeroHabitacion = (int) table.getValueAt(selectedRow, 1);
            LocalDate fecha = LocalDate.parse(table.getValueAt(selectedRow, 2).toString());
            String hora = (String) table.getValueAt(selectedRow, 3);
            elemento.setText("Seleccionado: ID Limpieza=" + idLimpieza + ", Número Habitación=" + numeroHabitacion + ", Fecha=" + fecha + ", Hora=" + hora);
            limpiezaSeleccionada.setIdLimpieza(idLimpieza);
            limpiezaSeleccionada.setNumeroHabitacion(numeroHabitacion);
            limpiezaSeleccionada.setFecha(fecha);
            limpiezaSeleccionada.setHora(hora);
        }
    }

    private void eliminarLimpieza() {
        if (limpiezaSeleccionada.getIdLimpieza() != 0) {
            controlador.deleteLimpieza(limpiezaSeleccionada.getIdLimpieza());
            JOptionPane.showMessageDialog(null, "Limpieza eliminada correctamente");
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una limpieza para eliminar");
        }
    }

    private void abrirEditar() {
        if (limpiezaSeleccionada.getIdLimpieza() != 0) {
            Editar editar = new Editar(limpiezaSeleccionada, controlador);
            editar.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una limpieza para editar");
        }
    }
}
