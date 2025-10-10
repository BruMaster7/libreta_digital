package main;

import main.config.Conexion;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import main.dao.CursoDAO;
import main.dao.UsuarioDAO;
import main.model.Curso;
import main.model.Usuario;
import main.services.UsuarioService;
import main.views.VentanaLogin;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;

public class App {
	 private static final Scanner sc = new Scanner(System.in);

	    public static void main(String[] args)  {
	    	VentanaLogin login = new VentanaLogin();
	    	login.setVisible(true);
	    }

}
