package main.model;

public class Planificacion {
	private int planificacion_id;
	private int curso_id;
	private String hipervinculo;
	private  java.util.Date Fecha;
	private int autor_id;
	
	public Planificacion() {
		this.planificacion_id = planificacion_id;
		this.curso_id = curso_id;
		this.hipervinculo = hipervinculo;
		this.Fecha = Fecha;
		this.autor_id = autor_id;
	}
	public int getPlanificacion_id() {
		return planificacion_id;
	}
	public void setPlanificacion_id(int planificacion_id) {
		this.planificacion_id = planificacion_id;
	}
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	public String getHipervinculo() {
		return hipervinculo;
	}
	public void setHipervinculo(String hipervinculo) {
		this.hipervinculo = hipervinculo;
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
