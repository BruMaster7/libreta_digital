package main.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.dao.CursoDAO;
import main.dao.RamaCursoDAO;
import main.dao.UsuarioCursoDAO;
import main.model.Curso;
import main.model.Usuario;
import main.model.Rama;

public class CursoService {

    private UsuarioCursoDAO usuarioCursoDAO;

    public CursoService(UsuarioCursoDAO usuarioCursoDAO) {
        this.usuarioCursoDAO = usuarioCursoDAO;
    }

    public List<Curso> obtenerCursosDeDocente(int idDocente) {
        try {
            return usuarioCursoDAO.obtenerCursosPorUsuario(idDocente);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static boolean vincularUsuarioaCurso(int usuarioID, String cursoSeleccionado) {
        try {
            Curso curso = CursoDAO.buscarPorNombre(cursoSeleccionado);
            if (curso == null) {
                throw new Exception("Curso no encontrado.");
            }

            // Insertar en tabla intermedia usuario_curso
            return UsuarioCursoDAO.vincularUsuarioACurso(usuarioID, curso.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


	public static Usuario obtenerDocenteVinculado(String cursoSeleccionado) {
		try {
			Curso curso = CursoDAO.buscarPorNombre(cursoSeleccionado);
			if (curso == null) {
				throw new Exception("Curso no encontrado.");
			}

			// Obtener el docente vinculado al curso
			return UsuarioCursoDAO.obtenerDocentePorCurso(curso.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Usuario> listarEstudiantesEnCurso(String cursoSeleccionado) {
		try {
			Curso curso = CursoDAO.buscarPorNombre(cursoSeleccionado);
			if (curso == null) {
				throw new Exception("Curso no encontrado.");
			}

			// Obtener los estudiantes vinculados al curso
			return UsuarioCursoDAO.obtenerEstudiantesActivosPorCurso(curso.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public static boolean desvincularUsuarioDeCurso(int id, String cursoSeleccionado) {
		try {
			Curso curso = CursoDAO.buscarPorNombre(cursoSeleccionado);
			if (curso == null) {
				throw new Exception("Curso no encontrado.");
			}

			// Desvincular usuario del curso
			return UsuarioCursoDAO.desvincularUsuarioDeCurso(id, curso.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean crearCurso(Curso nuevoCurso) {
		if (nuevoCurso.getNombre_curso() == null || nuevoCurso.getNombre_curso().isEmpty()) {
			System.err.println("❌ El nombre del curso no puede estar vacío.");
			return false;
		}

		 // Verificar si el curso ya existe
		Curso cursoExistente = CursoDAO.buscarPorNombre(nuevoCurso.getNombre_curso());
		if (cursoExistente != null) {
			System.err.println("❌ Ya existe un curso con el nombre: " + nuevoCurso.getNombre_curso());
			return false;
		}
		try {
			return CursoDAO.agregarCurso(nuevoCurso);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Curso buscarCursoPorNombre(String nombreCurso) {
		return CursoDAO.buscarPorNombre(nombreCurso);
	}

	public static boolean actualizarCurso(Curso cursoExistente) {
		return CursoDAO.modificarCurso(cursoExistente);
	}

	public static List<Curso> listarTodosLosCursos() {
		return CursoDAO.listarCursos();
	}

	public static boolean DarDeBajaCurso(String nombreCurso) {
		
		return CursoDAO.DarDeBajaCurso(nombreCurso);}

	public static Object obtenerNombreRamaPorId(int rama_id) {
		return RamaCursoDAO.obtenerNombreRamaPorId(rama_id);
	}



public static List<Rama> listarCursosPorRama(int ramaid){
	return RamaCursoDAO.listarCursosPorRama(ramaid);
}}