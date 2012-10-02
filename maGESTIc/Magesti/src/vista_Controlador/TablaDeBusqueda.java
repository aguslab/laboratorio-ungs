package vista_Controlador;

import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Orden_Trabajo;

@SuppressWarnings("serial")
public class TablaDeBusqueda extends JInternalFrame 
{
	private JPanel jpMostrar = new JPanel ();
	private DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private JTable tablaBusqueda;
	TablaDeBusqueda(String titulo) 
	{
		
		super (titulo, false, true, false, true);
		setSize (475, 280);
		jpMostrar.setLayout (null);
		jspTabla = new JScrollPane (tablaBusqueda);
		jspTabla.setBounds (10, 11, 445, 223);
		jpMostrar.add (jspTabla);
		
		tablaBusqueda = new JTable();
		tablaBusqueda.setModel(new DefaultTableModel(new Object[][] {},new String[] {}));
		jspTabla.setViewportView(tablaBusqueda);
		getContentPane().add (jpMostrar);
		setVisible (true);
	}
		
	void buscarOT()
		{
			// Llenamos el modelo
		dtmMagesti = new DefaultTableModel(null, getColumnas());

			setFilas();

			tablaBusqueda.setModel(dtmMagesti);
			jspTabla.add(tablaBusqueda);
			this.add(jspTabla);
			this.setSize(500, 200);

			jspTabla.setViewportView(tablaBusqueda);

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
			// Conectar a PostgreSQL\\
			ResultSet result = ConexionDB.getbaseDatos().consultar("SELECT * FROM orden_trabajo");
			// CachedRowSet crs =
			// cnndb.Function("SELECT deptno, dname, loc FROM dept");

			Object datos[] = new Object[12]; // Numero de columnas de la tabla

			try 
			{
				while (result.next()) 
				{
					for (int i = 0; i < 12; i++) 
					{
						datos[i] = result.getObject(i + 1);
					}
					dtmMagesti.addRow(datos);
				}
				// result.close();
			} 
			catch (Exception e) 
			{
			}
	}

}