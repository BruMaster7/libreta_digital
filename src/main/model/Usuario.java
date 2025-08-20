package main.model;

import java.sql.Date;

public class Usuario {
    private int usuarioId;
    private String documento;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String email;
    private String contrasena;
    private boolean estado;
    private int rol_id;

    public Usuario(int usuarioId, String documento, String nombre, String apellido,Date fechaNacimiento, String email, boolean estado, int rol_id) {
        this.usuarioId = usuarioId;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.estado = estado;
        this.rol_id = rol_id;
        
    }
    
    public Usuario() {
		
	}

	public int getRolId() {
    	return rol_id;
    }
    
    public int getId() {
    	return usuarioId;
    }
    
    public String getDocumento() {
    	return documento;
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

    public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
    public String getEmail() {
        return email;
    }

    public boolean getEstado() {
        return estado;
    }
    
    public String getContrasena() {
    	return contrasena;
    }

    @Override
    public String toString() {
        return usuarioId + ": " + nombre + " " + apellido + " (" + email + ") - " + estado;
    }

	public int getRol_id() {
		return rol_id;
	}

	public void setRol_id(int rolid) {
		this.rol_id = rolid;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}




}

