package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class formDuenio extends JInternalFrame 
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					formDuenio frame = new formDuenio(null, null, null);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public formDuenio(String nom, String cuit, String tel) 
	{
		setClosable(true);
		setTitle("Datos del Due\u00F1o/Locador");
		setBounds(100, 100, 327, 151);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblCuit = new JLabel("CUIT:");
		lblCuit.setBounds(10, 47, 46, 14);
		getContentPane().add(lblCuit);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(10, 84, 46, 14);
		getContentPane().add(lblTelfono);
		
		textField = new JTextField();
		textField.setBounds(67, 8, 235, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(nom);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 44, 236, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(cuit);
		
		textField_2 = new JTextField();
		textField_2.setBounds(66, 81, 236, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(tel);

	}
	
	
}
