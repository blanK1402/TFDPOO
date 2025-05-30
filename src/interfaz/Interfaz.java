package interfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import utilidades.Utilidades;
import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Interfaz extends JFrame {

	Terminal terminal = new Terminal("Terminal");
	private static final Color COLOR = new Color(0, 120, 215);

	public Interfaz(Terminal t) {
		this.terminal = t;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1006, 600);
		setTitle("Gestión de Transporte");
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 10, 980, 540);
		getContentPane().add(tabbedPane);

		// Pestaña PASAJERO

		String[] columnNamesPasajero = {"Nombre", "ID"};
		final DefaultTableModel modelPasajero = new DefaultTableModel(columnNamesPasajero, 0);
		final JTable tablePasajero = new JTable(modelPasajero);
		tablePasajero.setRowHeight(30);
		tablePasajero.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tablePasajero.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tablePasajero.getTableHeader().setBackground(new Color(50, 50, 50));
		tablePasajero.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollPasajero = new JScrollPane(tablePasajero);

		JPanel panelPasajero = new JPanel(new BorderLayout());
		JPanel botonesPasajero = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnCrearPasajero = new JButton("Crear Pasajero");

		btnCrearPasajero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearPasajero(modelPasajero);
			}
		});

		btnCrearPasajero.setBackground(COLOR);
		btnCrearPasajero.setForeground(Color.WHITE);
		btnCrearPasajero.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEditarPasajero = new JButton("Editar");
		btnEditarPasajero.setBackground(COLOR);
		btnEditarPasajero.setForeground(Color.WHITE);
		btnEditarPasajero.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEliminarPasajero = new JButton("Eliminar");
		btnEliminarPasajero.setBackground(COLOR);
		btnEliminarPasajero.setForeground(Color.WHITE);
		btnEliminarPasajero.setFont(new Font("SansSerif", Font.BOLD, 14));
		botonesPasajero.add(btnCrearPasajero);
		botonesPasajero.add(btnEditarPasajero);
		botonesPasajero.add(btnEliminarPasajero);
		panelPasajero.add(botonesPasajero, BorderLayout.NORTH);

		panelPasajero.add(scrollPasajero, BorderLayout.CENTER);
		tabbedPane.addTab("Pasajero", panelPasajero);

		// Pestaña CONDUCTOR

		String[] columnNamesConductor = {"Nombre", "ID", "Categoría", "Años de Experiencia", "Licencia"};
		final DefaultTableModel modelConductor = new DefaultTableModel(columnNamesConductor, 0);
		JTable tableConductor = new JTable(modelConductor);
		tableConductor.setRowHeight(30);
		tableConductor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableConductor.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tableConductor.getTableHeader().setBackground(new Color(50, 50, 50));
		tableConductor.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollConductor = new JScrollPane(tableConductor);

		JPanel panelConductor = new JPanel(new BorderLayout());
		JPanel botonesConductor = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnCrearConductor = new JButton("Crear Conductor");
		btnCrearConductor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearConductor(modelConductor);
			}
		});

		btnCrearConductor.setBackground(COLOR);
		btnCrearConductor.setForeground(Color.WHITE);
		btnCrearConductor.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEditarConductor = new JButton("Editar");
		btnEditarConductor.setBackground(COLOR);
		btnEditarConductor.setForeground(Color.WHITE);
		btnEditarConductor.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEliminarConductor = new JButton("Eliminar");
		btnEliminarConductor.setBackground(COLOR);
		btnEliminarConductor.setForeground(Color.WHITE);
		btnEliminarConductor.setFont(new Font("SansSerif", Font.BOLD, 14));
		botonesConductor.add(btnCrearConductor);
		botonesConductor.add(btnEditarConductor);
		botonesConductor.add(btnEliminarConductor);
		panelConductor.add(botonesConductor, BorderLayout.NORTH);

		panelConductor.add(scrollConductor, BorderLayout.CENTER);
		tabbedPane.addTab("Conductor", panelConductor);

		// Pestaña OMNIBUS

		JPanel panelOmnibus = new JPanel(new BorderLayout());

		String[] columnNamesOmnibus = {"Matrícula", "Asientos", "A/C", "Televisor", "Baño", "Disponibilidad", "Conductores"};
		Object[][] dataOmnibus = {};
		final DefaultTableModel modelOmnibus = new DefaultTableModel(dataOmnibus, columnNamesOmnibus);
		JTable tableOmnibus = new JTable(modelOmnibus);
		tableOmnibus.setRowHeight(30);
		tableOmnibus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableOmnibus.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tableOmnibus.getTableHeader().setBackground(new Color(50, 50, 50));
		tableOmnibus.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollOmnibus = new JScrollPane(tableOmnibus);
		panelOmnibus.add(scrollOmnibus, BorderLayout.CENTER);

		JPanel botonesOmnibus = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnCrearOmnibus = new JButton("Crear Omnibus");
		btnCrearOmnibus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearOmnibus(modelOmnibus);
			}
		});

		btnCrearOmnibus.setBackground(COLOR);
		btnCrearOmnibus.setForeground(Color.WHITE);
		btnCrearOmnibus.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEditarOmnibus = new JButton("Editar");
		btnEditarOmnibus.setBackground(COLOR);
		btnEditarOmnibus.setForeground(Color.WHITE);
		btnEditarOmnibus.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEliminarOmnibus = new JButton("Eliminar");
		btnEliminarOmnibus.setBackground(COLOR);
		btnEliminarOmnibus.setForeground(Color.WHITE);
		btnEliminarOmnibus.setFont(new Font("SansSerif", Font.BOLD, 14));
		botonesOmnibus.add(btnCrearOmnibus);
		botonesOmnibus.add(btnEditarOmnibus);
		botonesOmnibus.add(btnEliminarOmnibus);
		panelOmnibus.add(botonesOmnibus, BorderLayout.NORTH);
		tabbedPane.addTab("Omnibus", panelOmnibus);

		// Pestaña VIAJE

		String[] columnasViaje = {"ID", "Destino", "Ómnibus", "Conductor", "Fecha Salida", "Precio"};
		final DefaultTableModel modelViaje = new DefaultTableModel();
		for(String columna : columnasViaje){
			modelViaje.addColumn(columna);
		}
		final JTable tablaViaje = new JTable(modelViaje);
		tablaViaje.setRowHeight(30);
		tablaViaje.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tablaViaje.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tablaViaje.getTableHeader().setBackground(new Color(50, 50, 50));
		tablaViaje.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollViaje = new JScrollPane(tablaViaje);

		JPanel panelViaje = new JPanel(new BorderLayout());
		JPanel botonesViaje = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnCrearViaje = new JButton("Crear Viaje");
		btnCrearViaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearViaje(modelViaje);
			}
		});

		btnCrearViaje.setBackground(COLOR);
		btnCrearViaje.setForeground(Color.WHITE);
		btnCrearViaje.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEditarViaje = new JButton("Editar");
		btnEditarViaje.setBackground(COLOR);
		btnEditarViaje.setForeground(Color.WHITE);
		btnEditarViaje.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEliminarViaje = new JButton("Eliminar");
		btnEliminarViaje.setBackground(COLOR);
		btnEliminarViaje.setForeground(Color.WHITE);
		btnEliminarViaje.setFont(new Font("SansSerif", Font.BOLD, 14));
		botonesViaje.add(btnCrearViaje);
		botonesViaje.add(btnEditarViaje);
		botonesViaje.add(btnEliminarViaje);
		panelViaje.add(botonesViaje, BorderLayout.NORTH);

		panelViaje.add(scrollViaje, BorderLayout.CENTER);
		tabbedPane.addTab("Viaje", panelViaje);

		// Pestaña RESERVA

		String[] columnNamesReserva = {"Pasajero", "Nro Reservación", "Destino", "Fecha Reservación", "Fecha Viaje", "Estado"};
		Object[][] dataReserva = {};
		final DefaultTableModel modelReserva = new DefaultTableModel(dataReserva, columnNamesReserva);
		JTable tableReserva = new JTable(modelReserva);
		tableReserva.setRowHeight(30);
		tableReserva.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableReserva.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tableReserva.getTableHeader().setBackground(new Color(50, 50, 50));
		tableReserva.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollReserva = new JScrollPane(tableReserva);


		JPanel panelReserva = new JPanel(new BorderLayout());
		JPanel botonesReserva = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnCrearReserva = new JButton("Crear Reserva");
		btnCrearReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearReserva(modelReserva);
			}
		});
		btnCrearReserva.setBackground(COLOR);
		btnCrearReserva.setForeground(Color.WHITE);
		btnCrearReserva.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEditarReserva = new JButton("Editar");
		btnEditarReserva.setBackground(COLOR);
		btnEditarReserva.setForeground(Color.WHITE);
		btnEditarReserva.setFont(new Font("SansSerif", Font.BOLD, 14));
		JButton btnEliminarReserva = new JButton("Eliminar");
		btnEliminarReserva.setBackground(COLOR);
		btnEliminarReserva.setForeground(Color.WHITE);
		btnEliminarReserva.setFont(new Font("SansSerif", Font.BOLD, 14));
		botonesReserva.add(btnCrearReserva);
		botonesReserva.add(btnEditarReserva);
		botonesReserva.add(btnEliminarReserva);
		panelReserva.add(botonesReserva, BorderLayout.NORTH);
		panelReserva.add(scrollReserva, BorderLayout.CENTER);

		tabbedPane.addTab("Reserva", panelReserva);

		// Pestaña TERMINAL

		JPanel panelTerminal = new JPanel(new BorderLayout());
		JPanel panelImportar = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnImportarDatos = new JButton("Importar Datos");
		
		btnImportarDatos.setBackground(COLOR);
		btnImportarDatos.setForeground(Color.WHITE);
		btnImportarDatos.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelImportar.add(btnImportarDatos);
		panelTerminal.add(panelImportar, BorderLayout.NORTH);
		JPanel panelCentroTerminal = new JPanel();
		panelCentroTerminal.setLayout(new BoxLayout(panelCentroTerminal, BoxLayout.Y_AXIS));

		JPanel panelReportes = new JPanel();
		panelReportes.setBorder(BorderFactory.createTitledBorder("Reportes"));
		panelReportes.setLayout(null);

		JButton btnReporte1 = new JButton("conductores con mas viajes");
		btnReporte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Conductor> conductores = terminal.getConductores();
				int maxViajes = 0;
				ArrayList<Conductor> conductoresConMasViajes = new ArrayList<>();

				for (Conductor c : conductores) {
					int numViajes = c.getViajes().size();
					if (numViajes > maxViajes) {
						maxViajes = numViajes;
						conductoresConMasViajes.clear(); 
						conductoresConMasViajes.add(c);
					} else if (numViajes == maxViajes) {
						conductoresConMasViajes.add(c); 
					}
				}

				System.out.println("Conductores con más viajes (" + maxViajes + " viajes):");
				for (Conductor c : conductoresConMasViajes) {
					System.out.println("- " + c.getNombre());
				}
			}
		});

		btnReporte1.setBounds(11, 21, 265, 25);
		btnReporte1.setBackground(COLOR);
		btnReporte1.setForeground(Color.WHITE);
		btnReporte1.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte1);

		JButton btnReporte2 = new JButton("Salarios de Conductores");
		btnReporte2.setBounds(11, 111, 265, 25);
		btnReporte2.setBackground(COLOR);
		btnReporte2.setForeground(Color.WHITE);
		btnReporte2.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte2);

		JButton btnReporte3 = new JButton("Omnibus Disponibles");
		btnReporte3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Omnibus disponibles: ");
				for(Omnibus o : terminal.getOmnibuses()){
					if(o.getDisponibilidad().equals("Disponible")){
						System.out.println(o);
					}
				}
			}
		});
		btnReporte3.setBounds(11, 51, 265, 25);
		btnReporte3.setBackground(COLOR);
		btnReporte3.setForeground(Color.WHITE);
		btnReporte3.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte3);

		JButton btnReporte4 = new JButton("Viajes con Mayor Capacidad Vendida");
		btnReporte4.setBounds(286, 111, 265, 25);
		btnReporte4.setBackground(COLOR);
		btnReporte4.setForeground(Color.WHITE);
		btnReporte4.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte4);

		JButton btnReporte5 = new JButton("Listado Reservaciones");
		btnReporte5.setBounds(11, 81, 265, 25);
		btnReporte5.setBackground(COLOR);
		btnReporte5.setForeground(Color.WHITE);
		btnReporte5.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte5);

		JButton btnReporte6 = new JButton("Pasajeros Frecuentes");
		btnReporte6.setBounds(286, 21, 265, 25);
		btnReporte6.setBackground(COLOR);
		btnReporte6.setForeground(Color.WHITE);
		btnReporte6.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte6);

		JButton btnReporte8 = new JButton("Destinos Populares");
		btnReporte8.setBounds(286, 51, 265, 25);
		btnReporte8.setBackground(COLOR);
		btnReporte8.setForeground(Color.WHITE);
		btnReporte8.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte8);

		JButton btnReporte10 = new JButton("Recaudación Último Mes");
		btnReporte10.setBounds(286, 81, 265, 25);
		btnReporte10.setBackground(COLOR);
		btnReporte10.setForeground(Color.WHITE);
		btnReporte10.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelReportes.add(btnReporte10);

		panelCentroTerminal.add(panelReportes);

		JPanel panelFecha = new JPanel();
		panelFecha.setBorder(BorderFactory.createTitledBorder("Fecha"));
		panelFecha.setLayout(null);

		JButton btnAdelantarHora = new JButton("Adelantar Hora");
		btnAdelantarHora.setBounds(157, 55, 149, 27);
		btnAdelantarHora.setBackground(COLOR);
		btnAdelantarHora.setForeground(Color.WHITE);
		btnAdelantarHora.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelFecha.add(btnAdelantarHora);

		JButton btnEstablecerFecha = new JButton("Establecer Nueva Fecha");
		btnEstablecerFecha.setBounds(11, 90, 295, 27);
		btnEstablecerFecha.setBackground(COLOR);
		btnEstablecerFecha.setForeground(Color.WHITE);
		btnEstablecerFecha.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelFecha.add(btnEstablecerFecha);

		panelCentroTerminal.add(panelFecha);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFecha.setBounds(11, 28, 46, 14);
		panelFecha.add(lblFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblHora.setBounds(157, 26, 46, 14);
		panelFecha.add(lblHora);

		final JLabel label = new JLabel(terminal.getFecha().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label.setBounds(58, 28, 90, 14);
		panelFecha.add(label);

		final JLabel label_1 = new JLabel(terminal.getFecha().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label_1.setBounds(195, 26, 90, 14);
		panelFecha.add(label_1);
		panelTerminal.add(panelCentroTerminal, BorderLayout.CENTER);
		tabbedPane.addTab("Terminal", panelTerminal);

		JButton btnAdelantarDia = new JButton("Adelantar Día");
		btnAdelantarDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				terminal.adelantarDia();
				label.setText(terminal.getFecha().toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			}
		});
		btnAdelantarDia.setBounds(11, 55, 137, 27);
		btnAdelantarDia.setBackground(COLOR);
		btnAdelantarDia.setForeground(Color.WHITE);
		btnAdelantarDia.setFont(new Font("SansSerif", Font.BOLD, 14));
		panelFecha.add(btnAdelantarDia);

	}

	public void crearPasajero(DefaultTableModel modelPasajero) {
		VentanaPasajero ventanaPasajero = new VentanaPasajero(Interfaz.this, terminal.getPasajeros(), terminal.getFecha().toLocalDate());
		ventanaPasajero.setVisible(true);

		if (ventanaPasajero.isConfirmado()) {
			try {
				Pasajero nuevoPasajero = ventanaPasajero.getPasajero();
				terminal.addPasajero(nuevoPasajero);
				modelPasajero.addRow(nuevoPasajero.toTableList());
				JOptionPane.showMessageDialog(null, "Pasajero creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				crearPasajero(modelPasajero);
			}
		}
	}


	public void crearConductor(DefaultTableModel modelConductor) {
		VentanaConductor ventanaConductor = new VentanaConductor(Interfaz.this, terminal.getConductores());
		ventanaConductor.setVisible(true);

		if (ventanaConductor.isConfirmado()) {
			try {
				Conductor nuevoConductor = ventanaConductor.getConductor();
				terminal.addConductor(nuevoConductor);
				modelConductor.addRow(nuevoConductor instanceof ConductorA ? 
						((ConductorA)nuevoConductor).toTableList() 
						: nuevoConductor instanceof ConductorB ? ((ConductorB)nuevoConductor).toTableList() 
								: ((ConductorC)nuevoConductor).toTableList());
				JOptionPane.showMessageDialog(null, "ConductorCreadoCorrectamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				crearConductor(modelConductor);
			}
		}
	}

	public void crearOmnibus(DefaultTableModel modelOmnibus) {
		VentanaOmnibus ventanaOmnibus = new VentanaOmnibus(Interfaz.this, terminal.getConductores());
		ventanaOmnibus.setVisible(true);

		if (ventanaOmnibus.isConfirmado()) {
			try {
				Omnibus nuevoOmnibus = ventanaOmnibus.getOmnibus();
				terminal.addOmnibus(nuevoOmnibus);
				modelOmnibus.addRow(nuevoOmnibus.toTableList());
				JOptionPane.showMessageDialog(null, "Ómnibus creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				crearOmnibus(modelOmnibus);
			}
		}
	}

	public void crearViaje(DefaultTableModel modelViaje) {
		VentanaViaje ventanaViaje = new VentanaViaje(Interfaz.this, terminal.getOmnibuses(), terminal.getConductores(), terminal.getFecha(), terminal.getViajes());
		ventanaViaje.setVisible(true);

		if (ventanaViaje.isConfirmado()) {
			try {
				Viaje nuevoViaje = ventanaViaje.getViaje();
				terminal.addViaje(nuevoViaje);
				modelViaje.addRow(nuevoViaje.toTableList());
				JOptionPane.showMessageDialog(null, "Viaje creado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				crearViaje(modelViaje);
			}
		}
	}

	public void crearReserva(DefaultTableModel modelReserva) {
		VentanaReserva ventanaReserva = new VentanaReserva(Interfaz.this, terminal.getViajes(), terminal.getPasajeros(), terminal.getFecha());
		ventanaReserva.setVisible(true);

		if (ventanaReserva.isConfirmado()) {
			try {
				Reserva nuevaReserva = ventanaReserva.getReserva();
				terminal.addReserva(nuevaReserva);
				modelReserva.addRow(nuevaReserva.toTableList());
				JOptionPane.showMessageDialog(null, "Reserva creada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				crearReserva(modelReserva);
			}
		}
	}


}
