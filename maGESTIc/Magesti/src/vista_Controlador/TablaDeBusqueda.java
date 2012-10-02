package vista_Controlador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TablaDeBusqueda extends JInternalFrame 
{

	private JPanel jpMostrar = new JPanel ();
	private DefaultTableModel dtmMagesti;
	private JTable tbTabla;
	private JScrollPane jspTabla;
	TablaDeBusqueda(String titulo) 
	{
		
		super (titulo, false, true, false, true);
		setSize (475, 280);
		jpMostrar.setLayout (null);
		jspTabla = new JScrollPane (tbTabla);
		jspTabla.setBounds (20, 20, 425, 200);
		jpMostrar.add (jspTabla);
		getContentPane().add (jpMostrar);
		setVisible (true);

	}
	


}