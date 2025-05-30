package interfaz;

import javax.swing.*;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Terminal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaConductor extends JDialog {

    private JTextField txtNombre, txtExperiencia, txtLicencia;
    private JComboBox<String> comboCategoria;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Conductor conductor;

    public VentanaConductor(JFrame parent, ArrayList<Conductor> listaConductores) {
        super(parent, "Crear Nuevo Conductor", true);
        setSize(400, 300);
        setLayout(null);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 150;

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 120, 25);
        lblNombre.setFont(etiquetaFont);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(desplazamientoDerecha, 20, 220, 25);
        txtNombre.setFont(campoFont);
        add(txtNombre);

        JLabel lblExperiencia = new JLabel("Experiencia:");
        lblExperiencia.setBounds(20, 100, 120, 25);
        lblExperiencia.setFont(etiquetaFont);
        add(lblExperiencia);

        txtExperiencia = new JTextField();
        txtExperiencia.setBounds(desplazamientoDerecha, 100, 220, 25);
        txtExperiencia.setFont(campoFont);
        add(txtExperiencia);

        JLabel lblLicencia = new JLabel("Licencia:");
        lblLicencia.setBounds(20, 140, 120, 25);
        lblLicencia.setFont(etiquetaFont);
        add(lblLicencia);

        txtLicencia = new JTextField();
        txtLicencia.setBounds(desplazamientoDerecha, 140, 220, 25);
        txtLicencia.setFont(campoFont);
        add(txtLicencia);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 180, 120, 25);
        lblCategoria.setFont(etiquetaFont);
        add(lblCategoria);

        comboCategoria = new JComboBox<>(new String[]{"A", "B", "C"});
        comboCategoria.setBounds(desplazamientoDerecha, 180, 220, 25);
        add(comboCategoria);

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
                confirmarConductor();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void confirmarConductor() {
        try {
            String nombre = txtNombre.getText();
            String id = String.valueOf(Terminal.getIdConductor());
            String experiencia = txtExperiencia.getText();
            String licencia = txtLicencia.getText();
            String categoria = (String) comboCategoria.getSelectedItem();

            if (categoria.equals("A")) {
                conductor = new ConductorA(nombre, id, experiencia, licencia);
            } else if (categoria.equals("B")) {
                conductor = new ConductorB(nombre, id, experiencia, licencia);
            } else {
                conductor = new ConductorC(nombre, id, experiencia, licencia);
            }

            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Conductor getConductor() {
        return conductor;
    }
}
