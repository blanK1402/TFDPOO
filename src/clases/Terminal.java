package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

public class Terminal {
    private static LocalDateTime fechaHora;
    private static final AtomicLong idReservas = new AtomicLong(1);
    private static final AtomicLong idViajes = new AtomicLong(1);
    private static final AtomicLong idConductores = new AtomicLong(1);
    private static HashMap<String, Integer> destinosDistancias;

    private String nombre;
    private static ArrayList<String> listaNombres;
    private ArrayList<Conductor> conductores;
    private HashSet<Integer> conductoresLicencias;
    private ArrayList<Omnibus> omnibuses;
    private HashSet<String> omnibusID;
    private HashMap<String, ArrayList<Viaje>> viajes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Reserva> reservasEspera;
    private ArrayList<Pasajero> pasajeros;
    private HashSet<Integer> pasajerosID;
    private HashMap<LocalDate, HashMap<Omnibus, Integer>> registro;

    public Terminal(String nombre) {
        setNombre(nombre);
        fechaHora = LocalDateTime.now();

        listaNombres = new ArrayList<String>();
        
        conductores = new ArrayList<>();
        conductoresLicencias = new HashSet<>();

        omnibuses = new ArrayList<Omnibus>();
        omnibusID = new HashSet<>();

        reservas = new ArrayList<>();
        reservasEspera = new ArrayList<>();
        pasajeros = new ArrayList<>();
        pasajerosID = new HashSet<>();
        registro = new HashMap<>();

        setListaNombres();
        setDestinosDistancias();
        setViajes();
    }

    public HashSet<String> getOmnibusId(){
    	return omnibusID;
    }
    
    public HashSet<Integer> getIdPasajeros(){
		return pasajerosID;
    }
    
    private void setDestinosDistancias() {
        destinosDistancias = new HashMap<>();
        destinosDistancias.put("Pinar del R�o", 169);
        destinosDistancias.put("Artemisa", 70);
        destinosDistancias.put("Mayabeque", 50);
        destinosDistancias.put("Matanzas", 104);
        destinosDistancias.put("Villa Clara", 267);
        destinosDistancias.put("Cienfuegos", 275);
        destinosDistancias.put("Sancti Sp�ritus", 359);
        destinosDistancias.put("Ciego de �vila", 460);
        destinosDistancias.put("Camag�ey", 540);
        destinosDistancias.put("Las Tunas", 662);
        destinosDistancias.put("Holgu�n", 689);
        destinosDistancias.put("Granma", 743);
        destinosDistancias.put("Santiago de Cuba", 860);
        destinosDistancias.put("Guant�namo", 905);
    }

    public static HashMap<String, Integer> getDestinosDistancias(){
    	return destinosDistancias;
    }
    
    public HashSet<Integer> getLicencias(){
		return conductoresLicencias;
    	
    }
    
    private void setViajes() {
        this.viajes = new HashMap<String, ArrayList<Viaje>>();
        for (String destino : destinosDistancias.keySet()) {
            this.viajes.put(destino, new ArrayList<Viaje>());
        }
    }

    public static ArrayList<String> getListaNombres(){
    	return listaNombres;
    }
    
    public static void setListaNombres() {
        listaNombres = new ArrayList<String>(Arrays.asList(
            "Alejandro", "Beatriz", "Carlos", "Daniela", "Eduardo",
            "Fernanda", "Gabriel", "Hortensia", "Ignacio", "Jimena",
            "Kevin", "Laura", "Manuel", "Natalia", "Oscar",
            "Patricia", "Quetzal", "Ricardo", "Sof�a", "Tom�s",
            "Ulises", "Valeria", "Wendy", "Ximena", "Yolanda",
            "Zacar�as", "Ana", "Beto", "Claudia", "Diego",
            "Esteban", "Fabiola", "Gustavo", "Helena", "Iv�n",
            "Jos�", "Karla", "Luis", "Mar�a", "No�",
            "Olga", "Pablo", "Queen", "Ra�l", "Silvia",
            "Tania", "Uriel", "Ver�nica", "Wilfrido", "X�chitl",
            "Adri�n", "Berenice", "Cecilia", "Diana", "Emilio",
            "F�tima", "Gerardo", "Isabel", "Jorge", "Karina",
            "Leonardo", "M�nica", "Nicol�s", "Ofelia", "Pedro",
            "Querub�n", "Roc�o", "Samuel", "Teresa", "V�ctor",
            "Yahir", "Zoe", "Alberto", "B�rbara", "Cristian",
            "Dulce", "Ernesto", "Francisca", "Guillermo", "H�ctor",
            "Irene", "Joaqu�n", "Lorena", "Miguel", "Norma",
            "Octavio", "Perla", "Rafael", "Salma", "Tadeo",
            "Vanessa", "Yaretzi", "Alma", "Benito", "Camila",
            "Dami�n", "Esmeralda", "Felipe", "Gloria", "Hugo",
            "In�s", "Jacobo", "Leticia", "Mario", "Nadia",
            "Orlando", "Paulina", "Rub�n", "Sara", "Teodoro",
            "Vanesa", "Yadira", "Alfonso", "Blanca", "C�sar",
            "Deborah", "Efra�n", "Florencia", "Griselda", "Horacio",
            "Ilse", "Julio", "Luisa", "Marcelo", "Nelly",
            "Omar", "Pilar", "Ram�n", "Susana", "Timoteo",
            "Violeta", "Yael", "Arturo", "Briseida", "Clemente",
            "Dora", "El�as", "Felicia", "Georgina", "Heriberto",
            "Itzel", "Jazm�n", "Le�n", "Magdalena", "N�stor",
            "Obdulia", "Priscila", "Renato", "Selene", "Tiburcio",
            "Vianey", "Yulissa", "Aldo", "Belinda", "Cirilo",
            "Deyanira", "Ezequiel", "Fidel", "Graciela", "Hermelinda",
            "Isela", "Javier", "Kenia", "L�zaro", "Mireya",
            "Natividad", "Otilia", "Pascual", "Rosario", "Santos"
        ));
    }
    
    public static LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public static void setFechaHora(LocalDateTime nuevaFechaHora) {
        fechaHora = nuevaFechaHora;
    }

    public static String getIdReserva() {
        return String.valueOf(idReservas.getAndIncrement());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public ArrayList<Reserva> getReservasEspera() {
        return reservasEspera;
    }

    public ArrayList<Conductor> getConductores() {
        return conductores;
    }

    public ArrayList<Omnibus> getOmnibus() {
        return omnibuses;
    }

    public HashMap<String, ArrayList<Viaje>> getViajes() {
        return viajes;
    }

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void addRegistro(LocalDate fecha, Omnibus omnibus, int capacidadesVendidas) {
        HashMap<Omnibus, Integer> par = new HashMap<>();
        par.put(omnibus, capacidadesVendidas);
        registro.put(fecha, par);
    }

    public void addPasajero(Pasajero nuevoPasajero) throws IllegalArgumentException {
        if (pasajerosID.contains(nuevoPasajero.getId())) {
            throw new IllegalArgumentException("Ya existe un pasajero con ese ID");
        }
        pasajerosID.add(nuevoPasajero.getId());
        pasajeros.add(nuevoPasajero);
    }

    public void addReserva(Reserva reserva) throws IllegalArgumentException {
        if (reserva.getEstado().equals("Confirmada")) {
            reservas.add(reserva);
        } else {
            reservasEspera.add(reserva);
        }
    }

    public void addOmnibus(Omnibus omnibus) {
        if (omnibusID.contains(omnibus.getMatricula())) {
            throw new IllegalArgumentException("Ya existe un omnibus con esa matr�cula");
        }
        omnibusID.add(omnibus.getMatricula());
        omnibuses.add(omnibus);
    }

    public void addViaje(Viaje viaje) {
        viajes.get(viaje.getDestino()).add(viaje);
    }

    public void addConductor(Conductor conductor) {
        conductoresLicencias.add(conductor.getLicencia());
        conductores.add(conductor);
    }

    public static LocalDateTime getFecha() {
        return fechaHora;
    }
    
	public static String getIdViaje() {
		return String.valueOf(idViajes.getAndIncrement());
	}
	
	public static String getIdConductor() {
		return String.valueOf(idConductores.getAndIncrement());
	}
}
