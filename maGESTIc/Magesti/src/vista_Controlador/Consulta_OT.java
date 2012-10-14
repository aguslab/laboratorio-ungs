package vista_Controlador;

import java.sql.ResultSet;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Orden_Trabajo;

public class Consulta_OT extends JFrame 
{

	DefaultTableModel dtmEjemplo;
	JTable tblEjemplo;
	JScrollPane scpEjemplo;

	public Consulta_OT() 
	{

		tblEjemplo = new JTable();
		scpEjemplo = new JScrollPane();

		// Llenamos el modelo
		dtmEjemplo = new DefaultTableModel(null, getColumnas());

		setFilas();

		tblEjemplo.setModel(dtmEjemplo);
		scpEjemplo.add(tblEjemplo);
		this.add(scpEjemplo);
		this.setSize(500, 200);

		scpEjemplo.setViewportView(tblEjemplo);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}

	// Encabezados de la tabla
	private String[] getColumnas() 
	{
		String columna[] = Orden_Trabajo.getNomColum();
		return columna;
	}

	private void setFilas() 
	{
		ResultSet result = ConexionDB.getbaseDatos().consultar
		(
			"SELECT * FROM orden_trabajo"
		);
		Object datos[] = new Object[12]; // Numero de columnas de la tabla
		try 
		{
			while (result.next()) 
			{
				for (int i = 0; i < 12; i++) 
				{
					datos[i] = result.getObject(i + 1);
					
				}
				
				dtmEjemplo.addRow(datos);
			}

			// result.close();
		} 
		catch (Exception e) 
		{
		}
	}

	public static void main(String args[]) 
	{
		Consulta_OT obj1 = new Consulta_OT();
		obj1.setVisible(true);
	}

}
