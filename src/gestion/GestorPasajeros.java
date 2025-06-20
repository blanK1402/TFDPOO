package gestion;

import clases.Pasajero;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorPasajeros {
    private final HashMap<String, Pasajero> pasajeros = new HashMap<>();

    public void addPasajero(Pasajero p) {
        pasajeros.put(p.getId(), p);
    }

    public Pasajero getPasajero(String id) {
        return pasajeros.get(id);
    }

    public HashMap<String, Pasajero> getPasajerosMap() {
        return pasajeros;
    }

    public void removePasajero(String id) {
        pasajeros.remove(id);
    }

	public ArrayList<Pasajero> getPasajeros() {
		return new ArrayList<>(pasajeros.values());
	}

	public void clear() {
		pasajeros.clear();
	}
}