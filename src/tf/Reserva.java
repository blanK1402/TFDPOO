package tf;
import java.time.*;

public class Reserva {
	private Pasajero pasajero;
	private int numReserva;
	private String destino;
	private LocalDateTime fechaActual;
	private LocalDateTime fechaDeseada;
	private String estado;
	
	public Reserva(Pasajero pasajero, int numReserva, String destino, LocalDateTime fechaActual, LocalDateTime fechaDeseada){
		setPasajero(pasajero);
		setNumReserva(numReserva);
		setDestino(destino);
		setFechaActual(fechaActual);
		setFechaDeseada(fechaDeseada);
	}
	public Pasajero getPasajero() {
		return pasajero;
	}
	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}
	public int getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(int numReserva) {
		this.numReserva = numReserva;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getEstado() {
		return estado;
	}
	public LocalDateTime getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(LocalDateTime fechaActual) {
		this.fechaActual = fechaActual;
	}
	public LocalDateTime getFechaDeseada() {
		return fechaDeseada;
	}
	public void setFechaDeseada(LocalDateTime fechaDeseada) {
		this.fechaDeseada = fechaDeseada;
	}
	@Override
	public String toString(){
		return String.valueOf(numReserva);
	}
}
