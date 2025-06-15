package interfaz;

import javax.swing.*;

import clases.Conductor;
import clases.Omnibus;
import clases.Terminal;

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
    Terminal t;

    public VentanaOmnibus(Interfaz interfaz) {
        super(interfaz, "Crear Nuevo Ómnibus", true);
        setT();
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

        comboConductor1 = new JComboBox<Conductor>(t.getConductores().toArray(new Conductor[0]));
        comboConductor1.addItem(null);
        comboConductor1.setSelectedItem(null);
        comboConductor1.setBounds(desplazamientoDerecha, 230, 220, 25);
        add(comboConductor1);

        comboConductor2 = new JComboBox<>(t.getConductores().toArray(new Conductor[0]));
        comboConductor2.addItem(null);
        comboConductor2.setSelectedItem(null);
        comboConductor2.setBounds(desplazamientoDerecha, 260, 220, 25);
        add(comboConductor2);

        comboConductor3 = new JComboBox<>(t.getConductores().toArray(new Conductor[0]));
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

        setLocationRelativeTo(interfaz);

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
    
    public VentanaOmnibus(Interfaz interfaz, String id) {
        super(interfaz, "Crear Nuevo Ómnibus", true);
        setT();
        setOmnibus(id);
        setSize(400, 400);
        setLayout(null);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 14);
        Font campoFont = new Font("Arial", Font.PLAIN, 14);
        int desplazamientoDerecha = 140;

        JLabel lblMatricula = new JLabel("Matrícula:");
        lblMatricula.setBounds(20, 20, 120, 25);
        lblMatricula.setFont(etiquetaFont);
        add(lblMatricula);

        txtMatricula = new JTextField(id);
        txtMatricula.setEditable(false);
        txtMatricula.setBounds(desplazamientoDerecha, 20, 220, 25);
        txtMatricula.setFont(campoFont);
        add(txtMatricula);

        JLabel lblAsientos = new JLabel("Cantidad de Asientos:");
        lblAsientos.setBounds(20, 60, 120, 25);
        lblAsientos.setFont(etiquetaFont);
        add(lblAsientos);

        txtAsientos = new JTextField(String.valueOf(omnibus.getAsientos()));
        txtAsientos.setBounds(desplazamientoDerecha, 60, 220, 25);
        txtAsientos.setFont(campoFont);
        add(txtAsientos);

        JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
        lblDisponibilidad.setBounds(20, 100, 120, 25);
        lblDisponibilidad.setFont(etiquetaFont);
        add(lblDisponibilidad);

        comboDisponibilidad = new JComboBox<>(new String[]{"Disponible", "En carretera", "En reparación"});
        comboDisponibilidad.setSelectedIndex(omnibus.getDisponibilidad().equals("Disponible") ? 0 : omnibus.getDisponibilidad().equals("En carretera") ? 1 : 2);
        comboDisponibilidad.setBounds(desplazamientoDerecha, 100, 220, 25);
        add(comboDisponibilidad);

        JLabel lblComodidades = new JLabel("Comodidades:");
        lblComodidades.setBounds(20, 140, 120, 25);
        lblComodidades.setFont(etiquetaFont);
        add(lblComodidades);

        chkAire = new JCheckBox("Aire acondicionado");
        chkAire.setBounds(desplazamientoDerecha, 140, 220, 25);
        chkAire.setSelected(omnibus.getComodidades().contains("Aire acondicionado"));
        add(chkAire);

        chkTelevisor = new JCheckBox("Televisor");
        chkTelevisor.setBounds(desplazamientoDerecha, 170, 220, 25);
        chkTelevisor.setSelected(omnibus.getComodidades().contains("TV"));
        add(chkTelevisor);

        chkBaño = new JCheckBox("Baño");
        chkBaño.setBounds(desplazamientoDerecha, 200, 220, 25);
        chkBaño.setSelected(omnibus.getComodidades().contains("Baño"));
        add(chkBaño);

        JLabel lblConductores = new JLabel("Conductores (máx. 3):");
        lblConductores.setBounds(20, 230, 120, 25);
        lblConductores.setFont(etiquetaFont);
        add(lblConductores);

        ArrayList<Conductor> disponibles = new ArrayList<>(t.getConductores());
        disponibles.add(0, null);

        comboConductor1 = new JComboBox<>(disponibles.toArray(new Conductor[0]));
        comboConductor1.setBounds(desplazamientoDerecha, 230, 220, 25);
        comboConductor1.setSelectedItem(omnibus.getConductores().size() > 0 ? omnibus.getConductores().get(0) : null);
        add(comboConductor1);

        comboConductor2 = new JComboBox<>(disponibles.toArray(new Conductor[0]));
        comboConductor2.setBounds(desplazamientoDerecha, 260, 220, 25);
        comboConductor2.setSelectedItem(omnibus.getConductores().size() > 1 ? omnibus.getConductores().get(1) : null);
        add(comboConductor2);

        comboConductor3 = new JComboBox<>(disponibles.toArray(new Conductor[0]));
        comboConductor3.setBounds(desplazamientoDerecha, 290, 220, 25);
        comboConductor3.setSelectedItem(omnibus.getConductores().size() > 2 ? omnibus.getConductores().get(2) : null);
        add(comboConductor3);

        btnConfirmar = new JButton("Guardar");
        btnConfirmar.setBounds(70, 330, 120, 30);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(210, 330, 120, 30);
        add(btnCancelar);

        setLocationRelativeTo(interfaz);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarOmnibus();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    protected void editarOmnibus() {
    	try {
            String asientos = txtAsientos.getText().trim();
            String disponibilidad = (String) comboDisponibilidad.getSelectedItem();

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

            omnibus = new Omnibus(omnibus.getMatricula(), asientos, disponibilidad, comodidades);
            for (Conductor conductor : conductoresSeleccionados) {
                omnibus.addConductor(conductor);
            }

            Terminal.getTerminal().addOmnibus(omnibus);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}

	private void setOmnibus(String id) {
		omnibus = Terminal.getTerminal().getOmnibus(id);
	}

	private void setT() {
		this.t = Terminal.getTerminal();
	}

	private void confirmarOmnibus() {
        try {
            String matricula = txtMatricula.getText().trim();
            String asientos = txtAsientos.getText().trim();
            String disponibilidad = (String) comboDisponibilidad.getSelectedItem();

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


    public boolean confirmado() {
        return confirmado;
    }

    public Omnibus getOmnibus() {
        return omnibus;
    }
}
