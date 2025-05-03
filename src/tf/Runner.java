package tf;

import java.awt.Container;
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
					for(Conductor conductor : conductores){
						terminal.addConductor(conductor);
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
            String id = String.valueOf(i+1); // ID aleatorio positivo
            String experiencia = String.valueOf(random.nextInt(20)); // Entre 0 y 20 años de experiencia
            String licencia = String.valueOf(i + 1000); // Licencia de 4 dígitos
            
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
	
	public Terminal getTerminal(){
		return terminal;
	}
}
