package tf;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;

public class Runner {
	private Terminal terminal;
	private static final String[] NOMBRES = {
        "Carlos", "Luis", "Ana", "Maria", "Pedro", "Sofia", "Javier", "Elena",
        "Fernando", "Gabriela", "Raúl", "Valentina", "Diego", "Camila", "Miguel",
        "Isabella", "Alejandro", "Lucía", "Eduardo", "Andrea", "Ricardo", "Paula",
        "Manuel", "Victoria", "David", "Beatriz", "Jorge", "Carla", "Cristian",
        "Daniela", "Sebastián", "Patricia", "Enrique", "Lorena", "Adrián", "Estefanía"
    };
    private static final Random random = new Random();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terminal terminal = new Terminal("Mi Terminal");
					ArrayList<Conductor> conductores = generarConductores(20);
					ArrayList<Omnibus> omnibuses = generarOmnibus(20, conductores);
					for(Conductor conductor : conductores){
						terminal.addConductor(conductor);
					}
					for(Omnibus o : omnibuses){
						terminal.addOmnibus(o);
					}
					Interfaz frame = new Interfaz(terminal);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static ArrayList<Conductor> generarConductores(int n) {

        ArrayList<Conductor> conductores = new ArrayList<Conductor>();
        
		for (int i = 0; i < n; i++) {
            String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
            String id = String.valueOf(i+1); 
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
            
            conductores .add(conductor);
        }

        return conductores;
    }
	
	private static ArrayList<Omnibus> generarOmnibus(int n, ArrayList<Conductor> conductores) {
        ArrayList<Omnibus> omnibusList = new ArrayList<Omnibus>();
        ArrayList<String> comodidades = new ArrayList<String>();
        
        for (int i = 0; i < n; i++) {
        	int index = random.nextInt(conductores.size()-4);
            String matricula = "A" + (random.nextInt(900000) + 100000);
            String asientos = String.valueOf(random.nextInt(100) + 1);
            ArrayList<Conductor> misConductores = new ArrayList<Conductor>();
            String disponibilidad = null;
            
            if(i % 2 == 0){
            	comodidades.add("Aire acondicionado");
                disponibilidad = "Disponible";
                misConductores.add(conductores.get(index));
            }
            if(i % 3 == 0){
                disponibilidad = "En carretera";
                misConductores.add(conductores.get(index+1));
            }
            if(i % 5 == 0){
            	comodidades.add("TV");
                disponibilidad = "En reparación";
                misConductores.add(conductores.get(index+2));
            }
            
            comodidades.add("Banyo");
            disponibilidad = disponibilidad == null ? "Disponible" : disponibilidad;
            Omnibus omnibus = new Omnibus(matricula, asientos, disponibilidad, comodidades);
            omnibusList.add(omnibus);
        }
        
        return omnibusList;
    }
	
	public Terminal getTerminal(){
		return terminal;
	}
}
