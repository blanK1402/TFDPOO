package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
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

	public void addConductor(Conductor c) {
		conductores.put(String.valueOf(c.getId()), c);
	}

	public void addPasajero(Pasajero p) {
		pasajeros.put(p.getId(), p);
		usuarios.put(p.getId(), new Usuario(p.getId(), "0000", "User"));
	}

	public void addViaje(Viaje v) {
		viajes.put(String.valueOf(v.getId()), v);
		v.getConductor().addViaje(v);
		v.getOmnibus().addViaje(v);
	}

	public void addReserva(Reserva r) {
		r.getPasajero().addReserva(r);
		if(r.getViaje() != null){
			r.getViaje().addReservas(r);
		}
		reservas.put(String.valueOf(r.getNumReserva()), r);
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
}
