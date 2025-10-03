package main.services;

import main.dao.CalificacionDAO;

public class CalificacionService {

	public static boolean guardarCalificacion(int id, int id2, float nota) {
		//enviar a CalificacionDAO
		return CalificacionDAO.guardarCalificacion(id, id2, nota);
		
	}
	

}
