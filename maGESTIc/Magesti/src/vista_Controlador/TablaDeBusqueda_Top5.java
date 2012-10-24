package vista_Controlador;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Hojas_Utilizadas;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Procesos_x_OT;
import Modelo.Proveedor;
import Modelo.Variante;


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
			String columna[] = Orden_Trabajo.getNomColum();
			return columna;
		}

				
		private static void setFilas() 
 {
		Calendar fecha = Calendar.getInstance();
		Integer mm = fecha.get(Calendar.MONTH) + 1;
		Integer dd = fecha.get(Calendar.DATE);
		Integer aaaa = fecha.get(Calendar.YEAR);
		String fechaHoy = "'" + aaaa + "-" + mm + "-" + dd + "'";

		ResultSet result;
		result = ConexionDB.getbaseDatos().consultar(
				"SELECT o.id_orden_trabajo,o.nombre_producto, c.razon_social, o.f_confeccion,o.f_prometida,o.nombre_trabajo,o.descripcion,o.cantidad_a_entregar, o.cantidad_preimpresion, o.ancho,o.alto, o.apaisado,o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c  WHERE f_prometida>=" + fechaHoy
						+ "AND o.id_cliente=c.id_cliente " +"ORDER BY f_prometida LIMIT 0,5;");

		Integer CantColumnas = 14;
		Object datos[] = new Object[CantColumnas]; // Numero de columnas de la
													// tabla

		try {
			while (result.next()) {

				for (int i = 0; i < CantColumnas; i++) {
					datos[i] = result.getObject(i + 1);
					if(i==0){
						datos[i] = Metodos.EnteroAFactura((Integer) datos[0]);
					}
					if (i == 11) {
						datos[i] = Metodos.esApaisadaS((Boolean) datos[11]);
					}
				}
				dtmMagesti.addRow(datos);
			}
		} catch (Exception e) {
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
