package testNacho;

import static org.junit.Assert.*;
import org.junit.Test;
import modelo.SingletonHabitaciones;

public class HacerCheckInTest {

    @Test
    public void testHacerCheckIn() {
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
        singleton.hacerCheckIn();
        
    }
}
