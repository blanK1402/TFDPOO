package tf;

public class ConductorB extends Conductor{
	private int viajesMes;
	public ConductorB(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
		viajesMes = 0;
	}

	public double calcularSalario(){
		return 500 + viajesMes * 50;
	}
}