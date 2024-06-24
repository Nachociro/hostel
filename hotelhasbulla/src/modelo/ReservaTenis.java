package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

public class ReservaTenis {
	private int id_reserva_Tenis;
	private LinkedList<Habitacion> id_huesped;
	private LinkedList<CanchaTenis> id_Tenis;
	private Time horaInicio;
    private Time horaFin;
    private Date fecha;
	
    public ReservaTenis(int id_reserva_Tenis, LinkedList<Habitacion> id_huesped, LinkedList<CanchaTenis> id_Tenis, Time horaInicio, Time horaFin, Date fecha) {
		this.id_reserva_Tenis = id_reserva_Tenis;
		this.id_huesped = id_huesped;
		this.id_Tenis = id_Tenis;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fecha = fecha;
	}
	public int getId_reserva_Tenis() {
		return id_reserva_Tenis;
	}
	public void setId_reserva_Tenis(int id_reserva_Tenis) {
		this.id_reserva_Tenis = id_reserva_Tenis;
	}
	public LinkedList<Habitacion> getId_huesped() {
		return id_huesped;
	}
	public void setId_huesped(LinkedList<Habitacion> id_huesped) {
		this.id_huesped = id_huesped;
	}
	public LinkedList<CanchaTenis> getId_Tenis() {
		return id_Tenis;
	}
	public void setId_Tenis(LinkedList<CanchaTenis> id_Tenis) {
		this.id_Tenis = id_Tenis;
	}
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Reserva_Tenis [id_reserva_Tenis=" + id_reserva_Tenis + ", id_huesped=" + id_huesped + ", id_Tenis=" + id_Tenis + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fecha=" + fecha + "]";
	}   
}