package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.dao.AsistenciaDAO;
import main.dao.CalificacionDAO;
import main.model.CalificacionDetalle;
import main.model.Usuario;
import main.services.BoletinCurso;
import main.views.personalized.TextAreaRenderer;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import main.dao.CalificacionDAO;
import main.model.CalificacionDetalle;
import main.model.Usuario;
import main.services.BoletinCurso;
import main.views.personalized.TextAreaRenderer;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentanaEstudiante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableLegajo;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaEstudiante.class.getResource("/resources/Libreta.png")));
		setMinimumSize(new Dimension(1024, 640));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 637);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Arial", Font.BOLD, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneLegajo = new JScrollPane();
		scrollPaneLegajo.setBounds(47, 56, 905, 329);
		contentPane.add(scrollPaneLegajo);
		
		tableLegajo = new JTable();
		tableLegajo.setEnabled(false);
		tableLegajo.setBackground(new Color(211, 168, 255));
		tableLegajo.setGridColor(new Color(128, 0, 255));
		tableLegajo.setAlignmentY(Component.TOP_ALIGNMENT);
		tableLegajo.setRowHeight(30);
		tableLegajo.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		tableLegajo.setColumnSelectionAllowed(true);
		tableLegajo.setCellSelectionEnabled(true);
		tableLegajo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(128, 0, 255), new Color(128, 0, 255)));
		tableLegajo.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Asignatura", "Notas", "1\u00BA Parcial", "2\u00BAParcial", "Faltas", "Promedio"
			}
		));
		tableLegajo.getColumnModel().getColumn(0).setMinWidth(17);
		tableLegajo.getColumnModel().getColumn(1).setPreferredWidth(215);
		tableLegajo.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableLegajo.getColumnModel().getColumn(2).setMinWidth(44);
		tableLegajo.getColumnModel().getColumn(2).setMaxWidth(4444444);
		tableLegajo.getColumnModel().getColumn(3).setPreferredWidth(70);
		tableLegajo.getColumnModel().getColumn(3).setMinWidth(44);
		tableLegajo.getColumnModel().getColumn(3).setMaxWidth(444444);
		tableLegajo.getColumnModel().getColumn(4).setPreferredWidth(44);
		tableLegajo.getColumnModel().getColumn(4).setMinWidth(44);
		tableLegajo.getColumnModel().getColumn(4).setMaxWidth(4444);
		tableLegajo.getColumnModel().getColumn(5).setPreferredWidth(70);
		tableLegajo.getColumnModel().getColumn(5).setMinWidth(44);
		tableLegajo.getColumnModel().getColumn(5).setMaxWidth(4444);
		JTableHeader header = tableLegajo.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente más grande y en negrita
		header.setBackground(new Color(230, 230, 250)); // Color lavanda suave
		header.setForeground(new Color(60, 60, 60)); // Gris oscuro
		header.setOpaque(true);
		tableLegajo.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPaneLegajo.setViewportView(tableLegajo);
		
		JPanel panelSuperiorEstudiantes = new JPanel();
		panelSuperiorEstudiantes.setBackground(new Color(128, 0, 255));
		panelSuperiorEstudiantes.setBounds(0, 0, 1008, 55);
		contentPane.add(panelSuperiorEstudiantes);
		
		JLabel lblTitulo4 = new JLabel("Bienvenido a tu legajo");
		lblTitulo4.setForeground(Color.WHITE);
		lblTitulo4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 28));
		lblTitulo4.setBackground(new Color(128, 0, 255));
		panelSuperiorEstudiantes.add(lblTitulo4);
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

    // Convertimos los valores a lista y los ordenamos alfabéticamente por asignatura
    List<BoletinCurso> boletinList = new ArrayList<>(boletinMap.values());
    boletinList.sort(Comparator.comparing(BoletinCurso::getAsignatura));
    
    DefaultTableModel modelo = new DefaultTableModel(
        new String[]{"Asignatura", "Actividades", "1er Parcial", "2do Parcial", "Promedio", "Faltas"}, 0
    );

    for (BoletinCurso bc : boletinList) { // <-- usa la lista ordenada
        int faltas = AsistenciaDAO.obtenerFaltasPorEstudianteYCurso(estudianteId, bc.getAsignatura());
        modelo.addRow(new Object[]{
            bc.getAsignatura(),
            bc.getActividadesStr(),
            bc.getPrimerParcial() != null ? bc.getPrimerParcial() : "-",
            bc.getSegundoParcial() != null ? bc.getSegundoParcial() : "-",
            bc.getPromedio(),
            faltas
        });
    }

    tableLegajo.setModel(modelo);

    tableLegajo.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
}
}