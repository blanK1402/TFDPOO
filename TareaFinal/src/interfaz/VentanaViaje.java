package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import clases.Conductor;
import clases.Omnibus;
import clases.Viaje;

public class VentanaViaje extends JDialog {

    private LocalDateTime fecha;
    private HashMap<String, Integer> destinosDistancias;
    private JTextField txtId, txtFechaPartida, txtHoraPartida;
    private JLabel lblPrecio, lblValorPrecio;
    private JComboBox<Omnibus> comboOmnibus;
    private JComboBox<Conductor> comboConductor;
    private JComboBox<String> comboDestinos;
    private JButton btnConfirmar, btnCancelar;
    private boolean confirmado = false;
    private Viaje viaje;

    public VentanaViaje(JFrame parent, ArrayList<Omnibus> listaOmnibus, ArrayList<Conductor> listaConductores, LocalDateTime fechaActual) {
        super(parent, "Crear Nuevo Viaje", true);
        setSize(600, 420);
        setLayout(null);

        fecha = fechaActual;
        destinosDistancias = new HashMap<>();
        destinosDistancias.put("Pinar del Río", 163);
        destinosDistancias.put("Artemisa", 67);
        destinosDistancias.put("Mayabeque", 56);
        destinosDistancias.put("Matanzas", 105);
        destinosDistancias.put("Villa Clara", 277);
        destinosDistancias.put("Cienfuegos", 254);
        destinosDistancias.put("Sancti Spíritus", 357);
        destinosDistancias.put("Ciego de Ávila", 421);
        destinosDistancias.put("Camagüey", 533);
        destinosDistancias.put("Las Tunas", 657);
        destinosDistancias.put("Holguín", 689);
        destinosDistancias.put("Granma", 713);
        destinosDistancias.put("Santiago de Cuba", 847);
        destinosDistancias.put("Guantánamo", 911);

        Font etiquetaFont = new Font("Arial", Font.BOLD, 16);
        int desplazamientoDerecha = 140;

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 160, 30);
        lblId.setFont(etiquetaFont);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(desplazamientoDerecha + 130, 20, 250, 30);
        add(txtId);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(20, 60, 160, 30);
        lblDestino.setFont(etiquetaFont);
        add(lblDestino);

        comboDestinos = new JComboBox<>(destinosDistancias.keySet().toArray(new String[0]));
        comboDestinos.setBounds(desplazamientoDerecha + 130, 60, 250, 30);
        add(comboDestinos);

        JLabel lblFechaPartida = new JLabel("Fecha de Partida:");
        lblFechaPartida.setBounds(20, 100, 160, 30);
        lblFechaPartida.setFont(etiquetaFont);
        add(lblFechaPartida);

        txtFechaPartida = new JTextField();
        txtFechaPartida.setBounds(desplazamientoDerecha + 130, 100, 250, 30);
        add(txtFechaPartida);

        JLabel lblHoraPartida = new JLabel("Hora de Partida:");
        lblHoraPartida.setBounds(20, 140, 160, 30);
        lblHoraPartida.setFont(etiquetaFont);
        add(lblHoraPartida);

        txtHoraPartida = new JTextField();
        txtHoraPartida.setBounds(desplazamientoDerecha + 130, 140, 250, 30);
        add(txtHoraPartida);

        JLabel lblOmnibus = new JLabel("Ómnibus:");
        lblOmnibus.setBounds(20, 180, 160, 30);
        lblOmnibus.setFont(etiquetaFont);
        add(lblOmnibus);

        comboOmnibus = new JComboBox<>(listaOmnibus.toArray(new Omnibus[0]));
        comboOmnibus.setBounds(desplazamientoDerecha + 130, 180, 250, 30);
        add(comboOmnibus);

        JLabel lblConductor = new JLabel("Conductor:");
        lblConductor.setBounds(20, 220, 160, 30);
        lblConductor.setFont(etiquetaFont);
        add(lblConductor);

        comboConductor = new JComboBox<>(listaConductores.toArray(new Conductor[0]));
        comboConductor.setBounds(desplazamientoDerecha + 130, 220, 250, 30);
        add(comboConductor);

        lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 260, 160, 35);
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblPrecio);

        lblValorPrecio = new JLabel("0.00");
        lblValorPrecio.setBounds(desplazamientoDerecha + 130, 260, 250, 35);
        lblValorPrecio.setForeground(new Color(0, 128, 0));
        lblValorPrecio.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblValorPrecio);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(150, 320, 140, 35);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 320, 140, 35);
        add(btnCancelar);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarViaje();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setLocationRelativeTo(parent);
    }



    private LocalDateTime calcularLlegada(int distancia, LocalDateTime fecha) {
		return fecha.plusHours(distancia/55);
	}
    
    private void confirmarViaje() throws IllegalArgumentException {
        try {
            String id = txtId.getText().trim();
            String destino = (String) comboDestinos.getSelectedItem();
            Omnibus omnibus = (Omnibus) comboOmnibus.getSelectedItem();
            Conductor conductor = (Conductor) comboConductor.getSelectedItem();
            int distancia = destinosDistancias.get(destino);

            String fechaPartidaStr = txtFechaPartida.getText().trim();
            String horaPartidaStr = txtHoraPartida.getText().trim();
            LocalDate fechaPartida = LocalDate.parse(fechaPartidaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalTime horaPartida = LocalTime.parse(horaPartidaStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalDateTime fechaHoraPartida = LocalDateTime.of(fechaPartida, horaPartida);
            LocalDateTime fechaHoraLlegada = calcularLlegada(distancia, fechaHoraPartida);
            String fechaLlegadaStr = fechaHoraLlegada.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String horaLlegadaStr = fechaHoraLlegada.toLocalDate().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            viaje = new Viaje(id, distancia, fechaPartidaStr, horaPartidaStr, fechaLlegadaStr, horaLlegadaStr, destino, omnibus, conductor);
            confirmado = true;
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


	public boolean isConfirmado() {
        return confirmado;
    }

    public Viaje getViaje() {
        return viaje;
    }
}
