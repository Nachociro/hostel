package interfaces;

import modelo.Cliente;
import java.util.List;

public interface ClienteRepository {
    void addCliente(Cliente cliente);
    Cliente getClienteById(int id);
    List<Cliente> getAllClientes();
    void updateCliente(Cliente cliente);
    void deleteCliente(int id);
}

