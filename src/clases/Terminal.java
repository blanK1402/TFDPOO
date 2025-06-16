package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import utilidades.Utilidades;
import login.Usuario;

public class Terminal {
	private static LocalDateTime fechaHora;
	private static final AtomicLong idReservas = new AtomicLong(1);
	private static final AtomicLong idViajes = new AtomicLong(1);
	private static final AtomicLong idConductores = new AtomicLong(1);
	private static HashMap<String, Integer> destinosDistancias;

	private String nombre;

	private static Terminal terminal;
	private static HashMap<String, Usuario> usuarios;
	private HashMap<String, Conductor> conductores;
	private HashMap<String, Pasajero> pasajeros;
	private HashMap<String, Omnibus> omnibuses;
	private HashMap<String, Viaje> viajes;
	private HashMap<String, Reserva> reservas;
	private HashMap<String, Reserva> reservasEspera;
	private HashMap<String, Reserva> reservasCanceladas;

	private Terminal(String nombre) {
		setNombre(nombre);
		fechaHora = LocalDateTime.now();
		usuarios = new HashMap<>();
		pasajeros = new HashMap<>();
		conductores = new HashMap<>();
		omnibuses = new HashMap<>();
		viajes = new HashMap<>();
		reservas = new HashMap<>();
		reservasEspera = new HashMap<>();
		reservasCanceladas = new HashMap<>();
		setDestinosDistancias();
	}

	public static Terminal getTerminal(){
		if(terminal == null){
			terminal = new Terminal("Terminal");
		}
		return terminal;
	}

	public static String getRandomDestino(){

		ArrayList<String> destinos = new ArrayList<>(destinosDistancias.keySet());
		return destinos.get((int) (Math.random() * destinos.size()));
	}

	private void setDestinosDistancias() {
		destinosDistancias = new HashMap<>();
		destinosDistancias.put("Pinar del Río", 169);
		destinosDistancias.put("Artemisa", 70);
		destinosDistancias.put("Mayabeque", 50);
		destinosDistancias.put("Matanzas", 104);
		destinosDistancias.put("Villa Clara", 267);
		destinosDistancias.put("Cienfuegos", 275);
		destinosDistancias.put("Sancti Spíritus", 359);
		destinosDistancias.put("Ciego de Ávila", 460);
		destinosDistancias.put("Camagüey", 540);
		destinosDistancias.put("Las Tunas", 662);
		destinosDistancias.put("Holguín", 689);
		destinosDistancias.put("Granma", 743);
		destinosDistancias.put("Santiago de Cuba", 860);
		destinosDistancias.put("Guantánamo", 905);
	}

	public static HashMap<String, Integer> getDestinosDistancias(){
		return destinosDistancias;
	}

	public static void setFechaHora(LocalDateTime nuevaFechaHora) {
		fechaHora = nuevaFechaHora;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static LocalDateTime getFecha() {
		return fechaHora;
	}

	public ArrayList<Integer> adelantarDia() {
		LocalDateTime nuevaFecha = fechaHora.plusDays(1);
		setFechaHora(nuevaFecha);
		LocalDate fechaActual = nuevaFecha.toLocalDate();

		ArrayList<Integer> reservasAEliminar = new ArrayList<>();

		for (Reserva r : reservas.values()) {
			if (r.getFechaDeseada().isEqual(fechaActual) || r.getFechaDeseada().isBefore(fechaActual)) {
				reservasAEliminar.add(r.getNumReserva());
				r.getPasajero().removeReserva(r);
			}
		}

		for (Integer numReserva : reservasAEliminar) {
			reservas.remove(numReserva);
		}

		return reservasAEliminar;
	}

	public ArrayList<Reserva> getReservas(){
		return new ArrayList<>(reservas.values());
	}

	public void adelantarHora() {
		setFechaHora(fechaHora.plusHours(1));
	}

	public Conductor getConductor(String id) {
		return conductores.get(id);
	}

	public Pasajero getPasajero(String usuario) {
		return pasajeros.get(usuario);
	}

	public ArrayList<Pasajero> getPasajeros() {
		return new ArrayList<>(pasajeros.values());
	}

	public ArrayList<Conductor> getConductores() {
		return new ArrayList<>(conductores.values());
	}

	public ArrayList<Omnibus> getOmnibuses() {
		return new ArrayList<>(omnibuses.values());
	}

	public ArrayList<Viaje> getViajes() {
		return new ArrayList<>(viajes.values());
	}

	public long getConductoresId() {
		long id = idConductores.getAndIncrement();
		return conductores.containsKey(String.valueOf(id)) ? getConductoresId() : id;
	}

	public long getIdReserva() {
		long id = idReservas.getAndIncrement();
		return reservas.containsKey(String.valueOf(id)) ? getIdReserva() : id;
	}

	public long getIdViaje() {
		long id = idViajes.getAndIncrement();
		return viajes.containsKey(String.valueOf(id)) ? getIdViaje() : id;
	}

	public void decrementIdViaje(){
		idViajes.getAndDecrement();
	}

	public void decrementIdConductor(){
		idConductores.getAndDecrement();
	}

	public void decrementIdReserva(){
		idReservas.getAndDecrement();
	}

	public void addOmnibus(Omnibus o) {
		omnibuses.put(o.getMatricula(), o);
	}

	public void addConductor(Conductor c) throws IllegalArgumentException{
		conductores.put(String.valueOf(c.getId()), c);
	}

	public void containsPasajeroId(String id){
		if(pasajeros.containsKey(id)){
			throw new IllegalArgumentException("Ya existe un pasajero con ese ID");
		}
	}

	public void addPasajero(Pasajero p) throws IllegalArgumentException{
		pasajeros.put(p.getId(), p);
		usuarios.put(p.getId(), new Usuario(p.getId(), "0000", "User"));
	}

	public void addViaje(Viaje v) {
		ArrayList<Reserva> porMover = new ArrayList<>();
		viajes.put(String.valueOf(v.getId()), v);
		v.getConductor().addViaje(v);
		v.getOmnibus().addViaje(v);

		for(Reserva r : reservasEspera.values()){
			if(r.getFechaDeseada().equals(v.getFechaHoraPartida().toLocalDate()) && r.getDestino().equals(v.getDestino())){
				if(v.getAsientosLibres().size() > 0){
					r.setViaje(v);
					porMover.add(r);
				}
			}
		}

		for(Reserva r : porMover){
			reservasEspera.remove(String.valueOf(r.getNumReserva()));
			reservas.put(String.valueOf(r.getNumReserva()), r);
		}
	}

	public void addReserva(Reserva r) {
		r.getPasajero().addReserva(r);
		if(r.getViaje() != null){
			r.getViaje().addReservas(r);
			reservas.put(String.valueOf(r.getNumReserva()), r);
		}
		else{			
			reservasEspera.put(String.valueOf(r.getNumReserva()), r);
		}
	}


	public HashMap<String, ArrayList<Viaje>> getDestinosViajes() {
		HashMap<String, ArrayList<Viaje>> destinosViajes = new HashMap<>();

		for(String destino : destinosDistancias.keySet()){
			destinosViajes.put(destino, new ArrayList<Viaje>());
		}

		for(Viaje v : viajes.values()){
			destinosViajes.get(v.getDestino()).add(v);
		}

		return destinosViajes;
	}

	public Omnibus getOmnibus(String matricula) {
		return omnibuses.get(matricula);
	}

	public Viaje getViaje(String id) {
		return viajes.get(id);
	}

	public ArrayList<Usuario> getUsuarios() {
		return new ArrayList<Usuario>(usuarios.values());
	}

	public ArrayList<Reserva> getReservasEspera() {
		return new ArrayList<>(reservasEspera.values());
	}

	public void containsOmnibusId(String matricula) {
		if(omnibuses.containsKey(matricula)){
			throw new IllegalArgumentException("Ya existe un ómnibus con esa matrícula");
		}
	}

	public ArrayList<Omnibus> getOmnibusesDisponibles() {
		ArrayList<Omnibus> disponibles = new ArrayList<>();

		for(Omnibus o : omnibuses.values()){
			if(!(o.getDisponibilidad().equals("En reparación"))){
				disponibles.add(o);
			}
		}

		return disponibles;
	}

	public void clear() {
		viajes.clear();
		pasajeros.clear();
		conductores.clear();
		omnibuses.clear();
		reservas.clear();
	}

	public void eliminarDuplicados() {
		for(Reserva r : reservas.values()){
			if(reservasEspera.containsKey(String.valueOf(r.getNumReserva()))){
				reservasEspera.remove(String.valueOf(r.getNumReserva()));
			}
		}
	}

	public void quitarConductor(String id) {

		Conductor conductorAEliminar = getConductor(id);

		for (Viaje viaje : conductorAEliminar.getViajes()) {
			Omnibus omnibus = getOmnibus(viaje.getOmnibus().getMatricula());

			if (omnibus.getConductores().size() == 1 && conductores.size() > 1) {
				Conductor reemplazo = getConductor(String.valueOf(encontrarConductor(conductorAEliminar)));
				if (reemplazo != null) {
					if(!(omnibus.getConductores().contains(reemplazo))){
						omnibus.addConductor(reemplazo);
					}
				}
			}

			omnibus.quitarConductor(conductorAEliminar);
			viaje.reasignarConductor();
		}

		conductores.remove(id);
	}

	private int encontrarConductor(Conductor conductorAEliminar) {
		ArrayList<Conductor> misConductores = new ArrayList<Conductor>(conductores.values());
		Conductor c = null;
		int i = 0;
		
		while(c == null && i < misConductores.size()){
			if(misConductores.get(i).getId() != conductorAEliminar.getId()){
				c = misConductores.get(i);
			}
			i++;
		}
		
		return c.getId();
	}

	public void removePasajero(String id) {
		Pasajero p = getPasajero(id);
		for(Reserva r : p.getReservas()){
			for(Viaje v : viajes.values()){
				if(v.getReservas().contains(r)){
					v.cancelarReserva(r);
				}
			}

			if(reservas.containsKey(String.valueOf(r.getNumReserva()))){
				reservas.remove(String.valueOf(r.getNumReserva()));
			}

			if(reservasEspera.containsKey(String.valueOf(r.getNumReserva()))){
				reservasEspera.remove(String.valueOf(r.getNumReserva()));
			}

			r.setEstado("Cancelada");
			reservasCanceladas.put(String.valueOf(r.getNumReserva()), r);
		}
		pasajeros.remove(p.getId());
	}

	public ArrayList<Reserva> getReservasCanceladas() {
		return new ArrayList<Reserva>(reservasCanceladas.values());
	}

	public void cancelarReserva(String id) {
		Reserva r = getReserva(id);

		for(Viaje v : viajes.values()){
			if(v.getReservas().contains(r)){
				v.cancelarReserva(r);
			}
		}

		if(reservas.containsKey(String.valueOf(r.getNumReserva()))){
			reservas.remove(String.valueOf(r.getNumReserva()));
		}

		if(reservasEspera.containsKey(String.valueOf(r.getNumReserva()))){
			reservasEspera.remove(String.valueOf(r.getNumReserva()));
		}

		r.setEstado("Cancelada");
		reservasCanceladas.put(String.valueOf(r.getNumReserva()), r);
	}

	private Reserva getReserva(String id) {
		return reservas.containsKey(id) ? reservas.get(id) : reservasEspera.containsKey(id) ? reservasEspera.get(id) : reservasCanceladas.get(id);
	}
}
