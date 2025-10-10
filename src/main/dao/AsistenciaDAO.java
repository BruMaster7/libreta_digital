package main.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import main.config.Conexion;
import main.model.Asistencia;

public class AsistenciaDAO {
    private Connection conn;

    public AsistenciaDAO(Connection conn) {
        this.conn = conn;
    }

    // Guardar asistencia (insertar una fila por estudiante)
    public void guardarAsistencia(Asistencia asistencia) throws SQLException {
        String sql = "INSERT INTO asistencia (usuario_id, curso_id, fecha_clase, estado_asistencia) " +
                     "VALUES (?, ?, ?, ?) " +
                     "ON CONFLICT (usuario_id, curso_id, fecha_clase) " +
                     "DO UPDATE SET estado_asistencia = EXCLUDED.estado_asistencia";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, asistencia.getUsuarioId());
            ps.setInt(2, asistencia.getCursoId());
            ps.setDate(3, new java.sql.Date(asistencia.getFechaClase().getTime()));
            ps.setString(4, asistencia.getEstadoAsistencia());
            ps.executeUpdate();
        }
    }

    // Obtener asistencia de un curso y fecha
    public List<Asistencia> obtenerAsistenciaPorCursoYFecha(int cursoId, Date fechaClase) throws SQLException {
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM asistencia WHERE curso_id = ? AND fecha_clase = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cursoId);
            ps.setDate(2, new java.sql.Date(fechaClase.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asistencia a = new Asistencia();
                    a.setAsistenciaId(rs.getInt("asistencia_id"));
                    a.setUsuarioId(rs.getInt("usuario_id"));
                    a.setCursoId(rs.getInt("curso_id"));
                    a.setFechaClase(rs.getDate("fecha_clase"));
                    a.setEstadoAsistencia(rs.getString("estado_asistencia"));
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public static int obtenerFaltasPorEstudianteYCurso(int usuarioId, String nombreCurso) {
        String sql = """
            SELECT COUNT(*) AS faltas
            FROM asistencia a
            JOIN curso c ON a.curso_id = c.curso_id
            WHERE a.usuario_id = ? AND c.nombre_curso = ? AND a.estado_asistencia = 'Ausente'
        """;
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setString(2, nombreCurso);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("faltas");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

