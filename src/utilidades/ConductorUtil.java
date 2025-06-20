package utilidades;

import clases.Conductor;
import clases.ConductorA;
import clases.ConductorB;
import clases.ConductorC;
import clases.Terminal;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;

public class ConductorUtil {

    public static void importarConductores(Terminal t) throws IOException {
        Pattern patron = Pattern.compile("([a-zA-Z]+),([0-9]+),([A]|[B]|[C]),([0-9]+),([0-9]+)");
        for (String linea : FileUtils.readLines(".\\BaseDatos\\conductores.txt")) {
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                String nombre = matcher.group(1);
                String id = matcher.group(2);
                String exp = matcher.group(4);
                String licencia = matcher.group(5);
                
                Conductor conductor;
                switch (matcher.group(3)) {
                    case "A":
                        conductor = new ConductorA(nombre, id, exp, licencia);
                        break;
                    case "B":
                        conductor = new ConductorB(nombre, id, exp, licencia);
                        break;
                    case "C":
                        conductor = new ConductorC(nombre, id, exp, licencia);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de conductor no válido");
                }
                t.addConductor(conductor);
            }
        }
    }

    public static void exportarConductores(List<Conductor> conductores, String filePath) throws IOException {
        FileUtils.writeItems(conductores, filePath);
    }

    public static void actualizarTabla(DefaultTableModel model) {
        model.setRowCount(0);
        for (Conductor c : Terminal.getTerminal().getConductores()) {
            model.addRow(c.toTableList());
        }
    }
}