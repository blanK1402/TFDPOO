package utilidades;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import login.Usuario;
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

	public static Viaje buscarViaje(String destino, LocalDate fechaDeseada){
		Viaje elViaje = null;
		ArrayList<Viaje> viajes = Terminal.getTerminal().getDestinosViajes().get(destino);
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

	public static void validarCarnet(String carnet, LocalDate fecha) throws IllegalArgumentException{
		ArrayList<Integer> meses30 = new ArrayList<>();
		meses30.add(4);
		meses30.add(6);
		meses30.add(9);
		meses30.add(11);
		ArrayList<Integer> meses31 = new ArrayList<>();
		meses31.add(1);
		meses31.add(3);
		meses31.add(5);
		meses31.add(7);
		meses31.add(8);
		meses31.add(10);
		meses31.add(12);

		int anyo = completarAnyo(carnet.substring(0,2), fecha);
		int terceroCuarto = Integer.valueOf(carnet.substring(2, 4));
		int quintoSexto = Integer.valueOf(carnet.substring(4, 6));

		if (terceroCuarto < 1 || terceroCuarto > 12) {
			throw new IllegalArgumentException("El mes debe estar entre 1 y 12");
		}

		if (meses31.contains(terceroCuarto)) {
			if (quintoSexto < 1 || quintoSexto > 31) {
				throw new IllegalArgumentException("Día inválido para mes de 31 días. Debe estar entre 1 y 31");
			}
		} else if (meses30.contains(terceroCuarto)) {
			if (quintoSexto < 1 || quintoSexto > 30) {
				throw new IllegalArgumentException("Día inválido para mes de 30 días. Debe estar entre 1 y 30");
			}
		} else {
			int maxDias = bisiesto(anyo) ? 29 : 28;
			if (quintoSexto < 1 || quintoSexto > maxDias) {
				throw new IllegalArgumentException("Día inválido para febrero. Debe estar entre 1 y " + maxDias);
			}
		}
	}

	private static boolean bisiesto(int anyo) {
		return (anyo % 4 == 0 && anyo % 100 != 0) || (anyo % 400 == 0);
	}

	private static int completarAnyo(String digitos, LocalDate fecha) {
		int anyo = Integer.valueOf(fecha.format(DateTimeFormatter.ofPattern("yyyy")).substring(2,4));
		return anyo - Integer.valueOf(digitos) < 0 ? Integer.valueOf("19" + digitos) : Integer.valueOf("20" + digitos);
	}


	public static Usuario login(ArrayList<String> usuarioContrasena) throws IllegalArgumentException, IOException{
		HashMap<String, Usuario> contrasenas = new HashMap<>();
		String usuario = usuarioContrasena.get(0);
		String contrasena = usuarioContrasena.get(1);
		
		Datos.cargarContrasenas(contrasenas);
		contrasenas.put("Admin", new Usuario("Admin", "1234", "Admin"));

		if(contrasenas.containsKey(usuario) && contrasenas.get(usuario).getContraseña().equals(contrasena)){
			return contrasenas.get(usuario);
		}
		else{
			throw new IllegalArgumentException("Datos incorrectos");
		}

	}
}