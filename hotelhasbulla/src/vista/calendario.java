package vista;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class calendario extends JFrame {
    private JDateChooser dateChooser1;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JDateChooser dateChooser2;
    private boolean fechasSeleccionadas = false;

    public calendario() {
        // Configurar el JFrame
        setTitle("Calendario con Hora en JFrame");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Crear el primer JDateChooser
        dateChooser1 = new JDateChooser();
        dateChooser1.setDateFormatString("dd/MM/yyyy");

        // Establecer la fecha mínima a hoy
        Calendar today = Calendar.getInstance();
        today.add(Calendar.DAY_OF_YEAR, 1); // Agrega un día a la fecha actual
        dateChooser1.setMinSelectableDate(today.getTime());

        // Crear JSpinner para horas
        SpinnerNumberModel hourModel = new SpinnerNumberModel(0, 0, 23, 1);
        hourSpinner = new JSpinner(hourModel);
        JSpinner.NumberEditor hourEditor = new JSpinner.NumberEditor(hourSpinner, "00");
        hourSpinner.setEditor(hourEditor);

        // Crear JSpinner para minutos
        SpinnerNumberModel minuteModel = new SpinnerNumberModel(0, 0, 59, 1);
        minuteSpinner = new JSpinner(minuteModel);
        JSpinner.NumberEditor minuteEditor = new JSpinner.NumberEditor(minuteSpinner, "00");
        minuteSpinner.setEditor(minuteEditor);

        // Crear el segundo JDateChooser
        dateChooser2 = new JDateChooser();
        dateChooser2.setDateFormatString("dd/MM/yyyy");
        dateChooser2.setEnabled(false); // Inicialmente deshabilitado

        // Agregar ActionListener al primer JDateChooser
        dateChooser1.addPropertyChangeListener("date", evt -> {
            Date selectedDate = dateChooser1.getDate();
            if (selectedDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(selectedDate);
                cal.add(Calendar.DAY_OF_YEAR, 1);
                dateChooser2.setMinSelectableDate(cal.getTime());
                dateChooser2.setEnabled(true);
            }
        });

        // Crear el botón "Listo"
        JButton botonListo = new JButton("Listo");
        botonListo.addActionListener(e -> {
            fechasSeleccionadas = true;
            synchronized (this) {
                this.notify();
            }
        });

        // Agregar componentes al panel
        panel.add(new JLabel("Fecha 1:"));
        panel.add(dateChooser1);
        panel.add(new JLabel("Hora:"));
        panel.add(hourSpinner);
        panel.add(new JLabel("Minutos:"));
        panel.add(minuteSpinner);
        panel.add(new JLabel("Fecha 2:"));
        panel.add(dateChooser2);
        panel.add(botonListo);

        // Agregar el panel al JFrame
        add(panel);
    }

    public Date getFecha1() {
        return dateChooser1.getDate();
    }

    public Date getFecha2() {
        return dateChooser2.getDate();
    }

    public int getHora() {
        return (int) hourSpinner.getValue();
    }

    public int getMinutos() {
        return (int) minuteSpinner.getValue();
    }

    public boolean isFechasSeleccionadas() {
        return fechasSeleccionadas;
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(calendario::new);
    }
}
