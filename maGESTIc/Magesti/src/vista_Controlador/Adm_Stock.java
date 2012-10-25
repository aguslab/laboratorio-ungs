package vista_Controlador;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import Modelo.Calidad;
import Modelo.ConexionDB;
import Modelo.Formato_Papel;
import Modelo.Variante;
import java.awt.ComponentOrientation;

public class Adm_Stock extends JInternalFrame 
{
	private JTable tablaStock;

		public Adm_Stock() 
		{
			super ("Administracion de Stock", false, true, false, true);
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
			btnCerrar.setBounds(d.width-145, d.height-210, 120, 35);
			getContentPane().add(btnCerrar);
			
			JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
			btnConfirmar.setBounds(d.width-280, d.height-210, 120, 35);
			btnConfirmar.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					Integer cantFilasDatos = tablaStock.getRowCount();
					/*
					 * VER SI HACE ALGO EL GUARDAR
					 */
				}
			}
			);
			getContentPane().add(btnConfirmar);
			
			JButton button = new JButton("Solicitud de compra", new ImageIcon ("Imagenes/clientes.png"));
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					final SolicitudDeCompra nuevaSC = new SolicitudDeCompra(false);
					getDesktopPane().add(nuevaSC);
					nuevaSC.show ();
				}
			});
			button.setBounds(d.width-455, d.height-210, 160, 35);
			getContentPane().add(button);
			
			JPanel panStock = new JPanel();
			panStock.setBounds(10, 11, d.width-35, d.height-230);
			getContentPane().add(panStock);
			panStock.setLayout(null);
			
			JScrollPane spStock = new JScrollPane();
			spStock.setBounds(0, 0, d.width-35, d.height-230);
			panStock.add(spStock);
			
			tablaStock = new JTable();
			tablaStock.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Orden de Trabajo", "Solicitud de Compra", "Hojas totales", "Hojas usadas", "Marca", "Calidad", "Formato", "Variante", "Gramaje", "Remanente"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			tablaStock.getColumnModel().getColumn(0).setPreferredWidth(100);
			tablaStock.getColumnModel().getColumn(1).setPreferredWidth(107);
			spStock.setViewportView(tablaStock);
			tablaStock.getTableHeader().setReorderingAllowed(false);
			
			setFilas();
			
		}
			
			private void setFilas() 
			 {
				ResultSet result = ConexionDB
							.getbaseDatos()
							.consultar(
									"SELECT o.nombre_trabajo, sc.id_solicitud_compra, s.cant_hojas_totales, s.cant_hojas_usadas, s.marca, c.nombre, f.tamanio, v.nombre, s.gramaje, s.remanente FROM stock s, orden_trabajo o, solicitud_compra sc, calidad c,formato_papel f, variante v WHERE s.id_solicitud_compra=sc.id_solicitud_compra AND s.id_calidad=c.id_calidad AND f.id_formato_papel=s.id_formato AND v.id_variante=s.id_variante;");
			
				Integer CantColumnas=10;
				Object datos[] = new Object[CantColumnas];
				try 
				{
					DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaStock.getModel();
					while (result.next()) 
					{
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
							if(i==0){
								if(datos[i]==null){
									datos[i] = "Stockear";
								}
							}
							if(i==1){
								datos[i] = Metodos.EnteroAFactura((Integer) datos[1]);	
							}
						}
						tablaTempDatos.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
			
			
}
