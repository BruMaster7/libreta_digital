package main.model;

import java.sql.Date;

public class Evaluacion {
	private int id;
	private int curso_id;
	private String nombre_evaluacion;
	private java.util.Date fecha_evaluacion;
	private String tipo_evaluacion;
	private String descripcion;
	
	public Evaluacion() {
		this.id = id;
		this.setCurso_id(curso_id);
		this.nombre_evaluacion = nombre_evaluacion;
		this.fecha_evaluacion = fecha_evaluacion;
		this.tipo_evaluacion = tipo_evaluacion;
		this.descripcion = descripcion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_evaluacion() {
		return nombre_evaluacion;
	}
	public void setNombre_evaluacion(String nombre_evaluacion) {
		this.nombre_evaluacion = nombre_evaluacion;
	}
	public java.util.Date getFecha_evaluacion() {
		return fecha_evaluacion;
	}
	public void setFecha_evaluacion(java.util.Date fecha) {
		this.fecha_evaluacion = fecha;
	}
	public String getTipo_evaluacion() {
		return tipo_evaluacion;
	}
	public void setTipo_evaluacion(String tipo_evaluacion) {
		this.tipo_evaluacion = tipo_evaluacion;
	}
	public int getCurso_id() {
		return curso_id;
	}
	public void setCurso_id(int curso_id) {
		this.curso_id = curso_id;
	}
	public String getDescripcion() {
	    return descripcion;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	
	
}
