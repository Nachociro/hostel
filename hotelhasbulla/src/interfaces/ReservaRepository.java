package interfaces;

import modelo.Reserva;
import java.util.List;

public interface ReservaRepository {
    
	void addReserva(Reserva reserva);
    
	List<Reserva> getAllReservas();
    
	Reserva getReservaById(int id);
    
	void updateReserva(Reserva reserva);
    
	void deleteReserva(int id);
}
