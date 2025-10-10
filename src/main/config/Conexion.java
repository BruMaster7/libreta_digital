package main.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    public static Connection conectar() {
        try {
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
            System.out.println("✅ Conectado a PostgreSQL correctamente.");
            

            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
            return null;
        }

    }
    
}


