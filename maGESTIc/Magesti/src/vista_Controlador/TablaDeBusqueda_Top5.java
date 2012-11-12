package vista_Controlador;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import Modelo.ConexionDB;
import Modelo.Orden_Trabajo;
import Modelo.Proceso;
import Modelo.Procesos_x_OT;
import Modelo.Proveedor;

public class TablaDeBusqueda_Top5 extends JInternalFrame 
{

	private JPanel jpMostrar = new JPanel ();
	private static DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private static JTable tablaBusquedaTop5;
	
	private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
	private Dimension dimBarra = null; 
	
	public TablaDeBusqueda_Top5()
	{
		super ("5 Ordenes de Trabajo más Proximas", false, false, false, false);
		setSize (475, 280);
		jpMostrar.setLayout (new GridLayout (1,1));
		jspTabla = new JScrollPane (tablaBusquedaTop5);
		jpMostrar.add (jspTabla);
		tablaBusquedaTop5 = new JTable();
		tablaBusquedaTop5.setEnabled(false);
		tablaBusquedaTop5.getTableHeader().setReorderingAllowed(false);
		
		ocultarBarraTitulo();
		
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
				
				Integer id_OT=Metodos.FacturaAEntero(tablaBusquedaTop5.getValueAt(filaElegida, 0).toString());
				
				new BusquedaOrdenTrabajo(nuevaOT, id_OT);
			}
		});
		getContentPane().add (jpMostrar);
		
		// Llenamos el modelo
	dtmMagesti = new DefaultTableModel(null, getColumnas());

		setFilas();

		tablaBusquedaTop5.setModel(dtmMagesti);
		tablaBusquedaTop5.getColumnModel().getColumn(0).setPreferredWidth(85);
		jspTabla.add(tablaBusquedaTop5);
		this.setSize(500, 200);

		jspTabla.setViewportView(tablaBusquedaTop5);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
}
		
		// Encabezados de la tabla
		private static String[] getColumnas() 
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
				"SELECT DISTINCT o.id_orden_trabajo, c.razon_social,o.cantidad_a_entregar, o.f_confeccion,o.f_prometida, o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c  WHERE f_prometida>=" + fechaHoy
						+ " AND o.id_cliente=c.id_cliente AND o.estado <>" + cerrada + " ORDER BY f_prometida LIMIT 0,5;");


		Integer CantColumnas = getColumnas().length;

		Object datos[] = new Object[CantColumnas]; // Numero de columnas de la
													// tabla
		
		try 
		{
			while (result.next())
			{
				Integer id_ot=null;
				Integer id_proc=null;
				for (int i = 0; i < CantColumnas; i++) 
				{
					//porque las ultimas dos columnas no las obtengo de la consulta
					if(i<CantColumnas-2){
						datos[i] = result.getObject(i + 1);	
					}
					
					if(i==0)
					{
						id_ot=(Integer) datos[i];
						datos[i] = Metodos.EnteroAFactura((Integer) datos[i]);
					}
					if(i==3)
					{
						datos[i] = Metodos.YMDaDMY(datos[i].toString());
					}
					if(i==4)
					{
						datos[i] = Metodos.YMDaDMY(datos[i].toString());
					}
					if(i==7){
						datos[i]=Proceso.getNombreProceso(Procesos_x_OT.getIdProcEnEjecucion(id_ot));
						id_proc=Proceso.getIdProceso(datos[i].toString());
					}
					if(i==8){
						if(!datos[i-1].toString().equals("")){
							datos[i]=Proveedor.getRazonSocial(Procesos_x_OT.getIdProveedorSegunIdOT_Id_Proc(id_ot, id_proc));	
						}else{
							datos[i]="";
						}
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
				e.printStackTrace();
			}
		}
		
	public void ocultarBarraTitulo() {
		Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI())
				.getNorthPane();
		dimBarra = Barra.getPreferredSize();
		Barra.setSize(0, 0);
		Barra.setPreferredSize(new Dimension(0, 0));
		repaint();
	}
}
