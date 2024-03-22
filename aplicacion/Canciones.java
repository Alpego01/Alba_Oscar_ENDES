package aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;

public class Canciones extends JFrame {

	private static final long serialVersionUID = 1L;
	static Canciones frame1;
	private JPanel contentPane;
	private JTextField text;
	private JTextArea textArea2;//tengo que inicializar en segundo textArea para que me pueda aparecer
	
	public JTextField getText() {
		return text;
	}
	public void setText(JTextField text) {
		this.text = text;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 = new Canciones();
					frame1.setVisible(true);
					Login.frame2= new Login();
					Login.frame2.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Canciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 255));
		contentPane.setForeground(UIManager.getColor("CheckBox.foreground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		
		text = new JTextField();
		text.setBounds(67, 175, 115, 19);
		contentPane.add(text);
		text.setColumns(10);
		
		
		JLabel label1 = new JLabel("Código de grupo");
		label1.setForeground(UIManager.getColor("inactiveCaptionText"));
		label1.setFont(new Font("Serif", Font.PLAIN, 17));
		label1.setBounds(69, 129, 128, 36);
		contentPane.add(label1);
		
		JLabel lblNewLabel = new JLabel(" Grupos y Canciones");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Serif", Font.BOLD, 25));
		lblNewLabel.setBounds(443, 23, 231, 29);
		contentPane.add(lblNewLabel);
		
//		1. Mostrar grupos: Botón que lee el fichero csv todos los grupos y los guarda en un ArrayList. 
//		El contenido de este ArrayList se mostrará en un JtextArea.
		JButton boton1 = new JButton("Mostrar Grupos");
		boton1.setBackground(new Color(192, 192, 192));
		boton1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		boton1.setBounds(54, 69, 154, 36);
		contentPane.add(boton1);
		
		boton1.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	textArea2.setText("");
		        BufferedReader leer = null;
		        try {
		            leer = new BufferedReader(new FileReader("grupos.csv"));//intenta abrir el archivo
		            String linea; //declarar variable
		            ArrayList<String> grupo = new ArrayList<>();
		            while ((linea = leer.readLine()) != null) {//leer linea
		                linea=linea.replace(";"," - ");//para reemplazar 
		            	grupo.add(linea);//agregar linea en el arraylist
		            	textArea2.append(linea + "\n");//para que aparezca en el textArea
		                textArea2.setEditable(false);//para que el usuario no lo pueda editar
		            }
		        } catch (FileNotFoundException e1) {
		            e1.printStackTrace();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (leer != null) {
		                    leer.close(); 
		                }
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 62, 586, 399);
		contentPane.add(scrollPane);
		
		textArea2 = new JTextArea();
		scrollPane.setViewportView(textArea2);
		textArea2.setFont(new Font("Courier New", Font.PLAIN, 17));
		
//		2. Ver canciones: Es otro botón que lee un fichero csv con todos las canciones disponibles y 
//		las guarda en un ArrayList. . El contenido de este ArrayList se mostrará en otro JtextArea.
//		3. Ver canciones de un grupo concreto. Dado un JTextField en el que el usuario introduce un
//		código de grupo muestra en un JTextArea las canciones de ese grupo
		
		JButton boton2 = new JButton("Ver canciones");
		boton2.setBackground(new Color(192, 192, 192));
		boton2.setFont(new Font("SansSerif", Font.PLAIN, 17));
		boton2.setBounds(54, 250, 154, 36);
		contentPane.add(boton2);
		
		//la funcion es igual que el otro boton solo que cambiando el csv 
		//y tambien hay que poner la opcion de si se pone informacion en el texto que salgan las canciones con ese codigo
		boton2.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        textArea2.setText(""); // Limpiar el área de texto
		        BufferedReader leer = null;
		        try {
		            String cancion = text.getText().trim(); 
		            // Obtener la cancion del grupo ingresado
		            if (!cancion.isEmpty()) { // Verificar si se ingresó un código de grupo
		                leer = new BufferedReader(new FileReader("canciones.csv"));
		                String linea;
		                boolean encontrar = false;
		                while ((linea = leer.readLine()) != null) {
		                    String[] line = linea.split(";");
		                    if (line.length >= 2 && line[0].equals(cancion)) { 
		                    	// Verificar si el código de grupo coincide
		                    	encontrar = true;
		                        textArea2.append(linea.replace(";", "-") + "\n"); // Mostrar la canción en el área de texto
		                        textArea2.setEditable(false);
		                    }
		                }
		                if (!encontrar) {
		                    textArea2.setText("No hay ninguna cancion " + cancion); 
		                    // Mostrar mensaje si no se encuentra ninguna canción 
		                }
		            } else {
		                // Mostrar todas las canciones si no se puso un código de grupo
		                leer = new BufferedReader(new FileReader("canciones.csv"));
		                String linea;
		                while ((linea = leer.readLine()) != null) {
		                    textArea2.append(linea.replace(";", "-") + "\n"); // Mostrar la canción en el área de texto
		                    textArea2.setEditable(false);
		                }
		            }
		        } catch (FileNotFoundException e1) {
		            e1.printStackTrace();
		        } catch (IOException e1) {
		            e1.printStackTrace();
		        } finally {
		            try {
		                if (leer != null) {
		                    leer.close();
		                }
		            } catch (IOException e2) {
		                e2.printStackTrace();
		            }
		        }
		    }
		});

	}
}
