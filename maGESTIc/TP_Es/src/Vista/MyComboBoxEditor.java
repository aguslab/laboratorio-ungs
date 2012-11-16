package Vista;


import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;


@SuppressWarnings("serial")
public class MyComboBoxEditor extends DefaultCellEditor
{
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MyComboBoxEditor(String[] items) 
	{
		super(new JComboBox(items));
	}
}

