package main;

import main.config.Conexion;
import java.sql.Connection;

public class App {

	public static void main(String[] args) {
		Connection conexion = Conexion.conectar();
        if (conexion != null) {
            System.out.println("La conexión está lista para usarse.");
        } else {
            System.out.println("Falló la conexión.");
        }

	}

}
