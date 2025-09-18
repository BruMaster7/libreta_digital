package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.config.Conexion;
import main.model.Curso;
import main.model.Rama;

public class RamaCursoDAO {

	public static Object obtenerNombreRamaPorId(int rama_id) {
		String sql = "SELECT nombre_rama FROM rama WHERE rama_id = ?";
		try (Connection conn = Conexion.conectar();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, rama_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getString("nombre_rama");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Rama> listarCursosPorRama(int ramaid) {
		List<Rama> lista = new ArrayList<>();
		String sql = "SELECT r.rama_id, r.nombre_rama, c.curso_id, c.nombre_curso, c.estado " +
				"FROM rama r " +
				"LEFT JOIN curso c ON r.rama_id = c.rama_id " +
				"WHERE r.rama_id = ?";

		try (Connection conn = Conexion.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, ramaid);
			ResultSet rs = stmt.executeQuery();

			Rama currentRama = null;

			while (rs.next()) {
				if (currentRama == null || currentRama.getId() != rs.getInt("rama_id")) {
					if (currentRama != null) {
						lista.add(currentRama);
					}
					currentRama = new Rama();
					currentRama.setId(rs.getInt("rama_id"));
					currentRama.setNombre_rama(rs.getString("nombre_rama"));
					currentRama.setCursos(new ArrayList<>());
				}

				int cursoId = rs.getInt("curso_id");
				if (cursoId != 0) { // Asegura que hay un curso asociado
					Curso curso = new Curso();
					curso.setId(cursoId);
					curso.setNombre_curso(rs.getString("nombre_curso"));
					curso.setEstado(rs.getBoolean("estado"));
					curso.setRama_id(ramaid);

					currentRama.getCursos().add(curso);
				}
			}

			if (currentRama != null) {
				lista.add(currentRama);
			}

		} catch (Exception e) {
			System.err.println("❌ Error al listar cursos por rama: " + e.getMessage());
		}

		return lista;
	}

}
