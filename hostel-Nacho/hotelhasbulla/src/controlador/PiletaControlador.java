
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

    // Método para obtener la única pileta desde la base de datos
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
                // Si no hay registros en la tabla, inicializar con capacidad máxima y 0 personas
                pileta = new Pileta(50, 0); // Suponiendo que la capacidad máxima es 50 (ajustar según tu modelo)
                // Insertar la nueva pileta en la tabla si no hay ninguna
                String insertSql = "INSERT INTO pileta (capacidad, cantidad_personas) VALUES (?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, 50); // Capacidad máxima inicial
                    insertStmt.setInt(2, 0); // Inicialmente no hay personas
                    insertStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pileta;
    }

    // Método para actualizar la cantidad de personas en la base de datos
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
