package vista_Controlador;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Adm_Cliente extends JInternalFrame {
	private JTextField txtFRazon_Social;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Adm_Cliente() {
		super ("Administracion de Cliente", false, true, false, true);

		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		
		txtFRazon_Social = new JTextField();
		txtFRazon_Social.setBounds(82, 21, 501, 20);
		getContentPane().add(txtFRazon_Social);
		txtFRazon_Social.setColumns(10);
		
		JLabel lblRazonSocail = new JLabel("Razon Socail:");
		lblRazonSocail.setBounds(10, 21, 72, 20);
		getContentPane().add(lblRazonSocail);
		
		JLabel lblCuit = new JLabel("Cuit:");
		lblCuit.setBounds(48, 52, 34, 20);
		getContentPane().add(lblCuit);
		
		JLabel lblCondIva = new JLabel("Cond. Iva:");
		lblCondIva.setBounds(20, 83, 62, 20);
		getContentPane().add(lblCondIva);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(29, 114, 53, 20);
		getContentPane().add(lblDireccion);
		
		JLabel lblTel = new JLabel("Telefono:");
		lblTel.setBounds(29, 145, 53, 20);
		getContentPane().add(lblTel);
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setBounds(48, 176, 34, 20);
		getContentPane().add(lblMail);
		
		JLabel lblContacto = new JLabel("Contacto  Cliente");
		lblContacto.setFont(new Font("Arial", Font.BOLD, 18));
		lblContacto.setBounds(186, 207, 178, 20);
		getContentPane().add(lblContacto);
		
		JLabel lblNombreContacto = new JLabel("Nombre:");
		lblNombreContacto.setBounds(37, 238, 45, 20);
		getContentPane().add(lblNombreContacto);
		
		JLabel label = new JLabel("Telefono:");
		label.setBounds(29, 267, 53, 20);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Mail:");
		label_1.setBounds(48, 298, 34, 20);
		getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(82, 52, 501, 20);
		getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(82, 83, 501, 20);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(82, 114, 501, 20);
		getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(82, 145, 501, 20);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(82, 176, 501, 20);
		getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(82, 238, 501, 20);
		getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(82, 267, 501, 20);
		getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(82, 298, 501, 20);
		getContentPane().add(textField_7);
		
		JLabel lblDirEntrega = new JLabel("Direccion Entrega:");
		lblDirEntrega.setBounds(29, 345, 89, 26);
		getContentPane().add(lblDirEntrega);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(123, 348, 459, 20);
		getContentPane().add(textField_8);
		
		JButton button = new JButton("CERRAR");
		button.setBounds(393, 426, 89, 23);
		getContentPane().add(button);
		
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfirmar.setBounds(157, 426, 105, 23);
		getContentPane().add(btnConfirmar);

	}
}
