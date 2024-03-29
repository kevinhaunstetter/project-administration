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
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.MyDAOExcepcion;
import Swing.HandlerGeneral;
import entidades.Empleado;

/**
 * Definimos el layout para el panel necesario para la modif de un Empleado.
 * Cada vez que necesitamos mostrar un panel para la modif de Empleados,
 * directamente llamamos a esta clase. En su contructor, el panel espera un
 * handler, y una instancia de Empleado, desde el cual se tomaran los datos para
 * su modificacion.
 *
 */
public class PanelModificarEmpleado extends PanelPadre {

	private static final long serialVersionUID = 1L;

	private JButton botonAceptar;
	private JButton botonCancelar;

	private Empleado p;
	
	private JLabel lblTema;
	private JLabel lblNombreCompleto;
	private JLabel lblSueldoHora;
	
	private JTextField txtLegajo;
	private JTextField txtNombreCompleto;
	private JTextField txtSueldoHora;
	
	private HandlerGeneral handler;

	public PanelModificarEmpleado(HandlerGeneral handler, Empleado p) {
		this.handler = handler;
		this.p = p;
		initUI();
	}

	private void initUI() {

		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		lblTema = new JLabel("Modificacion de Empleados");
		lblNombreCompleto = new JLabel("Nombre Completo:");
		lblSueldoHora = new JLabel("Sueldo por Hora: ");
		
		txtLegajo = new JTextField("");
		txtNombreCompleto = new JTextField("");
		txtSueldoHora = new JTextField("");
		
		txtLegajo.setMaximumSize(new Dimension(450, 30));
		txtNombreCompleto.setMaximumSize(new Dimension(450, 30));
		txtSueldoHora.setMaximumSize(new Dimension(450, 30));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		
		JPanel rowNombreCompleto = new JPanel();
		JPanel rowSueldoHora = new JPanel();
		JPanel rowBotones = new JPanel();

		rowNombreCompleto.setLayout(new BoxLayout(rowNombreCompleto, BoxLayout.X_AXIS));
		rowBotones.setLayout(new BoxLayout(rowBotones, BoxLayout.X_AXIS));

	
		rowNombreCompleto.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));

		lblTema.setFont((new Font("Arial", Font.BOLD, 17)));
		lblTema.setForeground(Color.LIGHT_GRAY);

	

		rowNombreCompleto.add(lblNombreCompleto);
		rowSueldoHora.add(lblSueldoHora);
		rowBotones.add(botonAceptar);


	
		rowNombreCompleto.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));
		rowSueldoHora.add(Box.createHorizontalStrut(10));

	
		rowNombreCompleto.add(txtNombreCompleto);
		rowSueldoHora.add(txtSueldoHora);
		rowBotones.add(botonCancelar);


		rowNombreCompleto.add(Box.createHorizontalStrut(10));
		rowBotones.add(Box.createHorizontalStrut(10));
		rowSueldoHora.add(Box.createHorizontalStrut(10));

		txtLegajo.setText(String.valueOf(p.getLegajo()));
		txtNombreCompleto.setText(String.valueOf(p.getNombreCompleto()));
		txtSueldoHora.setText(String.valueOf(p.getSueldoHora()));

		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

				if (validarCampo(txtSueldoHora) || validarCampo(txtNombreCompleto))

					handler.mostrarError("Por favor complete todos los campos.");
				else {

					Empleado empleadoToModify = new Empleado(
							p.getLegajo(), Integer.valueOf(txtSueldoHora.getText()), txtNombreCompleto.getText());

					try {
						handler.modificarEmpleado(empleadoToModify);
						handler.mostrarExito("El Empleado " + empleadoToModify.getNombreCompleto() + " fue modificado.");
						handler.verEmpleados();
					} catch (MyDAOExcepcion e) {
						handler.mostrarError(e.getMessage());
					}
				}
			}
		});

		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				handler.cerrarPanel();

			}

		});

		this.add(rowSueldoHora);
		this.add(rowNombreCompleto);
		this.add(rowBotones);
	}

}