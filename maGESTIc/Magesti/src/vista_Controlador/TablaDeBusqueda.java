package vista_Controlador;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Elemento;
import Modelo.Formato_Papel;
import Modelo.Hojas_Utilizadas;
import Modelo.Materiales;
import Modelo.Orden_Trabajo;
import Modelo.Proveedor;
import Modelo.Variante;
import Modelo.Procesos_x_OT;

import java.awt.GridLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TablaDeBusqueda extends JInternalFrame 
{
	private JPanel jpMostrar = new JPanel ();
	private static DefaultTableModel dtmMagesti;
	private JScrollPane jspTabla;
	private static JTable tablaBusqueda;
	
	TablaDeBusqueda() 
	{
		super ("Buscar Orden de Trabajo", true, true, true, true);
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
				final OrdenDeTrabajo nuevaOT = new OrdenDeTrabajo ();
				
				getDesktopPane().add(nuevaOT);
				nuevaOT.show ();
				BusquedaOrdenTrabajo bot= new BusquedaOrdenTrabajo(nuevaOT, tablaBusqueda,filaElegida);
			}
		});
		
		
		getContentPane().add (jpMostrar);
		
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
			String columna[] = Orden_Trabajo.getNomColum();
			return columna;
		}

		
		
		private static void setFilas() 
		 {

			ResultSet result = ConexionDB.getbaseDatos().consultar(
								"SELECT o.id_orden_trabajo,o.nombre_producto, c.razon_social, o.f_confeccion,o.f_prometida,o.nombre_trabajo,o.descripcion,o.cantidad_a_entregar, o.cantidad_preimpresion, o.ancho,o.alto, o.apaisado,o.estado,o.hojas_utilizadas FROM orden_trabajo o, cliente c WHERE o.id_cliente=c.id_cliente order by id_orden_trabajo");
			
				Integer CantColumnas=14;
				Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

				try 
				{
					while (result.next()) 
					{
						
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
							if(i==0){
								datos[i]=Metodos.EnteroAFactura((Integer) datos[0]);
							}
							if (i==11)
							{
								datos[i]=Metodos.esApaisadaS((Boolean) datos[11]);
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
		
		static void Actualizar()
		{
			try
			{
				Metodos.borrarFilas((DefaultTableModel)tablaBusqueda.getModel());
				setFilas();
			}
			catch(Exception e)
			{
				
			}
		}
		
}