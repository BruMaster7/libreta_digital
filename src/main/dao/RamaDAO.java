package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.config.Conexion;
import main.model.Rama;

public class RamaDAO {

    public static Rama BuscarRamaPorNombre(String nombre) {
        String sql = "SELECT * FROM rama WHERE nombre_rama = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Rama miRama = new Rama();
                miRama.setId(rs.getInt("rama_id"));
                miRama.setNombre_rama(rs.getString("nombre_rama"));
                return miRama;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	public static List<Rama> listarRamas() {
		List<Rama> lista = new ArrayList<>();
		String sql = "SELECT rama_id, nombre_rama FROM rama";

		try (Connection conn = Conexion.conectar();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Rama r = new Rama(
						rs.getInt("rama_id"),
						rs.getString("nombre_rama"),
						null // Cursos se pueden cargar por separado si es necesario
				);
				lista.add(r);
			}

		} catch (Exception e) {
			System.err.println("❌ Error al listar ramas: " + e.getMessage());
		}

		return lista;
	}	}

