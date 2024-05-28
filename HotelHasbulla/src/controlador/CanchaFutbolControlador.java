package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.CanchaFutbol;

public class CanchaFutbolControlador {
    private final Connection connection;

    public CanchaFutbolControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<CanchaFutbol> getAllCanchas() {
        List<CanchaFutbol> canchas = new ArrayList<>();
        String query = "SELECT * FROM CanchaFutbol";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                CanchaFutbol cancha = new CanchaFutbol(
                        resultSet.getInt("id_futbol"),
                        resultSet.getInt("tamano"),
                        resultSet.getDouble("precio"),
                        resultSet.getBoolean("disponible")
                );
                canchas.add(cancha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canchas;
    }

    public CanchaFutbol getCanchaById(int id) {
        CanchaFutbol cancha = null;
        String query = "SELECT * FROM CanchaFutbol WHERE id_futbol = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cancha = new CanchaFutbol(
                            resultSet.getInt("id_futbol"),
                            resultSet.getInt("tamano"),
                            resultSet.getDouble("precio"),
                            resultSet.getBoolean("disponible")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cancha;
    }

    public void addCancha(CanchaFutbol cancha) {
        String query = "INSERT INTO CanchaFutbol (tamano, precio, disponible) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cancha.getTamano());
            statement.setDouble(2, cancha.getPrecio());
            statement.setBoolean(3, cancha.isDisponible());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cancha de fútbol insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCancha(CanchaFutbol cancha) {
        String query = "UPDATE CanchaFutbol SET tamano = ?, precio = ?, disponible = ? WHERE id_futbol = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cancha.getTamano());
            statement.setDouble(2, cancha.getPrecio());
            statement.setBoolean(3, cancha.isDisponible());
            statement.setInt(4, cancha.getId_futbol());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cancha de fútbol actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCancha(int id) {
        String query = "DELETE FROM CanchaFutbol WHERE id_futbol = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cancha de fútbol eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
