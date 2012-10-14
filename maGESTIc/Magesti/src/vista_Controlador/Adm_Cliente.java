package vista_Controlador;

import javax.swing.JInternalFrame;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
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
		button.setBounds(10, d.height-250, 120, 30);
		getContentPane().add(button);
		
		JButton btnConfirmar = new JButton("Guardar", new ImageIcon ("Imagenes/confirmar3.png"));
		btnConfirmar.setBounds(10, d.height-210, 120, 30);
		btnConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Integer cantFilasDatos = tablaDatosCliente.getRowCount();
				for (int i = 0; i < cantFilasDatos; i++) 
				{

					String Nro_Cliente = tablaDatosCliente.getValueAt(i, 0).toString();
					String razon_social = tablaDatosCliente.getValueAt(i, 1).toString();
					String CUIT = tablaDatosCliente.getValueAt(i, 2).toString();
					String cond_IVA = tablaDatosCliente.getValueAt(i, 3).toString();
					String direccion = tablaDatosCliente.getValueAt(i, 4).toString();
					String telefono = tablaDatosCliente.getValueAt(i, 5).toString();
					String mail = tablaDatosCliente.getValueAt(i, 6).toString();
					boolean activo = (Boolean) tablaDatosCliente.getValueAt(i, 7);
					
					String nombre_contacto = tablaContactoCliente.getValueAt(i, 1).toString();
					String telefono_contacto = tablaContactoCliente.getValueAt(i, 2).toString();
					String mail_contacto = tablaContactoCliente.getValueAt(i, 3).toString();
					String direccion_entrega = tablaContactoCliente.getValueAt(i, 4).toString();
						
					if(Nro_Cliente.equals(""))
					{
						Cliente cli = new Cliente(razon_social,CUIT,cond_IVA,direccion,telefono,
								mail,nombre_contacto,telefono_contacto,mail_contacto,direccion_entrega, activo);
						cli.Alta();
					}
					else
					{
						boolean result = ConexionDB
								.getbaseDatos()
								.ejecutar("UPDATE cliente SET razon_social = "+ "'"+razon_social+"'" + ",cuit = " + CUIT + ",cond_iva = "
								+"'" +cond_IVA+"'" + ",direccion = " + "'"+direccion+"'" + ",telefono = " + "'"+telefono+"'" + ",mail = " + "'"+ mail + "'" + ",nombre_contacto =" +
								"'"+nombre_contacto+"'" + ",telefono_contacto = "+ telefono_contacto + ",mail_contacto = " + "'"+mail_contacto+"'" + ",direccion_entrega = " + "'"+direccion_entrega+"'"
								+ ",activo = " + activo + " WHERE id_cliente =" + Integer.parseInt(Nro_Cliente));
					}
				}
				JOptionPane.showMessageDialog(null,"Se guardaron los cambios realizados");
				Actualizar();
			}
		}
		);
		getContentPane().add(btnConfirmar);
		
		JButton btnAgregar = new JButton("Agregar", new ImageIcon ("Imagenes/sumar.png"));
		btnAgregar.setBounds(10, d.height-290, 120, 30);
		btnAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				DefaultTableModel tablaTempDatos = (DefaultTableModel) tablaDatosCliente.getModel();
				Object nuevaFilaDatos[]= {"","","","","","","",true};
				tablaTempDatos.addRow(nuevaFilaDatos);
				
				DefaultTableModel tablaTempContacto = (DefaultTableModel) tablaContactoCliente.getModel();
				Object nuevaFilaContacto[]= {"","","","",""};
				tablaTempContacto.addRow(nuevaFilaContacto);
			}
		}
		);
		getContentPane().add(btnAgregar);
		
		//
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBounds(10, 11, d.width-30, d.height-190);

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
		spDatosCliente.setBounds(0, 0, d.width-193, d.height-190);
		panelDatos.add(spDatosCliente);
		
		tablaDatosCliente = new JTable();
		spDatosCliente.setViewportView(tablaDatosCliente);
		
		tablaDatosCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Razon Social", "CUIT", "Cond. IVA", "Direccion", "Telefono", "Mail", "Activo"
			}
		) {
			Class[] columnTypes = new Class[] 
			{
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
			{
					false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		tablaDatosCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
		tablaDatosCliente.getColumnModel().getColumn(1).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(1).setPreferredWidth(130);
		tablaDatosCliente.getColumnModel().getColumn(2).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(2).setPreferredWidth(102);
		tablaDatosCliente.getColumnModel().getColumn(3).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(3).setPreferredWidth(101);
		tablaDatosCliente.getColumnModel().getColumn(4).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(4).setPreferredWidth(225);
		tablaDatosCliente.getColumnModel().getColumn(5).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(6).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(6).setPreferredWidth(137);
		tablaDatosCliente.getColumnModel().getColumn(7).setResizable(false);
		tablaDatosCliente.getColumnModel().getColumn(7).setPreferredWidth(15);
		tablaDatosCliente.getTableHeader().setReorderingAllowed(false);
		
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
		
		tabbedPane.addTab("Contacto                   ",  new ImageIcon ("Imagenes/contacto.png"), panelContacto, null);
			
		JScrollPane spClienteContacto = new JScrollPane();
		spClienteContacto.setBounds(0, 0, d.width-193, d.height-190);
		panelContacto.add(spClienteContacto);
		
		tablaContactoCliente = new JTable();
		spClienteContacto.setViewportView(tablaContactoCliente);
		tablaContactoCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Nombre", "Telefono", "Mail", "Direccion de entrega"
			}
		) {
			Class[] columnTypes = new Class[] 
			{
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) 
			{
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
			{
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) 
			{
				return columnEditables[column];
			}
		});
		tablaContactoCliente.getColumnModel().getColumn(0).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(0).setPreferredWidth(15);
		tablaContactoCliente.getColumnModel().getColumn(1).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(1).setPreferredWidth(195);
		tablaContactoCliente.getColumnModel().getColumn(2).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(2).setPreferredWidth(115);
		tablaContactoCliente.getColumnModel().getColumn(3).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(3).setPreferredWidth(130);
		tablaContactoCliente.getColumnModel().getColumn(4).setResizable(false);
		tablaContactoCliente.getColumnModel().getColumn(4).setPreferredWidth(240);
		tablaContactoCliente.getTableHeader().setReorderingAllowed(false);
		this.setFilas();
		
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
			
			
			CantColumnas=5;
			Object contactos[] = new Object[CantColumnas];
			result = ConexionDB
					.getbaseDatos()
					.consultar(
							"SELECT id_cliente, nombre_contacto, telefono_contacto, mail_contacto, direccion_entrega from cliente");
			
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
	
		private void Actualizar()
		{
			/*tablaDatos.removeAll();
			tablaContacto.removeAll();
			setFilas();*/
		}
}
