package tf;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField nombreConductor;
	private JTextField idConductor;
	private JTextField experienciaConductor;
	private JTextField licenciaConductor;


	/**
	 * Create the frame.
	 */
	public Interfaz(final Terminal terminal) {
		final Terminal miTerminal = terminal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 11, 628, 432);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		tabbedPane.addTab("Conductor", null, panel, null);
		panel.setLayout(null);

		nombreConductor = new JTextField();
		nombreConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombreConductor.setBounds(10, 77, 185, 20);
		panel.add(nombreConductor);
		nombreConductor.setColumns(10);

		JLabel lblConducctor = new JLabel("Datos del conductor");
		lblConducctor.setBounds(10, 11, 194, 22);
		lblConducctor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblConducctor);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 44, 75, 22);
		panel.add(lblNombre);

		idConductor = new JTextField();
		idConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idConductor.setColumns(10);
		idConductor.setBounds(215, 77, 185, 20);
		panel.add(idConductor);

		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi.setBounds(215, 49, 75, 12);
		panel.add(lblCi);

		JLabel lblAnyosDeExperiencia = new JLabel("Anyos de experiencia:");
		lblAnyosDeExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyosDeExperiencia.setBounds(10, 108, 185, 22);
		panel.add(lblAnyosDeExperiencia);

		experienciaConductor = new JTextField();
		experienciaConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		experienciaConductor.setColumns(10);
		experienciaConductor.setBounds(10, 141, 185, 20);
		panel.add(experienciaConductor);

		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(10, 172, 93, 17);
		panel.add(lblCategoria);

		licenciaConductor = new JTextField();
		licenciaConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		licenciaConductor.setColumns(10);
		licenciaConductor.setBounds(215, 141, 185, 20);
		panel.add(licenciaConductor);

		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLicencia.setBounds(215, 113, 75, 12);
		panel.add(lblLicencia);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 200, 185, 36);
		panel.add(panel_1);
		panel_1.setLayout(null);

		final JRadioButton categoriaA = new JRadioButton("A");
		categoriaA.setFont(new Font("Tahoma", Font.PLAIN, 15));
		categoriaA.setBounds(6, 7, 40, 23);
		panel_1.add(categoriaA);

		final JRadioButton categoriaB = new JRadioButton("B");
		categoriaB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		categoriaB.setBounds(72, 7, 40, 23);
		panel_1.add(categoriaB);

		final JRadioButton categoriaC = new JRadioButton("C");
		categoriaC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		categoriaC.setBounds(139, 7, 40, 23);
		panel_1.add(categoriaC);

		JButton button = new JButton("Crear Conductor");

		button.setForeground(Color.BLACK);
		button.setBounds(215, 200, 185, 36);
		panel.add(button);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.BLACK);
		tabbedPane.addTab("Omnibus", null, panel_2, null);
		panel_2.setLayout(null);

		final JTextField matriculaOmni = new JTextField();
		matriculaOmni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matriculaOmni.setBounds(10, 77, 185, 20);
		panel_2.add(matriculaOmni);
		matriculaOmni.setColumns(10);

		JLabel lblDatosDelConductor = new JLabel("Datos del omnibus");
		lblDatosDelConductor.setBounds(10, 11, 194, 22);
		lblDatosDelConductor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(lblDatosDelConductor);

		JLabel lblNombre_1 = new JLabel("Matricula:");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre_1.setBounds(10, 44, 75, 22);
		panel_2.add(lblNombre_1);

		final JTextField asientosOmni = new JTextField();
		asientosOmni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		asientosOmni.setColumns(10);
		asientosOmni.setBounds(215, 77, 185, 20);
		panel_2.add(asientosOmni);

		JLabel lblCi_1 = new JLabel("Asientos:");
		lblCi_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_1.setBounds(215, 49, 75, 12);
		panel_2.add(lblCi_1);

		JLabel lblAnyosDeExperiencia_1 = new JLabel("Estado");
		lblAnyosDeExperiencia_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyosDeExperiencia_1.setBounds(10, 108, 185, 22);
		panel_2.add(lblAnyosDeExperiencia_1);

		JLabel lblCategoria_1 = new JLabel("Comodidades");
		lblCategoria_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria_1.setBounds(10, 172, 93, 17);
		panel_2.add(lblCategoria_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 200, 185, 84);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		final JRadioButton aire = new JRadioButton("aire");
		aire.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aire.setBounds(6, 7, 173, 23);
		panel_3.add(aire);

		final JRadioButton banyo = new JRadioButton("ba\u00F1o");
		banyo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		banyo.setBounds(6, 33, 173, 23);
		panel_3.add(banyo);

		final JRadioButton tv = new JRadioButton("TV");
		tv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tv.setBounds(6, 55, 173, 23);
		panel_3.add(tv);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(215, 122, 178, 162);
		panel_2.add(panel_4);
		panel_4.setLayout(null);

		final JComboBox<Conductor> cond1 = new JComboBox<Conductor>();
		cond1.setBounds(10, 30, 158, 20);
		cond1.addItem(null);
		panel_4.add(cond1);

		final JComboBox<Conductor> cond2 = new JComboBox<Conductor>();
		cond2.setBounds(10, 81, 158, 20);
		cond2.addItem(null);
		panel_4.add(cond2);

		final JComboBox<Conductor> cond3 = new JComboBox<Conductor>();
		cond3.setBounds(10, 131, 158, 20);
		cond3.addItem(null);
		panel_4.add(cond3);

		JButton button_1 = new JButton("Crear Ómnibus");
		
		button_1.setForeground(Color.BLACK);
		button_1.setBounds(120, 294, 185, 36);
		panel_2.add(button_1);

		JLabel lblConductor = new JLabel("Conductor1");
		lblConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConductor.setBounds(10, 11, 131, 12);
		panel_4.add(lblConductor);

		JLabel lblConductor_1 = new JLabel("Conductor2");
		lblConductor_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConductor_1.setBounds(10, 58, 131, 12);
		panel_4.add(lblConductor_1);

		JLabel lblConductor_2 = new JLabel("Conductor3");
		lblConductor_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConductor_2.setBounds(10, 112, 131, 12);
		panel_4.add(lblConductor_2);
		
		final JComboBox<String> estado = new JComboBox<String>();
		estado.addItem("En carretera");
		estado.addItem("Disponible");
		estado.addItem("En reparación");
		estado.setBounds(10, 141, 185, 20);
		panel_2.add(estado);

		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.BLACK);
		tabbedPane.addTab("Viajes", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblDatosDelViaje = new JLabel("Datos del viaje");
		lblDatosDelViaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDelViaje.setBounds(10, 11, 194, 22);
		panel_5.add(lblDatosDelViaje);

		JLabel lblOrigen = new JLabel("Origen:");
		lblOrigen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrigen.setBounds(10, 44, 75, 22);
		panel_5.add(lblOrigen);

		JTextField textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_8.setBounds(10, 77, 185, 20);
		panel_5.add(textField_8);
		textField_8.setColumns(10);

		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDestino.setBounds(215, 44, 75, 22);
		panel_5.add(lblDestino);

		JTextField textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_9.setColumns(10);
		textField_9.setBounds(215, 77, 185, 20);
		panel_5.add(textField_9);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFecha.setBounds(10, 108, 75, 22);
		panel_5.add(lblFecha);

		JTextField textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_10.setColumns(10);
		textField_10.setBounds(10, 141, 185, 20);
		panel_5.add(textField_10);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHora.setBounds(215, 108, 75, 22);
		panel_5.add(lblHora);

		JTextField textField_11 = new JTextField();
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_11.setColumns(10);
		textField_11.setBounds(215, 141, 185, 20);
		panel_5.add(textField_11);

		JLabel lblOmnibus = new JLabel("Omnibus:");
		lblOmnibus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOmnibus.setBounds(10, 172, 75, 22);
		panel_5.add(lblOmnibus);

		final JComboBox<Omnibus> comboBoxOmni = new JComboBox<Omnibus>();
		comboBoxOmni.setBounds(10, 205, 185, 20);
		panel_5.add(comboBoxOmni);

		JButton button_2 = new JButton("Crear Viaje");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.setForeground(Color.BLACK);
		button_2.setBounds(215, 197, 185, 36);
		panel_5.add(button_2);

		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.BLACK);
		tabbedPane.addTab("Reserva", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblDatosDeLa = new JLabel("Datos de la reserva");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDeLa.setBounds(10, 11, 194, 22);
		panel_6.add(lblDatosDeLa);

		JLabel lblNombre_2 = new JLabel("Nombre:");
		lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre_2.setBounds(10, 44, 75, 22);
		panel_6.add(lblNombre_2);

		JTextField textField_12 = new JTextField();
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_12.setBounds(10, 77, 185, 20);
		panel_6.add(textField_12);
		textField_12.setColumns(10);

		JLabel lblCi_2 = new JLabel("N\u00FAmero Reserva");
		lblCi_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_2.setBounds(215, 44, 185, 22);
		panel_6.add(lblCi_2);

		JTextField textField_13 = new JTextField();
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_13.setColumns(10);
		textField_13.setBounds(215, 77, 185, 20);
		panel_6.add(textField_13);

		JLabel lblViaje = new JLabel("Viaje:");
		lblViaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViaje.setBounds(10, 108, 75, 22);
		panel_6.add(lblViaje);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(10, 141, 185, 20);
		panel_6.add(comboBox_4);

		JLabel lblAsiento = new JLabel("Asiento:");
		lblAsiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsiento.setBounds(215, 108, 75, 22);
		panel_6.add(lblAsiento);

		JTextField textField_14 = new JTextField();
		textField_14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_14.setColumns(10);
		textField_14.setBounds(215, 141, 185, 20);
		panel_6.add(textField_14);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio.setBounds(10, 172, 75, 22);
		panel_6.add(lblPrecio);

		JTextField textField_15 = new JTextField();
		textField_15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_15.setColumns(10);
		textField_15.setBounds(10, 205, 185, 20);
		panel_6.add(textField_15);

		JButton button_3 = new JButton("Crear Reserva");
		button_3.setForeground(Color.BLACK);
		button_3.setBounds(10, 236, 185, 36);
		panel_6.add(button_3);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstado.setBounds(215, 172, 75, 22);
		panel_6.add(lblEstado);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(215, 200, 185, 72);
		panel_6.add(panel_7);
		panel_7.setLayout(null);

		JRadioButton rdbtnPendiente = new JRadioButton("En espera");
		rdbtnPendiente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPendiente.setBounds(6, 7, 110, 23);
		panel_7.add(rdbtnPendiente);

		JRadioButton rdbtnConfirmado = new JRadioButton("Confirmado");
		rdbtnConfirmado.setBounds(6, 42, 110, 23);
		panel_7.add(rdbtnConfirmado);
		rdbtnConfirmado.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panel_8 = new JPanel();
		panel_8.setForeground(Color.BLACK);
		tabbedPane.addTab("Pasajero", null, panel_8, null);
		panel_8.setLayout(null);

		JLabel lblDatosDelPasajero = new JLabel("Datos del pasajero");
		lblDatosDelPasajero.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDelPasajero.setBounds(10, 11, 194, 22);
		panel_8.add(lblDatosDelPasajero);

		JLabel lblNombre_3 = new JLabel("Nombre:");
		lblNombre_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre_3.setBounds(10, 44, 75, 22);
		panel_8.add(lblNombre_3);

		JTextField textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_16.setBounds(10, 77, 185, 20);
		panel_8.add(textField_16);
		textField_16.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(215, 44, 75, 22);
		panel_8.add(lblId);

		JTextField textField_17 = new JTextField();
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_17.setColumns(10);
		textField_17.setBounds(215, 77, 185, 20);
		panel_8.add(textField_17);

		JButton button_4 = new JButton("Crear Pasajero");
		button_4.setForeground(Color.BLACK);
		button_4.setBounds(215, 124, 185, 36);
		panel_8.add(button_4);

		JPanel panelTerminal = new JPanel();
		panelTerminal.setForeground(Color.BLACK);
		tabbedPane.addTab("Terminal", null, panelTerminal, null);
		panelTerminal.setLayout(null);

		JLabel lblReportesTerminal = new JLabel("Reportes de Terminal");
		lblReportesTerminal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblReportesTerminal.setBounds(10, 11, 250, 22);
		panelTerminal.add(lblReportesTerminal);

		JButton btnSalariosConductores = new JButton("Ver Salarios Conductores");
		btnSalariosConductores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSalariosConductores.setBounds(10, 50, 250, 30);
		panelTerminal.add(btnSalariosConductores);

		JButton btnConductorMes = new JButton("Conductor del Mes");
		btnConductorMes.setBounds(10, 90, 250, 30);
		panelTerminal.add(btnConductorMes);

		JButton btnRecaudacionMensual = new JButton("Recaudaci�n �ltimo Mes");
		btnRecaudacionMensual.setBounds(10, 130, 250, 30);
		panelTerminal.add(btnRecaudacionMensual);

		JButton btnListarOmnibus = new JButton("Listado de �mnibus");
		btnListarOmnibus.setBounds(10, 170, 250, 30);
		panelTerminal.add(btnListarOmnibus);

		JButton btnListarConductores = new JButton("Listado de Conductores");
		btnListarConductores.setBounds(10, 210, 250, 30);
		panelTerminal.add(btnListarConductores);

		JTextPane textAreaReportes = new JTextPane();
		textAreaReportes.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaReportes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textAreaReportes.setBounds(280, 50, 335, 190);
		panelTerminal.add(textAreaReportes);
		
		JButton button_5 = new JButton("Importar Conductores");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Conductor> conductores = terminal.getConductores();
				for(Conductor conductor : conductores){
					cond1.addItem(conductor);
					cond2.addItem(conductor);
					cond3.addItem(conductor);
				}
			}
		});
		button_5.setBounds(10, 251, 250, 30);
		panelTerminal.add(button_5);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreText = nombreConductor.getText();
				String idText = idConductor.getText();
				String experienciaText = experienciaConductor.getText();
				String licenciaText = licenciaConductor.getText();

				Conductor conductor;
				try{
					if (categoriaA.isSelected()) {
						conductor = new ConductorA(nombreText, idText, experienciaText, licenciaText);
					} else if (categoriaB.isSelected()) {
						conductor = new ConductorB(nombreText, idText, experienciaText, licenciaText);
					} else if (categoriaC.isSelected()) {
						conductor = new ConductorC(nombreText, idText, experienciaText, licenciaText);
					} else {
						throw new IllegalArgumentException("Debe seleccionar una categoría válida (A, B o C)");
					}

					terminal.addConductor(conductor);

					JOptionPane.showMessageDialog(null, "Conductor registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE); 
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    ArrayList<String> comodidades = new ArrayList<String>();

			    if (aire.isSelected()) {
			        comodidades.add("Aire acondicionado");
			    }
			    if (banyo.isSelected()) {
			        comodidades.add("Baño");
			    }
			    if (tv.isSelected()) {
			        comodidades.add("TV");
			    }

			    if (comodidades.isEmpty()) {
			       throw new IllegalArgumentException("Se debe seleccionar al menos una comodidad");
			    }

			    try {
			        Omnibus omnibus = new Omnibus(matriculaOmni.getText(), asientosOmni.getText(), (String) estado.getSelectedItem(), comodidades);

			        Conductor conductor1 = (Conductor)cond1.getSelectedItem();
			        Conductor conductor2 = (Conductor)cond2.getSelectedItem();
			        Conductor conductor3 = (Conductor)cond3.getSelectedItem();
			        
			        if (conductor1 == null && conductor2 == null && conductor3 == null) {
			            throw new IllegalArgumentException("Debe seleccionar al menos un conductor");
			        }

			        omnibus.addConductor(conductor1);
			        omnibus.addConductor(conductor2);
			        omnibus.addConductor(conductor3);
			        
			        terminal.addOmnibus(omnibus);
			        comboBoxOmni.addItem(omnibus);
			        JOptionPane.showMessageDialog(null, "Ómnibus registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

			    } catch (IllegalArgumentException e) {
			        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}

		});
	}
}