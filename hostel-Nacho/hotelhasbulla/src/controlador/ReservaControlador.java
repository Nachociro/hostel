package controlador;

import modelo.Reserva;
import interfaces.ReservaRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaControlador implements ReservaRepository {
    private final Connection connection;

    public ReservaControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void addReserva(Reserva reserva) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO reserva (fecha_entrada, fecha_salida, dni_huesped, numero_habitacion) VALUES (?, ?, ?, ?)"
            );
            statement.setDate(1, new java.sql.Date(reserva.getFecha_entrada().getTime()));
            statement.setDate(2, new java.sql.Date(reserva.getFecha_salida().getTime()));
            statement.setInt(3, reserva.getDniHuesped());
            statement.setInt(4, reserva.getNumero_habitacion());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Reserva insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reserva> getAllReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reserva");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Reserva reserva = new Reserva(
                        resultSet.getInt("id_reserva"),
                        resultSet.getDate("fecha_entrada"),
                        resultSet.getDate("fecha_salida"),
                        resultSet.getInt("dni_huesped"),
                        resultSet.getInt("numero_habitacion")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    @Override
    public Reserva getReservaById(int id) {
        Reserva reserva = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reserva WHERE id_reserva = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reserva = new Reserva(
                        resultSet.getInt("id_reserva"),
                        resultSet.getDate("fecha_entrada"),
                        resultSet.getDate("fecha_salida"),
                        resultSet.getInt("dni_huesped"),
                        resultSet.getInt("numero_habitacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }

    @Override
    public void updateReserva(Reserva reserva) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE reserva SET fecha_entrada = ?, fecha_salida = ?, dni_huesped = ?, numero_habitacion = ? WHERE id_reserva = ?"
            );
            statement.setDate(1, new java.sql.Date(reserva.getFecha_entrada().getTime()));
            statement.setDate(2, new java.sql.Date(reserva.getFecha_salida().getTime()));
            statement.setInt(3, reserva.getDniHuesped());
            statement.setInt(4, reserva.getNumero_habitacion());
            statement.setInt(5, reserva.getId_reserva());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reserva actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReserva(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reserva WHERE id_reserva = ?");
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reserva eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reserva buscarReservaPorDNI(int dni) {
        Reserva reserva = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reserva WHERE dni_huesped = ?");
            statement.setInt(1, dni);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reserva = new Reserva(
                        resultSet.getInt("id_reserva"),
                        resultSet.getDate("fecha_entrada"),
                        resultSet.getDate("fecha_salida"),
                        resultSet.getInt("dni_huesped"),
                        resultSet.getInt("numero_habitacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reserva;
    }
}
