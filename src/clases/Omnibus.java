package clases;

import Interfaces.Mostrable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import utilidades.FechaUtils;
import utilidades.ValidacionUtils;

public class Omnibus implements Mostrable{
	private String matricula;
	private int asientos;
	private ArrayList<String> comodidades;
	private String disponibilidad;
	private ArrayList<Conductor> conductores;
	private HashMap<String, ArrayList<Viaje>> viajes;

	public Omnibus(String matricula, String asientos, String disponibilidad, ArrayList<String> comodidades) {
		setMatricula(matricula);
		setAsientos(asientos);
		setDisponibilidad(disponibilidad);
		setComodidades(comodidades);
		conductores = new ArrayList<Conductor>();
		viajes = new HashMap<>();
	}

	public void addViaje(Viaje v){
		String fecha = v.getFechaHoraPartida().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		if (viajes.containsKey(fecha)) {
			viajes.get(fecha).add(v);
		} 
		else {
			ArrayList<Viaje> nuevaLista = new ArrayList<>();
			nuevaLista.add(v);
			viajes.put(fecha, nuevaLista);
		}
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

	public ArrayList<Viaje> getViajes(){
		ArrayList<Viaje> misViajes = new ArrayList<>();
		for(ArrayList<Viaje> vs : viajes.values()){
			misViajes.addAll(vs);
		}
		return misViajes;
	}
	
	public void setMatricula(String matricula) throws IllegalArgumentException{
		ValidacionUtils.validarMatricula(matricula);
		this.matricula = matricula;
	}

	public void setAsientos(String asientos) throws IllegalArgumentException{
		this.asientos = ValidacionUtils.validarAsientos(asientos);
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

	public LocalTime getHoraSalida(String fechaSalida) {
	    LocalTime hora = FechaUtils.parsearHora("01:00:00");
	    ArrayList<Viaje> viajesDia = viajes.get(fechaSalida);

	    if (viajesDia != null && !viajesDia.isEmpty()) {
	        Viaje v = viajesDia.get(viajesDia.size() - 1);
	        if (v.getFechaHoraLlegada() != null && v.getFechaHoraPartida() != null) {
	            hora = v.getFechaHoraLlegada().toLocalTime().plusMinutes(
	                (long) ((v.calcularTiempo(v.getDistancia()) + 1.5) * 60)
	            );

	            if (hora.isBefore(v.getFechaHoraPartida().toLocalTime())) {
	                hora = null;
	            }
	        } else {
	            hora = null;
	        }
	    }

	    return hora;
	}

	public void removeViaje(String fechaSalida) {
	    ArrayList<Viaje> lista = viajes.get(fechaSalida);
	    if (lista != null && !lista.isEmpty()) {
	        lista.remove(lista.size() - 1);
	    }
	}

	public void quitarConductor(Conductor conductorAEliminar) {
	    int i = 0;
	    boolean encontrado = false;
	    
	    while(i < conductores.size() && !encontrado) {
	        if(conductores.get(i).getId() == conductorAEliminar.getId()) {
	            conductores.remove(i);
	            encontrado = true;
	        } else {
	            i++;
	        }
	    }
	}



}