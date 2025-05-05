package tf;

public class Pasajero {
	private String nombre;
	private int id;
	
	public Pasajero(String nombre, String id){
		setNombre(nombre);
		setId(id);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws IllegalArgumentException{
		if(!(nombre.matches("^[a-zA-Z]+$"))){
			throw new IllegalArgumentException("El nombre debe contener solo letras");
		}
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(String id) throws IllegalArgumentException{
		try{
			this.id = Integer.parseInt(id);
		}catch(Exception e){
			throw new IllegalArgumentException("El id debe ser un número");
		}	
	}
	@Override
	public String toString(){
		return nombre + "id: " + id;
	}
}
