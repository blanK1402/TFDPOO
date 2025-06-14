package login;

import interfaz.Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import clases.Pasajero;
import clases.Terminal;
import runner.Runner;
import utilidades.Datos;
import utilidades.Utilidades;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField textUsuario;
	private Terminal terminal;

	public Login() {
		setT();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 220, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContrasea.setBounds(10, 97, 110, 37);
		contentPane.add(lblContrasea);

		txtId = new JTextField();
		txtId.setFocusCycleRoot(true);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtId.setBounds(10, 145, 185, 27);
		contentPane.add(txtId);
		txtId.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(10, 11, 110, 37);
		contentPane.add(lblUsuario);

		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textUsuario.setFocusCycleRoot(true);
		textUsuario.setColumns(10);
		textUsuario.setBounds(10, 59, 185, 27);
		contentPane.add(textUsuario);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<String> usuarioContrasena = new ArrayList<String>();
					usuarioContrasena.add(textUsuario.getText());
					usuarioContrasena.add(txtId.getText());
					
					Usuario usuario = Utilidades.login(usuarioContrasena);
					
					if(usuario.getRol().equals("Admin")){
						Interfaz frame = new Interfaz();
						frame.setVisible(true);
						dispose();
					}
					else if(usuario.getRol().equals("User")){
						Pasajero pasajero = terminal.getPasajero(usuario.getUsuario());
						Runner.lanzarInterfazUsuario(pasajero, terminal);
						dispose();
					}
				} catch (IllegalArgumentException | IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(10, 183, 86, 23);
		contentPane.add(btnAceptar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(109, 183, 86, 23);
		contentPane.add(btnSalir);
	}

	private void setT() {
		terminal = Terminal.getTerminal();
	}
}
