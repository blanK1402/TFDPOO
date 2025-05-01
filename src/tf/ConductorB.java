package tf;

public class ConductorB extends Conductor{
	private int viajesMes;
	public ConductorB(String nombre, int id, int experiencia, int licencia) {
		super(nombre, id, experiencia, licencia);
		viajesMes = 0;
	}

	public double calcularSalario(){
		return 500 + viajesMes * 50;
	}
}