package tf;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {
	private int id;
	private LocalDateTime fechaPartida;
	private String destino;
	private LocalDateTime fechaHoraLlegada;
	private Omnibus omnibus;
	private Conductor conductor;
	private ArrayList<Reserva> reservas;
	
	public Viaje(int id, LocalDateTime fechaPartida, String destino, LocalDateTime horaLlegada, Omnibus omnibus, Conductor conductor){
		setId(id);
		setFechaPartida(fechaPartida);
		setDestino(destino);
		setHoraLlegada(horaLlegada);
		setOmnibus(omnibus);
		setConductor(conductor);
		reservas = new ArrayList<Reserva>();
	}
	
	private void setHoraLlegada(LocalDateTime horaLlegada) {
		this.fechaHoraLlegada = horaLlegada;
	}
	private LocalDateTime getHoraLlegada() {
		return fechaHoraLlegada;
	}
	public void setId(int id){
		this.id = id; 
	}
	public int getId(){
		return id;
	}
	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}
	public void setFechaPartida(LocalDateTime fechaPartida) {
		this.fechaPartida = fechaPartida;
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
