package main.model;

public class Curso {
	private int id;
	@Override
	public String toString() {
		return nombre_curso;
	}

	private String nombre_curso;
	private boolean estado;
	private int rama_id;
	
	
	public Curso() {}
	
	public Curso(int id, String nombre_curso, boolean estado, int rama_id) {
		super();
		this.setId(id);
		this.nombre_curso = nombre_curso;
		this.estado = estado;
		this.rama_id = rama_id;
	}

	public String getNombre_curso() {
		return nombre_curso;
	}

	public void setNombre_curso(String nombre_curso) {
		this.nombre_curso = nombre_curso;
	}


	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getRama_id() {
		return rama_id;
	}
	public void setRama_id(int rama_id) {
		this.rama_id = rama_id;
	}
	
	
	
}
