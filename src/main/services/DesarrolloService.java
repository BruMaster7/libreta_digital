package main.services;

import java.util.List;

import main.dao.DesarrolloDAO;
import main.model.Desarrollo;
import main.model.Usuario;

public class DesarrolloService {

	public static Boolean guardarDesarrollo(Desarrollo desarrollo) {
		Desarrollo existente = DesarrolloDAO.obtenerDesarrolloPorFechaYCurso(desarrollo.getFecha().toString(), desarrollo.getCurso_id());
		if (existente != null) {
			desarrollo.setDesarrollo_id(existente.getDesarrollo_id());
		    return DesarrolloDAO.modificarDesarrollo(desarrollo);
		} else {
		return DesarrolloDAO.guardarDesarrollo(desarrollo);
	}}

	public static List<Desarrollo> obtenerDesarrollosPorCurso(int id) {
		return DesarrolloDAO.obtenerDesarrollosPorCurso(id);
	}

	public static Boolean eliminarDesarrolloPorFechaYCurso(String desarrolloSeleccionado, int id) {
		return DesarrolloDAO.eliminarDesarrolloPorFechaYCurso(desarrolloSeleccionado, id);
	}

	public static Desarrollo obtenerDesarrolloPorFechaYCurso(String desarrolloSeleccionado, int id) {
		return DesarrolloDAO.obtenerDesarrolloPorFechaYCurso(desarrolloSeleccionado, id);
	}

}
