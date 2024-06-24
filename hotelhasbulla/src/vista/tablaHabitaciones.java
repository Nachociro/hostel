package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import controlador.HabitacionControlador;
import modelo.Habitacion;
import modelo.SingletonHabitaciones;

public class tablaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private HabitacionControlador controlador;
    private SingletonHabitaciones singleton;
    private Date fechaEntrada;
    private Date fechaSalida;

    public tablaHabitaciones(Date fechaEntrada, Date fechaSalida, SingletonHabitaciones singleton) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.singleton = singleton;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        controlador = new HabitacionControlador();

        model = new DefaultTableModel(new String[]{"Número", "Tipo", "Descripción", "Precio", "Disponibilidad", "Limpieza", "Acción"}, 0);
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (column == 6) {
                    JButton btnSeleccionar = new JButton("Seleccionar");
                    btnSeleccionar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Habitacion habitacion = controlador.getAllHabitaciones().get(row);
                            singleton.setSeleccionada(habitacion);
                            dispose();
                        }
                    });
                    return btnSeleccionar;
                }
                return component;
            }
        };

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

        // Actualizar tabla con habitaciones disponibles para las fechas seleccionadas
        actualizarTabla(controlador.getHabitacionesDisponibles());
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
                habitacion.isLimpieza(),
                "Seleccionar"
            });
        }
        ajustarColumnas();
    }

    private void ajustarColumnas() {
        TableColumn columnaAccion = table.getColumnModel().getColumn(6);
        columnaAccion.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JButton((String) value);
            }
        });
    }

    public String obtenerTipoHabitacionSeleccionada() {
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
            return (String) table.getValueAt(filaSeleccionada, 1);
        }
        return null;
    }
}
