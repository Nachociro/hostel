package interfaces;

import java.util.List;
import modelo.ReservaTenis;

public interface ReservaTenisRepository {
    List<ReservaTenis> getAllReservas();
    ReservaTenis getReservaById(int id);
    void addReserva(ReservaTenis reserva);
    void updateReserva(ReservaTenis reserva);
    void deleteReserva(int id);
}
