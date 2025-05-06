package tf;

import java.util.ArrayList;

public abstract class Conductor {
    protected String nombre;
    protected int id;
    protected int experiencia;
    protected int licencia;
    protected ArrayList<Viaje> viajes;
    
    public Conductor(String nombre, String id, String experiencia, String licencia) {
        this.viajes = new ArrayList<>();
        setNombre(nombre);
        setId(id);
        setExperiencia(experiencia);
        setLicencia(licencia);
    }
    
    private static void validateNombre(String nombre) {
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacÌo");
        }
        if (!nombre.trim().matches("^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios");
        }
    }
    
    private static int validatePositiveNumber(String value, String fieldName) {
        try {
            int num = Integer.parseInt(value.trim());
            if (num <= 0) {
                throw new IllegalArgumentException(fieldName + " debe ser positivo");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un n˙mero entero v·lido");
        }
    }
    
    private static int validateNonNegativeNumber(String value, String fieldName) {
        try {
            int num = Integer.parseInt(value.trim());
            if (num < 0) {
                throw new IllegalArgumentException(fieldName + " no puede ser negativo");
            }
            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " debe ser un n˙mero entero v·lido");
        }
    }
    
    public ArrayList<Viaje> getViajes() {
        return new ArrayList<>(viajes);
    }
    
    public void addViaje(Viaje viaje) {
        if (viaje != null) {
            viajes.add(viaje);
        }
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        validateNombre(nombre);
        this.nombre = nombre.trim();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = validatePositiveNumber(id, "El ID");
    }
    
    public int getExperiencia() {
        return experiencia;
    }
    
    public void setExperiencia(String experiencia) {
        this.experiencia = validateNonNegativeNumber(experiencia, "La experiencia");
    }
    
    public int getLicencia() {
        return licencia;
    }
    
    public void setLicencia(String licencia) {
        this.licencia = validatePositiveNumber(licencia, "La licencia");
    }
    
    @Override
    public String toString() {
        return String.format("%s id:%d", nombre, id);
    }
    
    public abstract double calcularSalario();
}