package main.model;

public class Calificacion {
	private int id;
	private int evaluacion_id;
	private int usuario_id;
	private int nota;
	
	public Calificacion(int evaluacion_id, int usuario_id, int nota) {
		super();
		this.evaluacion_id = evaluacion_id;
		this.usuario_id = usuario_id;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}
	
	public int getEvaluacion_id() {
		return evaluacion_id;
	}
	public void setEvaluacion_id(int evaluacion_id) {
		this.evaluacion_id = evaluacion_id;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}

	
}
