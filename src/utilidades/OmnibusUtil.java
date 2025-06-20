package utilidades;

import clases.Conductor;
import clases.Omnibus;
import clases.Terminal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class OmnibusUtil {

    public static void importarOmnibuses(Terminal t) throws IOException {
        Pattern patron = Pattern.compile("^([A-Z]{1}[0-9]{6}),([0-9]*),([Si]{2}|[No]{2}),([Si]{2}|[No]{2}),([Si]{2}|[No]{2}),([^,]+),(.*)$");
        Pattern patronId = Pattern.compile("id:([0-9]+)");
        
        for (String linea : FileUtils.readLines(".\\BaseDatos\\omnibuses.txt")) {
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                Omnibus o = parseOmnibus(matcher, patronId, t);
                t.addOmnibus(o);
            }
        }
    }

    private static Omnibus parseOmnibus(Matcher matcher, Pattern idPattern, Terminal t) {
        String matricula = matcher.group(1);
        String asientos = matcher.group(2);
        ArrayList<String> comodidades = new ArrayList<>();
        if ("Si".equals(matcher.group(3))) comodidades.add("Aire acondicionado");
        if ("Si".equals(matcher.group(4))) comodidades.add("TV");
        if ("Si".equals(matcher.group(5))) comodidades.add("Baño");
        String estado = matcher.group(6);
        
        Omnibus o = new Omnibus(matricula, asientos, estado, comodidades);
        parseConductores(matcher.group(7), idPattern, o, t);
        
        return o;
    }
    
    private static void parseConductores(String data, Pattern pattern, Omnibus omnibus, Terminal t) {
        Matcher matcherId = pattern.matcher(data);
        while (matcherId.find()) {
            Conductor c = t.getConductor(matcherId.group(1));
            if (c != null) {
                omnibus.addConductor(c);
            }
        }
    }

    public static void exportarOmnibuses(List<Omnibus> omnibuses, String filePath) throws IOException {
        FileUtils.writeItems(omnibuses, filePath);
    }

    public static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (Omnibus o : Terminal.getTerminal().getOmnibuses()) {
            model.addRow(o.toTableList());
        }
    }
}