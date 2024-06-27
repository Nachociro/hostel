package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

    public static void mostrarMenuRecepcionista(JFrame frameActual) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frameActual.dispose(); // Cerrar la ventana actual
                    TablaRecepcionista frame = new TablaRecepcionista();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void mostrarMenuAdmin(JFrame parent) {
        TablaAdmin tablaAdmin = new TablaAdmin();
        tablaAdmin.setVisible(true);
        parent.dispose(); 
    }

    public static void mostrarMenuLimpieza(JFrame frameActual) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frameActual.dispose();
                    tablaLimpieza frame = new tablaLimpieza();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

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
}
