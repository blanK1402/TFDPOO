package tf;

public class ConductorC extends Conductor{
	public ConductorC(String nombre, int id, int experiencia, int licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 1000;
	}
}