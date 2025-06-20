package utilidades;

import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import clases.Reserva;
import clases.Terminal;
import login.Usuario;

public class Datos {

    public static void importarDatos() throws IOException {
        Terminal t = Terminal.getTerminal();
        t.clear();
        
        PasajeroUtil.importarPasajeros(t);
        ConductorUtil.importarConductores(t);
        OmnibusUtil.importarOmnibuses(t);
        ViajeUtil.importarViajes(t);
        ReservaUtil.importarReservas(t);
    }

    public static void guardarDatos() throws IOException {
        Terminal t = Terminal.getTerminal();
        String basePath = ".\\BaseDatos\\";
        
        PasajeroUtil.exportarPasajeros(t.getPasajeros(), basePath + "pasajeros.txt");
        ConductorUtil.exportarConductores(t.getConductores(), basePath + "conductores.txt");
        OmnibusUtil.exportarOmnibuses(t.getOmnibuses(), basePath + "omnibuses.txt");
        ViajeUtil.exportarViajes(t.getViajes(), basePath + "viajes.txt");
        
        ArrayList<Reserva> todasReservas = new ArrayList<>();
        todasReservas.addAll(t.getReservas());
        todasReservas.addAll(t.getReservasEspera());
        todasReservas.addAll(t.getReservasCanceladas());
        ReservaUtil.exportarReservas(todasReservas, basePath + "reservas.txt");
        
        UsuarioUtil.exportarUsuarios(t.getUsuarios(), basePath + "contrasenas.txt");
    }

    public static void cargarContrasenas(Map<String, Usuario> contrasenas) throws IOException {
        UsuarioUtil.importarContrasenas(contrasenas);
    }

    public static void actualizarTablaPasajeros(DefaultTableModel model) {
        PasajeroUtil.actualizarTabla(model);
    }

    public static void actualizarTablaConductores(DefaultTableModel model) {
        ConductorUtil.actualizarTabla(model);
    }

    public static void actualizarTablaOmnibuses(DefaultTableModel model) {
        OmnibusUtil.actualizarTabla(model);
    }

    public static void actualizarTablaViajes(DefaultTableModel model) {
        ViajeUtil.actualizarTabla(model);
    }

    public static void actualizarTablaReservas(DefaultTableModel model) {
        ReservaUtil.actualizarTabla(model);
    }
}