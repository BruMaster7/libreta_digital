package main.services;

import java.time.LocalDate;
import java.util.List;

import main.dao.UsuarioDAO;
import main.model.Usuario;

public class UsuarioService {

	public static boolean registrarUsuario(Usuario usuario) throws Exception {
		if (UsuarioDAO.buscarPorDocumento(usuario.getDocumento()) != null) {
        	throw new Exception("Ya existe un usuario con este documento.");
        }
		if (validarUsuario(usuario)) {
	        return UsuarioDAO.agregarUsuario(usuario);

		} else {
			return false;
		}

		

    }

    public static boolean editarUsuario(Usuario usuario) throws Exception {
    	if((validarUsuario(usuario))) {
    		return UsuarioDAO.modificarUsuario(usuario);
    } else {
			return false;
		}
	}

    public static boolean eliminarUsuario(int id) {
        return UsuarioDAO.eliminarUsuario(id);
    }

	public static Usuario buscarUsuarioPorCedula(String cedula) {
        String documento = cedula.trim();
		return UsuarioDAO.buscarPorDocumento(documento );

	}
	private static boolean validarUsuario(Usuario usuario) throws Exception{
		// Validar formato de campos
				if (usuario.getDocumento() == null || !usuario.getDocumento().matches("\\d{7,8}")) {
		            throw new Exception("Documento inválido. Debe contener 7 u 8 dígitos numéricos.");
		        }
		        if (usuario.getNombre() == null || !usuario.getNombre().matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,50}$")) {
		            throw new Exception("Nombre inválido. Solo letras y mínimo 2 caracteres.");
		        }

		        if (usuario.getApellido() == null || !usuario.getApellido().matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]{2,50}$")) {
		            throw new Exception("Apellido inválido. Solo letras y mínimo 2 caracteres.");
		        }

		        if (!usuario.getEmail().contains("@")) {
		       	 throw new Exception("Email inválido. Debe contener '@'.");
		        }

		        // Validar Fecha de nacimiento
		        if (usuario.getFechaNacimiento() == null) {
		            throw new Exception("Debe ingresar una fecha de nacimiento.");
		        }
		        LocalDate fechaNac = usuario.getFechaNacimiento().toLocalDate();
		        if (fechaNac.isAfter(LocalDate.now())) {
		            throw new Exception("La fecha de nacimiento no puede ser futura.");
		        }
		        if (fechaNac.isAfter(LocalDate.now().minusYears(11))) {
		            throw new Exception("El usuario debe tener al menos 11 años.");
		        }
		        if (fechaNac.isBefore(LocalDate.now().minusYears(100))) {
					throw new Exception("La fecha de nacimiento no puede ser mayor a 100 años.");
				}

		        if (usuario.getContrasena() == null || usuario.getContrasena().length() < 4) {
		            throw new Exception("La contraseña debe tener al menos 4 caracteres.");
		        }
		        return true;
	}

	public static List<Usuario> listarUsuariosPorRol(int rolId) {
		return UsuarioDAO.listarUsuariosPorRol(rolId);
	}
}

