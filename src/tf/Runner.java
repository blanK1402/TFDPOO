package tf;

import java.awt.EventQueue;

public class Runner {
	private Terminal terminal;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Terminal terminal = new Terminal("Mi Terminal");
					Interfaz frame = new Interfaz(terminal);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Terminal getTerminal(){
		return terminal;
	}
}
