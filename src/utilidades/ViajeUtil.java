package utilidades;

import clases.Conductor;
import clases.Omnibus;
import clases.Terminal;
import clases.Viaje;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class ViajeUtil {

    public static void importarViajes(Terminal t) throws IOException {
        Pattern patron = Pattern.compile("(.*),(.*),(.*),(.*), (.*),(.*), (.*),(.*)");
        Pattern patronId = Pattern.compile("id:([0-9]+)");
        
        for (String linea : FileUtils.readLines(".\\BaseDatos\\viajes.txt")) {
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                Viaje v = parseViaje(matcher, patronId, t);
                t.addViaje(v);
            }
        }
    }

    private static Viaje parseViaje(Matcher matcher, Pattern idPattern, Terminal t) {
        String id = matcher.group(1);
        String destino = matcher.group(2);
        String matricula = matcher.group(3);
        String conductorData = matcher.group(4);
        String fecha = matcher.group(6);
        String hora = matcher.group(7);
        
        Conductor conductor = parseConductor(conductorData, idPattern, t);
        Omnibus omnibus = t.getOmnibus(matricula);
        
        return new Viaje(id, fecha, hora, destino, omnibus, conductor);
    }
    
    private static Conductor parseConductor(String data, Pattern pattern, Terminal t) {
        if (data != null && !"null".equals(data)) {
            Matcher matcherId = pattern.matcher(data);
            if (matcherId.find()) {
                return t.getConductor(matcherId.group(1));
            }
        }
        return null;
    }

    public static void exportarViajes(List<Viaje> viajes, String filePath) throws IOException {
        FileUtils.writeItems(viajes, filePath);
    }

    public static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (Viaje v : Terminal.getTerminal().getViajes()) {
            model.addRow(v.toTableList());
        }
    }
}