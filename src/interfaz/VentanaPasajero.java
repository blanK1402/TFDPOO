package interfaz;

import javax.swing.*;

import clases.Pasajero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class VentanaPasajero extends JDialog {

    private JTextField txtNombre, txtId;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Pasajero pasajero;

    public VentanaPasajero(JFrame parent, ArrayList<Pasajero> listaPasajeros, final LocalDate fecha) {
        super(parent, "Crear Nuevo Pasajero", true);
        setSize(400, 250);
        setLayout(null);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 120;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        lblNombre.setFont(etiquetaFont);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(desplazamientoDerecha, 20, 220, 25);
        txtNombre.setFont(campoFont);
        add(txtNombre);

        JLabel lblId = new JLabel("Carné ID:");
        lblId.setBounds(20, 60, 100, 25);
        lblId.setFont(etiquetaFont);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(desplazamientoDerecha, 60, 220, 25);
        txtId.setFont(campoFont);
        add(txtId);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(70, 120, 120, 30);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(210, 120, 120, 30);
        add(btnCancelar);

        setLocationRelativeTo(parent);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarPasajero(fecha);
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void confirmarPasajero(LocalDate fecha) {
        try {
            String nombre = txtNombre.getText();
            String id = txtId.getText();
            if(id.length() != 11){
            	throw new IllegalArgumentException("El carnet debe tener 11 digitos");
            }
            pasajero = new Pasajero(nombre, id, fecha);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}
