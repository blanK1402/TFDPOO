package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import login.Usuario;

public class Datos {

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

		try (BufferedReader txt = new BufferedReader(new FileReader("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\src\\utilidades\\a.txt"))) {
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

	public static void importarDatos(Terminal t) throws FileNotFoundException, IOException{
		importarPasajeros(t);
		importarConductores(t);
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
}

