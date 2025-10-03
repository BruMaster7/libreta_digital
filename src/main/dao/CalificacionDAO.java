package main.dao;

import main.model.Calificacion;
import main.model.CalificacionDetalle;
import main.config.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAO {

    public List<CalificacionDetalle> obtenerCalificacionesPorEstudiante(int usuarioId) {
        List<CalificacionDetalle> lista = new ArrayList<>();

        String sql = """
            SELECT 
                c.nombre_curso,
                e.nombre_evaluacion,
                e.tipo_evaluacion,
                cal.nota
            FROM calificacion cal
            JOIN evaluacion e ON cal.evaluacion_id = e.evaluacion_id
            JOIN curso c ON e.curso_id = c.curso_id
            JOIN usuario u ON cal.usuario_id = u.usuario_id
            WHERE u.usuario_id = ? AND u.rol_id = 3
            ORDER BY c.nombre_curso, e.fecha_evaluacion;
        """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombreCurso = rs.getString("nombre_curso");
                String nombreEvaluacion = rs.getString("nombre_evaluacion");
                String tipoEvaluacion = rs.getString("tipo_evaluacion");
                float nota = rs.getFloat("nota");

                CalificacionDetalle detalle = new CalificacionDetalle(
                    nombreCurso, nombreEvaluacion, tipoEvaluacion, nota
                );

                lista.add(detalle);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener calificaciones: " + e.getMessage());
        }

        return lista;
    }

    public List<CalificacionDetalle> obtenerCalificacionesPorEstudianteYCurso(int usuarioId, int cursoId) {
        List<CalificacionDetalle> lista = new ArrayList<>();
        String sql = """
            SELECT c.nombre_curso, e.nombre_evaluacion, 
                   e.tipo_evaluacion, cal.nota
            FROM calificacion cal
            JOIN evaluacion e ON cal.evaluacion_id = e.evaluacion_id
            JOIN curso c ON e.curso_id = c.curso_id
            WHERE cal.usuario_id = ? AND c.curso_id = ?
            ORDER BY e.fecha_evaluacion
            """;
        
        try (Connection conexion = Conexion.conectar();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, cursoId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                CalificacionDetalle detalle = new CalificacionDetalle(
                    rs.getString("nombre_curso"),
                    rs.getString("nombre_evaluacion"),
                    rs.getString("tipo_evaluacion"),
                    rs.getFloat("nota")
                );
                lista.add(detalle);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener calificaciones: " + e.getMessage());
        }
        return lista;
    }

	public CalificacionDetalle obtenerCalificacionPorEstudianteYEvaluacion(int id, int id2) {
		CalificacionDetalle detalle = null;
		String sql = """
			SELECT c.nombre_curso, e.nombre_evaluacion, 
				   e.tipo_evaluacion, cal.nota
			FROM calificacion cal
			JOIN evaluacion e ON cal.evaluacion_id = e.evaluacion_id
			JOIN curso c ON e.curso_id = c.curso_id
			WHERE cal.usuario_id = ? AND e.evaluacion_id = ?
			""";
		
		try (Connection conexion = Conexion.conectar();
			 PreparedStatement stmt = conexion.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			stmt.setInt(2, id2);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				detalle = new CalificacionDetalle(
					rs.getString("nombre_curso"),
					rs.getString("nombre_evaluacion"),
					rs.getString("tipo_evaluacion"),
					rs.getFloat("nota")
				);
			}
		} catch (SQLException e) {
			System.err.println("Error al obtener calificación: " + e.getMessage());
		}
		return detalle;
	}

	public static boolean guardarCalificacion(int estudianteId, int evaluacionId, float nota) {
	    String sql = "INSERT INTO calificacion (usuario_id, evaluacion_id, nota) " +
	                 "VALUES (?, ?, ?) " +
	                 "ON CONFLICT (usuario_id, evaluacion_id) DO UPDATE SET nota = EXCLUDED.nota";

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, estudianteId);
	        stmt.setInt(2, evaluacionId);
	        stmt.setFloat(3, nota);

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}}



