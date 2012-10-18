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
					for (int i = 0; i < cantFilasDatos; i++) 
					{
			
						String Nro_Cliente = tablaStock.getValueAt(i, 0).toString();
						String razon_social = tablaStock.getValueAt(i, 1).toString();
						String CUIT = tablaStock.getValueAt(i, 2).toString();
						String cond_IVA = tablaStock.getValueAt(i, 3).toString();
						String direccion = tablaStock.getValueAt(i, 4).toString();
						String telefono = tablaStock.getValueAt(i, 5).toString();
						String mail = tablaStock.getValueAt(i, 6).toString();
						boolean activo = (Boolean) tablaStock.getValueAt(i, 7);
							
						if(Nro_Cliente.equals(""))
						{
						}
						else
						{
							/*boolean result = ConexionDB
									.getbaseDatos()
									.ejecutar("UPDATE );*/
						}
					}
					Actualizar();
				}
			}
			);
			getContentPane().add(btnConfirmar);
			
			JButton button = new JButton("Solicitud de compra", new ImageIcon ("Imagenes/clientes.png"));
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					//Codigo para crear una solicitud de compra
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
			tablaStock.setModel(new DefaultTableModel(new Object[][]{},new String[] 
			{
					"Orden de Trabajo", "Solicitud de Compra", "Cantidad de hojas", "Marca", "Calidad", "Formato", "Variante", "Gramaje", "Precio Unitario", "Unidad Media", "Importe"
			}) 
			{
				Class[] columnTypes = new Class[] 
				{
					String.class, String.class, Integer.class, String.class, String.class, String.class, String.class, Double.class, Double.class, String.class, Double.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tablaStock.getColumnModel().getColumn(0).setResizable(false);
			tablaStock.getColumnModel().getColumn(1).setResizable(false);
			tablaStock.getColumnModel().getColumn(2).setResizable(false);
			tablaStock.getColumnModel().getColumn(3).setResizable(false);
			tablaStock.getColumnModel().getColumn(4).setResizable(false);
			tablaStock.getColumnModel().getColumn(5).setResizable(false);
			tablaStock.getColumnModel().getColumn(6).setResizable(false);
			tablaStock.getColumnModel().getColumn(7).setResizable(false);
			tablaStock.getColumnModel().getColumn(8).setResizable(false);
			tablaStock.getColumnModel().getColumn(9).setResizable(false);
			tablaStock.getColumnModel().getColumn(10).setResizable(false);
			spStock.setViewportView(tablaStock);
			tablaStock.getTableHeader().setReorderingAllowed(false);
			
			// Valores para el combo
			String calidades[] = Calidad.getCalidades();
			TableColumn columnaCalidad = tablaStock.getColumnModel().getColumn(4);//table es la JTable, ponele que la col 0 es la del combo.
			columnaCalidad.setCellEditor(new MyComboBoxEditor(calidades));
			
			// Valores para el combo
			String variantes[] = Variante.getVariantes(); 
			TableColumn columnaVariante = tablaStock.getColumnModel().getColumn(6);//table es la JTable, ponele que la col 0 es la del combo.
			columnaVariante.setCellEditor(new MyComboBoxEditor(variantes));
			
			// Valores para el combo
			String formatos[] = Formato_Papel.getFormatos();
			TableColumn columnaFormato = tablaStock.getColumnModel().getColumn(5);//table es la JTable, ponele que la col 0 es la del combo.
			columnaFormato.setCellEditor(new MyComboBoxEditor(formatos));
		}
			
			private void setFilas() 
			 {
				ResultSet result = ConexionDB
							.getbaseDatos()
							.consultar(
									"SELECT id_cliente, razon_social, cuit, cond_iva, direccion,telefono,mail, activo from cliente");
			
				Integer CantColumnas=8;
				Object datos[] = new Object[CantColumnas];
				try 
				{
					DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaStock.getModel();
					while (result.next()) 
					{
						for (int i = 0; i < CantColumnas; i++) 
						{
							datos[i] = result.getObject(i + 1);
						}
						tablaTempDatos.addRow(datos);
					}
				} 
				catch (Exception e) 
				{
				}
			}
			
			private void Actualizar()
			{
				Metodos.borrarFilas((DefaultTableModel)tablaStock.getModel());
				setFilas();
			}
}
