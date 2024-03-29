package Swing.Panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Swing.HandlerLogin;

public class PanelLogin extends PanelPadre {

	private static final long serialVersionUID = 1L;

	private JTextField txtUsuario;
	private JPasswordField txtpass;
	private JButton botonIngresar;
	private JButton botonCancelar;
	private HandlerLogin handler;
	private JLabel lblTitulo;
	private JLabel lblUsuario;
	private JLabel lblContrase�a;
	
	
	public PanelLogin(HandlerLogin handler) {
		this.handler = handler;
		initUI();
	}

	private void initUI() {
		this.setSize(500, 500);

		botonIngresar = new JButton("Ingresar");
		botonCancelar = new JButton("Cancelar");
		
		lblTitulo = new JLabel("Login");
		
		lblUsuario= new JLabel("Usuario:");
		lblContrase�a = new JLabel("Contrase�a:");
		
		txtUsuario= new JTextField("");
		txtpass = new JPasswordField("");
		
		txtUsuario.setMaximumSize(new Dimension(450, 30));
		txtpass.setMaximumSize(new Dimension(450, 30));

		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		JPanel rowTitulo = new JPanel();
		JPanel rowUsuario= new JPanel();
		JPanel rowContrase�a = new JPanel();
		JPanel rowBotones = new JPanel();

		rowTitulo.setLayout(new BoxLayout(rowTitulo, BoxLayout.X_AXIS));
		rowUsuario.setLayout(new BoxLayout(rowUsuario, BoxLayout.X_AXIS));
		rowContrase�a.setLayout(new BoxLayout(rowContrase�a, BoxLayout.X_AXIS));
		rowBotones.setLayout(new BoxLayout(rowBotones, BoxLayout.X_AXIS));
		
		rowTitulo.add(Box.createHorizontalStrut(10));
		rowUsuario.add(Box.createHorizontalStrut(10));
		rowContrase�a.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));

		lblTitulo.setFont((new Font("Arial", Font.BOLD, 17)));
		lblTitulo.setForeground(Color.LIGHT_GRAY);

		rowTitulo.add(lblTitulo);
		rowUsuario.add(lblUsuario);
		rowContrase�a.add(lblContrase�a);
		
		rowBotones.add(botonIngresar);

		rowUsuario.add(Box.createHorizontalStrut(10));
		rowContrase�a.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));
		
		rowUsuario.add(txtUsuario);
		rowContrase�a.add(txtpass);
		rowBotones.add(botonCancelar);

		rowUsuario.add(Box.createHorizontalStrut(10));
		rowContrase�a.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));

		botonIngresar.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			    if(txtUsuario.getText().isEmpty() || txtpass.getPassword().length==0)
			      JOptionPane.showMessageDialog(null,"Por favor complete todos los campos.", "ERROR", JOptionPane.ERROR_MESSAGE);
			    else{
			      if(	"root".equals(txtUsuario.getText()) && "a".equals(String.valueOf(txtpass.getPassword()))){ 
			    	 
			    	  handler.cerrarPanelLogin();
			    	  
			    	
			      }
			      else
			        JOptionPane.showMessageDialog(null, "Usuario o contrasena incorrecto.", "ERROR", JOptionPane.ERROR_MESSAGE);
			      
			      }
			    }
			});

		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}

		});

		this.add(rowTitulo);
		this.add(rowUsuario);
		this.add(rowContrase�a);
		this.add(rowBotones);

	}

}