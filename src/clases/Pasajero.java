package clases;

import java.time.LocalDate;

import utilidades.Utilidades;

public class Pasajero {
	private String nombre;
	private String id;

	public Pasajero(String nombre, String id, LocalDate fecha){
		setNombre(nombre);
		setId(id, fecha);
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
