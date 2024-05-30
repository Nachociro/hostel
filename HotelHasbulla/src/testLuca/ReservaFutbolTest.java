package testLuca;

import modelo.CanchaFutbol;

public class ReservaFutbolTest {

    public static void main(String[] args) {
        
    	ReservaFutbolTest test = new ReservaFutbolTest();        
        test.testReservarCanchaDisponible();
        test.testReservarCanchaNoDisponible();
        test.testGetIdFutbol();
        test.testSetIdFutbol();
        test.testGetTamano();
        test.testSetTamano();
        test.testGetPrecio();
        test.testSetPrecio();
        test.testIsDisponible();
        test.testSetDisponible();
        test.testToString();
    }

    public void testReservarCanchaDisponible() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        cancha.reservarCancha();
        assertCondition(!cancha.isDisponible(), "testReservarCanchaDisponible");
    }

    public void testReservarCanchaNoDisponible() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, false);
        cancha.reservarCancha();
        assertCondition(!cancha.isDisponible(), "testReservarCanchaNoDisponible");
    }

    public void testGetIdFutbol() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        assertCondition(cancha.getId_futbol() == 1, "testGetIdFutbol");
    }

    public void testSetIdFutbol() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        cancha.setId_futbol(2);
        assertCondition(cancha.getId_futbol() == 2, "testSetIdFutbol");
    }

    public void testGetTamano() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        assertCondition(cancha.getTamano() == 100, "testGetTamano");
    }

    public void testSetTamano() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        cancha.setTamano(200);
        assertCondition(cancha.getTamano() == 200, "testSetTamano");
    }

    public void testGetPrecio() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        assertCondition(cancha.getPrecio() == 50.0, "testGetPrecio");
    }

    public void testSetPrecio() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        cancha.setPrecio(75.0);
        assertCondition(cancha.getPrecio() == 75.0, "testSetPrecio");
    }

    public void testIsDisponible() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        assertCondition(cancha.isDisponible(), "testIsDisponible");
    }

    public void testSetDisponible() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        cancha.setDisponible(false);
        assertCondition(!cancha.isDisponible(), "testSetDisponible");
    }

    public void testToString() {
        CanchaFutbol cancha = new CanchaFutbol(1, 100, 50.0, true);
        String expected = "CanchaFutbol [id_futbol=1, tamano=100, precio=50.0, disponible=true]";
        assertCondition(cancha.toString().equals(expected), "testToString");
    }

    private void assertCondition(boolean condition, String testName) {
        if (condition) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " failed.");
        }
    }
}
