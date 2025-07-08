package main.model;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String email;
    private String estado;

    public Usuario(int usuarioId, String nombre, String apellido, String email, String estado) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.estado = estado;
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

