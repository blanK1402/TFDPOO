package interfaz;

import javax.swing.*;

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

public class VentanaReserva extends JDialog {

	private JTextField txtNumeroReserva;
    private JComboBox<Pasajero> comboPasajeros;
    private JComboBox<String> comboDestinos;
    private JTextField txtFechaDeseada;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Reserva reserva;
    private Terminal terminal;

    public VentanaReserva(JFrame parent, ArrayList<Viaje> listaViajes, ArrayList<Pasajero> listaPasajeros, Terminal terminal2) {
        super(parent, "Crear Nueva Reserva", true);
        setSize(400, 250);
        setLayout(null);
        
        setTerminal(terminal);
        
        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 140;

        JLabel lblNumeroReserva = new JLabel("Número de Reserva:");
        lblNumeroReserva.setBounds(20, 140, 120, 25);
        lblNumeroReserva.setFont(etiquetaFont);
        add(lblNumeroReserva);

        txtNumeroReserva = new JTextField();
        txtNumeroReserva.setBounds(desplazamientoDerecha, 140, 220, 25);
        txtNumeroReserva.setFont(campoFont);
        add(txtNumeroReserva);
        
        JLabel lblPasajero = new JLabel("Seleccionar Pasajero:");
        lblPasajero.setBounds(20, 20, 120, 25);
        lblPasajero.setFont(etiquetaFont);
        add(lblPasajero);

        comboPasajeros = new JComboBox<>(listaPasajeros.toArray(new Pasajero[0]));
        comboPasajeros.setBounds(desplazamientoDerecha, 20, 220, 25);
        add(comboPasajeros);

        JLabel lblViaje = new JLabel("Seleccionar Viaje:");
        lblViaje.setBounds(20, 60, 120, 25);
        lblViaje.setFont(etiquetaFont);
        add(lblViaje);

        comboDestinos = new JComboBox<>();
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
                confirmarReserva();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void setTerminal(Terminal terminal2) {
    	this.terminal = terminal2;
	}

	private void confirmarReserva() {
        try {
            String numeroReserva = txtNumeroReserva.getText().trim();
            Pasajero pasajeroSeleccionado = (Pasajero) comboPasajeros.getSelectedItem();
            String destinoSeleccionado = (String) comboDestinos.getSelectedItem();
            LocalDateTime fechaActual = Terminal.getFechaHora();
            LocalDate fechaDeseada = LocalDate.parse(txtFechaDeseada.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Viaje viaje = terminal.getViaje(destinoSeleccionado, fechaDeseada);
            
            if (viaje == null) {
                int respuesta = JOptionPane.showConfirmDialog(null, "No hay disponibilidad en esa fecha, ¿desea ser agregado a una lista de espera?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    reserva = new Reserva(pasajeroSeleccionado, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada, 0);
                }
            } else {
                reserva = new Reserva(pasajeroSeleccionado, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada, viaje.getAsientos(1).size());
                viaje.addReservas(reserva);
            }
            
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al ingresar los datos. Verifica el formato.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public boolean isConfirmado() {
        return confirmado;
    }

    public Reserva getReserva() {
        return reserva;
    }
}
