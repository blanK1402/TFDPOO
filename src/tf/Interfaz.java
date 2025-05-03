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
	private JTextField textField;


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
		nombreConductor.setBounds(164, 45, 136, 20);
		panel.add(nombreConductor);
		nombreConductor.setColumns(10);

		JLabel lblConducctor = new JLabel("Crear conductor");
		lblConducctor.setBounds(71, 12, 146, 22);
		lblConducctor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblConducctor);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 44, 75, 22);
		panel.add(lblNombre);

		idConductor = new JTextField();
		idConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idConductor.setColumns(10);
		idConductor.setBounds(164, 77, 136, 20);
		panel.add(idConductor);

		JLabel lblCi = new JLabel("CI:");
		lblCi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi.setBounds(10, 81, 75, 12);
		panel.add(lblCi);

		JLabel lblAnyosDeExperiencia = new JLabel("Anyos de experiencia:");
		lblAnyosDeExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyosDeExperiencia.setBounds(10, 108, 185, 22);
		panel.add(lblAnyosDeExperiencia);

		experienciaConductor = new JTextField();
		experienciaConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		experienciaConductor.setColumns(10);
		experienciaConductor.setBounds(164, 108, 136, 20);
		panel.add(experienciaConductor);

		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria.setBounds(10, 172, 93, 17);
		panel.add(lblCategoria);

		licenciaConductor = new JTextField();
		licenciaConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		licenciaConductor.setColumns(10);
		licenciaConductor.setBounds(164, 141, 136, 20);
		panel.add(licenciaConductor);

		JLabel lblLicencia = new JLabel("Licencia");
		lblLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLicencia.setBounds(10, 145, 75, 12);
		panel.add(lblLicencia);

		JButton btnCrear_1 = new JButton("Crear");

		btnCrear_1.setForeground(Color.BLACK);
		btnCrear_1.setBounds(10, 200, 290, 36);
		panel.add(btnCrear_1);

		final JComboBox<String> categoria = new JComboBox<String>();
		categoria.addItem("A");
		categoria.addItem("B");
		categoria.addItem("C");
		categoria.setBounds(164, 172, 136, 20);
		panel.add(categoria);

		JLabel lblEliminarConductor = new JLabel("Eliminar conductor");
		lblEliminarConductor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEliminarConductor.setBounds(386, 12, 171, 22);
		panel.add(lblEliminarConductor);

		JLabel label = new JLabel("CI:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(386, 50, 39, 12);
		panel.add(label);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(417, 45, 136, 20);
		panel.add(textField);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setBounds(386, 71, 171, 36);
		panel.add(btnEliminar);

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.BLACK);
		tabbedPane.addTab("Omnibus", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblDatosDelConductor = new JLabel("Crear omnibus");
		lblDatosDelConductor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDelConductor.setBounds(10, 11, 194, 22);
		panel_2.add(lblDatosDelConductor);

		JLabel lblNombre_1 = new JLabel("Matricula:");
		lblNombre_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre_1.setBounds(10, 44, 75, 22);
		panel_2.add(lblNombre_1);

		final JTextField matriculaOmni = new JTextField();
		matriculaOmni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		matriculaOmni.setBounds(95, 44, 185, 20);
		panel_2.add(matriculaOmni);

		JLabel lblCi_1 = new JLabel("Asientos:");
		lblCi_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_1.setBounds(10, 81, 75, 12);
		panel_2.add(lblCi_1);

		final JTextField asientosOmni = new JTextField();
		asientosOmni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		asientosOmni.setBounds(95, 77, 185, 20);
		panel_2.add(asientosOmni);

		JLabel lblAnyosDeExperiencia_1 = new JLabel("Estado");
		lblAnyosDeExperiencia_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyosDeExperiencia_1.setBounds(10, 107, 75, 22);
		panel_2.add(lblAnyosDeExperiencia_1);

		final JComboBox<String> estado = new JComboBox<String>();
		estado.addItem("En carretera");
		estado.addItem("Disponible");
		estado.addItem("En reparación");
		estado.setBounds(95, 110, 185, 20);
		panel_2.add(estado);

		JLabel lblCategoria_1 = new JLabel("Comodidades");
		lblCategoria_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria_1.setBounds(306, 47, 93, 17);
		panel_2.add(lblCategoria_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(306, 81, 93, 84);
		panel_2.add(panel_3);
		panel_3.setLayout(null);

		final JRadioButton aire = new JRadioButton("aire");
		aire.setFont(new Font("Tahoma", Font.PLAIN, 15));
		aire.setBounds(6, 7, 81, 23);
		panel_3.add(aire);

		final JRadioButton banyo = new JRadioButton("baño");
		banyo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		banyo.setBounds(6, 33, 81, 23);
		panel_3.add(banyo);

		final JRadioButton tv = new JRadioButton("TV");
		tv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tv.setBounds(6, 55, 81, 23);
		panel_3.add(tv);

		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.BLACK);
		btnCrear.setBounds(306, 176, 93, 42);
		panel_2.add(btnCrear);

		final JComboBox<Conductor> cond1 = new JComboBox<Conductor>();
		cond1.addItem(null);
		cond1.setBounds(95, 141, 185, 20);
		panel_2.add(cond1);

		JLabel lblConductor = new JLabel("Conductor1");
		lblConductor.setBounds(10, 143, 75, 12);
		panel_2.add(lblConductor);
		lblConductor.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblConductor_1 = new JLabel("Conductor2");
		lblConductor_1.setBounds(10, 172, 75, 12);
		panel_2.add(lblConductor_1);
		lblConductor_1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JComboBox<Conductor> cond2 = new JComboBox<Conductor>();
		cond2.setBounds(95, 170, 185, 20);
		cond2.addItem(null);
		panel_2.add(cond2);

		JLabel lblConductor_2 = new JLabel("Conductor3");
		lblConductor_2.setBounds(10, 200, 75, 12);
		panel_2.add(lblConductor_2);
		lblConductor_2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JComboBox<Conductor> cond3 = new JComboBox<Conductor>();
		cond3.setBounds(95, 198, 185, 20);
		cond3.addItem(null);
		panel_2.add(cond3);

		JLabel lblEliminarOmnibus = new JLabel("Eliminar omnibus");
		lblEliminarOmnibus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEliminarOmnibus.setBounds(10, 232, 145, 22);
		panel_2.add(lblEliminarOmnibus);

		JLabel label_1 = new JLabel("Matricula:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(10, 265, 75, 22);
		panel_2.add(label_1);

		JComboBox<Conductor> comboBox_2 = new JComboBox<Conductor>();
		comboBox_2.setBounds(95, 268, 185, 20);
		panel_2.add(comboBox_2);

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setForeground(Color.BLACK);
		btnEliminar_1.setBounds(10, 297, 270, 33);
		panel_2.add(btnEliminar_1);

		


		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.BLACK);
		tabbedPane.addTab("Viajes", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblDatosDelViaje = new JLabel("Datos del viaje");
		lblDatosDelViaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDelViaje.setBounds(10, 11, 194, 22);
		panel_5.add(lblDatosDelViaje);

		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDestino.setBounds(10, 50, 80, 20);
		panel_5.add(lblDestino);

		JTextField txtDestino = new JTextField();
		txtDestino.setBounds(255, 52, 150, 20);
		panel_5.add(txtDestino);

		JLabel lblFechaSalida = new JLabel("Fecha y hora de salida:");
		lblFechaSalida.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaSalida.setBounds(10, 80, 183, 20);
		panel_5.add(lblFechaSalida);

		JTextField txtFechaSalida = new JTextField();
		txtFechaSalida.setBounds(255, 82, 150, 20);
		panel_5.add(txtFechaSalida);

		JLabel lblFechaLlegada = new JLabel("Fecha y hora estimada de llegada:");
		lblFechaLlegada.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaLlegada.setBounds(10, 110, 234, 20);
		panel_5.add(lblFechaLlegada);

		JTextField txtFechaLlegada = new JTextField();
		txtFechaLlegada.setBounds(255, 112, 150, 20);
		panel_5.add(txtFechaLlegada);

		JLabel lblOmnibus = new JLabel("Ómnibus:");
		lblOmnibus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOmnibus.setBounds(10, 140, 80, 20);
		panel_5.add(lblOmnibus);

		final JComboBox<Omnibus> comBoxOmni = new JComboBox<Omnibus>();
		comBoxOmni.setBounds(255, 140, 150, 20);
		panel_5.add(comBoxOmni);

		JButton btnCrearViaje = new JButton("Crear Viaje");
		btnCrearViaje.setBounds(10, 200, 395, 30);
		panel_5.add(btnCrearViaje);

		JComboBox<Omnibus> comboBox = new JComboBox<Omnibus>();
		comboBox.setBounds(255, 169, 150, 20);
		panel_5.add(comboBox);

		JLabel lblConductor_3 = new JLabel("Conductor");
		lblConductor_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConductor_3.setBounds(10, 169, 80, 20);
		panel_5.add(lblConductor_3);

		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.BLACK);
		tabbedPane.addTab("Reserva", null, panel_6, null);
		panel_6.setLayout(null);

		JLabel lblDatosDeLa = new JLabel("Crear reserva");
		lblDatosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDeLa.setBounds(10, 11, 194, 22);
		panel_6.add(lblDatosDeLa);

		JLabel lblNombre_2 = new JLabel("Pasajero");
		lblNombre_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre_2.setBounds(10, 44, 75, 22);
		panel_6.add(lblNombre_2);

		JLabel lblCi_2 = new JLabel("Número Reserva:");
		lblCi_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_2.setBounds(10, 76, 185, 22);
		panel_6.add(lblCi_2);

		JTextField textField_13 = new JTextField();
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_13.setColumns(10);
		textField_13.setBounds(138, 77, 145, 20);
		panel_6.add(textField_13);

		JLabel lblViaje = new JLabel("Viaje:");
		lblViaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViaje.setBounds(10, 108, 75, 22);
		panel_6.add(lblViaje);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(138, 109, 145, 20);
		panel_6.add(comboBox_4);

		JLabel lblAsiento = new JLabel("Asiento:");
		lblAsiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsiento.setBounds(10, 138, 75, 22);
		panel_6.add(lblAsiento);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio.setBounds(10, 172, 75, 22);
		panel_6.add(lblPrecio);

		JButton btnCrear_2 = new JButton("Crear");
		btnCrear_2.setForeground(Color.BLACK);
		btnCrear_2.setBounds(10, 205, 273, 36);
		panel_6.add(btnCrear_2);

		JLabel lblNewLabel = new JLabel("0.00");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(138, 178, 145, 14);
		panel_6.add(lblNewLabel);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(138, 141, 145, 20);
		panel_6.add(comboBox_3);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(138, 47, 145, 20);
		panel_6.add(comboBox_5);

		JLabel lblCancelarReserva = new JLabel("Cancelar reserva");
		lblCancelarReserva.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCancelarReserva.setBounds(387, 11, 194, 22);
		panel_6.add(lblCancelarReserva);

		JLabel lblNmeroReserva = new JLabel("Número Reserva:");
		lblNmeroReserva.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNmeroReserva.setBounds(344, 44, 118, 22);
		panel_6.add(lblNmeroReserva);

		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(468, 47, 145, 20);
		panel_6.add(comboBox_6);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBounds(340, 110, 273, 36);
		panel_6.add(btnCancelar);

		JLabel lblReenvolso = new JLabel("Reenvolso:");
		lblReenvolso.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblReenvolso.setBounds(344, 76, 75, 22);
		panel_6.add(lblReenvolso);

		JLabel label_2 = new JLabel("0.00");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(468, 82, 145, 14);
		panel_6.add(label_2);

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
		lblNombre_3.setBounds(10, 44, 60, 22);
		panel_8.add(lblNombre_3);

		JTextField textField_16 = new JTextField();
		textField_16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_16.setBounds(75, 45, 185, 20);
		panel_8.add(textField_16);
		textField_16.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(10, 77, 60, 22);
		panel_8.add(lblId);

		JTextField textField_17 = new JTextField();
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_17.setColumns(10);
		textField_17.setBounds(75, 76, 185, 20);
		panel_8.add(textField_17);

		JButton btnCrear_3 = new JButton("Crear");
		btnCrear_3.setForeground(Color.BLACK);
		btnCrear_3.setBounds(270, 44, 60, 52);
		panel_8.add(btnCrear_3);

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

		JButton btnRecaudacionMensual = new JButton("Recaudación Último Mes");
		btnRecaudacionMensual.setBounds(10, 130, 250, 30);
		panelTerminal.add(btnRecaudacionMensual);

		JButton btnListarOmnibus = new JButton("Listado de Ómnibus");
		btnListarOmnibus.setBounds(10, 170, 250, 30);
		panelTerminal.add(btnListarOmnibus);

		JButton btnListarConductores = new JButton("Listado de Conductores");
		btnListarConductores.setBounds(10, 210, 250, 30);
		panelTerminal.add(btnListarConductores);

		JTextPane textAreaReportes = new JTextPane();
		textAreaReportes.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaReportes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textAreaReportes.setBounds(280, 50, 335, 231);
		panelTerminal.add(textAreaReportes);

		JButton btnImportarDatos = new JButton("Importar Datos");
		btnImportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Conductor> conductores = terminal.getConductores();
				ArrayList<Omnibus> omnibuses = terminal.getOmnibuses();
				for(Conductor conductor : conductores){
					cond1.addItem(conductor);
					cond2.addItem(conductor);
					cond3.addItem(conductor);
				}
				for(Omnibus o : omnibuses){
					System.out.println("asd");
					comBoxOmni.addItem(o);
				}
			}
		});
		btnImportarDatos.setBounds(10, 251, 250, 30);
		panelTerminal.add(btnImportarDatos);

		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> comodidades = new ArrayList<String>();

				if (!aire.isSelected() && !banyo.isSelected() && !tv.isSelected()) {
				    JOptionPane.showMessageDialog(null, "Se debe seleccionar al menos una comodidad", "Error", JOptionPane.ERROR_MESSAGE);
				    throw new IllegalArgumentException("Se debe seleccionar al menos una comodidad");
				}

				if (aire.isSelected()) {
				    comodidades.add("Aire acondicionado");
				}
				if (banyo.isSelected()) {
				    comodidades.add("Baño");
				}
				if (tv.isSelected()) {
				    comodidades.add("TV");
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
					comBoxOmni.addItem(omnibus);
					JOptionPane.showMessageDialog(null, "Ómnibus registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		
		btnCrear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreText = nombreConductor.getText();
				String idText = idConductor.getText();
				String experienciaText = experienciaConductor.getText();
				String licenciaText = licenciaConductor.getText();
				String categoriaText = (String)categoria.getSelectedItem();

				try{
					Conductor conductor = null;
					if(categoria.equals("A")){
						conductor = new ConductorA(nombreText, idText, experienciaText, licenciaText);
					}else if(categoria.equals("B")){
						conductor = new ConductorB(nombreText, idText, experienciaText, licenciaText);
					}else{
						conductor = new ConductorC(nombreText, idText, experienciaText, licenciaText);
					}
					
					cond1.addItem(conductor);
					cond2.addItem(conductor);
					cond3.addItem(conductor);
					terminal.addConductor(conductor);

					JOptionPane.showMessageDialog(null, "Conductor registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE); 
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}
}