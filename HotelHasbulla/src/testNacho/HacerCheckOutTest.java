package testNacho;

import static org.junit.Assert.*;
import org.junit.Test;
import modelo.SingletonHabitaciones;

public class HacerCheckOutTest {

    @Test
    public void testHacerCheckOut() {
        SingletonHabitaciones singleton = SingletonHabitaciones.getInstance();
        singleton.hacerCheckOut();
        
    }
}
