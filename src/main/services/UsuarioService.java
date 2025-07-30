package main.services;

import main.dao.UsuarioDAO;
import main.model.Usuario;

public class UsuarioService {

    public static boolean registrarUsuario(Usuario usuario) {
        // Validaciones, por ejemplo:
        if (!usuario.getEmail().contains("@")) {
            System.err.println("❌ Email inválido.");
            return false;
        }
        
        if (UsuarioDAO.buscarPorDocumento(usuario.getDocumento()) != null) {
            System.err.println("❌ Ya existe un usuario con ese documento.");
            return false;
        }


        return UsuarioDAO.agregarUsuario(usuario);
    }

    public static boolean editarUsuario(Usuario usuario) {
        // Validaciones extra si hace falta
        return UsuarioDAO.modificarUsuario(usuario);
    }

    public static boolean eliminarUsuario(int id) {
        return UsuarioDAO.eliminarUsuario(id);
    }
}

