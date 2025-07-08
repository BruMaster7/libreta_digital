package main;

import main.config.Conexion;
import main.dao.UsuarioDAO;
import main.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class App {

	public static void main(String[] args) {
		Connection conexion = Conexion.conectar();
        if (conexion != null) {
            System.out.println("La conexión está lista para usarse.");
        } else {
            System.out.println("Falló la conexión.");
        }
        
        List<Usuario> usuarios = UsuarioDAO.listarUsuarios();

        if (usuarios.isEmpty()) {
            System.out.println("No se encontraron usuarios en la base de datos.");
        } else {
            System.out.println("📋 Lista de usuarios:");
            for (Usuario u : usuarios) {
                System.out.println(" - " + u);
            }
        }

	}

}
