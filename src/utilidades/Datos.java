package utilidades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;

public class Datos {

	public static void cargarDatos(Terminal terminal){
		try{
			Pattern patronPasajero = Pattern.compile("(.*),(.*)");
			cargarConductores(terminal);
			cargarPasajeros(terminal);
			cargarViajes(terminal);
			cargarOmnibuses(terminal);
		}
		catch(Exception e){

		}

	}

	private static void cargarConductores(Terminal terminal) throws IllegalArgumentException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*),(.*),(.*)(.*)");
		try {
			BufferedReader txtConductores = new BufferedReader(new FileReader("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\conductores.txt"));
			String linea;
			while((linea = txtConductores.readLine()) != null){
				Matcher matcher = patron.matcher(linea);
				if(matcher.group(2).equals("A")){
					terminal.addConductor(new ConductorA(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
				}
				else if(matcher.group(2).equals("B")){
					terminal.addConductor(new ConductorB(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
				}
				else{
					terminal.addConductor(new ConductorC(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}

	private static void cargarPasajeros(Terminal terminal) throws IOException {
		Pattern patron = Pattern.compile("(.*),(.*)");
		try {
			BufferedReader txtPasajeros = new BufferedReader(new FileReader("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\pasajeros.txt"));
			String linea;
			while((linea = txtPasajeros.readLine()) != null){
				Matcher matcher = patron.matcher(linea);
				terminal.addPasajero(new Pasajero(matcher.group(1), matcher.group(2), terminal.getFecha().toLocalDate()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void cargarViajes(Terminal terminal) throws IllegalArgumentException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*)");
		try {
			BufferedReader txtViajes = new BufferedReader(new FileReader("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\viajes.txt"));
			String linea;
			while((linea = txtViajes.readLine()) != null){
				Matcher matcher = patron.matcher(linea);
				terminal.addPasajero(new Pasajero(matcher.group(1), matcher.group(2), terminal.getFecha().toLocalDate()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/*
 				comodidades.contains("Aire acondicionado") ? "Si" : "No",
				comodidades.contains("TV") ? "Si" : "No",
				comodidades.contains("Baño") ? "Si" : "No",
	 */
	private static void cargarOmnibuses(Terminal terminal) throws IllegalArgumentException, IOException {
		Pattern patron = Pattern.compile("(.*),(.*),(.*),(.*),(.*),(.*),(.*)");
		Pattern patronId = Pattern.compile("id:([0-9]+)");

		try (BufferedReader txtOmnibus = new BufferedReader(new FileReader("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\omnibuses.txt"))) {
			String linea;
			while ((linea = txtOmnibus.readLine()) != null) {
				Matcher matcher = patron.matcher(linea);
				if (matcher.find()) {
					String conductoresId = matcher.group(7);
					ArrayList<String> comodidades = new ArrayList<>();
					
					if(matcher.group(3).equals("Si")){
						comodidades.add("Aire acondicionado");
					}
					if(matcher.group(4).equals("Si")){
						comodidades.add("TV");
					}
					if(matcher.group(5).equals("Si")){
						comodidades.add("Baño");
					}
					ArrayList<Conductor> conductores = new ArrayList<>();

					Matcher matcherId = patronId.matcher(conductoresId);
					while (matcherId.find()) {
						conductores.add(terminal.getConductor(matcherId.group(1)));
						conductoresId = conductoresId.replace(matcherId.group(1), "");
					}

					Omnibus o = new Omnibus(matcher.group(1), matcher.group(2), conductoresId, comodidades);
					for (Conductor c : conductores) {
						o.addConductor(c);
					}
				}
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Error al cargar datos");
		}
	}


	public static void guardarDatos(Terminal terminal) throws IOException {
		try (
				FileWriter txtReservas = new FileWriter("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\reservas.txt", true);
				FileWriter txtPasajeros = new FileWriter("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\pasajeros.txt", true);
				FileWriter txtOmnibus = new FileWriter("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\omnibuses.txt", true);
				FileWriter txtViajes = new FileWriter("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\viajes.txt", true);
				FileWriter txtConductores = new FileWriter("C:\\Users\\Roger\\Desktop\\DPOO FINAL\\TareaFinal\\BaseDatos\\conductores.txt", true)
				) {
			for (Reserva r : terminal.getReservas()) {
				txtReservas.write(String.join(",", r.toTableList()) + "\n");
			}

			for (Pasajero p : terminal.getPasajeros()) {
				txtPasajeros.write(String.join(",", p.toTableList()) + "\n");
			}

			for (Conductor c : terminal.getConductores()) {
				txtConductores.write(String.join(",", c.toTableList()) + "\n");
			}

			for (Viaje v : terminal.getViajes2()) {
				txtViajes.write(String.join(",", v.toTableList()) + "\n");
			}

			for (Omnibus o : terminal.getOmnibus()) {
				txtOmnibus.write(String.join(",", o.toTableList()) + "\n");
			}
		} catch (IOException e) {
			System.out.println("Error al guardar los datos: " + e.getMessage());
		}
	}

}
