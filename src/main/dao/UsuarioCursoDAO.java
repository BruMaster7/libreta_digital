package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.config.Conexion;
import main.model.Curso;
import main.model.Usuario;

public class UsuarioCursoDAO {
    
    private Connection conn;

    public UsuarioCursoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Curso> obtenerCursosPorUsuario(int idUsuario) throws SQLException {
        List<Curso> cursos = new ArrayList<>();
        String sql = """
            SELECT c.curso_id, c.nombre_curso, c.descripcion
            FROM usuario_curso uc
            JOIN curso c ON uc.curso_id = c.curso_id
            WHERE uc.usuario_id = ?
        """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Curso c = new Curso();
                    c.setId(rs.getInt("curso_id"));
                    c.setNombre_curso(rs.getString("nombre_curso"));
                    c.setDescripcion(rs.getString("descripcion"));
                    cursos.add(c);
                }
            }
        }
        return cursos;
    }
    
    public List<Usuario> obtenerEstudiantesActivosPorCurso(int cursoId) {
        List<Usuario> estudiantes = new ArrayList<>();

        String sql = "SELECT u.* " +
                     "FROM usuario u " +
                     "INNER JOIN usuario_curso uc ON u.usuario_id = uc.usuario_id " +
                     "WHERE uc.curso_id = ? " +
                     "AND u.estado = TRUE " +
                     "AND u.rol_id NOT IN (1, 2)"; 
                     // 1 = Admin, 2 = Docente

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cursoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario u = new Usuario();
                    u.setUsuarioId(rs.getInt("usuario_id"));
                    u.setDocumento(rs.getString("documento"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setEmail(rs.getString("email"));
                    u.setEstado(rs.getBoolean("estado"));
                    u.setRol_id(rs.getInt("rol_id"));

                    estudiantes.add(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

}

