package utilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import clases.Terminal;

public final class FechaUtils {

    private FechaUtils() {} 
    
    private static final DateTimeFormatter FECHA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter HORA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static LocalDate parsearFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        fecha = fecha.trim();
        try {
            return LocalDate.parse(fecha, FECHA_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato incorrecto de fecha, se espera el formato dd/MM/yyyy.");
        }
    }

    public static LocalTime parsearHora(String hora) {
        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora no puede estar vacía.");
        }
        hora = hora.trim();
        try {
            return LocalTime.parse(hora, HORA_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato incorrecto de hora, se espera el formato HH:mm:ss.");
        }
    }

    public static void validarCarnet(String carnet, LocalDate fechaActual) {
    	ArrayList<Integer> meses30 = new ArrayList<Integer>(Arrays.asList(4, 6, 9, 11));
    	ArrayList<Integer> meses31 = new ArrayList<Integer>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));

        int anyo = completarAnyo(carnet.substring(0, 2), fechaActual);
        int mes = Integer.parseInt(carnet.substring(2, 4));
        int dia = Integer.parseInt(carnet.substring(4, 6));

        Terminal.getTerminal().containsPasajeroId(carnet);
        
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("El mes debe estar entre 1 y 12");
        }

        if (meses31.contains(mes)) {
            if (dia < 1 || dia > 31) {
                throw new IllegalArgumentException("Día inválido para mes de 31 días. Debe estar entre 1 y 31");
            }
        } else if (meses30.contains(mes)) {
            if (dia < 1 || dia > 30) {
                throw new IllegalArgumentException("Día inválido para mes de 30 días. Debe estar entre 1 y 30");
            }
        } else {
            int maxDias = bisiesto(anyo) ? 29 : 28;
            if (dia < 1 || dia > maxDias) {
                throw new IllegalArgumentException("Día inválido para febrero. Debe estar entre 1 y " + maxDias);
            }
        }
    }

    private static boolean bisiesto(int anyo) {
        return (anyo % 4 == 0 && anyo % 100 != 0) || (anyo % 400 == 0);
    }

    private static int completarAnyo(String digitos, LocalDate fechaActual) {
        int anyoActual = fechaActual.getYear();
        int anyoDosDigitos = Integer.parseInt(digitos);
        return (anyoActual % 100 - anyoDosDigitos < 0) ? 
            1900 + anyoDosDigitos : 2000 + anyoDosDigitos;
    }
}