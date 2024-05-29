package controlador;

import modelo.Habitacion;
import modelo.Reserva;
import interfaces.HabitacionRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionControlador implements HabitacionRepository {
    private final Connection connection;

    public HabitacionControlador() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public void addHabitacion(Habitacion habitacion) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO habitaciones (numero_habitacion, tipo, descripcion, precio, disponibilidad, limpieza) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setInt(1, habitacion.getNumero_habitacion());
            statement.setString(2, habitacion.getTipo());
            statement.setString(3, habitacion.getDescripcion());
            statement.setDouble(4, habitacion.getPrecio());
            statement.setBoolean(5, habitacion.isDisponibilidad());
            statement.setBoolean(6, habitacion.isLimpieza());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Habitación insertada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Habitacion> getAllHabitaciones() {
        List<Habitacion> habitaciones = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM habitaciones");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Habitacion habitacion = new Habitacion(
                    resultSet.getInt("numero_habitacion"),
                    resultSet.getString("tipo"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("disponibilidad"),
                    resultSet.getBoolean("limpieza")
                );
                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }

    @Override
    public Habitacion getHabitacionByNumero(int numero_habitacion) {
        Habitacion habitacion = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM habitaciones WHERE numero_habitacion = ?");
            statement.setInt(1, numero_habitacion);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                habitacion = new Habitacion(
                    resultSet.getInt("numero_habitacion"),
                    resultSet.getString("tipo"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("disponibilidad"),
                    resultSet.getBoolean("limpieza")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacion;
    }

    @Override
    public void updateHabitacion(Habitacion habitacion) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE habitaciones SET tipo = ?, descripcion = ?, precio = ?, disponibilidad = ?, limpieza = ? WHERE numero_habitacion = ?"
            );
            statement.setString(1, habitacion.getTipo());
            statement.setString(2, habitacion.getDescripcion());
            statement.setDouble(3, habitacion.getPrecio());
            statement.setBoolean(4, habitacion.isDisponibilidad());
            statement.setBoolean(5, habitacion.isLimpieza());
            statement.setInt(6, habitacion.getNumero_habitacion());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Habitación actualizada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHabitacion(int numero_habitacion) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM habitaciones WHERE numero_habitacion = ?");
            statement.setInt(1, numero_habitacion);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Habitación eliminada exitosamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Habitacion> getHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        List<Habitacion> todasLasHabitaciones = getAllHabitaciones();

        for (Habitacion habitacion : todasLasHabitaciones) {
            if (habitacion.isDisponibilidad() && !habitacion.isLimpieza()) {
                habitacionesDisponibles.add(habitacion);
            }
        }

        return habitacionesDisponibles;
    }
}
