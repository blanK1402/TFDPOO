package utilidades;

import Interfaces.Mostrable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;
import login.Usuario;

public class Datos {

	public static void importarDatos() throws FileNotFoundException, IOException{
		Terminal t = Terminal.getTerminal();
		importarPasajeros(t);
		importarConductores(t);
		importarOmnibus(t);
		importarViajes(t);
		importarReservas(t);
	}

	public static ArrayList<String> obtenerLineas(String rutaArchivo) throws IOException {
		ArrayList<String> lineas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				lineas.add(linea);
			}
		}

		return lineas;
	}

	public static void cargarContrasenas(HashMap<String, Usuario> contrasenas) throws IllegalArgumentException, IOException {
		Pattern patron = Pattern.compile("([0-9]+):(.*),(.*)");

		try (BufferedReader txt = new BufferedReader(new FileReader(".\\.\\BaseDatos\\contrasenas.txt"))) {
			String linea;
			
			while ((linea = txt.readLine()) != null) {
				Matcher coincidencia = patron.matcher(linea);
				if (coincidencia.find()) {
					Usuario usuario = new Usuario(coincidencia.group(1), coincidencia.group(2), coincidencia.group(3));	
					contrasenas.put(usuario.getUsuario(), usuario);
				}
			}
		} 
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void cargarReservasUsuario(ArrayList<Reserva> reservas, DefaultTableModel modelReserva) {
		modelReserva.setRowCount(0);
		for(Reserva r : reservas){
			modelReserva.addRow(r.toTableList());
		}
	}

	private static void importarPasajeros(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*)");

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\pasajeros.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){

				Pasajero p = new Pasajero(matcher.group(1), matcher.group(2), Terminal.getFecha().toLocalDate());
				t.addPasajero(p);
			}
		}
	}

	private static void importarConductores(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("([a-zA-Z]+),([0-9]+),([A]|[B]|[C]),([0-9]+),([0-9]+)");

		//(String nombre, String id, String experiencia, String licencia)
		//Diana,1,A,36,84719

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\conductores.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){

				String nombre = matcher.group(1);
				String id = matcher.group(2);
				String exp = matcher.group(4);
				String licencia = matcher.group(5);

				if(matcher.group(3).equals("A")) {  
					t.addConductor(new ConductorA(nombre, id, exp, licencia));
				}
				else if(matcher.group(3).equals("B")){
					t.addConductor(new ConductorB(nombre, id, exp, licencia));
				}
				else{
					t.addConductor(new ConductorC(nombre, id, exp, licencia));
				}
			}
		}
	}

	//(String matricula, String asientos, String disponibilidad, ArrayList<String> comodidades)
	//T976211,94,No,Si,No,Disponible,[id:2, Selene, id:6, Hugo]

	private static void importarOmnibus(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("^([A-Z]{1}[0-9]{6}),([0-9]*),([Si]{2}|[No]{2}),([Si]{2}|[No]{2}),([Si]{2}|[No]{2}),([^,]+),(.*)$");
		Pattern patronId = Pattern.compile("id:([0-9]+)");

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\omnibuses.txt")){

			Matcher matcher = patron.matcher(linea);

			if(matcher.find()){
				Matcher matcherId = patronId.matcher(matcher.group(7));
				String matricula = matcher.group(1);
				String asientos = matcher.group(2);

				ArrayList<String> comodidades = new ArrayList<>();				
				if (matcher.group(3).equals("Si")) {
					comodidades.add("Aire acondicionado");
				}
				if (matcher.group(4).equals("Si")) {
					comodidades.add("TV");
				}
				if (matcher.group(5).equals("Si")) {
					comodidades.add("Baño");
				}

				String estado = matcher.group(6);

				Omnibus o = new Omnibus(matricula, asientos, estado, comodidades);

				while(matcherId.find()){
					o.addConductor(t.getConductor(matcherId.group(1)));
				}
				t.addOmnibus(o);
			}
		}

	}

	//2,Ciego de Ávila,V110538,id:5, Esmeralda,22/06/2025, 08:41:56,391.0
	//(String id, String fechaPartida, String horaPartida, String destino, Omnibus omnibus, Conductor conductor){
	private static void importarViajes(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*),(.*),id:(.*), (.*),(.*), (.*),(.*)");

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\viajes.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){
				String id = matcher.group(1);
				String destino = matcher.group(2);
				String matricula = matcher.group(3);
				String idConductor = matcher.group(4);
				String fecha = matcher.group(6);
				String hora = matcher.group(7);

				Viaje v = new Viaje(id, fecha, hora, destino, t.getOmnibus(matricula), t.getConductor(idConductor));
				t.addViaje(v);
			}
		}
	}

	//"Pasajero", "Nro Reservación", "Viaje", "Asiento", "Destino", "Fecha Reservación", "Fecha Viaje", "Estado"
	private static void importarReservas(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("id: ([0-9]+),([0-9]+),([0-9]+),([0-9]+),(.*),([0-9/]+),([0-9/]+),(.*)");

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\reservas.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){
				Pasajero p = t.getPasajero(matcher.group(1));
				String destino = matcher.group(5);
				String numReserva = matcher.group(2);
				LocalDate fechaActual = Utilidades.parsearFecha(matcher.group(6));
				LocalDate fechaDeseada = Utilidades.parsearFecha(matcher.group(7));
				Viaje v = null;
				
				if(matcher.group(3).matches("[0-9]")){
					v = t.getViaje(matcher.group(3));
				}

				Reserva r = new Reserva(p, numReserva, destino, fechaActual, fechaDeseada, 0);
				
				if(v != null){
					r.setViaje(v);
					v.addReservas(r);
				}
				t.addReserva(r);
			}
		}
	}


	public static void guardarDatos() throws IOException{
		Terminal t = Terminal.getTerminal();
		String[] rutas = {
				".\\.\\BaseDatos\\pasajeros.txt",
				".\\.\\BaseDatos\\conductores.txt",
				".\\.\\BaseDatos\\omnibuses.txt",
				".\\.\\BaseDatos\\viajes.txt",
				".\\.\\BaseDatos\\reservas.txt",
				".\\.\\BaseDatos\\contrasenas.txt"
		};

		escribirArchivo(t.getPasajeros(), rutas[0]);
		escribirArchivo(t.getConductores(), rutas[1]);
		escribirArchivo(t.getOmnibuses(), rutas[2]);
		escribirArchivo(t.getViajes(), rutas[3]);
		escribirArchivo(t.getReservas(), rutas[4]);
		escribirArchivo(t.getUsuarios(), rutas[5]);

	}

	private static <T extends Mostrable> void escribirArchivo(ArrayList<T> lista, String ruta) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
			for (T item : lista) {
				writer.write(String.join(",", item.toTableList()) + "\n");
			}
		}
	}
	
	public static void actualizarPasajeros(DefaultTableModel modelPasajero, Terminal terminal) {
	    modelPasajero.setRowCount(0); 
	    for(Pasajero p : terminal.getPasajeros()) {
	        modelPasajero.addRow(p.toTableList());
	    }
	}

	public static void actualizarConductores(DefaultTableModel modelConductor, Terminal terminal) {
	    modelConductor.setRowCount(0);
	    for(Conductor c : terminal.getConductores()) {
	        modelConductor.addRow(c.toTableList());
	    }
	}

	public static void actualizarOmnibuses(DefaultTableModel modelOmnibus, Terminal terminal) {
	    modelOmnibus.setRowCount(0);
	    for(Omnibus o : terminal.getOmnibuses()) {
	        modelOmnibus.addRow(o.toTableList());
	    }
	}

	public static void actualizarViajes(DefaultTableModel modelViaje, Terminal terminal) {
	    modelViaje.setRowCount(0);
	    for(Viaje v : terminal.getViajes()) {
	        modelViaje.addRow(v.toTableList());
	    }
	}

	public static void actualizarReservas(DefaultTableModel modelReserva, Terminal terminal) {
	    modelReserva.setRowCount(0);
	    for(Reserva r : terminal.getReservas()) {
	        modelReserva.addRow(r.toTableList());
	    }
	}

}

