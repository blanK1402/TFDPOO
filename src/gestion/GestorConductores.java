package gestion;

import clases.Conductor;

import java.util.ArrayList;
import java.util.HashMap;

public class GestorConductores {
    private final HashMap<String, Conductor> conductores = new HashMap<>();
    private long nextId = 1;

    public void addConductor(Conductor c) {
        conductores.put(String.valueOf(c.getId()), c);
    }

    public Conductor getConductor(String id) {
        return conductores.get(id);
    }

    public ArrayList<Conductor> getConductores() {
        return new ArrayList<Conductor>(conductores.values());
    }

    public void removeConductor(String id) {
        conductores.remove(id);
    }

    public long getNextId() {
        return nextId++;
    }

    public void clear() {
        conductores.clear();
    }
}