package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Pileta;

public class PiletaControlador {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    
    public Pileta getPileta() {
        Pileta pileta = null;

        String sql = "SELECT capacidad, cantidad_personas FROM pileta";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int capacidad = rs.getInt("capacidad");
                int cantidadPersonas = rs.getInt("cantidad_personas");
                pileta = new Pileta(capacidad, cantidadPersonas);
            } else {
                pileta = new Pileta(50, 0);
                String insertSql = "INSERT INTO pileta (capacidad, cantidad_personas) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, 50);
                    insertStmt.setInt(2, 0);
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pileta;
    }

    public void actualizarCantidadPersonas(Pileta pileta) {
        String sql = "UPDATE pileta SET cantidad_personas = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pileta.getCantidadPersonas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
