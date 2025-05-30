package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

public class Terminal {
    private LocalDateTime fechaHora;
    private static final AtomicLong idReservas = new AtomicLong(1);
    private static final AtomicLong idViajes = new AtomicLong(1);
    private static final AtomicLong idConductores = new AtomicLong(1);
    private static HashMap<String, Integer> destinosDistancias;

    private String nombre;

    private static ArrayList<String> listaNombres;
    private HashMap<String, Conductor> conductores;
    private HashMap<String, Pasajero> pasajeros;
    private HashMap<String, Omnibus> omnibuses;
    private HashMap<String, Viaje> viajes;
    private HashMap<String, ArrayList<Viaje>> destinoViajes;
    private HashMap<String, Reserva> reservas;
    private HashMap<String, Reserva> reservasEspera;
    private HashMap<String, Reserva> reservasCanceladas;

    public Terminal(String nombre) {
        setNombre(nombre);
        fechaHora = LocalDateTime.now();

        listaNombres = new ArrayList<String>();
        
        conductores = new HashMap<>();

        omnibuses = new HashMap<>();
        reservas = new HashMap<>();
        reservasEspera = new HashMap<>();
        pasajeros = new HashMap<>();
        setListaNombres();
        setDestinosDistancias();
    }

    private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static String getRandomDestino(){
    	ArrayList<String> destinos = new ArrayList<>(destinosDistancias.keySet());
    	return destinos.get((int) (Math.random() * destinos.size()));
    }
    
    private void setDestinosDistancias() {
        destinosDistancias = new HashMap<>();
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

    public static HashMap<String, Integer> getDestinosDistancias(){
    	return destinosDistancias;
    }
   

    public static ArrayList<String> getListaNombres(){
    	return listaNombres;
    }
    
    public static void setListaNombres() {
        listaNombres = new ArrayList<String>(Arrays.asList(
            "Alejandro", "Beatriz", "Carlos", "Daniela", "Eduardo",
            "Fernanda", "Gabriel", "Hortensia", "Ignacio", "Jimena",
            "Kevin", "Laura", "Manuel", "Natalia", "Oscar",
            "Patricia", "Quetzal", "Ricardo", "Sofía", "Tomás",
            "Ulises", "Valeria", "Wendy", "Ximena", "Yolanda",
            "Zacarías", "Ana", "Beto", "Claudia", "Diego",
            "Esteban", "Fabiola", "Gustavo", "Helena", "Iván",
            "José", "Karla", "Luis", "María", "Noé",
            "Olga", "Pablo", "Queen", "Raúl", "Silvia",
            "Tania", "Uriel", "Verónica", "Wilfrido", "Xóchitl",
            "Adrián", "Berenice", "Cecilia", "Diana", "Emilio",
            "Fátima", "Gerardo", "Isabel", "Jorge", "Karina",
            "Leonardo", "Mónica", "Nicolás", "Ofelia", "Pedro",
            "Querubín", "Rocío", "Samuel", "Teresa", "Víctor",
            "Yahir", "Zoe", "Alberto", "Bárbara", "Cristian",
            "Dulce", "Ernesto", "Francisca", "Guillermo", "Héctor",
            "Irene", "Joaquín", "Lorena", "Miguel", "Norma",
            "Octavio", "Perla", "Rafael", "Salma", "Tadeo",
            "Vanessa", "Yaretzi", "Alma", "Benito", "Camila",
            "Damián", "Esmeralda", "Felipe", "Gloria", "Hugo",
            "Inés", "Jacobo", "Leticia", "Mario", "Nadia",
            "Orlando", "Paulina", "Rubén", "Sara", "Teodoro",
            "Vanesa", "Yadira", "Alfonso", "Blanca", "César",
            "Deborah", "Efraín", "Florencia", "Griselda", "Horacio",
            "Ilse", "Julio", "Luisa", "Marcelo", "Nelly",
            "Omar", "Pilar", "Ramón", "Susana", "Timoteo",
            "Violeta", "Yael", "Arturo", "Briseida", "Clemente",
            "Dora", "Elías", "Felicia", "Georgina", "Heriberto",
            "Itzel", "Jazmín", "León", "Magdalena", "Néstor",
            "Obdulia", "Priscila", "Renato", "Selene", "Tiburcio",
            "Vianey", "Yulissa", "Aldo", "Belinda", "Cirilo",
            "Deyanira", "Ezequiel", "Fidel", "Graciela", "Hermelinda",
            "Isela", "Javier", "Kenia", "Lázaro", "Mireya",
            "Natividad", "Otilia", "Pascual", "Rosario", "Santos"
        ));
    }

    public void setFechaHora(LocalDateTime nuevaFechaHora) {
        fechaHora = nuevaFechaHora;
    }

    public LocalDateTime getFecha() {
        return fechaHora;
    }

	public void adelantarDia() {
		setFechaHora(fechaHora.plusDays(1));
	}
	
	public void adelantarHora() {
		setFechaHora(fechaHora.plusHours(1));
	}

	public void addOmnibus(Omnibus o) {
		omnibuses.put(o.getMatricula(), o);
	}

	public ArrayList<Conductor> getConductores() {
		return new ArrayList<>(conductores.values());
	}

	public ArrayList<Omnibus> getOmnibuses() {
		return new ArrayList<>(omnibuses.values());
	}

	public ArrayList<Pasajero> getPasajeros() {
		return new ArrayList<>(pasajeros.values());
	}

	public void addPasajero(Pasajero p) {
		pasajeros.put(p.getId(), p);
	}

	public void addConductor(Conductor c) {
		conductores.put(String.valueOf(c.getId()), c);
	}

	public void addViaje(Viaje v) {
		viajes.put(String.valueOf(v.getId()), v);
		destinoViajes.get(v.getDestino()).add(v);
		v.getConductor().addViaje(v);
	}

	public HashMap<String, ArrayList<Viaje>> getViajes() {
		return destinoViajes;
	}

	public void addReserva(Reserva r) {
		reservas.put(String.valueOf(r.getNumReserva()), r);
	}

	public static long getIdConductor() {
		return idConductores.getAndIncrement();
	}

	public static long getIdReserva() {
		return idReservas.getAndIncrement();
	}

	public static long getIdViaje() {
		return idViajes.getAndIncrement();
	}

	public Pasajero getPasajero(String usuario) {
		return pasajeros.get(usuario);
	}
	
}
