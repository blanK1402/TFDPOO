package clases;
import java.time.*;
import Interfaces.mostrable;
import java.time.format.DateTimeFormatter;

import Interfaces.mostrable;
import utilidades.Utilidades;

public class Reserva implements mostrable{
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
		setAsiento(asiento);
		setEstado(asiento);
		estado = "En espera";
		viaje = null;
	}
	
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
		estado = "Confirmada";
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
	public void setFechaDeseada(LocalDate fechaDeseada) {
		this.fechaDeseada = fechaDeseada;
	}
	@Override
	public String toString(){
		return String.valueOf(numReserva);
	}

	public String[] toTableList() {
        String[] res = {
        		String.valueOf(pasajero.toString()),
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
