package login;

import java.util.ArrayList;

public class Usuario {
	private String usuario;
	private String contrasena;
    private String rol;

    public Usuario(String usuario, String contrasena, String rol) {
    	setUsuario(usuario);
        setContrasena(contrasena);
        setRol(rol);
    }
    

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contrasena;
	}

	public void setContrasena(String contraseña) {
		this.contrasena = contraseña;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
    public String getUsuario() {
		return usuario;
	}

}
