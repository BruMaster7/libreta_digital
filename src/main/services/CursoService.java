package main.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.dao.UsuarioCursoDAO;
import main.model.Curso;

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
}

