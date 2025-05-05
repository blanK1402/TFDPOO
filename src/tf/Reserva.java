package tf;
import java.time.*;

public class Reserva {
	private Pasajero pasajero;
	private int numReserva;
	private String destino;
	private LocalDateTime fechaActual;
	private LocalDateTime fechaDeseada;
	private int asiento;
	private String estado;
	
	public Reserva(Pasajero pasajero, String numReserva, String destino, LocalDateTime localDateTime, LocalDateTime fechaDeseada, int asiento){
		setPasajero(pasajero);
		setNumReserva(numReserva);
		setDestino(destino);
		setFechaActual(localDateTime);
		setFechaDeseada(fechaDeseada);
		setAsiento(asiento);
	}
	
	public int getAsiento() {
		return asiento;
	}
	public void setAsiento(int asiento) {
		this.asiento = asiento;
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
	public void setNumReserva(String numReserva) throws IllegalArgumentException{
		try{
			int num = Integer.parseInt(numReserva);
			if(num <= 0){
				throw new IllegalArgumentException("El numero de reserva debe ser un numero valido mayor a 0");
			}
			this.numReserva = num;
		}catch(Exception e){
			throw new IllegalArgumentException("El numero de reserva debe ser un numero valido mayor a 0");
		}
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
	public void setFechaActual(LocalDateTime localDateTime) {
		this.fechaActual = localDateTime;
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
