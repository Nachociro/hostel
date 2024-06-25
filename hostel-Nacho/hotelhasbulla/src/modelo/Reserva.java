package modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Reserva {
    private static LinkedList<Reserva> reservas = new LinkedList<>();
    private static int idReserva = 0;

    private int id_reserva;
    private Date fecha_entrada;
    private Date fecha_salida;
    private int dniHuesped; // Nuevo atributo DNI del huésped
    private int numero_habitacion;

    public Reserva(int id_reserva, Date fecha_entrada, Date fecha_salida, int dniHuesped, int numero_habitacion) {
        this.id_reserva = id_reserva;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.dniHuesped = dniHuesped;
        this.numero_habitacion = numero_habitacion;
    }

    public static int generarIdReserva() {
        return ++idReserva;
    }

    public static void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public static Reserva buscarReservaPorDNI(int dniHuesped) {
        for (Reserva reserva : reservas) {
            if (reserva.getDniHuesped() == dniHuesped) {
                return reserva;
            }
        }
        return null;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getDniHuesped() {
        return dniHuesped;
    }

    public void setDniHuesped(int dniHuesped) {
        this.dniHuesped = dniHuesped;
    }

    public int getNumero_habitacion() {
        return numero_habitacion;
    }

    public void setNumero_habitacion(int numero_habitacion) {
        this.numero_habitacion = numero_habitacion;
    }

    @Override
    public String toString() {
        return "Reserva [id_reserva=" + id_reserva + ", fecha_entrada=" + fecha_entrada + ", fecha_salida=" + fecha_salida
                + ", dniHuesped=" + dniHuesped + ", numero_habitacion=" + numero_habitacion + "]";
    }
}
