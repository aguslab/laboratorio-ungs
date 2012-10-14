package vista_Controlador;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Detalle;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Proveedor;
import Modelo.Solicitud_compra;
import Modelo.Variante;

import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TablaDeBusqueda_SC extends JInternalFrame 
{
	private JPanel jpMostrar = new JPanel ();
	private DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private JTable tablaBusqueda;
	
	TablaDeBusqueda_SC(String titulo) 
	{
		super (titulo, true, true, true, true);
		setSize (475, 280);
		jpMostrar.setLayout (new GridLayout (1,1));
		jspTabla = new JScrollPane (tablaBusqueda);
		jpMostrar.add (jspTabla);
		tablaBusqueda = new JTable();
		tablaBusqueda.setEnabled(false);
		tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		
		tablaBusqueda.addMouseListener
		(
			new MouseAdapter() 
			{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				int filaElegida = tablaBusqueda.rowAtPoint(arg0.getPoint());
				final SolicitudDeCompra nuevaSC = new SolicitudDeCompra(false);
				
				getDesktopPane().add(nuevaSC);
				nuevaSC.show ();
				nuevaSC.getBtnAgregar().setEnabled(false);
				nuevaSC.getBtnAlmacenar().setEnabled(false);
				nuevaSC.getBtnBorrar().setEnabled(false);
				
				
				
				//Cargo en la ventana de OT los valores de la fila elegida
				Integer id_SC=(Integer)tablaBusqueda.getValueAt(filaElegida, 0);
				nuevaSC.getTxtNumero().setText(Orden_Trabajo.EnteroAFactura(id_SC));
				Date f=(Date) tablaBusqueda.getValueAt(filaElegida, 1);
				
				nuevaSC.getCbProveedor().setSelectedItem(tablaBusqueda.getValueAt(filaElegida, 2));
				nuevaSC.getCbProveedor().setEnabled(false);
				
				nuevaSC.getTxtFecha().setText(f.toString());
				nuevaSC.getTxtVendedor().setText((String) tablaBusqueda.getValueAt(filaElegida, 3));
				nuevaSC.getTxtVendedor().setEditable(false);
				
				String nom_ot=tablaBusqueda.getValueAt(filaElegida, 4).toString();
				nom_ot=Orden_Trabajo.getId_Con_nom_OT(nom_ot);
				nuevaSC.getCbNroOT().setSelectedItem(nom_ot);
				nuevaSC.getCbNroOT().setEnabled(false);
				
				
				//si envia a proveedor...
				if(tablaBusqueda.getValueAt(filaElegida, 5).toString().equals("true")){
					nuevaSC.getRdbtnEnviarAProveedor().setSelected(true);
					nuevaSC.getTxtDireccionRetiro().setEnabled(false);
				}else{
					nuevaSC.getRdbtnRetirar().setSelected(true);
					nuevaSC.getTxtDireccionRetiro().setText(tablaBusqueda.getValueAt(filaElegida, 6).toString());
					nuevaSC.getTxtDireccionRetiro().setEditable(false);
				}
				nuevaSC.getRdbtnEnviarAProveedor().setEnabled(false);
				nuevaSC.getRdbtnRetirar().setEnabled(false);
				
				
				nuevaSC.getCbMes().getModel().setSelectedItem(dameMes(separar(tablaBusqueda.getValueAt(filaElegida, 7).toString(), 1)));
				nuevaSC.getCbMes().setEnabled(false);
				
				nuevaSC.getCbDia().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 7).toString(), 2));
				nuevaSC.getCbDia().setEnabled(false);
				
				nuevaSC.getCbAnio().getModel().setSelectedItem(separar(tablaBusqueda.getValueAt(filaElegida, 7).toString(), 0));
				nuevaSC.getCbAnio().setEnabled(false);
				
				
				//si horario entrega es M
				if(tablaBusqueda.getValueAt(filaElegida, 8).equals("M")){
					nuevaSC.getRbManiana().setSelected(true);
				}else{
					nuevaSC.getRbTarde().setSelected(true);
				}
				nuevaSC.getRbTarde().setEnabled(false);
				nuevaSC.getRbManiana().setEnabled(false);
				
				nuevaSC.getTxtSubtotal().setText(SolicitudDeCompra.pasarAPesos(tablaBusqueda.getValueAt(filaElegida, 9).toString()));
				nuevaSC.getTxtIVA().setText(tablaBusqueda.getValueAt(filaElegida, 10).toString()+" %");
				nuevaSC.getTxtMontoIVA().setText(SolicitudDeCompra.pasarAPesos(tablaBusqueda.getValueAt(filaElegida, 11).toString()));
				nuevaSC.getTxtTotal().setText(SolicitudDeCompra.pasarAPesos(tablaBusqueda.getValueAt(filaElegida, 12).toString()));
				
				
				
				/*
				 *	Muestra los datos de la tabla Elemento 
				 */
				
								
				ArrayList<Integer> cantidad = Detalle.cantidadDeDetalle(id_SC);
				ArrayList<String> marca = Detalle.marcaDeDetalle(id_SC);
				ArrayList<Integer> id_Calidad = Detalle.calidadDeDetalle(id_SC);
				ArrayList<Integer> id_formato_Papel = Detalle.formato_papel_DeDetalle(id_SC);
				ArrayList<Integer> id_Variante = Detalle.varianteDeDetalle(id_SC);
				ArrayList<Integer> gramaje = Detalle.gramajeDeDetalle(id_SC);
				ArrayList<Double> precio_Unitario= Detalle.precioUnitarioDeDetalle(id_SC);
				ArrayList<String> unidad_medida= Detalle.unidadMedidaDeDetalle(id_SC);
				ArrayList<Double> importe= Detalle.importeDeDetalle(id_SC);
				
				
				DefaultTableModel temp = (DefaultTableModel) nuevaSC.getTablaDetalles().getModel();
				Object nuevaFilaElemento[]= {"",""};
				
				Integer cantFilas = Detalle.cantidadFilas(id_SC);
				for (int i = 0; i < cantFilas; i++) 
				{
					temp.addRow(nuevaFilaElemento);
					temp.setValueAt(cantidad.get(i), i, 0);
					temp.setValueAt(marca.get(i), i, 1);
					temp.setValueAt(Calidad.getNombre(id_Calidad.get(i)), i, 2);	
					temp.setValueAt((Formato_Papel.getTamanio(id_formato_Papel.get(i))), i, 3);	
					temp.setValueAt(Variante.getNombre(id_Variante.get(i)), i, 4);
					temp.setValueAt(gramaje.get(i), i, 5);
					temp.setValueAt(precio_Unitario.get(i), i, 6);
					temp.setValueAt(unidad_medida.get(i), i, 7);
					temp.setValueAt(importe.get(i), i, 8);
					
				}
				nuevaSC.getBtnAgregar().setEnabled(false);
				nuevaSC.getBtnBorrar().setEnabled(false);
				nuevaSC.getBtnAlmacenar().setEnabled(false);
				
				nuevaSC.getTablaDetalles().setEnabled(false);
								
			}
		});
		
		
		
		getContentPane().add (jpMostrar);
		dtmMagesti = new DefaultTableModel(null, getColumnas());
		setFilas();
		tablaBusqueda.setModel(dtmMagesti);
		jspTabla.add(tablaBusqueda);
		jspTabla.setViewportView(tablaBusqueda);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);	
	
			// Llenamos el modelo
		dtmMagesti = new DefaultTableModel(null, getColumnas());

			setFilas();

			tablaBusqueda.setModel(dtmMagesti);
			jspTabla.add(tablaBusqueda);
			this.setSize(500, 200);

			jspTabla.setViewportView(tablaBusqueda);

			setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	}
		

		// Encabezados de la tabla
		private String[] getColumnas() 
		{
			String columna[] = Solicitud_compra.getNomColumAMostrar();
			return columna;
		}

		
		
		private void setFilas() 
		 {
			Calendar fecha= Calendar.getInstance();
			Integer mm=fecha.get(Calendar.MONTH)+1;
			Integer dd=fecha.get(Calendar.DATE);
			Integer aaaa=fecha.get(Calendar.YEAR);	
			String fechaHoy="'"+aaaa+"-"+mm+"-"+dd+"'";
			
			ResultSet result=ConexionDB.getbaseDatos().consultar(
			"SELECT s.id_solicitud_compra, s.f_confeccion, p.razon_social, s.vendedor, o.nombre_trabajo, s.envia_proveedor, s.direccion_retiro, s.f_entrega, s.horario_entrega, s.subtotal, s.porcentaje_iva, s.monto_iva, s.total FROM solicitud_compra s, orden_trabajo o, proveedor p where o.id_orden_trabajo=s.id_orden_trabajo AND s.id_proveedor=p.id_proveedor order by id_solicitud_compra");
						
			
				Integer CantColumnas=13;
				// Numero de columnas de la tabla.Si se agregan columnas, se agregan aca tmb
				Object datos[] = new Object[getColumnas().length]; 

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