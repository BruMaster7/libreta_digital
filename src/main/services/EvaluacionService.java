package main.services;

import java.util.List;

import main.dao.EvaluacionDAO;
import main.model.Evaluacion;

public class EvaluacionService {

	 public static boolean guardarEvaluacion(Evaluacion evaluacion) {
	        if (evaluacion == null || evaluacion.getNombre_evaluacion() == null || evaluacion.getNombre_evaluacion().isEmpty()) {
	            return false;
	        }

	        // Primero buscamos si ya existe
	        Evaluacion existente = EvaluacionDAO.obtenerEvaluacionPorNombreYCursos(evaluacion.getNombre_evaluacion(), evaluacion.getCurso_id());

	        if (existente != null) {
	            // Si existe, actualizamos
	            evaluacion.setId(existente.getId()); // asignamos el ID para que el DAO actualice
	            return EvaluacionDAO.modificarEvaluacion(evaluacion);
	        } else {
	            // Si no existe, agregamos
	            return EvaluacionDAO.agregarEvaluacion(evaluacion);
	        }
	    }

	public static List<Evaluacion> listarEvaluacionesPorCurso(int cursoId) {
		return EvaluacionDAO.listarEvaluacionesPorCurso(cursoId);
	}
	
	public static Evaluacion obtenerEvaluacionPorNombreYCursos(String nombre, int cursoId) {
		return EvaluacionDAO.obtenerEvaluacionPorNombreYCursos(nombre, cursoId);
	}

	public static boolean eliminarEvaluacion(int id) {
		return EvaluacionDAO.eliminarEvaluacion(id);
	}

}