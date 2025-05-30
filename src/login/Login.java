package login;

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

import clases.Terminal;
import runner.Runner;
import utilidades.Utilidades;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtAsda;
	private JTextField textField;

	public Login(final Terminal terminal) {
		
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

		txtAsda = new JTextField();
		txtAsda.setFocusCycleRoot(true);
		txtAsda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtAsda.setBounds(10, 145, 185, 27);
		contentPane.add(txtAsda);
		txtAsda.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(10, 11, 110, 37);
		contentPane.add(lblUsuario);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setFocusCycleRoot(true);
		textField.setColumns(10);
		textField.setBounds(10, 59, 185, 27);
		contentPane.add(textField);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArrayList<String> usuarioContrasena = new ArrayList<String>();
					usuarioContrasena.add(textField.getText());
					usuarioContrasena.add(txtAsda.getText());
					try{
						Usuario usuario = Utilidades.login(usuarioContrasena);
						if(usuario.getRol().equals("Admin")){
							Runner.lanzarInterfazAdmin(terminal);
							dispose();
						}
						else{
							Runner.lanzarInterfazUsuario(terminal.getPasajero(usuario.getUsuario()));
						}
					}
					catch(IllegalArgumentException e){
						JOptionPane.showMessageDialog(null, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				} catch (IllegalArgumentException | IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(10, 183, 86, 23);
		contentPane.add(btnAceptar);

		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(109, 183, 86, 23);
		contentPane.add(btnSalir);
	}
}
