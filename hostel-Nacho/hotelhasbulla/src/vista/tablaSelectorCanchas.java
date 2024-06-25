package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class tablaSelectorCanchas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    tablaSelectorCanchas frame = new tablaSelectorCanchas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public tablaSelectorCanchas() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.BLACK); // Fondo negro
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Fuente para los botones
        Font buttonFont = new Font("Mandalore", Font.PLAIN, 20);

        // Crear y agregar botones con los mismos estilos
        JButton futbolButton = new JButton("Fútbol");
        futbolButton.setBounds(50, 50, 300, 40);
        futbolButton.setFont(buttonFont);
        futbolButton.setBackground(Color.RED); // Fondo rojo
        futbolButton.setForeground(Color.BLACK); // Texto negro
        contentPane.add(futbolButton);
        futbolButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new tablaCanchaFutbol().setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });

        JButton tenisButton = new JButton("Tenis");
        tenisButton.setBounds(50, 125, 300, 40);
        tenisButton.setFont(buttonFont);
        tenisButton.setBackground(Color.RED);
        tenisButton.setForeground(Color.BLACK);
        contentPane.add(tenisButton);
        tenisButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new tablaCanchaTenis().setVisible(true);
                dispose(); // Cerrar la ventana actual
            }
        });

        JButton salirButton = new JButton("Salir");
        salirButton.setBounds(50, 200, 300, 40);
        salirButton.setFont(buttonFont);
        salirButton.setBackground(Color.RED);
        salirButton.setForeground(Color.BLACK);
        contentPane.add(salirButton);
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saliendo de la gestión de canchas.");
                dispose(); // Cerrar la ventana actual
            }
        });
    }
}
