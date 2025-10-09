package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import main.dao.CursoDAO;
import main.dao.UsuarioDAO;
import main.model.Curso;
import main.model.Rama;
import main.model.Usuario;
import main.services.CursoService;
import main.services.UsuarioService;
import main.services.RamaService;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;


public class VentanaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableEstudiantesVisado;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private JTextField textCiCrearusuario;
	private JTextField textNombreCrearusuario;
	private JTextField textApellidoCrearusuario;
	private JTextField textCorreoCrearusuario;
	private JTextField textCiDarbaja;
	private JTextField textCiEditarusuario;
	private JTextField textNombreEditarusuario;
	private JTextField textApellidoEditarusuario;
	private JTextField textCorreoEditarusuario;
	private static Usuario Administrador;
	private int usuarioIdEditar = -1;
	private int rolIdEditar = 0;
	private JPasswordField textPasswordCrearUsuario;
	private JPasswordField textPasswardEditarUsuario;
	private JTextField txtVacante;
	private JTable tableEstudiantesCurso;
	private JTextField txtCiEstudianteVinculado;
	private JTextField txtCiDocenteVinculado;
	private JDateChooser dChooserFechaNacAlta;
	private JDateChooser dChooserFechaNacEdit;
	private JTable table;
	private JTextField txtDocenteVincGestion;
	private JTextField textField_1;
	private JTextField txtCiGestion;
	private JTextField txtNombreCursoAlta;
	private JTable table_1;
	private JTable tableCursos;
	private JLabel txtDescripAltaCurso;
	private JComboBox cmbEstadoCurso;
	private JLabel txtDescripcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin(Administrador);
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
	public VentanaAdmin(Usuario Administrador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		setMinimumSize(new Dimension(1024, 649));
		setSize(new Dimension(1082, 699));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1024, 640));
		contentPane.setMaximumSize(new Dimension(2222, 22222));
		contentPane.setMinimumSize(new Dimension(1024, 640));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null); // CENTRAR LA VENTANA PADRE
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		tabbedPane.setBackground(new Color(167, 79, 255));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel tabGestion = new JPanel();
		tabbedPane.addTab("Gestión", null, tabGestion, null);
		tabGestion.setLayout(null);

		JTabbedPane SubtabbedPane = new JTabbedPane(JTabbedPane.TOP);
		SubtabbedPane.setBackground(new Color(210, 166, 255));
		SubtabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));
		SubtabbedPane.setBounds(10, 11, 999, 586);
		tabGestion.add(SubtabbedPane);

		JPanel tabCrearusu = new JPanel();
		SubtabbedPane.addTab("Crear usuario", null, tabCrearusu, null);
		tabCrearusu.setLayout(null);

		JLabel lblLogoLibAdm_1 = new JLabel("New label");
		lblLogoLibAdm_1.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		lblLogoLibAdm_1.setBounds(555, 1, 151, 106);
		tabCrearusu.add(lblLogoLibAdm_1);

		JLabel lblTituloGestion = new JLabel("Gestión de usuarios");
		lblTituloGestion.setForeground(new Color(128, 0, 255));
		lblTituloGestion.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTituloGestion.setBounds(270, 24, 304, 33);
		tabCrearusu.add(lblTituloGestion);

		JLabel lblSubtituloCrearusuario = new JLabel("Crear usuario");
		lblSubtituloCrearusuario.setForeground(new Color(128, 0, 255));
		lblSubtituloCrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSubtituloCrearusuario.setBounds(358, 67, 136, 42);
		tabCrearusu.add(lblSubtituloCrearusuario);

		JSeparator sprTitl = new JSeparator();
		sprTitl.setOrientation(SwingConstants.VERTICAL);
		sprTitl.setForeground(new Color(128, 0, 255));
		sprTitl.setBounds(309, 52, 20, 40);
		tabCrearusu.add(sprTitl);

		JSeparator sprTitl2 = new JSeparator();
		sprTitl2.setForeground(new Color(128, 0, 255));
		sprTitl2.setBounds(308, 90, 46, 19);
		tabCrearusu.add(sprTitl2);

		JComboBox comboBoxRol = new JComboBox();
		comboBoxRol.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxRol.setBounds(219, 164, 241, 27);
		tabCrearusu.add(comboBoxRol);
		comboBoxRol.addItem("Administrador");
		comboBoxRol.addItem("Docente");
		comboBoxRol.addItem("Estudiante");

		textCiCrearusuario = new JTextField();
		textCiCrearusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textCiCrearusuario.setColumns(10);
		textCiCrearusuario.setBounds(219, 210, 241, 33);
		tabCrearusu.add(textCiCrearusuario);

		textNombreCrearusuario = new JTextField();
		textNombreCrearusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textNombreCrearusuario.setColumns(10);
		textNombreCrearusuario.setBounds(219, 272, 241, 33);
		tabCrearusu.add(textNombreCrearusuario);

		textApellidoCrearusuario = new JTextField();
		textApellidoCrearusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textApellidoCrearusuario.setColumns(10);
		textApellidoCrearusuario.setBounds(219, 320, 241, 33);
		tabCrearusu.add(textApellidoCrearusuario);

		textCorreoCrearusuario = new JTextField();
		textCorreoCrearusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textCorreoCrearusuario.setColumns(10);
		textCorreoCrearusuario.setBounds(219, 414, 241, 33);
		tabCrearusu.add(textCorreoCrearusuario);

		textPasswordCrearUsuario = new JPasswordField();
		textPasswordCrearUsuario.setBounds(219, 470, 241, 33);
		tabCrearusu.add(textPasswordCrearUsuario);
		JLabel lblAvatarCrearusuario = new JLabel("");
		lblAvatarCrearusuario
				.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/AvatarVioletAchede.png")));
		lblAvatarCrearusuario.setBounds(581, 177, 241, 230);
		tabCrearusu.add(lblAvatarCrearusuario);

		JLabel lblCICrearusuario = new JLabel("C.I:");
		lblCICrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCICrearusuario.setBounds(182, 214, 27, 22);
		tabCrearusu.add(lblCICrearusuario);

		JLabel lblNombreCrearusuario = new JLabel("Nombre/s:");
		lblNombreCrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNombreCrearusuario.setBounds(119, 272, 90, 22);
		tabCrearusu.add(lblNombreCrearusuario);

		JLabel lblApellidoCrearusuario = new JLabel("Apellido/s:");
		lblApellidoCrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblApellidoCrearusuario.setBounds(117, 324, 92, 22);
		tabCrearusu.add(lblApellidoCrearusuario);

		JLabel lblFechaNacimientoCrearUsuario = new JLabel("Fecha Nac.:");
		lblFechaNacimientoCrearUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblFechaNacimientoCrearUsuario.setBounds(108, 374, 101, 22);
		tabCrearusu.add(lblFechaNacimientoCrearUsuario);

		JLabel lblCorreoCrearusuario = new JLabel("Correo:");
		lblCorreoCrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCorreoCrearusuario.setBounds(147, 425, 62, 22);
		tabCrearusu.add(lblCorreoCrearusuario);

		JLabel lblPassCrearusuario = new JLabel("Contraseña:");
		lblPassCrearusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassCrearusuario.setBounds(108, 471, 101, 22);
		tabCrearusu.add(lblPassCrearusuario);

		JLabel lblRolCrearUsuario = new JLabel("Rol:");
		lblRolCrearUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblRolCrearUsuario.setBounds(163, 162, 46, 22);
		tabCrearusu.add(lblRolCrearUsuario);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario usuario = new Usuario();
					usuario.setDocumento(textCiCrearusuario.getText().trim());
					usuario.setNombre(textNombreCrearusuario.getText().trim());
					usuario.setApellido(textApellidoCrearusuario.getText().trim());
					java.util.Date fechaNacimiento = dChooserFechaNacAlta.getDate();
					if (fechaNacimiento == null) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Seleccione una fecha de nacimiento.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					Date fechaSQL = new Date(fechaNacimiento.getTime()); // convierte a java.sql.Date
					usuario.setFechaNacimiento(fechaSQL);

					usuario.setEmail(textCorreoCrearusuario.getText().trim());
					usuario.setContrasena(textPasswordCrearUsuario.getText().trim());
					usuario.setEstado(true); // Por defecto, el usuario se crea activo

					// Mapeo rol (nombre → número)
					String rolSeleccionado = comboBoxRol.getSelectedItem().toString();
					int rolId = 0;
					switch (rolSeleccionado) {
					case "Administrador":
						rolId = 1;
						break;
					case "Docente":
						rolId = 2;
						break;
					case "Estudiante":
						rolId = 3;
						break;
					}
					usuario.setRol_id(rolId);

					boolean exito = UsuarioService.registrarUsuario(usuario);

					if (exito) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Usuario creado correctamente.");
						textCiCrearusuario.setText("");
						textNombreCrearusuario.setText("");
						textApellidoCrearusuario.setText("");
						dChooserFechaNacAlta.setDate(null);
						textCorreoCrearusuario.setText("");
						textPasswordCrearUsuario.setText("");
					} else {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "No se pudo crear el usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnAgregar.setBackground(new Color(128, 0, 255));
		btnAgregar.setBounds(631, 467, 104, 33);
		tabCrearusu.add(btnAgregar);

		dChooserFechaNacAlta = new JDateChooser();
		dChooserFechaNacAlta.setBounds(219, 372, 241, 31);
		tabCrearusu.add(dChooserFechaNacAlta);

		JPanel tabDardebaja = new JPanel();
		SubtabbedPane.addTab("Dar de baja", null, tabDardebaja, null);
		tabDardebaja.setLayout(null);

		JLabel lblTituloGestion2 = new JLabel("Gestión de usuarios");
		lblTituloGestion2.setForeground(new Color(128, 0, 255));
		lblTituloGestion2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTituloGestion2.setBounds(323, 28, 287, 33);
		tabDardebaja.add(lblTituloGestion2);

		JLabel lblLogoLibAdm2 = new JLabel("New label");
		lblLogoLibAdm2.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		lblLogoLibAdm2.setBounds(601, 0, 151, 106);
		tabDardebaja.add(lblLogoLibAdm2);

		JSeparator sprTitl3 = new JSeparator();
		sprTitl3.setOrientation(SwingConstants.VERTICAL);
		sprTitl3.setForeground(new Color(128, 0, 255));
		sprTitl3.setBounds(368, 56, 20, 40);
		tabDardebaja.add(sprTitl3);

		JSeparator sprTitl4 = new JSeparator();
		sprTitl4.setForeground(new Color(128, 0, 255));
		sprTitl4.setBounds(368, 96, 60, 23);
		tabDardebaja.add(sprTitl4);

		JLabel lblSubtituloDarbaja = new JLabel("Dar de baja");
		lblSubtituloDarbaja.setForeground(new Color(128, 0, 255));
		lblSubtituloDarbaja.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSubtituloDarbaja.setBounds(431, 72, 136, 42);
		tabDardebaja.add(lblSubtituloDarbaja);

		JLabel lblCIDarbaja = new JLabel("C.I:");
		lblCIDarbaja.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblCIDarbaja.setBounds(297, 165, 33, 54);
		tabDardebaja.add(lblCIDarbaja);

		textCiDarbaja = new JTextField();
		textCiDarbaja.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		textCiDarbaja.setColumns(10);
		textCiDarbaja.setBounds(349, 162, 229, 58);
		tabDardebaja.add(textCiDarbaja);

		JLabel lblAvatarDarbaja = new JLabel("");
		lblAvatarDarbaja.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/AvatarVioletAchede.png")));
		lblAvatarDarbaja.setBounds(363, 243, 215, 200);
		tabDardebaja.add(lblAvatarDarbaja);

		JLabel lblNombreDinamicDarbaja = new JLabel("-");
		lblNombreDinamicDarbaja.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblNombreDinamicDarbaja.setBounds(395, 465, 342, 14);
		tabDardebaja.add(lblNombreDinamicDarbaja);

		JButton btnCargarDarbaja = new JButton("Cargar ");
		btnCargarDarbaja.setForeground(new Color(255, 255, 255));
		btnCargarDarbaja.setBackground(new Color(128, 0, 255));
		btnCargarDarbaja.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCargarDarbaja.setBounds(588, 184, 89, 23);
		tabDardebaja.add(btnCargarDarbaja);
		btnCargarDarbaja.addActionListener(e -> {

			try {
				String cedula = textCiDarbaja.getText();
				Usuario usuario = UsuarioService.buscarUsuarioPorCedula(cedula);
				if (usuario != null) {
					lblNombreDinamicDarbaja.setText(usuario.getNombre() + " " + usuario.getApellido());
					JOptionPane.showMessageDialog(this,
							"Usuario encontrado:\n" + "Cedula: " + usuario.getDocumento() + "\n" + "Nombre: "
									+ usuario.getNombre() + " " + usuario.getApellido() + "\n" + "Fecha de Nacimiento: "
									+ usuario.getFechaNacimiento() + "\n" + "Email: " + usuario.getEmail() + "\n"
									+ "Estado: " + (usuario.getEstado() ? "Activo" : "Inactivo") + "\n" + "Rol: "
									+ usuario.getRol_id());
				} else {
					JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
					lblNombreDinamicDarbaja.setText("-"); // resetear si no lo encuentra

				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JLabel lblNombreDarbaja = new JLabel("Nombre:");
		lblNombreDarbaja.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNombreDarbaja.setBounds(323, 462, 62, 14);
		tabDardebaja.add(lblNombreDarbaja);

		JButton btnDarbaja = new JButton("Dar de baja");
		btnDarbaja.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnDarbaja.setBackground(new Color(255, 81, 81));
		btnDarbaja.setBounds(601, 334, 136, 40);
		tabDardebaja.add(btnDarbaja);
		btnDarbaja.addActionListener(e -> {
			try {
				String cedula = textCiDarbaja.getText();
				Usuario usuario = UsuarioService.buscarUsuarioPorCedula(cedula);

				if (usuario != null) {
					int id = usuario.getUsuarioId();
					boolean exito = UsuarioService.eliminarUsuario(id);

					if (exito) {
						JOptionPane.showMessageDialog(this, "Usuario dado de baja correctamente.");
						textCiDarbaja.setText("");
					} else {
						JOptionPane.showMessageDialog(this, "No se pudo dar de baja al usuario.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "No se encontró un usuario con esa cédula.");
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JPanel tabListado = new JPanel();
		SubtabbedPane.addTab("Listado", null, tabListado, null);
		tabListado.setLayout(null);

		JLabel lblListadoDeUsu = new JLabel("Listado de Usuarios");
		lblListadoDeUsu.setBounds(363, 11, 240, 22);
		lblListadoDeUsu.setForeground(new Color(128, 0, 255));
		lblListadoDeUsu.setFont(new Font("Segoe UI", Font.BOLD, 26));
		tabListado.add(lblListadoDeUsu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 86, 773, 374);
		tabListado.add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(216, 191, 216));
		table.setRowHeight(30);
		table.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, { null, null, null }, { null, null, null }, { null, null, null },
						{ null, null, null }, { null, null, null }, { null, null, null }, },
				new String[] { "Nombre", "Correo", "C.I" }));
		scrollPane.setViewportView(table);

		JComboBox cmbFiltro1 = new JComboBox();
		cmbFiltro1.setModel(new DefaultComboBoxModel(new String[] { "Estudiante", "Docente", "Administrador" }));
		cmbFiltro1.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		cmbFiltro1.setBounds(162, 51, 112, 22);
		tabListado.add(cmbFiltro1);

		JLabel lblNewLabel_3 = new JLabel("Rol:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(133, 54, 33, 14);
		tabListado.add(lblNewLabel_3);

		JButton btnListarUsuarios = new JButton("Listar");
		btnListarUsuarios.setForeground(new Color(255, 255, 255));
		btnListarUsuarios.setBackground(new Color(153, 50, 204));
		btnListarUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnListarUsuarios.setBounds(284, 51, 71, 22);
		tabListado.add(btnListarUsuarios);
		btnListarUsuarios.addActionListener(e -> {
			try {
				String rolSeleccionado = cmbFiltro1.getSelectedItem().toString();
				int rolId = 0;
				switch (rolSeleccionado) {
				case "Administrador":
					rolId = 1;
					break;
				case "Docente":
					rolId = 2;
					break;
				case "Estudiante":
					rolId = 3;
					break;
				}

				List<Usuario> usuarios = UsuarioService.listarUsuariosPorRol(rolId);

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0); //

				for (Usuario usuario : usuarios) {
					model.addRow(new Object[] { usuario.getNombre() + " " + usuario.getApellido(), usuario.getEmail(),
							usuario.getDocumento() });
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		JPanel tabEditar = new JPanel();
		SubtabbedPane.addTab("Editar", null, tabEditar, null);
		tabEditar.setLayout(null);

		JLabel lblTitulo3 = new JLabel("Gestión de usuarios");
		lblTitulo3.setForeground(new Color(128, 0, 255));
		lblTitulo3.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblTitulo3.setBounds(284, 22, 287, 33);
		tabEditar.add(lblTitulo3);

		JLabel lblLogoLibAdm3 = new JLabel("New label");
		lblLogoLibAdm3.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		lblLogoLibAdm3.setBounds(571, -11, 151, 106);
		tabEditar.add(lblLogoLibAdm3);

		JSeparator spr5 = new JSeparator();
		spr5.setOrientation(SwingConstants.VERTICAL);
		spr5.setForeground(new Color(128, 0, 255));
		spr5.setBounds(320, 55, 20, 40);
		tabEditar.add(spr5);

		JSeparator spr6 = new JSeparator();
		spr6.setForeground(new Color(128, 0, 255));
		spr6.setBounds(319, 92, 79, 12);
		tabEditar.add(spr6);

		JLabel lblSubtituloEditarusuario = new JLabel("Editar usuario\r\n");
		lblSubtituloEditarusuario.setForeground(new Color(128, 0, 255));
		lblSubtituloEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSubtituloEditarusuario.setBackground(UIManager.getColor("Button.background"));
		lblSubtituloEditarusuario.setBounds(401, 76, 136, 27);
		tabEditar.add(lblSubtituloEditarusuario);

		JLabel lblCiEditarusuario = new JLabel("C.I:");
		lblCiEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCiEditarusuario.setBounds(249, 162, 32, 28);
		tabEditar.add(lblCiEditarusuario);

		JLabel lblNombreEditarusuario = new JLabel("Nombre/s:");
		lblNombreEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNombreEditarusuario.setBounds(191, 220, 90, 28);
		tabEditar.add(lblNombreEditarusuario);

		JLabel lblApellidoEditarusuario = new JLabel("Apellido/s:");
		lblApellidoEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblApellidoEditarusuario.setBounds(185, 275, 95, 28);
		tabEditar.add(lblApellidoEditarusuario);

		JLabel lblFechaNac = new JLabel("Fecha Nac:");
		lblFechaNac.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblFechaNac.setBounds(186, 324, 95, 22);
		tabEditar.add(lblFechaNac);

		JLabel lblPassEditarusuario = new JLabel("Contraseña:");
		lblPassEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblPassEditarusuario.setBounds(177, 375, 104, 28);
		tabEditar.add(lblPassEditarusuario);

		JLabel lblCorreoEditarusuario = new JLabel("Correo:");
		lblCorreoEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblCorreoEditarusuario.setBounds(212, 423, 62, 28);
		tabEditar.add(lblCorreoEditarusuario);

		JLabel lblEstadoEditarusuario = new JLabel("Estado:");
		lblEstadoEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEstadoEditarusuario.setBounds(212, 470, 80, 28);
		tabEditar.add(lblEstadoEditarusuario);

		textCiEditarusuario = new JTextField();
		textCiEditarusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textCiEditarusuario.setColumns(10);
		textCiEditarusuario.setBounds(284, 161, 241, 33);
		tabEditar.add(textCiEditarusuario);

		textNombreEditarusuario = new JTextField();
		textNombreEditarusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textNombreEditarusuario.setColumns(10);
		textNombreEditarusuario.setBounds(284, 220, 241, 33);
		tabEditar.add(textNombreEditarusuario);

		dChooserFechaNacEdit = new JDateChooser();
		dChooserFechaNacEdit.setBounds(284, 321, 241, 31); // ajusta las coordenadas según tu diseño
		tabEditar.add(dChooserFechaNacEdit);

		textApellidoEditarusuario = new JTextField();
		textApellidoEditarusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textApellidoEditarusuario.setColumns(10);
		textApellidoEditarusuario.setBounds(284, 272, 241, 33);
		tabEditar.add(textApellidoEditarusuario);

		textCorreoEditarusuario = new JTextField();
		textCorreoEditarusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textCorreoEditarusuario.setColumns(10);
		textCorreoEditarusuario.setBounds(284, 418, 241, 33);
		tabEditar.add(textCorreoEditarusuario);

		textPasswardEditarUsuario = new JPasswordField();
		textPasswardEditarUsuario.setBounds(284, 370, 241, 33);
		tabEditar.add(textPasswardEditarUsuario);

		JComboBox<String> comboEstadoEditarusuario = new JComboBox<>(new String[] { "true", "false" });
		comboEstadoEditarusuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		comboEstadoEditarusuario.setBounds(284, 470, 100, 33);
		tabEditar.add(comboEstadoEditarusuario);

		JLabel lblAvatarEditarusuario = new JLabel("");
		lblAvatarEditarusuario
				.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/AvatarVioletAchede.png")));
		lblAvatarEditarusuario.setBounds(601, 184, 215, 200);
		tabEditar.add(lblAvatarEditarusuario);

		JButton btnCargarEditarusuario = new JButton("Cargar");
		btnCargarEditarusuario.setBackground(new Color(128, 0, 255));
		btnCargarEditarusuario.setForeground(new Color(255, 255, 255));
		btnCargarEditarusuario.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnCargarEditarusuario.setBounds(527, 169, 89, 23);
		tabEditar.add(btnCargarEditarusuario);
		// agregar accion al boton cargar
		btnCargarEditarusuario.addActionListener(e -> {
			try {
				String cedula = textCiEditarusuario.getText();
				Usuario usuario = UsuarioService.buscarUsuarioPorCedula(cedula);

				if (usuario != null) {
					usuarioIdEditar = usuario.getUsuarioId();
					rolIdEditar = usuario.getRolId();
					textNombreEditarusuario.setText(usuario.getNombre());
					textApellidoEditarusuario.setText(usuario.getApellido());
					textCorreoEditarusuario.setText(usuario.getEmail());
					textPasswardEditarUsuario.setText(usuario.getContrasena());
					comboEstadoEditarusuario.setSelectedItem(String.valueOf(usuario.getEstado()));

				} else {
					JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
					textCiEditarusuario.setText("");
					textNombreEditarusuario.setText("");
					textApellidoEditarusuario.setText("");
					textCorreoEditarusuario.setText("");
					textPasswardEditarUsuario.setText("");
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnEditar.setBackground(new Color(128, 0, 255));
		btnEditar.setBounds(649, 431, 104, 33);
		tabEditar.add(btnEditar);
		btnEditar.addActionListener(e -> {
			try {
				if (usuarioIdEditar == -1) {
					JOptionPane.showMessageDialog(this, "Primero cargue un usuario para editar.");
					return;
				}

				Usuario usuario = new Usuario();
				usuario.setUsuarioId(usuarioIdEditar); // ID del usuario a editar
				usuario.setDocumento(textCiEditarusuario.getText().trim());
				usuario.setNombre(textNombreEditarusuario.getText().trim());
				usuario.setApellido(textApellidoEditarusuario.getText().trim());
				java.util.Date fechaNacimiento = dChooserFechaNacEdit.getDate();
				if (fechaNacimiento == null) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Seleccione una fecha de nacimiento.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Date fechaSQL = new Date(fechaNacimiento.getTime()); // convierte java.util.Date a java.sql.Date
				usuario.setFechaNacimiento(fechaSQL);

				usuario.setContrasena(textPasswardEditarUsuario.getText().trim());
				usuario.setEmail(textCorreoEditarusuario.getText().trim());
				usuario.setRol_id(rolIdEditar); // mantenemos el rol que ya tenía
				usuario.setEstado(Boolean.parseBoolean((String) comboEstadoEditarusuario.getSelectedItem()));

				boolean exito = UsuarioService.editarUsuario(usuario);

				if (exito) {
					JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
					usuarioIdEditar = -1; // reset
					rolIdEditar = 0; // reset
					textCiEditarusuario.setText("");
					textNombreEditarusuario.setText("");
					textApellidoEditarusuario.setText("");
					textPasswardEditarUsuario.setText("");
					textCorreoEditarusuario.setText("");
					dChooserFechaNacEdit.setDate(null); // limpia el selector de fecha
					comboEstadoEditarusuario.setSelectedIndex(0);

					dChooserFechaNacEdit.setBounds(284, 321, 241, 31);
					tabEditar.add(dChooserFechaNacEdit);

				} else {
					JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JPanel tabGestionCurs = new JPanel();
		tabbedPane.addTab("Cursos", null, tabGestionCurs, null);
		tabGestionCurs.setLayout(null);

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		tabbedPane_1.setBounds(0, 0, 1009, 597);
		tabGestionCurs.add(tabbedPane_1);

		JPanel tabGestionCursos = new JPanel();
		tabGestionCursos.setLayout(null);
		tabbedPane_1.addTab("Gestion", null, tabGestionCursos, null);

		JLabel lblGestinDeCursos = new JLabel("Gestión de Cursos");
		lblGestinDeCursos.setBounds(75, 11, 261, 41);
		lblGestinDeCursos.setForeground(new Color(128, 0, 255));
		lblGestinDeCursos.setFont(new Font("Segoe UI", Font.BOLD, 30));
		tabGestionCursos.add(lblGestinDeCursos);
		JComboBox cmbSubCurso = new JComboBox();
		cmbSubCurso.setModel(new DefaultComboBoxModel(new String[] {"Seleccione una rama"}));
		cmbSubCurso.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cmbSubCurso.setBounds(107, 139, 180, 33);
		tabGestionCursos.add(cmbSubCurso);

		JComboBox cmbCurso = new JComboBox();
		
		List<Rama> ramasCrear = RamaService.listarRamas();
		DefaultComboBoxModel<String> modelCrearRamas = new DefaultComboBoxModel<>();
		Map<String, Integer> mapaRamas = new HashMap<>();
		modelCrearRamas.addElement("Seleccione una rama");

		for (Rama rama : ramasCrear) {
		    modelCrearRamas.addElement(rama.getNombre_rama());
		    mapaRamas.put(rama.getNombre_rama(), rama.getId());
		}

		cmbCurso.setModel(modelCrearRamas);
		cmbCurso.setSelectedIndex(0);
		cmbCurso.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cmbCurso.setBounds(107, 95, 180, 33);
		tabGestionCursos.add(cmbCurso);

		cmbCurso.addActionListener(e -> {
		    try {
		        String ramaSeleccionada = cmbCurso.getSelectedItem().toString();
		        Integer idRama = mapaRamas.get(ramaSeleccionada);

		        // Pedir cursos a la BD (devuelve List<Rama>)
		        List<Rama> ramas = CursoService.listarCursosPorRama(idRama);

		        DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<>();
		        if (!ramas.isEmpty()) {
		            Rama rama = ramas.get(0); // solo viene la rama que seleccionaste
		            for (Curso curso : rama.getCursos()) {
		                modelCursos.addElement(curso.getNombre_curso());
		            }
		        }

		        cmbSubCurso.setModel(modelCursos);

		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		    }
		});

		JLabel lblSeleccionDeCurso = new JLabel("Seleccion de curso");
		lblSeleccionDeCurso.setForeground(new Color(128, 0, 255));
		lblSeleccionDeCurso.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSeleccionDeCurso.setBounds(131, 66, 141, 22);
		tabGestionCursos.add(lblSeleccionDeCurso);

		JLabel lblNewLabel = new JLabel("Docente vinculado");
		lblNewLabel.setForeground(new Color(128, 0, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel.setBounds(120, 192, 167, 14);
		tabGestionCursos.add(lblNewLabel);

		txtVacante = new JTextField();
		txtVacante.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtVacante.setText("Vacante");
		txtVacante.setBounds(107, 217, 180, 22);
		tabGestionCursos.add(txtVacante);
		txtVacante.setColumns(10);
		txtVacante.setEditable(false);

		JButton btnVincularDocente = new JButton("Vincular");
		btnVincularDocente.setForeground(new Color(255, 255, 255));
		btnVincularDocente.setBackground(new Color(0, 64, 0));
		btnVincularDocente.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnVincularDocente.setBounds(43, 522, 140, 33);
		tabGestionCursos.add(btnVincularDocente);
		btnVincularDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cedulaDocente = txtCiDocenteVinculado.getText().trim().toString();
					String cursoSeleccionado = cmbSubCurso.getSelectedItem().toString();


					Usuario docente = UsuarioService.buscarUsuarioPorCedula(cedulaDocente);
					if (docente == null) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "No se encontró un docente con esa cédula.",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					boolean exito = CursoService.vincularUsuarioaCurso(docente.getId(), cursoSeleccionado);

					if (exito) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Docente vinculado al curso correctamente.");
						txtCiDocenteVinculado.setText("");
						txtVacante.setText(docente.getNombre() + " " + docente.getApellido());
					} else {
						JOptionPane.showMessageDialog(VentanaAdmin.this,
								"No se pudo vincular al docente. Verifique la cédula y el curso.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "El curso seleccionado no es válido.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/AvatarVioletAchede.png")));
		lblNewLabel_1.setBounds(94, 250, 205, 200);
		tabGestionCursos.add(lblNewLabel_1);

		JButton btnDesvincularDocente = new JButton("Desvincular");
		btnDesvincularDocente.setForeground(Color.WHITE);
		btnDesvincularDocente.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnDesvincularDocente.setBackground(new Color(128, 0, 0));
		btnDesvincularDocente.setBounds(196, 522, 140, 33);
		tabGestionCursos.add(btnDesvincularDocente);

		JLabel lblEstudiantesEnEl = new JLabel("Estudiantes en el curso");
		lblEstudiantesEnEl.setForeground(new Color(128, 0, 255));
		lblEstudiantesEnEl.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblEstudiantesEnEl.setBounds(615, 26, 180, 22);
		tabGestionCursos.add(lblEstudiantesEnEl);

		JScrollPane scrollPaneEstudiantes_1 = new JScrollPane();
		scrollPaneEstudiantes_1.setBounds(435, 63, 537, 451);
		tabGestionCursos.add(scrollPaneEstudiantes_1);

		tableEstudiantesCurso = new JTable();
		tableEstudiantesCurso.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, },
				new String[] { "Estudiante", "C.I" }));
		tableEstudiantesCurso.setRowHeight(30);
		tableEstudiantesCurso.setFont(new Font("Segoe UI", Font.BOLD, 14));
		tableEstudiantesCurso.setBackground(new Color(255, 210, 255));
		scrollPaneEstudiantes_1.setViewportView(tableEstudiantesCurso);

		JButton btnVincularEstudiante = new JButton("Agregar");
		btnVincularEstudiante.setForeground(Color.WHITE);
		btnVincularEstudiante.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnVincularEstudiante.setBackground(new Color(0, 64, 0));
		btnVincularEstudiante.setBounds(684, 530, 140, 33);
		tabGestionCursos.add(btnVincularEstudiante);
		btnVincularEstudiante.addActionListener(e -> {
			try {
				String cedulaEstudiante = txtCiEstudianteVinculado.getText().trim();
				String cursoSeleccionado = cmbSubCurso.getSelectedItem().toString();

				// Buscar al estudiante por cédula
				Usuario estudiante = UsuarioService.buscarUsuarioPorCedula(cedulaEstudiante);
				if (estudiante == null) {
					JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Vincular estudiante al curso
				boolean exito = CursoService.vincularUsuarioaCurso(estudiante.getId(), cursoSeleccionado);

				if (exito) {
					JOptionPane.showMessageDialog(this, "Estudiante vinculado al curso correctamente.");
					txtCiEstudianteVinculado.setText("");

					// Listar estudiantes del curso
					List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cursoSeleccionado);
					DefaultTableModel model = (DefaultTableModel) tableEstudiantesCurso.getModel();
					model.setRowCount(0); // Limpiar tabla

					for (Usuario est : estudiantes) {
						model.addRow(new Object[] { est.getNombre() + " " + est.getApellido(), est.getDocumento() });
					}

				} else {
					JOptionPane.showMessageDialog(this,
							"No se pudo vincular al estudiante. Verifique la cédula y el curso.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton btnDesvincularEstudiante = new JButton("Desvincular");
		btnDesvincularEstudiante.setForeground(Color.WHITE);
		btnDesvincularEstudiante.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnDesvincularEstudiante.setBackground(new Color(128, 0, 0));
		btnDesvincularEstudiante.setBounds(833, 530, 140, 33);
		tabGestionCursos.add(btnDesvincularEstudiante);
		btnDesvincularEstudiante.addActionListener(e -> {
			try {
				String cedulaEstudiante = txtCiEstudianteVinculado.getText().trim();
				String cursoSeleccionado = cmbSubCurso.getSelectedItem().toString();

				// Buscar al estudiante por cédula
				Usuario estudiante = UsuarioService.buscarUsuarioPorCedula(cedulaEstudiante);
				if (estudiante == null) {
					JOptionPane.showMessageDialog(this, "Estudiante no encontrado.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Desvincular estudiante del curso
				boolean exito = CursoService.desvincularUsuarioDeCurso(estudiante.getId(), cursoSeleccionado);

				if (exito) {
					JOptionPane.showMessageDialog(this, "Estudiante desvinculado del curso correctamente.");
					txtCiEstudianteVinculado.setText("");

					// Listar estudiantes del curso
					List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cursoSeleccionado);
					DefaultTableModel model = (DefaultTableModel) tableEstudiantesCurso.getModel();
					model.setRowCount(0); // Limpiar tabla

					for (Usuario est : estudiantes) {
						model.addRow(new Object[] { est.getNombre() + " " + est.getApellido(), est.getDocumento() });
					}

				} else {
					JOptionPane.showMessageDialog(this,
							"No se pudo desvincular al estudiante. Verifique la cédula y el curso.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		txtCiEstudianteVinculado = new JTextField();
		txtCiEstudianteVinculado.setBounds(498, 534, 162, 26);
		tabGestionCursos.add(txtCiEstudianteVinculado);
		txtCiEstudianteVinculado.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("C.I:");
		lblNewLabel_2.setForeground(new Color(128, 0, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setBounds(462, 533, 30, 22);
		tabGestionCursos.add(lblNewLabel_2);

		JButton btnCargarCurso = new JButton("Cargar");
		btnCargarCurso.setForeground(Color.WHITE);
		btnCargarCurso.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnCargarCurso.setBackground(new Color(128, 0, 255));
		btnCargarCurso.setBounds(320, 143, 85, 22);
		tabGestionCursos.add(btnCargarCurso);
		btnCargarCurso.addActionListener(e -> {
			try {
				String cursoSeleccionado = cmbSubCurso.getSelectedItem().toString();

				// Obtener docente vinculado al curso
				Usuario docente = CursoService.obtenerDocenteVinculado(cursoSeleccionado);
				if (docente != null) {
					txtCiDocenteVinculado.setText(docente.getDocumento());
					txtVacante.setText(docente.getNombre() + " " + docente.getApellido());
				} else {
					txtCiDocenteVinculado.setText("");
					txtVacante.setText("Vacante");
				}

				// Listar estudiantes del curso
				List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cursoSeleccionado);
				DefaultTableModel model = (DefaultTableModel) tableEstudiantesCurso.getModel();
				model.setRowCount(0); // Limpiar tabla

				for (Usuario est : estudiantes) {
					model.addRow(new Object[] { est.getNombre() + " " + est.getApellido(), est.getDocumento() });
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		txtCiDocenteVinculado = new JTextField();
		txtCiDocenteVinculado.setText("1222333-4");
		txtCiDocenteVinculado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtCiDocenteVinculado.setColumns(10);
		txtCiDocenteVinculado.setBounds(119, 461, 180, 33);
		tabGestionCursos.add(txtCiDocenteVinculado);

		JLabel lblNewLabel_2_1 = new JLabel("C.I:");
		lblNewLabel_2_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(75, 464, 30, 22);
		tabGestionCursos.add(lblNewLabel_2_1);

		JComboBox cmbCursoGestion = new JComboBox();
		cmbCursoGestion.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cmbCursoGestion.setEnabled(false);
		cmbCursoGestion.setBounds(107, 125, 180, 33);
		tabGestionCursos.add(cmbCursoGestion);

		JPanel tabAltaCurso = new JPanel();
		tabbedPane_1.addTab("Crear", null, tabAltaCurso, null);
		tabAltaCurso.setLayout(null);

		JLabel lblAltaYBaja = new JLabel("Alta de Cursos");
		lblAltaYBaja.setBounds(382, 28, 213, 41);
		lblAltaYBaja.setForeground(new Color(128, 0, 255));
		lblAltaYBaja.setFont(new Font("Segoe UI", Font.BOLD, 30));
		tabAltaCurso.add(lblAltaYBaja);

		JComboBox cmbRamaAlta = new JComboBox();
		cmbRamaAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbRamaAlta.setBounds(382, 127, 297, 35);
		tabAltaCurso.add(cmbRamaAlta);

		List<Rama> ramas = RamaService.listarRamas();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		model.addElement("Seleccione una rama");

		for (Rama rama : ramas) {
		    model.addElement(rama.getNombre_rama());
		}

		cmbRamaAlta.setModel(model);
		cmbRamaAlta.setSelectedIndex(0);


		txtNombreCursoAlta = new JTextField();
		txtNombreCursoAlta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNombreCursoAlta.setColumns(10);
		txtNombreCursoAlta.setBounds(382, 184, 297, 35);
		txtNombreCursoAlta.setEnabled(false);
		tabAltaCurso.add(txtNombreCursoAlta);
		cmbRamaAlta.addActionListener(e -> {
		    String seleccion = cmbRamaAlta.getSelectedItem().toString();
		    if (seleccion != null && !seleccion.equals("Seleccione una rama")) {
		        txtNombreCursoAlta.setEnabled(true);  
		    } else {
		        txtNombreCursoAlta.setEnabled(false); }
		});		
		

		JComboBox cmbEstadoCurso = new JComboBox();
		cmbEstadoCurso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbEstadoCurso.setBounds(383, 230, 142, 35);
		tabAltaCurso.add(cmbEstadoCurso);
		cmbEstadoCurso.setModel(new DefaultComboBoxModel(new String[] { "true", "false" }));
		
		JButton btnCrearCurso = new JButton("Crear curso");
		btnCrearCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rama = cmbRamaAlta.getSelectedItem().toString();
					int ramaid = RamaService.obtenerRamaIdPorNombre(rama);
					String nombreCurso = txtNombreCursoAlta.getText().trim();
					String estadoStr = cmbEstadoCurso.getSelectedItem().toString();
					boolean estado = Boolean.parseBoolean(estadoStr);

					Curso nuevoCurso = new Curso();
					nuevoCurso.setRama_id(ramaid);
					nuevoCurso.setNombre_curso(nombreCurso);
					nuevoCurso.setEstado(estado);

					boolean exito = CursoService.crearCurso(nuevoCurso);

					if (exito) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Curso creado exitosamente.");
						txtNombreCursoAlta.setText("");
						cmbRamaAlta.setSelectedIndex(0);
						cmbEstadoCurso.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(VentanaAdmin.this,
								"No se pudo crear el curso. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCrearCurso.setForeground(Color.WHITE);
		btnCrearCurso.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnCrearCurso.setBackground(new Color(128, 0, 255));
		btnCrearCurso.setBounds(383, 276, 169, 33);
		tabAltaCurso.add(btnCrearCurso);

		JLabel lblNewLabel_4 = new JLabel("Rama a la que pertenece:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4.setForeground(new Color(128, 0, 255));
		lblNewLabel_4.setBounds(153, 132, 215, 20);
		tabAltaCurso.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Nombre del curso:");
		lblNewLabel_4_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(212, 190, 156, 20);
		tabAltaCurso.add(lblNewLabel_4_1);

		JLabel lblLogoLibAdm_1_1 = new JLabel("New label");
		lblLogoLibAdm_1_1.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		lblLogoLibAdm_1_1.setBounds(605, 4, 151, 106);
		tabAltaCurso.add(lblLogoLibAdm_1_1);


		JLabel lblNewLabel_4_1_1_1 = new JLabel("Estado:");
		lblNewLabel_4_1_1_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_1_1.setBounds(306, 235, 62, 20);
		tabAltaCurso.add(lblNewLabel_4_1_1_1);

		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Listar y editar", null, panel, null);
		panel.setLayout(null);

		JComboBox cmbCursoEditar = new JComboBox();
		cmbCursoEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cmbCursoEditar.setEnabled(false);
		cmbCursoEditar.setBounds(147, 160, 180, 33);
		panel.add(cmbCursoEditar);
		cmbCursoEditar.setModel(new DefaultComboBoxModel(new String[] { "Seleccione una rama" }));

		JComboBox cmbRamaEditarCurso = new JComboBox();
		cmbRamaEditarCurso.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cmbRamaEditarCurso.setBounds(147, 103, 180, 33);
		panel.add(cmbRamaEditarCurso);

		List<Rama> ramasEditar = RamaService.listarRamas();
		DefaultComboBoxModel<String> modelEditarRamas = new DefaultComboBoxModel<>();
		
		Map<String, Integer> mapaEditarRamas = new HashMap<>();
		modelEditarRamas.addElement("Seleccione una rama");
		

		for (Rama rama : ramasEditar) {
		    modelEditarRamas.addElement(rama.getNombre_rama());
		    mapaEditarRamas.put(rama.getNombre_rama(), rama.getId());
		}
		
		cmbRamaEditarCurso.setModel(modelEditarRamas);
		cmbRamaEditarCurso.setSelectedIndex(0);

		cmbRamaEditarCurso.addActionListener(e -> {
		    try {
		        String ramaSeleccionada = (String) cmbRamaEditarCurso.getSelectedItem();
		        if (ramaSeleccionada == null) return;

		        Integer idRama = mapaEditarRamas.get(ramaSeleccionada);
		        if (idRama == null) return;

		        // Pedir cursos a la BD (devuelve List<Rama>)
		        List<Rama> ramasEditarCurso = CursoService.listarCursosPorRama(idRama);

		        DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<>();
		        if (!ramasEditarCurso.isEmpty()) {
		            Rama rama = ramasEditarCurso.get(0);
		            for (Curso curso : rama.getCursos()) {
		                modelCursos.addElement(curso.getNombre_curso());
		            }
		        }

		        cmbCursoEditar.setModel(modelCursos);
		        cmbCursoEditar.setEnabled(true);

		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		    }
		});

		

		JLabel lblNewLabel_4_1_1_2 = new JLabel("Nuevo nombre:");
		lblNewLabel_4_1_1_2.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_1_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_1_2.setBounds(10, 216, 142, 41);
		panel.add(lblNewLabel_4_1_1_2);
		

		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Estado:");
		lblNewLabel_4_1_1_1_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_1_1_1.setBounds(73, 273, 62, 20);
		panel.add(lblNewLabel_4_1_1_1_1);

		JComboBox cmbEstado = new JComboBox();
		cmbEstado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbEstado.setBounds(145, 268, 142, 35);
		panel.add(cmbEstado);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] { "true", "false" }));

		JScrollPane scrollPaneEstudiantes_1_1 = new JScrollPane();
		scrollPaneEstudiantes_1_1.setBounds(445, 56, 537, 451);
		panel.add(scrollPaneEstudiantes_1_1);

		tableCursos = new JTable();
		tableCursos.setModel(new DefaultTableModel(
		        new Object[][] { { null, null, null }, { null, null, null } },
		        new String[] { "Nombre", "Rama", "Estudiantes (Cantidad)" }
		));
		tableCursos.getColumnModel().getColumn(2).setMaxWidth(50);
		scrollPaneEstudiantes_1_1.setViewportView(tableCursos);

		List<Curso> cursos = CursoService.listarTodosLosCursos();
		DefaultTableModel model1 = (DefaultTableModel) tableCursos.getModel();
		model1.setRowCount(0);

		for (Curso cur : cursos) {
		    List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cur.getNombre_curso());
		    model1.addRow(new Object[] { 
		        cur.getNombre_curso(),
		        CursoService.obtenerNombreRamaPorId(cur.getRama_id()),
		        estudiantes.size()
		    });
		}

		

		JLabel lblEditarOEliminar = new JLabel("Editar curso");
		lblEditarOEliminar.setForeground(new Color(128, 0, 255));
		lblEditarOEliminar.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblEditarOEliminar.setBounds(137, 23, 174, 41);
		panel.add(lblEditarOEliminar);

		JLabel lblListadoDeCursos = new JLabel("Listado de cursos");
		lblListadoDeCursos.setForeground(new Color(128, 0, 255));
		lblListadoDeCursos.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblListadoDeCursos.setBounds(598, 11, 209, 33);
		panel.add(lblListadoDeCursos);

		JLabel lblNewLabel_4_1_1_2_1 = new JLabel("Rama:");
		lblNewLabel_4_1_1_2_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_1_2_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_1_2_1.setBounds(84, 106, 53, 20);
		panel.add(lblNewLabel_4_1_1_2_1);

		JLabel lblNewLabel_4_1_1_2_1_1 = new JLabel("Curso:");
		lblNewLabel_4_1_1_2_1_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_1_2_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_1_2_1_1.setBounds(84, 160, 53, 20);
		panel.add(lblNewLabel_4_1_1_2_1_1);
		
		JTextArea txtEditarNombreCurso = new JTextArea();
		txtEditarNombreCurso.setBounds(147, 220, 225, 33);
		panel.add(txtEditarNombreCurso);
		//colocar que el texto del curso seleccionado aparezca en el text area
		cmbCursoEditar.addActionListener(e -> {
			String cursoSeleccionado = cmbCursoEditar.getSelectedItem().toString();
			if (cursoSeleccionado != null) {
				txtEditarNombreCurso.setText(cursoSeleccionado);
			}
		});

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rama = cmbRamaEditarCurso.getSelectedItem().toString();
					int ramaid = RamaService.obtenerRamaIdPorNombre(rama);
					String viejoNombreCurso = cmbCursoEditar.getSelectedItem().toString();
					String nombreCurso = txtEditarNombreCurso.getText().trim();
					String estadoStr = cmbEstado.getSelectedItem().toString();
					boolean estado = Boolean.parseBoolean(estadoStr);

					Curso cursoExistente = CursoService.buscarCursoPorNombre(viejoNombreCurso);
					cursoExistente.setRama_id(ramaid);
					cursoExistente.setEstado(estado);
					cursoExistente.setNombre_curso(nombreCurso);

					boolean exito = CursoService.actualizarCurso(cursoExistente);

					if (exito) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Curso actualizado exitosamente.");
						  List<Rama> ramasEditarCurso = CursoService.listarCursosPorRama(ramaid);
			                DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<>();
			                if (!ramasEditarCurso.isEmpty()) {
			                    Rama r = ramasEditarCurso.get(0);
			                    for (Curso c : r.getCursos()) {
			                        modelCursos.addElement(c.getNombre_curso());
			                    }
			                }
			                cmbCursoEditar.setModel(modelCursos);
			                cmbCursoEditar.setEnabled(true);

						// Actualizar la tabla de cursos
						tableCursos = new JTable();
						tableCursos.setModel(new DefaultTableModel(
						        new Object[][] { { null, null, null }, { null, null, null } },
						        new String[] { "Nombre", "Rama", "Estudiantes (Cantidad)" }
						));
						tableCursos.getColumnModel().getColumn(2).setMaxWidth(50);
						scrollPaneEstudiantes_1_1.setViewportView(tableCursos);

						List<Curso> cursos = CursoService.listarTodosLosCursos();
						DefaultTableModel model1 = (DefaultTableModel) tableCursos.getModel();
						model1.setRowCount(0);

						for (Curso cur : cursos) {
						    List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cur.getNombre_curso());
						    model1.addRow(new Object[] { 
						        cur.getNombre_curso(),
						        CursoService.obtenerNombreRamaPorId(cur.getRama_id()),
						        estudiantes.size()
						    });
						}


					} else {
						JOptionPane.showMessageDialog(VentanaAdmin.this,
								"No se pudo actualizar el curso. Intente nuevamente.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnActualizar.setBackground(new Color(128, 0, 255));
		btnActualizar.setBounds(185, 336, 142, 33);
		panel.add(btnActualizar);


		JPanel tabBajaCurso = new JPanel();
		tabbedPane_1.addTab("Dar de baja", null, tabBajaCurso, null);
		tabBajaCurso.setLayout(null);

		JLabel lblBajaDeCursos = new JLabel("Baja de Cursos");
		lblBajaDeCursos.setForeground(new Color(128, 0, 255));
		lblBajaDeCursos.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblBajaDeCursos.setBounds(326, 47, 213, 41);
		tabBajaCurso.add(lblBajaDeCursos);

		JLabel lblLogoLibAdm_1_1_1 = new JLabel("New label");
		lblLogoLibAdm_1_1_1.setIcon(new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.png")));
		lblLogoLibAdm_1_1_1.setBounds(549, 23, 151, 106);
		tabBajaCurso.add(lblLogoLibAdm_1_1_1);

		JLabel lblNewLabel_4_2 = new JLabel("Rama a la que pertenece:");
		lblNewLabel_4_2.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_2.setBounds(97, 151, 215, 20);
		tabBajaCurso.add(lblNewLabel_4_2);

		// Combo de ramas
		JComboBox cmbRamaCursoBaja = new JComboBox();
		cmbRamaCursoBaja.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbRamaCursoBaja.setBounds(326, 146, 297, 35);
		tabBajaCurso.add(cmbRamaCursoBaja);

		// Combo de cursos asociados a la rama
		JComboBox cmbNombreCursoBaja = new JComboBox();
		cmbNombreCursoBaja.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbNombreCursoBaja.setBounds(326, 204, 297, 35);
		tabBajaCurso.add(cmbNombreCursoBaja);
		cmbNombreCursoBaja.setModel(new DefaultComboBoxModel(new String[] { "Seleccione una rama" }));

		// Cargar ramas en el combo
		List<Rama> ramasBaja = RamaService.listarRamas();
		DefaultComboBoxModel<String> modelRamasBaja = new DefaultComboBoxModel<>();
		Map<String, Integer> mapaRamasBaja = new HashMap<>();
		modelRamasBaja.addElement("Seleccione una rama");

		for (Rama rama : ramasBaja) {
		    modelRamasBaja.addElement(rama.getNombre_rama());
		    mapaRamasBaja.put(rama.getNombre_rama(), rama.getId());
		}

		cmbRamaCursoBaja.setModel(modelRamasBaja);
		cmbRamaCursoBaja.setSelectedIndex(0);
		// ActionListener para cargar cursos según rama seleccionada
		cmbRamaCursoBaja.addActionListener(e -> {
		    try {
		        String ramaSeleccionada = (String) cmbRamaCursoBaja.getSelectedItem();
		        if (ramaSeleccionada == null) return;

		        Integer idRama = mapaRamasBaja.get(ramaSeleccionada);
		        if (idRama == null) return;

		        // Obtener cursos de la BD
		        List<Rama> ramasConCursos = CursoService.listarCursosPorRama(idRama);

		        DefaultComboBoxModel<String> modelCursos = new DefaultComboBoxModel<>();
		        if (!ramasConCursos.isEmpty()) {
		            Rama rama = ramasConCursos.get(0);
		            for (Curso curso : rama.getCursos()) {
		                modelCursos.addElement(curso.getNombre_curso());
		            }
		        }

		        cmbNombreCursoBaja.setModel(modelCursos);

		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		    }
		});


		JLabel lblNewLabel_4_1_2 = new JLabel("Nombre del curso:");
		lblNewLabel_4_1_2.setForeground(new Color(128, 0, 255));
		lblNewLabel_4_1_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_4_1_2.setBounds(156, 209, 156, 20);
		tabBajaCurso.add(lblNewLabel_4_1_2);

		JLabel lblNewLabel_5 = new JLabel("Docente vinculado:");
		lblNewLabel_5.setForeground(new Color(128, 0, 255));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_5.setBounds(211, 277, 128, 14);
		tabBajaCurso.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("Estudiantes vinculados:");
		lblNewLabel_5_1.setForeground(new Color(128, 0, 255));
		lblNewLabel_5_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_5_1.setBounds(500, 277, 156, 14);
		tabBajaCurso.add(lblNewLabel_5_1);

		JLabel lblDocenteVincBaja = new JLabel("-");
		lblDocenteVincBaja.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblDocenteVincBaja.setBounds(349, 278, 129, 14);
		tabBajaCurso.add(lblDocenteVincBaja);

		JLabel lblEstudiantesVinculadoBaja = new JLabel("-");
		lblEstudiantesVinculadoBaja.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEstudiantesVinculadoBaja.setBounds(666, 278, 46, 14);
		tabBajaCurso.add(lblEstudiantesVinculadoBaja);
		JButton btnCargarCursoBaja = new JButton("Ver datos");
		btnCargarCursoBaja.setForeground(Color.WHITE);
		btnCargarCursoBaja.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnCargarCursoBaja.setBackground(new Color(128, 0, 255));
		btnCargarCursoBaja.setBounds(632, 207, 126, 26);
		tabBajaCurso.add(btnCargarCursoBaja);
		btnCargarCursoBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cursoSeleccionado = cmbNombreCursoBaja.getSelectedItem().toString();

					// Obtener docente vinculado al curso
					Usuario docente = CursoService.obtenerDocenteVinculado(cursoSeleccionado);
					if (docente != null) {
						lblDocenteVincBaja.setText(docente.getNombre() + " " + docente.getApellido());
					} else {
						lblDocenteVincBaja.setText("No hay docente vinculado");
					}

					// Listar estudiantes del curso
					List<Usuario> estudiantes = CursoService.listarEstudiantesEnCurso(cursoSeleccionado);
					lblEstudiantesVinculadoBaja.setText(String.valueOf(estudiantes.size()));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});


		JButton btnDarDeBajaCurso = new JButton("Dar de baja");
		btnDarDeBajaCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String cursoSeleccionado = cmbNombreCursoBaja.getSelectedItem().toString();
					boolean exito = CursoService.DarDeBajaCurso(cursoSeleccionado);
					if (exito) {
						JOptionPane.showMessageDialog(VentanaAdmin.this, "Curso dado de baja exitosamente.");
						cmbNombreCursoBaja.setSelectedIndex(0);
					} else {
						JOptionPane.showMessageDialog(VentanaAdmin.this,
								"No se pudo dar de baja el curso. Intente nuevamente.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(VentanaAdmin.this, "Error: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnDarDeBajaCurso.setForeground(Color.WHITE);
		btnDarDeBajaCurso.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnDarDeBajaCurso.setBackground(new Color(128, 0, 0));
		btnDarDeBajaCurso.setBounds(381, 344, 166, 35);
		tabBajaCurso.add(btnDarDeBajaCurso);

		JPanel tabVisadoAdm = new JPanel();
		tabbedPane.addTab("Visado\r\n", new ImageIcon(VentanaAdmin.class.getResource("/resources/Libreta.ico")),
				tabVisadoAdm, null);
		tabVisadoAdm.setLayout(null);

		JPanel tabSuperior = new JPanel();
		tabSuperior.setBounds(0, 0, 1009, 38);
		tabSuperior.setBackground(new Color(158, 62, 255));
		tabVisadoAdm.add(tabSuperior);

		JLabel lblSeleccionCursoVisado = new JLabel("Selecciona un curso para visar:");
		lblSeleccionCursoVisado.setForeground(new Color(0, 0, 0));
		lblSeleccionCursoVisado.setFont(new Font("Segoe UI", Font.BOLD, 20));
		tabSuperior.add(lblSeleccionCursoVisado);

		JComboBox comboBoxCursosVisado = new JComboBox();
		comboBoxCursosVisado.setForeground(new Color(128, 0, 255));
		comboBoxCursosVisado.setBackground(new Color(255, 255, 255));
		comboBoxCursosVisado.setModel(new DefaultComboBoxModel(
				new String[] { "Informatica 9º3", "Informatica 9º4", "Ingles 7º3", "Ingles 8º3" }));
		comboBoxCursosVisado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tabSuperior.add(comboBoxCursosVisado);

		JButton btnCargarVisado = new JButton("Cargar información");
		btnCargarVisado.setForeground(new Color(0, 0, 0));
		btnCargarVisado.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnCargarVisado.setBackground(new Color(255, 255, 255));
		tabSuperior.add(btnCargarVisado);

		JButton btnGuardarVisado = new JButton("Guardar visado");
		btnGuardarVisado.setForeground(Color.BLACK);
		btnGuardarVisado.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		btnGuardarVisado.setBackground(Color.WHITE);
		tabSuperior.add(btnGuardarVisado);

		JPanel panelDocente = new JPanel();
		panelDocente.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(128, 0, 255)));
		panelDocente.setBackground(new Color(255, 255, 255));
		panelDocente.setBounds(10, 49, 364, 80);
		tabVisadoAdm.add(panelDocente);
		panelDocente.setLayout(null);

		JLabel lblDocenteVisado = new JLabel("Docente a cargo:");
		lblDocenteVisado.setBounds(13, 7, 134, 27);
		panelDocente.add(lblDocenteVisado);
		lblDocenteVisado.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblCiVisado = new JLabel("C.I:");
		lblCiVisado.setBounds(119, 39, 28, 27);
		panelDocente.add(lblCiVisado);
		lblCiVisado.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblDocenteVisadoDinamic = new JLabel("-");
		lblDocenteVisadoDinamic.setBounds(148, 9, 168, 25);
		panelDocente.add(lblDocenteVisadoDinamic);
		lblDocenteVisadoDinamic.setFont(new Font("Segoe UI", Font.BOLD, 12));

		JLabel lblCiVisadoDinamic = new JLabel("-");
		lblCiVisadoDinamic.setBounds(149, 40, 117, 25);
		panelDocente.add(lblCiVisadoDinamic);
		lblCiVisadoDinamic.setFont(new Font("Segoe UI", Font.BOLD, 14));

		JLabel lblPlanifAnual = new JLabel("Planificación anual:");
		lblPlanifAnual.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPlanifAnual.setBounds(10, 161, 129, 14);
		tabVisadoAdm.add(lblPlanifAnual);

		JLabel lblPlanifAnualDinamic = new JLabel("-");
		lblPlanifAnualDinamic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlanifAnualDinamic.setBounds(143, 158, 175, 20);
		tabVisadoAdm.add(lblPlanifAnualDinamic);

		JScrollPane scrollPaneEstudiantes = new JScrollPane();
		scrollPaneEstudiantes.setBounds(441, 75, 537, 451);
		tabVisadoAdm.add(scrollPaneEstudiantes);

		tableEstudiantesVisado = new JTable();
		tableEstudiantesVisado.setRowHeight(30);
		tableEstudiantesVisado.setBackground(new Color(255, 210, 255));
		tableEstudiantesVisado.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "Estudiante", "Actividades", "1\u00BA P", "2\u00BA P", "Promedio" }));
		tableEstudiantesVisado.getColumnModel().getColumn(0).setPreferredWidth(151);
		tableEstudiantesVisado.getColumnModel().getColumn(1).setPreferredWidth(130);
		tableEstudiantesVisado.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableEstudiantesVisado.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableEstudiantesVisado.getColumnModel().getColumn(4).setPreferredWidth(48);
		JTableHeader header = tableEstudiantesVisado.getTableHeader();
		tableEstudiantesVisado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		scrollPaneEstudiantes.setViewportView(tableEstudiantesVisado);

		JLabel lblEstudiantesCurso = new JLabel("Estudiantes del curso");
		lblEstudiantesCurso.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEstudiantesCurso.setBounds(615, 43, 185, 25);
		tabVisadoAdm.add(lblEstudiantesCurso);

		JLabel lblDesarrolloVisado = new JLabel("Desarrollo del curso:");
		lblDesarrolloVisado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblDesarrolloVisado.setBounds(10, 212, 146, 14);
		tabVisadoAdm.add(lblDesarrolloVisado);

		JScrollPane scrollPaneDesarrolloVisado = new JScrollPane();
		scrollPaneDesarrolloVisado.setBounds(10, 237, 146, 180);
		tabVisadoAdm.add(scrollPaneDesarrolloVisado);

		JList listDesarrolloVisado = new JList();
		scrollPaneDesarrolloVisado.setViewportView(listDesarrolloVisado);
		listDesarrolloVisado.setModel(new AbstractListModel() {
			String[] values = new String[] { "1/08", "31/07", "22/07", "8/08", "Prueba2", "Prueba3", "Prueba",
					"Prueba4", "Prueba5" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		listDesarrolloVisado.setValueIsAdjusting(true);
		listDesarrolloVisado.setForeground(new Color(128, 0, 255));
		listDesarrolloVisado.setFont(new Font("Arial", Font.BOLD, 18));
		listDesarrolloVisado.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 255),
				new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255)));
		listDesarrolloVisado.setBackground(new Color(230, 204, 255));

		JTextArea txtrDesarrolloVisado = new JTextArea();
		txtrDesarrolloVisado.setLineWrap(true);
		txtrDesarrolloVisado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		txtrDesarrolloVisado.setText("aaa");
		txtrDesarrolloVisado.setBounds(162, 235, 266, 151);
		tabVisadoAdm.add(txtrDesarrolloVisado);

		JLabel lblPlanifVisado = new JLabel("Link planif:");
		lblPlanifVisado.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPlanifVisado.setBounds(164, 395, 83, 14);
		tabVisadoAdm.add(lblPlanifVisado);

		JLabel lblPlanifVisadoDinamic = new JLabel("(Opcional)\r\n");
		lblPlanifVisadoDinamic.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblPlanifVisadoDinamic.setBounds(237, 395, 81, 16);
		tabVisadoAdm.add(lblPlanifVisadoDinamic);

		JRadioButton rdbtnPlanifAnualCVisado = new JRadioButton("Completo");
		buttonGroup.add(rdbtnPlanifAnualCVisado);
		rdbtnPlanifAnualCVisado.setBounds(10, 182, 84, 23);
		tabVisadoAdm.add(rdbtnPlanifAnualCVisado);

		JRadioButton rdbtnPlanifAnualIVisado = new JRadioButton("Incompleto\r\n");
		buttonGroup.add(rdbtnPlanifAnualIVisado);
		rdbtnPlanifAnualIVisado.setBounds(100, 182, 99, 23);
		tabVisadoAdm.add(rdbtnPlanifAnualIVisado);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(128, 0, 255));
		separator_2.setBounds(4, 211, 438, 13);
		tabVisadoAdm.add(separator_2);

		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setForeground(new Color(128, 0, 255));
		separator_2_1.setBounds(-3, 448, 442, 13);
		tabVisadoAdm.add(separator_2_1);

		JRadioButton rdbtnDesarrolloCVisado = new JRadioButton("Completo");
		buttonGroup_1.add(rdbtnDesarrolloCVisado);
		rdbtnDesarrolloCVisado.setBounds(14, 420, 85, 23);
		tabVisadoAdm.add(rdbtnDesarrolloCVisado);

		JRadioButton rdbtnDesarrolloIVisado = new JRadioButton("Incompleto\r\n");
		buttonGroup_1.add(rdbtnDesarrolloIVisado);
		rdbtnDesarrolloIVisado.setBounds(97, 420, 98, 23);
		tabVisadoAdm.add(rdbtnDesarrolloIVisado);

		JRadioButton rdbtnPromediosCVisado = new JRadioButton("Completo");
		buttonGroup_3.add(rdbtnPromediosCVisado);
		rdbtnPromediosCVisado.setBounds(592, 537, 85, 23);
		tabVisadoAdm.add(rdbtnPromediosCVisado);

		JRadioButton rdbtnPromediosIVisado = new JRadioButton("Incompleto\r\n");
		buttonGroup_3.add(rdbtnPromediosIVisado);
		rdbtnPromediosIVisado.setBounds(674, 537, 98, 23);
		tabVisadoAdm.add(rdbtnPromediosIVisado);

		JLabel lblEvSemestralVisado = new JLabel("1ºer Evaluación semestral:");
		lblEvSemestralVisado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEvSemestralVisado.setBounds(10, 472, 185, 20);
		tabVisadoAdm.add(lblEvSemestralVisado);

		JLabel lblEvSemestral2Visado = new JLabel("2ºda Evaluación semestral:");
		lblEvSemestral2Visado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEvSemestral2Visado.setBounds(8, 513, 185, 20);
		tabVisadoAdm.add(lblEvSemestral2Visado);

		JLabel lblEvSemestralDinamicVisado = new JLabel("-");
		lblEvSemestralDinamicVisado.setBounds(190, 477, 191, 14);
		tabVisadoAdm.add(lblEvSemestralDinamicVisado);

		JLabel lblEvSemestral2DinamicVisado = new JLabel("-");
		lblEvSemestral2DinamicVisado.setBounds(188, 516, 156, 14);
		tabVisadoAdm.add(lblEvSemestral2DinamicVisado);

		JRadioButton rdbtnEvSemestralCVisado = new JRadioButton("Completo");
		buttonGroup_2.add(rdbtnEvSemestralCVisado);
		rdbtnEvSemestralCVisado.setBounds(54, 537, 85, 23);
		tabVisadoAdm.add(rdbtnEvSemestralCVisado);

		JRadioButton rdbtnEvSemestralIVisado = new JRadioButton("Incompleto\r\n");
		buttonGroup_2.add(rdbtnEvSemestralIVisado);
		rdbtnEvSemestralIVisado.setBounds(149, 537, 98, 23);
		tabVisadoAdm.add(rdbtnEvSemestralIVisado);

		JLabel lblPromediosVisado = new JLabel("Promedios:");
		lblPromediosVisado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPromediosVisado.setBounds(500, 537, 99, 19);
		tabVisadoAdm.add(lblPromediosVisado);

	}
}