package main.model;

public class CalificacionDetalle {
	    private String nombreCurso;
	    private String nombreEvaluacion;
	    private String tipoEvaluacion;
	    private float nota;
		
		public CalificacionDetalle(String nombreCurso, String nombreEvaluacion, String tipoEvaluacion, float nota) {
			super();
			this.nombreCurso = nombreCurso;
			this.nombreEvaluacion = nombreEvaluacion;
			this.tipoEvaluacion = tipoEvaluacion;
			this.nota = nota;
		}
		public String getNombreCurso() {
			return nombreCurso;
		}

		public void setNombreCurso(String nombreCurso) {
			this.nombreCurso = nombreCurso;
		}
		public String getNombreEvaluacion() {
			return nombreEvaluacion;
		}
		public void setNombreEvaluacion(String nombreEvaluacion) {
			this.nombreEvaluacion = nombreEvaluacion;
		}
		public String getTipoEvaluacion() {
			return tipoEvaluacion;
		}
		public void setTipoEvaluacion(String tipoEvaluacion) {
			this.tipoEvaluacion = tipoEvaluacion;
		}
		public float getNota() {
			return nota;
		}
		public void setNota(float nota) {
			this.nota = nota;
		}

	}

