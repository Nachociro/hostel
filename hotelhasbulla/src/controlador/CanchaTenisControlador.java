package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.CanchaTenis;

public class CanchaTenisControlador {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<CanchaTenis> getAllCanchas() {
        List<CanchaTenis> canchas = new ArrayList<>();

        String sql = "SELECT * FROM cancha_tenis";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_tenis");
                String piso = rs.getString("piso");
                double precio = rs.getDouble("precio");
                boolean disponible = rs.getBoolean("disponible");
                canchas.add(new CanchaTenis(id, piso, precio, disponible));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canchas;
    }

    public void reservarCancha(int id, int numPersonas) {
        String sql = "UPDATE cancha_tenis SET disponible = false, precio = ? WHERE id_tenis = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            double precio = 100.0;
            if (numPersonas >= 8) {
                precio *= 0.75;
            }

            stmt.setDouble(1, precio);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelarReserva(int id) {
        String sql = "UPDATE cancha_tenis SET disponible = true WHERE id_tenis = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
