package modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;

public class Reserva_Futbol {
	private int id_reserva_futbol;
	private LinkedList<Habitacion> id_huesped;
	private LinkedList<Cancha_Futbol> id_futbol;
	private Time horaInicio;
    private Time horaFin;
    private Date fecha;
	
    public Reserva_Futbol(int id_reserva_futbol, LinkedList<Habitacion> id_huesped, LinkedList<Cancha_Futbol> id_futbol, Time horaInicio, Time horaFin, Date fecha) {
		this.id_reserva_futbol = id_reserva_futbol;
		this.id_huesped = id_huesped;
		this.id_futbol = id_futbol;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.fecha = fecha;
	}
	public int getId_reserva_futbol() {
		return id_reserva_futbol;
	}
	public void setId_reserva_futbol(int id_reserva_futbol) {
		this.id_reserva_futbol = id_reserva_futbol;
	}
	public LinkedList<Habitacion> getId_huesped() {
		return id_huesped;
	}
	public void setId_huesped(LinkedList<Habitacion> id_huesped) {
		this.id_huesped = id_huesped;
	}
	public LinkedList<Cancha_Futbol> getId_futbol() {
		return id_futbol;
	}
	public void setId_futbol(LinkedList<Cancha_Futbol> id_futbol) {
		this.id_futbol = id_futbol;
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
		return "Reserva_Futbol [id_reserva_futbol=" + id_reserva_futbol + ", id_huesped=" + id_huesped + ", id_futbol=" + id_futbol + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", fecha=" + fecha + "]";
	} 
}