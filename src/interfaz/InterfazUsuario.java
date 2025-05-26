package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utilidades.Utilidades;
import clases.Pasajero;
import clases.Terminal;

public class InterfazUsuario extends JFrame {

	private JPanel contentPane;

	public InterfazUsuario(Pasajero usuario) {
		final Color COLOR = new Color(0, 120, 215);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		String[] columnNamesReserva = {"Pasajero", "Nro Reservación", "Destino", "Fecha Reservación", "Fecha Viaje", "Estado"};
		Object[][] dataReserva = {};
		final DefaultTableModel modelReserva = new DefaultTableModel(dataReserva, columnNamesReserva);
		Utilidades.cargarReservasUsuario(usuario.getReservas(), modelReserva);
		JTable tableReserva = new JTable(modelReserva);
		tableReserva.setRowHeight(30);
		tableReserva.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tableReserva.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tableReserva.getTableHeader().setBackground(new Color(50, 50, 50));
		tableReserva.getTableHeader().setForeground(Color.WHITE);
		JScrollPane scrollReserva = new JScrollPane(tableReserva);


		JPanel panelReserva = new JPanel(new BorderLayout());
		JPanel botonesReserva = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelReserva.add(botonesReserva, BorderLayout.NORTH);
		panelReserva.add(scrollReserva, BorderLayout.CENTER);

	}

}
