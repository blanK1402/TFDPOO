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
		final Terminal terminal = new Terminal("Terminal");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login(terminal);
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
					Datos.importarDatos(t);
					InterfazUsuario frame = new InterfazUsuario(pasajero);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}