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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField nombreConductor;
	private JTextField idConductor;
	private JTextField experienciaConductor;
	private JTextField licenciaConductor;
	private JTextField textField;
	private JTextField idViaje;
	private JTextField txtHoraSalida;
	private JTextField txtDdmmaaaa;
	private JTextField txtHoraminsegundo;
	private JTextPane txtReportes;


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

		final JTextField nombrePasajero = new JTextField();
		nombrePasajero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nombrePasajero.setBounds(75, 45, 185, 20);
		panel_8.add(nombrePasajero);
		nombrePasajero.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId.setBounds(10, 77, 60, 22);
		panel_8.add(lblId);

		final JTextField idPasajero = new JTextField();
		idPasajero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idPasajero.setColumns(10);
		idPasajero.setBounds(75, 76, 185, 20);
		panel_8.add(idPasajero);
		
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
		matriculaOmni.setBounds(105, 44, 153, 20);
		panel_2.add(matriculaOmni);

		JLabel lblCi_1 = new JLabel("Asientos:");
		lblCi_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_1.setBounds(10, 81, 75, 12);
		panel_2.add(lblCi_1);

		final JTextField asientosOmni = new JTextField();
		asientosOmni.setFont(new Font("Tahoma", Font.PLAIN, 15));
		asientosOmni.setBounds(105, 77, 153, 20);
		panel_2.add(asientosOmni);

		JLabel lblAnyosDeExperiencia_1 = new JLabel("Estado");
		lblAnyosDeExperiencia_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAnyosDeExperiencia_1.setBounds(10, 107, 75, 22);
		panel_2.add(lblAnyosDeExperiencia_1);

		final JComboBox<String> estado = new JComboBox<String>();
		estado.addItem("En carretera");
		estado.addItem("Disponible");
		estado.addItem("En reparación");
		estado.setBounds(105, 110, 153, 20);
		panel_2.add(estado);

		JLabel lblCategoria_1 = new JLabel("Comodidades");
		lblCategoria_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCategoria_1.setBounds(10, 230, 93, 17);
		panel_2.add(lblCategoria_1);

		JButton btnCrear = new JButton("Crear");
		btnCrear.setForeground(Color.BLACK);
		btnCrear.setBounds(10, 258, 248, 42);
		panel_2.add(btnCrear);

		final JComboBox<Conductor> cond1 = new JComboBox<Conductor>();
		cond1.addItem(null);
		cond1.setBounds(105, 141, 153, 20);
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
		cond2.setBounds(105, 170, 153, 20);
		cond2.addItem(null);
		panel_2.add(cond2);

		JLabel lblConductor_2 = new JLabel("Conductor3");
		lblConductor_2.setBounds(10, 200, 75, 12);
		panel_2.add(lblConductor_2);
		lblConductor_2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JComboBox<Conductor> cond3 = new JComboBox<Conductor>();
		cond3.setBounds(105, 201, 153, 20);
		cond3.addItem(null);
		panel_2.add(cond3);

		JLabel lblEliminarOmnibus = new JLabel("Eliminar omnibus");
		lblEliminarOmnibus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEliminarOmnibus.setBounds(422, 11, 145, 22);
		panel_2.add(lblEliminarOmnibus);

		JLabel label_1 = new JLabel("Matricula:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_1.setBounds(361, 44, 75, 22);
		panel_2.add(label_1);

		JComboBox<Conductor> comboBox_2 = new JComboBox<Conductor>();
		comboBox_2.setBounds(432, 47, 153, 20);
		panel_2.add(comboBox_2);

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.setForeground(Color.BLACK);
		btnEliminar_1.setBounds(361, 73, 225, 33);
		panel_2.add(btnEliminar_1);

		final JRadioButton aire = new JRadioButton("aire");
		aire.setBounds(102, 228, 53, 23);
		panel_2.add(aire);
		aire.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JRadioButton banyo = new JRadioButton("baño");
		banyo.setBounds(154, 228, 63, 23);
		panel_2.add(banyo);
		banyo.setFont(new Font("Tahoma", Font.PLAIN, 15));

		final JRadioButton tv = new JRadioButton("TV");
		tv.setBounds(216, 228, 45, 23);
		panel_2.add(tv);
		tv.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JPanel panel_5 = new JPanel();
		panel_5.setForeground(Color.BLACK);
		tabbedPane.addTab("Viajes", null, panel_5, null);
		panel_5.setLayout(null);

		JLabel lblDatosDelViaje = new JLabel("Crear viaje");
		lblDatosDelViaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDatosDelViaje.setBounds(10, 11, 194, 22);
		panel_5.add(lblDatosDelViaje);

		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDestino.setBounds(10, 80, 80, 20);
		panel_5.add(lblDestino);

		JLabel lblFechaSalida = new JLabel("Fecha de salida:");
		lblFechaSalida.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaSalida.setBounds(10, 110, 173, 20);
		panel_5.add(lblFechaSalida);

		final JTextField txtFechaSalida = new JTextField();
		txtFechaSalida.setBounds(192, 112, 150, 20);
		panel_5.add(txtFechaSalida);

		JLabel lblOmnibus = new JLabel("Ómnibus:");
		lblOmnibus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOmnibus.setBounds(10, 172, 80, 20);
		panel_5.add(lblOmnibus);

		final JComboBox<Omnibus> comBoxOmni = new JComboBox<Omnibus>();
		comBoxOmni.setBounds(192, 174, 150, 20);
		panel_5.add(comBoxOmni);

		final JComboBox<Conductor> comboBoxCond = new JComboBox<Conductor>();
		comboBoxCond.setBounds(192, 205, 150, 20);
		panel_5.add(comboBoxCond);

		JLabel lblConductor_3 = new JLabel("Conductor");
		lblConductor_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConductor_3.setBounds(10, 203, 80, 20);
		panel_5.add(lblConductor_3);

		JLabel lblId_1 = new JLabel("ID:");
		lblId_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblId_1.setBounds(10, 49, 80, 20);
		panel_5.add(lblId_1);

		idViaje = new JTextField();
		idViaje.setBounds(192, 51, 150, 20);
		panel_5.add(idViaje);

		JLabel lblHoraDeSalida = new JLabel("Hora de salida:");
		lblHoraDeSalida.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHoraDeSalida.setBounds(10, 141, 173, 20);
		panel_5.add(lblHoraDeSalida);

		txtHoraSalida = new JTextField();
		txtHoraSalida.setBounds(192, 141, 150, 20);
		panel_5.add(txtHoraSalida);

		JLabel lblEliminarViaje = new JLabel("Eliminar viaje");
		lblEliminarViaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEliminarViaje.setBounds(386, 11, 119, 22);
		panel_5.add(lblEliminarViaje);

		JLabel label_4 = new JLabel("ID:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(363, 49, 37, 20);
		panel_5.add(label_4);

		JComboBox<Omnibus> comboBox_1 = new JComboBox<Omnibus>();
		comboBox_1.setBounds(386, 51, 150, 20);
		panel_5.add(comboBox_1);

		JButton btnEliminarViaje = new JButton("Eliminar viaje");
		btnEliminarViaje.setBounds(363, 77, 173, 30);
		panel_5.add(btnEliminarViaje);

		final JComboBox destinoViaje = new JComboBox();
		destinoViaje.addItem("Pinar del Río");
		destinoViaje.addItem("Artemisa");
		destinoViaje.addItem("Mayabeque");
		destinoViaje.addItem("Matanzas");
		destinoViaje.addItem("Villa Clara");
		destinoViaje.addItem("Cienfuegos");
		destinoViaje.addItem("Sancti Spíritus");
		destinoViaje.addItem("Ciego de Ávila");
		destinoViaje.addItem("Camagüey");
		destinoViaje.addItem("Las Tunas");
		destinoViaje.addItem("Holguín");
		destinoViaje.addItem("Granma");
		destinoViaje.addItem("Santiago de Cuba");
		destinoViaje.addItem("Guantánamo");
		destinoViaje.setBounds(192, 82, 150, 20);
		panel_5.add(destinoViaje);

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
		lblNombre_2.setBounds(10, 44, 123, 22);
		panel_6.add(lblNombre_2);

		JLabel lblCi_2 = new JLabel("Número Reserva:");
		lblCi_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCi_2.setBounds(10, 76, 123, 22);
		panel_6.add(lblCi_2);

		final JTextField textField_13 = new JTextField();
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_13.setColumns(10);
		textField_13.setBounds(138, 77, 167, 20);
		panel_6.add(textField_13);

		JLabel lblViaje = new JLabel("Viaje:");
		lblViaje.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblViaje.setBounds(10, 108, 118, 22);
		panel_6.add(lblViaje);

		JLabel lblAsiento = new JLabel("Asiento:");
		lblAsiento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAsiento.setBounds(10, 138, 123, 22);
		panel_6.add(lblAsiento);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrecio.setBounds(10, 172, 123, 22);
		panel_6.add(lblPrecio);

		final JLabel labelPrecio = new JLabel("0.00");
		labelPrecio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPrecio.setBounds(138, 178, 167, 14);
		panel_6.add(labelPrecio);

		final JComboBox<Integer> comboBoxAsiento = new JComboBox();
		comboBoxAsiento.setBounds(138, 141, 167, 20);
		panel_6.add(comboBoxAsiento);

		final JComboBox<Pasajero> comboBoxP = new JComboBox();
		comboBoxP.setBounds(138, 47, 167, 20);
		panel_6.add(comboBoxP);

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

		JPanel panelTerminal = new JPanel();
		panelTerminal.setForeground(Color.BLACK);
		tabbedPane.addTab("Terminal", null, panelTerminal, null);
		panelTerminal.setLayout(null);

		JButton btnConductorMes = new JButton("Conductor del Mes");
		btnConductorMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtReportes.setText(terminal.mejoresConductores());
			}
		});
		btnConductorMes.setBounds(422, 0, 170, 30);
		panelTerminal.add(btnConductorMes);

		JButton btnRecaudacionMensual = new JButton("Listado de Pasajeros");
		btnRecaudacionMensual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtReportes.setText(terminal.getReportePasajeros());
			}
		});
		btnRecaudacionMensual.setBounds(213, 41, 175, 30);
		panelTerminal.add(btnRecaudacionMensual);

		JButton btnListarOmnibus = new JButton("Listado de Ómnibus");
		btnListarOmnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtReportes.setText(terminal.getReporteOmnibus());
			}
		});
		btnListarOmnibus.setBounds(422, 41, 170, 30);
		panelTerminal.add(btnListarOmnibus);


		txtReportes = new JTextPane();
		txtReportes.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtReportes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtReportes.setBounds(10, 87, 378, 238);
		panelTerminal.add(txtReportes);

		final JComboBox<Viaje> comboBoxViaje = new JComboBox<Viaje>();
		comboBoxViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Viaje viaje = (Viaje) comboBoxViaje.getSelectedItem();
				for(int asiento : viaje.getAsientos(0)){
					comboBoxAsiento.addItem(asiento);
				}
				labelPrecio.setText(String.valueOf(viaje.precio()));
			}
		});
		comboBoxViaje.setBounds(138, 109, 167, 20);
		panel_6.add(comboBoxViaje);

		JButton btnCrearViaje = new JButton("Crear Viaje");
		btnCrearViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String destino = (String) destinoViaje.getSelectedItem();
				Conductor conductor = (Conductor) comboBoxCond.getSelectedItem();
				Omnibus omnibus = (Omnibus) comBoxOmni.getSelectedItem();
				String fechaSalida = txtFechaSalida.getText();
				String horaSalida = txtHoraSalida.getText();
				LocalDate auxFecha = LocalDate.parse(fechaSalida, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				LocalTime auxHora = LocalTime.parse(horaSalida ,DateTimeFormatter.ofPattern("HH:mm:ss"));
				LocalDateTime estimado = terminal.calcularFechaEstimada(destino, auxFecha.atTime(auxHora));
				String id = idViaje.getText();

				try{
					Viaje viaje = new Viaje(id, terminal.getDistancia(destino), fechaSalida, horaSalida, destino, estimado.toLocalDate(), estimado.toLocalTime(), omnibus, conductor);
					terminal.addViaje(viaje);
					conductor.addViaje(viaje);
					comboBoxViaje.addItem(viaje);
					JOptionPane.showMessageDialog(null, "Viaje registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrearViaje.setBounds(10, 236, 334, 30);
		panel_5.add(btnCrearViaje);
		JButton btnImportarDatos = new JButton("Importar Datos");
		btnImportarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ArrayList<Conductor> conductores = Runner.generarConductores(20, terminal.getConductores(), terminal.getNombresList());
					ArrayList<Omnibus> omnibuses = Runner.generarOmnibus(20, conductores, terminal.getOmnibuses());
					for(Conductor conductor : conductores){
						terminal.addConductor(conductor);
						cond1.addItem(conductor);
						cond2.addItem(conductor);
						cond3.addItem(conductor);

					}
					for(Omnibus omnibus : omnibuses){
						if(omnibus.getDisponibilidad().equals("Disponible")){
							terminal.addOmnibus(omnibus);
						}
						
						comBoxOmni.addItem(omnibus);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Ya se encuentran los datos en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnImportarDatos.setBounds(10, 41, 175, 30);
		panelTerminal.add(btnImportarDatos);

		final JLabel lblNewLabel_1 = new JLabel("Dia: " + terminal.getFechaHora().toLocalDate().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy"))));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(422, 110, 160, 30);
		panelTerminal.add(lblNewLabel_1);

		JLabel lblFechaYHora = new JLabel("Fecha y Hora");
		lblFechaYHora.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFechaYHora.setBounds(432, 82, 160, 22);
		panelTerminal.add(lblFechaYHora);

		comBoxOmni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxCond.removeAllItems();
				ArrayList<Conductor> conductoresOmni = ((Omnibus) comBoxOmni.getSelectedItem()).getConductores();
				for(Conductor c : conductoresOmni){
					comboBoxCond.addItem(c);
				}
			}
		});

		JButton btnCrear_2 = new JButton("Crear");
		btnCrear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Viaje viaje = (Viaje) comboBoxViaje.getSelectedItem();
					float labelPrecio = viaje.precio();
					Pasajero p = (Pasajero) comboBoxP.getSelectedItem();
					String numReserva = textField_13.getText();
					int asiento = (Integer) comboBoxAsiento.getSelectedItem();
					try{
						Reserva r = new Reserva(p, numReserva, viaje.getDestino(), terminal.getFechaHora(), terminal.getFechaHora().plusHours(4), asiento);
						terminal.addReserva(r);
						comboBoxAsiento.removeItemAt(comboBoxAsiento.getSelectedIndex());
						JOptionPane.showMessageDialog(null, "Reserva anadida correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception e){
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Llene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrear_2.setForeground(Color.BLACK);
		btnCrear_2.setBounds(10, 205, 295, 36);
		panel_6.add(btnCrear_2);
		
		JButton btnNewButton = new JButton("Adelantar dia");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminal.setFechaHora(terminal.getFechaHora().plusDays(1));
				lblNewLabel_1.setText("Dia: " + terminal.getFechaHora().toLocalDate().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy"))));
			}
		});
		btnNewButton.setBounds(422, 178, 160, 23);
		panelTerminal.add(btnNewButton);

		txtDdmmaaaa = new JTextField();
		txtDdmmaaaa.setText("dd/mm/aaaa");
		txtDdmmaaaa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDdmmaaaa.setColumns(10);
		txtDdmmaaaa.setBounds(473, 280, 109, 20);
		panelTerminal.add(txtDdmmaaaa);

		JButton btnEstablecerNuevaFecha = new JButton("Establecer fecha");
		btnEstablecerNuevaFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnEstablecerNuevaFecha.setBounds(422, 246, 160, 23);
		panelTerminal.add(btnEstablecerNuevaFecha);

		final JLabel label_3 = new JLabel("Hora: " + terminal.getFechaHora().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(422, 137, 160, 30);
		panelTerminal.add(label_3);

		JButton btnAdelantarHora = new JButton("Adelantar hora");
		btnAdelantarHora.setBounds(422, 212, 160, 23);
		panelTerminal.add(btnAdelantarHora);
		btnAdelantarHora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminal.setFechaHora(terminal.getFechaHora().plusHours(1));
				label_3.setText("Hora: " + terminal.getFechaHora().toLocalTime().format(DateTimeFormatter.ofPattern(("HH:mm:ss"))));
			}
		});

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFecha.setBounds(422, 284, 46, 14);
		panelTerminal.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHora.setBounds(422, 311, 46, 14);
		panelTerminal.add(lblHora);

		txtHoraminsegundo = new JTextField();
		txtHoraminsegundo.setText("hh:mm:ss");
		txtHoraminsegundo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtHoraminsegundo.setColumns(10);
		txtHoraminsegundo.setBounds(473, 309, 109, 20);
		panelTerminal.add(txtHoraminsegundo);

		btnCrear.addActionListener(new ActionListener() {
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

		JButton btnSalariosConductores = new JButton("Ver Salarios Conductores");
		btnSalariosConductores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtReportes.setText(terminal.getReporteSalarios());
			}
		});
		btnSalariosConductores.setBounds(213, 0, 175, 30);
		panelTerminal.add(btnSalariosConductores);

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

					terminal.addConductor(conductor);

					cond1.addItem(conductor);
					cond2.addItem(conductor);
					cond3.addItem(conductor);

					JOptionPane.showMessageDialog(null, "Conductor registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE); 
				}catch(IllegalArgumentException e){
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		JButton btnCrear_3 = new JButton("Crear");
		btnCrear_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = nombrePasajero.getText();
				String id = idPasajero.getText();
				
				try{
					Pasajero p = new Pasajero(nombre, id);
					terminal.addPasajero(p);
					comboBoxP.addItem(p);
					JOptionPane.showMessageDialog(null, "Pasajero registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrear_3.setForeground(Color.BLACK);
		btnCrear_3.setBounds(270, 44, 87, 52);
		panel_8.add(btnCrear_3);

		JButton btnListarConductores = new JButton("Listado de Conductores");
		btnListarConductores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtReportes.setText(terminal.getReporteConductores());
			}
		});
		btnListarConductores.setBounds(10, 0, 175, 30);
		panelTerminal.add(btnListarConductores);

	}
	public boolean getTxtReportesAutoscrolls() {
		return txtReportes.getAutoscrolls();
	}
	public void setTxtReportesAutoscrolls(boolean autoscrolls) {
		txtReportes.setAutoscrolls(autoscrolls);
	}
	
}