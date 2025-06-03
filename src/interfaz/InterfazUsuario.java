package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import utilidades.Datos;
import clases.Pasajero;

public class InterfazUsuario extends JFrame {

    private Pasajero pasajero;
    private JPanel contentPane;
    private JTable tableReserva;

    public InterfazUsuario(Pasajero pasajero) {
        this.pasajero = pasajero;
        
        // Configuración básica de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setTitle("Interfaz de Usuario - " + pasajero.getNombre());
        
        // Panel principal
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // Configuración de la tabla de reservas
        configurarTablaReservas();
        
        // Panel de botones
        JPanel panelBotones = crearPanelBotones();
        
        // Agregar componentes al contentPane
        contentPane.add(panelBotones, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(tableReserva), BorderLayout.CENTER);
    }

    private void configurarTablaReservas() {
    	String[] columnNames = {"Pasajero", "Nro Reservación", "Viaje", "Asiento", "Destino", "Fecha Reservación", "Fecha Viaje", "Estado"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        Datos.cargarReservasUsuario(pasajero.getReservas(), model);
        
        tableReserva = new JTable(model);
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
        
        // Botón para nueva reserva (con clase anónima)
        JButton btnNuevaReserva = new JButton("Nueva Reserva");
        btnNuevaReserva.setBackground(new Color(0, 120, 215));
        btnNuevaReserva.setForeground(Color.WHITE);
        btnNuevaReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirNuevaReserva();
            }
        });
        
        // Botón para actualizar (con clase anónima)
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        
        panelBotones.add(btnNuevaReserva);
        panelBotones.add(btnActualizar);
        
        return panelBotones;
    }

    private void abrirNuevaReserva() {
        // Implementar lógica para abrir ventana de nueva reserva
    }

    private void actualizarTabla() {
        DefaultTableModel model = (DefaultTableModel) tableReserva.getModel();
        model.setRowCount(0);
        Datos.cargarReservasUsuario(pasajero.getReservas(), model);
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}