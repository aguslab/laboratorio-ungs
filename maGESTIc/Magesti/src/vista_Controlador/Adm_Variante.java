package vista_Controlador;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import Modelo.Variante;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Font;

public class Adm_Variante extends JInternalFrame {
	private JTextField txtNombreVariante;

	
	public Adm_Variante() {
		super("Administracion Variante");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		String [] Variantes=Variante.getVariantes();
		
		JLabel lblNuevo = new JLabel("Agregar Variante");
		lblNuevo.setFont(new Font("Arial", Font.BOLD, 11));
		lblNuevo.setBackground(Color.BLACK);
		lblNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevo.setForeground(Color.WHITE);
		lblNuevo.setBounds(10, 11, 414, 18);
		getContentPane().add(lblNuevo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(34, 51, 56, 22);
		getContentPane().add(lblNombre);
		
		txtNombreVariante = new JTextField();
		txtNombreVariante.setBounds(107, 47, 123, 26);
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
		
		JComboBox comboBox = new JComboBox(Variantes);
		comboBox.setBounds(107, 174, 123, 22);
		getContentPane().add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 414, 18);
		getContentPane().add(panel);
		
		JLabel lblEliminarVariante = new JLabel("Eliminar Variante");
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
		
		JButton btnCerrar = new JButton("CERRAR");
		btnCerrar.setBounds(172, 237, 89, 23);
		getContentPane().add(btnCerrar);

	}
}
