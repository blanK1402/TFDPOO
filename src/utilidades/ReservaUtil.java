package utilidades;

import clases.Pasajero;
import clases.Reserva;
import clases.Terminal;
import clases.Viaje;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import utilidades.Utilidades;

public class ReservaUtil {

    public static void importarReservas(Terminal t) throws IOException {
        Pattern patron = Pattern.compile("id: ([0-9]+),([0-9]+),([0-9]+|Sin viaje),([0-9]+|None),(.*),([0-9/]+),([0-9/]+),(.*)");
        
        for (String linea : FileUtils.readLines(".\\BaseDatos\\reservas.txt")) {
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                Reserva r = parseReserva(matcher, t);
                t.addReserva(r);
            }
        }
    }

    private static Reserva parseReserva(Matcher matcher, Terminal t) {
        String pasajeroId = matcher.group(1);
        String numReserva = matcher.group(2);
        String destino = matcher.group(5);
        LocalDate fechaActual = FechaUtils.parsearFecha(matcher.group(6));
        LocalDate fechaDeseada = FechaUtils.parsearFecha(matcher.group(7));
        String estado = matcher.group(8);
        
        Pasajero pasajero = "Cancelada".equals(estado) ? null : t.getPasajero(pasajeroId);
        Reserva r = new Reserva(pasajero, numReserva, destino, fechaActual, fechaDeseada, 0);
        
        if (!"Cancelada".equals(estado)) {
            Viaje viaje = Utilidades.buscarViaje(destino, fechaDeseada);
            if (viaje != null) {
                r.setViaje(viaje);
                viaje.addReservas(r);
            }
        }
        
        return r;
    }

    public static void exportarReservas(List<Reserva> reservas, String filePath) throws IOException {
        FileUtils.writeItems(reservas, filePath);
    }

    public static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        Terminal terminal = Terminal.getTerminal();
        for (Reserva r : terminal.getReservas()) {
            model.addRow(r.toTableList());
        }
        for (Reserva r : terminal.getReservasEspera()) {
            model.addRow(r.toTableList());
        }
    }
}