package tf;

public class Pasajero {
	private String nombre;
	private int id;
	
	public Pasajero(String nombre, int id){
		setNombre(nombre);
		setId(id);
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString(){
		return nombre + "id: " + id;
	}
}
