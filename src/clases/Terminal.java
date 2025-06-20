package clases;

import gestion.GestorConductores;
import gestion.GestorOmnibus;
import gestion.GestorPasajeros;
import gestion.GestorReservas;
import gestion.GestorViajes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import login.Usuario;

public class Terminal {
    private static LocalDateTime fechaHora;
    private static Terminal terminal;
    
    private final GestorPasajeros gestorPasajeros = new GestorPasajeros();
    private final GestorConductores gestorConductores = new GestorConductores();
    private final GestorOmnibus gestorOmnibus = new GestorOmnibus();
    private final static GestorViajes gestorViajes = new GestorViajes();
    private final GestorReservas gestorReservas = new GestorReservas();
    
    private String nombre;
    private HashMap<String, Usuario> usuarios = new HashMap<>();

    private Terminal(String nombre) {
        setNombre(nombre);
        fechaHora = LocalDateTime.now();
    }

    public static Terminal getTerminal() {
        if (terminal == null) {
            terminal = new Terminal("Terminal");
        }
        return terminal;
    }

    public String getRandomDestino() {
        return gestorViajes.getRandomDestino();
    }

    public HashMap<String, Integer> getDestinosDistancias() {
        return gestorViajes.getDestinosDistancias();
    }

    public void setFechaHora(LocalDateTime nuevaFechaHora) {
        fechaHora = nuevaFechaHora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha() {
        return fechaHora;
    }

    public Conductor getConductor(String id) {
        return gestorConductores.getConductor(id);
    }

    public Pasajero getPasajero(String usuario) {
        return gestorPasajeros.getPasajero(usuario);
    }

    public ArrayList<Pasajero> getPasajeros() {
        return gestorPasajeros.getPasajeros();
    }

    public ArrayList<Conductor> getConductores() {
        return gestorConductores.getConductores();
    }

    public ArrayList<Omnibus> getOmnibuses() {
        return gestorOmnibus.getOmnibusesList();
    }

    public ArrayList<Viaje> getViajes() {
        return gestorViajes.getViajesList();
    }

    public void addOmnibus(Omnibus o) {
        gestorOmnibus.addOmnibus(o);
    }

    public void addConductor(Conductor c) {
        gestorConductores.addConductor(c);
    }

    public void addPasajero(Pasajero p) {
        gestorPasajeros.addPasajero(p);
        usuarios.put(p.getId(), new Usuario(p.getId(), "0000", "User"));
    }

    public void addViaje(Viaje v) {
        gestorViajes.addViaje(v);
    }

    public void addReserva(Reserva r) {
        gestorReservas.addReserva(r);
    }

    public HashMap<String, ArrayList<Viaje>> getDestinosViajes() {
        return gestorViajes.getDestinosViajes();
    }

    public Omnibus getOmnibus(String matricula) {
        return gestorOmnibus.getOmnibus(matricula);
    }

    public Viaje getViaje(String id) {
        return gestorViajes.getViaje(id);
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public ArrayList<Reserva> getReservasEspera() {
        return gestorReservas.getReservasEsperaList();
    }

    public ArrayList<Omnibus> getOmnibusesDisponibles() {
        return gestorOmnibus.getOmnibusesList();
    }

    public void clear() {
        gestorViajes.clear();
        gestorPasajeros.clear();
        gestorConductores.clear();
        gestorOmnibus.clear();
        gestorReservas.clear();
    }

    public void removeConductor(String id) {
        gestorConductores.removeConductor(id);
    }

    public void removePasajero(String id) {
        gestorPasajeros.removePasajero(id);
        usuarios.remove(id);
    }

    public ArrayList<Reserva> getReservasCanceladas() {
        return gestorReservas.getReservasCanceladasList();
    }

    public void cancelarReserva(String id) {
        gestorReservas.cancelarReserva(id);
    }

    public Reserva getReserva(String id) {
        return gestorReservas.getReserva(id);
    }

    public void removeOmnibus(Omnibus omnibus) {
        gestorOmnibus.removeOmnibus(omnibus.getMatricula());
    }

    public long getNextIdConductores(){
    	return gestorConductores.getNextId();
    }
    
    public long getNextIdReservas(){
    	return gestorReservas.getNextId();
    }
    
	public ArrayList<Reserva> getReservas() {
		return gestorReservas.getReservasList();
	}

	public long getNextIdViaje() {
		return gestorViajes.getNextId();
	}

	public void removeViaje(int id) {
		gestorViajes.removeViaje(String.valueOf(id));
	}

	public void containsOmnibusId(String matricula) {
		if(gestorOmnibus.getOmnibus(matricula) != null){
			throw new IllegalArgumentException("Ya existe un ómnibus con esa matrícula");
		}
	}

	public void containsPasajeroId(String carnet) {
		if(gestorPasajeros.getPasajero(carnet) != null){
			throw new IllegalArgumentException("Ya existe un pasajero con ese ID");
		}
	}
}