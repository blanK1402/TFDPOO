package utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import login.Usuario;

public class UsuarioUtil {

    public static void importarContrasenas(Map<String, Usuario> contrasenas) throws IOException {
        try (BufferedReader txt = new BufferedReader(new FileReader(".\\BaseDatos\\contrasenas.txt"))) {
            String linea;
            Pattern patron = Pattern.compile("([0-9]+),(.*),(.*)");
            
            while ((linea = txt.readLine()) != null) {
                Matcher matcher = patron.matcher(linea);
                if (matcher.find()) {
                    Usuario usuario = new Usuario(
                        matcher.group(1), 
                        matcher.group(2), 
                        matcher.group(3)
                    );
                    contrasenas.put(usuario.getUsuario(), usuario);
                }
            }
        }
    }

    public static void exportarUsuarios(ArrayList<Usuario> usuarios, String filePath) throws IOException {
        FileUtils.writeItems(usuarios, filePath);
    }
}