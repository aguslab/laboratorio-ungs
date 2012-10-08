package vista_Controlador;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Modelo.Formato_Papel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;

public class Adm_Formato extends JInternalFrame {
	private JTextField txtNombreVariante;
	private JTextField textField;

	
	public Adm_Formato() {
		super("Administracion Formato");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		String[] Formato=Formato_Papel.getFormatos();
		
		JLabel lblNuevo = new JLabel("Agregar Formato");
		lblNuevo.setFont(new Font("Arial", Font.BOLD, 11));
		lblNuevo.setBackground(Color.BLACK);
		lblNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevo.setForeground(Color.WHITE);
		lblNuevo.setBounds(10, 11, 414, 18);
		getContentPane().add(lblNuevo);
		
		JLabel lblNombre = new JLabel("Valor:");
		lblNombre.setBounds(56, 51, 34, 22);
		getContentPane().add(lblNombre);
		
		txtNombreVariante = new JTextField();
		txtNombreVariante.setBounds(93, 48, 34, 28);
		getContentPane().add(txtNombreVariante);
		txtNombreVariante.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(285, 51, 89, 23);
		getContentPane().add(btnAgregar);
		
		JLabel label_1 = new JLabel("Nombre:");
		label_1.setBounds(34, 174, 56, 22);
		getContentPane().add(label_1);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(285, 174, 89, 23);
		getContentPane().add(btnBorrar);
		
		JComboBox comboBox = new JComboBox(Formato);
		comboBox.setBounds(107, 174, 123, 22);
		getContentPane().add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 414, 18);
		getContentPane().add(panel);
		
		JLabel lblEliminarVariante = new JLabel("Eliminar Formato");
		lblEliminarVariante.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminarVariante.setForeground(Color.WHITE);
		lblEliminarVariante.setFont(new Font("Arial", Font.BOLD, 11));
		lblEliminarVariante.setBackground(Color.BLACK);
		lblEliminarVariante.setBounds(10, 116, 414, 18);
		getContentPane().add(lblEliminarVariante);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 116, 414, 18);
		getContentPane().add(panel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(148, 48, 34, 28);
		getContentPane().add(textField);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(123, 55, 28, 18);
		getContentPane().add(lblX);
		
		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setBounds(162, 237, 89, 23);
		getContentPane().add(btnCerrar);

	}
}
