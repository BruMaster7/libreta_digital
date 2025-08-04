package main.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import java.awt.Toolkit;

public class SeleccionCursos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionCursos frame = new SeleccionCursos();
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
	public SeleccionCursos() {
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
		
		JList listCursos = new JList();
		listCursos.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255), new Color(128, 0, 255)));
		listCursos.setValueIsAdjusting(true);
		listCursos.setBackground(new Color(230, 204, 255));
		listCursos.setForeground(new Color(128, 0, 255));
		listCursos.setBounds(414, 225, 141, 267);
		listCursos.setFont(new Font("Arial", Font.BOLD, 18));
		listCursos.setModel(new AbstractListModel() {
			String[] values = new String[] {"Informática 9°5", "Matemática 8°1", "Matemática 7°2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		contentPane.add(listCursos);
		
		JButton btnIngresarCurso = new JButton("Ingresar al curso");
		btnIngresarCurso.setFont(new Font("Arial", Font.PLAIN, 18));
		btnIngresarCurso.setForeground(new Color(255, 255, 255));
		btnIngresarCurso.setBackground(new Color(125, 14, 226));
		btnIngresarCurso.setBounds(384, 521, 196, 69);
		contentPane.add(btnIngresarCurso);
		
		JLabel lblIconoCursos = new JLabel("New label");
		lblIconoCursos.setIcon(new ImageIcon(SeleccionCursos.class.getResource("/resources/Libreta.png")));
		lblIconoCursos.setBounds(409, 32, 146, 123);
		contentPane.add(lblIconoCursos);
		
		
		
		
	
		
		
		
		
		
	}
}
