package clases;

import java.awt.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Omnibus {
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
	
	public boolean verificarDisponibilidad(LocalDateTime fechaSalida, LocalDateTime fechaLlegada){
		ArrayList<Viaje> viajesDia = viajes.get(fechaSalida.toLocalDate());
		int i = 0;
		boolean centinela = true;
		while(i < viajesDia.size() && centinela){
			Viaje viaje = viajesDia.get(i);
			if(!(viaje.getFechaHoraPartida().isAfter(fechaLlegada) || viaje.getFechaHoraLlegada().isBefore(fechaSalida))){
				centinela = false;
			}
		}
		return centinela;
	}

	public void setMatricula(String matricula) throws IllegalArgumentException{
		String mat = matricula.trim();
		if (mat.isEmpty()) {
			throw new IllegalArgumentException("La matrícula no puede estar vacía");
		}
		if (!mat.matches("^[A-Z]\\d{6}$")) {
			throw new IllegalArgumentException("La matrícula tiene formato LetraMayuscula######");
		}
		this.matricula = mat;
	}

	public void setAsientos(String asientos) throws IllegalArgumentException{
		if(asientos.trim().isEmpty()){
			throw new IllegalArgumentException("Escriba el número de asientos");
		}
		try {
			int num = Integer.parseInt(asientos.trim());
			if (num <= 0 || num > 100) {
				throw new IllegalArgumentException("Los asientos deben ser un número entre 1 y 100");
			}
			this.asientos = num;
		} catch (Exception e) {
			throw new IllegalArgumentException("Los asientos deben ser un número", e);
		}
	}

	public void setComodidades(ArrayList<String> comodidades) throws IllegalArgumentException{
		this.comodidades = comodidades;
	}

	public void setDisponibilidad(String disponibilidad) throws IllegalArgumentException{
		if(disponibilidad.isEmpty()){
			throw new IllegalArgumentException("Seleccione la disponibilidad");
		}
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