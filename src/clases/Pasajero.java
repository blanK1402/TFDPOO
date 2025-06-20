package clases;

import Interfaces.Mostrable;

import java.time.LocalDate;
import java.util.ArrayList;

import utilidades.FechaUtils;
import utilidades.ValidacionUtils;

public class Pasajero implements Mostrable{
	private String nombre;
	private String id;
	private ArrayList<Reserva> reservas;

	public Pasajero(String nombre, String id, LocalDate fecha){
		setNombre(nombre);
		setId(id, fecha);
		reservas = new ArrayList<Reserva>();
	}
	
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}

	public void addReserva(Reserva r) {
		this.reservas.add(r);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws IllegalArgumentException{
		ValidacionUtils.validarNombre(nombre);
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id, LocalDate fecha) throws IllegalArgumentException{
		FechaUtils.validarCarnet(id, fecha);
		this.id = id;	
	}
	public void removeReserva(Reserva r){
		reservas.remove(r);
	}
	
	@Override
	public String toString(){
		return nombre + " id: " + id;
	}

	public String[] toTableList() {
		String[] res = {nombre, String.valueOf(id)};
		return res;
	}
}
