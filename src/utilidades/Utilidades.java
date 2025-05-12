package utilidades;

import java.time.LocalDate;
import java.util.ArrayList;

import clases.Reserva;
import clases.Viaje;

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
    
    public static Viaje buscarViaje(ArrayList<Viaje> viajes, LocalDate fechaDeseada){
    	Viaje elViaje = null;
    	int i = 0;
    	while(i < viajes.size() && elViaje == null){
    		Viaje v = viajes.get(i);
    		if(v.getFechaHoraPartida().toLocalDate().equals(fechaDeseada) && v.getOmnibus().getDisponibilidad().equals("Disponible") && v.getAsientosLibres().size() > 0){
    			elViaje = viajes.get(i);
    		}
    		i++;
    	}
		return elViaje;
    }
}
