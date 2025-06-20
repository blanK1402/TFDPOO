package gestion;

import clases.Viaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GestorViajes {
    private final HashMap<String, Viaje> viajes = new HashMap<>();
    private final HashMap<String, Integer> destinosDistancias = new HashMap<>();
    private long nextId = 1;
    private final Random random = new Random();

    public GestorViajes() {
        inicializarDestinos();
    }

    private void inicializarDestinos() {
        destinosDistancias.put("Pinar del Río", 169);
        destinosDistancias.put("Artemisa", 70);
        destinosDistancias.put("Mayabeque", 50);
        destinosDistancias.put("Matanzas", 104);
        destinosDistancias.put("Villa Clara", 267);
        destinosDistancias.put("Cienfuegos", 275);
        destinosDistancias.put("Sancti Spíritus", 359);
        destinosDistancias.put("Ciego de Ávila", 460);
        destinosDistancias.put("Camagüey", 540);
        destinosDistancias.put("Las Tunas", 662);
        destinosDistancias.put("Holguín", 689);
        destinosDistancias.put("Granma", 743);
        destinosDistancias.put("Santiago de Cuba", 860);
        destinosDistancias.put("Guantánamo", 905);
    }

    public String getRandomDestino() {
        ArrayList<String> destinos = new ArrayList<>(destinosDistancias.keySet());
        return destinos.get(random.nextInt(destinos.size()));
    }

    public void addViaje(Viaje v) {
        viajes.put(String.valueOf(v.getId()), v);
    }

    public Viaje getViaje(String id) {
        return viajes.get(id);
    }

    public ArrayList<Viaje> getViajesList() {
        return new ArrayList<>(viajes.values());
    }

    public long getNextId() {
        return nextId++;
    }

    public void removeViaje(String id) {
        viajes.remove(id);
    }
    
    public HashMap<String, Integer> getDestinosDistancias() {
        return destinosDistancias;
    }

    public HashMap<String, ArrayList<Viaje>> getDestinosViajes() {
        HashMap<String, ArrayList<Viaje>> destinosViajes = new HashMap<>();

        for (Viaje viaje : viajes.values()) {
            String destino = viaje.getDestino();

            if (!destinosViajes.containsKey(destino)) {
                destinosViajes.put(destino, new ArrayList<Viaje>());
            }

            destinosViajes.get(destino).add(viaje);
        }

        return destinosViajes;
    }

	public void clear() {
		viajes.clear();
	}
}