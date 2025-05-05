package tf;

public class ConductorB extends Conductor{
	public ConductorB(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 500 + viajes.size() * 50;
	}
}