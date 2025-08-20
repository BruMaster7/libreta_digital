package main.dao;

import main.config.Conexion;
import main.model.CalificacionDetalle;
import main.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

	public static Usuario login(String email, String contrasenaPlano) {
	    Usuario usuario = null;

	    String sql = "SELECT usuario_id, documento, nombre, apellido, fecha_nacimiento, email, estado, rol_id FROM usuario " +
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
	                rs.getDate("fecha_nacimiento"),
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
        String sql = "SELECT usuario_id, documento, nombre, apellido, fecha_nacimiento, email, estado, rol_id FROM usuario";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("documento"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_nacimiento"),
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
	
	public static boolean agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (documento, nombre, apellido, fecha_nacimiento, email, estado, contrasena_hash, rol_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getDocumento());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setDate(4, usuario.getFechaNacimiento());
            stmt.setString(5, usuario.getEmail());
            stmt.setBoolean(6, usuario.getEstado());
            stmt.setString(7,usuario.getContrasena());
            stmt.setInt(8, usuario.getRolId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET documento = ?, nombre = ?, apellido = ?, fecha_nacimiento = ?, email = ?, estado = ?, rol_id = ? " +
                     "WHERE usuario_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getDocumento());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setDate(4, usuario.getFechaNacimiento());
            stmt.setString(5, usuario.getEmail());
            stmt.setBoolean(6, usuario.getEstado());
            stmt.setInt(7, usuario.getRolId());
            stmt.setInt(8, usuario.getUsuarioId());

            int filasAfectadas = stmt.executeUpdate();
            System.out.println("UPDATE ejecutado, filas afectadas=" + filasAfectadas);

            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarUsuario(int usuarioId) {
        String sql = "UPDATE usuario SET estado = false WHERE usuario_id = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    
    public static Usuario buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM usuario WHERE documento = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuarioId(rs.getInt("usuario_id"));
                usuario.setDocumento(rs.getString("documento"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getBoolean("estado"));
                usuario.setRol_id(rs.getInt("rol_id"));
                usuario.setContrasena(rs.getString("contrasena_hash"));
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // No se encontró usuario con ese documento
    }

}
