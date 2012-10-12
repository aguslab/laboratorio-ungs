package vista_Controlador;

import javax.swing.JInternalFrame;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.ConexionDB;

import java.awt.Color;
import java.sql.ResultSet;

public class Adm_Cliente extends JInternalFrame 
{
	private JTable tablaDatosCliente;
	private JTable tablaContactoCliente;
	
	public Adm_Cliente() 
	{
		super ("Administracion de Clientes", false, true, false, true);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

		getContentPane().setLayout(null);
		
		JButton button = new JButton("Cerrar", new ImageIcon ("Imagenes/cerrar3.png"));
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
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
		
		
		JButton btnAgregar = new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));
		btnAgregar.setBounds(10, d.height-305, 120, 35);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaDatosCliente.getModel();
					Object nuevo[]= {""};
					tablaTemp.addRow(nuevo);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar", new ImageIcon ("Imagenes/restar.png"));
		btnBorrar.setBounds(10, d.height-265, 120, 35);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{	
					DefaultTableModel tablaTemp = (DefaultTableModel) tablaDatosCliente.getModel();
					if(tablaTemp.getRowCount()>0)
					{
						tablaTemp.removeRow(tablaDatosCliente.getSelectedRow());
					}
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnBorrar);
		
		//
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-40, d.height-165);

		getContentPane().add(tabbedPane);
		// DATOS
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder
		(
			new LineBorder
			(
				new Color
				(0, 0, 0)
			)
		);
		panelDatos.setLayout(null);
		
		tabbedPane.addTab("Datos                         ",  new ImageIcon ("Imagenes/datos.png"), panelDatos, null);

		JScrollPane spDatosCliente = new JScrollPane();
		spDatosCliente.setViewportBorder(null);
		spDatosCliente.setBounds(0, 0, d.width-210, d.height-165);
		panelDatos.add(spDatosCliente);
		
		tablaDatosCliente = new JTable();
		spDatosCliente.setViewportView(tablaDatosCliente);
		
		tablaDatosCliente.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Mail"}) {
				Class[] columnTypes = new Class[] 
				{
					String.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) 
				{
					return columnTypes[columnIndex];
				}
			});
		
		
		// CONTACTO
		JPanel panelContacto = new JPanel();
		panelContacto.setBorder
		(
			new LineBorder
			(
				new Color(0, 0, 0)
			)
		);
		panelContacto.setLayout(null);
		
		tabbedPane.addTab("Contacto                      ",  new ImageIcon ("Imagenes/contacto.png"), panelContacto, null);
			
		JScrollPane spClienteContacto = new JScrollPane();
		spClienteContacto.setBounds(0, 0, d.width-210, d.height-165);
		panelContacto.add(spClienteContacto);
		
		tablaContactoCliente = new JTable();
		spClienteContacto.setViewportView(tablaContactoCliente);
		tablaContactoCliente.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"Nombre", "Telefono", "Mail", "Direccion de entrega"}) {
				Class[] columnTypes = new Class[] 
				{
					String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) 
				{
					return columnTypes[columnIndex];
				}});
		
		this.setFilas();
		
	}
	
	private void setFilas() 
	 {
		ResultSet result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT razon_social, cuit, cond_iva, direccion,telefono,mail from cliente");
		
			Integer CantColumnas=6;
			Object datos[] = new Object[CantColumnas];
			try 
			{
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatosCliente.getModel();
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
			
			
			CantColumnas=4;
			Object contactos[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT nombre_contacto, telefono_contacto, mail_contacto, direccion_entrega from cliente");
			
			try 
			{
				DefaultTableModel tablaTempContactos = (DefaultTableModel) tablaContactoCliente.getModel();
				while (result.next()) 
				{
					for (int i = 0; i < CantColumnas; i++) 
					{
						contactos[i] = result.getObject(i + 1);
					}
					tablaTempContactos.addRow(contactos);
				}
			} 
			catch (Exception e) 
			{
			}
		}
}
