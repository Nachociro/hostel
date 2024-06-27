package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.CanchaFutbol;

public class CanchaFutbolControlador {
    private static final String URL = "jdbc:mysql://localhost:3306/hostel";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public List<CanchaFutbol> getAllCanchas() {
        List<CanchaFutbol> canchas = new ArrayList<>();

        String sql = "SELECT * FROM cancha_futbol";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_futbol");
                int tamano = rs.getInt("tamano");
                double precio = rs.getDouble("precio");
                boolean disponible = rs.getBoolean("disponible");
                canchas.add(new CanchaFutbol(id, tamano, precio, disponible));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return canchas;
    }
    
    public boolean estaCanchaDisponible(int id) {
        String sql = "SELECT disponible FROM cancha_futbol WHERE id_futbol = ?";
        boolean disponible = false;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    disponible = rs.getBoolean("disponible");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponible;
    }

    public void reservarCancha(int id, int numPersonas) {
        if (!estaCanchaDisponible(id)) {
            JOptionPane.showMessageDialog(null, "La cancha ya está reservada. No se puede realizar la reserva.");
            return;
        }

        String sql = "UPDATE cancha_futbol SET disponible = false WHERE id_futbol = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            double precioOriginal = obtenerPrecioCancha(id);
            double precioFinal = precioOriginal;
            if (numPersonas >= 16) {
                precioFinal *= 0.75;
            }
            JOptionPane.showMessageDialog(null, "Precio final de la reserva: " + precioFinal);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cancelarReserva(int id) {
        if (estaCanchaDisponible(id)) {
            JOptionPane.showMessageDialog(null, "La cancha no está reservada. No se puede cancelar la reserva.");
            return;
        }
        
        String sql = "UPDATE cancha_futbol SET disponible = true WHERE id_futbol = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	JOptionPane.showMessageDialog(null, "Reserva cancelada con éxito");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private double obtenerPrecioCancha(int id) {
        String sql = "SELECT precio FROM cancha_futbol WHERE id_futbol = ?";
        double precio = 0.0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    precio = rs.getDouble("precio");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return precio;
    }
}