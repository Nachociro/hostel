package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import modelo.CanchaTenis;
import modelo.Habitacion;
import modelo.ReservaTenis;

public class ReservaTenisControlador {
    private final Connection connection;

    public ReservaTenisControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<ReservaTenis> getAllReservas() {
        List<ReservaTenis> reservas = new ArrayList<>();
        String query = "SELECT * FROM ReservaTenis";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
             
            while (resultSet.next()) {
                // Este bloque es para convertir los IDs de huesped y tenis en listas de objetos
                LinkedList<Habitacion> huespedes = obtenerHuespedes(resultSet.getInt("id_ReservaTenis"));
                LinkedList<CanchaTenis> canchas = obtenerCanchas(resultSet.getInt("id_ReservaTenis"));

                ReservaTenis reserva = new ReservaTenis(
                        resultSet.getInt("id_ReservaTenis"),
                        huespedes,
                        canchas,
                        resultSet.getTime("horaInicio"),
                        resultSet.getTime("horaFin"),
                        resultSet.getDate("fecha")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public ReservaTenis getReservaById(int id) {
        ReservaTenis reserva = null;
        String query = "SELECT * FROM ReservaTenis WHERE id_ReservaTenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LinkedList<Habitacion> huespedes = obtenerHuespedes(id);
                    LinkedList<CanchaTenis> canchas = obtenerCanchas(id);

                    reserva = new ReservaTenis(
                            resultSet.getInt("id_ReservaTenis"),
                            huespedes,
                            canchas,
                            resultSet.getTime("horaInicio"),
                            resultSet.getTime("horaFin"),
                            resultSet.getDate("fecha")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    public void addReserva(ReservaTenis reserva) {
        String query = "INSERT INTO ReservaTenis (horaInicio, horaFin, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setTime(1, reserva.getHoraInicio());
            statement.setTime(2, reserva.getHoraFin());
            statement.setDate(3, reserva.getFecha());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Reserva de tenis insertada exitosamente");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int reservaId = generatedKeys.getInt(1);
                        agregarHuespedesYcanchas(reservaId, reserva.getId_huesped(), reserva.getId_Tenis());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReserva(ReservaTenis reserva) {
        String query = "UPDATE ReservaTenis SET horaInicio = ?, horaFin = ?, fecha = ? WHERE id_ReservaTenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setTime(1, reserva.getHoraInicio());
            statement.setTime(2, reserva.getHoraFin());
            statement.setDate(3, reserva.getFecha());
            statement.setInt(4, reserva.getId_reserva_Tenis());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reserva de tenis actualizada exitosamente");
                actualizarHuespedesYcanchas(reserva.getId_reserva_Tenis(), reserva.getId_huesped(), reserva.getId_Tenis());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReserva(int id) {
        String query = "DELETE FROM ReservaTenis WHERE id_ReservaTenis = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reserva de tenis eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Habitacion> obtenerHuespedes(int reservaId) {
        // Implementar la lógica para obtener la lista de huespedes asociados a la reserva
        return new LinkedList<>();
    }

    private LinkedList<CanchaTenis> obtenerCanchas(int reservaId) {
        // Implementar la lógica para obtener la lista de canchas asociadas a la reserva
        return new LinkedList<>();
    }

    private void agregarHuespedesYcanchas(int reservaId, LinkedList<Habitacion> huespedes, LinkedList<CanchaTenis> canchas) {
        // Implementar la lógica para agregar huespedes y canchas a la reserva
    }

    private void actualizarHuespedesYcanchas(int reservaId, LinkedList<Habitacion> huespedes, LinkedList<CanchaTenis> canchas) {
        // Implementar la lógica para actualizar huespedes y canchas de la reserva
    }
}
