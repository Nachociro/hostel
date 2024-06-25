package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.HabitacionControlador;
import modelo.Habitacion;
import modelo.SingletonHabitaciones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class tablaHabitaciones extends JFrame {
    private SingletonHabitaciones singleton;
    private HabitacionControlador habitacionControlador;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public tablaHabitaciones(SingletonHabitaciones singleton) {
        this.singleton = singleton;
        habitacionControlador = new HabitacionControlador();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Seleccione una Habitación");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Número de Habitación");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Disponibilidad");

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        cargarHabitaciones();

        JButton btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int filaSeleccionada = tabla.getSelectedRow();
            	if (filaSeleccionada != -1) {
            	    Object valor = modeloTabla.getValueAt(filaSeleccionada, 0);
            	    if (valor != null && !valor.toString().isEmpty()) {
            	        try {
            	            int numeroHabitacion = Integer.parseInt(valor.toString());
            	            Habitacion habitacionSeleccionada = habitacionControlador.getHabitacionByNumero(numeroHabitacion);

            	            // Asignar la habitación seleccionada al singleton
            	            singleton.setSeleccionada(habitacionSeleccionada);

            	            // Cerrar la ventana
            	            dispose();
            	        } catch (NumberFormatException ex) {
            	            JOptionPane.showMessageDialog(null, "Error: El número de habitación seleccionado no es válido.");
            	        }
            	    } else {
            	        JOptionPane.showMessageDialog(null, "Debe seleccionar una habitación válida.");
            	    }
            	} else {
            	    JOptionPane.showMessageDialog(null, "Debe seleccionar una habitación.");
            	}
            }
        });
        contentPane.add(btnConfirmar, BorderLayout.SOUTH);
    }

    private void cargarHabitaciones() {
        List<Habitacion> habitaciones = singleton.getHabitacionesDisponibles();
        for (Habitacion habitacion : habitaciones) {
            modeloTabla.addRow(new Object[]{
                habitacion.getNumero_habitacion(),
                habitacion.getTipo(),
                habitacion.getPrecio(),
                habitacion.isDisponibilidad() ? "Disponible" : "Ocupado"
            });
        }
    }
}
