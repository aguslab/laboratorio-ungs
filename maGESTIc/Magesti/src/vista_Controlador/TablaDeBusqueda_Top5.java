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
				nuevaOT.getEstado().setEnabled(true);
				nuevaOT.getTxtCantidadDeHojasUtilizadas().setText((tablaBusquedaTop5.getValueAt(filaElegida, 13)).toString());
				nuevaOT.getTxtCantidadDeHojasUtilizadas().setEnabled(true);
				
				//Cargo en la ventana de OT los valores de la fila elegida
				nuevaOT.getTxtNro().setText(Metodos.EnteroAFactura((Integer)tablaBusquedaTop5.getValueAt(filaElegida, 0)));
				
				nuevaOT.getTipoProducto().setText((String) tablaBusquedaTop5.getValueAt(filaElegida, 1));
				nuevaOT.getTipoProducto().setEditable(false);
				
				nuevaOT.getCboMes().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 3).toString(), 1)));
				nuevaOT.getCboMes().setEnabled(false);
				
				nuevaOT.getCboDia().getModel().setSelectedItem(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 3).toString(), 2));
				nuevaOT.getCboDia().setEnabled(false);
				
				nuevaOT.getCboAnio().getModel().setSelectedItem(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 3).toString(), 0));
				nuevaOT.getCboAnio().setEnabled(false);
				
				nuevaOT.getCboMes2().getModel().setSelectedItem(Metodos.dameMes(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 4).toString(), 1)));
				nuevaOT.getCboMes2().setEnabled(false);
				
				nuevaOT.getCboDia2().getModel().setSelectedItem(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 4).toString(), 2));
				nuevaOT.getCboDia2().setEnabled(false);
				
				nuevaOT.getCboAnio2().getModel().setSelectedItem(Metodos.separar(tablaBusquedaTop5.getValueAt(filaElegida, 4).toString(), 0));
				nuevaOT.getCboAnio2().setEnabled(false);
				
				nuevaOT.getTxtNombreOT().setText((String) tablaBusquedaTop5.getValueAt(filaElegida, 5));
				nuevaOT.getTxtNombreOT().setEditable(false);
				
				nuevaOT.getTxtDescripcion().setText((String) tablaBusquedaTop5.getValueAt(filaElegida, 6));
				nuevaOT.getTxtDescripcion().setEditable(false);
				
				nuevaOT.getTxtCantidadAEntregar().setText(Integer.toString((Integer) tablaBusquedaTop5.getValueAt(filaElegida, 7)));
				nuevaOT.getTxtCantidadAEntregar().setEditable(false);
				
				nuevaOT.getTxtPreimpresion().setText(Integer.toString((Integer) tablaBusquedaTop5.getValueAt(filaElegida, 8)));
				nuevaOT.getTxtPreimpresion().setEditable(false);
				
				nuevaOT.getTxtAncho().setText(tablaBusquedaTop5.getValueAt(filaElegida, 9).toString());
				nuevaOT.getTxtAncho().setEditable(false);
				
				nuevaOT.getTxtAlto().setText(tablaBusquedaTop5.getValueAt(filaElegida, 10).toString());
				nuevaOT.getTxtAlto().setEditable(false);
				
				//nuevaOT.getChbApaisado().getModel().setSelected((Boolean) tablaBusqueda.getValueAt(filaElegida, 11));
				nuevaOT.getChbApaisado().getModel().setSelected(Metodos.esApaisadaB(tablaBusquedaTop5.getValueAt(filaElegida, 11).toString()));
				nuevaOT.getChbApaisado().setEnabled(false);
				
				nuevaOT.getEstado().getModel().setSelectedItem((String)tablaBusquedaTop5.getValueAt(filaElegida, 12));
				
				nuevaOT.getCliente().setSelectedItem(tablaBusquedaTop5.getValueAt(filaElegida, 2).toString());
				nuevaOT.getCliente().setEnabled(false);
				nuevaOT.getBtnLimpiarCampos().setEnabled(false);
				
				//permitir ingresar solo numeros en hojas utilizadas
				nuevaOT.getTxtCantidadDeHojasUtilizadas().addKeyListener(new KeyListener() 
				{
					
					@Override
					public void keyTyped(KeyEvent ke) 
					{
						char c = ke.getKeyChar ();
						if (!((Character.isDigit (c) || (c == KeyEvent.VK_BACK_SPACE)))) 
						{
							getToolkit().beep ();
							ke.consume ();
						}
					}
					public void keyReleased(KeyEvent arg0) {}
					public void keyPressed(KeyEvent arg0) {}
				});
				
				
				nuevaOT.getTablaElementos().setModel(new DefaultTableModel(new Object[][] {},
						new String[] {"Elemento", "Cantidad", "Hojas Utilizadas"}) 
					{
						Class[] columnTypes = new Class[] 
						{
							String.class, Integer.class, Integer.class
						};
						public Class getColumnClass(int columnIndex) 
						{
							return columnTypes[columnIndex];
						}
						boolean[] columnEditables = new boolean[] 
						{
							false, false, true
						};
						public boolean isCellEditable(int row, int column) 
						{
							return columnEditables[column];
						}
					});
				//Muestra los datos de la tabla Elemento
				Integer id_OT=Metodos.FacturaAEntero(nuevaOT.getTxtNro().getText());
				Integer cantFilas = Elemento.cantidadFilas(id_OT);
				ArrayList<String> elemento = Elemento.nombreDeElemento(id_OT);
				ArrayList<Integer> cantidad = Elemento.cantidadDeElemento(id_OT);
				ArrayList<Integer> cantHojasUtil= Hojas_Utilizadas.getCantHojas(Elemento.getIdElementos(id_OT));
				DefaultTableModel temp = (DefaultTableModel) nuevaOT.getTablaElementos().getModel();
				nuevaOT.getTablaElementos().setEnabled(false);
				Object nuevaFilaElemento[]= {"",""};
				Integer total=0;
				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFilaElemento);
					temp.setValueAt(elemento.get(i), i, 0);
					temp.setValueAt(cantidad.get(i), i, 1);	
					if(cantHojasUtil.size()>0){
						temp.setValueAt(cantHojasUtil.get(i), i, 2);	
					}
					total= total +cantHojasUtil.get(i);
					nuevaOT.getTxtCantidadDeHojasUtilizadas().setText(total.toString());
				}
				nuevaOT.getTxtCantidadAEntregar().setEditable(false);
				nuevaOT.getBtnAgregarFila().setEnabled(false);
				nuevaOT.getBtnBorrarFila().setEnabled(false);
				nuevaOT.getBtnAlmacenar().setEnabled(false);
				
				nuevaOT.getTablaElementos().setEnabled(true);
				
				
				
				
				
				//Muestra los datos de la tabla Materiales
				
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
				nuevaOT.getTablaMateriales().setEnabled(false);
				
				
				//Muestra los datos de la tabla Orden de ejecucion
				
				cantFilas = Procesos_x_OT.getCantidadFilas(id_OT);
				ArrayList<String> procesos = Procesos_x_OT.BuscarProc_x_OT(id_OT);
				ArrayList<Boolean> tercerizadas = Procesos_x_OT.getTercerizada(id_OT);
				ArrayList<Integer> proveedor = Procesos_x_OT.getProveedor(id_OT);
				ArrayList<String> observaciones = Procesos_x_OT.getObservaciones(id_OT);
				ArrayList<Boolean> cumplida = Procesos_x_OT.getCumplida(id_OT);
				
				//permite que la columna cumplida sea editable
				nuevaOT.getTablaOrdenEjecucion().setModel(new DefaultTableModel(new Object[][] {},
						new String[] {"Proceso", "Tercerizada", "Proveedor", "Observaciones", "Cumplida"}) 
					{
						Class[] columnTypes = new Class[] 
						{
							String.class, Boolean.class, String.class, String.class, Boolean.class
						};
						public Class getColumnClass(int columnIndex) 
						{
							return columnTypes[columnIndex];
						}
						boolean[] columnEditables = new boolean[] 
						{
							false, false,false, false, true
						};
						public boolean isCellEditable(int row, int column) 
						{
							return columnEditables[column];
						}
					});
				
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
				nuevaOT.getBtnConfirmarSeleccion().setEnabled(false);
				nuevaOT.getTablaOrdenEjecucion().setAutoResizeMode(1);
				
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
				"select * from orden_trabajo where f_prometida>" + fechaHoy
						+ "order by f_prometida limit 0,5;");

		Integer CantColumnas = 14;
		Object datos[] = new Object[CantColumnas]; // Numero de columnas de la
													// tabla

		try {
			while (result.next()) {

				for (int i = 0; i < CantColumnas; i++) {
					datos[i] = result.getObject(i + 1);
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
