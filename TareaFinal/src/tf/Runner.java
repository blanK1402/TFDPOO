package tf;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Runner {
    private Terminal terminal;
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

    public static ArrayList<Pasajero> generarPasajeros(ArrayList<Pasajero> pasajerosExistentes, String[] nombres) {

    	HashSet<Integer> idsExistentes = new HashSet<Integer>();
    	ArrayList<Pasajero> nuevosPasajeros = new ArrayList<Pasajero>();
        Random random = new Random();
        
        for (Pasajero p: pasajerosExistentes) {
        	idsExistentes.add(p.getId());
        }
        
        for (int i = 0; i < nombres.length; i++) {
                Pasajero pasajero = new Pasajero(nombres[i].trim(), generarIdUnico(idsExistentes));
                nuevosPasajeros.add(pasajero);
        }
        
        return nuevosPasajeros;
    }

    
    public static ArrayList<Conductor> generarConductores(int cantidad, ArrayList<Conductor> conductoresExistentes, String[] nombres) {
        ArrayList<Conductor> nuevosConductores = new ArrayList<Conductor>();
        HashSet<Integer> idsExistentes = new HashSet<Integer>();
        
        for (Conductor c : conductoresExistentes) {
        	idsExistentes.add(c.getId());
        }

        for (int i = 0; i < cantidad; i++) {
            nuevosConductores.add(crearConductor(i, nombres, idsExistentes));
        }
        return nuevosConductores;
    }

    public static ArrayList<Viaje> generarViajes(int cantidad, HashMap<String, Integer> destinosDistancias, 
            Terminal terminal, 
            ArrayList<Conductor> conductores, 
            ArrayList<Omnibus> omnibuses
            ) {

        ArrayList<Viaje> viajes = new ArrayList<>();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.of(8, 0);

        for (int i = 0; i < cantidad; i++) {
            viajes.add(crearViajeAleatorio(
                destinosDistancias, terminal, conductores, omnibuses, 
                i, dateFormat, timeFormat, currentDate, currentTime
            ));
        }
        return viajes;
    }

    public static ArrayList<Omnibus> generarOmnibus(int cantidad, ArrayList<Conductor> conductores, ArrayList<Omnibus> omnibusesExistentes) {
        ArrayList<Omnibus> omnibusList = new ArrayList<>();
        HashSet<String> matriculasExistentes = obtenerMatriculas(omnibusesExistentes);

        for (int i = 0; i < cantidad; i++) {
            omnibusList.add(crearOmnibus(i, conductores, matriculasExistentes));
        }
        return omnibusList;
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

    private static Viaje crearViajeAleatorio(HashMap<String, Integer> destinosDistancias,
            Terminal terminal, ArrayList<Conductor> conductores, ArrayList<Omnibus> omnibuses,
            int indice, DateTimeFormatter dateFormat, DateTimeFormatter timeFormat,
            LocalDate currentDate, LocalTime currentTime) {

        ArrayList<String> destinos = new ArrayList<>(destinosDistancias.keySet());
        String randomDestino = destinos.get(random.nextInt(destinos.size()));
        int distancia = destinosDistancias.get(randomDestino);
        Conductor conductor = conductores.get(random.nextInt(conductores.size()));
        Omnibus omnibus = omnibuses.get(random.nextInt(omnibuses.size()));

        LocalDate fecha = currentDate.plusDays(random.nextInt(30));
        LocalTime hora = currentTime.plusHours(random.nextInt(12));
        LocalDateTime estimado = terminal.calcularFechaEstimada(randomDestino, fecha.atTime(hora));

        return new Viaje(
            String.valueOf(indice + 1),
            distancia,
            fecha.format(dateFormat),
            hora.format(timeFormat),
            randomDestino,
            estimado.toLocalDate(),
            estimado.toLocalTime(),
            omnibus,
            conductor
        );
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