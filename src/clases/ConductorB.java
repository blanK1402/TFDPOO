package clases;


public class ConductorB extends Conductor{
	public ConductorB(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 500 + viajes.size() * 50;
	}
	
    public String[] toTableList() {
        String[] res = {
        		String.valueOf(nombre),
        		String.valueOf(id),
        		"B",
        		String.valueOf(experiencia),
        		String.valueOf(licencia)
        };
                
		return res;
    }
}