package clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicLong;

public class Terminal {
    private static LocalDateTime fechaHora;
    private static final AtomicLong idReservas = new AtomicLong(1);
    private static HashMap<String, Integer> destinosDistancias;

    private String nombre;
    private ArrayList<Conductor> conductores;
    private HashSet<Integer> conductoresID;
    private HashSet<Integer> conductoresLicencias;
    private ArrayList<Omnibus> omnibuses;
    private HashSet<String> omnibusID;
    private HashMap<String, ArrayList<Viaje>> viajes;
    private HashSet<Integer> viajesID;
    private ArrayList<Reserva> reservas;
    private ArrayList<Reserva> reservasEspera;
    private ArrayList<Pasajero> pasajeros;
    private HashSet<Integer> pasajerosID;
    private HashMap<LocalDate, HashMap<Omnibus, Integer>> registro;

    public Terminal(String nombre) {
        setNombre(nombre);
        fechaHora = LocalDateTime.now();

        conductores = new ArrayList<>();
        conductoresID = new HashSet<>();
        conductoresLicencias = new HashSet<>();

        omnibuses = new ArrayList<>();
        omnibusID = new HashSet<>();

        viajesID = new HashSet<>();
        reservas = new ArrayList<>();
        reservasEspera = new ArrayList<>();
        pasajeros = new ArrayList<>();
        pasajerosID = new HashSet<>();
        registro = new HashMap<>();

        setDestinosDistancias();
        setViajes();
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

    private void setViajes() {
        this.viajes = new HashMap<String, ArrayList<Viaje>>();
        for (String destino : destinosDistancias.keySet()) {
            this.viajes.put(destino, new ArrayList<Viaje>());
        }
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

    // Métodos de instancia
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
            throw new IllegalArgumentException("Ya existe un omnibus con esa matrícula");
        }
        omnibusID.add(omnibus.getMatricula());
        omnibuses.add(omnibus);
    }

    public void addViaje(Viaje viaje) {
        if (viajesID.contains(viaje.getId())) {
            throw new IllegalArgumentException("Ya existe un viaje con ese ID");
        }
        if (viaje.getFechaHoraPartida().toLocalDate().isBefore(fechaHora.toLocalDate())) {
            throw new IllegalArgumentException("La fecha de partida debe ser mayor a la fecha actual");
        }
        viajesID.add(viaje.getId());
        viajes.get(viaje.getDestino()).add(viaje);
    }

    public void addConductor(Conductor conductor) {
        if (conductoresID.contains(conductor.getId())) {
            throw new IllegalArgumentException("Ya existe un conductor con ese ID");
        } else if (conductoresLicencias.contains(conductor.getLicencia())) {
            throw new IllegalArgumentException("Ya existe un conductor con esa licencia");
        }
        conductoresID.add(conductor.getId());
        conductoresLicencias.add(conductor.getLicencia());
        conductores.add(conductor);
    }

    public static LocalDateTime getFecha() {
        return fechaHora;
    }
}
