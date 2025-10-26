package main.services;

import main.dao.VisadoDAO;
import main.dao.UsuarioDAO;
import main.model.Visado;
import main.model.Usuario;

public class VisadoService {

    private static VisadoDAO dao = new VisadoDAO();

    // Método simple que delega el guardado al DAO
    public static boolean crearVisado(Visado visado) {
        try {
            return dao.guardarVisado(visado);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtiene un Visado por cursoId
    public static Visado obtenerVisadoPorCurso(int cursoId) {
        try {
            return dao.obtenerVisadoPorCurso(cursoId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Obtiene un Usuario por ID
    public static Usuario obtenerUsuarioPorId(int usuarioId) {
        return UsuarioDAO.buscarUsuarioPorId(usuarioId);
    }
}
