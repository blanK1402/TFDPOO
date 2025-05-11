package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Terminal {

	private String nombre;
	private static LocalDateTime fechaHora;
	private ArrayList<Conductor> conductores;
	private HashSet<Integer> conductoresID;
	private HashSet<Integer> conductoresLicencias;
	private ArrayList<Omnibus> omnibuses;
	private HashSet<String> omnibusID;
	private static HashMap<String, ArrayList<Viaje>> viajes;
	private HashSet<Integer> viajesID;
	private ArrayList<Reserva> reservas;
	private ArrayList<Reserva> reservasEspera;
	private HashSet<Integer> reservasID;
	private ArrayList<Pasajero> pasajeros;
	private HashSet<Integer> pasajerosID;
	private HashMap<LocalDate, HashMap<Omnibus, Integer>> registro;

	public Terminal(String nombre){
		setNombre(nombre);
		fechaHora = LocalDateTime.now();
		conductores = new ArrayList<Conductor>();
		omnibuses = new ArrayList<Omnibus>();
		viajes = new HashMap<String, ArrayList<Viaje>>();
		reservas = new ArrayList<Reserva>();
		pasajeros = new ArrayList<Pasajero>();
		registro = new HashMap<>();
		reservasEspera = new ArrayList<>();
		conductoresID = new HashSet<Integer>();
		pasajerosID = new HashSet<Integer>();
		reservasID = new HashSet<Integer>();
		omnibusID = new HashSet<String>();
		viajesID = new HashSet<Integer>();
		conductoresLicencias = new HashSet<Integer>();
	}

	public static LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	public ArrayList<Reserva> getReservasEspera(){
		return reservasEspera;
	}
	
	public static void setFechaHora(LocalDateTime nuevaFechaHora) {
		fechaHora = nuevaFechaHora;
	}

	public void addRegistro(LocalDate fecha, Omnibus omnibus, int capacidadesVendidas){
		HashMap<Omnibus, Integer> par = new HashMap<Omnibus, Integer>();
		par.put(omnibus, capacidadesVendidas);
		registro.put(fecha, par);
	}
	
	public ArrayList<Pasajero> getPasajeros(){
		return pasajeros;
	}
	
	public void addPasajero(Pasajero nuevoPasajero) throws IllegalArgumentException{
		if(pasajerosID.contains(nuevoPasajero.getId())){
			throw new IllegalArgumentException("Ya existe un pasajero con ese ID");
		}
		pasajerosID.add(nuevoPasajero.getId());
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

	public void addReserva(Reserva reserva) throws IllegalArgumentException{
		if(reservasID.contains(reserva.getNumReserva())){
			throw new IllegalArgumentException("Ya existe una reserva con ese número");
		}
		reservasID.add(reserva.getNumReserva());
		if(reserva.getEstado().equals("Confirmada")){
			reservas.add(reserva);
		}
		else{
			reservasEspera.add(reserva);
		}
	}
	
	public ArrayList<Conductor> getConductores() {
		return conductores;
	}
	
	public ArrayList<Omnibus> getOmnibus() {
		return omnibuses;
	}
	
	public ArrayList<Viaje> getViajes() {
		ArrayList<Viaje> todosViajes = new ArrayList<Viaje>();
		for(ArrayList<Viaje> viajesList : viajes.values()){
			for(Viaje v : viajesList){
				todosViajes.add(v);
			}
		}
		return todosViajes;
	}

	public void addOmnibus(Omnibus omnibus){
		if(omnibusID.contains(omnibus.getMatricula())){
			throw new IllegalArgumentException("Ya existe un omnibus con esa matrícula");
		}
		omnibusID.add(omnibus.getMatricula());
		omnibuses.add(omnibus);
	}
	
	public void addViaje(Viaje viaje){
		if(viajesID.contains(viaje.getId())){
			throw new IllegalArgumentException("Ya existe un viaje con ese ID");
		}
		if(viaje.getFechaHoraPartida().toLocalDate().isBefore(fechaHora.toLocalDate())){
			throw new IllegalArgumentException("La fecha de partida debe ser mayor a la fecha actual");
		}
		
		viajesID.add(viaje.getId());
		viajes.get(viaje.getDestino()).add(viaje);
	}

	public void addConductor(Conductor conductor){
		if(conductoresID.contains(conductor.getId())){
			throw new IllegalArgumentException("Ya existe un conductor con ese ID");
		}
		else if(conductoresLicencias.contains(conductor.getLicencia())){
			throw new IllegalArgumentException("Ya existe un conductor con esa licencia");
		}
		conductoresID.add(conductor.getId());
		conductoresLicencias.add(conductor.getLicencia());
		conductores.add(conductor);
	}

	public static LocalDateTime getFecha() {
		return fechaHora;
	}

	public static Viaje getViaje(String destinoSeleccionado, LocalDate fechaDeseada) {
		ArrayList<Viaje> listaViajes = viajes.get(destinoSeleccionado);
		Viaje elViaje = null;
		int i = 0;
		while(i < listaViajes.size() && elViaje == null){
			if(listaViajes.get(i).getFechaHoraPartida().toLocalDate().equals(fechaDeseada) && listaViajes.get(i).getAsientos(0).size() > 0){
				elViaje = listaViajes.get(i);
			}
		}
		return elViaje;
	}
	
}
