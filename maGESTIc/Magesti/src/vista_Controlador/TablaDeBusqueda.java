package vista_Controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Modelo.ConexionDB;


@SuppressWarnings("serial")
public class TablaDeBusqueda extends JInternalFrame 
{
	private OrdenDeTrabajo nuevaOT;
	private JPanel jpMostrar = new JPanel ();
	private static DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private static JTable tablaBusqueda;
	private TableRowSorter<DefaultTableModel> trsfiltro;


	TablaDeBusqueda() 
	{
		super ("Buscar Orden de Trabajo", true, true, true, true);
		
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
			getContentPane().setLayout(null);
		
			JButton btnCerrar = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
			btnCerrar.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					setVisible (false);
					dispose();
				}
			});
			btnCerrar.setBounds(1215, 556, 120, 35);
			getContentPane().add(btnCerrar);
			
			jspTabla= new JScrollPane();
			jspTabla.setBounds(0, 45, 1325, 486);
			
			jpMostrar= new JPanel();
			jpMostrar.setBounds(10, 7, d.width-35, d.height-230);
			jpMostrar.setLayout(null);

			tablaBusqueda= new JTable();
			
			dtmMagesti= new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"<html>Nro Orden<br> Trabajo</html>", "Producto", "Cliente", "<html>Fecha de<br> confeccion</html>", "<html>Fecha<br> prometida</html>", "<html>Nombre del<br> trabajo</html>", "Descripcion", "<html>Cantidad a<br> entregar</html>", "<html>Preimpresiones </html>", "Ancho", "Alto", "Apaisado", "Estado", "<html>Hojas<br> Utilizadas</html>"
							}
						) {
							Class[] columnTypes = new Class[] {
								String.class, String.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
								false, false, false, false, false, false, false, false, false, false, false, false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						};

			setFilas();
			
			tablaBusqueda.setModel(dtmMagesti);
			
			tablaBusqueda.getColumnModel().getColumn(0).setPreferredWidth(85);
			tablaBusqueda.getColumnModel().getColumn(8).setPreferredWidth(85);

			jspTabla.setViewportView(tablaBusqueda);
			getContentPane().add(jpMostrar);
			jpMostrar.add(jspTabla);
			tablaBusqueda.getTableHeader().setReorderingAllowed(false);
			
			
			/*
			 * Ocultar OT cerradas
			 */
			JButton btnOcultarCerradas = new JButton("Ocultar Cerradas");
			btnOcultarCerradas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Metodos.borrarFilas((DefaultTableModel) tablaBusqueda.getModel());
					setFilasSinCerradas();
					
				}
			});
			btnOcultarCerradas.setBounds(38, 10, 125, 23);
			jpMostrar.add(btnOcultarCerradas);
			

			/*
			 * ACCION AL SELECCIONAR UNA FILA
			 */
			tablaBusqueda.addMouseListener
			(
				new MouseAdapter() 
				{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int filaElegida = tablaBusqueda.rowAtPoint(arg0.getPoint());
					if (nuevaOT == null || nuevaOT.isClosed() || (!nuevaOT.isShowing() && !nuevaOT.isIcon())) 
					{
						nuevaOT = new OrdenDeTrabajo ();
						getDesktopPane().add(nuevaOT);
						nuevaOT.show ();
					}
					else
					{
						nuevaOT.toFront();
					}
					Integer id_OT = Metodos.FacturaAEntero(tablaBusqueda.getValueAt(filaElegida, 0).toString());
					new BusquedaOrdenTrabajo(nuevaOT, id_OT);
				}
			});
			
			
			
			
			jspTabla.setViewportView(tablaBusqueda);
			getContentPane().add(jpMostrar);
			jpMostrar.add(jspTabla);
			
			JButton btnMostrarTodas = new JButton("Mostrar Todas");
			btnMostrarTodas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Actualizar();
				}
			});
			btnMostrarTodas.setBounds(190, 10, 125, 23);
			jpMostrar.add(btnMostrarTodas);
			
			
	
		}
	
	
//		private void filtro(String cadena, int columna) {
//		
//		trsfiltro = new TableRowSorter<DefaultTableModel>(dtmMagesti);
//		trsfiltro.setRowFilter(RowFilter.regexFilter(cadena, columna));
//		tablaBusqueda.setModel(dtmMagesti);
//		tablaBusqueda.setRowSorter(trsfiltro);
//		
//		}

	private static void setFilas() 
	 {

		ResultSet result = ConexionDB.getbaseDatos().consultar(
							"SELECT o.id_orden_trabajo,o.nombre_producto, c.razon_social, o.f_confeccion,o.f_prometida,o.nombre_trabajo,o.descripcion,o.cantidad_a_entregar, o.cantidad_preimpresion, o.ancho, o.alto, o.apaisado,o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c WHERE o.id_orden_trabajo!=1 AND o.id_cliente=c.id_cliente order by id_orden_trabajo");
		
			Integer CantColumnas=14;
			Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

			try 
			{
				while (result.next()) 
				{
					
					for (int i = 0; i < CantColumnas; i++) 
					{
						datos[i] = result.getObject(i + 1);
						if(i==0)
						{
							datos[i]=Metodos.EnteroAFactura((Integer) datos[0]);
						}
						if(i==3)
						{
							datos[i] = Metodos.YMDaDMY(datos[i].toString());
						}
						if(i==4)
						{
							datos[i] = Metodos.YMDaDMY(datos[i].toString());
						}
						if (i==11)
						{
							datos[i]=Metodos.esApaisadaS((Boolean) datos[11]);
						}
					}
					dtmMagesti.addRow(datos);
				}
			}
			catch (Exception e) 
			{
				
			}
		}
	
	
	private static void setFilasSinCerradas() 
 {
		ResultSet result = ConexionDB
				.getbaseDatos()
				.consultar(
						"SELECT o.id_orden_trabajo,o.nombre_producto, c.razon_social, o.f_confeccion,o.f_prometida,o.nombre_trabajo,o.descripcion,o.cantidad_a_entregar, o.cantidad_preimpresion, o.ancho,o.alto, o.apaisado,o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c WHERE o.id_orden_trabajo!=1 AND o.id_cliente=c.id_cliente AND o.estado!= 'Cerrada' ORDER BY id_orden_trabajo");

		Integer CantColumnas = 14;
		Object datos[] = new Object[CantColumnas]; // Numero de columnas de la
													// tabla

		try {
			while (result.next()) {

				for (int i = 0; i < CantColumnas; i++) {
					datos[i] = result.getObject(i + 1);
					if (i == 0) {
						datos[i] = Metodos.EnteroAFactura((Integer) datos[0]);
					}
					if (i == 3) {
						datos[i] = Metodos.YMDaDMY(datos[i].toString());
					}
					if (i == 4) {
						datos[i] = Metodos.YMDaDMY(datos[i].toString());
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
			
			
	public static void Actualizar()
	{
		if(tablaBusqueda != null){
		Metodos.borrarFilas((DefaultTableModel)tablaBusqueda.getModel());
		setFilas();
		}
	}
}
