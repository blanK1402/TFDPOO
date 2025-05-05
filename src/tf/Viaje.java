package tf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Viaje {
	private String id;
	private int distancia;
	private LocalDate fechaPartida;
	private LocalTime HoraPartida;
	private LocalDate fechaLlegada;
	private LocalTime HoraLlegada;
	private String destino;
	private Omnibus omnibus;
	private Conductor conductor;
	private ArrayList<Reserva> reservas;
	
	public Viaje(String id, int distancia, String fechaPartida, String horaPartida, String destino, String fechaLlegada, String horaLlegada, Omnibus omnibus, Conductor conductor){
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

	public LocalTime getHoraPartida() {
		return HoraPartida;
	}

	public void setHoraPartida(String horaPartida) throws IllegalArgumentException{
		try{
			HoraPartida = LocalTime.parse(horaPartida, DateTimeFormatter.ofPattern("HH:mm:ss"));
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de hora salida incorrecto, fotmato esperado hh:mm:ss");
		}
	}

	public LocalDate getFechaLlegada() {
		return fechaLlegada;
	}

	public void setFechaLlegada(String fechaLlegada) throws IllegalArgumentException{
		try{
			this.fechaLlegada = LocalDate.parse(fechaLlegada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de fecha llegada incorrecto, fotmato esperado dd/mm/yyyy");
		}
		
	}

	public LocalTime getHoraLlegada() {
		return HoraLlegada;
	}

	public void setHoraLlegada(String horaLlegada) throws IllegalArgumentException{
		try{
			HoraLlegada = HoraPartida = LocalTime.parse(horaLlegada, DateTimeFormatter.ofPattern("HH:mm:ss"));
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de hora llegada incorrecto, fotmato esperado hh:mm:ss");
		}
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
	public String toString(){
		return String.valueOf(id);
	}
}
