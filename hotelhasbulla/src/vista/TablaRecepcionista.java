package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.SingletonHabitaciones;
import modelo.SelectorCanchas;

public class TablaRecepcionista extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
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

    /**
     * Create the frame.
     */
    public TablaRecepcionista() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Create and add buttons
        JButton btnReservar = new JButton("Reservar Habitación");
        btnReservar.setBounds(50, 50, 200, 30);
        contentPane.add(btnReservar);
        btnReservar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SingletonHabitaciones.getInstance().reservarHabitacion();
            }
        });

        JButton btnCheckIn = new JButton("Hacer Check-in");
        btnCheckIn.setBounds(50, 100, 200, 30);
        contentPane.add(btnCheckIn);
        btnCheckIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SingletonHabitaciones.getInstance().hacerCheckIn();
            }
        });

        JButton btnCheckOut = new JButton("Hacer Check-out");
        btnCheckOut.setBounds(50, 150, 200, 30);
        contentPane.add(btnCheckOut);
        btnCheckOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SingletonHabitaciones.getInstance().hacerCheckOut();
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
