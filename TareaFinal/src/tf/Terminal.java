package tf;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Terminal {

	private String nombre;
	private static final String[] nombresList = {
		"Carlos", "Luis", "Ana", "Maria", "Pedro", "Sofia", "Javier", "Elena",
		"Fernando", "Gabriela", "Raúl", "Valentina", "Diego", "Camila", "Miguel",
		"Isabella", "Alejandro", "Lucía", "Eduardo", "Andrea", "Ricardo", "Paula",
		"Manuel", "Victoria", "David", "Beatriz", "Jorge", "Carla", "Cristian",
		"Daniela", "Sebastián", "Patricia", "Enrique", "Lorena", "Adrián", "Estefanía"
	};
	private HashMap<String, Integer> distancias;

	private LocalDateTime fechaHora;
	private ArrayList<Conductor> conductores;
	private ArrayList<Omnibus> omnibuses;
	private ArrayList<Viaje> viajes;
	private ArrayList<Reserva> reservas;
	private ArrayList<Pasajero> pasajeros;

	public Terminal(String nombre){
		setNombre(nombre);
		distancias = new HashMap<String, Integer>();
		distancias.put("Pinar del Río", 163);
		distancias.put("Artemisa", 67);
		distancias.put("Mayabeque", 56);
		distancias.put("Matanzas", 105);
		distancias.put("Villa Clara", 277);
		distancias.put("Cienfuegos", 254);
		distancias.put("Sancti Spíritus", 357);
		distancias.put("Ciego de Ávila", 421);
		distancias.put("Camagüey", 533);
		distancias.put("Las Tunas", 657);
		distancias.put("Holguín", 689);
		distancias.put("Granma", 713);
		distancias.put("Santiago de Cuba", 847);
		distancias.put("Guantánamo", 911);
		fechaHora = LocalDateTime.now();
		conductores = new ArrayList<Conductor>();
		omnibuses = new ArrayList<Omnibus>();
		viajes = new ArrayList<Viaje>();
		reservas = new ArrayList<Reserva>();
		pasajeros = new ArrayList<Pasajero>();
	}

	public int getDistancia(String destino){
		return distancias.get(destino);
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public String[] getNombresList(){
		return nombresList;
	}
	public HashMap<String, Integer> getDestinosDistancias(){
		return this.distancias;
	}
	public ArrayList<Pasajero> getPasajeros(){
		return pasajeros;
	}
	public void addPasajero(Pasajero nuevoPasajero){
		int newId = nuevoPasajero.getId();
		int i = 0;
		while(i < pasajeros.size() && pasajeros.get(i).getId() != newId){
			i++;
		}
		if(i != pasajeros.size()){
			throw new IllegalArgumentException("Ya existe un pasajero con ese ID");
		}
		pasajeros.add(nuevoPasajero);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public ArrayList<Reserva> getReservasEspera() {
		ArrayList<Reserva> enEspera = new ArrayList<Reserva>();
		for(Reserva reserva : reservas){
			if(reserva.getEstado().equals("Espera")){
				enEspera.add(reserva);
			}
		}
		return enEspera;
	}

	public void addReserva(Reserva reserva) throws IllegalArgumentException{
		int newId = reserva.getNumReserva();
		int i = 0;
		while(i < reservas.size() && reservas.get(i).getNumReserva() != newId){
			i++;
		}
		if(i != reservas.size()){
			throw new IllegalArgumentException("Ya existe una reserva con ese ID");
		}
		reservas.add(reserva);
	}
	public ArrayList<Conductor> getConductores() {
		return conductores;
	}
	public ArrayList<Omnibus> getOmnibuses() {
		return omnibuses;
	}
	public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void addOmnibus(Omnibus omnibus){
		int i = 0;
		while(i < omnibuses.size() && !omnibuses.get(i).getMatricula().equals(omnibus.getMatricula())){
			i++;
		}
		if(i != omnibuses.size()){
			throw new IllegalArgumentException("Ya existe un omnibus con esa matricula");
		}
		omnibuses.add(omnibus);
	}
	public void addViaje(Viaje viaje){
		String newId = String.valueOf(viaje.getId());
		int i = 0;
		while(i < viajes.size() && !(String.valueOf(viajes.get(i).getId()).equals(newId))){
			i++;
		}
		if(i != viajes.size()){
			throw new IllegalArgumentException("Ya existe un viaje con ese ID");
		}
		if(viaje.getFechaPartida().isBefore(fechaHora.toLocalDate())){
			throw new IllegalArgumentException("La fecha de partida debe ser mayor a la fecha actual");
		}
		viajes.add(viaje);
	}

	public String getReporteSalarios() {
		StringBuilder reporte = new StringBuilder();

		for (Conductor conductor : conductores) {
			reporte.append("Nombre: " + conductor.getNombre() + "|ID: " + conductor.getId());
			if (conductor instanceof ConductorA) {
				reporte.append("|Salario: " + ((ConductorA)conductor).calcularSalario());
			} else if (conductor instanceof ConductorB) {
				reporte.append("|Salario: " + ((ConductorB)conductor).calcularSalario());
			} else if (conductor instanceof ConductorC) {
				reporte.append("|Salario: " + ((ConductorC)conductor).calcularSalario());
			}
			reporte.append("\n");
		}

		return reporte.toString();
	}
	
	public String getReporteConductores() {
		StringBuilder reporte = new StringBuilder();

		for (Conductor conductor : conductores) {
			reporte.append("Nombre: " + conductor.getNombre() + "|ID: " + conductor.getId() + "|Experiencia(años): " + conductor.getExperiencia() + "|Licencia: " + conductor.getLicencia() + "\n");
		}

		return reporte.toString();
	}

	public String getReporteOmnibus() {
	    StringBuilder reporte = new StringBuilder();

	    for (Omnibus omnibus : omnibuses) {
	        reporte.append("Matrícula: ")
	        .append(omnibus.getMatricula())
	        .append("|Asientos: ")
	        .append(omnibus.getAsientos())
	        .append("|Comodidades: ")
	        .append(String.join(", ", omnibus.getComodidades()))
	        .append("|Disponibilidad: ")
	        .append(omnibus.getDisponibilidad()).append("\n");
	    }

	    return reporte.toString();
	}

	public LocalDateTime calcularFechaEstimada(String destino, LocalDateTime fechaPartida){
		return fechaPartida.plusHours(distancias.get(destino)/55);
	}
	
	public void addConductor(Conductor conductor){
		int newId = conductor.getId();
		int i = 0;
		while(i < conductores.size() && conductores.get(i).getId() != newId && conductores.get(i).getLicencia() != conductor.getLicencia()){
			i++;
		}
		if(i != conductores.size()){
			throw new IllegalArgumentException("Ya existe un conductor con ese ID o esa licencia");
		}
		conductores.add(conductor);
	}
	
	public String mejoresConductores() {
	    StringBuilder reporte = new StringBuilder();
	    ArrayList<Conductor> mejores = new ArrayList<Conductor>();
	    int max = -1;

	    for (Conductor c : conductores) {
	        int viajesC = c.getViajes().size();

	        if (viajesC > max) {
	            mejores.clear();
	            mejores.add(c);
	            max = viajesC;
	        } else if (viajesC == max) {
	            mejores.add(c);
	        }
	    }

	    for (Conductor mejor : mejores) {
	        reporte.append(mejor.getNombre()).append("|").append(mejor.getId()).append("|").append(mejor.getViajes().size()).append("\n");
	    }

	    return reporte.toString();
	}
	
	public ArrayList<String> getDestinos() {
	    return new ArrayList<String>(distancias.keySet());
	}

	public String getReportePasajeros() {
		StringBuilder reporte = new StringBuilder();

	    for (Pasajero p : pasajeros) {
	        reporte.append(p.getNombre() + "|" + p.getId() + "\n");
	    }

	    return reporte.toString();
	}

}
