package tf;

public abstract class Conductor {
	protected String nombre;
	protected int id;
	protected int experiencia;
	protected int licencia;
	
	public Conductor(String nombre, int id, int experiencia, int licencia){
		setNombre(nombre);
		setId(id);
		setExperiencia(experiencia);
		setLicencia(licencia);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre.isEmpty()){
			throw new IllegalArgumentException("Se deben llenar todos los campos");
		}
		else if(nombre.matches("[^a-zA-Z]")){
			throw new IllegalArgumentException("Formato inválido");
		}
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getLicencia() {
		return licencia;
	}

	public void setLicencia(int licencia) {
		this.licencia = licencia;
	}
	
	@Override
	public String toString(){
		return nombre + " id:" + id;
	}
	
	public abstract double calcularSalario();
}
