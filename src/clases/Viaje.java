package clases;

import Interfaces.Mostrable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import utilidades.Utilidades;

public class Viaje implements Mostrable{
	private int id;
	private int distancia;
	private LocalDateTime fechaHoraPartida;
	private LocalDateTime fechaHoraLlegada;
	private String destino;
	private Omnibus omnibus;
	private Conductor conductor;
	private HashSet<Integer> asientosVendidos;
	private HashSet<Integer> asientosLibres;
	private ArrayList<Reserva> reservas;

	public Viaje(String id, String fechaPartida, String horaPartida, String destino, Omnibus omnibus, Conductor conductor){
		setId(id);
		setDistancia(Terminal.getDestinosDistancias().get(destino));
		setFechaHoraPartida(fechaPartida, horaPartida);
		setFechaHoraLlegada(fechaPartida, horaPartida, distancia);
		setDestino(destino);
		setOmnibus(omnibus);
		setConductor(conductor);
		asientosVendidos = new HashSet<>();
		asientosLibres = new HashSet<>(omnibus.getAsientosList());
		reservas = new ArrayList<>();
	}

	public void setFechaHoraPartida(String fecha, String hora) throws IllegalArgumentException {
		this.fechaHoraPartida = LocalDateTime.of(Utilidades.parsearFecha(fecha), Utilidades.parsearHora(hora));
	}
	
	public LocalDateTime getFechaHoraPartida() {
		return fechaHoraPartida;
	}

	public LocalDateTime getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public int getDistancia() {
		return distancia;
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

	private LocalDateTime calcularLlegada(String fechaSalida, String horaSalida, int distancia) throws IllegalArgumentException{
		try{
			return Utilidades.parsearFecha(fechaSalida).atTime(Utilidades.parsearHora(horaSalida)).plusHours(distancia/55);
		}catch(Exception e){
			throw new IllegalArgumentException("Formato de fecha invalido");
		}
	}
	
	public void setFechaHoraLlegada(String fecha, String hora, int distancia) throws IllegalArgumentException {
		this.fechaHoraLlegada = calcularLlegada(fecha, hora, distancia);
	}
	
	public HashSet<Integer> getAsientosLibres(){
		return asientosLibres;
	}
	public HashSet<Integer> getAsientosVendidos(){
		return asientosVendidos;
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
		reservas.add(reserva);
		asientosLibres.remove(reserva.getAsiento());
		asientosVendidos.add(reserva.getAsiento());
	}
	public Reserva cancelarReserva(Reserva r){
		reservas.remove(r);
		asientosVendidos.remove(r.getAsiento());
		asientosLibres.add(r.getAsiento());
		return r;
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
				fechaHoraPartida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm:ss")),
				String.valueOf(precio())
		};
		return res;
	}

	public int getAsiento() {
		return asientosLibres.iterator().next();
	}
}
