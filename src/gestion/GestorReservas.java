package gestion;

import clases.Reserva;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorReservas {
    private final HashMap<String, Reserva> reservas = new HashMap<>();
    private final HashMap<String, Reserva> reservasEspera = new HashMap<>();
    private final HashMap<String, Reserva> reservasCanceladas = new HashMap<>();
    private long nextId = 1;

    public void addReserva(Reserva r) {
        if (r.getViaje() != null) {
            reservas.put(String.valueOf(r.getNumReserva()), r);
        } else {
            reservasEspera.put(String.valueOf(r.getNumReserva()), r);
        }
        if(r.getEstado().equals("Cancelada")){
        	reservasCanceladas.put(String.valueOf(r.getNumReserva()), r);
        }
    }

    public Reserva getReserva(String id) {
    	Reserva r = null;
        if (reservas.containsKey(id)) r = reservas.get(id);
        if (reservasEspera.containsKey(id)) r = reservasEspera.get(id);
        if (reservasCanceladas.containsKey(id)) r = reservasCanceladas.get(id);
        return r;
    }

    public ArrayList<Reserva> getReservasList() {
        return new ArrayList<>(reservas.values());
    }
    
    public ArrayList<Reserva> getReservasEsperaList() {
        return new ArrayList<>(reservas.values());
    }
    
    public ArrayList<Reserva> getReservasCanceladasList() {
        return new ArrayList<>(reservas.values());
    }

    public long getNextId() {
        return nextId++;
    }

    public void cancelarReserva(String id) {
        Reserva r = getReserva(id);
        reservas.remove(id);
        reservasEspera.remove(id);
        reservasCanceladas.put(id, r);
    }

	public void clear() {
		reservasEspera.clear();
	}
}