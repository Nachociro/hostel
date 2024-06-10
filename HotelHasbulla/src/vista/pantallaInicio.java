package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.SingletonHabitaciones;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pantallaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldUsuario;
    private JPasswordField passwordField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    pantallaInicio frame = new pantallaInicio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public pantallaInicio() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 70, 80, 20);
        contentPane.add(lblUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 120, 80, 20);
        contentPane.add(lblContrasena);

        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(150, 70, 200, 20);
        contentPane.add(textFieldUsuario);
        textFieldUsuario.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 200, 20);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = textFieldUsuario.getText();
                String contrasena = new String(passwordField.getPassword());
                realizarLogin(usuario, contrasena);
            }
        });
        btnLogin.setBounds(150, 170, 100, 30);
        contentPane.add(btnLogin);
    }

    private void realizarLogin(String usuario, String contrasena) {
        if ("recepcionista".equalsIgnoreCase(usuario) && "1234".equals(contrasena)) {
            mostrarMenuRecepcionista();
        } else if ("limpieza".equalsIgnoreCase(usuario) && "1234".equals(contrasena)) {
            mostrarMenuLimpieza();
        } else if ("admin".equalsIgnoreCase(usuario) && "ADMIN".equals(contrasena)) {
            mostrarMenuAdmin();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Intente de nuevo.");
        }
    }

    private void mostrarMenuRecepcionista() {
        int opcion;
        do {
            opcion = Main.mostrarMenuRecepcionista();
            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(this, "Reservando habitación");
                    SingletonHabitaciones.getInstance().reservarHabitacion();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "Hacer check in");
                    SingletonHabitaciones.getInstance().hacerCheckIn();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this, "Hacer check out");
                    SingletonHabitaciones.getInstance().hacerCheckOut();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(this, "Gestionar pileta");
                    break;
                case 5:
                    JOptionPane.showMessageDialog(this, "Gestionar reservas de extras");
                    break;
                case 6:
                    JOptionPane.showMessageDialog(this, "Saliendo del programa");
                    break;
            }
        } while (opcion != 6);
        System.exit(0); 
    }

    private void mostrarMenuLimpieza() {
        int opcion;
        do {
            opcion = Main.mostrarMenuLimpieza();
            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(this, "Empezando la limpieza rápida");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "Empezando la limpieza para nuevo huésped");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this, "Saliendo del programa");
                    break;
            }
        } while (opcion != 3);
        System.exit(0);  
    }

    private void mostrarMenuAdmin() {
        int opcion;
        do {
            opcion = Main.mostrarMenuAdmin();
            switch (opcion) {
                case 1:
                    JOptionPane.showMessageDialog(this, "Gestionando personal de limpieza");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "Gestionando habitaciones");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(this, "Saliendo del programa");
                    break;
            }
        } while (opcion != 3);
        System.exit(0);  
    }
}
