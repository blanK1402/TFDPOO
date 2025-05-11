package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import utilidades.Utilidades;

public class Viaje {
	private int id;
	private int distancia;
	private LocalDateTime fechaHoraPartida;
	private LocalDateTime fechaHoraLlegada;
	private String destino;
	private Omnibus omnibus;
	private Conductor conductor;
	private HashSet<Integer> asientosVendidos;
	private ArrayList<Reserva> reservas;
	
	public Viaje(String id, int distancia, String fechaPartida, String horaPartida, String fechaLlegada, String horaLlegada, String destino, Omnibus omnibus, Conductor conductor){
	    setId(id);
	    setDistancia(distancia);
	    setFechaHoraPartida(fechaPartida, horaPartida);
	    setFechaHoraLlegada(fechaLlegada, horaLlegada);
	    setDestino(destino);
	    setOmnibus(omnibus);
	    setConductor(conductor);
	    asientosVendidos = new HashSet<>();
	    reservas = new ArrayList<>();
	}

	public void setFechaHoraPartida(String fecha, String hora) throws IllegalArgumentException {
	    try {
	        LocalDate fechaPartida = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	        LocalTime horaPartida = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
	        this.fechaHoraPartida = LocalDateTime.of(fechaPartida, horaPartida);
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Formato incorrecto, se espera dd/MM/yyyy y HH:mm:ss");
	    }
	}

	
	public LocalDateTime getFechaHoraPartida() {
	    return fechaHoraPartida;
	}
	
	public LocalDateTime getFechaHoraLlegada() {
	    return fechaHoraLlegada;
	}
	
	public void setFechaHoraLlegada(String fecha, String hora) throws IllegalArgumentException {
	    try {
	        LocalDate fechaParseada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	        LocalTime horaParseada = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm:ss"));
	        this.fechaHoraLlegada = LocalDateTime.of(fechaParseada, horaParseada);
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Formato incorrecto, se espera dd/MM/yyyy y HH:mm:ss");
	    }
	}

	
	public int getDistancia() {
		return distancia;
	}
	
	public void desreservasAsiento(int asiento){
		asientosVendidos.remove(asiento);
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public float precio(){
		float importeTotal = (float) (distancia * 0.8);
		
		if(omnibus.getComodidades().contains("Aire acondicionado")){
			importeTotal += 15;
		}
		if(omnibus.getComodidades().contains("TV")){
			importeTotal += 5;
		}
		if(omnibus.getComodidades().contains("Baño")){
			importeTotal += 3;
		}
		if(fechaHoraPartida.toLocalTime().isAfter(LocalTime.of(18, 0))){
			importeTotal += 20;
		}
		
		return importeTotal;
	}

	public ArrayList<Integer> getAsientos(int n){
		ArrayList<Integer> ocupados = new ArrayList<Integer>();
		ArrayList<Integer> libres = new ArrayList<Integer>();
		for(Reserva r : reservas){
			ocupados.add(r.getAsiento());
		}
		for(int num : omnibus.getAsientosList()){
			if(!(ocupados.contains(num))){
				libres.add(num);
			}
		}
		return n == 1 ? ocupados : libres;
	}
	
	public void setId(String id) throws IllegalArgumentException{
		Utilidades.validarNumeroPositivo(id, "El id");
		this.id = Integer.parseInt(id); 
	}
	public int getId(){
		return id;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Omnibus getOmnibus() {
		return omnibus;
	}
	public void setOmnibus(Omnibus omnibus) {
		this.omnibus = omnibus;
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	public void addReservas(Reserva reserva) {
		if(!(reservas.contains(reserva))){			
			reservas.add(reserva);
		}
	}
	
	@Override
	public String toString(){
		return destino + " " + fechaHoraPartida.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	public String[] toTableList() {
        String[] res = {
                String.valueOf(id),
                destino,
                omnibus.toString(),
                conductor.toString(),
                fechaHoraPartida.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fechaHoraPartida.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
                fechaHoraLlegada.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fechaHoraLlegada.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
                String.valueOf(distancia),
                String.valueOf(reservas.size())
        };
		return res;
    }
}
