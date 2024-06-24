package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import usuario.Limpieza;
import controlador.DatabaseConnection;

public class LimpiezaControlador {

    private Connection connection;

    public LimpiezaControlador() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<Limpieza> getAllLimpiezas() {
        List<Limpieza> limpiezas = new ArrayList<>();
        String query = "SELECT * FROM limpieza";

        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idLimpieza = rs.getInt("idLimpieza");
                int numeroHabitacion = rs.getInt("numeroHabitacion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String hora = rs.getString("hora");
                Limpieza limpieza = new Limpieza(idLimpieza, numeroHabitacion, fecha, hora);
                limpiezas.add(limpieza);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return limpiezas;
    }

    public void deleteLimpieza(int idLimpieza) {
        String query = "DELETE FROM limpieza WHERE idLimpieza = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idLimpieza);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLimpieza(Limpieza limpieza) {
        String query = "UPDATE limpieza SET numeroHabitacion = ?, fecha = ?, hora = ? WHERE idLimpieza = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limpieza.getNumeroHabitacion());
            pstmt.setDate(2, Date.valueOf(limpieza.getFecha()));
            pstmt.setString(3, limpieza.getHora());
            pstmt.setInt(4, limpieza.getIdLimpieza());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tieneLimpiezaActivaEnHabitacion(int numeroHabitacion) {
        boolean tieneLimpiezaActiva = false;
        String query = "SELECT COUNT(*) AS count FROM limpieza WHERE numeroHabitacion = ? AND fecha >= ?";

        LocalDate fechaActual = LocalDate.now();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, numeroHabitacion);
            pstmt.setDate(2, Date.valueOf(fechaActual));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                tieneLimpiezaActiva = (count > 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tieneLimpiezaActiva;
    }

    // Método para añadir una nueva limpieza (opcional)
    public void addLimpieza(Limpieza limpieza) {
        String query = "INSERT INTO limpieza (numeroHabitacion, fecha, hora) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limpieza.getNumeroHabitacion());
            pstmt.setDate(2, Date.valueOf(limpieza.getFecha()));
            pstmt.setString(3, limpieza.getHora());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
