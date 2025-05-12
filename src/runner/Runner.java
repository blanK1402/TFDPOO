package runner;

import interfaz.Interfaz;

import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Omnibus;
import clases.Terminal;

public class Runner {
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}