package runner;

import interfaz.Interfaz;
import interfaz.InterfazUsuario;

import java.awt.EventQueue;

import utilidades.Datos;
import clases.Pasajero;
import clases.Terminal;
import login.Login;

public class Runner {
	public static void main(String[] args) {
		Terminal terminal = Terminal.getTerminal();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(Terminal.getTerminal());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void lanzarInterfazUsuario(final Pasajero pasajero, final Terminal t) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Datos.importarDatos();
					InterfazUsuario frame = new InterfazUsuario(pasajero);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}