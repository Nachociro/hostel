package modelo;

import javax.swing.JOptionPane;

public class Habitacion {
    private int numero_habitacion;
    private String tipo;
    private String descripcion;
    private double precio;
    private boolean disponibilidad;
    private boolean limpieza;

    public Habitacion(int numero_habitacion, String tipo, String descripcion, double precio, boolean disponibilidad, boolean limpieza) {
        this.numero_habitacion = numero_habitacion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
        this.limpieza = limpieza;
    }

    public int getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(int numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean isLimpieza() {
        return limpieza;
    }

    public void setLimpieza(boolean limpieza) {
        this.limpieza = limpieza;
    }

    @Override
    public String toString() {
        return "Habitacion [numero_habitacion=" + numero_habitacion + ", tipo=" + tipo + ", descripcion=" + descripcion
                + ", precio=" + precio + ", disponibilidad=" + disponibilidad + ", limpieza=" + limpieza + "]";
    }

    public void reservarHabitacion() {
        if (this.disponibilidad && !this.limpieza) {
            this.disponibilidad = false;
            System.out.println("Habitación " + this.numero_habitacion + " reservada con éxito.");
        } else {
            System.out.println("Habitación " + this.numero_habitacion + " no está disponible para reservar.");
        }
    }

    public static void reservarHabitacion(Habitacion[] habitaciones) {
        String habitacionNum = JOptionPane.showInputDialog("Ingrese el número de la habitación a reservar:");
        int num = Integer.parseInt(habitacionNum);
        boolean habitacionEncontrada = false;

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero_habitacion() == num) {
                habitacion.reservarHabitacion();
                habitacionEncontrada = true;
                break;
            }
        }

        if (!habitacionEncontrada) {
            JOptionPane.showMessageDialog(null, "Habitación no encontrada.");
        }
    }
}
