package main.dao;

import main.model.Planificacion;
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
public class PlanificacionDAO {

	public static Boolean guardarPlanificacionAnual(Planificacion plani) {
		String sql = "INSERT INTO planificacion (curso_id, hipervinculo, fecha_subida, autor_id) VALUES (?, ?, ?, ?)";
			 try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, plani.getCurso_id());
	        stmt.setString(2, plani.getHipervinculo());

	        // OJO: convertir java.util.Date -> java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(plani.getFecha().getTime());
	        stmt.setDate(3, fechaSQL);

	        stmt.setInt(4, plani.getAutor_id());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
		
	}


	public static Planificacion obtenerPlanificacionPorCursoYdocente(int curso_id, int autor_id) {
		String sql = "SELECT * FROM planificacion WHERE curso_id = ? AND autor_id = ?";
	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, curso_id);
	        stmt.setInt(2, autor_id);

	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                Planificacion plani = new Planificacion();
	                plani.setPlanificacion_id(rs.getInt("planificacion_id"));
	                plani.setCurso_id(rs.getInt("curso_id"));
	                plani.setHipervinculo(rs.getString("hipervinculo"));
	                plani.setFecha(rs.getDate("fecha_subida"));
	                plani.setAutor_id(rs.getInt("autor_id"));
	                return plani;
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null;
	}


	public static Boolean modificarPlanificacionAnual(Planificacion plani) {
		String sql = "UPDATE planificacion SET hipervinculo = ?, fecha_subida = ? WHERE planificacion_id = ?";
	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, plani.getHipervinculo());

	        // OJO: convertir java.util.Date -> java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(plani.getFecha().getTime());
	        stmt.setDate(2, fechaSQL);

	        stmt.setInt(3, plani.getPlanificacion_id());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
