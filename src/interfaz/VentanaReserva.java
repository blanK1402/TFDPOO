package interfaz;

import javax.swing.*;

import utilidades.Utilidades;
import clases.Omnibus;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class VentanaReserva extends JDialog {

	private JComboBox<Pasajero> comboPasajeros;
	private JComboBox<String> comboDestinos;
	private JTextField txtFechaDeseada;
	private JButton btnConfirmar, btnCancelar;
	private boolean confirmado = false;
	private Reserva reserva;
	private HashMap<String, ArrayList<Viaje>> destinosViajes;

	public VentanaReserva(JFrame parent, HashMap<String, ArrayList<Viaje>> destinosViajes, ArrayList<Pasajero> listaPasajeros, final LocalDateTime fechaA) {
		super(parent, "Crear Nueva Reserva", true);
		setSize(400, 250);
		setLayout(null);

		this.destinosViajes = destinosViajes;

		Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
		Font campoFont = new Font("Arial", Font.PLAIN, 14);
		int desplazamientoDerecha = 140;

		JLabel lblPasajero = new JLabel("Seleccionar Pasajero:");
		lblPasajero.setBounds(20, 20, 120, 25);
		lblPasajero.setFont(etiquetaFont);
		add(lblPasajero);

		comboPasajeros = new JComboBox<>(listaPasajeros.toArray(new Pasajero[0]));
		comboPasajeros.setBounds(desplazamientoDerecha, 20, 220, 25);
		add(comboPasajeros);

		JLabel lblViaje = new JLabel("Seleccionar Destino:");
		lblViaje.setBounds(20, 60, 120, 25);
		lblViaje.setFont(etiquetaFont);
		add(lblViaje);

		comboDestinos = new JComboBox<>();
		for(String d : destinosViajes.keySet()){
			comboDestinos.addItem(d);
		}
		comboDestinos.setBounds(desplazamientoDerecha, 60, 220, 25);
		add(comboDestinos);

		JLabel lblFechaDeseada = new JLabel("Fecha Deseada:");
		lblFechaDeseada.setBounds(20, 100, 120, 25);
		lblFechaDeseada.setFont(etiquetaFont);
		add(lblFechaDeseada);

		txtFechaDeseada = new JTextField();
		txtFechaDeseada.setBounds(desplazamientoDerecha, 100, 220, 25);
		txtFechaDeseada.setFont(campoFont);
		add(txtFechaDeseada);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(70, 180, 120, 30);
		add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(210, 180, 120, 30);
		add(btnCancelar);

		setLocationRelativeTo(parent);

		btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarReserva(fechaA);
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void confirmarReserva(LocalDateTime fechaAct) {
	    String numeroReserva = Terminal.getIdReserva();
	    Pasajero pasajeroSeleccionado = (Pasajero) comboPasajeros.getSelectedItem();
	    String destinoSeleccionado = (String) comboDestinos.getSelectedItem();
	    LocalDateTime fechaActual = fechaAct;
	    LocalDate fechaDeseada = LocalDate.parse(txtFechaDeseada.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

	    Viaje viaje = Utilidades.buscarViaje(destinosViajes.get(destinoSeleccionado), fechaDeseada);

	    if (viaje == null) {
	        manejarReservaListaEspera(pasajeroSeleccionado, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada);
	    } else {
	    	reserva = new Reserva(pasajeroSeleccionado, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada, viaje.getAsiento());
	        reserva.setViaje(viaje);
		    confirmado = true;
		    viaje.addReservas(reserva);
	    }
	    
	    dispose();
	}

	private void manejarReservaListaEspera(Pasajero pasajero, String numeroReserva, String destino, LocalDateTime fechaActual, LocalDate fechaDeseada) {
	    int respuesta = JOptionPane.showConfirmDialog(null, "No hay disponibilidad en esa fecha, ¿desea añadir la reserva a una lista de espera?", "Confirmar", JOptionPane.YES_NO_OPTION);

	    if (respuesta == JOptionPane.YES_OPTION) {
	        reserva = new Reserva(pasajero, numeroReserva, destino, fechaActual, fechaDeseada, 0);
	        confirmado = true;
	    } else {
	        dispose();
	    }
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public Reserva getReserva() {
		return reserva;
	}
}
