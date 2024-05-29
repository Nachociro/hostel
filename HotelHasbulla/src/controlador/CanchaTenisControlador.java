package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.CanchaTenis;

public class CanchaTenisControlador {
    private final Connection connection;

    public CanchaTenisControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<CanchaTenis> getAllCanchas() {
        List<CanchaTenis> canchas = new ArrayList<>();
        String query = "SELECT * FROM CanchaTenis";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                CanchaTenis cancha = new CanchaTenis(
                        resultSet.getInt("id_Tenis"),
                        resultSet.getString("piso"),
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

    public CanchaTenis getCanchaById(int id) {
        CanchaTenis cancha = null;
        String query = "SELECT * FROM CanchaTenis WHERE id_Tenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cancha = new CanchaTenis(
                            resultSet.getInt("id_Tenis"),
                            resultSet.getString("piso"),
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

    public void addCancha(CanchaTenis cancha) {
        String query = "INSERT INTO CanchaTenis (piso, precio, disponible) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cancha.getpiso());
            statement.setDouble(2, cancha.getPrecio());
            statement.setBoolean(3, cancha.isDisponible());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Cancha de tenis insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCancha(CanchaTenis cancha) {
        String query = "UPDATE CanchaTenis SET piso = ?, precio = ?, disponible = ? WHERE id_Tenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cancha.getpiso());
            statement.setDouble(2, cancha.getPrecio());
            statement.setBoolean(3, cancha.isDisponible());
            statement.setInt(4, cancha.getId_Tenis());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cancha de tenis actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCancha(int id) {
        String query = "DELETE FROM CanchaTenis WHERE id_Tenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cancha de tenis eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
