package testEma;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import usuario.LimpiezaHabitacion;

import java.time.LocalDate;
import java.time.LocalTime;

class LimpiezaHabitacionTest {

    @Test
    void testValidarFechaYHora() {
        LimpiezaHabitacion limpiezaHabitacion = new LimpiezaHabitacion(1, 101, LocalDate.now().plusDays(1), LocalTime.now().plusHours(1).toString());
        assertTrue(limpiezaHabitacion.validarFechaYHora(), "La fecha y hora deben ser futuras");
    }

    @Test
    void testTipoLimpieza() {
        LimpiezaHabitacion limpiezaHabitacion = new LimpiezaHabitacion(1, 101, LocalDate.now().plusDays(1), LocalTime.now().plusHours(1).toString());
        assertEquals("Limpieza Definitiva", limpiezaHabitacion.tipoLimpieza(), "La limpieza debe ser definitiva por defecto");
    }

    @Test
    void testDuracionLimpieza() {
        LimpiezaHabitacion limpiezaHabitacionRutina = new LimpiezaHabitacion(1, 101, LocalDate.now().plusDays(1), LocalTime.now().plusHours(1).toString()) {
            @Override
            public String tipoLimpieza() {
                return "Limpieza de Rutina";
            }
        };
        assertDoesNotThrow(() -> limpiezaHabitacionRutina.duracionLimpieza(), "No debe lanzar excepción para limpieza de rutina");

        LimpiezaHabitacion limpiezaHabitacionDefinitiva = new LimpiezaHabitacion(1, 101, LocalDate.now().plusDays(1), LocalTime.now().plusHours(1).toString());
        assertDoesNotThrow(() -> limpiezaHabitacionDefinitiva.duracionLimpieza(), "No debe lanzar excepción para limpieza definitiva");
    }
}
