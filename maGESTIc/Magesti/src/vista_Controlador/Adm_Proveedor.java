package vista_Controlador;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Adm_Proveedor extends JInternalFrame 
{
	private JTable tablaDatos;
	private JTable tablaContacto;
	
	public Adm_Proveedor() 
	{
		super ("Administracion de Proveedor", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		
		JButton button = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible (false);
				dispose();
			}
		});
		button.setBounds(10, d.height-225, 120, 35);
		getContentPane().add(button);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-185, 120, 35);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
			}
		}
		);
		getContentPane().add(btnConfirmar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, d.height-305, 120, 35);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaDatos.getModel();
					Object nuevo[]= {""};
					tablaTemp.addRow(nuevo);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(10, d.height-265, 120, 35);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaDatos.getModel();
					if(tablaTemp.getRowCount()>0)
					{
						tablaTemp.removeRow(tablaDatos.getSelectedRow());
					}
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnBorrar);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-40, d.height-165);
		getContentPane().add(tabbedPane);
		
		JPanel panDatos = new JPanel();
		tabbedPane.addTab("Datos                      ",  new ImageIcon ("Imagenes/datos.png"), panDatos, null);
		panDatos.setLayout(null);
		
		JScrollPane spDatos = new JScrollPane();
		spDatos.setBounds(0, 0, d.width-210, d.height-165);
		panDatos.add(spDatos);
		
		tablaDatos = new JTable();
		tablaDatos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Mail"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaDatos.getColumnModel().getColumn(0).setResizable(false);
		tablaDatos.getColumnModel().getColumn(0).setPreferredWidth(120);
		tablaDatos.getColumnModel().getColumn(1).setResizable(false);
		tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(102);
		tablaDatos.getColumnModel().getColumn(2).setResizable(false);
		tablaDatos.getColumnModel().getColumn(2).setPreferredWidth(101);
		tablaDatos.getColumnModel().getColumn(3).setResizable(false);
		tablaDatos.getColumnModel().getColumn(3).setPreferredWidth(178);
		tablaDatos.getColumnModel().getColumn(4).setResizable(false);
		tablaDatos.getColumnModel().getColumn(5).setResizable(false);
		tablaDatos.getColumnModel().getColumn(5).setPreferredWidth(137);
		spDatos.setViewportView(tablaDatos);
		
		tablaDatos.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaDatos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		JPanel panContacto = new JPanel();
		tabbedPane.addTab("Contacto                      ", new ImageIcon ("Imagenes/contacto.png"), panContacto, null);
		panContacto.setLayout(null);
		
		JScrollPane spContacto = new JScrollPane();
		spContacto.setBounds(0, 0, d.width-210, d.height-165);
		panContacto.add(spContacto);
		
		tablaContacto = new JTable();
		tablaContacto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Telefono", "Mail", "Direccion de retiro"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaContacto.getColumnModel().getColumn(0).setResizable(false);
		tablaContacto.getColumnModel().getColumn(0).setPreferredWidth(155);
		tablaContacto.getColumnModel().getColumn(1).setResizable(false);
		tablaContacto.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaContacto.getColumnModel().getColumn(2).setResizable(false);
		tablaContacto.getColumnModel().getColumn(2).setPreferredWidth(119);
		tablaContacto.getColumnModel().getColumn(3).setResizable(false);
		tablaContacto.getColumnModel().getColumn(3).setPreferredWidth(179);
		spContacto.setViewportView(tablaContacto);
		
		tablaContacto.setPreferredScrollableViewportSize(new Dimension(1100, 500));
		tablaContacto.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tablaContacto.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		this.setFilas();

	}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT razon_social, cuit, cond_iva, direccion,telefono,mail from proveedor");
		
			Integer CantColumnas=6;
			Object datos[] = new Object[CantColumnas]; // Numero de columnas de la tabla

			try 
			{
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatos.getModel();
				while (result.next()) 
				{
					
					for (int i = 0; i < CantColumnas; i++) 
					{
						datos[i] = result.getObject(i + 1);
					}
					tablaTempDatos.addRow(datos);
				}
				// result.close();
			} 
			catch (Exception e) 
			{
			}
			
			
			CantColumnas=4;
			Object contactos[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT nombre_contacto, telefono_contacto, mail_contacto, direccion_retiro from proveedor");
			
			try 
			{
				DefaultTableModel tablaTempContactos = (DefaultTableModel) tablaContacto.getModel();
				while (result.next()) 
				{
					
					for (int i = 0; i < CantColumnas; i++) 
					{
						contactos[i] = result.getObject(i + 1);
					}
					tablaTempContactos.addRow(contactos);
				}
				// result.close();
			} 
			catch (Exception e) 
			{
			}
		}
}
