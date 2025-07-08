package main.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection conexion;

    public static Connection conectar() {
        if (conexion != null) return conexion;

        Dotenv dotenv = Dotenv.configure()
                .directory("src/resources")
                .ignoreIfMissing()
                .load();

        String url = String.format("jdbc:postgresql://%s:%s/%s",
                dotenv.get("DB_HOST"),
                dotenv.get("DB_PORT"),
                dotenv.get("DB_NAME"));
        String usuario = dotenv.get("DB_USER");
        String contraseña = dotenv.get("DB_PASSWORD");

        try {
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conectado a PostgreSQL correctamente.");
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }

        return conexion;
    }
}

