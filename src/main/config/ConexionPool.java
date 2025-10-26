package main.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConexionPool {
    private static final int MAX_CONNECTIONS = 10;
    private static final BlockingQueue<Connection> connectionPool = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
    private static volatile boolean initialized = false;
    
    // Clase Conexion original para compatibilidad hacia atrás
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
    
    // Nuevo método con pool de conexiones
    public static void initializePool() {
        if (initialized) return;
        
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
            
            for (int i = 0; i < MAX_CONNECTIONS; i++) {
                Connection conn = DriverManager.getConnection(url, usuario, contraseña);
                connectionPool.offer(conn);
            }
            
            initialized = true;
            System.out.println("✅ Pool de conexiones inicializado con " + MAX_CONNECTIONS + " conexiones.");
        } catch (SQLException e) {
            System.err.println("❌ Error al inicializar pool: " + e.getMessage());
        }
    }
    
    public static Connection getConnectionFromPool() throws InterruptedException {
        if (!initialized) {
            initializePool();
        }
        return connectionPool.take();
    }
    
    public static void releaseConnection(Connection conn) {
        if (conn != null && !connectionPool.offer(conn)) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void closeAllConnections() {
        while (!connectionPool.isEmpty()) {
            try {
                Connection conn = connectionPool.poll();
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        initialized = false;
    }
}

