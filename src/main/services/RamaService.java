package main.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.dao.RamaDAO;
import main.model.Rama;
public class RamaService {
	public static int obtenerRamaIdPorNombre(String rama) {
		try {
			return RamaDAO.BuscarRamaPorNombre(rama).getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Indica que no se encontró la rama
		}
	}

	public static List<Rama> listarRamas() {
		try {
		return RamaDAO.listarRamas();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
