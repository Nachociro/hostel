package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.PiletaControlador;
import modelo.Pileta;

public class TablaPileta extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private PiletaControlador controlador;
    private JLabel elemento;
    private Pileta pileta;

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

    public TablaPileta() {
        setTitle("Gestión de Pileta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Font buttonFont = new Font("Mandalore", Font.PLAIN, 20);

        controlador = new PiletaControlador();
        pileta = controlador.getPileta();

        elemento = new JLabel("Pileta:");
        elemento.setBounds(5, 5, 900, 39);
        elemento.setFont(buttonFont);
        elemento.setForeground(Color.RED);
        contentPane.add(elemento);

        String[] columnNames = { "Capacidad Máxima", "Capacidad Actual", "Llena" };
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 50, 909, 50);
        contentPane.add(scrollPane);

        JButton ingresarButton = new JButton("Ingresar Personas");
        ingresarButton.setFont(buttonFont);
        ingresarButton.setBackground(Color.RED);
        ingresarButton.setForeground(Color.BLACK);
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pileta.ingresarPersonas();
                actualizarTabla();
                controlador.actualizarCantidadPersonas(pileta);
            }
        });
        ingresarButton.setBounds(158, 183, 300, 80);
        contentPane.add(ingresarButton);

        JButton retirarButton = new JButton("Retirar Personas");
        retirarButton.setFont(buttonFont);
        retirarButton.setBackground(Color.RED);
        retirarButton.setForeground(Color.BLACK);
        retirarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pileta.retirarPersonas();
                actualizarTabla();
                controlador.actualizarCantidadPersonas(pileta);
            }
        });
        retirarButton.setBounds(509, 183, 300, 80);
        contentPane.add(retirarButton);

        JButton salirButton = new JButton("Salir");
        salirButton.setFont(buttonFont);
        salirButton.setBackground(Color.RED);
        salirButton.setForeground(Color.BLACK);
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        salirButton.setBounds(336, 302, 300, 80);
        contentPane.add(salirButton);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void actualizarTabla() {
        model.setRowCount(0);
        model.addRow(new Object[] { 50, pileta.getCantidadPersonas(), pileta.getCantidadPersonas() >= 50 });
        elemento.setText("Pileta: Capacidad Máxima=50, Capacidad Actual=" + pileta.getCantidadPersonas());
    }
}