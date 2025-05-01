package tf;

import java.util.ArrayList;

public class Omnibus {
	
	private String matricula;
	private int asientos;
	private ArrayList<String> comodidades;
	private String disponibilidad;
	private ArrayList<Conductor> conductores;
	
	public Omnibus(String matricula, int asientos, String disponibilidad){
		setMatricula(matricula);
		setAsientos(asientos);
		setDisponibilidad(disponibilidad);
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getAsientos() {
		return asientos;
	}
	public void setAsientos(int asientos) {
		this.asientos = asientos;
	}
	public ArrayList<String> getComodidades() {
		return comodidades;
	}
	public void setComodidades(ArrayList<String> comodidades) {
		this.comodidades = comodidades;
	}
	public String getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(String disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public ArrayList<Conductor> getConductores() {
		return conductores;
	}
	public void addConductor(Conductor conductor) {
		conductores.add(conductor);
	}
}
