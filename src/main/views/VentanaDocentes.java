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
import main.dao.AsistenciaDAO;
import main.dao.CalificacionDAO;
import main.dao.UsuarioCursoDAO;
import main.dao.UsuarioDAO;
import main.model.Asistencia;
import main.model.CalificacionDetalle;
import main.model.Curso;
import main.model.Desarrollo;
import main.model.Evaluacion;
import main.model.Planificacion;
import main.model.Usuario;
import main.services.CalificacionService;
import main.services.DesarrolloService;
import main.services.EvaluacionService;
import main.services.PlanificacionService;
import main.views.personalized.AsistenciaEditor;
import main.views.personalized.AsistenciaRenderer;

import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaDocentes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEstudiantes;
	private JTextField textPlanifAnual;
	private JTable table;
	private JTable tableLista;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private static Usuario Docente;
	private static Curso Curso;
    private UsuarioCursoDAO usuarioCursoDAO;
    private CalificacionDAO calificacionDAO;
    private List<Usuario> estudiantesDelCurso;
    private Planificacion planificacion;
    private JDateChooser dChooserFechaCargada;
    private JDateChooser dChooserFechaGuardada;
    private JTable tableEvaluar;
	private JDateChooser dChooserDesarrollo;

    private JTextField txtTituloEvaluacion;	// Clase interna para el resumen del estudiante en el curso
    private final Action action = new SwingAction();
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
		scrollPane.setBounds(10, 11, 993, 501);
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
		
		JButton btnCerrarPromedios_1 = new JButton("Cerrar Promedios");
		btnCerrarPromedios_1.setBounds(390, 3, 165, 25);
		btnCerrarPromedios_1.setForeground(Color.WHITE);
		btnCerrarPromedios_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnCerrarPromedios_1.setBackground(new Color(128, 0, 255));
		panelBtnEstudiantes.add(btnCerrarPromedios_1);
		
		JButton btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cargarEstudiantes();
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnActualizar.setBackground(new Color(128, 0, 255));
		btnActualizar.setBounds(696, 5, 165, 25);
		panelBtnEstudiantes.add(btnActualizar);
		
		JPanel tabEvaluacion = new JPanel();
		tabbedPane.addTab("Evaluación", null, tabEvaluacion, null);
		tabEvaluacion.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setForeground(new Color(255, 255, 255));
		tabbedPane_1.setBackground(new Color(138, 43, 226));
		tabbedPane_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tabbedPane_1.setBounds(0, 0, 993, 555);
		tabEvaluacion.add(tabbedPane_1);
		
		JPanel panelEvaluaciones = new JPanel();
		panelEvaluaciones.setForeground(new Color(255, 255, 255));
		tabbedPane_1.addTab("Evaluaciones", null, panelEvaluaciones, null);
		panelEvaluaciones.setLayout(null);
		
		JLabel lblIconoEval = new JLabel("");
		lblIconoEval.setBounds(173, 13, 102, 110);
		lblIconoEval.setIcon(new ImageIcon(VentanaDocentes.class.getResource("/resources/ExamenIcon.png")));
		panelEvaluaciones.add(lblIconoEval);
		
		JLabel lblEvaluacion = new JLabel("Evaluación");
		lblEvaluacion.setForeground(new Color(128, 0, 255));
		lblEvaluacion.setFont(new Font("Arial", Font.BOLD, 24));
		lblEvaluacion.setBounds(47, 52, 146, 29);
		panelEvaluaciones.add(lblEvaluacion);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
		lblFecha.setBounds(78, 162, 47, 14);
		panelEvaluaciones.add(lblFecha);
		
		JDateChooser dChooserEvaluacion = new JDateChooser();
		dChooserEvaluacion.setBackground(new Color(216, 191, 216));
		dChooserEvaluacion.setBounds(135, 162, 140, 20);
		panelEvaluaciones.add(dChooserEvaluacion);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Actividad", "Oral", "Parcial1", "Parcial2"}));
		comboBox_1.setBackground(new Color(216, 191, 216));
		comboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		comboBox_1.setBounds(137, 228, 138, 22);
		panelEvaluaciones.add(comboBox_1);
		
		JLabel lblTipoEv_1 = new JLabel("Tipo:");
		lblTipoEv_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblTipoEv_1.setBounds(91, 232, 34, 14);
		panelEvaluaciones.add(lblTipoEv_1);
		
		JLabel lblContenidoEvaluacion_1 = new JLabel("Contenido");
		lblContenidoEvaluacion_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblContenidoEvaluacion_1.setBounds(639, 89, 77, 14);
		panelEvaluaciones.add(lblContenidoEvaluacion_1);
		
		JTextArea txtrIngreseLosContenidos_1 = new JTextArea();
		txtrIngreseLosContenidos_1.setText("Ingrese los contenidos \r\nabordados en la evaluación...");
		txtrIngreseLosContenidos_1.setForeground(new Color(0, 0, 0));
		txtrIngreseLosContenidos_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtrIngreseLosContenidos_1.setBackground(new Color(216, 191, 216));
		txtrIngreseLosContenidos_1.setBounds(452, 114, 468, 224);
		panelEvaluaciones.add(txtrIngreseLosContenidos_1);
		
		txtTituloEvaluacion = new JTextField();
		txtTituloEvaluacion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtTituloEvaluacion.setColumns(10);
		txtTituloEvaluacion.setBackground(new Color(216, 191, 216));
		txtTituloEvaluacion.setBounds(131, 271, 222, 24);
		panelEvaluaciones.add(txtTituloEvaluacion);

		JButton btnGuardarEvaluacion = new JButton("Guardar Evaluación");
		btnGuardarEvaluacion.setForeground(Color.WHITE);
		btnGuardarEvaluacion.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardarEvaluacion.setBackground(new Color(128, 0, 255));
		btnGuardarEvaluacion.setBounds(337, 421, 185, 57);
		panelEvaluaciones.add(btnGuardarEvaluacion);
		btnGuardarEvaluacion.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            java.util.Date fecha = dChooserEvaluacion.getDate();
		            String tipo = comboBox_1.getSelectedItem().toString();
		            String descripcion = txtrIngreseLosContenidos_1.getText();
		            String titulo = txtTituloEvaluacion.getText();

		            // Crear objeto Evaluacion
		            Evaluacion evaluacion = new Evaluacion();
		            evaluacion.setCurso_id(Curso.getId());
		            evaluacion.setNombre_evaluacion(titulo);
		            evaluacion.setFecha_evaluacion(fecha);
		            evaluacion.setTipo_evaluacion(tipo);
		            evaluacion.setDescripcion(descripcion);

		            // Enviar al servicio
		            boolean exito = EvaluacionService.guardarEvaluacion(evaluacion);
		            if (exito) {
		                JOptionPane.showMessageDialog(null, "Evaluación guardada con éxito");

		                dChooserEvaluacion.setDate(null);
		                comboBox_1.setSelectedIndex(0);
		                txtrIngreseLosContenidos_1.setText("");
		                txtTituloEvaluacion.setText("");
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al guardar la evaluación", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al guardar evaluación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		
		JLabel lblTtulo = new JLabel("Título:");
		lblTtulo.setFont(new Font("Arial", Font.BOLD, 14));
		lblTtulo.setBounds(78, 278, 47, 14);
		panelEvaluaciones.add(lblTtulo);
		
		JPanel panelEvaluar = new JPanel();
		panelEvaluar.setForeground(new Color(255, 255, 255));
		tabbedPane_1.addTab("Evaluar", null, panelEvaluar, null);
		panelEvaluar.setLayout(null);

		// Obtener evaluaciones del curso seleccionado
		int cursoId = Curso.getId(); // suponiendo que Curso.getId() devuelve el ID del curso actual
		List<Evaluacion> evaluaciones = EvaluacionService.listarEvaluacionesPorCurso(cursoId);

		// Crear un DefaultListModel y llenarlo con los nombres de las evaluaciones
		DefaultListModel<String> modelo = new DefaultListModel<>();
		for (Evaluacion ev : evaluaciones) {
		    modelo.addElement(ev.getNombre_evaluacion());
		}

		// Crear el JList usando el modelo dinámico
		JList<String> listEvaluaciones = new JList<>(modelo);
		listEvaluaciones.setBackground(new Color(216, 191, 216));
		listEvaluaciones.setFont(new Font("Segoe UI", Font.BOLD, 14));
		listEvaluaciones.setBounds(30, 62, 231, 279);
		panelEvaluar.add(listEvaluaciones);

		
		JLabel lblNewLabel_4 = new JLabel("Actividades creadas");
		lblNewLabel_4.setForeground(new Color(128, 0, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setBounds(54, 26, 176, 25);
		panelEvaluar.add(lblNewLabel_4);
		
		JScrollPane scrollPaneEstudiantes_1 = new JScrollPane();
		scrollPaneEstudiantes_1.setBounds(390, 28, 537, 416);
		panelEvaluar.add(scrollPaneEstudiantes_1);
		
		tableEvaluar = new JTable();
		tableEvaluar.setRowHeight(32);
		tableEvaluar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableEvaluar.setBackground(new Color(216, 191, 216));
		tableEvaluar.setModel(new DefaultTableModel(
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
				"Estudiante", "Nota"
			}
		));
		tableEvaluar.getColumnModel().getColumn(1).setMaxWidth(220);
		scrollPaneEstudiantes_1.setViewportView(tableEvaluar);
		
		JLabel lblNewLabel_1 = new JLabel("Descripción de la evaluación:");
		lblNewLabel_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_1.setBounds(65, 352, 154, 14);
		panelEvaluar.add(lblNewLabel_1);
		
		JTextArea textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBackground(new Color(211, 211, 211));
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setBounds(42, 367, 204, 97);
		panelEvaluar.add(textAreaDescripcion);
		JButton btnCargarEvaluacion = new JButton("Cargar ");
		btnCargarEvaluacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String evaluacionSeleccionada = listEvaluaciones.getSelectedValue();
				if (evaluacionSeleccionada == null) {
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una evaluación de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}

				// Obtener la evaluación completa por su nombre y curso
				Evaluacion ev = EvaluacionService.obtenerEvaluacionPorNombreYCursos(evaluacionSeleccionada, cursoId);
				if (ev == null) {
				    JOptionPane.showMessageDialog(null, "No se encontró la evaluación seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				// Mostrar la descripción de la evaluación
				textAreaDescripcion.setText(ev.getDescripcion());

				// Cargar los estudiantes del curso
				estudiantesDelCurso = usuarioCursoDAO.obtenerEstudiantesActivosPorCurso(cursoId);

				// Crear el modelo para la tabla
				DefaultTableModel model = (DefaultTableModel) tableEvaluar.getModel();
				model.setRowCount(0); // Limpiar filas existentes

				// Llenar la tabla con los estudiantes y sus calificaciones para la evaluación seleccionada
				for (Usuario estudiante : estudiantesDelCurso) {
				    CalificacionDetalle calificacion = calificacionDAO.obtenerCalificacionPorEstudianteYEvaluacion(estudiante.getId(), ev.getId());
				    Float nota = calificacion != null ? calificacion.getNota() : null;
				    model.addRow(new Object[] {
				        estudiante.getNombre() + " " + estudiante.getApellido(),
				        nota != null ? nota : ""
				    });
				}}
		});
		btnCargarEvaluacion.setBackground(new Color(128, 0, 255));
		btnCargarEvaluacion.setForeground(new Color(255, 255, 255));
		btnCargarEvaluacion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCargarEvaluacion.setBounds(30, 475, 111, 39);
		panelEvaluar.add(btnCargarEvaluacion);
		
		JButton btnEliminarEvaluacion = new JButton("Eliminar");
		btnEliminarEvaluacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
									    String evaluacionSeleccionada = listEvaluaciones.getSelectedValue();
				    if (evaluacionSeleccionada == null) {
				        JOptionPane.showMessageDialog(null, "Por favor, seleccione una evaluación de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    // Obtener la evaluación completa por su nombre y curso
				    Evaluacion ev = EvaluacionService.obtenerEvaluacionPorNombreYCursos(evaluacionSeleccionada, cursoId);
				    if (ev == null) {
				        JOptionPane.showMessageDialog(null, "No se encontró la evaluación seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
				        return;
				    }

				    // Confirmar eliminación
				    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar la evaluación \"" + ev.getNombre_evaluacion() + "\"?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
				    if (confirm != JOptionPane.YES_OPTION) {
				        return; // Si no confirma, salir del método
				    }

				    // Eliminar la evaluación
				    boolean exito = EvaluacionService.eliminarEvaluacion(ev.getId());
				    if (exito) {
				        JOptionPane.showMessageDialog(null, "Evaluación eliminada con éxito.");

				        // Actualizar la lista y limpiar la tabla
				        modelo.removeElement(evaluacionSeleccionada);
				        DefaultTableModel model = (DefaultTableModel) tableEvaluar.getModel();
				        model.setRowCount(0); // Limpiar filas existentes
				        textAreaDescripcion.setText("");
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al eliminar la evaluación.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(null, "Error al eliminar evaluación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
			}
		});
		btnEliminarEvaluacion.setForeground(Color.WHITE);
		btnEliminarEvaluacion.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnEliminarEvaluacion.setBackground(new Color(169, 169, 169));
		btnEliminarEvaluacion.setBounds(150, 475, 111, 39);
		panelEvaluar.add(btnEliminarEvaluacion);
		JButton btnGuardarEvaluacionNota = new JButton("Guardar");

		btnGuardarEvaluacionNota.setForeground(new Color(255, 255, 255));
		btnGuardarEvaluacionNota.setBackground(new Color(128, 0, 255));
		btnGuardarEvaluacionNota.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardarEvaluacionNota.setBounds(600, 462, 120, 39);
		panelEvaluar.add(btnGuardarEvaluacionNota);

		btnGuardarEvaluacionNota.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Verificamos que haya una evaluación seleccionada
		            String evaluacionSeleccionada = listEvaluaciones.getSelectedValue();
		            if (evaluacionSeleccionada == null) {
		                JOptionPane.showMessageDialog(null, "Seleccione una evaluación primero.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            Evaluacion ev = EvaluacionService.obtenerEvaluacionPorNombreYCursos(evaluacionSeleccionada, cursoId);
		            if (ev == null) {
		                JOptionPane.showMessageDialog(null, "No se encontró la evaluación seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Recorremos la tabla y guardamos cada nota
		            DefaultTableModel model = (DefaultTableModel) tableEvaluar.getModel();

		            for (int i = 0; i < model.getRowCount(); i++) {
		                String nombreCompleto = (String) model.getValueAt(i, 0);
		                Object notaObj = model.getValueAt(i, 1);

		                if (notaObj == null || notaObj.toString().isEmpty()) {
		                    continue; // ignorar si no hay nota
		                }

		                float nota;
		                try {
		                    nota = Float.parseFloat(notaObj.toString());
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(null, "Nota inválida en la fila " + (i + 1), "Error", JOptionPane.ERROR_MESSAGE);
		                    continue;
		                }

		                // Buscamos el estudiante por nombre completo
		                Usuario estudiante = estudiantesDelCurso.stream()
		                        .filter(u -> (u.getNombre() + " " + u.getApellido()).equals(nombreCompleto))
		                        .findFirst()
		                        .orElse(null);

		                if (estudiante != null) {
		                    // Guardamos la calificación
		                	boolean exito = CalificacionService.guardarCalificacion(estudiante.getId(), ev.getId(), nota);
		                	System.out.println("Guardando nota para: " + estudiante.getNombre() + " " + estudiante.getApellido() + " - Nota: " + nota);
		                }
		            }

		            JOptionPane.showMessageDialog(null, "Notas guardadas correctamente.");

		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error al guardar notas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});

		JPanel tabPlanificaciones = new JPanel();
		tabbedPane.addTab("Planificaciones", null, tabPlanificaciones, null);
		tabPlanificaciones.setLayout(null);
		
		JPanel panelPlanificaciones = new JPanel();
		panelPlanificaciones.setBounds(0, 0, 993, 563);
		tabPlanificaciones.add(panelPlanificaciones);
		panelPlanificaciones.setLayout(null);
		
		JLabel lblSubirPlanif = new JLabel("Subir planificación anual (Link público de drive)");
		lblSubirPlanif.setFont(new Font("Arial", Font.BOLD, 14));
		lblSubirPlanif.setBounds(301, 159, 347, 24);
		panelPlanificaciones.add(lblSubirPlanif);
		
		textPlanifAnual = new JTextField();
		planificacion = PlanificacionService.obtenerPlanificacion(Curso.getId(), Docente.getId());
		textPlanifAnual.setText(planificacion.getHipervinculo());
		textPlanifAnual.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textPlanifAnual.setBounds(229, 190, 477, 29);
		panelPlanificaciones.add(textPlanifAnual);
		textPlanifAnual.setColumns(10);
		
		
		JButton btnGuardarPlanif = new JButton("Guardar Planificación");
		btnGuardarPlanif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Planificacion plani = new Planificacion();
				String link = textPlanifAnual.getText();
				plani.setCurso_id(Curso.getId());
				plani.setHipervinculo(link);
				plani.setAutor_id(Docente.getId());
				java.util.Date fechaActual = new java.util.Date();
				plani.setFecha(fechaActual);
				if (link.isEmpty()) {
				    JOptionPane.showMessageDialog(null, "Por favor, ingrese un link válido.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}

				try {
				    Boolean exito = PlanificacionService.guardarPlanificacionAnual(plani);
				    if (exito) {
				        JOptionPane.showMessageDialog(null, "Planificación guardada con éxito.");
				        textPlanifAnual.setText("");
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al guardar la planificación.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGuardarPlanif.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnGuardarPlanif.setForeground(new Color(255, 255, 255));
		btnGuardarPlanif.setBackground(new Color(128, 0, 255));
		btnGuardarPlanif.setBounds(376, 243, 185, 50);
		panelPlanificaciones.add(btnGuardarPlanif);
		
		table = new JTable();
		table.setBounds(313, 328, 1, 1);
		panelPlanificaciones.add(table);
		
		JLabel lblPlanificaciones = new JLabel("Planificaciones");
		lblPlanificaciones.setForeground(new Color(128, 0, 255));
		lblPlanificaciones.setFont(new Font("Arial", Font.BOLD, 24));
		lblPlanificaciones.setBounds(389, 12, 185, 24);
		panelPlanificaciones.add(lblPlanificaciones);
		
		JLabel lblIconoPlano = new JLabel("");
		lblIconoPlano.setIcon(new ImageIcon(VentanaDocentes.class.getResource("/resources/Plano.png")));
		lblIconoPlano.setBounds(425, 27, 156, 123);
		panelPlanificaciones.add(lblIconoPlano);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(841, 335, -807, -10);
		panelPlanificaciones.add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(128, 0, 255));
		separator_2.setBounds(-160, 327, 1151, 13);
		panelPlanificaciones.add(separator_2);
		
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
		
		dChooserFechaCargada = new JDateChooser();
		BorderLayout borderLayout = (BorderLayout) dChooserFechaCargada.getLayout();
		borderLayout.setHgap(20);
		panelSupPasarLista.add(dChooserFechaCargada);
		
		JButton btnNewButton_1 = new JButton("Cargar lista\r\n");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(128, 0, 255));
		panelSupPasarLista.add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 0));
		panelSupPasarLista.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Presente");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panelSupPasarLista.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 0));
		panelSupPasarLista.add(panel_1);
		
		JLabel lblNewLabel_3 = new JLabel("No presente");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		panelSupPasarLista.add(lblNewLabel_3);
		
		JPanel panelInfPasarLista = new JPanel();
		panelPasarLista.add(panelInfPasarLista, BorderLayout.SOUTH);
		
		JLabel FechaLista = new JLabel("Fecha:");
		FechaLista.setFont(new Font("Arial", Font.BOLD, 14));
		panelInfPasarLista.add(FechaLista);
		
		dChooserFechaGuardada = new JDateChooser();
		panelInfPasarLista.add(dChooserFechaGuardada);
		
		JButton btnGuardarLista = new JButton("Guardar lista");
		btnGuardarLista.setForeground(new Color(255, 255, 255));
		btnGuardarLista.setBackground(new Color(128, 0, 255));
		btnGuardarLista.setFont(new Font("Arial", Font.BOLD, 14));
		panelInfPasarLista.add(btnGuardarLista);
		
		//Acción GUARDAR LISTA
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelPasarLista.add(scrollPane_1, BorderLayout.CENTER);
		
		// PESTAÑA DE PASAR LISTA //
		tableLista = new JTable();
		tableLista.setRowHeight(24);
		tableLista.setBackground(new Color(219, 183, 255));
		tableLista.setFont(new Font("Arial", Font.BOLD, 14));
		DefaultTableModel modelo1 = new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Estudiante", "Condición" }
			);
		tableLista.setModel(modelo1);
		tableLista.getColumnModel().getColumn(1).setCellRenderer(new AsistenciaRenderer());
		tableLista.getColumnModel().getColumn(1).setCellEditor(new AsistenciaEditor());
		List<Usuario> estudiantes = usuarioCursoDAO.obtenerEstudiantesActivosPorCurso(Curso.getId());
		for (Usuario est : estudiantes) {
		    modelo1.addRow(new Object[] { est.getNombre() + " " + est.getApellido(), null });
		}
		// Dentro del constructor, al crear la tabla de pasar lista:
		estudiantesDelCurso = usuarioCursoDAO.obtenerEstudiantesActivosPorCurso(Curso.getId());
		
		btnGuardarLista.addActionListener(e -> guardarLista());
		btnNewButton_1.addActionListener(e -> cargarLista()); // btnNewButton_1 es el botón "Cargar lista"
		
		
		
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
		lblFechaDesarrollo.setBounds(68, 134, 47, 14);
		panelDesarrollo.add(lblFechaDesarrollo);
		
		JLabel lblDesDesarrollo = new JLabel("Desarrollo:");
		lblDesDesarrollo.setFont(new Font("Arial", Font.BOLD, 14));
		lblDesDesarrollo.setBounds(38, 183, 77, 20);
		panelDesarrollo.add(lblDesDesarrollo);
		
		JTextArea textDesarrollo = new JTextArea();
		textDesarrollo.setBackground(new Color(216, 191, 216));
		textDesarrollo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textDesarrollo.setBounds(125, 182, 450, 163);
		panelDesarrollo.add(textDesarrollo);
		
		JDateChooser dChooserDesarrollo = new JDateChooser();
		dChooserDesarrollo.setBackground(new Color(216, 191, 216));
		dChooserDesarrollo.setBounds(125, 130, 162, 27);
		panelDesarrollo.add(dChooserDesarrollo);
		
		
		DefaultListModel<String> modelDesarrollos = new DefaultListModel<>();
		JList<String> listDesarrollos = new JList<>(modelDesarrollos);
		listDesarrollos.setLayoutOrientation(JList.VERTICAL_WRAP);
		listDesarrollos.setForeground(new Color(128, 0, 255));
		listDesarrollos.setFont(new Font("Arial", Font.BOLD, 18));
		listDesarrollos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255)));
		listDesarrollos.setBackground(new Color(230, 204, 255));
		listDesarrollos.setBounds(773, 125, 250, 304);
		panelDesarrollo.add(listDesarrollos);

		// Cargar los desarrollos del curso actual
		List<Desarrollo> desarrollos = DesarrolloService.obtenerDesarrollosPorCurso(Curso.getId());
		modelDesarrollos.clear();
		for (Desarrollo d : desarrollos) {
		    String texto = d.getFecha().toString();
		    modelDesarrollos.addElement(texto);
		}

		
		JButton btnGuardarDesarrollo = new JButton("Guardar Desarrollo");
		btnGuardarDesarrollo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Desarrollo desarrollo = new Desarrollo();
				java.util.Date fechaSeleccionada = dChooserDesarrollo.getDate();
				if (fechaSeleccionada == null) {
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				java.sql.Date fechaClase = new java.sql.Date(fechaSeleccionada.getTime());
				desarrollo.setCurso_id(Curso.getId());
				desarrollo.setFecha(fechaClase);
				desarrollo.setContenido(textDesarrollo.getText());
				desarrollo.setAutor_id(Docente.getId());
	
				if (textDesarrollo.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(null, "El contenido del desarrollo no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				try {
				    Boolean exito = DesarrolloService.guardarDesarrollo(desarrollo);
				    if (exito) {
				        JOptionPane.showMessageDialog(null, "Desarrollo guardado con éxito.");
				        List<Desarrollo> desarrollos = DesarrolloService.obtenerDesarrollosPorCurso(Curso.getId());
						modelDesarrollos.clear();
						for (Desarrollo d : desarrollos) {
						    String texto = d.getFecha().toString();
						    modelDesarrollos.addElement(texto);
						}
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al guardar el desarrollo.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnGuardarDesarrollo.setForeground(new Color(255, 255, 255));
		btnGuardarDesarrollo.setBackground(new Color(0, 64, 0));
		btnGuardarDesarrollo.setFont(new Font("Arial", Font.BOLD, 14));
		btnGuardarDesarrollo.setBounds(255, 449, 187, 37);
		panelDesarrollo.add(btnGuardarDesarrollo);
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desarrolloSeleccionado = (String) listDesarrollos.getSelectedValue();
				if (desarrolloSeleccionado == null) {
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione un desarrollo de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				try {
					Boolean exito = DesarrolloService.eliminarDesarrolloPorFechaYCurso(desarrolloSeleccionado, Curso.getId());
				    if (exito) {
				        JOptionPane.showMessageDialog(null, "Desarrollo eliminado con éxito.");
				        List<Desarrollo> desarrollos = DesarrolloService.obtenerDesarrollosPorCurso(Curso.getId());
						modelDesarrollos.clear();
						for (Desarrollo d : desarrollos) {
						    String texto = d.getFecha().toString();
						    modelDesarrollos.addElement(texto);
						}

				    } else {
				        JOptionPane.showMessageDialog(null, "Error al eliminar el desarrollo.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(null, "Error al eliminar desarrollo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEliminar.setBackground(new Color(64, 0, 0));
		btnEliminar.setBounds(452, 449, 187, 37);
		panelDesarrollo.add(btnEliminar);
		JButton btnVerHistorial = new JButton("Ver contenido");
		btnVerHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desarrolloSeleccionado = (String) listDesarrollos.getSelectedValue();
				if (desarrolloSeleccionado == null) {
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione un desarrollo de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
				    return;
				}
				try {
					Desarrollo d = DesarrolloService.obtenerDesarrolloPorFechaYCurso(desarrolloSeleccionado, Curso.getId());
				    if (d != null) {
				        textDesarrollo.setText(d.getContenido());
				        dChooserDesarrollo.setDate(d.getFecha());
				    } else {
				        JOptionPane.showMessageDialog(null, "No se encontró el desarrollo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(null, "Error al obtener desarrollo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}});
		btnVerHistorial.setFont(new Font("Arial", Font.BOLD, 14));
		btnVerHistorial.setForeground(new Color(255, 255, 255));
		btnVerHistorial.setBackground(new Color(128, 0, 255));
		btnVerHistorial.setBounds(773, 449, 141, 37);
		panelDesarrollo.add(btnVerHistorial);
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
                    } else if ("Parcial1".equalsIgnoreCase(tipo)) {
                        resumen.setPrimerParcial(nota);
                    } else if ("Parcial2".equalsIgnoreCase(tipo)) {
                        resumen.setSegundoParcial(nota);
                    }
                }

                // Consultar faltas reales
                int faltas = AsistenciaDAO.obtenerFaltasPorEstudianteYCurso(estudiante.getUsuarioId(), Curso.getNombre_curso());
                resumen.setFaltas(faltas);

                // Añadir fila a la tabla
                model.addRow(new Object[]{
                    resumen.getDocumento(),
                    resumen.getNombre(),
                    resumen.getApellido(),
                    resumen.getActividadesStr(),
                    resumen.getPrimerParcial(),
                    resumen.getSegundoParcial(),
                    resumen.getPromedio(),
                    resumen.getFaltas()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes: " + e.getMessage());
        }
    }
	
	private void guardarLista() {
	    // Obtenemos la fecha seleccionada en el JDateChooser
	    java.util.Date fechaSeleccionada = dChooserFechaGuardada.getDate();

	    if (fechaSeleccionada == null) {
	        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
	        return;
	    }

	    // Convertimos java.util.Date -> java.sql.Date
	    java.sql.Date fechaClase = new java.sql.Date(fechaSeleccionada.getTime());

	    try {
	        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
	        try (Connection conn = Conexion.conectar()) {
	            AsistenciaDAO asistenciaDAO = new AsistenciaDAO(conn);

	            for (int i = 0; i < model.getRowCount(); i++) {
	                Usuario estudiante = estudiantesDelCurso.get(i);
	                String estado = (String) model.getValueAt(i, 1);

	                Asistencia asistencia = new Asistencia();
	                asistencia.setUsuarioId(estudiante.getUsuarioId());
	                asistencia.setCursoId(Curso.getId());
	                asistencia.setFechaClase(fechaClase);
	                asistencia.setEstadoAsistencia(estado);

	                asistenciaDAO.guardarAsistencia(asistencia);
	            }
	        }
	        JOptionPane.showMessageDialog(this, "Lista guardada correctamente.");
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al guardar la lista: " + ex.getMessage());
	    }
	}

	private void cargarLista() {
	    // Obtenemos la fecha seleccionada en el JDateChooser
	    java.util.Date fechaSeleccionada = dChooserFechaCargada.getDate();
	    if (fechaSeleccionada == null) {
	        JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.");
	        return;
	    }

	    // Convertimos java.util.Date -> java.sql.Date
	    java.sql.Date fechaClase = new java.sql.Date(fechaSeleccionada.getTime());

	    try (Connection conn = Conexion.conectar()) {
	        AsistenciaDAO asistenciaDAO = new AsistenciaDAO(conn);
	        List<Asistencia> asistencias = asistenciaDAO.obtenerAsistenciaPorCursoYFecha(Curso.getId(), fechaClase);

	        DefaultTableModel model = (DefaultTableModel) tableLista.getModel();
	        for (int i = 0; i < model.getRowCount(); i++) {
	            Usuario estudiante = estudiantesDelCurso.get(i);
	            String estado = null;

	            for (Asistencia a : asistencias) {
	                if (a.getUsuarioId() == estudiante.getUsuarioId()) {
	                    estado = a.getEstadoAsistencia();
	                    break;
	                }
	            }
	            model.setValueAt(estado, i, 1);
	        }

	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(this, "Error al cargar la lista: " + ex.getMessage());
	    }
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
