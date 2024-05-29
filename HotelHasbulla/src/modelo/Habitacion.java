package modelo;

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
}
