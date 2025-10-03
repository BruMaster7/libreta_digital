package main.dao;

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

public class EvaluacionDAO {

	public static boolean agregarEvaluacion(Evaluacion evaluacion) {
	    String sql = "INSERT INTO evaluacion (curso_id, nombre_evaluacion, fecha_evaluacion, tipo_evaluacion, descripcion) " +
	                 "VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, evaluacion.getCurso_id());
	        stmt.setString(2, evaluacion.getNombre_evaluacion());

	        // OJO: convertir java.util.Date -> java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(evaluacion.getFecha_evaluacion().getTime());
	        stmt.setDate(3, fechaSQL);

	        stmt.setString(4, evaluacion.getTipo_evaluacion());
	        stmt.setString(5, evaluacion.getDescripcion());

	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static boolean modificarEvaluacion(Evaluacion evaluacion) {
			    String sql = "UPDATE evaluacion SET curso_id = ?, nombre_evaluacion = ?, fecha_evaluacion = ?, tipo_evaluacion = ?, descripcion = ? " +
	                 "WHERE evaluacion_id = ?";
	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, evaluacion.getCurso_id());
	        stmt.setString(2, evaluacion.getNombre_evaluacion());

	        // OJO: convertir java.util.Date -> java.sql.Date
	        java.sql.Date fechaSQL = new java.sql.Date(evaluacion.getFecha_evaluacion().getTime());
	        stmt.setDate(3, fechaSQL);

	        stmt.setString(4, evaluacion.getTipo_evaluacion());
	        stmt.setString(5, evaluacion.getDescripcion());
	        stmt.setInt(6, evaluacion.getId());

	        int filasAfectadas = stmt.executeUpdate();
	        System.out.println("UPDATE ejecutado, filas afectadas=" + filasAfectadas);

	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;}
	    }

	public static Evaluacion obtenerEvaluacionPorNombreYCursos(String nombre_evaluacion, int curso_id){
        String sql = "SELECT * FROM evaluacion WHERE nombre_evaluacion = ? AND curso_id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre_evaluacion);
            stmt.setInt(2, curso_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Evaluacion ev = new Evaluacion();
                ev.setId(rs.getInt("evaluacion_id"));
                ev.setCurso_id(rs.getInt("curso_id"));
                ev.setNombre_evaluacion(rs.getString("nombre_evaluacion"));
                ev.setFecha_evaluacion(rs.getDate("fecha_evaluacion"));
                ev.setTipo_evaluacion(rs.getString("tipo_evaluacion"));
                ev.setDescripcion(rs.getString("descripcion"));
                return ev;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // no existe
    

}
	
	public static List<Evaluacion> listarEvaluacionesPorCurso(int cursoId) {
	    List<Evaluacion> lista = new ArrayList<>();
	    String sql = "SELECT * FROM evaluacion WHERE curso_id = ? ORDER BY fecha_evaluacion";

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, cursoId);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Evaluacion ev = new Evaluacion();
	            ev.setId(rs.getInt("evaluacion_id"));
	            ev.setCurso_id(rs.getInt("curso_id"));
	            ev.setNombre_evaluacion(rs.getString("nombre_evaluacion"));
	            ev.setFecha_evaluacion(rs.getDate("fecha_evaluacion"));
	            ev.setTipo_evaluacion(rs.getString("tipo_evaluacion"));
	            ev.setDescripcion(rs.getString("descripcion"));
	            lista.add(ev);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}

	public static boolean eliminarEvaluacion(int id) {
			    String sql = "DELETE FROM evaluacion WHERE evaluacion_id = ?";

	    try (Connection conn = Conexion.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setInt(1, id);
	        int filasAfectadas = stmt.executeUpdate();
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}
