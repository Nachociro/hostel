package vista;

import com.toedter.calendar.JDateChooser;

import modelo.SingletonHabitaciones;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class calendario extends JFrame {
    private JDateChooser dateChooser1;
    private JSpinner hourSpinner;
    private JSpinner minuteSpinner;
    private JDateChooser dateChooser2;

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

        // Crear el botón "Confirmar"
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.addActionListener(e -> {
            Date fecha1 = dateChooser1.getDate();
            Date fecha2 = dateChooser2.getDate();
            int hora = (int) hourSpinner.getValue();
            int minutos = (int) minuteSpinner.getValue();

            // Configurar las fechas y hora seleccionadas
            Calendar calFecha1 = Calendar.getInstance();
            calFecha1.setTime(fecha1);
            calFecha1.set(Calendar.HOUR_OF_DAY, hora);
            calFecha1.set(Calendar.MINUTE, minutos);
            Date fechaEntrada = new Date(calFecha1.getTimeInMillis());

            Calendar calFecha2 = Calendar.getInstance();
            calFecha2.setTime(fecha2);
            calFecha2.set(Calendar.HOUR_OF_DAY, hora);
            calFecha2.set(Calendar.MINUTE, minutos);
            Date fechaSalida = new Date(calFecha2.getTimeInMillis());

            // Actualizar las fechas en el Singleton
            SingletonHabitaciones.getInstance().setFechas(fechaEntrada, fechaSalida);

            // Cerrar la ventana después de configurar las fechas
            dispose();
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
        panel.add(botonConfirmar);

        // Agregar el panel al JFrame
        add(panel);
    }

    public void setFechas(Date fechaEntrada, Date fechaSalida) {
        dateChooser1.setDate(fechaEntrada);
        dateChooser2.setDate(fechaSalida);
    }

    public static void main(String[] args) {
        // Ejecutar en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            calendario cal = new calendario();
            cal.setVisible(true);
        });
    }
}
