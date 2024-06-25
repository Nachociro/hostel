package interfaces;

import java.util.List;
import modelo.ReservaFutbol;

public interface ReservaFutbolRepository {
    List<ReservaFutbol> getAllReservas();
    ReservaFutbol getReservaById(int id);
    void addReserva(ReservaFutbol reserva);
    void updateReserva(ReservaFutbol reserva);
    void deleteReserva(int id);
}
