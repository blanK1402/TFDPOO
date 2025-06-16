package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.*;

import clases.Conductor;
import clases.Omnibus;
import clases.Terminal;
import clases.Viaje;

public class VentanaViaje extends JDialog {

    private JLabel lblId;
    private HashMap<String, Integer> destinosDistancias;
    private JTextField txtFechaPartida;
    private JComboBox<Omnibus> comboOmnibus;
    private JComboBox<Conductor> comboConductor;
    private JComboBox<String> comboDestinos;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Viaje viaje;
    private Terminal t;

    public VentanaViaje(Interfaz interfaz) {
        super(interfaz, "Crear Nuevo Viaje", true);
        setT();
        setSize(600, 380);
        setLayout(null);

        destinosDistancias = new HashMap<>(Terminal.getDestinosDistancias());

        Font etiquetaFont = new Font("Arial", Font.BOLD, 16);
        int desplazamientoDerecha = 140;

        lblId = new JLabel(String.valueOf(t.getIdViaje()));
        lblId.setBounds(desplazamientoDerecha + 100, 20, 250, 30);
        add(lblId);

        JLabel lblId2 = new JLabel("ID:");
        lblId2.setBounds(20, 20, 120, 30);
        lblId2.setFont(etiquetaFont);
        add(lblId2);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 60, 120, 30);
        lblDestino.setFont(etiquetaFont);
        add(lblDestino);

        comboDestinos = new JComboBox<>(destinosDistancias.keySet().toArray(new String[0]));
        comboDestinos.setBounds(desplazamientoDerecha + 100, 60, 250, 30);
        add(comboDestinos);

        JLabel lblFechaPartida = new JLabel("Fecha de Partida:");
        lblFechaPartida.setBounds(20, 100, 160, 30);
        lblFechaPartida.setFont(etiquetaFont);
        add(lblFechaPartida);

        txtFechaPartida = new JTextField();
        txtFechaPartida.setBounds(desplazamientoDerecha + 100, 100, 250, 30);
        add(txtFechaPartida);

        JLabel lblOmnibus = new JLabel("Ómnibus:");
        lblOmnibus.setBounds(20, 180, 120, 30);
        lblOmnibus.setFont(etiquetaFont);
        add(lblOmnibus);

        comboOmnibus = new JComboBox<>(t.getOmnibusesDisponibles().toArray(new Omnibus[0]));
        comboOmnibus.setBounds(desplazamientoDerecha + 100, 180, 250, 30);
        add(comboOmnibus);

        JLabel lblConductor = new JLabel("Conductor");
        lblConductor.setBounds(20, 220, 120, 30);
        lblConductor.setFont(etiquetaFont);
        add(lblConductor);

        comboConductor = new JComboBox<>();
        comboConductor.setBounds(desplazamientoDerecha + 100, 220, 250, 30);
        add(comboConductor);

        comboOmnibus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Omnibus seleccionado = (Omnibus) comboOmnibus.getSelectedItem();
                actualizarComboConductor(seleccionado);
            }
        });

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(150, 280, 140, 35);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 280, 140, 35);
        add(btnCancelar);

        setLocationRelativeTo(interfaz);

        btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmarViaje();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.decrementIdViaje();
				dispose();
			}
		});
    }

    public VentanaViaje(Interfaz interfaz, String id) {
        super(interfaz, "Editar Viaje", true);
        setT();
        setViaje(id);
        setSize(600, 380);
        setLayout(null);

        destinosDistancias = new HashMap<>(Terminal.getDestinosDistancias());

        Font etiquetaFont = new Font("Arial", Font.BOLD, 16);
        int desplazamientoDerecha = 140;

        lblId = new JLabel(String.valueOf(viaje.getId()));
        lblId.setBounds(desplazamientoDerecha + 100, 20, 250, 30);
        add(lblId);

        JLabel lblId2 = new JLabel("ID:");
        lblId2.setBounds(20, 20, 120, 30);
        lblId2.setFont(etiquetaFont);
        add(lblId2);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 60, 120, 30);
        lblDestino.setFont(etiquetaFont);
        add(lblDestino);

        comboDestinos = new JComboBox<>(destinosDistancias.keySet().toArray(new String[0]));
        comboDestinos.setSelectedItem(viaje.getDestino());
        comboDestinos.setBounds(desplazamientoDerecha + 100, 60, 250, 30);
        add(comboDestinos);

        JLabel lblFechaPartida = new JLabel("Fecha de Partida:");
        lblFechaPartida.setBounds(20, 100, 160, 30);
        lblFechaPartida.setFont(etiquetaFont);
        add(lblFechaPartida);

        txtFechaPartida = new JTextField(viaje.getFechaHoraPartida().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFechaPartida.setBounds(desplazamientoDerecha + 100, 100, 250, 30);
        add(txtFechaPartida);

        JLabel lblOmnibus = new JLabel("Ómnibus:");
        lblOmnibus.setBounds(20, 180, 120, 30);
        lblOmnibus.setFont(etiquetaFont);
        add(lblOmnibus);

        comboOmnibus = new JComboBox<>(t.getOmnibusesDisponibles().toArray(new Omnibus[0]));
        comboOmnibus.setSelectedItem(viaje.getOmnibus());
        comboOmnibus.setBounds(desplazamientoDerecha + 100, 180, 250, 30);
        add(comboOmnibus);

        JLabel lblConductor = new JLabel("Conductor");
        lblConductor.setBounds(20, 220, 120, 30);
        lblConductor.setFont(etiquetaFont);
        add(lblConductor);

        comboConductor = new JComboBox<>();
        comboConductor.setBounds(desplazamientoDerecha + 100, 220, 250, 30);
        add(comboConductor);

        comboOmnibus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Omnibus seleccionado = (Omnibus) comboOmnibus.getSelectedItem();
                actualizarComboConductor(seleccionado);
            }
        });

        btnConfirmar = new JButton("Editar");
        btnConfirmar.setBounds(150, 280, 140, 35);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 280, 140, 35);
        add(btnCancelar);

        setLocationRelativeTo(interfaz);

        actualizarComboConductor((Omnibus) comboOmnibus.getSelectedItem());

        btnConfirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editarViaje();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t.decrementIdViaje();
				dispose();
			}
		});
    }

    private void setViaje(String id) {
        viaje = t.getViaje(id);
    }

    private void setT() {
        this.t = Terminal.getTerminal();
    }

    private void confirmarViaje() {
        try {
            String destino = (String) comboDestinos.getSelectedItem();
            String fechaSalida = txtFechaPartida.getText();
            Omnibus omnibus = (Omnibus) comboOmnibus.getSelectedItem();
            Conductor conductor = (Conductor) comboConductor.getSelectedItem();

            if (destino == null || fechaSalida.isEmpty() || omnibus == null || conductor == null) {
                JOptionPane.showMessageDialog(this, "Faltan datos por completar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalTime horaSalida = omnibus.getHoraSalida(fechaSalida);
            if (horaSalida == null) {
                throw new IllegalArgumentException("El ómnibus seleccionado no está disponible en esa fecha");
            }

            viaje = new Viaje(String.valueOf(t.getIdViaje()), fechaSalida, horaSalida.format(DateTimeFormatter.ofPattern("HH:mm:ss")), destino, omnibus, conductor);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarViaje() {
        try {
            String destino = (String) comboDestinos.getSelectedItem();
            String fechaSalida = txtFechaPartida.getText();
            Omnibus omnibusSeleccionado = (Omnibus) comboOmnibus.getSelectedItem();
            Omnibus omnibusOriginal = viaje.getOmnibus();
            Conductor conductor = (Conductor) comboConductor.getSelectedItem();

            if (destino == null || fechaSalida.isEmpty() || omnibusSeleccionado == null || conductor == null) {
                JOptionPane.showMessageDialog(this, "Faltan datos por completar.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (omnibusOriginal != null && omnibusOriginal.equals(omnibusSeleccionado)) {
                omnibusOriginal.removeViaje(fechaSalida);
            }

            LocalTime horaSalida = omnibusSeleccionado.getHoraSalida(fechaSalida);
            if (horaSalida == null) {
                throw new IllegalArgumentException("El ómnibus seleccionado no está disponible en esa fecha");
            }

            t.getViajes().remove(viaje);
            viaje = new Viaje(String.valueOf(viaje.getId()), fechaSalida, horaSalida.format(DateTimeFormatter.ofPattern("HH:mm:ss")), destino, omnibusSeleccionado, conductor);
            t.addViaje(viaje);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void actualizarComboConductor(Omnibus omnibus) {
        comboConductor.removeAllItems();
        try {
            if (omnibus != null) {
                for (Conductor c : omnibus.getConductores()) {
                    comboConductor.addItem(c);
                }
            }
            if (viaje != null) {
                comboConductor.setSelectedItem(viaje.getConductor());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean confirmado() {
        return confirmado;
    }

    public Viaje getViaje() {
        return viaje;
    }
}
