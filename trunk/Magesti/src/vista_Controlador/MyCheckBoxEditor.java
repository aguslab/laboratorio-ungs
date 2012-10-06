package vista_Controlador;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class MyCheckBoxEditor extends DefaultCellEditor 
{
	public MyCheckBoxEditor() 
	{
		super(new JCheckBox());
	}
	
}
