package main.model;

import java.util.List;

public class Rama {
	private int rama_id;
	private String nombre_rama;
	private List <Curso> cursos;
	
	
	public Rama() {}
	public Rama(int rama_id, String nombre_rama, List<Curso> cursos) {
		super();
		this.rama_id = rama_id;
		this.nombre_rama = nombre_rama;
		this.cursos = cursos;
	}
	public int getId() {
		return rama_id;
	}
	public void setId(int id) {
		this.rama_id = id;
	}
	public String getNombre_rama() {
		return nombre_rama;
	}
	public void setNombre_rama(String nombre_rama) {
		this.nombre_rama = nombre_rama;
	}
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
}
