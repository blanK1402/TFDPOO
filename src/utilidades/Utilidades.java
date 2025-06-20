package utilidades;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import login.Usuario;
import clases.Terminal;
import clases.Viaje;

public class Utilidades {
    
    public static Viaje buscarViaje(String destino, LocalDate fechaDeseada) {
        HashMap<String, ArrayList<Viaje>> destinosViajes = Terminal.getTerminal().getDestinosViajes();
        ArrayList<Viaje> viajes = destinosViajes.get(destino);
        
        if (viajes != null) {
            for (Viaje v : viajes) {
                if (v.getFechaHoraPartida().toLocalDate().equals(fechaDeseada) && 
                    "Disponible".equals(v.getOmnibus().getDisponibilidad().trim()) && 
                    v.getAsientosLibres().size() > 0) {
                    return v;
                }
            }
        }
        return null;
    }

    public static Usuario login(List<String> usuarioContrasena) throws IOException {
        HashMap<String, Usuario> contrasenas = new HashMap<>();
        String usuario = usuarioContrasena.get(0);
        String contrasena = usuarioContrasena.get(1);
        
        Datos.cargarContrasenas(contrasenas);
        contrasenas.put("Admin", new Usuario("Admin", "1234", "Admin"));

        if (contrasenas.containsKey(usuario) && contrasenas.get(usuario).getContraseña().equals(contrasena)) {
            return contrasenas.get(usuario);
        } else {
            throw new IllegalArgumentException("Datos incorrectos");
        }
    }
}