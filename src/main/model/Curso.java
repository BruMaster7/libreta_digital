package main.model;

public class Curso {
	private int id;
	private String nombre_curso;
	private String descripcion;
	private boolean estado;
	
	public Curso(String nombre_curso, String descripcion, boolean estado) {
		super();
		this.nombre_curso = nombre_curso;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public String getNombre_curso() {
		return nombre_curso;
	}

	public void setNombre_curso(String nombre_curso) {
		this.nombre_curso = nombre_curso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
