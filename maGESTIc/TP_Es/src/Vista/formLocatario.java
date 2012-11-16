package Vista;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class formLocatario extends JInternalFrame 
{
	private JTextField textField;
	private JTextField textField_1;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					formLocatario frame = new formLocatario(null,null);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public formLocatario(String nom, String cuit) 
	{
		setClosable(true);
		setTitle("Datos del Locatario");
		setBounds(100, 100, 346, 109);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 11, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("CUIT/CUIL");
		lblNewLabel.setBounds(20, 46, 68, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(98, 8, 230, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(nom);
		
		textField_1 = new JTextField();
		textField_1.setBounds(98, 43, 230, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(cuit);

	}

}
