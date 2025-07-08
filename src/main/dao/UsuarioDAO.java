package main.dao;

import main.config.Conexion;
import main.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT usuario_id, nombre, apellido, email, estado FROM usuario";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("estado")
                );
                lista.add(u);
            }

        } catch (Exception e) {
            System.err.println("❌ Error al listar usuarios: " + e.getMessage());
        }

        return lista;
    }
}
