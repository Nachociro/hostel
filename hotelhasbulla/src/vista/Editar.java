

package vista;

import usuario.Limpieza;
import controlador.LimpiezaControlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Editar extends JFrame {

    private Limpieza limpieza;
    private LimpiezaControlador controlador;
    private JTextField txtNumeroHabitacion;
    private JTextField txtFecha;
    private JTextField txtHora;

    public Editar(Limpieza limpiezaSeleccionada, LimpiezaControlador controlador) {
        this.limpieza = limpiezaSeleccionada;
        this.controlador = controlador;

        setTitle("Editar Limpieza");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(5, 2, 5, 5));

        getContentPane().add(new JLabel("Número de Habitación:"));
        txtNumeroHabitacion = new JTextField(String.valueOf(limpiezaSeleccionada.getNumeroHabitacion()));
        getContentPane().add(txtNumeroHabitacion);

        getContentPane().add(new JLabel("Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField(limpiezaSeleccionada.getFecha().toString());
        getContentPane().add(txtFecha);

        getContentPane().add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField(limpiezaSeleccionada.getHora());
        getContentPane().add(txtHora);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiezaSeleccionada.setNumeroHabitacion(Integer.parseInt(txtNumeroHabitacion.getText()));
                limpiezaSeleccionada.setFecha(LocalDate.parse(txtFecha.getText()));
                limpiezaSeleccionada.setHora(txtHora.getText());
                controlador.updateLimpieza(limpiezaSeleccionada);
                JOptionPane.showMessageDialog(null, "Limpieza actualizada");
                dispose();
            }
        });
        getContentPane().add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCancelar);
    }
}