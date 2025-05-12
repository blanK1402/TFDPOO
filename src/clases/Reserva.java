package clases;
import java.time.*;
import java.time.format.DateTimeFormatter;

import utilidades.Utilidades;

public class Reserva {
	private Pasajero pasajero;
	private int numReserva;
	private String destino;
	private LocalDateTime fechaActual;
	private LocalDate fechaDeseada;
	private int asiento;
	private String estado;
	
	public Reserva(Pasajero pasajero, String numReserva, String destino, LocalDateTime localDateTime, LocalDate fechaDeseada, int asiento){
		setPasajero(pasajero);
		setNumReserva(numReserva);
		setDestino(destino);
		setFechaActual(localDateTime);
		setFechaDeseada(fechaDeseada);
		setAsiento(asiento);
		setEstado(asiento);
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
	public LocalDateTime getFechaActual() {
		return fechaActual;
	}
	public void setFechaActual(LocalDateTime localDateTime) {
		this.fechaActual = localDateTime;
	}
	public LocalDate getFechaDeseada() {
		return fechaDeseada;
	}
	public void setFechaDeseada(LocalDate fechaDeseada) {
		this.fechaDeseada = fechaDeseada;
	}
	@Override
	public String toString(){
		return String.valueOf(numReserva);
	}

	public String[] toTableList() {
        String[] res = {
        		String.valueOf(numReserva),
        		String.valueOf(asiento),
        		destino,
        		fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        		fechaDeseada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        		estado
        };
		return res;
    }
}
