package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.dao.CalificacionDAO;
import main.model.CalificacionDetalle;
import main.model.Usuario;
import main.services.BoletinCurso;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

public class VentanaEstudiante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableBoletin;
	private static Usuario estudiante;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEstudiante frame = new VentanaEstudiante(estudiante);
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
	public VentanaEstudiante(Usuario estudiante) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 606);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 746, 567);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 56, 597, 363);
		panel.add(scrollPane);
		
		tableBoletin = new JTable();
		tableBoletin.setEnabled(false);

		scrollPane.setViewportView(tableBoletin);
	    cargarBoletin(estudiante.getId());

	}
	
	private void cargarBoletin(int estudianteId) {
	    CalificacionDAO dao = new CalificacionDAO();
	    List<CalificacionDetalle> calificaciones = dao.obtenerCalificacionesPorEstudiante(estudianteId);

	    Map<String, BoletinCurso> boletinMap = new HashMap<>();

	    for (CalificacionDetalle c : calificaciones) {
	        String curso = c.getNombreCurso();
	        boletinMap.putIfAbsent(curso, new BoletinCurso(curso));
	        boletinMap.get(curso).agregarNota(c.getTipoEvaluacion(), c.getNombreEvaluacion(), c.getNota());
	    }

	    DefaultTableModel modelo = new DefaultTableModel(
	        new String[]{"Asignatura", "Actividades", "1er Parcial", "2do Parcial", "Promedio", "Faltas"}, 0
	    );

	    for (BoletinCurso bc : boletinMap.values()) {
	        modelo.addRow(new Object[]{
	            bc.getAsignatura(),
	            bc.getActividadesStr(),
	            bc.getPrimerParcial() != null ? bc.getPrimerParcial() : "-",
	            bc.getSegundoParcial() != null ? bc.getSegundoParcial() : "-",
	            bc.getPromedio(),
	            "-" // Podés reemplazar cuando tengas faltas
	        });
	    }

	    tableBoletin.setModel(modelo);
	}


}
