package vista_Controlador;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Proveedor;
import Modelo.Variante;
import Modelo.Procesos_x_OT;

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
		boolean top5OT = top5;
		setSize (475, 280);
		jpMostrar.setLayout (new GridLayout (1,1));
		jspTabla = new JScrollPane (tablaBusqueda);
		jpMostrar.add (jspTabla);
		tablaBusqueda = new JTable();
		tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		
		tablaBusqueda.addMouseListener
		(
			new MouseAdapter() 
			{
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
				
				nuevaOT.getTipoProducto().setText((String) tablaBusqueda.getValueAt(filaElegida, 1));
				nuevaOT.getTipoProducto().setEditable(false);
				
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
				nuevaOT.getTxtNombreOT().setEditable(false);
				
				nuevaOT.getTxtDescripcion().setText((String) tablaBusqueda.getValueAt(filaElegida, 6));
				nuevaOT.getTxtDescripcion().setEditable(false);
				
				nuevaOT.getTxtCantidadAEntregar().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 7)));
				nuevaOT.getTxtCantidadAEntregar().setEditable(false);
				
				nuevaOT.getTxtPreimpresion().setText(Integer.toString((Integer) tablaBusqueda.getValueAt(filaElegida, 8)));
				nuevaOT.getTxtPreimpresion().setEditable(false);
				
				nuevaOT.getTxtAncho().setText((tablaBusqueda.getValueAt(filaElegida, 9)).toString());
				nuevaOT.getTxtAncho().setEditable(false);
				
				nuevaOT.getTxtAlto().setText(tablaBusqueda.getValueAt(filaElegida, 10).toString());
				nuevaOT.getTxtAlto().setEditable(false);
				
				//nuevaOT.getChbApaisado().getModel().setSelected((Boolean) tablaBusqueda.getValueAt(filaElegida, 11));
				nuevaOT.getChbApaisado().getModel().setSelected(Modelo.Orden_Trabajo.esApaisadaB(tablaBusqueda.getValueAt(filaElegida, 11).toString()));
				nuevaOT.getChbApaisado().setEnabled(false);
				
				nuevaOT.getEstado().getModel().setSelectedItem((String)tablaBusqueda.getValueAt(filaElegida, 12));
				
				nuevaOT.getCliente().setSelectedItem(tablaBusqueda.getValueAt(filaElegida, 2).toString());
				nuevaOT.getCliente().setEnabled(false);
				nuevaOT.getBtnLimpiarCampos().setEnabled(false);
				
				//Muestra los datos de la tabla Elemento
				Integer id_OT=Orden_Trabajo.FacturaAEntero(nuevaOT.getTxtNro().getText());
				Integer cantFilas = Elemento.cantidadFilas(id_OT);
				ArrayList<String> elemento = Elemento.nombreDeElemento(id_OT);
				ArrayList<Integer> cantidad = Elemento.cantidadDeElemento(id_OT);
				DefaultTableModel temp = (DefaultTableModel) nuevaOT.getTablaElementos().getModel();

				Object nuevaFilaElemento[]= {"",""};
				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFilaElemento);
					temp.setValueAt(elemento.get(i), i, 0);
					temp.setValueAt(cantidad.get(i), i, 1);	
				}
				
				//Muestra los datos de la tabla Materiales
				
				//ArrayList<Integer> tipo_Elemento = Materiales.getID_elemento(id_OT);
				ArrayList<Integer> gramaje = Materiales.getGramaje(id_OT);
				ArrayList<Integer> poses_x_pliego = Materiales.getPoses_x_pliego(id_OT);
				ArrayList<Integer> pliegos_netos = Materiales.getPliegos_netos(id_OT);
				ArrayList<Integer> pliegos_en_demasia = Materiales.getPliegos_en_demasia(id_OT);
				ArrayList<Integer> hojas = Materiales.getHojas(id_OT);
				ArrayList<Integer> id_calidad = Materiales.getID_Calidad(id_OT);
				ArrayList<Integer> id_variante = Materiales.getID_Variante(id_OT);
				ArrayList<Integer> id_formato_papel = Materiales.getId_formato_papel(id_OT);
				ArrayList<Integer> pliegos_x_hoja = Materiales.getPliegos_x_Hojas(id_OT);
				
				DefaultTableModel tempMat = (DefaultTableModel) nuevaOT.getTablaMateriales().getModel();

				Object nuevaFilaMateriales[]= {"",0, 0,"", "", "", 0, 0, 0, 0, 0};
				cantFilas=Materiales.getID_Materiales(id_OT).size();
				for (int i = 0; i < cantFilas; i++) 
				{
					tempMat.addRow(nuevaFilaMateriales);
					tempMat.setValueAt(elemento.get(i), i, 0);

					tempMat.setValueAt(cantidad.get(i), i, 1);
					tempMat.setValueAt(gramaje.get(i), i, 2);	

					tempMat.setValueAt((Formato_Papel.getTamanio(id_formato_papel.get(i))), i, 3);	
					tempMat.setValueAt(Variante.getNombre(id_variante.get(i)), i, 4);	
					tempMat.setValueAt(Calidad.getNombre(id_calidad.get(i)), i, 5);	

					tempMat.setValueAt(pliegos_en_demasia.get(i), i, 6);	
					tempMat.setValueAt(poses_x_pliego.get(i), i, 7);	
					tempMat.setValueAt(pliegos_x_hoja.get(i), i, 8);	
					tempMat.setValueAt(hojas.get(i), i, 9);	
					tempMat.setValueAt(pliegos_netos.get(i), i, 10);	
				}
				
				//Muestra los datos de la tabla Orden de ejecucion
				cantFilas = Procesos_x_OT.getCantidadFilas(id_OT);
				ArrayList<String> procesos = Procesos_x_OT.BuscarProc_x_OT(id_OT);
				ArrayList<Boolean> tercerizadas = Procesos_x_OT.getTercerizada(id_OT);
				ArrayList<Integer> proveedor = Procesos_x_OT.getProveedor(id_OT);
				ArrayList<String> observaciones = Procesos_x_OT.getObservaciones(id_OT);
				ArrayList<Boolean> cumplida = Procesos_x_OT.getCumplida(id_OT);
				
				DefaultTableModel tempOE = (DefaultTableModel) nuevaOT.getTablaOrdenEjecucion().getModel();

				Object nuevaFilaOrdenEjecucion[]= {"",false, "","", false};

				for (int i = 0; i < cantFilas; i++) 
				{
					tempOE.addRow(nuevaFilaOrdenEjecucion);
					tempOE.setValueAt(procesos.get(i), i, 0);
					tempOE.setValueAt(tercerizadas.get(i), i, 1);	
					tempOE.setValueAt(Proveedor.getRazonSocial(proveedor.get(i)), i, 2);	
					tempOE.setValueAt(observaciones.get(i), i, 3);	
					tempOE.setValueAt(cumplida.get(i), i, 4);	
				}
				
			}
			
		});
		
		getContentPane().add (jpMostrar);
		dtmMagesti = new DefaultTableModel(null, getColumnas());
		setFilas(top5);
		tablaBusqueda.setModel(dtmMagesti);
		jspTabla.add(tablaBusqueda);
		jspTabla.setViewportView(tablaBusqueda);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);	
	
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
							if (i==11)
							{
								
								//System.out.println(Modelo.Orden_Trabajo.esApaisadaS((Boolean) datos[11]));
								datos[i]=Modelo.Orden_Trabajo.esApaisadaS((Boolean) datos[11]);
							}
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
				ResultSet result = ConexionDB
				.getbaseDatos()
				.consultar
				(
					"SELECT e.id_elemento, e.id_orden_trabajo, e.tipo_elemento, e.cantidad FROM elemento e, orden_trabajo o WHERE o.id_orden_trabajo = e.id_orden_trabajo"
				);
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