package clases;
import java.time.*;

import Interfaces.Mostrable;

import java.time.format.DateTimeFormatter;

public class Reserva implements Mostrable{
	private Pasajero pasajero;
	private int numReserva;
	private String destino;
	private LocalDate fechaActual;
	private LocalDate fechaDeseada;
	private int asiento;
	private String estado;
	private Viaje viaje;

	public Reserva(Pasajero pasajero, String numReserva, String destino, LocalDate fecha, LocalDate fechaDeseada, int asiento){
		setPasajero(pasajero);
		setNumReserva(numReserva);
		setDestino(destino);
		setFechaActual(fecha);
		setFechaDeseada(fechaDeseada);
		setEstado(asiento);
		viaje = null;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
		estado = viaje != null ? "Confirmada" : "En espera";
		asiento = viaje != null ? viaje.getAsiento() : 0;
	}
	public Viaje getViaje(){
		return viaje;
	}

	private void setEstado(int asientoReserva) {
		this.estado = asientoReserva == 0 ? "En espera" : "Confirmada";
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
		this.numReserva = Integer.valueOf(numReserva);
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
	public LocalDate getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(LocalDate fecha) {
		this.fechaActual = fecha;
	}
	public LocalDate getFechaDeseada() {
		return fechaDeseada;
	}
	public float cancelarReserva() {
		float devolucion = 0;
		
		if(viaje != null){
			LocalDateTime ahora = Terminal.getTerminal().getFecha();
		    LocalDateTime salida = viaje.getFechaHoraPartida();

		    long horasDiferencia = java.time.Duration.between(ahora, salida).toHours();

		    float precio = viaje.precio();
		    
		    if (horasDiferencia >= 48) {
		        devolucion = precio;
		    } else if (horasDiferencia >= 24) {
		        devolucion = precio * 0.5f;
		    } 
		}
	    return devolucion;	    
	}

	public void setFechaDeseada(LocalDate fechaDeseada) {
		this.fechaDeseada = fechaDeseada;
		if(fechaDeseada.isBefore(Terminal.getTerminal().getFecha().toLocalDate())){
			throw new IllegalArgumentException("Debe ser una fecha mayor a la fecha actual");
		}
	}
	@Override
	public String toString(){
		return String.valueOf(numReserva);
	}

	public String[] toTableList() {
		String[] res = {
				estado.equals("Cancelada") ? "null" : String.valueOf(pasajero.toString()),
				String.valueOf(numReserva),
				estado.equals("Confirmada") ? String.valueOf(viaje.getId()) : "Sin viaje",
						estado.equals("Confirmada") ? String.valueOf(asiento) : "None",
								destino,
								fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
								fechaDeseada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
								estado
		};
		return res;
	}
}
