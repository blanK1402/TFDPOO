package tf;

public class ConductorC extends Conductor{
	public ConductorC(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 1000;
	}
}