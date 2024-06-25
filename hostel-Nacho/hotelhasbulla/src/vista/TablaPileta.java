
package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controlador.PiletaControlador;
import modelo.Pileta;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.Font;

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
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 909, 452);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Inicializar controlador y obtener la única pileta
        controlador = new PiletaControlador();
        pileta = controlador.getPileta(); // Obtener la única pileta

        // Crear el JLabel para mostrar la selección
        elemento = new JLabel("Pileta:");
        elemento.setFont(new Font("Tahoma", Font.PLAIN, 15));
        elemento.setBounds(507, 24, 365, 39);
        contentPane.add(elemento);
        
        // Crear la tabla y el modelo
        String[] columnNames = {"Capacidad Máxima", "Capacidad Actual", "Llena"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        actualizarTabla();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 452, 427);
        contentPane.add(scrollPane);

        // Botón para ingresar personas
        JButton ingresarButton = new JButton("Ingresar Personas");
        ingresarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pileta.ingresarPersonas();
                actualizarTabla();
                controlador.actualizarCantidadPersonas(pileta); // Actualizar cantidad de personas en la base de datos
            }
        });
        ingresarButton.setBounds(474, 141, 166, 61);
        contentPane.add(ingresarButton);

        // Botón para retirar personas
        JButton retirarButton = new JButton("Retirar Personas");
        retirarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pileta.retirarPersonas();
                actualizarTabla();
                controlador.actualizarCantidadPersonas(pileta); // Actualizar cantidad de personas en la base de datos
            }
        });
        retirarButton.setBounds(717, 141, 166, 61);
        contentPane.add(retirarButton);

        // Botón para salir del programa
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí no es necesario guardar ningún estado, simplemente se cierra la aplicación
                System.exit(0);
            }
        });
        salirButton.setBounds(605, 245, 166, 61);
        contentPane.add(salirButton);

        // Configurar el modelo de selección (solo permite una fila a la vez)
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // No se necesita escuchador de selección en este caso, dado que solo hay una pileta
    }

    // Método para actualizar la tabla con los datos de la pileta
    private void actualizarTabla() {
        // Limpiar el modelo de la tabla
        model.setRowCount(0);

        // Agregar los datos de la pileta al modelo
        model.addRow(new Object[]{50, pileta.getCantidadPersonas(), pileta.getCantidadPersonas() >= 50});

        // Actualizar el JLabel con la información de la pileta
        elemento.setText("Pileta: Capacidad Máxima=50, Capacidad Actual=" + pileta.getCantidadPersonas());
    }
}
