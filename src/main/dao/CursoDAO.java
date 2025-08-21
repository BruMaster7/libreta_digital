package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.config.Conexion;
import main.model.Curso;

public class CursoDAO {

    // --- LISTAR TODOS LOS CURSOS ---
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

    // --- BUSCAR CURSO POR ID ---
    public static Curso buscarPorId(int id) {
        String sql = "SELECT curso_id, nombre_curso, descripcion, estado FROM curso WHERE curso_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getString("descripcion"),
                        rs.getBoolean("estado")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar curso: " + e.getMessage());
        }
        return null;
    }

    // --- AGREGAR CURSO ---
    public static boolean agregarCurso(Curso curso) {
        String sql = "INSERT INTO curso (nombre_curso, descripcion, estado) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre_curso());
            stmt.setString(2, curso.getDescripcion());
            stmt.setBoolean(3, curso.isEstado());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al agregar curso: " + e.getMessage());
            return false;
        }
    }

    // --- MODIFICAR CURSO ---
    public static boolean modificarCurso(Curso curso) {
        String sql = "UPDATE curso SET nombre_curso = ?, descripcion = ?, estado = ? WHERE curso_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre_curso());
            stmt.setString(2, curso.getDescripcion());
            stmt.setBoolean(3, curso.isEstado());
            stmt.setInt(4, curso.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al modificar curso: " + e.getMessage());
            return false;
        }
    }

    // --- ELIMINAR CURSO ---
    public static boolean eliminarCurso(int id) {
        String sql = "DELETE FROM curso WHERE curso_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar curso: " + e.getMessage());
            return false;
        }
    }
    
    
}
