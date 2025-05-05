package tf;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Runner {
	private Terminal terminal;
	
	private static final String[] provinciasCuba = {
		    "Pinar del Río", "Artemisa", "Mayabeque", "Matanzas", 
		    "Villa Clara", "Cienfuegos", "Sancti Spíritus", 
		    "Ciego de Ávila", "Camagüey", "Las Tunas", "Holguín", 
		    "Granma", "Santiago de Cuba", "Guantánamo"
		};


    private static final Random random = new Random();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terminal terminal = new Terminal("Mi Terminal");
					Interfaz frame = new Interfaz(terminal);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	static ArrayList<Conductor> generarConductores(int n, ArrayList<Conductor> conductoresList, String[] nombresList) {

		String id;
        ArrayList<Conductor> conductores = new ArrayList<Conductor>();
        HashSet<Integer> idExistentes = new HashSet<Integer>();
        
        for(Conductor c : conductoresList){
        	idExistentes.add(c.getId());
        }
        
		for (int i = 0; i < n; i++) {
            String nombre = nombresList[random.nextInt(nombresList.length)];
            
            do{
            	id = String.valueOf(random.nextInt(10000)); 
            }while(idExistentes.contains(id));
            
            idExistentes.add(Integer.valueOf(id));
            
            String experiencia = String.valueOf(random.nextInt(20));
            String licencia = String.valueOf(i + 1000);
            
            Conductor conductor;
            
            if(i%2 == 0){
            	conductor = new ConductorA(nombre, id, experiencia, licencia);
            }
            else if(i%3 == 0){
            	conductor = new ConductorB(nombre, id, experiencia, licencia);
            }
            else{
            	conductor = new ConductorC(nombre, id, experiencia, licencia);
            }
            
            conductores.add(conductor);
        }

        return conductores;
    }
	

	static ArrayList<Omnibus> generarOmnibus(int n, ArrayList<Conductor> conductores, ArrayList<Omnibus> omnibuses) {
	    ArrayList<Omnibus> omnibusList = new ArrayList<Omnibus>();
	    HashSet<String> matriculasExistentes = new HashSet<String>();

	    for (Omnibus o : omnibuses) {
	        matriculasExistentes.add(o.getMatricula());
	    }

	    Random random = new Random();

	    for (int i = 0; i < n; i++) {
	        int index = random.nextInt(Math.max(conductores.size() - 3, 1));
	        String matricula;

	        do {
	            matricula = "A" + (random.nextInt(900000) + 100000);
	        } while (!matriculasExistentes.add(matricula));

	        String asientos = String.valueOf(random.nextInt(100) + 1);
	        ArrayList<Conductor> misConductores = new ArrayList<Conductor>();
	        ArrayList<String> comodidades = new ArrayList<String>();

	        misConductores.add(conductores.get(index));

	        if (i % 2 == 0) {
	            comodidades.add("Aire acondicionado");
	        }
	        if (i % 3 == 0 && index + 1 < conductores.size()) {
	            misConductores.add(conductores.get(index + 1));
	        }
	        if (i % 5 == 0 && index + 2 < conductores.size()) {
	            comodidades.add("TV");
	            misConductores.add(conductores.get(index + 2));
	        }

	        comodidades.add("Baño");

	        Omnibus omnibus = new Omnibus(matricula, asientos, "Disponible", comodidades);
	        for (Conductor c : misConductores) {
	            omnibus.addConductor(c);
	        }
	        omnibusList.add(omnibus);
	    }

	    return omnibusList;
	}

	
	public Terminal getTerminal(){
		return terminal;
	}
}
