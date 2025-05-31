package clases;

import Interfaces.mostrable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import utilidades.Utilidades;

public class Omnibus implements mostrable{
	private String matricula;
	private int asientos;
	private ArrayList<String> comodidades;
	private String disponibilidad;
	private ArrayList<Conductor> conductores;
	private HashMap<LocalDate, ArrayList<Viaje>> viajes;

	public Omnibus(String matricula, String asientos, String disponibilidad, ArrayList<String> comodidades) {
		setMatricula(matricula);
		setAsientos(asientos);
		setDisponibilidad(disponibilidad);
		setComodidades(comodidades);
		conductores = new ArrayList<Conductor>();
	}

	public String getMatricula() {
		return matricula;
	}

	public int getAsientos() {
		return asientos;
	}

	public ArrayList<String> getComodidades() {
		return comodidades;
	}

	public String getDisponibilidad() {
		return disponibilidad;
	}

	public ArrayList<Conductor> getConductores() {
		return conductores;
	}

	public void setMatricula(String matricula) throws IllegalArgumentException{
		Utilidades.validarMatricula(matricula);
		this.matricula = matricula;
	}

	public void setAsientos(String asientos) throws IllegalArgumentException{
		this.asientos = Utilidades.validarAsientos(asientos);
	}

	public void setComodidades(ArrayList<String> comodidades) throws IllegalArgumentException{
		this.comodidades = comodidades;
	}

	public void setDisponibilidad(String disponibilidad){
		this.disponibilidad = disponibilidad;
	}

	public void addConductor(Conductor conductor) throws IllegalArgumentException{
		if(conductor != null){
			for (Conductor c : conductores) {
				if (c.getId() == conductor.getId()) {
					throw new IllegalArgumentException("No se pueden repetir conductores");
				}
			}
			conductores.add(conductor);
		}
	}
	public ArrayList<Integer> getAsientosList(){
		ArrayList<Integer> asientosList = new ArrayList<Integer>();
		int i = 1;
		while(i <= asientos){
			asientosList.add(i);
			i+=1;
		}
		return asientosList;
	}

	@Override
	public String toString(){
		return String.valueOf(matricula);
	}
	public String[] toTableList() {
		String[] res = {
				matricula,
				String.valueOf(asientos),
				comodidades.contains("Aire acondicionado") ? "Si" : "No",
				comodidades.contains("TV") ? "Si" : "No",
				comodidades.contains("Baño") ? "Si" : "No",
				disponibilidad,
				conductores.isEmpty() ? "Sin conductores" : conductores.toString()};
		return res;
	}
}