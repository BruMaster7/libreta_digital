package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.Curso;

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
}

