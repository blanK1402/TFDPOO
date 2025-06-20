package gestion;

import clases.Omnibus;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorOmnibus {
    private final HashMap<String, Omnibus> omnibuses = new HashMap<>();

    public void addOmnibus(Omnibus o) {
        omnibuses.put(o.getMatricula(), o);
    }

    public Omnibus getOmnibus(String matricula) {
        return omnibuses.get(matricula);
    }

    public ArrayList<Omnibus> getOmnibusesList() {
        return new ArrayList<>(omnibuses.values());
    }

    public void removeOmnibus(String matricula) {
        omnibuses.remove(matricula);
    }

	public void clear() {
		omnibuses.clear();
	}
}