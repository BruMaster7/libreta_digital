package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import main.config.Conexion;
import main.dao.CalificacionDAO;
import main.dao.UsuarioCursoDAO;
import main.dao.UsuarioDAO;
import main.model.CalificacionDetalle;
import main.model.Curso;
import main.model.Usuario;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class VentanaDocentes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEstudiantes;
	private JTextField textPlanif;
	private JTable table;
	private JTextField textLinkEvaluacion;
	private JTextField textField_1;
	private JTable tableLista;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtFechaDesarrollo;
	private JTextField textLinkPlanif;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private static Usuario Docente;
	private static Curso Curso;
    private UsuarioCursoDAO usuarioCursoDAO;
    private CalificacionDAO calificacionDAO;
    
	// Clase interna para el resumen del estudiante en el curso
    public class ResumenEstudianteCurso {
        private String documento;
        private String nombre;
        private String apellido;
        private List<Double> actividades = new ArrayList<>();
        private Double primerParcial;
        private Double segundoParcial;
        private int faltas; // Aun no tengo este dato, falta hacer la funcionalidad

        public ResumenEstudianteCurso(String documento, String nombre, String apellido) {
            this.documento = documento;
            this.nombre = nombre;
            this.apellido = apellido;
        }

        // Getters y setters
        public String getDocumento() { return documento; }
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public List<Double> getActividades() { return actividades; }
        public Double getPrimerParcial() { return primerParcial; }
        public void setPrimerParcial(Double primerParcial) { this.primerParcial = primerParcial; }
        public Double getSegundoParcial() { return segundoParcial; }
        public void setSegundoParcial(Double segundoParcial) { this.segundoParcial = segundoParcial; }
        public int getFaltas() { return faltas; }
        public void setFaltas(int faltas) { this.faltas = faltas; }
        
        public String getActividadesStr() {
            if (actividades.isEmpty()) return "-";
            return actividades.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        }
        
        public Double getPromedio() {
            double suma = 0;
            int count = 0;
            
            if (primerParcial != null) {
                suma += primerParcial;
                count++;
            }
            if (segundoParcial != null) {
                suma += segundoParcial;
                count++;
            }
            for (Double act : actividades) {
                suma += act;
                count++;
            }
            
            return count > 0 ? suma / count : 0.0;
        }
    }




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDocentes frame = new VentanaDocentes(Docente, Curso);
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
	public VentanaDocentes(Usuario Docente, Curso Curso) {
		VentanaDocentes.Docente = Docente; // asignar usuario
	    VentanaDocentes.Curso = Curso;     // asignar curso
	    
	    this.usuarioCursoDAO = new UsuarioCursoDAO(Conexion.conectar());
	    this.calificacionDAO = new CalificacionDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDocentes.class.getResource("/resources/Libreta.png")));
		setTitle("Gestión de curso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 640);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(235, 235, 235));
		tabbedPane.setFont(new Font("Arial", Font.PLAIN, 18));
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(new Color(125, 14, 226));
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel tabEstudiantes = new JPanel();
		tabEstudiantes.setBackground(new Color(173, 108, 208));
		tabbedPane.addTab("Estudiantes", null, tabEstudiantes, null);
		tabEstudiantes.setLayout(null);
		
		JPanel panelEstudiantes = new JPanel();
		panelEstudiantes.setBounds(0, 0, 993, 555);
		tabEstudiantes.add(panelEstudiantes);
		panelEstudiantes.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Listado de estudiantes");
		lblNewLabel.setBounds(0, 0, 993, 19);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		panelEstudiantes.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 19, 993, 501);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panelEstudiantes.add(scrollPane);
		
		tableEstudiantes = new JTable();
		tableEstudiantes.setRowHeight(43);
		tableEstudiantes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 0, 255)));
		tableEstudiantes.setBackground(new Color(205, 155, 255));
		tableEstudiantes.setFont(new Font("Arial", Font.BOLD, 14));
		tableEstudiantes.setForeground(new Color(0, 0, 0));
		tableEstudiantes.setModel(new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "Documento", "Nombre", "Apellido", "Actividades",
	                "Primer Parcial", "Segundo Parcial", "Promedio", "Faltas"
	            }
	        ));
		cargarEstudiantes();
		tableEstudiantes.getColumnModel().getColumn(3).setPreferredWidth(68);
		tableEstudiantes.getColumnModel().getColumn(3).setMinWidth(42);
		tableEstudiantes.getColumnModel().getColumn(3).setMaxWidth(800);
		tableEstudiantes.getColumnModel().getColumn(4).setPreferredWidth(68);
		tableEstudiantes.getColumnModel().getColumn(4).setMinWidth(42);
		tableEstudiantes.getColumnModel().getColumn(4).setMaxWidth(800);
		scrollPane.setViewportView(tableEstudiantes);
		JTableHeader header = tableEstudiantes.getTableHeader();
		header.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente más grande y en negrita
		header.setBackground(new Color(230, 230, 250)); // Color lavanda suave
		header.setForeground(new Color(60, 60, 60)); // Gris oscuro
		header.setOpaque(true);
		tableEstudiantes.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane.setViewportView(tableEstudiantes);
		JPanel panelBtnEstudiantes = new JPanel();
		panelBtnEstudiantes.setBounds(0, 520, 993, 35);
		panelEstudiantes.add(panelBtnEstudiantes);
		panelBtnEstudiantes.setLayout(null);
		
		JButton btnAgregarNota_1 = new JButton("Agregar nota");
		btnAgregarNota_1.setBounds(220, 5, 123, 25);
		btnAgregarNota_1.setForeground(Color.WHITE);
		btnAgregarNota_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnAgregarNota_1.setBackground(new Color(128, 0, 255));
		panelBtnEstudiantes.add(btnAgregarNota_1);
		
		JButton btnEditarNota_1 = new JButton("Editar nota\r\n");
		btnEditarNota_1.setBounds(353, 5, 109, 25);
		btnEditarNota_1.setForeground(Color.WHITE);
		btnEditarNota_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnEditarNota_1.setBackground(new Color(128, 0, 255));
		panelBtnEstudiantes.add(btnEditarNota_1);
		
		JButton btnCerrarPromedios_1 = new JButton("Cerrar Promedios");
		btnCerrarPromedios_1.setBounds(472, 5, 157, 25);
		btnCerrarPromedios_1.setForeground(Color.WHITE);
		btnCerrarPromedios_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnCerrarPromedios_1.setBackground(new Color(128, 0, 255));
		panelBtnEstudiantes.add(btnCerrarPromedios_1);
		
		JLabel lblNewLabel_1 = new JLabel("PERDON BRU NO SE COMO PONER LOS BOTONES EN LA GRILLA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(639, 9, 354, 19);
		panelBtnEstudiantes.add(lblNewLabel_1);
		
		JPanel tabPlanificaciones = new JPanel();
		tabbedPane.addTab("Planificaciones", null, tabPlanificaciones, null);
		tabPlanificaciones.setLayout(null);
		
		JPanel panelPlanificaciones = new JPanel();
		panelPlanificaciones.setBounds(0, 0, 993, 563);
		tabPlanificaciones.add(panelPlanificaciones);
		panelPlanificaciones.setLayout(null);
		
		JLabel lblSubirPlanif = new JLabel("Subir planificación anual (Link público de drive):");
		lblSubirPlanif.setFont(new Font("Arial", Font.BOLD, 14));
		lblSubirPlanif.setBounds(20, 119, 347, 24);
		panelPlanificaciones.add(lblSubirPlanif);
		
		textPlanif = new JTextField();
		textPlanif.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textPlanif.setBounds(417, 117, 477, 29);
		panelPlanificaciones.add(textPlanif);
		textPlanif.setColumns(10);
		
		JButton btnGuardarPlanif = new JButton("Guardar Planificación");
		btnGuardarPlanif.setForeground(new Color(255, 255, 255));
		btnGuardarPlanif.setBackground(new Color(128, 0, 255));
		btnGuardarPlanif.setBounds(590, 157, 173, 57);
		panelPlanificaciones.add(btnGuardarPlanif);
		
		JSeparator separatorPlanif = new JSeparator();
		separatorPlanif.setForeground(new Color(255, 0, 255));
		separatorPlanif.setBackground(new Color(128, 0, 255));
		separatorPlanif.setToolTipText("Evaluaciones");
		separatorPlanif.setBounds(20, 218, 973, 35);
		panelPlanificaciones.add(separatorPlanif);
		
		JFormattedTextField textFecha = new JFormattedTextField();
		textFecha.setFont(new Font("Arial", Font.BOLD, 14));
		textFecha.setBounds(238, 324, 111, 20);
		panelPlanificaciones.add(textFecha);
		
		table = new JTable();
		table.setBounds(313, 328, 1, 1);
		panelPlanificaciones.add(table);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
		lblFecha.setBounds(162, 327, 54, 14);
		panelPlanificaciones.add(lblFecha);
		
		JLabel lblContenidoEvaluacion = new JLabel("Contenido:");
		lblContenidoEvaluacion.setFont(new Font("Arial", Font.BOLD, 14));
		lblContenidoEvaluacion.setBounds(139, 388, 77, 14);
		panelPlanificaciones.add(lblContenidoEvaluacion);
		
		JTextArea txtrIngreseLosContenidos = new JTextArea();
		txtrIngreseLosContenidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtrIngreseLosContenidos.setBackground(new Color(255, 255, 255));
		txtrIngreseLosContenidos.setForeground(new Color(128, 128, 255));
	
		txtrIngreseLosContenidos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtrIngreseLosContenidos.getText().equals("Ingrese los contenidos \\r\\n abordados en la evaluación...")) {
					txtrIngreseLosContenidos.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (txtrIngreseLosContenidos.getText().trim().isEmpty()) {
					txtrIngreseLosContenidos.setText("Ingrese los contenidos \r\nabordados en la evaluación...");
					txtrIngreseLosContenidos.setForeground(new Color(128, 128, 255));
				}
			}
			
		});
		
		
		txtrIngreseLosContenidos.setText("Ingrese los contenidos \r\nabordados en la evaluación...");
		txtrIngreseLosContenidos.setBounds(238, 384, 222, 88);
		panelPlanificaciones.add(txtrIngreseLosContenidos);
		
		JLabel lblEvaluacion = new JLabel("Evaluación");
		lblEvaluacion.setForeground(new Color(128, 0, 255));
		lblEvaluacion.setFont(new Font("Arial", Font.BOLD, 24));
		lblEvaluacion.setBounds(73, 245, 146, 29);
		panelPlanificaciones.add(lblEvaluacion);
		
		JLabel lblPlanificaciones = new JLabel("Planificaciones");
		lblPlanificaciones.setForeground(new Color(128, 0, 255));
		lblPlanificaciones.setFont(new Font("Arial", Font.BOLD, 24));
		lblPlanificaciones.setBounds(39, 35, 185, 24);
		panelPlanificaciones.add(lblPlanificaciones);
		
		JLabel lblDocumentoPlanif = new JLabel("Link a documento (opcional):");
		lblDocumentoPlanif.setFont(new Font("Arial", Font.BOLD, 14));
		lblDocumentoPlanif.setBounds(20, 505, 208, 14);
		panelPlanificaciones.add(lblDocumentoPlanif);
		
		textLinkEvaluacion = new JTextField();
		textLinkEvaluacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textLinkEvaluacion.setBounds(238, 501, 262, 24);
		panelPlanificaciones.add(textLinkEvaluacion);
		textLinkEvaluacion.setColumns(10);
		
		JButton btnGuardarEvaluacion = new JButton("Guardar Evaluación");
		btnGuardarEvaluacion.setForeground(new Color(255, 255, 255));
		btnGuardarEvaluacion.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardarEvaluacion.setBackground(new Color(128, 0, 255));
		btnGuardarEvaluacion.setBounds(578, 484, 185, 57);
		panelPlanificaciones.add(btnGuardarEvaluacion);
		
		JLabel lblIconoEval = new JLabel("New label");
		lblIconoEval.setIcon(new ImageIcon(VentanaDocentes.class.getResource("/resources/ExamenIcon.png")));
		lblIconoEval.setBounds(206, 226, 111, 75);
		panelPlanificaciones.add(lblIconoEval);
		
		JLabel lblIconoPlano = new JLabel("");
		lblIconoPlano.setIcon(new ImageIcon(VentanaDocentes.class.getResource("/resources/Plano.png")));
		lblIconoPlano.setBounds(234, -15, 156, 123);
		panelPlanificaciones.add(lblIconoPlano);
		
		JPanel tabPasarLista = new JPanel();
		tabbedPane.addTab("Pasar Lista", null, tabPasarLista, null);
		tabPasarLista.setLayout(null);
		
		JPanel panelPasarLista = new JPanel();
		panelPasarLista.setBounds(0, 0, 993, 563);
		tabPasarLista.add(panelPasarLista);
		panelPasarLista.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSupPasarLista = new JPanel();
		panelPasarLista.add(panelSupPasarLista, BorderLayout.NORTH);
		
		JLabel lblFechaClase = new JLabel("Clase del día:");
		lblFechaClase.setFont(new Font("Arial", Font.BOLD, 14));
		panelSupPasarLista.add(lblFechaClase);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelSupPasarLista.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cargar lista\r\n");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 0, 255));
		panelSupPasarLista.add(btnNewButton_1);
		
		JPanel panelInfPasarLista = new JPanel();
		panelPasarLista.add(panelInfPasarLista, BorderLayout.SOUTH);
		
		JButton btnGuardarLista = new JButton("Guardar lista");
		btnGuardarLista.setForeground(new Color(255, 255, 255));
		btnGuardarLista.setBackground(new Color(128, 0, 255));
		btnGuardarLista.setFont(new Font("Arial", Font.BOLD, 14));
		panelInfPasarLista.add(btnGuardarLista);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelPasarLista.add(scrollPane_1, BorderLayout.CENTER);
		
		
		tableLista = new JTable();
		tableLista.setRowHeight(24);
		tableLista.setBackground(new Color(219, 183, 255));
		tableLista.setFont(new Font("Arial", Font.BOLD, 14));
		tableLista.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Esudiante", "Condici\u00F3n"
			}
		));
		JTableHeader headerL = tableLista.getTableHeader();
		headerL.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Fuente más grande y en negrita
		headerL.setBackground(new Color(230, 230, 250)); // Color lavanda suave
		headerL.setForeground(new Color(60, 60, 60)); // Gris oscuro
		headerL.setOpaque(true);
		
		tableLista.getColumnModel().getColumn(1).setPreferredWidth(48);
		tableLista.getColumnModel().getColumn(1).setMaxWidth(1000000000);
		scrollPane_1.setViewportView(tableLista);
		JLabel lblNewLabel_5 = new JLabel("Pasar lista");
		JPanel tabVisado = new JPanel();
		tabbedPane.addTab("Visado", null, tabVisado, null);
		tabVisado.setLayout(null);
		
		JPanel panelVisado = new JPanel();
		panelVisado.setBounds(0, 0, 993, 555);
		tabVisado.add(panelVisado);
		panelVisado.setLayout(null);
		
		JLabel lblDef3 = new JLabel("Link de la planificación anual:");
		lblDef3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDef3.setBounds(219, 123, 228, 22);
		panelVisado.add(lblDef3);
		
		JLabel lblEstadoVisado = new JLabel("Estado de visado:");
		lblEstadoVisado.setForeground(new Color(128, 0, 255));
		lblEstadoVisado.setBounds(389, 24, 209, 23);
		panelVisado.add(lblEstadoVisado);
		lblEstadoVisado.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		JRadioButton rdbtnPlanifCEstadovis = new JRadioButton("Visado Completo");
		rdbtnPlanifCEstadovis.setBounds(464, 126, 121, 23);
		panelVisado.add(rdbtnPlanifCEstadovis);
		rdbtnPlanifCEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		buttonGroup.add(rdbtnPlanifCEstadovis);
		
		JRadioButton rdbtnPlanifIEstadovis = new JRadioButton("Visado incompleto");
		rdbtnPlanifIEstadovis.setBounds(586, 126, 131, 23);
		panelVisado.add(rdbtnPlanifIEstadovis);
		rdbtnPlanifIEstadovis.setSelected(true);
		rdbtnPlanifIEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		buttonGroup.add(rdbtnPlanifIEstadovis);
		
		JLabel lblDef2 = new JLabel("Promedios de estudiantes:");
		lblDef2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDef2.setBounds(246, 200, 201, 22);
		panelVisado.add(lblDef2);
		
		JRadioButton rdbtnPromedioCEstadovis = new JRadioButton("Visado Completo");
		buttonGroup_1.add(rdbtnPromedioCEstadovis);
		rdbtnPromedioCEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnPromedioCEstadovis.setBounds(464, 203, 121, 23);
		panelVisado.add(rdbtnPromedioCEstadovis);
		
		JRadioButton rdbtnPromedioIEstadovis = new JRadioButton("Visado incompleto");
		buttonGroup_1.add(rdbtnPromedioIEstadovis);
		rdbtnPromedioIEstadovis.setSelected(true);
		rdbtnPromedioIEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnPromedioIEstadovis.setBounds(586, 203, 131, 23);
		panelVisado.add(rdbtnPromedioIEstadovis);
		
		JRadioButton rdbtnPlanifSEstadovis = new JRadioButton("Sin revisión");
		buttonGroup.add(rdbtnPlanifSEstadovis);
		rdbtnPlanifSEstadovis.setBounds(719, 126, 109, 23);
		panelVisado.add(rdbtnPlanifSEstadovis);
		
		JRadioButton rdbtnPromedioSEstadovis = new JRadioButton("Sin revisión");
		buttonGroup_1.add(rdbtnPromedioSEstadovis);
		rdbtnPromedioSEstadovis.setSelected(true);
		rdbtnPromedioSEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnPromedioSEstadovis.setBounds(719, 203, 131, 23);
		panelVisado.add(rdbtnPromedioSEstadovis);
		
		JLabel lblDef = new JLabel("Registro de evaluación semestral:");
		lblDef.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDef.setBounds(192, 294, 255, 22);
		panelVisado.add(lblDef);
		
		JRadioButton rdbtnEvalCEstadovis = new JRadioButton("Visado Completo");
		buttonGroup_2.add(rdbtnEvalCEstadovis);
		rdbtnEvalCEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnEvalCEstadovis.setBounds(464, 297, 121, 23);
		panelVisado.add(rdbtnEvalCEstadovis);
		
		JRadioButton rdbtnEvalIEstadovis = new JRadioButton("Visado incompleto");
		buttonGroup_2.add(rdbtnEvalIEstadovis);
		rdbtnEvalIEstadovis.setSelected(true);
		rdbtnEvalIEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnEvalIEstadovis.setBounds(586, 297, 131, 23);
		panelVisado.add(rdbtnEvalIEstadovis);
		
		JRadioButton rdbtnEvalSEstadovis = new JRadioButton("Sin revisión");
		buttonGroup_2.add(rdbtnEvalSEstadovis);
		rdbtnEvalSEstadovis.setSelected(true);
		rdbtnEvalSEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnEvalSEstadovis.setBounds(719, 297, 131, 23);
		panelVisado.add(rdbtnEvalSEstadovis);
		
		JLabel lblDesarrolloEstadoVis = new JLabel("Desarrollo del curso:");
		lblDesarrolloEstadoVis.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDesarrolloEstadoVis.setBounds(291, 394, 156, 23);
		panelVisado.add(lblDesarrolloEstadoVis);
		
		JRadioButton rdbtnDesarrolloCEstadovis = new JRadioButton("Visado Completo");
		buttonGroup_3.add(rdbtnDesarrolloCEstadovis);
		rdbtnDesarrolloCEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnDesarrolloCEstadovis.setBounds(464, 397, 121, 23);
		panelVisado.add(rdbtnDesarrolloCEstadovis);
		
		JRadioButton rdbtnDesarrolloIEstadovis = new JRadioButton("Visado incompleto");
		buttonGroup_3.add(rdbtnDesarrolloIEstadovis);
		rdbtnDesarrolloIEstadovis.setSelected(true);
		rdbtnDesarrolloIEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnDesarrolloIEstadovis.setBounds(586, 397, 131, 23);
		panelVisado.add(rdbtnDesarrolloIEstadovis);
		
		JRadioButton rdbtnDesarrolloSEstadovis = new JRadioButton("Sin revisión");
		buttonGroup_3.add(rdbtnDesarrolloSEstadovis);
		rdbtnDesarrolloSEstadovis.setSelected(true);
		rdbtnDesarrolloSEstadovis.setFont(new Font("Arial", Font.BOLD, 12));
		rdbtnDesarrolloSEstadovis.setBounds(719, 397, 131, 23);
		panelVisado.add(rdbtnDesarrolloSEstadovis);
		
		JPanel tabDesarrollo = new JPanel();
		tabbedPane.addTab("Desarrollo", null, tabDesarrollo, null);
		tabDesarrollo.setLayout(null);
		
		JPanel panelDesarrollo = new JPanel();
		panelDesarrollo.setBounds(0, 0, 1003, 563);
		tabDesarrollo.add(panelDesarrollo);
		panelDesarrollo.setLayout(null);
		
		JLabel lblDesarrollo = new JLabel("Desarrollo del curso.");
		lblDesarrollo.setForeground(new Color(128, 0, 255));
		lblDesarrollo.setFont(new Font("Arial", Font.BOLD, 24));
		lblDesarrollo.setBounds(344, 25, 244, 51);
		panelDesarrollo.add(lblDesarrollo);
		
		JLabel lblFechaDesarrollo = new JLabel("Fecha:");
		lblFechaDesarrollo.setFont(new Font("Arial", Font.BOLD, 14));
		lblFechaDesarrollo.setBounds(68, 143, 47, 14);
		panelDesarrollo.add(lblFechaDesarrollo);
		
		txtFechaDesarrollo = new JTextField();
		txtFechaDesarrollo.setText("(Aca iria un date chooser)");
		txtFechaDesarrollo.setBounds(125, 141, 149, 20);
		panelDesarrollo.add(txtFechaDesarrollo);
		txtFechaDesarrollo.setColumns(10);
		
		JLabel lblDesDesarrollo = new JLabel("Desarrollo:");
		lblDesDesarrollo.setFont(new Font("Arial", Font.BOLD, 14));
		lblDesDesarrollo.setBounds(38, 183, 77, 20);
		panelDesarrollo.add(lblDesDesarrollo);
		
		JTextArea textDesarrollo = new JTextArea();
		textDesarrollo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDesarrollo.setBounds(125, 182, 366, 143);
		panelDesarrollo.add(textDesarrollo);
		
		JLabel lblLinkPlanif = new JLabel("Link a planificación (Opcional):");
		lblLinkPlanif.setFont(new Font("Arial", Font.BOLD, 14));
		lblLinkPlanif.setBounds(38, 383, 218, 14);
		panelDesarrollo.add(lblLinkPlanif);
		
		textLinkPlanif = new JTextField();
		textLinkPlanif.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		textLinkPlanif.setBounds(255, 381, 218, 20);
		panelDesarrollo.add(textLinkPlanif);
		textLinkPlanif.setColumns(10);
		
		JButton btnVerHistorial = new JButton("Ver historial");
		btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 14));
		btnVerHistorial.setForeground(new Color(255, 255, 255));
		btnVerHistorial.setBackground(new Color(128, 0, 255));
		btnVerHistorial.setBounds(756, 132, 141, 37);
		panelDesarrollo.add(btnVerHistorial);
		
		JButton btnGuardarDesarrollo = new JButton("Guardar Desarrollo");
		btnGuardarDesarrollo.setForeground(new Color(255, 255, 255));
		btnGuardarDesarrollo.setBackground(new Color(128, 0, 255));
		btnGuardarDesarrollo.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardarDesarrollo.setBounds(208, 449, 187, 37);
		panelDesarrollo.add(btnGuardarDesarrollo);
		
		JList listDesarrollos = new JList();
		listDesarrollos.setLayoutOrientation(JList.VERTICAL_WRAP);
		listDesarrollos.setModel(new AbstractListModel() {
			String[] values = new String[] {"Clase 5/07", "Clase 26/07", "Clase 31/07", "Clase 1/08"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listDesarrollos.setValueIsAdjusting(true);
		listDesarrollos.setForeground(new Color(128, 0, 255));
		listDesarrollos.setFont(new Font("Arial", Font.BOLD, 18));
		listDesarrollos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255)));
		listDesarrollos.setBackground(new Color(230, 204, 255));
		listDesarrollos.setBounds(756, 186, 141, 267);
		panelDesarrollo.add(listDesarrollos);
	}
	
	private void cargarEstudiantes() {
        try {
            // Obtener estudiantes activos del curso
            List<Usuario> estudiantes = usuarioCursoDAO.obtenerEstudiantesActivosPorCurso(Curso.getId());
            DefaultTableModel model = (DefaultTableModel) tableEstudiantes.getModel();
            model.setRowCount(0); // Limpiar tabla existente

            for (Usuario estudiante : estudiantes) {
                // Obtener calificaciones del estudiante en este curso
                List<CalificacionDetalle> calificaciones = 
                    calificacionDAO.obtenerCalificacionesPorEstudianteYCurso(
                        estudiante.getUsuarioId(), Curso.getId());

                // Crear resumen del estudiante
                ResumenEstudianteCurso resumen = new ResumenEstudianteCurso(
                    estudiante.getDocumento(),
                    estudiante.getNombre(),
                    estudiante.getApellido()
                );

                // Procesar calificaciones
                for (CalificacionDetalle calificacion : calificaciones) {
                    String tipo = calificacion.getTipoEvaluacion();
                    double nota = calificacion.getNota();

                    if ("Actividad".equalsIgnoreCase(tipo)) {
                        resumen.getActividades().add(nota);
                    } else if ("Primer Parcial".equalsIgnoreCase(tipo)) {
                        resumen.setPrimerParcial(nota);
                    } else if ("Segundo Parcial".equalsIgnoreCase(tipo)) {
                        resumen.setSegundoParcial(nota);
                    }
                }

                // Añadir fila a la tabla
                model.addRow(new Object[]{
                    resumen.getDocumento(),
                    resumen.getNombre(),
                    resumen.getApellido(),
                    resumen.getActividadesStr(),
                    resumen.getPrimerParcial(),
                    resumen.getSegundoParcial(),
                    resumen.getPromedio(),
                    resumen.getFaltas() // Puede ser 0 o null temporalmente
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + e.getMessage());
        }
    }
}
