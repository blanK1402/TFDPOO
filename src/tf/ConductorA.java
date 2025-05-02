package tf;

public class ConductorA extends Conductor{
	public ConductorA(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 600 + 0.3*500*experiencia;
	}
}
