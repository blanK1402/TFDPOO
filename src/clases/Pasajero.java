package clases;

import Interfaces.mostrable;
import java.time.LocalDate;
import java.util.ArrayList;

import Interfaces.mostrable;
import utilidades.Utilidades;

public class Pasajero implements mostrable{
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
		Utilidades.validarNombre(nombre);
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id, LocalDate fecha) throws IllegalArgumentException{
		Utilidades.validarCarnet(id, fecha);
		this.id = id;	
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
