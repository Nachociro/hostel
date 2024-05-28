package interfaces;

import java.util.List;
import modelo.CanchaTenis;

public interface CanchaTenisRepository {
    List<CanchaTenis> getAllCanchas();
    CanchaTenis getCanchaById(int id);
    void addCancha(CanchaTenis cancha);
    void updateCancha(CanchaTenis cancha);
    void deleteCancha(int id);
}
