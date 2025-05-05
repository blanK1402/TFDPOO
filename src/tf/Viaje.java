package tf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

public class Viaje {
	private String id;
	private int distancia;
	private LocalDate fechaPartida;
	private LocalTime horaPartida;
	private LocalDate fechaLlegada;
	private LocalTime horaLlegada;
	private String destino;
	private Omnibus omnibus;
	private Conductor conductor;
	private ArrayList<Reserva> reservas;
	
	public Viaje(String id, int distancia, String fechaPartida, String horaPartida, String destino, LocalDate fechaLlegada, LocalTime horaLlegada, Omnibus omnibus, Conductor conductor){
		setId(id);
		setDistancia(distancia);
		setFechaPartida(fechaPartida);
		setHoraPartida(horaPartida);
		setFechaLlegada(fechaLlegada);
		setHoraLlegada(horaLlegada);
		setDestino(destino);
		setOmnibus(omnibus);
		setConductor(conductor);
		reservas = new ArrayList<Reserva>();
	}
	
	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public LocalDate getFechaPartida() {
		return fechaPartida;
	}

	public void setFechaPartida(String fechaPartida) throws IllegalArgumentException{
		try{
			this.fechaPartida = LocalDate.parse(fechaPartida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de fecha salida incorrecto, fotmato esperado hh:mm:ss");
		}
		
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
		if(horaPartida.isAfter(LocalTime.of(18, 0))){
			importeTotal += 20;
		}
		
		return importeTotal;
	}
	public LocalTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) throws IllegalArgumentException{
		try{
			this.horaPartida = LocalTime.parse(horaPartida, DateTimeFormatter.ofPattern("HH:mm:ss"));
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de hora salida incorrecto, fotmato esperado hh:mm:ss");
		}
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
	
	public LocalDate getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(LocalDate fecha){
		this.fechaLlegada = fecha;
	}

	public LocalTime getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(LocalTime hora){
		this.horaLlegada = hora;
	}
	
	public void setId(String id2){
		this.id = id2; 
	}
	public String getId(){
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
	public void setReservas(ArrayList<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	@Override
	public String toString(){
		return destino + " " + fechaPartida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
