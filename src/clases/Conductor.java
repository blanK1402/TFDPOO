package clases;

import Interfaces.Mostrable;
import java.util.ArrayList;

import utilidades.Utilidades;

public abstract class Conductor implements Mostrable {
    protected String nombre;
    protected int id;
    protected int experiencia;
    protected int licencia;
    protected ArrayList<Viaje> viajes;

    public Conductor(String nombre, String id, String experiencia, String licencia) {
        viajes = new ArrayList<>();
        setNombre(nombre);
        setId(id);
        setExperiencia(experiencia);
        setLicencia(licencia);
    }
    
    public ArrayList<Viaje> getViajes() {
        return new ArrayList<>(viajes);
    }
    
    public void addViaje(Viaje viaje) {
        viajes.add(viaje);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
		Utilidades.validarNombre(nombre);
		this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(String id) {
		Utilidades.validarNumeroPositivo(id, "El id");
		this.id = Integer.valueOf(id);
    }
    
    public int getExperiencia() {
        return experiencia;
    }
    
    public void setExperiencia(String experiencia) {
    	Utilidades.validarNumeroNoNegativo(experiencia, "La experiencia");
        this.experiencia = Integer.valueOf(experiencia);
    }
    
    public int getLicencia() {
        return licencia;
    }
    
    public void setLicencia(String licencia) {
    	Utilidades.validarLicencia(licencia, Terminal.getTerminal().getConductores());
        this.licencia = Integer.valueOf(licencia);
    }
    
    @Override
    public String toString() {
        return String.format("id:%d, %s", id, nombre);
    }
    
    public abstract String[] toTableList();
    
    public abstract double calcularSalario();
}