package utilidades;

import Interfaces.mostrable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
import login.Usuario;

public class Datos {

	public static void importarDatos(Terminal t) throws FileNotFoundException, IOException{
		importarPasajeros(t);
		importarConductores(t);
		importarOmnibus(t);
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

	public static void cargarContrasenas(HashMap<ArrayList<String>, Usuario> contrasenas) throws IllegalArgumentException, IOException {
		Pattern patron = Pattern.compile("^(\\w+):([^,]+),([^,]+)$");

		try (BufferedReader txt = new BufferedReader(new FileReader(".\\.\\BaseDatos\\contrasenas.txt"))) {
			String linea;
			while ((linea = txt.readLine()) != null) {
				Matcher coincidencia = patron.matcher(linea);
				if (coincidencia.matches()) { 
					ArrayList<String> usuarioContrasena = new ArrayList<String>();
					usuarioContrasena.add(coincidencia.group(2));
					usuarioContrasena.add(coincidencia.group(3));
					Usuario usuario = new Usuario(usuarioContrasena.get(0), usuarioContrasena.get(1), coincidencia.group(1));	
					contrasenas.put(usuarioContrasena, usuario);
				}
			}
		} 
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static void cargarReservasUsuario(ArrayList<Reserva> reservas, DefaultTableModel modelReserva) {
		for(Reserva r : reservas){
			modelReserva.addRow(r.toTableList());
		}
	}

	private static void importarPasajeros(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*)");

		for(String linea : obtenerLineas(".\\.\\BaseDatos\\pasajeros.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){

				Pasajero p = new Pasajero(matcher.group(1), matcher.group(2), t.getFecha().toLocalDate());
				t.addPasajero(p);
			}
		}
	}

	private static void importarConductores(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*),(.*),(.*),(.*)");

		//(String nombre, String id, String experiencia, String licencia)
		//Diana,1,A,36,84719
		for(String linea : obtenerLineas(".\\.\\BaseDatos\\conductores.txt")){
			Matcher matcher = patron.matcher(linea);
			if(matcher.find()){
				String nombre = matcher.group(1);
				String id = matcher.group(2);
				String exp = matcher.group(4);
				String licencia = matcher.group(5);

				switch(matcher.group(3)) {  
				case "A": 
					t.addConductor(new ConductorA(nombre, id, exp, licencia));
					break;
				case "B": 
					t.addConductor(new ConductorB(nombre, id, exp, licencia));
					break;
				case "C": 
					t.addConductor(new ConductorC(nombre, id, exp, licencia));
					break;
				}
			}
		}
	}

	//(String matricula, String asientos, String disponibilidad, ArrayList<String> comodidades)
	//T976211,94,No,Si,No,Disponible,[id:2, Selene, id:6, Hugo]

	private static void importarOmnibus(Terminal t) throws FileNotFoundException, IOException {
		Pattern patron = Pattern.compile("([A-Z][0-9]{6}),([0-9]*),(Si|No),(Si|No),(Si|No),([a-zA-Z]*),(.*)");

		try{
			for(String linea : obtenerLineas(".\\.\\BaseDatos\\omnibuses.txt")){
				Matcher matcher = patron.matcher(linea);

				if(matcher.find()){
					String matricula = matcher.group(1);
					String asientos = matcher.group(2);

					ArrayList<String> comodidades = new ArrayList<>();

					if ("Si".equals(matcher.group(3))) {
						comodidades.add("Aire acondicionado");
					}
					if ("Si".equals(matcher.group(4))) {
						comodidades.add("TV");
					}
					if ("Si".equals(matcher.group(5))) {
						comodidades.add("Baño");
					}

					String estado = matcher.group(6);

					Omnibus o = new Omnibus(matricula, asientos, estado, comodidades);

					o.addConductor(t.getConductor("1"));
					t.addOmnibus(o);
				}
			}
		}
		catch(Exception e){
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	public static void guardarDatos(Terminal t) throws IOException{
		String[] rutas = {
				".\\.\\BaseDatos\\pasajeros.txt",
				".\\.\\BaseDatos\\conductores.txt",
				".\\.\\BaseDatos\\omnibuses.txt",
				".\\.\\BaseDatos\\viajes.txt",
				".\\.\\BaseDatos\\reservas.txt"
		};
			
		escribirArchivo(t.getPasajeros(), rutas[0]);
		escribirArchivo(t.getConductores(), rutas[1]);
		escribirArchivo(t.getOmnibuses(), rutas[2]);
		escribirArchivo(t.getViajes2(), rutas[3]);
		escribirArchivo(t.getReservas(), rutas[4]);
		
	}

	private static <T extends mostrable> void escribirArchivo(ArrayList<T> lista, String ruta) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
			for (T item : lista) {
				writer.write(String.join(",", item.toTableList()) + "\n");
			}
		}
	}

}

