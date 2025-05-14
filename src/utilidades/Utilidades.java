package utilidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Pasajero;
import clases.Terminal;
import clases.Viaje;

public class Utilidades {
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

    public static Viaje buscarViaje(ArrayList<Viaje> viajes, LocalDate fechaDeseada){
        Viaje elViaje = null;
        int i = 0;
        while(i < viajes.size() && elViaje == null){
            Viaje v = viajes.get(i);
            if(v.getFechaHoraPartida().toLocalDate().equals(fechaDeseada) && v.getOmnibus().getDisponibilidad().trim().equals("Disponible") && v.getAsientosLibres().size() > 0){
                elViaje = viajes.get(i);
            }
            i++;
        }
        return elViaje;
    }

    public static void validarMatricula(String matricula) throws IllegalArgumentException{
        matricula = matricula.trim();
        if (matricula.isEmpty()) {
            throw new IllegalArgumentException("La matrícula no puede estar vacía.");
        }
        if (!matricula.matches("^[A-Z]\\d{6}$")) {
            throw new IllegalArgumentException("La matrícula debe tener el formato: Letra mayúscula seguida de 6 dígitos.");
        }
    }

    public static int validarAsientos(String asientos) throws IllegalArgumentException{
        asientos = asientos.trim();
        int num;
        if(asientos.isEmpty()){
            throw new IllegalArgumentException("Escriba el número de asientos.");
        }
        try {
            num = Integer.parseInt(asientos);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Los asientos deben ser un número entero válido.");
        }
        if (num <= 10 || num > 100) {
            throw new IllegalArgumentException("Los asientos deben ser un número entre 10 y 100.");
        }
        return num;
    }

    public static LocalDate parsearFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        fecha = fecha.trim();
        try {
            return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch(Exception e) {
            throw new IllegalArgumentException("Formato incorrecto de fecha, se espera el formato dd/MM/yyyy.");
        }
    }

    public static LocalTime parsearHora(String hora){
        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora no puede estar vacía.");
        }
        hora = hora.trim();
        try {
            return LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch(Exception e) {
            throw new IllegalArgumentException("Formato incorrecto de hora, se espera el formato HH:mm:ss.");
        }
    }
    
    public Pasajero crearPasajeroRandom(HashSet<Integer> pasajerosID){
    	int tamanyo = Terminal.getListaNombres().size(); 
    	String nombre = Terminal.getListaNombres().get((int) (Math.random() * tamanyo));
    	String id = generarId(pasajerosID);
    	
		return new Pasajero(id, nombre);
    }

	private String generarId(HashSet<Integer> pasajerosID) {
		int id = (int) (Math.random() * 100000);
		return pasajerosID.contains(id) ? generarId(pasajerosID) : String.valueOf(id);
	}
	
    public Conductor crearConductorRandom(HashSet<Integer> conductoresID, HashSet<Integer> conductoresLicencias){
    	int tamanyo = Terminal.getListaNombres().size(); 
    	String nombre = Terminal.getListaNombres().get((int) (Math.random() * tamanyo));
    	String id = generarId(conductoresID);
    	String licencia = generarId(conductoresLicencias);
    	String categoria = Arrays.asList("A", "B", "C").get((int) (Math.random() * 3));
    	
    	Conductor c;
    	
    	if(categoria.equals("A")){
    		c = new ConductorA(nombre, id, String.valueOf(((int)Math.random() * 40)), licencia);
    	}else if(categoria.equals("B")){
    		c = new ConductorB(nombre, id, String.valueOf(((int)Math.random() * 40)), licencia);
    	}else{
    		c = new ConductorC(nombre, id, String.valueOf(((int)Math.random() * 40)), licencia);
    	}
    	
		return c;
    }
    
}
