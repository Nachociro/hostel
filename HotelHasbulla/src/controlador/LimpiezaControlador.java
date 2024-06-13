package controlador;

import java.util.List;

import testEma.LimpiezaHabitacion;
import usuario.Limpieza; // Asumiendo que esta es la clase correcta a importar

public class LimpiezaControlador {

    // Método para eliminar una limpieza por su ID
    public void deleteLimpieza(int idLimpieza) {
        // Aquí deberías escribir la lógica para eliminar una limpieza por su ID
        // Por ejemplo, si estás trabajando con una base de datos:
        // limpiezaDAO.deleteLimpieza(idLimpieza);
        // O si estás utilizando alguna otra forma de almacenamiento de datos:
        // limpiezaRepository.deleteLimpieza(idLimpieza);
        // O cualquier otra lógica que se ajuste a tu aplicación
    }

    // Método para obtener todas las limpiezas
    public List<LimpiezaHabitacion> getAllLimpiezas() {
        // Aquí deberías escribir la lógica para obtener todas las limpiezas
        // Por ejemplo, si estás trabajando con una base de datos:
        // return limpiezaDAO.getAllLimpiezas();
        // O si estás utilizando alguna otra forma de almacenamiento de datos:
        // return limpiezaRepository.getAllLimpiezas();
        // O cualquier otra lógica que se ajuste a tu aplicación
        return null; // Por ahora retornamos null ya que la implementación concreta depende de cómo accedas a los datos
    }
}
