package runner;

import interfaz.Interfaz;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Terminal;

public class Runner {
    private Terminal terminal;
    private static final Random random = new Random();

    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terminal terminal = new Terminal("Mi Terminal");
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public Terminal getTerminal() {
        return terminal;
    }

    private static Conductor crearConductor(int indice, String[] nombres, HashSet<Integer> idsExistentes) {
        String nombre = nombres[random.nextInt(nombres.length)];
        String id = generarIdUnico(idsExistentes);
        String experiencia = String.valueOf(random.nextInt(20));
        String licencia = String.valueOf(indice + 1000);

        if (indice % 2 == 0) {
            return new ConductorA(nombre, id, experiencia, licencia);
        }
        return indice % 3 == 0 
            ? new ConductorB(nombre, id, experiencia, licencia) 
            : new ConductorC(nombre, id, experiencia, licencia);
    }

    private static String generarIdUnico(HashSet<Integer> idsExistentes) {
        int id;
        do {
            id = random.nextInt(10000);
        } while (idsExistentes.contains(id));
        idsExistentes.add(id);
        return String.valueOf(id);
    }


    private static HashSet<String> obtenerMatriculas(ArrayList<Omnibus> omnibuses) {
        HashSet<String> matriculas = new HashSet<>();
        for (Omnibus o : omnibuses) {
            matriculas.add(o.getMatricula());
        }
        return matriculas;
    }

    private static Omnibus crearOmnibus(int indice, ArrayList<Conductor> conductores, HashSet<String> matriculasExistentes) {
        String matricula = generarMatriculaUnica(matriculasExistentes);
        String asientos = String.valueOf(random.nextInt(100) + 1);
        ArrayList<Conductor> conductoresOmnibus = seleccionarConductores(indice, conductores);
        ArrayList<String> comodidades = determinarComodidades(indice);

        Omnibus omnibus = new Omnibus(matricula, asientos, "Disponible", comodidades);
        for(Conductor c : conductoresOmnibus){
        	omnibus.addConductor(c);
        }
        return omnibus;
    }

    private static String generarMatriculaUnica(HashSet<String> matriculasExistentes) {
        String matricula;
        do {
            matricula = "A" + (random.nextInt(900000) + 100000);
        } while (matriculasExistentes.contains(matricula));
        matriculasExistentes.add(matricula);
        return matricula;
    }

    private static ArrayList<Conductor> seleccionarConductores(int indice, ArrayList<Conductor> conductores) {
        ArrayList<Conductor> conductoresOmnibus = new ArrayList<>();
        int index = random.nextInt(Math.max(conductores.size() - 3, 1));
        
        conductoresOmnibus.add(conductores.get(index));
        if (indice % 2 == 0 && index + 1 < conductores.size()) {
            conductoresOmnibus.add(conductores.get(index + 1));
        }
        if (indice % 3 == 0 && index + 2 < conductores.size()) {
            conductoresOmnibus.add(conductores.get(index + 2));
        }
        return conductoresOmnibus;
    }

    private static ArrayList<String> determinarComodidades(int indice) {
        ArrayList<String> comodidades = new ArrayList<>();
        comodidades.add("Baño");
        if (indice % 2 == 0) comodidades.add("TV");
        if (indice % 3 == 0) comodidades.add("Aire acondicionado");
        return comodidades;
    }
}