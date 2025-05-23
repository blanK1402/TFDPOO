package utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Pasajero;
import clases.Reserva;
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

	public static Pasajero crearPasajeroRandom(HashSet<String> pasajerosID){
		int tamanyo = Terminal.getListaNombres().size(); 
		String nombre = Terminal.getListaNombres().get((int) (Math.random() * tamanyo));
		String id = generarId(pasajerosID);

		return new Pasajero(nombre, id);
	}

	private static String generarUltimos(){
		String nums = "";
		for(int i = 0; i < 5; i++){
			nums = nums + (int)(Math.random() * 10);
		}
		return nums;
	}

	private static String generarId(HashSet<String> pasajerosID) {
		int anyo = (int) (Math.random() * 100);
		int month = (int) ((Math.random() * 12) + 1);
		int day = (int) (Math.random() * 28) + 1;

		String id = String.format("%02d%02d%02d", anyo, month, day) + generarUltimos();

		return pasajerosID.contains(id) ? generarId(pasajerosID) : id;
	}

	private static String generarLicencia(HashSet<Integer> pasajerosID) {
		int id = (int) (Math.random() * 100000);
		return pasajerosID.contains(id) ? generarLicencia(pasajerosID) : String.valueOf(id);
	}

	public static void validarCarnet(String carnet) throws IllegalArgumentException{
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

		int anyo = completarAnyo(carnet.substring(0,2));
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

	private static int completarAnyo(String digitos) {
		int anyo = Integer.valueOf(Terminal.getFechaHora().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy")).substring(2,4));
		return anyo - Integer.valueOf(digitos) < 0 ? Integer.valueOf("19" + digitos) : Integer.valueOf("20" + digitos);
	}

	public static Conductor crearConductorRandom(HashSet<Integer> conductoresLicencias){
		int tamanyo = Terminal.getListaNombres().size(); 
		String nombre = Terminal.getListaNombres().get((int) (Math.random() * tamanyo));
		String id = Terminal.getIdConductor();
		String licencia = generarLicencia(conductoresLicencias);
		String categoria = Arrays.asList("A", "B", "C").get((int) (Math.random() * 3));

		Conductor c;

		if(categoria.equals("A")){
			c = new ConductorA(nombre, id, String.valueOf((int) (Math.random() * 40)), licencia);
		}else if(categoria.equals("B")){
			c = new ConductorB(nombre, id, String.valueOf((int) (Math.random() * 40)), licencia);
		}else{
			c = new ConductorC(nombre, id, String.valueOf((int) (Math.random() * 40)), licencia);
		}

		return c;
	}

	public static Omnibus crearOmnibus(HashSet<String> OmnibusId, ArrayList<Conductor> conductores){
		String matricula = generarMatricula(OmnibusId);
		String estado = "Disponible";
		ArrayList<String> comodidades = new ArrayList<>();
		HashSet<Conductor> conductoresSet = new HashSet<>();
		int asientos = (int) (Math.random() * 80 + 21);

		if(asientos % 2 == 0){
			comodidades.add("TV");
			conductoresSet.add(conductores.get((int) (Math.random() * conductores.size())));
		}

		if(asientos % 3 == 0){
			comodidades.add("Baño");
			conductoresSet.add(conductores.get((int) (Math.random() * conductores.size())));
			estado = "En carretera";
		}
		if(asientos % 5 == 0){
			estado = "En reparación";
			comodidades.add("Aire acondicionado");
		}

		conductoresSet.add(conductores.get((int) (Math.random() * conductores.size())));

		Omnibus o = new Omnibus(matricula, String.valueOf(asientos), estado, comodidades);

		for(Conductor c : conductoresSet){
			o.addConductor(c);
		}

		return o;
	}

	public static String generarMatricula(HashSet<String> omnibusId){
		char letra = (char)((char)(Math.random() * 26) + 'A');
		String mat = letra + String.valueOf((int) (Math.random() * 900000) + 100000);
		return omnibusId.contains(mat) ? generarMatricula(omnibusId) : mat;
	}

	public static Viaje crearViajeRandom(ArrayList<Omnibus> Omnibus){
		String id = Terminal.getIdViaje();
		String destino = Terminal.getRandomDestino();
		Omnibus o = Omnibus.get((int) (Math.random() * Omnibus.size()));
		Conductor c = o.getConductores().get((int) (Math.random() * o.getConductores().size()));
		LocalDateTime fHS = LocalDateTime.now().plusDays((long) (Math.random() * 100)).plusHours((long) (Math.random()*10));
		return new Viaje(id, fHS.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), fHS.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), destino, o, c);
	} 

	public static Reserva crearReservaRandom(ArrayList<Pasajero> pasajeros, ArrayList<Viaje> viajes){
		Pasajero pasajero = pasajeros.get((int) (Math.random() * pasajeros.size()));
		Viaje reservaViaje = viajes.get((int) (Math.random() * viajes.size()));
		String numReserva = Terminal.getIdReserva();
		String destino = reservaViaje.getDestino();
		LocalDateTime fechaActual = Terminal.getFechaHora();
		LocalDate fechaDeseada = fechaActual.plusDays((long) (Math.random() * 101)).toLocalDate();
		int asiento = reservaViaje.getAsiento();

		Reserva r = new Reserva(pasajero, numReserva, destino, fechaActual, fechaDeseada, asiento);	
		r.setViaje(reservaViaje);
		return r;
	}
}
