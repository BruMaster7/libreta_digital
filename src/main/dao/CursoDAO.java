package main.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.config.Conexion;
import main.model.Curso;

public class CursoDAO {
	public static List<Curso> listarCursos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT curso_id, nombre_curso, descripcion, estado FROM curso";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
                lista.add(c);
            }

        } catch (Exception e) {
            System.err.println("❌ Error al listar cursos: " + e.getMessage());
        }

        return lista;
    }
}
