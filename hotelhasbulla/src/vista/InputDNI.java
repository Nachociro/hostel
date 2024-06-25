package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import modelo.SingletonHabitaciones;

public class InputDNI extends JFrame {
    private JTextField textField;
    private SingletonHabitaciones singleton;

    public InputDNI(SingletonHabitaciones singleton) {
        this.singleton = singleton;
        setTitle("Ingresar DNI");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JLabel lblDni = new JLabel("Ingrese el ID del huésped:");
        getContentPane().add(lblDni, BorderLayout.NORTH);

        textField = new JTextField();
        getContentPane().add(textField, BorderLayout.CENTER);
        textField.setColumns(10);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id_huesped = textField.getText();
                if (!id_huesped.isEmpty()) {
                    singleton.finalizarReserva(id_huesped);
                    dispose();
                } else {
                    lblDni.setText("El ID no puede estar vacío. Intente de nuevo.");
                }
            }
        });
        getContentPane().add(btnAceptar, BorderLayout.SOUTH);
    }
}
