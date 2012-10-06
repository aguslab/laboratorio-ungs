package vista_Controlador;

import java.awt.event.*;

import javax.swing.*;
import java.awt.TextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")



public class SolicitudDeCompra extends JInternalFrame implements ActionListener, Config
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	public SolicitudDeCompra(boolean  RP) {
super ("Solicitud de Compra (SC)", false, true, false, true);
		
		setSize (564, 529);
		
		//jpOrdenDeTrabajo.setBounds (0, 0, 500, 115);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N\u00FAmero:");
		lblNewLabel.setBounds(10, 11, 65, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(85, 8, 78, 25);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Proveedor:");
		lblNewLabel_1.setBounds(173, 11, 68, 14);
		getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(242, 8, 302, 25);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha de Confecci\u00F3n:");
		lblNewLabel_2.setBounds(10, 47, 129, 14);
		getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(139, 44, 46, 25);
		getContentPane().add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(183, 44, 42, 25);
		getContentPane().add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(225, 44, 60, 25);
		getContentPane().add(comboBox_3);
		
		JLabel lblNewLabel_3 = new JLabel("Vendedor:");
		lblNewLabel_3.setBounds(295, 47, 63, 14);
		getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(358, 44, 186, 25);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("N\u00B0 Orden de Trabajo / Stockeo:");
		lblNewLabel_4.setBounds(10, 81, 205, 14);
		getContentPane().add(lblNewLabel_4);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(195, 78, 349, 25);
		getContentPane().add(comboBox_4);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 106, 534, 218);
		getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Condiciones de Entrega", null, panel_1, null);
		panel_1.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enviar Proveedor");
		chckbxNewCheckBox.setBounds(6, 7, 159, 23);
		panel_1.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Retirar");
		chckbxNewCheckBox_1.setBounds(6, 33, 61, 23);
		panel_1.add(chckbxNewCheckBox_1);
		
		JLabel lblNewLabel_9 = new JLabel("Direcci\u00F3n de Retiro:");
		lblNewLabel_9.setBounds(16, 63, 121, 14);
		panel_1.add(lblNewLabel_9);
		
		textField_6 = new JTextField();
		textField_6.setBounds(157, 57, 362, 25);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha de Entrega");
		lblNewLabel_10.setBounds(10, 96, 93, 14);
		panel_1.add(lblNewLabel_10);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(134, 93, 69, 25);
		panel_1.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(202, 93, 61, 25);
		panel_1.add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(262, 93, 61, 25);
		panel_1.add(comboBox_7);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ma\u00F1ana");
		rdbtnNewRadioButton.setBounds(410, 109, 109, 23);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Tarde");
		rdbtnNewRadioButton_1.setBounds(410, 148, 109, 23);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel_11 = new JLabel("Horario de Entrega:");
		lblNewLabel_11.setBounds(295, 131, 109, 14);
		panel_1.add(lblNewLabel_11);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.addTab("Detalle", null, tabbedPane_1, null);
		
		JButton btnNewButton_1 = new JButton("Cerrar");
		btnNewButton_1.setBounds(461, 459, 89, 30);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Confirmar");
		btnNewButton_2.setBounds(362, 459, 89, 30);
		getContentPane().add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(464, 428, 86, 25);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(464, 397, 86, 25);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(464, 366, 86, 25);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(464, 335, 86, 25);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 335, 338, 116);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setEnabled(true);
		
		
		JButton btnNewButton_3 = new JButton("Confirmar");
		btnNewButton_3.setBounds(10, 11, 89, 23);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Rechazar");
		btnNewButton_4.setBounds(10, 42, 89, 23);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Incompleta");
		btnNewButton_5.setBounds(10, 76, 89, 23);
		panel.add(btnNewButton_5);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(105, 10, 223, 96);
		panel.add(textArea);
		
		
		if (RP)
		{
			btnNewButton_3.setEnabled(true);
			btnNewButton_4.setEnabled(true);
			btnNewButton_5.setEnabled(true);
			textArea.setEnabled(true);
		}
		else
		{
			btnNewButton_3.setEnabled(false);
			btnNewButton_4.setEnabled(false);
			btnNewButton_5.setEnabled(false);
			textArea.setEnabled(false);
		}
		
		JLabel lblNewLabel_5 = new JLabel("Subtotal:");
		lblNewLabel_5.setBounds(386, 338, 68, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("% IVA:");
		lblNewLabel_6.setBounds(386, 369, 68, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Monto IVA:");
		lblNewLabel_7.setBounds(386, 400, 68, 14);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("TOTAL:");
		lblNewLabel_8.setBounds(386, 431, 68, 14);
		getContentPane().add(lblNewLabel_8);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	};
	



	