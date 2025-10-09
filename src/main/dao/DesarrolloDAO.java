package main.dao;

import java.util.List;

import main.model.Desarrollo;
import main.model.Usuario;
import main.config.Conexion;
import main.model.CalificacionDetalle;
import main.model.Evaluacion;
import main.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DesarrolloDAO {

	public static Boolean guardarDesarrollo(Desarrollo desarrollo) {
		String sql = "INSERT INTO desarrollo (curso_id, contenido,fecha, autor_id) VALUES (?, ?, ?, ?)";
		try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, desarrollo.getCurso_id());
	        stmt.setString(2, desarrollo.getContenido());

	        // OJO: convertir java.util.Date -> java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(desarrollo.getFecha().getTime());
	        stmt.setDate(3, fechaSQL);

	        stmt.setInt(4, desarrollo.getAutor_id());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static List<Desarrollo> obtenerDesarrollosPorCurso(int cursoId) {
	    List<Desarrollo> desarrollos = new ArrayList<>();
	    String sql = "SELECT * FROM desarrollo WHERE curso_id = ? ORDER BY fecha DESC"; 

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, cursoId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Desarrollo desarrollo = new Desarrollo();
	            desarrollo.setDesarrollo_id(rs.getInt("desarrollo_id")); 
	            desarrollo.setCurso_id(rs.getInt("curso_id"));
	            desarrollo.setContenido(rs.getString("contenido"));
	            desarrollo.setFecha(rs.getDate("fecha"));
	            desarrollo.setAutor_id(rs.getInt("autor_id"));

	            desarrollos.add(desarrollo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Error al obtener los desarrollos: " + e.getMessage());
	    }

	    return desarrollos;
	}

	public static Desarrollo obtenerDesarrolloPorFechaYCurso(String desarrolloSeleccionado, int id) {
		String sql = "SELECT * FROM desarrollo WHERE fecha = ? AND curso_id = ?";
		try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        java.sql.Date fechaSQL = java.sql.Date.valueOf(desarrolloSeleccionado);

	        stmt.setDate(1, fechaSQL);
	        stmt.setInt(2, id);

	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            Desarrollo desarrollo = new Desarrollo();
	            desarrollo.setDesarrollo_id(rs.getInt("desarrollo_id")); 
	            desarrollo.setCurso_id(rs.getInt("curso_id"));
	            desarrollo.setContenido(rs.getString("contenido"));
	            desarrollo.setFecha(rs.getDate("fecha"));
	            desarrollo.setAutor_id(rs.getInt("autor_id"));

	            return desarrollo;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Error al obtener el desarrollo: " + e.getMessage());
	    }

	    return null; // no existe
	}

	public static Boolean modificarDesarrollo(Desarrollo desarrollo) {
	    String sql = "UPDATE desarrollo SET contenido = ?, fecha = ?, autor_id = ?, curso_id = ? WHERE desarrollo_id = ?";
	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, desarrollo.getContenido());
	        stmt.setDate(2, new java.sql.Date(desarrollo.getFecha().getTime()));
	        stmt.setInt(3, desarrollo.getAutor_id());
	        stmt.setInt(4, desarrollo.getCurso_id());
	        stmt.setInt(5, desarrollo.getDesarrollo_id());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static Boolean eliminarDesarrolloPorFechaYCurso(String desarrolloSeleccionado, int id) {
		String sql = "DELETE FROM desarrollo WHERE fecha = ? AND curso_id = ?";
	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        java.sql.Date fechaSQL = java.sql.Date.valueOf(desarrolloSeleccionado);

	        stmt.setDate(1, fechaSQL);
	        stmt.setInt(2, id);

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
