package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.dao.UsuarioDAO;
import main.model.Usuario;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import java.awt.Label;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pwdContrasena;
	public Color btnHover = new Color (156,142,240);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setLocationByPlatform(true);
		setUndecorated(true);
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setBounds(244, 5, 296, 225);
		lblImagen.setIcon(new ImageIcon(VentanaLogin.class.getResource("/main/images/Docente_Login.png")));
		panel.add(lblImagen);
		
		JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Roboto", Font.BOLD, 24));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(265, 192, 251, 78);
		panel.add(lblTitulo);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.LIGHT_GRAY);
		txtEmail.setBorder(null);
		txtEmail.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail.setText("Ingrese su Email");
		txtEmail.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtEmail.setBounds(244, 272, 300, 21);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(79, 67, 188));
		separator.setBounds(244, 294, 300, 12);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(79, 67, 188));
		separator_1.setBounds(244, 340, 300, 12);
		panel.add(separator_1);
		
		pwdContrasena = new JPasswordField();
		pwdContrasena.setBorder(null);
		pwdContrasena.setForeground(Color.LIGHT_GRAY);
		pwdContrasena.setText("Contraseña");
		pwdContrasena.setBounds(244, 317, 300, 21);
		panel.add(pwdContrasena);
		
		JPanel loginBtn = new JPanel();
		loginBtn.addMouseListener(new MouseAdapter() {
		});
		loginBtn.setBorder(null);
		loginBtn.setBackground(new Color(79, 67, 188));
		loginBtn.setBounds(325, 392, 122, 40);
		panel.add(loginBtn);
		loginBtn.setLayout(null);
		
		Label loginTxt = new Label("INGRESAR");
		loginTxt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				loginBtn.setBackground(btnHover);
				loginTxt.setForeground(btnHover);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				loginTxt.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = txtEmail.getText();
				String contrasena = new String(pwdContrasena.getPassword());
				Usuario userLogueado = UsuarioDAO.login(email, contrasena);
				
				if (userLogueado != null) {
				    System.out.println("✅ Bienvenido " + userLogueado.getNombre());
				    // Abrir ventana principal o continuar al sistema
				} else {
				    System.out.println("❌ Credenciales incorrectas.");
				}
				
				
			}
		});
		loginTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginTxt.setForeground(Color.WHITE);
		loginTxt.setFont(new Font("Roboto", Font.BOLD, 18));
		loginTxt.setAlignment(Label.CENTER);
		loginTxt.setBounds(0, 0, 122, 40);
		loginBtn.add(loginTxt);
		
		JPanel panelCerrar = new JPanel();
		panelCerrar.setBackground(Color.RED);
		panelCerrar.setBounds(761, 0, 39, 33);
		panel.add(panelCerrar);
		panelCerrar.setLayout(null);
		
		JLabel lblCerrar = new JLabel("X");
		lblCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panelCerrar.setBackground(new Color(249, 135, 138));
				lblCerrar.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelCerrar.setBackground(Color.RED);
				lblCerrar.setForeground(Color.WHITE);
			}
		});
		lblCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCerrar.setFont(new Font("Roboto", Font.BOLD, 26));
		lblCerrar.setForeground(Color.WHITE);
		lblCerrar.setBounds(0, 0, 39, 33);
		panelCerrar.add(lblCerrar);

	}
}
