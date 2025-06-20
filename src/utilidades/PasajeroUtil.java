package utilidades;

import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

public class PasajeroUtil {

    public static void importarPasajeros(Terminal t) throws IOException {
        Pattern patron = Pattern.compile("(.*),(.*)");
        for (String linea : FileUtils.readLines(".\\BaseDatos\\pasajeros.txt")) {
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                Pasajero p = new Pasajero(
                    matcher.group(1), 
                    matcher.group(2), 
                    Terminal.getTerminal().getFecha().toLocalDate()
                );
                t.addPasajero(p);
            }
        }
    }

    public static void exportarPasajeros(List<Pasajero> pasajeros, String filePath) throws IOException {
        FileUtils.writeItems(pasajeros, filePath);
    }

    public static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (Pasajero p : Terminal.getTerminal().getPasajeros()) {
            model.addRow(p.toTableList());
        }
    }

	public static void cargarReservasUsuario(ArrayList<Reserva> reservas, DefaultTableModel modelReserva) {
		for(Reserva r : reservas){
			modelReserva.addRow(r.toTableList());
		}
	}
}