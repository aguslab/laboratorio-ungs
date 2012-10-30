package vista_Controlador;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Modelo.ConexionDB;
import Modelo.Orden_Trabajo;
import Modelo.Proceso;
import Modelo.Procesos_x_OT;

public class TablaDeBusqueda_Top5 extends JInternalFrame 
{

	private JPanel jpMostrar = new JPanel ();
	private static DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private static JTable tablaBusquedaTop5;
	
	public TablaDeBusqueda_Top5()
	{
		super ("Consultar Orden de Trabajo", true, true, true, true);
		setSize (475, 280);
		jpMostrar.setLayout (new GridLayout (1,1));
		jspTabla = new JScrollPane (tablaBusquedaTop5);
		jpMostrar.add (jspTabla);
		tablaBusquedaTop5 = new JTable();
		tablaBusquedaTop5.setEnabled(false);
		tablaBusquedaTop5.getTableHeader().setReorderingAllowed(false);
		
		tablaBusquedaTop5.addMouseListener
		(
			new MouseAdapter() 
			{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int filaElegida = tablaBusquedaTop5.rowAtPoint(arg0.getPoint());
				final OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
				
				
				getDesktopPane().add(nuevaOT);
				nuevaOT.show ();
				BusquedaOrdenTrabajo bot= new BusquedaOrdenTrabajo(nuevaOT, tablaBusquedaTop5,filaElegida);
			}
		});
		getContentPane().add (jpMostrar);
		
		// Llenamos el modelo
	dtmMagesti = new DefaultTableModel(null, getColumnas());

		setFilas();

		tablaBusquedaTop5.setModel(dtmMagesti);
		jspTabla.add(tablaBusquedaTop5);
		this.setSize(500, 200);

		jspTabla.setViewportView(tablaBusquedaTop5);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
}
		
		// Encabezados de la tabla
		private String[] getColumnas() 
		{
			String columna[] = Orden_Trabajo.getNomColumTop5();
			return columna;
		}

				
		private static void setFilas() 
 {
		Calendar fecha = Calendar.getInstance();
		Integer mm = fecha.get(Calendar.MONTH) + 1;
		Integer dd = fecha.get(Calendar.DATE);
		Integer aaaa = fecha.get(Calendar.YEAR);
		String fechaHoy = "'" + aaaa + "-" + mm + "-" + dd + "'";
		String cerrada = "'" + "Cerrada" + "'";

		ResultSet result;
		result = ConexionDB.getbaseDatos().consultar(
				"SELECT o.id_orden_trabajo, c.razon_social, o.f_confeccion,o.f_prometida,o.cantidad_a_entregar, o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c  WHERE f_prometida>=" + fechaHoy
						+ "AND o.id_cliente=c.id_cliente AND o.estado <>" + cerrada + "ORDER BY f_prometida LIMIT 0,5;");

		Integer CantColumnas = 7;
		Object datos[] = new Object[CantColumnas]; // Numero de columnas de la
													// tabla
		try 
		{
			while (result.next())
			{
				for (int i = 0; i < CantColumnas; i++) 
				{
					datos[i] = result.getObject(i + 1);
					if(i==0)
					{
						datos[i] = Metodos.EnteroAFactura((Integer) datos[0]);
					}
					
				}
				dtmMagesti.addRow(datos);
			}
		} 
		catch (Exception e) 
		{
		}
	}
		
		
		static void Actualizar()
		{
			try
			{
				Metodos.borrarFilas((DefaultTableModel)tablaBusquedaTop5.getModel());
				setFilas();
			}
			catch(Exception e)
			{
				
			}
		}
}
