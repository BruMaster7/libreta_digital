package main.services;

import main.dao.PlanificacionDAO;
import main.model.Planificacion;

public class PlanificacionService {

	public static Boolean guardarPlanificacionAnual(Planificacion plani) {
		Planificacion existente = PlanificacionDAO.obtenerPlanificacionPorCursoYdocente(plani.getCurso_id(), plani.getAutor_id());
		if (existente != null) {
			// Si existe, actualizamos
			plani.setPlanificacion_id(existente.getPlanificacion_id()); // asignamos el ID para que el DAO actualice
			return PlanificacionDAO.modificarPlanificacionAnual(plani);
		}
		return PlanificacionDAO.guardarPlanificacionAnual(plani);
	}
}
