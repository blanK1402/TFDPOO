package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utilidades.Datos;
import clases.Pasajero;
import clases.Terminal;

import java.awt.SystemColor;
import java.io.IOException;

import interfaz.VentanaReserva;

public class InterfazUsuario extends JFrame {

    private Pasajero pasajero;
    private JPanel contentPane;
    private JTable tableReserva;
    private DefaultTableModel modelReserva;
    private Terminal terminal;

    public InterfazUsuario(Pasajero pasajero) {
        setPasajero(pasajero);
        setTerminal();
        
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setTitle("Interfaz de Usuario - " + pasajero.getNombre());
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        configurarTablaReservas();
        
        JPanel panelBotones = crearPanelBotones();
        
        contentPane.add(panelBotones, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(tableReserva), BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cerrarAplicacion();
            }
        });
    }

    private void setTerminal() {
		terminal = Terminal.getTerminal();
	}

	private void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	private void cerrarAplicacion() {
        dispose();
    }
	
	private void configurarTablaReservas() {
        String[] columnNames = {"Pasajero", "Nro Reservación", "Viaje", "Asiento", "Destino", "Fecha Reservación", "Fecha Viaje", "Estado"};
        
        modelReserva = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        Datos.cargarReservasUsuario(pasajero.getReservas(), modelReserva);
        
        tableReserva = new JTable(modelReserva);
        tableReserva.setRowHeight(30);
        tableReserva.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tableReserva.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        tableReserva.getTableHeader().setBackground(new Color(0, 120, 215));
        tableReserva.getTableHeader().setForeground(Color.WHITE);
        tableReserva.setAutoCreateRowSorter(true);
    }


    private JPanel crearPanelBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.setBackground(Color.WHITE);
        
        JButton btnNuevaReserva = new JButton("Nueva Reserva");
        btnNuevaReserva.setBackground(new Color(0, 120, 215));
        btnNuevaReserva.setForeground(Color.WHITE);
        btnNuevaReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNuevaReserva();
            }
        });
        
        panelBotones.add(btnNuevaReserva);
        
        JButton btnEditarReserva = new JButton("Editar Reserva");
        btnEditarReserva.setForeground(Color.WHITE);
        btnEditarReserva.setBackground(SystemColor.textHighlight);
        panelBotones.add(btnEditarReserva);
        
        JButton btnEliminarReserva = new JButton("Eliminar Reserva");
        btnEliminarReserva.setForeground(Color.WHITE);
        btnEliminarReserva.setBackground(SystemColor.textHighlight);
        panelBotones.add(btnEliminarReserva);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					Datos.guardarDatos();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        });
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(SystemColor.textHighlight);
        panelBotones.add(btnGuardar);
        
        return panelBotones;
    }

    private void abrirNuevaReserva() {
		VentanaReserva ventana = new VentanaReserva(InterfazUsuario.this, pasajero);
		ventana.setVisible(true);
		if(ventana.Confirmada()){
			terminal.addReserva(ventana.getReserva());
			modelReserva.addRow(ventana.getReserva().toTableList());
		}
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}