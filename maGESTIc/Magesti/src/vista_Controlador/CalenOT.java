package vista_Controlador;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalenOT extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	

	/**
	 * Create the dialog.
	 */
	public CalenOT(String txta) {
		super();
		setBounds(200, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOrdenesDeTrabajo = new JLabel("ORDENES DE TRABAJO PARA ESTA FECHA");
			lblOrdenesDeTrabajo.setFont(new Font("DejaVu Serif", Font.BOLD, 16));
			lblOrdenesDeTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrdenesDeTrabajo.setBounds(10, 0, 414, 32);
			contentPanel.add(lblOrdenesDeTrabajo);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBackground(SystemColor.activeCaption);
			textArea.setBounds(107, 43, 299, 175);
			textArea.setText(txta);
			textArea.setEditable(false);
			contentPanel.add(textArea);
		}
		{
			JLabel lblNroOt = new JLabel("Nro OT:");
			lblNroOt.setBounds(20, 48, 46, 14);
			contentPanel.add(lblNroOt);
		}
		{
			JLabel lblNombreOt = new JLabel("Nombre OT:");
			lblNombreOt.setBounds(20, 73, 66, 14);
			contentPanel.add(lblNombreOt);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
