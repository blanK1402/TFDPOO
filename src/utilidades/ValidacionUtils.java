package utilidades;

import java.time.LocalDate;
import java.util.List;
import clases.Conductor;

public final class ValidacionUtils {

    private ValidacionUtils() {}

    public static void validarNombre(String nombre) {
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        }
    }

    public static int validarNumeroPositivo(String value, String fieldName) {
        value = value.trim();
        try {
            int num = Integer.parseInt(value);
            if (num <= 0) {
                throw new IllegalArgumentException(fieldName + " debe ser un número positivo.");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un número entero válido.");
        }
    }

    public static int validarNumeroNoNegativo(String value, String fieldName) {
        value = value.trim();
        try {
            int num = Integer.parseInt(value);
            if (num < 0) {
                throw new IllegalArgumentException(fieldName + " no puede ser negativo.");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un número entero válido.");
        }
    }

    public static void validarMatricula(String matricula) {
        matricula = matricula.trim();
        if (matricula.isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        if (!matricula.matches("^[A-Z]\\d{6}$")) {
            throw new IllegalArgumentException("La matrícula debe tener el formato: Letra mayúscula seguida de 6 dígitos.");
        }
    }

    public static int validarAsientos(String asientos) {
        asientos = asientos.trim();
        int num;
        if (asientos.isEmpty()) {
            throw new IllegalArgumentException("Escriba el número de asientos.");
        }
        try {
            num = Integer.parseInt(asientos);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los asientos deben ser un número entero válido.");
        }
        if (num < 10 || num > 160) {
            throw new IllegalArgumentException("Los asientos deben ser un número entre 10 y 160.");
        }
        return num;
    }

    public static void validarLicencia(String licencia, List<Conductor> conductores) {
        if (!licencia.matches("^[0-9]{5}$")) {
            throw new IllegalArgumentException("La licencia debe ser un número entero positivo válido de 5 dígitos");
        }
        for (Conductor c : conductores) {
            if (String.valueOf(c.getLicencia()).equals(licencia)) {
                throw new IllegalArgumentException("Ya existe un conductor con esa licencia");
            }
        }
    }

    public static void validarFecha(LocalDate fechaDeseada) {
        if (fechaDeseada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha debe ser mayor a la fecha actual");
        }
    }
}