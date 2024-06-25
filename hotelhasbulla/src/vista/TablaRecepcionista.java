package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.SingletonHabitaciones;
import modelo.SelectorCanchas;

public class TablaRecepcionista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TablaRecepcionista frame = new TablaRecepcionista();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TablaRecepcionista() {
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
        JButton btnReservar = new JButton("Reservar Habitación");
        btnReservar.setBounds(50, 50, 300, 40);
        btnReservar.setFont(buttonFont);
        btnReservar.setBackground(Color.RED); // Fondo rojo
        btnReservar.setForeground(Color.BLACK); // Texto negro
        contentPane.add(btnReservar);
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
                singleton.reservarHabitacion();
            }
        });

        JButton btnCheckIn = new JButton("Hacer Check-in");
        btnCheckIn.setBounds(50, 100, 300, 40);
        btnCheckIn.setFont(buttonFont);
        btnCheckIn.setBackground(Color.RED);
        btnCheckIn.setForeground(Color.BLACK);
        contentPane.add(btnCheckIn);
        btnCheckIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
                singleton.hacerCheckIn();
            }
        });

        JButton btnCheckOut = new JButton("Hacer Check-out");
        btnCheckOut.setBounds(50, 150, 300, 40);
        btnCheckOut.setFont(buttonFont);
        btnCheckOut.setBackground(Color.RED);
        btnCheckOut.setForeground(Color.BLACK);
        contentPane.add(btnCheckOut);
        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {               
                dispose();
                SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
                singleton.hacerCheckOut();
            }
        });
        
        JButton btnGestionarPileta = new JButton("Gestionar Pileta");
        btnGestionarPileta.setBounds(50, 200, 200, 30);
        contentPane.add(btnGestionarPileta);
        btnGestionarPileta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TablaPileta tablaPileta = new TablaPileta();
                tablaPileta.setVisible(true);
            }
        });

        JButton btnGestionarCanchas = new JButton("Gestionar Canchas");
        btnGestionarCanchas.setBounds(50, 250, 200, 30);
        contentPane.add(btnGestionarCanchas);
        btnGestionarCanchas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SelectorCanchas.elegirDeporte();
            }
        });
    }
}
