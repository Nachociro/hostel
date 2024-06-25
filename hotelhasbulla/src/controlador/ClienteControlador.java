package controlador;

import modelo.Cliente;
import interfaces.ClienteRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteControlador implements ClienteRepository {
    private final Connection connection;

    public ClienteControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void addCliente(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO cliente (nombre_huesped, apellido, correo, telefono, resena) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, cliente.getNombre_huesped());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCorreo());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getResena());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cliente insertado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente getClienteById(int id) {
        Cliente cliente = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE id_huesped = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente(
                    resultSet.getInt("id_huesped"),
                    resultSet.getString("nombre_huesped"),
                    resultSet.getString("apellido"),
                    resultSet.getString("correo"),
                    resultSet.getString("telefono"),
                    resultSet.getString("resena")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente(
                    resultSet.getInt("id_huesped"),
                    resultSet.getString("nombre_huesped"),
                    resultSet.getString("apellido"),
                    resultSet.getString("correo"),
                    resultSet.getString("telefono"),
                    resultSet.getString("resena")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void updateCliente(Cliente cliente) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE cliente SET nombre_huesped = ?, apellido = ?, correo = ?, telefono = ?, resena = ? WHERE id_huesped = ?"
            );
            statement.setString(1, cliente.getNombre_huesped());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getCorreo());
            statement.setString(4, cliente.getTelefono());
            statement.setString(5, cliente.getResena());
            statement.setInt(6, cliente.getId_huesped());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente actualizado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCliente(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cliente WHERE id_huesped = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cliente eliminado exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente buscarClientePorDni(int dniHuesped) {
        Cliente cliente = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cliente WHERE dni = ?")) {
            statement.setInt(1, dniHuesped);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new Cliente(
                        resultSet.getInt("id_huesped"),
                        resultSet.getString("nombre_huesped"),
                        resultSet.getString("apellido"),
                        resultSet.getString("correo"),
                        resultSet.getString("telefono"),
                        resultSet.getString("resena")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }
	
}
