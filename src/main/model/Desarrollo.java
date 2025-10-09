package main.model;

public class Desarrollo {
	private int desarrollo_id;
	private int curso_id;
	private String contenido;
	private  java.util.Date Fecha;
	private int autor_id;
	
	public Desarrollo() {
		this.desarrollo_id = desarrollo_id;
		this.curso_id = curso_id;
		this.contenido = contenido;
		this.Fecha = Fecha;
		this.autor_id = autor_id;
	}
	public int getDesarrollo_id() {
		return desarrollo_id;
	}
	public void setDesarrollo_id(int desarrollo_id) {
		this.desarrollo_id = desarrollo_id;
	}
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public java.util.Date getFecha() {
		return Fecha;
	}
	public void setFecha(java.util.Date fecha) {
		Fecha = fecha;
	}
	public int getAutor_id() {
		return autor_id;
	}
	public void setAutor_id(int autor_id) {
		this.autor_id = autor_id;
	}

}
