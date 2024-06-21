package interfaces;

import java.util.List;
import modelo.CanchaFutbol;

public interface CanchaFutbolRepository {
    List<CanchaFutbol> getAllCanchas();
    CanchaFutbol getCanchaById(int id);
    void addCancha(CanchaFutbol cancha);
    void updateCancha(CanchaFutbol cancha);
    void deleteCancha(int id);
}
