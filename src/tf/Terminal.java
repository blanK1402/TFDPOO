package tf;

import java.awt.TextArea;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Terminal {
	
	private String nombre;
	private ArrayList<Conductor> conductores;
	private ArrayList<Omnibus> omnibuses;
	private ArrayList<Viaje> viajes;
	private ArrayList<Reserva> reservas;
	private ArrayList<Pasajero> pasajeros;
	
	
	public Terminal(String nombre){
		setNombre(nombre);
		conductores = new ArrayList<Conductor>();
		omnibuses = new ArrayList<Omnibus>();
		viajes = new ArrayList<Viaje>();
		reservas = new ArrayList<Reserva>();
		pasajeros = new ArrayList<Pasajero>();
	}
	public ArrayList<Pasajero> getPasajeros(){
		return pasajeros;
	}
	public void addPasajero(Pasajero nuevoPasajero){
		int newId = nuevoPasajero.getId();
		int i = 0;
		while(pasajeros.get(i).getId() != newId){
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
	
	public void addReserva(Reserva reserva){
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
	
	public void calcular(TextArea texto){
	}
	
	public void addViaje(Viaje viaje){
		int newId = viaje.getId();
		int i = 0;
		while(i < viajes.size() && viajes.get(i).getId() != newId){
			i++;
		}
		if(i != viajes.size()){
			throw new IllegalArgumentException("Ya existe un viaje con ese ID");
		}
		viajes.add(viaje);
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
	
}
