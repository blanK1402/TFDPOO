package interfaz;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import clases.Conductor;
import clases.Omnibus;
import clases.Terminal;
import clases.Viaje;

public class VentanaViaje extends JDialog {

	private LocalDateTime fecha;
	private JLabel lblId;
	private HashMap<String, Integer> destinosDistancias;
	private JTextField txtFechaPartida, txtHoraPartida;
	private JComboBox<Omnibus> comboOmnibus;
	private JComboBox<Conductor> comboConductor;
	private JComboBox<String> comboDestinos;
	private JButton btnConfirmar, btnCancelar;
	private boolean confirmado = false;
	private Viaje viaje;
	Terminal t;

	public VentanaViaje(JFrame parent, final Terminal t) {
		super(parent, "Crear Nuevo Viaje", true);
		setT(t);
		setSize(600, 380);
		setLayout(null);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		fecha = t.getFecha();
		destinosDistancias = new HashMap<>(Terminal.getDestinosDistancias());

		Font etiquetaFont = new Font("Arial", Font.BOLD, 16);
		int desplazamientoDerecha = 140;
		
		lblId = new JLabel("");
		
		lblId.setText(String.valueOf(t.getIdViaje()));
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

		JLabel lblHoraPartida = new JLabel("Hora de Partida:");
		lblHoraPartida.setBounds(20, 140, 160, 30);
		lblHoraPartida.setFont(etiquetaFont);
		add(lblHoraPartida);

		txtHoraPartida = new JTextField();
		txtHoraPartida.setBounds(desplazamientoDerecha + 100, 140, 250, 30);
		add(txtHoraPartida);

		JLabel lblOmnibus = new JLabel("Ómnibus:");
		lblOmnibus.setBounds(20, 180, 120, 30);
		lblOmnibus.setFont(etiquetaFont);
		add(lblOmnibus);

		comboOmnibus = new JComboBox<>(t.getOmnibuses().toArray(new Omnibus[0]));
		comboOmnibus.setBounds(desplazamientoDerecha + 100, 180, 250, 30);
		add(comboOmnibus);

		JLabel lblConductor = new JLabel("Conductor");
		lblConductor.setBounds(20, 220, 120, 30);
		lblConductor.setFont(etiquetaFont);
		add(lblConductor);

		comboConductor = new JComboBox<>(t.getConductores().toArray(new Conductor[0]));
		comboConductor.setBounds(desplazamientoDerecha + 100, 220, 250, 30);
		add(comboConductor);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(150, 280, 140, 35);
		add(btnConfirmar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(300, 280, 140, 35);
		add(btnCancelar);

		setLocationRelativeTo(parent);
		
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

	private void setT(Terminal t) {
		this.t = t;
	}
	
	private void confirmarViaje() {
		try{
			String id = lblId.getText();
		    String destino = (String) comboDestinos.getSelectedItem();
		    String fechaSalida = txtFechaPartida.getText();
		    String horaSalida = txtHoraPartida.getText();
		    Omnibus omnibus = (Omnibus) comboOmnibus.getSelectedItem();
		    Conductor conductor = (Conductor) comboConductor.getSelectedItem();
		    viaje = new Viaje(id, fechaSalida, horaSalida, destino, omnibus, conductor);
		    confirmado = true;
		    dispose();   
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}


	public boolean isConfirmado() {
		return confirmado;
	}

	public Viaje getViaje() {
		return viaje;
	}
}
