package vista;

import javax.swing.*;
import controlador.ClienteControlador;
import modelo.Cliente;

public class DatosCliente extends JFrame {

    private JLabel lblDNI, lblNombre, lblApellido, lblCorreo, lblTelefono;
    private JTextField txtDNI, txtNombre, txtApellido, txtCorreo, txtTelefono;
    private JButton btnGuardar;

    private String nombreCliente; 

    public DatosCliente() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Datos del Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        lblDNI = new JLabel("DNI:");
        lblDNI.setBounds(30, 30, 80, 25);
        panel.add(lblDNI);

        txtDNI = new JTextField();
        txtDNI.setBounds(120, 30, 200, 25);
        panel.add(txtDNI);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 70, 80, 25);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 70, 200, 25);
        panel.add(txtNombre);

        lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(30, 110, 80, 25);
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(120, 110, 200, 25);
        panel.add(txtApellido);

        lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 150, 80, 25);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 150, 200, 25);
        panel.add(txtCorreo);

        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(30, 190, 80, 25);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 190, 200, 25);
        panel.add(txtTelefono);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(120, 230, 100, 30);
        btnGuardar.addActionListener(e -> guardarCliente());
        panel.add(btnGuardar);

        getContentPane().add(panel);
    }

    private void guardarCliente() {
        try {
            if (!isDatosCompletos()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar todos los datos del cliente.");
                return;
            }

            int dni = Integer.parseInt(txtDNI.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();
            String resena = "";

            Cliente cliente = new Cliente(dni, nombre, apellido, correo, telefono, resena);
            ClienteControlador clienteControlador = new ClienteControlador();
            clienteControlador.addCliente(cliente);

            nombreCliente = nombre;

            JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");

            limpiarCampos();

            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void limpiarCampos() {
        txtDNI.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatosCliente frame = new DatosCliente();
            frame.setVisible(true);
        });
    }

    public boolean isDatosCompletos() {
        return !txtDNI.getText().trim().isEmpty() &&
               !txtNombre.getText().trim().isEmpty() &&
               !txtApellido.getText().trim().isEmpty() &&
               !txtCorreo.getText().trim().isEmpty() &&
               !txtTelefono.getText().trim().isEmpty();
    }



    public String getIdHuesped() {
        return txtDNI.getText();
    }
}
