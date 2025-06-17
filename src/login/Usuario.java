package login;

import Interfaces.Mostrable;

public class Usuario implements Mostrable{
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
    
    public String[] toTableList() {
        String usuarioLimpio = (usuario != null) ? usuario.trim() : "null";
        String rolLimpio = (rol != null) ? rol.trim() : "null";

        return new String[] {
            usuarioLimpio,
            "0000",
            rolLimpio
        };
    }

}
