package vista_Controlador;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableCellEditor;

public class MyComboBoxEditor extends DefaultCellEditor implements TableCellEditor 
{
	public MyComboBoxEditor(String[] items) 
	{
		super(new JComboBox<Object>(items));
	}
}

