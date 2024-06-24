package vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;

public class pantallaInicio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private Choice userChoice;

    private static final String CONTRASENA_ADMIN = "ADMIN";
    private static final String CONTRASENA_EMPLEADO = "1234";

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
        setBounds(100, 100, 679, 472);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Hotel Hasbulla");
        lblTitulo.setForeground(Color.RED);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("MLS 2023", Font.PLAIN, 58));
        lblTitulo.setBounds(0, 29, 665, 60);
        contentPane.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Mandalore", Font.ITALIC, 20));
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setBounds(147, 173, 88, 20);
        contentPane.add(lblUsuario);

        userChoice = new Choice();
        userChoice.setBackground(UIManager.getColor("Button.background"));
        userChoice.setForeground(Color.BLACK);
        userChoice.setBounds(281, 173, 200, 30);
        userChoice.add("admin");
        userChoice.add("recepcionista");
        userChoice.add("limpieza");
        contentPane.add(userChoice);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Mandalore", Font.ITALIC, 20));
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setBounds(147, 237, 136, 20);
        contentPane.add(lblContrasena);

        passwordField = new JPasswordField();
        passwordField.setBackground(UIManager.getColor("Button.background"));
        passwordField.setForeground(Color.BLACK);
        passwordField.setBounds(281, 242, 200, 20);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("Mandalore", Font.PLAIN, 20));
        btnLogin.setBackground(Color.RED);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = obtenerUsuarioSeleccionado();
                String contrasena = new String(passwordField.getPassword());
                realizarLogin(usuario, contrasena);
            }
        });
        btnLogin.setBounds(306, 307, 127, 43);
        contentPane.add(btnLogin);
    }

    private String obtenerUsuarioSeleccionado() {
        return userChoice.getSelectedItem();
    }

    private void realizarLogin(String usuario, String contrasena) {
        switch (usuario) {
            case "recepcionista":
            case "limpieza":
                if (CONTRASENA_EMPLEADO.equals(contrasena)) {
                    if (usuario.equals("recepcionista")) {
                        Main.mostrarMenuRecepcionista();
                    } else {
                        Main.mostrarMenuLimpieza();
                    }
                } else {
                    mostrarMensajeCredencialesIncorrectas();
                }
                break;
            case "admin":
                if (CONTRASENA_ADMIN.equals(contrasena)) {
                    Main.mostrarMenuAdmin();
                } else {
                    mostrarMensajeCredencialesIncorrectas();
                }
                break;
            default:
                mostrarMensajeCredencialesIncorrectas();
        }
    }

    private void mostrarMensajeCredencialesIncorrectas() {
        JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Intente de nuevo.");
    }
}
