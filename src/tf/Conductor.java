package tf;

import java.util.ArrayList;

public abstract class Conductor {
    protected String nombre;
    protected int id;
    protected int experiencia;
    protected int licencia;
    protected ArrayList<Viaje> viajes;
    
    public Conductor(String nombre, String id, String experiencia, String licencia) {
    	viajes = new ArrayList();
        setNombre(nombre);
        setId(id);
        setExperiencia(experiencia);
        setLicencia(licencia);
    }
    
    public ArrayList<Viaje> getViajes() {
		return viajes;
	}

	public void addViaje(Viaje viaje) {
		viajes.add(viaje);
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException{
        if(nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacÌo");
        }
        
        String nombreLimpio = nombre.trim();
        if(!nombreLimpio.matches("^[a-zA-Z·ÈÌÛ˙¡…Õ”⁄Ò—\\s]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios");
        }
        
        this.nombre = nombreLimpio;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) throws IllegalArgumentException{
        try {
            int idValue = Integer.parseInt(id.trim());
            if(idValue <= 0) {
                throw new IllegalArgumentException("El ID debe ser un n˙mero positivo");
            }
            this.id = idValue;
        } catch (Exception e) {
            throw new IllegalArgumentException("El ID debe ser un n˙mero entero v·lido");
        }
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) throws IllegalArgumentException{
        try {
            int expValue = Integer.parseInt(experiencia.trim());
            if(expValue < 0) {
                throw new IllegalArgumentException("La experiencia no puede ser negativa");
            }
            this.experiencia = expValue;
        } catch (Exception e) {
            throw new IllegalArgumentException("La experiencia debe ser un n˙mero entero v·lido");
        }
    }

    public int getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) throws IllegalArgumentException{
        try {
            int licenciaValue = Integer.parseInt(licencia.trim());
            if(licenciaValue <= 0) {
                throw new IllegalArgumentException("El n˙mero de licencia debe ser positivo");
            }
            this.licencia = licenciaValue;
        } catch (Exception e) {
            throw new IllegalArgumentException("La licencia debe ser un n˙mero entero v·lido");
        }
    }
    
    @Override
    public String toString() {
        return nombre + " id:" + id;
    }
    
    public abstract double calcularSalario();
}