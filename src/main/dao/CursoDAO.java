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
        String sql = "SELECT curso_id, nombre_curso, estado, rama_id FROM curso";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getBoolean("estado"),
                        rs.getInt("rama_id")
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
        String sql = "SELECT curso_id, nombre_curso, estado, rama_id FROM curso WHERE curso_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Curso(
                        rs.getInt("curso_id"),
                        rs.getString("nombre_curso"),
                        rs.getBoolean("estado"),
                        rs.getInt("rama_id")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar curso: " + e.getMessage());
        }
        return null;
    }

    // --- AGREGAR CURSO ---
    public static boolean agregarCurso(Curso curso) {
    			String sql = "INSERT INTO curso (nombre_curso, estado, rama_id) VALUES (?, ?, ?)";
		try (Connection conn = Conexion.conectar();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, curso.getNombre_curso());
			stmt.setBoolean(2, curso.isEstado());
			stmt.setInt(3, curso.getRama_id());

			int filas = stmt.executeUpdate();
			return filas > 0;

		} catch (SQLException e) {
			System.err.println("❌ Error al agregar curso: " + e.getMessage());
			return false;
		}
    }

    // --- MODIFICAR CURSO ---
    public static boolean modificarCurso(Curso curso) {
        String sql = "UPDATE curso SET nombre_curso = ?, estado = ? WHERE curso_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNombre_curso());
            stmt.setBoolean(2, curso.isEstado());
            stmt.setInt(3, curso.getId());

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

    public static Curso buscarPorNombre(String nombre) {
		String sql = "SELECT * FROM curso WHERE nombre_curso = ?";
		try (Connection conn = Conexion.conectar();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Curso miCurso = new Curso();
				miCurso.setId(rs.getInt("curso_id"));
				miCurso.setNombre_curso(rs.getString("nombre_curso"));
				miCurso.setEstado(rs.getBoolean("estado"));
				return miCurso;
			}

		} catch (SQLException e) {
			System.err.println("❌ Error al buscar curso: " + e.getMessage());
		} return null;
	}

    public static boolean DarDeBajaCurso(String nombre_curso) {
        String sql = "UPDATE Curso SET estado = false WHERE nombre_curso = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre_curso);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
