package aplicacion;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Login frame2;
	private JTextField text1;
	private JTextField text2;

	
	public JTextField getText1() {
		return text1;
	}
	public void setText1(JTextField text1) {
		this.text1 = text1;
	}
	public JTextField getText2() {
		return text2;
	}
	public void setText2(JTextField text2) {
		this.text2 = text2;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame2 = new Login();
					frame2.setVisible(true);
					Canciones.frame1= new Canciones();
					Canciones.frame1.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label1 = new JLabel("Usuario");
		label1.setFont(new Font("Serif", Font.PLAIN, 17));
		label1.setBounds(72, 51, 96, 23);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Contraseña");
		label2.setFont(new Font("Serif", Font.PLAIN, 17));
		label2.setBounds(72, 109, 96, 23);
		contentPane.add(label2);
		
		JButton boton = new JButton("Entrar");
		boton.setFont(new Font("Serif", Font.PLAIN, 17));
		boton.setBounds(165, 179, 120, 21);
		contentPane.add(boton);
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (text1.getText().trim().equals("admin") && text2.getText().trim().equals("admin")) {
					frame2.setVisible(false);
					Canciones.frame1.setVisible(true);
				}else {
					if (!text1.getText().equals("") && !text2.getText().equals(""))
						JOptionPane.showMessageDialog(contentPane, "Error al identificarse", 
								"Riberadeltajo", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		});

		text1 = new JTextField();
		text1.setFont(new Font("Serif", Font.PLAIN, 17));
		text1.setBounds(217, 48, 96, 26);
		contentPane.add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setFont(new Font("Serif", Font.PLAIN, 17));
		text2.setBounds(217, 109, 96, 23);
		contentPane.add(text2);
		text2.setColumns(10);
	}
}
