package main.dao;

import main.config.Conexion;
import main.model.CalificacionDetalle;
import main.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	public static Usuario login(String email, String contrasenaPlano) {
	    Usuario usuario = null;

	    String sql = "SELECT usuario_id, documento, nombre, apellido, email, estado, rol_id FROM usuario " +
	                 "WHERE email = ? AND contrasena_hash = ?";

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, email);
	        stmt.setString(2, contrasenaPlano); // Comparación directa

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            usuario = new Usuario(
	                rs.getInt("usuario_id"),
                    rs.getString("documento"),
	                rs.getString("nombre"),
	                rs.getString("apellido"),
	                rs.getString("email"),
	                rs.getBoolean("estado"),
                    rs.getInt("rol_id")
	            );
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return usuario;
	}
	
	
	public static List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT usuario_id, documento, nombre, apellido, email, estado, rol_id FROM usuario";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getBoolean("estado"),
                        rs.getInt("rol_id")
                );
                lista.add(u);
            }

        } catch (Exception e) {
            System.err.println("❌ Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }
	
	public static void verBoletinEstudiante(int usuarioId) {
	    CalificacionDAO dao = new CalificacionDAO();
	    List<CalificacionDetalle> calificaciones = dao.obtenerCalificacionesPorEstudiante(usuarioId);

	    if (calificaciones.isEmpty()) {
	        System.out.println("No hay calificaciones registradas.");
	        return;
	    }

	    System.out.println("📋 Boletín del Estudiante:");
	    for (CalificacionDetalle c : calificaciones) {
	        System.out.println("Curso: " + c.getNombreCurso());
	        System.out.println("Evaluación: " + c.getNombreEvaluacion());
	        System.out.println("Tipo: " + c.getTipoEvaluacion());
	        System.out.println("Nota: " + c.getNota());
	        System.out.println("-----------");
	    }
	}

}
