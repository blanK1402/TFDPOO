package interfaz;

import javax.swing.*;

import utilidades.FechaUtils;
import utilidades.Utilidades;
import utilidades.ValidacionUtils;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;
import gestion.GestorReservas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class VentanaReserva extends JDialog {

    private JLabel labelPasajero;
    private JComboBox<String> comboDestinos;
    private JTextField txtFechaDeseada;
    private JButton btnConfirmar, btnCancelar;
    private JLabel lblIdReserva;
    private boolean confirmado = false;
    private Reserva reserva;
    private Terminal t;
    private Pasajero pasajero;

    public VentanaReserva(JDialog parent, Pasajero pasajero) {
        super(parent, "Crear Nueva Reserva", true);
        setTerminal();
        setPasajero(pasajero);
        setSize(400, 300);  
        setLayout(null);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 140;

        JLabel lblIdTitulo = new JLabel("ID Reserva:");
        lblIdTitulo.setBounds(20, 20, 120, 25);
        lblIdTitulo.setFont(etiquetaFont);
        add(lblIdTitulo);

        lblIdReserva = new JLabel("Nuevo ID será generado");
        lblIdReserva.setBounds(desplazamientoDerecha, 20, 220, 25);
        lblIdReserva.setFont(campoFont);
        add(lblIdReserva);

        JLabel lblPasajero = new JLabel("Pasajero:");
        lblPasajero.setBounds(20, 60, 120, 25);  
        lblPasajero.setFont(etiquetaFont);
        add(lblPasajero);

        labelPasajero = new JLabel(pasajero.toString());
        labelPasajero.setBounds(desplazamientoDerecha, 60, 220, 25);  
        add(labelPasajero);

        JLabel lblViaje = new JLabel("Seleccionar Destino:");
        lblViaje.setBounds(20, 100, 120, 25);  
        lblViaje.setFont(etiquetaFont);
        add(lblViaje);

        comboDestinos = new JComboBox<>();
        for(String d : t.getDestinosDistancias().keySet()){
            comboDestinos.addItem(d);
        }
        comboDestinos.setBounds(desplazamientoDerecha, 100, 220, 25);  
        add(comboDestinos);

        JLabel lblFechaDeseada = new JLabel("Fecha Deseada:");
        lblFechaDeseada.setBounds(20, 140, 120, 25);  
        lblFechaDeseada.setFont(etiquetaFont);
        add(lblFechaDeseada);

        txtFechaDeseada = new JTextField();
        txtFechaDeseada.setBounds(desplazamientoDerecha, 140, 220, 25);  
        txtFechaDeseada.setFont(campoFont);
        add(txtFechaDeseada);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(70, 220, 120, 30);  
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(210, 220, 120, 30);  
        add(btnCancelar);

        setLocationRelativeTo(parent);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarReserva(t.getFecha());
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    private void setTerminal() {
        this.t = Terminal.getTerminal();
    }

    private void confirmarReserva(LocalDateTime fechaAct) {
        try {
            String numeroReserva = String.valueOf(t.getNextIdReservas());
            
            String destinoSeleccionado = (String) comboDestinos.getSelectedItem();
            LocalDate fechaActual = Terminal.getTerminal().getFecha().toLocalDate();
            LocalDate fechaDeseada = LocalDate.parse(txtFechaDeseada.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            ValidacionUtils.validarFecha(fechaDeseada);

            Viaje viaje = Utilidades.buscarViaje(destinoSeleccionado, fechaDeseada);

            if (viaje == null) {
                manejarReservaListaEspera(pasajero, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada);
            } else {
                reserva = new Reserva(pasajero, numeroReserva, destinoSeleccionado, fechaActual, fechaDeseada, viaje.getAsiento());
                reserva.setViaje(viaje);
            }

            t.addReserva(reserva);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manejarReservaListaEspera(Pasajero pasajero, String numeroReserva, String destino, LocalDate fechaActual, LocalDate fechaDeseada) {
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "No hay disponibilidad en esa fecha, ¿desea añadir la reserva a una lista de espera?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            reserva = new Reserva(pasajero, numeroReserva, destino, fechaActual, fechaDeseada, 0);
            reserva.setEstado("En espera");
            confirmado = true;
        } else {
            dispose();
        }
    }

    public boolean Confirmada() {
        return confirmado;
    }

    public Reserva getReserva() {
        return reserva;
    }
}