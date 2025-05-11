package utilidades;

import java.util.ArrayList;

public class Utilidades {

    public static void validarNombre(String nombre) {
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
    }

    public static int validarNumeroPositivo(String value, String fieldName) {
        try {
            int num = Integer.parseInt(value.trim());
            if (num <= 0) {
                throw new IllegalArgumentException(fieldName + " debe ser un número positivo.");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un número entero válido.");
        }
    }

    public static int validarNumeroNoNegativo(String value, String fieldName) {
        try {
            int num = Integer.parseInt(value.trim());
            if (num < 0) {
                throw new IllegalArgumentException(fieldName + " no puede ser negativo.");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un número entero válido.");
        }
    }
}
