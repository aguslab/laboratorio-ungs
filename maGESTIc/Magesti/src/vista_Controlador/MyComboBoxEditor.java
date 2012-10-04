package vista_Controlador;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class MyComboBoxEditor extends DefaultCellEditor implements TableCellEditor 
{
	public MyComboBoxEditor(String[] items) 
	{
		super(new JComboBox(items));
	}
}

