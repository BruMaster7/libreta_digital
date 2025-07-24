package main.model;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String email;
    private String estado;
    private int rol_id;

    public Usuario(int usuarioId, String nombre, String apellido, String email, String estado, int rol_id) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.estado = estado;
        this.rol_id = rol_id;
        
    }
    
    public int getRolId() {
    	return rol_id;
    }
    
    public int getId() {
    	return usuarioId;
    }
    
    public int getUsuarioId() {
        return usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return usuarioId + ": " + nombre + " " + apellido + " (" + email + ") - " + estado;
    }
}

