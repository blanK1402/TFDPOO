package interfaz;

import javax.swing.*;

import clases.Conductor;
import clases.Omnibus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaOmnibus extends JDialog {

    private JTextField txtMatricula, txtAsientos;
    private JCheckBox chkAire, chkTelevisor, chkBaño;
    private JComboBox<String> comboDisponibilidad;
    private JComboBox<Conductor> comboConductor1, comboConductor2, comboConductor3;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Omnibus omnibus;

    public VentanaOmnibus(JFrame parent, ArrayList<Conductor> listaConductores) {
        super(parent, "Crear Nuevo Ómnibus", true);
        setSize(400, 400);
        setLayout(null);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 140;

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(20, 20, 120, 25);
        lblMatricula.setFont(etiquetaFont);
        add(lblMatricula);

        txtMatricula = new JTextField();
        txtMatricula.setBounds(desplazamientoDerecha, 20, 220, 25);
        txtMatricula.setFont(campoFont);
        add(txtMatricula);

        JLabel lblAsientos = new JLabel("Cantidad de Asientos:");
        lblAsientos.setBounds(20, 60, 120, 25);
        lblAsientos.setFont(etiquetaFont);
        add(lblAsientos);

        txtAsientos = new JTextField();
        txtAsientos.setBounds(desplazamientoDerecha, 60, 220, 25);
        txtAsientos.setFont(campoFont);
        add(txtAsientos);

        JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
        lblDisponibilidad.setBounds(20, 100, 120, 25);
        lblDisponibilidad.setFont(etiquetaFont);
        add(lblDisponibilidad);

        comboDisponibilidad = new JComboBox<>(new String[]{"Disponible", "En carretera", "En reparación"});
        comboDisponibilidad.setBounds(desplazamientoDerecha, 100, 220, 25);
        add(comboDisponibilidad);

        JLabel lblComodidades = new JLabel("Comodidades:");
        lblComodidades.setBounds(20, 140, 120, 25);
        lblComodidades.setFont(etiquetaFont);
        add(lblComodidades);

        chkAire = new JCheckBox("Aire acondicionado");
        chkAire.setBounds(desplazamientoDerecha, 140, 220, 25);
        add(chkAire);

        chkTelevisor = new JCheckBox("Televisor");
        chkTelevisor.setBounds(desplazamientoDerecha, 170, 220, 25);
        add(chkTelevisor);

        chkBaño = new JCheckBox("Baño");
        chkBaño.setBounds(desplazamientoDerecha, 200, 220, 25);
        add(chkBaño);

        JLabel lblConductores = new JLabel("Conductores (máx. 3):");
        lblConductores.setBounds(20, 230, 120, 25);
        lblConductores.setFont(etiquetaFont);
        add(lblConductores);

        comboConductor1 = new JComboBox<Conductor>(listaConductores.toArray(new Conductor[0]));
        comboConductor1.addItem(null);
        comboConductor1.setSelectedItem(null);
        comboConductor1.setBounds(desplazamientoDerecha, 230, 220, 25);
        add(comboConductor1);

        comboConductor2 = new JComboBox<>(listaConductores.toArray(new Conductor[0]));
        comboConductor2.addItem(null);
        comboConductor2.setSelectedItem(null);
        comboConductor2.setBounds(desplazamientoDerecha, 260, 220, 25);
        add(comboConductor2);

        comboConductor3 = new JComboBox<>(listaConductores.toArray(new Conductor[0]));
        comboConductor3.addItem(null);
        comboConductor3.setSelectedItem(null);
        comboConductor3.setBounds(desplazamientoDerecha, 290, 220, 25);
        add(comboConductor3);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(70, 330, 120, 30);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(210, 330, 120, 30);
        add(btnCancelar);

        setLocationRelativeTo(parent);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarOmnibus();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void confirmarOmnibus() {
        try {
            String matricula = txtMatricula.getText().trim();
            if (matricula.isEmpty()) {
                throw new Exception("La matrícula no puede estar vacía.");
            }

            String asientos = txtAsientos.getText().trim();
            if (asientos.isEmpty()) {
                throw new Exception("Debe especificar el número de asientos.");
            }

            String disponibilidad = (String) comboDisponibilidad.getSelectedItem();
            if (disponibilidad == null || disponibilidad.isEmpty()) {
                throw new Exception("Debe seleccionar una opción de disponibilidad.");
            }

            ArrayList<String> comodidades = new ArrayList<>();
            if (chkAire.isSelected()) comodidades.add("Aire acondicionado");
            if (chkTelevisor.isSelected()) comodidades.add("TV");
            if (chkBaño.isSelected()) comodidades.add("Baño");

            ArrayList<Conductor> conductoresSeleccionados = new ArrayList<>();
            if (comboConductor1.getSelectedItem() != null) {
                conductoresSeleccionados.add((Conductor) comboConductor1.getSelectedItem());
            }
            if (comboConductor2.getSelectedItem() != null) {
                conductoresSeleccionados.add((Conductor) comboConductor2.getSelectedItem());
            }
            if (comboConductor3.getSelectedItem() != null) {
                conductoresSeleccionados.add((Conductor) comboConductor3.getSelectedItem());
            }

            if (conductoresSeleccionados.isEmpty()) {
                throw new Exception("Debe seleccionar al menos un conductor.");
            }

            omnibus = new Omnibus(matricula, asientos, disponibilidad, comodidades);
            for (Conductor conductor : conductoresSeleccionados) {
                omnibus.addConductor(conductor);
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

    public Omnibus getOmnibus() {
        return omnibus;
    }
}
