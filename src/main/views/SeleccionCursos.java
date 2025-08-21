package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import main.config.Conexion;
import main.dao.UsuarioCursoDAO;
import main.model.Curso;
import main.model.Usuario;
import main.services.CursoService;

import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.List;

public class SeleccionCursos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Usuario Docente;
    private JList<Curso> listCursos;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    SeleccionCursos frame = new SeleccionCursos(Docente);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SeleccionCursos(Usuario Docente) {
        this.Docente = Docente;

        setIconImage(Toolkit.getDefaultToolkit().getImage(SeleccionCursos.class.getResource("/resources/Libreta.png")));
        setTitle("Mis Cursos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTituloCursos = new JLabel("Seleccione el curso al cual desea ingresar");
        lblTituloCursos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloCursos.setBounds(267, 168, 488, 28);
        lblTituloCursos.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(lblTituloCursos);

        // ✅ Modelo dinámico
        DefaultListModel<Curso> modeloCursos = new DefaultListModel<>();
        try {
            Connection conn = Conexion.conectar(); // usa tu clase de conexión
            UsuarioCursoDAO usuarioCursoDAO = new UsuarioCursoDAO(conn);
            CursoService cursoService = new CursoService(usuarioCursoDAO);

            List<Curso> cursos = cursoService.obtenerCursosDeDocente(Docente.getUsuarioId());
            for (Curso c : cursos) {
                modeloCursos.addElement(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        listCursos = new JList<>(modeloCursos);
        listCursos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255)));
        listCursos.setBackground(new Color(230, 204, 255));
        listCursos.setForeground(new Color(128, 0, 255));
        listCursos.setFont(new Font("Arial", Font.BOLD, 18));
        listCursos.setBounds(414, 225, 300, 267);
        contentPane.add(listCursos);
        
        JButton btnIngresarCurso = new JButton("Ingresar al curso");
        btnIngresarCurso.setFont(new Font("Arial", Font.PLAIN, 18));
        btnIngresarCurso.setForeground(new Color(255, 255, 255));
        btnIngresarCurso.setBackground(new Color(125, 14, 226));
        btnIngresarCurso.setBounds(384, 521, 196, 69);
        contentPane.add(btnIngresarCurso);
        
        JLabel lblIconoCursos = new JLabel();
        lblIconoCursos.setIcon(new ImageIcon(SeleccionCursos.class.getResource("/resources/Libreta.png")));
        lblIconoCursos.setBounds(409, 32, 146, 123);
        contentPane.add(lblIconoCursos);

        // ✅ Acción de botón
        btnIngresarCurso.addActionListener(e -> {
            Curso seleccionado = listCursos.getSelectedValue();
            if (seleccionado != null) {
                JOptionPane.showMessageDialog(this, 
                        "Ingresando al curso: " + seleccionado.getNombre_curso());
                // aquí abrirías la siguiente vista, pasando el curso seleccionado
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un curso primero.");
            }
        });
    }
}

