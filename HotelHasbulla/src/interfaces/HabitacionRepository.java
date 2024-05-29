package interfaces;

import java.util.List;
import modelo.Habitacion;

public interface HabitacionRepository {
    
	void addHabitacion(Habitacion habitacion);
    
    List<Habitacion> getAllHabitaciones();
   
    Habitacion getHabitacionByNumero(int numero_habitacion);
    
    void updateHabitacion(Habitacion habitacion);
    
    void deleteHabitacion(int numero_habitacion);
}

