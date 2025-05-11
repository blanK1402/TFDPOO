package clases;


public class ConductorC extends Conductor{
	public ConductorC(String nombre, String id, String experiencia, String licencia) {
		super(nombre, id, experiencia, licencia);
	}

	public double calcularSalario(){
		return 1000;
	}
	
    public String[] toTableList() {
        String[] res = {
        		String.valueOf(nombre),
        		String.valueOf(id),
        		"C",
        		String.valueOf(experiencia),
        		String.valueOf(licencia),
        		String.valueOf(viajes.size())
        };
                
		return res;
    }
}