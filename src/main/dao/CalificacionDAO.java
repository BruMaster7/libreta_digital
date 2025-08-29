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
	
}


