package main.dao;

import main.config.Conexion;
import main.model.Visado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class VisadoDAO {

    // Inserta un nuevo Visado en la tabla "visado"
    public boolean guardarVisado(Visado v) throws SQLException {
        String sql = "INSERT INTO visado (usuario_id_administrador, fecha_visado,id_curso, planificacion, parciales, desarrollo, promedios) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getUsuarioIdAdministrador());
            // si no se pasó fecha, usar la fecha actual
            Timestamp fecha = v.getFechaVisado() != null ? v.getFechaVisado() : new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(2, fecha);
            ps.setInt(3, v.getCursoId());
            ps.setBoolean(4, v.isPlanificacion());
            ps.setBoolean(5, v.isParciales());
            ps.setBoolean(6, v.isDesarrollo());
            ps.setBoolean(7, v.isPromedios());

            int filas = ps.executeUpdate();
            return filas > 0;
        }
    }

    // Obtiene un Visado por cursoId
    public Visado obtenerVisadoPorCurso(int cursoId) throws SQLException {
        String sql = "SELECT * FROM visado WHERE id_curso = ? ORDER BY fecha_visado DESC LIMIT 1";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cursoId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Visado visado = new Visado();
                visado.setVisadoId(rs.getInt("visado_id"));
                visado.setUsuarioIdAdministrador(rs.getInt("usuario_id_administrador"));
                visado.setFechaVisado(rs.getTimestamp("fecha_visado"));
                visado.setCursoId(rs.getInt("id_curso"));
                visado.setPlanificacion(rs.getBoolean("planificacion"));
                visado.setParciales(rs.getBoolean("parciales"));
                visado.setDesarrollo(rs.getBoolean("desarrollo"));
                visado.setPromedios(rs.getBoolean("promedios"));
                return visado;
            }
        }
        return null;
    }
}
