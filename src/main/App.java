package main;

import main.config.Conexion;
import main.dao.UsuarioDAO;
import main.model.Usuario;
import main.services.UsuarioService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class App {
	 private static final Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) {
	        int opcion;

	        do {
	            System.out.println("\n--- MENÚ USUARIOS ---");
	            System.out.println("1. Agregar usuario");
	            System.out.println("2. Modificar usuario");
	            System.out.println("3. Eliminar usuario");
	            System.out.println("4. Buscar usuario por documento");
	            System.out.println("5. Salir");
	            System.out.print("Selecciona una opción: ");
	            opcion = sc.nextInt();
	            sc.nextLine(); // limpiar buffer

	            switch (opcion) {
	                case 1 -> agregarUsuario();
	                case 2 -> modificarUsuario();
	                case 3 -> eliminarUsuario();
	                case 4 -> buscarPorDocumento();
	                case 5 -> System.out.println("👋 Fin del programa.");
	                default -> System.out.println("❌ Opción no válida.");
	            }

	        } while (opcion != 5);
	    }

	    private static void agregarUsuario() {
	        System.out.println("\n--- Agregar Usuario ---");

	        Usuario usuario = leerDatosUsuario(false); // sin ID
	        boolean exito = UsuarioService.registrarUsuario(usuario);

	        if (exito) {
	            System.out.println("✅ Usuario registrado correctamente.");
	        } else {
	            System.out.println("❌ Error al registrar usuario.");
	        }
	    }

	    private static void modificarUsuario() {
	        System.out.println("\n--- Modificar Usuario ---");

	        System.out.print("ID del usuario a modificar: ");
	        int id = sc.nextInt(); sc.nextLine();

	        Usuario usuario = leerDatosUsuario(true); // con ID
	        usuario.setUsuarioId(id);

	        boolean exito = UsuarioService.editarUsuario(usuario);

	        if (exito) {
	            System.out.println("✅ Usuario modificado correctamente.");
	        } else {
	            System.out.println("❌ Error al modificar usuario.");
	        }
	    }

	    private static void eliminarUsuario() {
	        System.out.println("\n--- Eliminar Usuario ---");

	        System.out.print("ID del usuario a eliminar: ");
	        int id = sc.nextInt(); sc.nextLine();

	        boolean exito = UsuarioService.eliminarUsuario(id);

	        if (exito) {
	            System.out.println("✅ Usuario eliminado correctamente.");
	        } else {
	            System.out.println("❌ No se pudo eliminar el usuario.");
	        }
	    }

	    private static void buscarPorDocumento() {
	        System.out.println("\n--- Buscar Usuario por Documento ---");

	        System.out.print("Documento: ");
	        String documento = sc.nextLine().trim();

	        Usuario usuario = UsuarioDAO.buscarPorDocumento(documento);

	        if (usuario != null) {
	            System.out.println("🧾 Usuario encontrado:");
	            System.out.println("ID: " + usuario.getUsuarioId());
	            System.out.println("Nombre: " + usuario.getNombre() + " " + usuario.getApellido());
	            System.out.println("Email: " + usuario.getEmail());
	            System.out.println("Estado: " + (usuario.getEstado() ? "Activo" : "Inactivo"));
	            System.out.println("Rol ID: " + usuario.getRol_id());
	        } else {
	            System.out.println("❌ No se encontró un usuario con ese documento.");
	        }
	    }

	    private static Usuario leerDatosUsuario(boolean incluirId) {
	        Usuario usuario = new Usuario();

	        System.out.print("Documento: ");
	        usuario.setDocumento(sc.nextLine().trim());

	        System.out.print("Nombre: ");
	        usuario.setNombre(sc.nextLine().trim());

	        System.out.print("Apellido: ");
	        usuario.setApellido(sc.nextLine().trim());

	        System.out.print("Email: ");
	        usuario.setEmail(sc.nextLine().trim());
	        
	        System.out.print("Contrasena: ");
	        usuario.setContrasena(sc.nextLine().trim());

	        System.out.print("Estado (true/false): ");
	        usuario.setEstado(Boolean.parseBoolean(sc.nextLine().trim()));

	        System.out.print("Rol ID (entero): ");
	        usuario.setRol_id(Integer.parseInt(sc.nextLine().trim()));

	        return usuario;
	    }

}
