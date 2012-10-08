package vista_Controlador;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;
import Modelo.Elemento;
import Modelo.Orden_Trabajo;
import java.awt.GridLayout;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



@SuppressWarnings("serial")
public class TablaDeBusqueda extends JInternalFrame 
{
	private JPanel jpMostrar = new JPanel ();
	private DefaultTableModel dtmMagesti;
	private DefaultTableModel dtmElemento;
	private JScrollPane jspTabla;
	private JTable tablaBusqueda;
	
	
	TablaDeBusqueda(String titulo,boolean top5) 
	{
		super (titulo, true, true, true, true);
		boolean top5OT=top5;
		setSize (475, 280);
		jpMostrar.setLayout (new GridLayout (1,1));
		jspTabla = new JScrollPane (tablaBusqueda);
		jpMostrar.add (jspTabla);
		tablaBusqueda = new JTable();
		tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		
		tablaBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int filaElegida = tablaBusqueda.rowAtPoint(arg0.getPoint());
				OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
				
			
				getDesktopPane().add(nuevaOT);
				nuevaOT.show ();
				nuevaOT.getEstado().setEnabled(true);
				nuevaOT.getTxtCantidadDeHojasUtilizadas().setEnabled(true);
				
				//Cargo en la ventana de OT los valores de la fila elegida
				nuevaOT.getTxtNro().setText(Orden_Trabajo.EnteroAFactura((Integer)tablaBusqueda.getValueAt(filaElegida, 0)));
				/*nuevaOT.id_OT=(Integer) tablaBusqueda.getValueAt(filaElegida, 0);
				nuevaOT.getTxtNro().setText(id_OT+"");*/
				
				nuevaOT.getTipoProducto().setText((String) tablaBusqueda.getValueAt(filaElegida, 1));
				nuevaOT.getTipoProducto().setEnabled(false);
				
				nuevaOT.getCboMes().getModel().setSelectedItem(dameMes(separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 1)));
				nuevaOT.getCboMes().setEnabled(false);
				
				nuevaOT.getCboDia().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 2));
				nuevaOT.getCboDia().setEnabled(false);
				
				nuevaOT.getCboAnio().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 3).toString(), 0));
				nuevaOT.getCboAnio().setEnabled(false);
				
				nuevaOT.getCboMes2().getModel().setSelectedItem(dameMes(separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 1)));
				nuevaOT.getCboMes2().setEnabled(false);
				
				nuevaOT.getCboDia2().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 2));
				nuevaOT.getCboDia2().setEnabled(false);
				
				nuevaOT.getCboAnio2().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 4).toString(), 0));
				nuevaOT.getCboAnio2().setEnabled(false);
				
				nuevaOT.getTxtNombreOT().setText((String) tablaBusqueda.getValueAt(filaElegida, 5));
				nuevaOT.getTxtNombreOT().setEnabled(false);
				
				nuevaOT.getTxtDescripcion().setText((String) tablaBusqueda.getValueAt(filaElegida, 6));
				nuevaOT.getTxtDescripcion().setEnabled(false);
				
				nuevaOT.getTxtCantidadAEntregar().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 7)));
				nuevaOT.getTxtCantidadAEntregar().setEnabled(false);
				
				nuevaOT.getTxtPreimpresion().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 8)));
				nuevaOT.getTxtPreimpresion().setEnabled(false);
				
				nuevaOT.getTxtAncho().setText((tablaBusqueda.getValueAt(filaElegida, 9)).toString());
				nuevaOT.getTxtAncho().setEnabled(false);
				
				nuevaOT.getTxtAlto().setText(tablaBusqueda.getValueAt(filaElegida, 10).toString());
				nuevaOT.getTxtAlto().setEnabled(false);
				
				nuevaOT.getChbApaisado().getModel().setSelected((Boolean) tablaBusqueda.getValueAt(filaElegida, 11));
				nuevaOT.getChbApaisado().setEnabled(false);
				
				nuevaOT.getEstado().getModel().setSelectedItem((String)tablaBusqueda.getValueAt(filaElegida, 12));
				
				nuevaOT.getCliente().setSelectedItem(tablaBusqueda.getValueAt(filaElegida, 2).toString());
				nuevaOT.getCliente().setEnabled(false);

				nuevaOT.getBtnLimpiarCampos().setEnabled(false);
				
				Integer id_OT=Orden_Trabajo.FacturaAEntero(nuevaOT.getTxtNro().getText());
				Integer cantFilas = Elemento.cantidadFilas(id_OT);
				ArrayList<String> elemento = Elemento.nombreDeElemento(id_OT);
				ArrayList<Integer> cantidad = Elemento.cantidadDeElemento(id_OT);
				DefaultTableModel temp = (DefaultTableModel) nuevaOT.getTablaElementos().getModel();

				Object nuevaFila[]= {"",""};
				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFila);
					temp.setValueAt(elemento.get(i), i, 0);
					temp.setValueAt(cantidad.get(i), i, 1);	
				}
				

				
				//Esto agrega la tabla llena al OT creado.
				//nuevaOT.add(llenarTablaElemento(nuevaOT.getTablaElementos()));
			//	nuevaOT.add(llenarTablaElemento(tablaAuxElementos));
			}
		});
		getContentPane().add (jpMostrar);
		dtmMagesti = new DefaultTableModel(null, getColumnas());
		setFilas(top5);
		tablaBusqueda.setModel(dtmMagesti);
		jspTabla.add(tablaBusqueda);
		jspTabla.setViewportView(tablaBusqueda);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		/*
		tablaBusqueda.setEnabled(false);
		tablaBusqueda.setModel(new DefaultTableModel(new Object[][] {},new String[] {}));
		jspTabla.setViewportView(tablaBusqueda);
		getContentPane().add (jpMostrar);
		//setVisible (true);
		 */
		
	
			// Llenamos el modelo
		dtmMagesti = new DefaultTableModel(null, getColumnas());

			setFilas(top5);

			tablaBusqueda.setModel(dtmMagesti);
			jspTabla.add(tablaBusqueda);
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

		
		
		private void setFilas(boolean top5) 
		 {
			Calendar fecha= Calendar.getInstance();
			Integer mm=fecha.get(Calendar.MONTH)+1;
			Integer dd=fecha.get(Calendar.DATE);
			Integer aaaa=fecha.get(Calendar.YEAR);	
			String fechaHoy="'"+aaaa+"-"+mm+"-"+dd+"'";
			
			ResultSet result;
			if(top5){
				result = ConexionDB
						.getbaseDatos()
						.consultar(
								"select * from orden_trabajo where f_prometida>"+fechaHoy+"order by f_prometida limit 0,5;");	
			}else{
				result = ConexionDB
						.getbaseDatos()
						.consultar(
								"SELECT o.id_orden_trabajo,o.nombre_producto, c.razon_social, o.f_confeccion,o.f_prometida,o.nombre_trabajo,o.descripcion,o.cantidad_a_entregar, o.cantidad_preimpresion, o.ancho,o.alto, o.apaisado,o.estado FROM orden_trabajo o, cliente c where o.id_cliente=c.id_cliente order by id_orden_trabajo");
			}
						
			
				Integer CantColumnas=13;
				Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

				try 
				{
					while (result.next()) 
					{
						
						for (int i = 0; i < CantColumnas; i++) 
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
		
		//Consulta sql para conseguir los datos que se van a mostrar en la tabla de Elementos
		private void setFilasDeElementos(JTable elemento) 
		 {
			
				// Conectar a MySQL\\
				ResultSet result = ConexionDB
						.getbaseDatos()
						.consultar(
								"SELECT e.id_elemento, e.id_orden_trabajo, e.tipo_elemento, e.cantidad FROM elemento e, orden_trabajo o WHERE o.id_orden_trabajo = e.id_orden_trabajo");
				Integer CantColumnas = 4;
				Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

				try 
				{
					while (result.next()) 
					{
						
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
						}
						dtmElemento.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
		

		//Llena la tabla de elemento con los datos conseguidos 
		private JTable llenarTablaElemento(JTable tablaElemento) 
		{
			dtmElemento = (DefaultTableModel) tablaElemento.getModel();
			setFilasDeElementos(tablaElemento);

			tablaElemento.setModel(dtmElemento);
			
			return tablaElemento;
		}
		
		static String separar(String fecha, int numero)
		{
		StringTokenizer s = new StringTokenizer(fecha, "-");
		int cantidadChars = 0;
		int numeroIdentificador = numero;
		String parte = "";
		while(s.hasMoreTokens())
		{
			String elemento = s.nextToken();
			if (cantidadChars < 1 && numeroIdentificador == 0 )
			{
				parte=elemento;
				break;
			}
			else if((cantidadChars >= 1 && cantidadChars < 2) && numeroIdentificador == 1)
			{
				parte=elemento;
				break;
			}
			else if((cantidadChars >=2 && cantidadChars < 3) && numeroIdentificador == 2)
			{
				parte=elemento;
				break;
			}
				cantidadChars++;
		}
			return parte;
		}

		
		static String dameMes(String mes)
		{
			if(mes.equals("01") | mes.equals("1"))
			{
				return "Enero";
			}
			else if(mes.equals("02") | mes.equals("2"))
			{
				return "Febrero";
			}
			else if(mes.equals("03") | mes.equals("3"))
			{
				return "Marzo";
			}
			else if(mes.equals("04") | mes.equals("4"))
			{
				return "Abril";
			}
			else if(mes.equals("05") | mes.equals("5"))
			{
				return "Mayo";
			}
			else if(mes.equals("06") | mes.equals("6"))
			{
				return "Junio";
			}
			else if(mes.equals("07") | mes.equals("7"))
			{
				return "Julio";
			}
			else if(mes.equals("08") | mes.equals("8"))
			{
				return "Agosto";
			}
			else if(mes.equals("09") | mes.equals("9"))
			{
				return "Septiembre";
			}
			else if(mes.equals("10"))
			{
				return "Octubre";
			}
			else if(mes.equals("11"))
			{
				return "Noviembre";
			}
			else
			{
				return "Diciembre";
			}
		}
		
		
}