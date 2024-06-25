package testLuca;

import modelo.CanchaTenis;

public class ReservaTenisTest {
	
	public static void main(String[] args) {
        
		ReservaTenisTest test = new ReservaTenisTest();
        test.testReservarCanchaDisponible();
        test.testReservarCanchaNoDisponible();
        test.testGetIdTenis();
        test.testSetIdTenis();
        test.testGetPiso();
        test.testSetPiso();
        test.testGetPrecio();
        test.testSetPrecio();
        test.testIsDisponible();
        test.testSetDisponible();
        test.testToString();
    }

    public void testReservarCanchaDisponible() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        cancha.reservarCancha();
        assertCondition(!cancha.isDisponible(), "testReservarCanchaDisponible");
    }

    public void testReservarCanchaNoDisponible() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, false);
        cancha.reservarCancha();
        assertCondition(!cancha.isDisponible(), "testReservarCanchaNoDisponible");
    }

    public void testGetIdTenis() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        assertCondition(cancha.getId_Tenis() == 1, "testGetIdTenis");
    }

    public void testSetIdTenis() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        cancha.setId_Tenis(2);
        assertCondition(cancha.getId_Tenis() == 2, "testSetIdTenis");
    }

    public void testGetPiso() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        assertCondition(cancha.getpiso().equals("Césped"), "testGetPiso");
    }

    public void testSetPiso() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        cancha.setpiso("Arcilla");
        assertCondition(cancha.getpiso().equals("Arcilla"), "testSetPiso");
    }

    public void testGetPrecio() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        assertCondition(cancha.getPrecio() == 50.0, "testGetPrecio");
    }

    public void testSetPrecio() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        cancha.setPrecio(75.0);
        assertCondition(cancha.getPrecio() == 75.0, "testSetPrecio");
    }

    public void testIsDisponible() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        assertCondition(cancha.isDisponible(), "testIsDisponible");
    }

    public void testSetDisponible() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        cancha.setDisponible(false);
        assertCondition(!cancha.isDisponible(), "testSetDisponible");
    }

    public void testToString() {
        CanchaTenis cancha = new CanchaTenis(1, "Césped", 50.0, true);
        String expected = "CanchaTenis [id_Tenis=1, piso=Césped, precio=50.0, disponible=true]";
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
