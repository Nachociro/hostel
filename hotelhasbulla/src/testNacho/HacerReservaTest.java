package testNacho;
import static org.junit.Assert.*;
import org.junit.Test;
import modelo.SingletonHabitaciones;

public class HacerReservaTest {

    @Test
    public void testReservarHabitacion() {
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
        assertTrue(singleton.reservarHabitacion());
    }
}
